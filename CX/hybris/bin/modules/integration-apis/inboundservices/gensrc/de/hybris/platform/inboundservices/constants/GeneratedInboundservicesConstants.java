/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 13 févr. 2025, 16:27:28                     ---
 * ----------------------------------------------------------------
 *  
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.inboundservices.constants;

/**
 * @deprecated since ages - use constants in Model classes instead
 */
@Deprecated(since = "ages", forRemoval = false)
@SuppressWarnings({"unused","cast"})
public class GeneratedInboundservicesConstants
{
	public static final String EXTENSIONNAME = "inboundservices";
	public static class TC
	{
		public static final String INBOUNDREQUEST = "InboundRequest".intern();
		public static final String INBOUNDREQUESTERROR = "InboundRequestError".intern();
		public static final String INBOUNDREQUESTMEDIA = "InboundRequestMedia".intern();
		public static final String INTEGRATIONCLIENTCREDENTIALSDETAILS = "IntegrationClientCredentialsDetails".intern();
	}
	public static class Attributes
	{
		public static class ExposedDestination
		{
			public static final String INBOUNDCHANNELCONFIGURATION = "inboundChannelConfiguration".intern();
			public static final String INBOUNDCHANNELCONFIGURATIONPOS = "inboundChannelConfigurationPOS".intern();
		}
		public static class InboundChannelConfiguration
		{
			public static final String EXPOSEDDESTINATIONS = "exposedDestinations".intern();
		}
	}
	public static class Relations
	{
		public static final String INBOUNDCHANNELCONFIGURATION2EXPOSEDDESTINATION = "InboundChannelConfiguration2ExposedDestination".intern();
		public static final String INBOUNDREQUEST2INBOUNDREQUESTERROR = "InboundRequest2InboundRequestError".intern();
	}
	
	protected GeneratedInboundservicesConstants()
	{
		// private constructor
	}
	
	
}
