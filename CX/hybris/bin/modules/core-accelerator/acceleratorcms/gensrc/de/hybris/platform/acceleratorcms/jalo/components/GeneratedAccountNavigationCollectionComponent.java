/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorcms.jalo.components;

import de.hybris.platform.acceleratorcms.constants.AcceleratorCmsConstants;
import de.hybris.platform.acceleratorcms.jalo.components.AccountNavigationComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.acceleratorcms.jalo.components.AccountNavigationCollectionComponent AccountNavigationCollectionComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedAccountNavigationCollectionComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>AccountNavigationCollectionComponent.components</code> attribute **/
	public static final String COMPONENTS = "components";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(COMPONENTS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AccountNavigationCollectionComponent.components</code> attribute.
	 * @return the components - A collection of Account navigation components
	 */
	public List<AccountNavigationComponent> getComponents(final SessionContext ctx)
	{
		List<AccountNavigationComponent> coll = (List<AccountNavigationComponent>)getProperty( ctx, COMPONENTS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AccountNavigationCollectionComponent.components</code> attribute.
	 * @return the components - A collection of Account navigation components
	 */
	public List<AccountNavigationComponent> getComponents()
	{
		return getComponents( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AccountNavigationCollectionComponent.components</code> attribute. 
	 * @param value the components - A collection of Account navigation components
	 */
	public void setComponents(final SessionContext ctx, final List<AccountNavigationComponent> value)
	{
		setProperty(ctx, COMPONENTS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AccountNavigationCollectionComponent.components</code> attribute. 
	 * @param value the components - A collection of Account navigation components
	 */
	public void setComponents(final List<AccountNavigationComponent> value)
	{
		setComponents( getSession().getSessionContext(), value );
	}
	
}
