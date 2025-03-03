/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.impex.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum ImpExProcessModeEnum declared at extension impex.
 */
public enum ImpExProcessModeEnum implements HybrisEnumValue
{
	/**
	 * Generated enum value for ImpExProcessModeEnum.insert declared at extension impex.
	 */
	INSERT("insert"),
	/**
	 * Generated enum value for ImpExProcessModeEnum.update declared at extension impex.
	 */
	UPDATE("update"),
	/**
	 * Generated enum value for ImpExProcessModeEnum.remove declared at extension impex.
	 */
	REMOVE("remove"),
	/**
	 * Generated enum value for ImpExProcessModeEnum.ignore declared at extension impex.
	 */
	IGNORE("ignore"),
	/**
	 * Generated enum value for ImpExProcessModeEnum.insert_update declared at extension impex.
	 */
	INSERT_UPDATE("insert_update");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "ImpExProcessModeEnum";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "ImpExProcessModeEnum";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private ImpExProcessModeEnum(final String code)
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
