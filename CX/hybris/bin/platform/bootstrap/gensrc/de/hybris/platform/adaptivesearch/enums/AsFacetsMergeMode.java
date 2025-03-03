/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.adaptivesearch.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum AsFacetsMergeMode declared at extension adaptivesearch.
 */
public enum AsFacetsMergeMode implements HybrisEnumValue
{
	/**
	 * Generated enum value for AsFacetsMergeMode.ADD_AFTER declared at extension adaptivesearch.
	 */
	ADD_AFTER("ADD_AFTER"),
	/**
	 * Generated enum value for AsFacetsMergeMode.ADD_BEFORE declared at extension adaptivesearch.
	 */
	ADD_BEFORE("ADD_BEFORE"),
	/**
	 * Generated enum value for AsFacetsMergeMode.REPLACE declared at extension adaptivesearch.
	 */
	REPLACE("REPLACE");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "AsFacetsMergeMode";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "AsFacetsMergeMode";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private AsFacetsMergeMode(final String code)
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
