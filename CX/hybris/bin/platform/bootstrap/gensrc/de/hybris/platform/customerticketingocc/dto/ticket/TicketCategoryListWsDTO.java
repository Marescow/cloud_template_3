/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:38
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.customerticketingocc.dto.ticket;

import java.io.Serializable;
import de.hybris.platform.customerticketingocc.dto.ticket.TicketCategoryWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Represents a list of ticket categories.
 */
@ApiModel(value="TicketCategoryList", description="Represents a list of ticket categories.")
public  class TicketCategoryListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>TicketCategoryListWsDTO.ticketCategories</code> property defined at extension <code>customerticketingocc</code>. */
@ApiModelProperty(name="ticketCategories") 	
	private List<TicketCategoryWsDTO> ticketCategories;
	
	public TicketCategoryListWsDTO()
	{
		// default constructor
	}
	
	public void setTicketCategories(final List<TicketCategoryWsDTO> ticketCategories)
	{
		this.ticketCategories = ticketCategories;
	}

	public List<TicketCategoryWsDTO> getTicketCategories() 
	{
		return ticketCategories;
	}
	

}