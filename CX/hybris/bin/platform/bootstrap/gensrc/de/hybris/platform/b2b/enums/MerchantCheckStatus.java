/*
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2b.enums;

import de.hybris.platform.core.HybrisEnumValue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Generated enum MerchantCheckStatus declared at extension b2bcommerce.
 */
public class MerchantCheckStatus implements HybrisEnumValue
{
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "MerchantCheckStatus";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "MerchantCheckStatus";
	private static final ConcurrentMap<String, MerchantCheckStatus> cache = new ConcurrentHashMap<String, MerchantCheckStatus>();
	/**
	* Generated enum value for MerchantCheckStatus.APPROVED declared at extension b2bcommerce.
	*/
	public static final MerchantCheckStatus APPROVED = valueOf("APPROVED");
	
	/**
	* Generated enum value for MerchantCheckStatus.REJECTED declared at extension b2bcommerce.
	*/
	public static final MerchantCheckStatus REJECTED = valueOf("REJECTED");
	
	/**
	* Generated enum value for MerchantCheckStatus.OPEN declared at extension b2bcommerce.
	*/
	public static final MerchantCheckStatus OPEN = valueOf("OPEN");
	
	/**
	* Generated enum value for MerchantCheckStatus.ERROR declared at extension b2bcommerce.
	*/
	public static final MerchantCheckStatus ERROR = valueOf("ERROR");
	
	/**
	* Generated enum value for MerchantCheckStatus.FAILURE declared at extension b2bcommerce.
	*/
	public static final MerchantCheckStatus FAILURE = valueOf("FAILURE");
	
	
	/** The code of this enum.*/
	private final String code;
	private final String codeLowerCase;
	private static final long serialVersionUID = 0L;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private MerchantCheckStatus(final String code)
	{
		this.code = code.intern();
		this.codeLowerCase = this.code.toLowerCase().intern();
	}
	
	
	/**
	 * Compares this object to the specified object. The result is <code>true</code>
	 * if and only if the argument is not <code>null</code> and is an <code>MerchantCheckStatus
	 * </code> object that contains the enum value <code>code</code> as this object.
	 *  
	 * @param obj the object to compare with.
	 * @return <code>true</code> if the objects are the same;
	 *         <code>false</code> otherwise.
	 */
	@Override
	public boolean equals(final Object obj)
	{
		try
		{
			final HybrisEnumValue enum2 = (HybrisEnumValue) obj;
			return this == enum2
			|| (enum2 != null && !this.getClass().isEnum() && !enum2.getClass().isEnum()
			&& this.getType().equalsIgnoreCase(enum2.getType()) && this.getCode().equalsIgnoreCase(enum2.getCode()));
		}
		catch (final ClassCastException e)
		{
			return false;
		}
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
	
	/**
	 * Returns a hash code for this <code>MerchantCheckStatus</code>.
	 *  
	 * @return a hash code value for this object, equal to the enum value <code>code</code>
	 *         represented by this <code>MerchantCheckStatus</code> object.
	 */
	@Override
	public int hashCode()
	{
		return this.codeLowerCase.hashCode();
	}
	
	/**
	 * Returns the code representing this enum value.
	 *  
	 * @return a string representation of the value of this object.
	 */
	@Override
	public String toString()
	{
		return this.code.toString();
	}
	
	/**
	 * Returns a <tt>MerchantCheckStatus</tt> instance representing the specified enum value.
	 *  
	 * @param code an enum value
	 * @return a <tt>MerchantCheckStatus</tt> instance representing <tt>value</tt>. 
	 */
	public static MerchantCheckStatus valueOf(final String code)
	{
		final String key = code.toLowerCase();
		MerchantCheckStatus result = cache.get(key);
		if (result == null)
		{
			MerchantCheckStatus newValue = new MerchantCheckStatus(code);
			MerchantCheckStatus previous = cache.putIfAbsent(key, newValue);
			result = previous != null ? previous : newValue;
		}
		return result;
	}
	
	private Object writeReplace()
	{
		return new de.hybris.bootstrap.typesystem.HybrisDynamicEnumValueSerializedForm(this.getClass(), getCode());
	}
	
}
