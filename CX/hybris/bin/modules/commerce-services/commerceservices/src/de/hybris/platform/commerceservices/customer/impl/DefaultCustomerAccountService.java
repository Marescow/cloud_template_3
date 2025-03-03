/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commerceservices.customer.impl;

import static de.hybris.platform.commerceservices.constants.CommerceServicesConstants.CONSENT_GIVEN;
import static de.hybris.platform.commerceservices.constants.CommerceServicesConstants.POPULATING_CONSENTS_ENABLED;
import static de.hybris.platform.commerceservices.constants.CommerceServicesConstants.USER_CONSENTS;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.consent.CommerceConsentService;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.customer.CustomerEmailResolutionService;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.PasswordMismatchException;
import de.hybris.platform.commerceservices.customer.TokenInvalidatedException;
import de.hybris.platform.commerceservices.customer.dao.CustomerAccountDao;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.commerceservices.event.ChangeUIDEvent;
import de.hybris.platform.commerceservices.event.ClosedAccountEvent;
import de.hybris.platform.commerceservices.event.ForgottenPwdEvent;
import de.hybris.platform.commerceservices.event.RegisterEvent;
import de.hybris.platform.commerceservices.event.SavedAddressEvent;
import de.hybris.platform.commerceservices.event.UpdatedProfileEvent;
import de.hybris.platform.commerceservices.event.DeletedAddressEvent;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.commerceservices.model.consent.ConsentTemplateModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commerceservices.security.SecureToken;
import de.hybris.platform.commerceservices.security.SecureTokenService;
import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.dto.NewSubscription;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.impl.UniqueAttributesInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.PasswordEncoderConstants;
import de.hybris.platform.servicelayer.user.PasswordEncoderService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.Assert;


/**
 * Default implementation of {@link CustomerAccountService}
 */
public class DefaultCustomerAccountService implements CustomerAccountService
{
	private static final Logger LOG = Logger.getLogger(DefaultCustomerAccountService.class);
	private CommerceConsentService commerceConsentService;
	private SessionService sessionService;
	private UserService userService;
	private PaymentService paymentService;
	private ModelService modelService;
	private FlexibleSearchService flexibleSearchService;
	private I18NService i18nService;
	private PasswordEncoderService passwordEncoderService;
	private SecureTokenService secureTokenService;
	private long tokenValiditySeconds;
	private CustomerAccountDao customerAccountDao;
	private BaseStoreService baseStoreService;
	private BaseSiteService baseSiteService;
	private EventService eventService;
	private CommonI18NService commonI18NService;
	private TypeService typeService;
	private CustomerEmailResolutionService customerEmailResolutionService;
	private String passwordEncoding = PasswordEncoderConstants.DEFAULT_ENCODING;
	private CustomerNameStrategy customerNameStrategy;
	private String monthsForOrderExpiry;
	private TimeService timeService;
	private CommerceCommonI18NService commerceCommonI18NService;
	private ConfigurationService configurationService;

