/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.subscriptionservices.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum SubscriptionStatus declared at extension subscriptionservices.
 */
public enum SubscriptionStatus implements HybrisEnumValue
{
	/**
	 * Generated enum value for SubscriptionStatus.active declared at extension subscriptionservices.
	 */
	ACTIVE("active"),
	/**
	 * Generated enum value for SubscriptionStatus.expired declared at extension subscriptionservices.
	 */
	EXPIRED("expired"),
	/**
	 * Generated enum value for SubscriptionStatus.cancelled declared at extension subscriptionservices.
	 */
	CANCELLED("cancelled");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "SubscriptionStatus";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "SubscriptionStatus";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private SubscriptionStatus(final String code)
	{
		this.code = code.intern();
	}
	
	
	/**
	 * Gets the code of this enum value.
	 *  
	 * @return code of value
	 */
	@Override
	public String getCode()
	{
		return this.code;
	}
	
	/**
	 * Gets the type this enum value belongs to.
	 *  
	 * @return code of type
	 */
	@Override
	public String getType()
	{
		return SIMPLE_CLASSNAME;
	}
	
}
