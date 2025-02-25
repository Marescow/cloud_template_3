/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.chinesecommercewebservicescommons.dto;

import java.io.Serializable;
import de.hybris.platform.chinesecommercewebservicescommons.dto.DeliveryTimeSlotWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * list of delivery timeslot
 */
@ApiModel(value="deliveryTimeSlotList", description="list of delivery timeslot")
public  class DeliveryTimeSlotListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** list of delivery timeslot<br/><br/><i>Generated property</i> for <code>DeliveryTimeSlotListWsDTO.deliveryTimeSlots</code> property defined at extension <code>chinesecommercewebservicescommons</code>. */
@ApiModelProperty(name="deliveryTimeSlots", value="list of delivery timeslot") 	
	private List<DeliveryTimeSlotWsDTO> deliveryTimeSlots;
	
	public DeliveryTimeSlotListWsDTO()
	{
		// default constructor
	}
	
	public void setDeliveryTimeSlots(final List<DeliveryTimeSlotWsDTO> deliveryTimeSlots)
	{
		this.deliveryTimeSlots = deliveryTimeSlots;
	}

	public List<DeliveryTimeSlotWsDTO> getDeliveryTimeSlots() 
	{
		return deliveryTimeSlots;
	}
	

}