/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.basecommerce.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum InStockStatus declared at extension basecommerce.
 * <p/>
 * Internal flag for stock status.
 */
public enum InStockStatus implements HybrisEnumValue
{
	/**
	 * Generated enum value for InStockStatus.forceInStock declared at extension basecommerce.
	 */
	FORCEINSTOCK("forceInStock"),
	/**
	 * Generated enum value for InStockStatus.forceOutOfStock declared at extension basecommerce.
	 */
	FORCEOUTOFSTOCK("forceOutOfStock"),
	/**
	 * Generated enum value for InStockStatus.notSpecified declared at extension basecommerce.
	 */
	NOTSPECIFIED("notSpecified");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "InStockStatus";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "InStockStatus";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private InStockStatus(final String code)
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
