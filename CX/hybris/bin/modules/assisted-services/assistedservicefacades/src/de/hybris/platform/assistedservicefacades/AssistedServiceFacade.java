/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.assistedservicefacades;

import de.hybris.platform.assistedservicefacades.user.data.AutoSuggestionCustomerData;
import de.hybris.platform.assistedserviceservices.exception.AssistedServiceException;
import de.hybris.platform.assistedserviceservices.utils.AssistedServiceSession;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.jalo.user.LoginToken;
import org.apache.commons.lang.NotImplementedException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Facade interface for assisted service functionality.
 */
public interface AssistedServiceFacade
{
	/**
	 * Adds a customer and/or cart to the current session.
	 *
	 * @param customerId
	 *           id to identify the customer by (usually email address)
	 * @param cartId
	 *           the id of the customer cart to pick up
	 * @throws AssistedServiceException
	 * 			 In case of:
	 * 			 1) blank parameters
	 * 			 2) bad credentials,
	 * 			 3) AS agent isn't logged in yet
	 * 			 4) AS agent trying to emulate himself
	 */
	void emulateCustomer(final String customerId, final String cartId) throws AssistedServiceException;

	/**
	 * Adds provided customer, cart or an order to the current session.
	 *
	 * @param customerId
	 *           id to identify the customer by (usually email address)
	 * @param cartId
	 *           the id of the customer cart to pick up
	 *
	 * @param orderId
	 *           the id of the customer order to pick up
	 * @throws AssistedServiceException
	 * 			 In case of:
	 * 			 1) both customerId and cartId parameters blank
	 * 			 2) bad credentials,
	 * 			 3) AS agent isn't logged in yet
	 * 			 4) AS agent trying to emulate himself
	 */
	void emulateCustomer(final String customerId, final String cartId, final String orderId) throws AssistedServiceException;

	/**
	 * Remove customer and/or cart from the current session.
	 */
	void stopEmulateCustomer();

	/**
	 * Starts the Assisted Service Mode.
	 */
	void launchAssistedServiceMode();

	/**
	 * Ends the Assisted Service Mode.
	 */
	void quitAssistedServiceMode();

	/**
	 * Logs in Assisted Service agent using provided credentials.
	 *
	 * @param username
	 *            uid
	 * @param password
	 * 			  password
	 * @throws AssistedServiceException
	 *            In case of bad credentials
	 */
	void loginAssistedServiceAgent(final String username, final String password) throws AssistedServiceException;

	/**
	 * Logs in Assisted Service agent using provided SAML credentials.
	 *
	 * @param username
	 *            uid
	 * @param password
	 * 			  password
	 * @throws AssistedServiceException
	 *            In case of bad credentials
	 */
	void loginAssistedServiceAgentSAML(final String username, final String password) throws AssistedServiceException;

	/**
	 * Logs in Assisted Service agent using provided SAML credentials.
	 *
	 * @param username
	 *            uid
	 * @param token
	 * 			  token
	 * @throws AssistedServiceException
	 *            In case of bad credentials
	 */
	default void loginAssistedServiceAgentSAML(final String username, final LoginToken token) throws AssistedServiceException
	{
		loginAssistedServiceAgentSAML(username, token.getPassword());
	}

	/**
	 * Logs out Assisted Service agent by removing session attribute.
	 *
	 * @throws AssistedServiceException
	 * 			  In case there is no AS agent session
	 */
	void logoutAssistedServiceAgent() throws AssistedServiceException;

	/**
	 * Whether or not Assisted Service module is launched.
	 *
	 * @return true when assisted service mode has been launched
	 */
	boolean isAssistedServiceModeLaunched();


	/**
	 * Returns timeout for assisted service agent session in seconds.
	 *
	 * @return session in seconds
	 */
	int getAssistedServiceSessionTimeout();

	/**
	 * Returns timer value (in seconds) for assisted service agent session, that displays inside widget.
	 *
	 * @return value in seconds
	 */
	int getAssistedServiceSessionTimerValue();

	/**
	 * Returns assisted service attributes map for page model.
	 *
	 * @return String->Object map for page model.
	 */
	Map<String, Object> getAssistedServiceSessionAttributes();

