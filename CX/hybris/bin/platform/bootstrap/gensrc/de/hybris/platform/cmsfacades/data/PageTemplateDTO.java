/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:37
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmsfacades.data;

import java.io.Serializable;


import java.util.Objects;
public  class PageTemplateDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>PageTemplateDTO.pageTypeCode</code> property defined at extension <code>cmsfacades</code>. */
	
	private String pageTypeCode;

	/** <i>Generated property</i> for <code>PageTemplateDTO.active</code> property defined at extension <code>cmsfacades</code>. */
	
	private Boolean active;
	
	public PageTemplateDTO()
	{
		// default constructor
	}
	
	public void setPageTypeCode(final String pageTypeCode)
	{
		this.pageTypeCode = pageTypeCode;
	}

	public String getPageTypeCode() 
	{
		return pageTypeCode;
	}
	
	public void setActive(final Boolean active)
	{
		this.active = active;
	}

	public Boolean getActive() 
	{
		return active;
	}
	

}