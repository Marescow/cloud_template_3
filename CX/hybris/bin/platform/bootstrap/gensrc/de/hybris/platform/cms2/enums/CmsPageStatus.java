/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cms2.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum CmsPageStatus declared at extension cms2.
 */
public enum CmsPageStatus implements HybrisEnumValue
{
	/**
	 * Generated enum value for CmsPageStatus.active declared at extension cms2.
	 * <p/>
	 * Active state.
	 */
	ACTIVE("active"),
	/**
	 * Generated enum value for CmsPageStatus.deleted declared at extension cms2.
	 * <p/>
	 * Soft deleted state.
	 */
	DELETED("deleted");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "CmsPageStatus";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "CmsPageStatus";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private CmsPageStatus(final String code)
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
