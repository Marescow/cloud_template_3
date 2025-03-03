/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.textfieldconfiguratortemplateocctests.jalo;

import de.hybris.platform.directpersistence.annotation.SLDSafe;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.hybris.platform.textfieldconfiguratortemplateocctests.constants.TextfieldconfiguratortemplateocctestsConstants;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>TextfieldconfiguratortemplateocctestsManager</code>.
 */
@SuppressWarnings({"unused","cast"})
@SLDSafe
public class TextfieldconfiguratortemplateocctestsManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
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
	
	public static final TextfieldconfiguratortemplateocctestsManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (TextfieldconfiguratortemplateocctestsManager) em.getExtension(TextfieldconfiguratortemplateocctestsConstants.EXTENSIONNAME);
	}
	
	@Override
	public String getName()
	{
		return TextfieldconfiguratortemplateocctestsConstants.EXTENSIONNAME;
	}
	
}
