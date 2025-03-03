/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2b.jalo;

import de.hybris.platform.b2b.constants.B2BCommerceConstants;
import de.hybris.platform.b2b.jalo.B2BBookingLineEntry;
import de.hybris.platform.b2b.jalo.B2BBudget;
import de.hybris.platform.b2b.jalo.B2BComment;
import de.hybris.platform.b2b.jalo.B2BCostCenter;
import de.hybris.platform.b2b.jalo.B2BCreditCheckResult;
import de.hybris.platform.b2b.jalo.B2BCreditLimit;
import de.hybris.platform.b2b.jalo.B2BCustomer;
import de.hybris.platform.b2b.jalo.B2BCustomerEmailConstraint;
import de.hybris.platform.b2b.jalo.B2BMerchantCheckResult;
import de.hybris.platform.b2b.jalo.B2BQuoteLimit;
import de.hybris.platform.b2b.jalo.B2BRegistration;
import de.hybris.platform.b2b.jalo.B2BRegistrationApprovedProcess;
import de.hybris.platform.b2b.jalo.B2BRegistrationProcess;
import de.hybris.platform.b2b.jalo.B2BRegistrationRejectedProcess;
import de.hybris.platform.b2b.jalo.B2BReportingSet;
import de.hybris.platform.b2b.jalo.B2BUnit;
import de.hybris.platform.b2b.jalo.B2BUserGroup;
import de.hybris.platform.basecommerce.jalo.site.BaseSite;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.link.Link;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.util.OneToManyHandler;
import de.hybris.platform.workflow.jalo.Workflow;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>B2BCommerceManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedB2BCommerceManager extends Extension
{
	/**
	* {@link OneToManyHandler} for handling 1:n B2BCOMMENTS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<B2BComment> ABSTRACTORDER2B2BCOMMENTB2BCOMMENTSHANDLER = new OneToManyHandler<B2BComment>(
	B2BCommerceConstants.TC.B2BCOMMENT,
	true,
	"order",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link OneToManyHandler} for handling 1:n UNIT's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<B2BUnit> B2BUNIT2EMPLOYEEUNITHANDLER = new OneToManyHandler<B2BUnit>(
	B2BCommerceConstants.TC.B2BUNIT,
	false,
	"accountManager",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("costCenter", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.AbstractOrderEntry", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("locale", AttributeMode.INITIAL);
		tmp.put("workflow", AttributeMode.INITIAL);
		tmp.put("quoteExpirationDate", AttributeMode.INITIAL);
		tmp.put("Unit", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.AbstractOrder", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("enableRegistration", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.basecommerce.jalo.site.BaseSite", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.b2bcomments</code> attribute.
	 * @return the b2bcomments - B2B Comments associated to Order
	 */
	public Collection<B2BComment> getB2bcomments(final SessionContext ctx, final AbstractOrder item)
	{
		return ABSTRACTORDER2B2BCOMMENTB2BCOMMENTSHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.b2bcomments</code> attribute.
	 * @return the b2bcomments - B2B Comments associated to Order
	 */
	public Collection<B2BComment> getB2bcomments(final AbstractOrder item)
	{
		return getB2bcomments( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.b2bcomments</code> attribute. 
	 * @param value the b2bcomments - B2B Comments associated to Order
	 */
	public void setB2bcomments(final SessionContext ctx, final AbstractOrder item, final Collection<B2BComment> value)
	{
		ABSTRACTORDER2B2BCOMMENTB2BCOMMENTSHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.b2bcomments</code> attribute. 
	 * @param value the b2bcomments - B2B Comments associated to Order
	 */
	public void setB2bcomments(final AbstractOrder item, final Collection<B2BComment> value)
	{
		setB2bcomments( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to b2bcomments. 
	 * @param value the item to add to b2bcomments - B2B Comments associated to Order
	 */
	public void addToB2bcomments(final SessionContext ctx, final AbstractOrder item, final B2BComment value)
	{
		ABSTRACTORDER2B2BCOMMENTB2BCOMMENTSHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to b2bcomments. 
	 * @param value the item to add to b2bcomments - B2B Comments associated to Order
	 */
	public void addToB2bcomments(final AbstractOrder item, final B2BComment value)
	{
		addToB2bcomments( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from b2bcomments. 
	 * @param value the item to remove from b2bcomments - B2B Comments associated to Order
	 */
	public void removeFromB2bcomments(final SessionContext ctx, final AbstractOrder item, final B2BComment value)
	{
		ABSTRACTORDER2B2BCOMMENTB2BCOMMENTSHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from b2bcomments. 
	 * @param value the item to remove from b2bcomments - B2B Comments associated to Order
	 */
	public void removeFromB2bcomments(final AbstractOrder item, final B2BComment value)
	{
		removeFromB2bcomments( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.costCenter</code> attribute.
	 * @return the costCenter
	 */
	public B2BCostCenter getCostCenter(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (B2BCostCenter)item.getProperty( ctx, B2BCommerceConstants.Attributes.AbstractOrderEntry.COSTCENTER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.costCenter</code> attribute.
	 * @return the costCenter
	 */
	public B2BCostCenter getCostCenter(final AbstractOrderEntry item)
	{
		return getCostCenter( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.costCenter</code> attribute. 
	 * @param value the costCenter
	 */
	public void setCostCenter(final SessionContext ctx, final AbstractOrderEntry item, final B2BCostCenter value)
	{
		item.setProperty(ctx, B2BCommerceConstants.Attributes.AbstractOrderEntry.COSTCENTER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.costCenter</code> attribute. 
	 * @param value the costCenter
	 */
	public void setCostCenter(final AbstractOrderEntry item, final B2BCostCenter value)
	{
		setCostCenter( getSession().getSessionContext(), item, value );
	}
	
	public B2BBookingLineEntry createB2BBookingLineEntry(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BBOOKINGLINEENTRY );
			return (B2BBookingLineEntry)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BBookingLineEntry : "+e.getMessage(), 0 );
		}
	}
	
	public B2BBookingLineEntry createB2BBookingLineEntry(final Map attributeValues)
	{
		return createB2BBookingLineEntry( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BBudget createB2BBudget(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BBUDGET );
			return (B2BBudget)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BBudget : "+e.getMessage(), 0 );
		}
	}
	
	public B2BBudget createB2BBudget(final Map attributeValues)
	{
		return createB2BBudget( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BComment createB2BComment(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BCOMMENT );
			return (B2BComment)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BComment : "+e.getMessage(), 0 );
		}
	}
	
	public B2BComment createB2BComment(final Map attributeValues)
	{
		return createB2BComment( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BCostCenter createB2BCostCenter(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BCOSTCENTER );
			return (B2BCostCenter)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BCostCenter : "+e.getMessage(), 0 );
		}
	}
	
	public B2BCostCenter createB2BCostCenter(final Map attributeValues)
	{
		return createB2BCostCenter( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BCreditCheckResult createB2BCreditCheckResult(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BCREDITCHECKRESULT );
			return (B2BCreditCheckResult)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BCreditCheckResult : "+e.getMessage(), 0 );
		}
	}
	
	public B2BCreditCheckResult createB2BCreditCheckResult(final Map attributeValues)
	{
		return createB2BCreditCheckResult( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BCreditLimit createB2BCreditLimit(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BCREDITLIMIT );
			return (B2BCreditLimit)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BCreditLimit : "+e.getMessage(), 0 );
		}
	}
	
	public B2BCreditLimit createB2BCreditLimit(final Map attributeValues)
	{
		return createB2BCreditLimit( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BCustomer createB2BCustomer(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BCUSTOMER );
			return (B2BCustomer)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BCustomer : "+e.getMessage(), 0 );
		}
	}
	
	public B2BCustomer createB2BCustomer(final Map attributeValues)
	{
		return createB2BCustomer( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BCustomerEmailConstraint createB2BCustomerEmailConstraint(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BCUSTOMEREMAILCONSTRAINT );
			return (B2BCustomerEmailConstraint)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BCustomerEmailConstraint : "+e.getMessage(), 0 );
		}
	}
	
	public B2BCustomerEmailConstraint createB2BCustomerEmailConstraint(final Map attributeValues)
	{
		return createB2BCustomerEmailConstraint( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BMerchantCheckResult createB2BMerchantCheckResult(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BMERCHANTCHECKRESULT );
			return (B2BMerchantCheckResult)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BMerchantCheckResult : "+e.getMessage(), 0 );
		}
	}
	
	public B2BMerchantCheckResult createB2BMerchantCheckResult(final Map attributeValues)
	{
		return createB2BMerchantCheckResult( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BQuoteLimit createB2BQuoteLimit(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BQUOTELIMIT );
			return (B2BQuoteLimit)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BQuoteLimit : "+e.getMessage(), 0 );
		}
	}
	
	public B2BQuoteLimit createB2BQuoteLimit(final Map attributeValues)
	{
		return createB2BQuoteLimit( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BRegistration createB2BRegistration(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BREGISTRATION );
			return (B2BRegistration)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BRegistration : "+e.getMessage(), 0 );
		}
	}
	
	public B2BRegistration createB2BRegistration(final Map attributeValues)
	{
		return createB2BRegistration( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BRegistrationApprovedProcess createB2BRegistrationApprovedProcess(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BREGISTRATIONAPPROVEDPROCESS );
			return (B2BRegistrationApprovedProcess)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BRegistrationApprovedProcess : "+e.getMessage(), 0 );
		}
	}
	
	public B2BRegistrationApprovedProcess createB2BRegistrationApprovedProcess(final Map attributeValues)
	{
		return createB2BRegistrationApprovedProcess( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BRegistrationProcess createB2BRegistrationProcess(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BREGISTRATIONPROCESS );
			return (B2BRegistrationProcess)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BRegistrationProcess : "+e.getMessage(), 0 );
		}
	}
	
	public B2BRegistrationProcess createB2BRegistrationProcess(final Map attributeValues)
	{
		return createB2BRegistrationProcess( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BRegistrationRejectedProcess createB2BRegistrationRejectedProcess(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BREGISTRATIONREJECTEDPROCESS );
			return (B2BRegistrationRejectedProcess)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BRegistrationRejectedProcess : "+e.getMessage(), 0 );
		}
	}
	
	public B2BRegistrationRejectedProcess createB2BRegistrationRejectedProcess(final Map attributeValues)
	{
		return createB2BRegistrationRejectedProcess( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BReportingSet createB2BReportingSet(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BREPORTINGSET );
			return (B2BReportingSet)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BReportingSet : "+e.getMessage(), 0 );
		}
	}
	
	public B2BReportingSet createB2BReportingSet(final Map attributeValues)
	{
		return createB2BReportingSet( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BUnit createB2BUnit(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BUNIT );
			return (B2BUnit)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BUnit : "+e.getMessage(), 0 );
		}
	}
	
	public B2BUnit createB2BUnit(final Map attributeValues)
	{
		return createB2BUnit( getSession().getSessionContext(), attributeValues );
	}
	
	public B2BUserGroup createB2BUserGroup(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( B2BCommerceConstants.TC.B2BUSERGROUP );
			return (B2BUserGroup)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating B2BUserGroup : "+e.getMessage(), 0 );
		}
	}
	
	public B2BUserGroup createB2BUserGroup(final Map attributeValues)
	{
		return createB2BUserGroup( getSession().getSessionContext(), attributeValues );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.enableRegistration</code> attribute.
	 * @return the enableRegistration - Indicates if the website supports registration request.
	 */
	public Boolean isEnableRegistration(final SessionContext ctx, final BaseSite item)
	{
		return (Boolean)item.getProperty( ctx, B2BCommerceConstants.Attributes.BaseSite.ENABLEREGISTRATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.enableRegistration</code> attribute.
	 * @return the enableRegistration - Indicates if the website supports registration request.
	 */
	public Boolean isEnableRegistration(final BaseSite item)
	{
		return isEnableRegistration( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.enableRegistration</code> attribute. 
	 * @return the enableRegistration - Indicates if the website supports registration request.
	 */
	public boolean isEnableRegistrationAsPrimitive(final SessionContext ctx, final BaseSite item)
	{
		Boolean value = isEnableRegistration( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseSite.enableRegistration</code> attribute. 
	 * @return the enableRegistration - Indicates if the website supports registration request.
	 */
	public boolean isEnableRegistrationAsPrimitive(final BaseSite item)
	{
		return isEnableRegistrationAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseSite.enableRegistration</code> attribute. 
	 * @param value the enableRegistration - Indicates if the website supports registration request.
	 */
	public void setEnableRegistration(final SessionContext ctx, final BaseSite item, final Boolean value)
	{
		item.setProperty(ctx, B2BCommerceConstants.Attributes.BaseSite.ENABLEREGISTRATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseSite.enableRegistration</code> attribute. 
	 * @param value the enableRegistration - Indicates if the website supports registration request.
	 */
	public void setEnableRegistration(final BaseSite item, final Boolean value)
	{
		setEnableRegistration( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseSite.enableRegistration</code> attribute. 
	 * @param value the enableRegistration - Indicates if the website supports registration request.
	 */
	public void setEnableRegistration(final SessionContext ctx, final BaseSite item, final boolean value)
	{
		setEnableRegistration( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseSite.enableRegistration</code> attribute. 
	 * @param value the enableRegistration - Indicates if the website supports registration request.
	 */
	public void setEnableRegistration(final BaseSite item, final boolean value)
	{
		setEnableRegistration( getSession().getSessionContext(), item, value );
	}
	
	@Override
	public String getName()
	{
		return B2BCommerceConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.locale</code> attribute.
	 * @return the locale - name of the entire locale, with the language, country
	 * 						and variant separated by underbars,
	 * 						which was used when the order
	 * 						was created.
	 */
	public String getLocale(final SessionContext ctx, final AbstractOrder item)
	{
		return (String)item.getProperty( ctx, B2BCommerceConstants.Attributes.AbstractOrder.LOCALE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.locale</code> attribute.
	 * @return the locale - name of the entire locale, with the language, country
	 * 						and variant separated by underbars,
	 * 						which was used when the order
	 * 						was created.
	 */
	public String getLocale(final AbstractOrder item)
	{
		return getLocale( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.locale</code> attribute. 
	 * @param value the locale - name of the entire locale, with the language, country
	 * 						and variant separated by underbars,
	 * 						which was used when the order
	 * 						was created.
	 */
	public void setLocale(final SessionContext ctx, final AbstractOrder item, final String value)
	{
		item.setProperty(ctx, B2BCommerceConstants.Attributes.AbstractOrder.LOCALE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.locale</code> attribute. 
	 * @param value the locale - name of the entire locale, with the language, country
	 * 						and variant separated by underbars,
	 * 						which was used when the order
	 * 						was created.
	 */
	public void setLocale(final AbstractOrder item, final String value)
	{
		setLocale( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.quoteExpirationDate</code> attribute.
	 * @return the quoteExpirationDate
	 */
	public Date getQuoteExpirationDate(final SessionContext ctx, final AbstractOrder item)
	{
		return (Date)item.getProperty( ctx, B2BCommerceConstants.Attributes.AbstractOrder.QUOTEEXPIRATIONDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.quoteExpirationDate</code> attribute.
	 * @return the quoteExpirationDate
	 */
	public Date getQuoteExpirationDate(final AbstractOrder item)
	{
		return getQuoteExpirationDate( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.quoteExpirationDate</code> attribute. 
	 * @param value the quoteExpirationDate
	 */
	public void setQuoteExpirationDate(final SessionContext ctx, final AbstractOrder item, final Date value)
	{
		item.setProperty(ctx, B2BCommerceConstants.Attributes.AbstractOrder.QUOTEEXPIRATIONDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.quoteExpirationDate</code> attribute. 
	 * @param value the quoteExpirationDate
	 */
	public void setQuoteExpirationDate(final AbstractOrder item, final Date value)
	{
		setQuoteExpirationDate( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.Unit</code> attribute.
	 * @return the Unit
	 */
	public B2BUnit getUnit(final SessionContext ctx, final AbstractOrder item)
	{
		return (B2BUnit)item.getProperty( ctx, B2BCommerceConstants.Attributes.AbstractOrder.UNIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.Unit</code> attribute.
	 * @return the Unit
	 */
	public B2BUnit getUnit(final AbstractOrder item)
	{
		return getUnit( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.Unit</code> attribute. 
	 * @param value the Unit
	 */
	public void setUnit(final SessionContext ctx, final AbstractOrder item, final B2BUnit value)
	{
		item.setProperty(ctx, B2BCommerceConstants.Attributes.AbstractOrder.UNIT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.Unit</code> attribute. 
	 * @param value the Unit
	 */
	public void setUnit(final AbstractOrder item, final B2BUnit value)
	{
		setUnit( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.workflow</code> attribute.
	 * @return the workflow
	 */
	public Workflow getWorkflow(final SessionContext ctx, final AbstractOrder item)
	{
		return (Workflow)item.getProperty( ctx, B2BCommerceConstants.Attributes.AbstractOrder.WORKFLOW);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.workflow</code> attribute.
	 * @return the workflow
	 */
	public Workflow getWorkflow(final AbstractOrder item)
	{
		return getWorkflow( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.workflow</code> attribute. 
	 * @param value the workflow
	 */
	public void setWorkflow(final SessionContext ctx, final AbstractOrder item, final Workflow value)
	{
		item.setProperty(ctx, B2BCommerceConstants.Attributes.AbstractOrder.WORKFLOW,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.workflow</code> attribute. 
	 * @param value the workflow
	 */
	public void setWorkflow(final AbstractOrder item, final Workflow value)
	{
		setWorkflow( getSession().getSessionContext(), item, value );
	}
	
}