	/**
	 * @deprecated since 2005, please use the method {@link AssistedServiceFacade#getSuggestedCustomerData(String)}
	 *
	 * Validate session and get list of customers which username or email starts with provided value.
	 *
	 * @param username
	 *           uid or customer's name
	 * @return suggested customers
	 * @throws AssistedServiceException
	 * 			  In case Assisted Service Agent is not logged in or Assisted Service mode inactive
	 */
	@Deprecated(since = "2005", forRemoval = true)
	List<CustomerData> getSuggestedCustomerList(final String username) throws AssistedServiceException;

	/**
	 * Validate session and get list of customers which username or email starts with provided value.
	 *
	 * @param username
	 *           uid or customer's name
	 * @return suggested customers
	 */
	List<AutoSuggestionCustomerData> getSuggestedCustomerData(String username);

	/**
	 * Get list of customers which username or email starts with provided value.
	 *
	 * @param searchTerm
	 *           part of uid or customer's name
	 * @param pageableData
	 *			 the pageable data
	 * @return list of customers
	 */
	List<CustomerData> getCustomers(final String searchTerm, final PageableData pageableData);

	/**
	 * Get paged customers which username or email starts with provided value.
	 *
	 * @param searchTerm
	 *           part of uid or customer's name
	 * @param pageableData
	 *			 the pageable data
	 * @return {@link SearchPageData} of customers
	 */
	default SearchPageData<CustomerData> getPagedCustomers(final String searchTerm, final PageableData pageableData)
	{
		throw new NotImplementedException();
	};

	/**
	 * Get list of customers which username or email starts with provided value.
	 *
	 * @param searchTerm
	 * 			part of uid or customer's name
	 * @return list of customers
	 */
	 List<AutoSuggestionCustomerData> getCustomerSuggestions(final String searchTerm);

	/**
	 * Personify customer based on customer stored on login step and current session cart.
	 *
	 * @throws AssistedServiceException
	 * 			 In case of:
	 * 			 1) AS agent isn't logged in yet
	 * 			 2) AS agent trying to emulate himself
	 */
	void emulateAfterLogin() throws AssistedServiceException;

	/**
	 * Binds customer with provided id to cart if it's anonymous cart.
	 *
	 * @param customerId
	 * 			 email of to be newly created customer that is used as uid
	 * @param cartId
	 * 			 the id of the customer cart to pick up
	 * @throws AssistedServiceException
	 * 			 In cas of missing cart with given cardId
	 */
	void bindCustomerToCart(final String customerId, final String cartId) throws AssistedServiceException;

	/**
	 * Binds customer with provided id to cart if it's anonymous cart, but not emulate the customer.
	 *
	 * @param customerId
	 *           email of customer which should be bound to the cart
	 * @param cartId
	 *           the id of anonymous cart to be bound with user
	 * @throws AssistedServiceException
	 *            In case of missing cart with given cartId OR the cart is not anonymous OR missing customer with the
	 *            customerId
	 */
	void bindCustomerToCartWithoutEmulating(final String customerId, final String cartId) throws AssistedServiceException;


	/**
	 * Creates a new customer by it email and name.
	 *
	 * @param customerId
	 *           email of to be newly created customer that is used as uid
	 *
	 * @param customerName
	 *           name of to be newly created customer (firstname and surname separated by space symbol)
	 *
	 * @return CustomerModel
	 * @throws AssistedServiceException
	 * 			 In case of:
	 * 			 1) trying to create already existing customer with given customerId
	 * 			 2) disabled customer creation
	 */
	CustomerData createCustomer(String customerId, String customerName) throws AssistedServiceException;

	/**
	 * Checks if there is ASM information in the current session or no, this will be used by Session Restriction mainly
	 *
	 * @return boolean value to indicates if there are a valid assisted service info in the session or no.
	 */
	boolean isAssistedServiceAgentLoggedIn();

	/**
	 * Returns collection of a customer's carts
	 *
	 * @param customer
	 *           customer model whose carts to be returned
	 * @return collection of the customer's cart models
	 */
	Collection<CartData> getCartListForCustomer(final CustomerModel customer);

	/**
	 * Returns ASM session object with all information about current asm session.
	 *
	 * @return asm session object
	 */
	AssistedServiceSession getAsmSession();

	/**
	 * Get the order's customer using orderFacade.getOrderDetailsForCode
	 *
	 * @param orderId
* 				  the id of the customer order to get customer
	 * @return customerData
	 */
	CustomerData getCustomerByOrder(final String orderId);
}
