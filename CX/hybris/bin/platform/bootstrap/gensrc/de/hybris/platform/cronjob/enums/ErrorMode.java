/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cronjob.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum ErrorMode declared at extension processing.
 */
public enum ErrorMode implements HybrisEnumValue
{
	/**
	 * Generated enum value for ErrorMode.FAIL declared at extension processing.
	 */
	FAIL("FAIL"),
	/**
	 * Generated enum value for ErrorMode.PAUSE declared at extension processing.
	 */
	PAUSE("PAUSE"),
	/**
	 * Generated enum value for ErrorMode.IGNORE declared at extension processing.
	 */
	IGNORE("IGNORE");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "ErrorMode";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "ErrorMode";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private ErrorMode(final String code)
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
