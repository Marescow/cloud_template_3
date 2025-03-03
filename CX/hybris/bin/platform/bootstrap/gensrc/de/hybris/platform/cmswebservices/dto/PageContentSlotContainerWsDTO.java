/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:41
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.cmswebservices.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Specifies properties of the page content slot container.
 */
@ApiModel(value="PageContentSlotContainerWsDTO", description="Specifies properties of the page content slot container.")
public  class PageContentSlotContainerWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>PageContentSlotContainerWsDTO.pageId</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="pageId", example="homepage") 	
	private String pageId;

	/** <i>Generated property</i> for <code>PageContentSlotContainerWsDTO.slotId</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="slotId", example="Section1Slot-Homepage") 	
	private String slotId;

	/** <i>Generated property</i> for <code>PageContentSlotContainerWsDTO.containerId</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="containerId") 	
	private String containerId;

	/** <i>Generated property</i> for <code>PageContentSlotContainerWsDTO.containerType</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="containerType") 	
	private String containerType;

	/** <i>Generated property</i> for <code>PageContentSlotContainerWsDTO.components</code> property defined at extension <code>cmswebservices</code>. */
@ApiModelProperty(name="components") 	
	private List<String> components;
	
	public PageContentSlotContainerWsDTO()
	{
		// default constructor
	}
	
	public void setPageId(final String pageId)
	{
		this.pageId = pageId;
	}

	public String getPageId() 
	{
		return pageId;
	}
	
	public void setSlotId(final String slotId)
	{
		this.slotId = slotId;
	}

	public String getSlotId() 
	{
		return slotId;
	}
	
	public void setContainerId(final String containerId)
	{
		this.containerId = containerId;
	}

	public String getContainerId() 
	{
		return containerId;
	}
	
	public void setContainerType(final String containerType)
	{
		this.containerType = containerType;
	}

	public String getContainerType() 
	{
		return containerType;
	}
	
	public void setComponents(final List<String> components)
	{
		this.components = components;
	}

	public List<String> getComponents() 
	{
		return components;
	}
	

}