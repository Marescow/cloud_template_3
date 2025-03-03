/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commerceservices.spring.security;

import de.hybris.bootstrap.util.LocaleHelper;

import de.hybris.platform.spring.security.CoreUserDetails;
import de.hybris.platform.spring.security.CoreUserDetailsService;


/**
 * accelerator specific implementation for providing user data access
 */
public class OriginalUidUserDetailsService extends CoreUserDetailsService
{
	@Override
	public CoreUserDetails loadUserByUsername(final String username)
	{
		if (isOtpVerificationTokenIdFormat(username))
		{
			return super.loadUserByUsername(username);
		}
		else
		{
			return super.loadUserByUsername(username.toLowerCase(LocaleHelper.getPersistenceLocale()));
		}
	}
}
