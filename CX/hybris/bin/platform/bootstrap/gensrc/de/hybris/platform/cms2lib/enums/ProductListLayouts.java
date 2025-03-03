/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cms2lib.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum ProductListLayouts declared at extension cms2lib.
 */
public enum ProductListLayouts implements HybrisEnumValue
{
	/**
	 * Generated enum value for ProductListLayouts.listViewLayout declared at extension cms2lib.
	 */
	LISTVIEWLAYOUT("listViewLayout"),
	/**
	 * Generated enum value for ProductListLayouts.thumbViewLayout declared at extension cms2lib.
	 */
	THUMBVIEWLAYOUT("thumbViewLayout");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "ProductListLayouts";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "ProductListLayouts";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private ProductListLayouts(final String code)
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
