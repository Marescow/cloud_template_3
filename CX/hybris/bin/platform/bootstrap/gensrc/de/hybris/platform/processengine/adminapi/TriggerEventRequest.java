/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:34
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.processengine.adminapi;

import java.io.Serializable;


import java.util.Objects;
public  class TriggerEventRequest  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>TriggerEventRequest.event</code> property defined at extension <code>processing</code>. */
	
	private String event;

	/** <i>Generated property</i> for <code>TriggerEventRequest.choice</code> property defined at extension <code>processing</code>. */
	
	private String choice;
	
	public TriggerEventRequest()
	{
		// default constructor
	}
	
	public void setEvent(final String event)
	{
		this.event = event;
	}

	public String getEvent() 
	{
		return event;
	}
	
	public void setChoice(final String choice)
	{
		this.choice = choice;
	}

	public String getChoice() 
	{
		return choice;
	}
	

}