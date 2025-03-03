/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.acceleratorservices.jalo.cms2.pages;

import de.hybris.platform.acceleratorservices.constants.AcceleratorServicesConstants;
import de.hybris.platform.acceleratorservices.jalo.cms2.pages.DocumentPage;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.acceleratorservices.jalo.cms2.pages.EmailPage EmailPage}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedEmailPage extends DocumentPage
{
	/** Qualifier of the <code>EmailPage.fromEmail</code> attribute **/
	public static final String FROMEMAIL = "fromEmail";
	/** Qualifier of the <code>EmailPage.fromName</code> attribute **/
	public static final String FROMNAME = "fromName";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(DocumentPage.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(FROMEMAIL, AttributeMode.INITIAL);
		tmp.put(FROMNAME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EmailPage.fromEmail</code> attribute.
	 * @return the fromEmail - Holds the from email address.
	 */
	public String getFromEmail(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedEmailPage.getFromEmail requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, FROMEMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EmailPage.fromEmail</code> attribute.
	 * @return the fromEmail - Holds the from email address.
	 */
	public String getFromEmail()
	{
		return getFromEmail( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EmailPage.fromEmail</code> attribute. 
	 * @return the localized fromEmail - Holds the from email address.
	 */
	public Map<Language,String> getAllFromEmail(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,FROMEMAIL,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EmailPage.fromEmail</code> attribute. 
	 * @return the localized fromEmail - Holds the from email address.
	 */
	public Map<Language,String> getAllFromEmail()
	{
		return getAllFromEmail( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EmailPage.fromEmail</code> attribute. 
	 * @param value the fromEmail - Holds the from email address.
	 */
	public void setFromEmail(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedEmailPage.setFromEmail requires a session language", 0 );
		}
		setLocalizedProperty(ctx, FROMEMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EmailPage.fromEmail</code> attribute. 
	 * @param value the fromEmail - Holds the from email address.
	 */
	public void setFromEmail(final String value)
	{
		setFromEmail( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EmailPage.fromEmail</code> attribute. 
	 * @param value the fromEmail - Holds the from email address.
	 */
	public void setAllFromEmail(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,FROMEMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EmailPage.fromEmail</code> attribute. 
	 * @param value the fromEmail - Holds the from email address.
	 */
	public void setAllFromEmail(final Map<Language,String> value)
	{
		setAllFromEmail( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EmailPage.fromName</code> attribute.
	 * @return the fromName - Holds the from name.
	 */
	public String getFromName(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedEmailPage.getFromName requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, FROMNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EmailPage.fromName</code> attribute.
	 * @return the fromName - Holds the from name.
	 */
	public String getFromName()
	{
		return getFromName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EmailPage.fromName</code> attribute. 
	 * @return the localized fromName - Holds the from name.
	 */
	public Map<Language,String> getAllFromName(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,FROMNAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EmailPage.fromName</code> attribute. 
	 * @return the localized fromName - Holds the from name.
	 */
	public Map<Language,String> getAllFromName()
	{
		return getAllFromName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EmailPage.fromName</code> attribute. 
	 * @param value the fromName - Holds the from name.
	 */
	public void setFromName(final SessionContext ctx, final String value)
	{
		if ( ctx == null) 
		{
			throw new JaloInvalidParameterException( "ctx is null", 0 );
		}
		if( ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedEmailPage.setFromName requires a session language", 0 );
		}
		setLocalizedProperty(ctx, FROMNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EmailPage.fromName</code> attribute. 
	 * @param value the fromName - Holds the from name.
	 */
	public void setFromName(final String value)
	{
		setFromName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EmailPage.fromName</code> attribute. 
	 * @param value the fromName - Holds the from name.
	 */
	public void setAllFromName(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,FROMNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EmailPage.fromName</code> attribute. 
	 * @param value the fromName - Holds the from name.
	 */
	public void setAllFromName(final Map<Language,String> value)
	{
		setAllFromName( getSession().getSessionContext(), value );
	}
	
}