	protected void addPaymentInfo(final CustomerModel customerModel, final PaymentInfoModel paymentInfoModel)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(paymentInfoModel, "Payment info model cannot be null");
		final List<PaymentInfoModel> paymentInfoModels = new ArrayList<PaymentInfoModel>(customerModel.getPaymentInfos());
		if (!paymentInfoModels.contains(paymentInfoModel))
		{
			paymentInfoModels.add(paymentInfoModel);
			customerModel.setPaymentInfos(paymentInfoModels);

			getModelService().saveAll(customerModel, paymentInfoModel);
			getModelService().refresh(customerModel);
		}
	}

	@Override
	public CreditCardPaymentInfoModel createPaymentSubscription(final CustomerModel customerModel, final CardInfo cardInfo,
			final BillingInfo billingInfo, final String titleCode, final String paymentProvider, final boolean saveInAccount)
	{
		validateParameterNotNull(customerModel, "Customer cannot be null");
		validateParameterNotNull(cardInfo, "CardInfo cannot be null");
		validateParameterNotNull(billingInfo, "billingInfo cannot be null");
		validateParameterNotNull(paymentProvider, "PaymentProvider cannot be null");
		final CurrencyModel currencyModel = getCurrency(customerModel);
		validateParameterNotNull(currencyModel, "Customer session currency cannot be null");

		final Currency currency = getI18nService().getBestMatchingJavaCurrency(currencyModel.getIsocode());

		final AddressModel billingAddress = getModelService().create(AddressModel.class);
		if (StringUtils.isNotBlank(titleCode))
		{
			final TitleModel title = new TitleModel();
			title.setCode(titleCode);
			billingAddress.setTitle(getFlexibleSearchService().getModelByExample(title));
		}
		billingAddress.setFirstname(billingInfo.getFirstName());
		billingAddress.setLastname(billingInfo.getLastName());
		billingAddress.setLine1(billingInfo.getStreet1());
		billingAddress.setLine2(billingInfo.getStreet2());
		billingAddress.setTown(billingInfo.getCity());
		billingAddress.setPostalcode(billingInfo.getPostalCode());
		billingAddress.setCountry(getCommonI18NService().getCountry(billingInfo.getCountry()));
		if (billingInfo.getRegion() != null)
		{
			billingAddress.setRegion(getCommonI18NService().getRegion(billingAddress.getCountry(), billingInfo.getRegion()));
		}
		billingAddress.setPhone1(billingInfo.getPhoneNumber());
		final String email = getCustomerEmailResolutionService().getEmailForCustomer(customerModel);
		billingAddress.setEmail(email);

		final String merchantTransactionCode = customerModel.getUid() + "-" + UUID.randomUUID();
		try
		{
			final NewSubscription subscription = getPaymentService().createSubscription(merchantTransactionCode, paymentProvider,
					currency, billingAddress, cardInfo);

			if (StringUtils.isNotBlank(subscription.getSubscriptionID()))
			{
				final CreditCardPaymentInfoModel cardPaymentInfoModel = getModelService().create(CreditCardPaymentInfoModel.class);
				cardPaymentInfoModel.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
				cardPaymentInfoModel.setUser(customerModel);
				cardPaymentInfoModel.setSubscriptionId(subscription.getSubscriptionID());
				cardPaymentInfoModel.setNumber(getMaskedCardNumber(cardInfo.getCardNumber()));
				cardPaymentInfoModel.setType(cardInfo.getCardType());
				cardPaymentInfoModel.setCcOwner(cardInfo.getCardHolderFullName());
				cardPaymentInfoModel.setValidToMonth(String.format("%02d", cardInfo.getExpirationMonth()));
				cardPaymentInfoModel.setValidToYear(String.valueOf(cardInfo.getExpirationYear()));
				if (cardInfo.getIssueMonth() != null)
				{
					cardPaymentInfoModel.setValidFromMonth(String.valueOf(cardInfo.getIssueMonth()));
				}
				if (cardInfo.getIssueYear() != null)
				{
					cardPaymentInfoModel.setValidFromYear(String.valueOf(cardInfo.getIssueYear()));
				}

				cardPaymentInfoModel.setSubscriptionId(subscription.getSubscriptionID());
				cardPaymentInfoModel.setSaved(saveInAccount);
				if (!StringUtils.isEmpty(cardInfo.getIssueNumber()))
				{
					cardPaymentInfoModel.setIssueNumber(Integer.valueOf(cardInfo.getIssueNumber()));
				}

				billingAddress.setOwner(cardPaymentInfoModel);
				cardPaymentInfoModel.setBillingAddress(billingAddress);

				getModelService().saveAll(billingAddress, cardPaymentInfoModel);
				getModelService().refresh(customerModel);

				addPaymentInfo(customerModel, cardPaymentInfoModel);

				return cardPaymentInfoModel;
			}
		}
		catch (final AdapterException ae)
		{
			LOG.error("Failed to create subscription for customer. Customer PK: " + String.valueOf(customerModel.getPk())
					+ " Exception: " + ae.getClass().getName());

			return null;
		}

		return null;
	}

	protected CurrencyModel getCurrency(final CustomerModel customerModel)
	{
		if (customerModel != null && customerModel.getSessionCurrency() != null)
		{
			return customerModel.getSessionCurrency();
		}
		return getCommonI18NService().getCurrentCurrency();
	}

	protected String getMaskedCardNumber(final String cardNumber)
	{
		if (cardNumber != null && cardNumber.trim().length() > 4)
		{
			final String endPortion = cardNumber.trim().substring(cardNumber.length() - 4);
			return "************" + endPortion;
		}
		return null;
	}

	@Override
	public void setDefaultPaymentInfo(final CustomerModel customerModel, final PaymentInfoModel paymentInfoModel)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(paymentInfoModel, "Payment info model cannot be null");
		if (customerModel.getPaymentInfos().contains(paymentInfoModel))
		{
			customerModel.setDefaultPaymentInfo(paymentInfoModel);
			getModelService().save(customerModel);
			getModelService().refresh(customerModel);
		}
	}

	@Override
	public List<CreditCardPaymentInfoModel> getCreditCardPaymentInfos(final CustomerModel customerModel, final boolean saved)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		return getCustomerAccountDao().findCreditCardPaymentInfosByCustomer(customerModel, saved);
	}

	@Override
	public CreditCardPaymentInfoModel getCreditCardPaymentInfoForCode(final CustomerModel customerModel, final String code)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		return getCustomerAccountDao().findCreditCardPaymentInfoByCustomer(customerModel, code);
	}

	@Override
	public List<AddressModel> getAllAddressEntries(final CustomerModel customerModel)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		final List<AddressModel> addressModels = new ArrayList<AddressModel>();
		addressModels.addAll(customerModel.getAddresses());
		return addressModels;
	}

	@Override
	public List<AddressModel> getAddressBookEntries(final CustomerModel customerModel)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		final List<AddressModel> addressModels = new ArrayList<AddressModel>();

		for (final AddressModel address : customerModel.getAddresses())
		{
			if (Boolean.TRUE.equals(address.getVisibleInAddressBook()))
			{
				addressModels.add(address);
			}
		}

		return addressModels;
	}

	@Override
	public List<AddressModel> getAddressBookDeliveryEntries(final CustomerModel customerModel)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		return getCustomerAccountDao().findAddressBookDeliveryEntriesForCustomer(customerModel,
				getCommerceCommonI18NService().getAllCountries());
	}

	@Override
	public AddressModel getAddressForCode(final CustomerModel customerModel, final String code)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");

		for (final AddressModel addressModel : getAllAddressEntries(customerModel))
		{
			if (addressModel.getPk().getLongValueAsString().equals(code))
			{
				return addressModel;
			}
		}
		return null;
	}

	@Override
	public AddressModel getDefaultAddress(final CustomerModel customerModel)
	{
		return customerModel.getDefaultShipmentAddress();
	}

	@Override
	public void saveAddressEntry(final CustomerModel customerModel, final AddressModel addressModel)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(addressModel, "Address model cannot be null");
		final List<AddressModel> customerAddresses = new ArrayList<AddressModel>();
		customerAddresses.addAll(customerModel.getAddresses());
		if (customerModel.getAddresses().contains(addressModel))
		{
			getModelService().save(addressModel);
		}
		else
		{
			addressModel.setOwner(customerModel);
			getModelService().save(addressModel);
			getModelService().refresh(addressModel);
			customerAddresses.add(addressModel);
		}
		customerModel.setAddresses(customerAddresses);

		getModelService().save(customerModel);
		getModelService().refresh(customerModel);
		getEventService().publishEvent(initializeEvent(new SavedAddressEvent(), customerModel));
	}

	@Override
	public void deleteAddressEntry(final CustomerModel customerModel, final AddressModel addressModel)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(addressModel, "Address model cannot be null");

		if (customerModel.getAddresses().contains(addressModel))
		{
			final boolean changeDefaultAddress = addressModel.equals(getDefaultAddress(customerModel));

			getModelService().remove(addressModel);
			getModelService().refresh(customerModel);

			final Iterator<AddressModel> addressIterator = customerModel.getAddresses().iterator();
			if (changeDefaultAddress && addressIterator.hasNext())
			{
				setDefaultAddressEntry(customerModel, addressIterator.next());
			}
			getEventService().publishEvent(initializeEvent(new DeletedAddressEvent(), customerModel));
		}
		else
		{
			throw new IllegalArgumentException(
					"Address " + addressModel + " does not belong to the customer " + customerModel + " and will not be removed.");
		}
	}

	@Override
	public void deleteCCPaymentInfo(final CustomerModel customerModel, final CreditCardPaymentInfoModel creditCardPaymentInfo)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(creditCardPaymentInfo, "CreditCardPaymentInfo model cannot be null");
		if (customerModel.getPaymentInfos().contains(creditCardPaymentInfo))
		{
			creditCardPaymentInfo.setSaved(false);
			getModelService().save(creditCardPaymentInfo);
			getModelService().refresh(customerModel);
		}
		else
		{
			throw new IllegalArgumentException("Credit Card Payment Info " + creditCardPaymentInfo
					+ " does not belong to the customer " + customerModel + " and will not be removed.");
		}
	}

	/**
	 * @deprecated Since 6.7. Use
	 *             {@link CustomerAccountService#deleteCCPaymentInfo(CustomerModel, CreditCardPaymentInfoModel)}
	 */
	@Deprecated(since = "6.7", forRemoval = true)
	@Override
	public void unlinkCCPaymentInfo(final CustomerModel customerModel, final CreditCardPaymentInfoModel creditCardPaymentInfo)
	{
		deleteCCPaymentInfo(customerModel, creditCardPaymentInfo);
	}

	@Override
	public void setDefaultAddressEntry(final CustomerModel customerModel, final AddressModel addressModel)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(addressModel, "Address model cannot be null");
		if (customerModel.getAddresses().contains(addressModel))
		{
			customerModel.setDefaultPaymentAddress(addressModel);
			customerModel.setDefaultShipmentAddress(addressModel);
		}
		else
		{
			final AddressModel clone = getModelService().clone(addressModel);
			clone.setOwner(customerModel);
			getModelService().save(clone);
			final List<AddressModel> customerAddresses = new ArrayList<AddressModel>();
			customerAddresses.addAll(customerModel.getAddresses());
			customerAddresses.add(clone);
			customerModel.setAddresses(customerAddresses);
			customerModel.setDefaultPaymentAddress(clone);
			customerModel.setDefaultShipmentAddress(clone);
		}
		getModelService().save(customerModel);
		getModelService().refresh(customerModel);
	}

	@Override
	public void clearDefaultAddressEntry(final CustomerModel customerModel)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		customerModel.setDefaultPaymentAddress(null);
		customerModel.setDefaultShipmentAddress(null);
		getModelService().save(customerModel);
		getModelService().refresh(customerModel);
	}

	@Override
	public Collection<TitleModel> getTitles()
	{
		return getUserService().getAllTitles();
	}

	@Override
	public void register(final CustomerModel customerModel, final String password) throws DuplicateUidException
	{
		registerCustomer(customerModel, password);
		getEventService().publishEvent(initializeEvent(new RegisterEvent(), customerModel));
		populateAndGetSessionConsentsToRegisteredUser(customerModel, getBaseSiteService().getCurrentBaseSite());
	}

	protected List<ConsentTemplateModel> populateAndGetSessionConsentsToRegisteredUser(final CustomerModel customerModel,
			final BaseSiteModel baseSiteModel)
	{
		final Boolean shouldPopulateConsents = getSessionService().getAttribute(POPULATING_CONSENTS_ENABLED);
		final Map<String, String> anonymousConsents = getSessionService().getAttribute(USER_CONSENTS);

		if (BooleanUtils.isNotTrue(shouldPopulateConsents))
		{
			LOG.debug("Not populating new user with session consents due to populate consent flag not set or false");
			return List.of();
		}

		if (!getUserService().isAnonymousUser(getUserService().getCurrentUser()))
		{
			LOG.info("Session consents are only populated to user being registered a.k.a. anonymous during registration process");
			return List.of();
		}

		final List<ConsentTemplateModel> sessionConsents = MapUtils.emptyIfNull(anonymousConsents).entrySet().stream() //
				.filter(entry -> CONSENT_GIVEN.equals(entry.getValue())) //
				.map(entry -> getCommerceConsentService().getLatestConsentTemplate(entry.getKey(), baseSiteModel)) //
				.collect(Collectors.toList());
		sessionConsents.forEach(consentTemplate -> getCommerceConsentService().giveConsent(customerModel, consentTemplate));
		return sessionConsents;
	}

	@Override
	public void registerGuestForAnonymousCheckout(final CustomerModel customerModel, final String password)
			throws DuplicateUidException
	{
		registerCustomer(customerModel, password);
	}

	@Override
	public void updateProfile(final CustomerModel customerModel, final String titleCode, final String name, final String login)
			throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("customerModel", customerModel);

		customerModel.setUid(login);
		customerModel.setName(name);
		if (StringUtils.isNotBlank(titleCode))
		{
			customerModel.setTitle(getUserService().getTitleForCode(titleCode));
		}
		else
		{
			customerModel.setTitle(null);
		}
		internalSaveCustomer(customerModel);
		getEventService().publishEvent(initializeEvent(new UpdatedProfileEvent(), customerModel));
	}

	@Override
	public void changePassword(final UserModel userModel, final String oldPassword, final String newPassword)
			throws PasswordMismatchException
	{
		validateParameterNotNullStandardMessage("customerModel", userModel);
		if (!getUserService().isAnonymousUser(userModel))
		{
			if (getPasswordEncoderService().isValid(userModel, oldPassword))
			{
				getUserService().setPassword(userModel, newPassword, userModel.getPasswordEncoding());
				getModelService().save(userModel);
			}
			else
			{
				throw new PasswordMismatchException(userModel.getUid());
			}
		}
	}

	@Override
	public void forgottenPassword(final CustomerModel customerModel)
	{
		validateParameterNotNullStandardMessage("customerModel", customerModel);
		final long timeStamp = getTokenValiditySeconds() > 0L ? new Date().getTime() : 0L;
		final SecureToken data = new SecureToken(customerModel.getUid(), timeStamp);
		final String token = getSecureTokenService().encryptData(data);
		customerModel.setToken(token);
		getModelService().save(customerModel);
		getEventService().publishEvent(initializeEvent(new ForgottenPwdEvent(token), customerModel));
	}

	@Override
	public void updatePassword(final String token, final String newPassword) throws TokenInvalidatedException
	{
		Assert.hasText(token, "The field [token] cannot be empty");
		Assert.hasText(newPassword, "The field [newPassword] cannot be empty");

		final SecureToken data = getSecureTokenService().decryptData(token);
		if (getTokenValiditySeconds() > 0L)
		{
			final long delta = new Date().getTime() - data.getTimeStamp();
			if (delta / 1000 > getTokenValiditySeconds())
			{
				throw new IllegalArgumentException("token expired");
			}
		}

		final CustomerModel customer = getUserService().getUserForUID(data.getData(), CustomerModel.class);
		if (customer == null)
		{
			throw new IllegalArgumentException("user for token not found");
		}
		if (!token.equals(customer.getToken()))
		{
			throw new TokenInvalidatedException();
		}
		customer.setToken(null);
		Class b2BCustomerModelClass = null;
		try
		{
			b2BCustomerModelClass = Class.forName("de.hybris.platform.b2b.model.B2BCustomerModel");
		}
		catch (final ClassNotFoundException e)
		{
			LOG.warn("Can not find B2BCustomerModel class");
		}
		if (b2BCustomerModelClass == null || !b2BCustomerModelClass.isAssignableFrom(customer.getClass()) || invokeGetActive(customer,
				b2BCustomerModelClass))
		{
			customer.setLoginDisabled(false);
		}
		getModelService().save(customer);

		getUserService().setPassword(data.getData(), newPassword, getPasswordEncoding());
	}

	/**
	 * @deprecated Since 5.0.
	 */
	@Override
	@Deprecated(since = "5.0", forRemoval = true)
	public OrderModel getOrderForCode(final CustomerModel customerModel, final String code, final BaseStoreModel store)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(code, "Order code cannot be null");
		validateParameterNotNull(store, "Store must not be null");
		return getCustomerAccountDao().findOrderByCustomerAndCodeAndStore(customerModel, code, store);
	}

	@Override
	public OrderModel getGuestOrderForGUID(final String guid, final BaseStoreModel store)
	{
		validateParameterNotNull(guid, "Order guid cannot be null");
		validateParameterNotNull(store, "Store must not be null");

		try
		{

			final OrderModel orderModel = getCustomerAccountDao().findOrderByGUIDAndStore(guid, store, null);

			if (new DateTime(getTimeService().getCurrentTime()).minusMonths(Integer.parseInt(getMonthsForOrderExpiry())).toDate()
					.after(orderModel.getDate()))
			{
				throw new IllegalArgumentException("OrderModel with guid " + guid + " is not visible due to being older than "
						+ getMonthsForOrderExpiry() + " months");
			}

			return orderModel;

		}
		catch (final ModelNotFoundException ex)
		{
			return null;
		}
	}

	@Override
	public OrderModel getOrderDetailsForGUID(final String guid, final BaseStoreModel store)
	{
		validateParameterNotNull(guid, "Order guid cannot be null");
		validateParameterNotNull(store, "Store must not be null");

		try
		{
			return getCustomerAccountDao().findOrderByGUIDAndStore(guid, store, null);
		}
		catch (final ModelNotFoundException ex)
		{
			return null;
		}
	}

	@Override
	public List<OrderModel> getOrderList(final CustomerModel customerModel, final BaseStoreModel store, final OrderStatus[] status)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(store, "Store must not be null");
		return getCustomerAccountDao().findOrdersByCustomerAndStore(customerModel, store, status);
	}

	@Override
	public SearchPageData<OrderModel> getOrderList(final CustomerModel customerModel, final BaseStoreModel store,
			final OrderStatus[] status, final PageableData pageableData)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(store, "Store must not be null");
		validateParameterNotNull(pageableData, "PageableData must not be null");
		return getCustomerAccountDao().findOrdersByCustomerAndStore(customerModel, store, status, pageableData);
	}

	@Override
	public SearchPageData<ReturnRequestModel> getReturnRequestsByCustomerAndStore(final CustomerModel customerModel,
			final BaseStoreModel store, final ReturnStatus[] returnStatuses, final PageableData pageableData)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(store, "Store must not be null");
		validateParameterNotNull(pageableData, "PageableData must not be null");
		return getCustomerAccountDao().findReturnRequestsByCustomerAndStore(customerModel, store, returnStatuses, pageableData);
	}

	@Override
	public void convertGuestToCustomer(final String pwd, final String orderGUID) throws DuplicateUidException
	{
		final OrderModel orderModel = getOrderDetailsForGUID(orderGUID, getBaseStoreService().getCurrentBaseStore());
		if (orderModel == null)
		{
			throw new UnknownIdentifierException("Order with guid " + orderGUID + " not found in current BaseStore");
		}

		final CustomerModel customer = (CustomerModel) orderModel.getUser();
		if (!CustomerType.GUEST.equals(customer.getType()))
		{
			throw new IllegalArgumentException("Order with guid " + orderGUID + " does not belong to guest user");
		}

		fillValuesForCustomerInfo(customer);
		register(customer, pwd);
		getUserService().setCurrentUser(customer);
	}

	@Override
	public OrderModel getOrderForCode(final String code, final BaseStoreModel store)
	{
		validateParameterNotNull(code, "Order code cannot be null");
		validateParameterNotNull(store, "Store must not be null");
		return getCustomerAccountDao().findOrderByCodeAndStore(code, store);
	}

	protected void fillValuesForCustomerInfo(final CustomerModel customer) throws DuplicateUidException
	{
		// Pull the name from the guest's billing info
		if (customer.getDefaultPaymentAddress() != null)
		{
			customer.setName(getCustomerNameStrategy().getName(customer.getDefaultPaymentAddress().getFirstname(),
					customer.getDefaultPaymentAddress().getLastname()));
			customer.setTitle(customer.getDefaultPaymentAddress().getTitle());
		}
		customer.setUid(StringUtils.substringAfter(customer.getUid(), "|"));
		customer.setType(null);
	}

	protected AbstractCommerceUserEvent initializeEvent(final AbstractCommerceUserEvent event, final CustomerModel customerModel)
	{
		event.setBaseStore(getBaseStoreService().getCurrentBaseStore());
		event.setSite(getBaseSiteService().getCurrentBaseSite());
		event.setCustomer(customerModel);
		event.setLanguage(getCommonI18NService().getCurrentLanguage());
		event.setCurrency(getCommonI18NService().getCurrentCurrency());
		return event;
	}

	protected void generateCustomerId(final CustomerModel customerModel)
	{
		customerModel.setCustomerID(UUID.randomUUID().toString());
	}

	/**
	 * Saves the customer translating model layer exceptions regarding duplicate identifiers
	 */
	protected void internalSaveCustomer(final CustomerModel customerModel) throws DuplicateUidException
	{
		try
		{
			getModelService().save(customerModel);
		}
		catch (final ModelSavingException e)
		{
			if (e.getCause() instanceof InterceptorException
					&& ((InterceptorException) e.getCause()).getInterceptor().getClass().equals(UniqueAttributesInterceptor.class))
			{
				throw new DuplicateUidException(customerModel.getUid(), e);
			}
			else
			{
				throw e;
			}
		}
		catch (final AmbiguousIdentifierException e)
		{
			throw new DuplicateUidException(customerModel.getUid(), e);
		}
	}

	@Override
	public void changeUid(final String newUid, final String currentPassword)
			throws DuplicateUidException, PasswordMismatchException
	{
		Assert.hasText(newUid, "The field [newEmail] cannot be empty");
		Assert.hasText(currentPassword, "The field [currentPassword] cannot be empty");

		final String newUidLower = newUid.toLowerCase();
		final CustomerModel currentUser = (CustomerModel) getUserService().getCurrentUser();
		final ChangeUIDEvent event = new ChangeUIDEvent(currentUser.getOriginalUid(), newUid);
		currentUser.setOriginalUid(newUid);

		// check uniqueness only if the uids are case insensitive different
		if (!currentUser.getUid().equalsIgnoreCase(newUid))
		{
			checkUidUniqueness(newUidLower);
		}
		adjustPassword(currentUser, newUidLower, currentPassword);
		getEventService().publishEvent(initializeEvent(event, currentUser));
	}

	@Override
	public CustomerModel closeAccount(final CustomerModel customer)
	{
		if (getUserService().isAnonymousUser(customer))
		{
			throw new AccessDeniedException("Unable to close account for anonymous user.");
		}
		customer.setDeactivationDate(getTimeService().getCurrentTime());
		getModelService().save(customer);
		getEventService().publishEvent(initializeEvent(new ClosedAccountEvent(), customer));
		return customer;
	}

	@Override
	public void setPopulatingConsentsInSessionEnabled(final boolean populatingConsentsInSessionEnabled)
	{
		getSessionService().setAttribute(POPULATING_CONSENTS_ENABLED, populatingConsentsInSessionEnabled);
	}

	/**
	 * Adjusts a given current user {@link UserModel#UID} with a newUid value unless the user with newUid does not exists
	 * and password for current user matches given currentPassword.
	 */
	protected void adjustPassword(final UserModel currentUser, final String newUid, final String currentPassword)
			throws PasswordMismatchException
	{

		// Validate that the current password is correct
		if (!getPasswordEncoderService().isValid(currentUser, currentPassword))
		{
			throw new PasswordMismatchException(currentUser.getUid());
		}

		// Save the newUid
		currentUser.setUid(newUid);
		getModelService().save(currentUser);

		// Update the password
		getUserService().setPassword(currentUser, currentPassword, currentUser.getPasswordEncoding());
		getModelService().save(currentUser);
	}

	protected void checkUidUniqueness(final String newUid) throws DuplicateUidException
	{
		// Check if the newUid is available
		try
		{
			if (getUserService().getUserForUID(newUid) != null)
			{
				throw new DuplicateUidException("User with email " + newUid + " already exists.");
			}
		}
		catch (final UnknownIdentifierException unknownIdentifierException)
		{
			// That's ok - user for new uid was not found
		}
	}

	protected void registerCustomer(final CustomerModel customerModel, final String password) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("customerModel", customerModel);

		generateCustomerId(customerModel);
		if (password != null)
		{
			getUserService().setPassword(customerModel, password, getPasswordEncoding());
		}
		internalSaveCustomer(customerModel);
	}

	protected PaymentService getPaymentService()
	{
		return paymentService;
	}

	@Required
	public void setPaymentService(final PaymentService paymentService)
	{
		this.paymentService = paymentService;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected I18NService getI18nService()
	{
		return i18nService;
	}

	@Required
	public void setI18nService(final I18NService i18nService)
	{
		this.i18nService = i18nService;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected PasswordEncoderService getPasswordEncoderService()
	{
		return passwordEncoderService;
	}

	@Required
	public void setPasswordEncoderService(final PasswordEncoderService passwordEncoderService)
	{
		this.passwordEncoderService = passwordEncoderService;
	}

	protected SecureTokenService getSecureTokenService()
	{
		return secureTokenService;
	}

	@Required
	public void setSecureTokenService(final SecureTokenService secureTokenService)
	{
		this.secureTokenService = secureTokenService;
	}

	protected long getTokenValiditySeconds()
	{
		return tokenValiditySeconds;
	}

	@Required
	public void setTokenValiditySeconds(final long tokenValiditySeconds)
	{
		if (tokenValiditySeconds < 0)
		{
			throw new IllegalArgumentException("tokenValiditySeconds has to be >= 0");
		}
		this.tokenValiditySeconds = tokenValiditySeconds;
	}

	protected CustomerAccountDao getCustomerAccountDao()
	{
		return customerAccountDao;
	}

	@Required
	public void setCustomerAccountDao(final CustomerAccountDao customerAccountDao)
	{
		this.customerAccountDao = customerAccountDao;
	}


	protected BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(final BaseStoreService service)
	{
		this.baseStoreService = service;
	}

	protected BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService siteService)
	{
		this.baseSiteService = siteService;
	}

	protected CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	protected EventService getEventService()
	{
		return eventService;
	}

	@Required
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}

	protected CustomerEmailResolutionService getCustomerEmailResolutionService()
	{
		return customerEmailResolutionService;
	}

	@Required
	public void setCustomerEmailResolutionService(final CustomerEmailResolutionService customerEmailResolutionService)
	{
		this.customerEmailResolutionService = customerEmailResolutionService;
	}

	protected String getPasswordEncoding()
	{
		return passwordEncoding;
	}

	// Optional: Defaults to PasswordEncoderConstants.DEFAULT_ENCODING
	public void setPasswordEncoding(final String passwordEncoding)
	{
		Assert.hasText(passwordEncoding);
		this.passwordEncoding = passwordEncoding;
	}

	protected FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	public TypeService getTypeService()
	{
		return typeService;
	}

	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

	protected CustomerNameStrategy getCustomerNameStrategy()
	{
		return customerNameStrategy;
	}

	@Required
	public void setCustomerNameStrategy(final CustomerNameStrategy customerNameStrategy)
	{
		this.customerNameStrategy = customerNameStrategy;
	}

	protected String getMonthsForOrderExpiry()
	{
		return monthsForOrderExpiry;
	}

	@Required
	public void setMonthsForOrderExpiry(final String monthsForOrderExpiry)
	{
		this.monthsForOrderExpiry = monthsForOrderExpiry;
	}

	protected TimeService getTimeService()
	{
		return timeService;
	}

	@Required
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}

	protected CommerceCommonI18NService getCommerceCommonI18NService()
	{
		return commerceCommonI18NService;
	}

	@Required
	public void setCommerceCommonI18NService(final CommerceCommonI18NService commerceCommonI18NService)
	{
		this.commerceCommonI18NService = commerceCommonI18NService;
	}

	protected ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	@Required
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	protected CommerceConsentService getCommerceConsentService()
	{
		return commerceConsentService;
	}

	@Required
	public void setCommerceConsentService(final CommerceConsentService commerceConsentService)
	{
		this.commerceConsentService = commerceConsentService;
	}

	protected SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	private boolean invokeGetActive(Object obj, Class clazz)
	{
		boolean result = false;
		Method method = null;
		try
		{
			method = clazz.getMethod("getActive");
			result = (method != null) && (boolean)(method.invoke(obj));
		}
		catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e)
		{
			LOG.warn("Failed to invoke the method getActive");
		}
		return result;
	}
}
