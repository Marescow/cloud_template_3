/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:36
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.order;

import java.io.Serializable;
import de.hybris.platform.commercewebservicescommons.dto.order.CartWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Objects;
/**
 * Representation of a Save Cart Result
 */
@ApiModel(value="SaveCartResult", description="Representation of a Save Cart Result")
public  class SaveCartResultWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** Cart data information for saved cart<br/><br/><i>Generated property</i> for <code>SaveCartResultWsDTO.savedCartData</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="savedCartData", value="Cart data information for saved cart") 	
	private CartWsDTO savedCartData;
	
	public SaveCartResultWsDTO()
	{
		// default constructor
	}
	
	public void setSavedCartData(final CartWsDTO savedCartData)
	{
		this.savedCartData = savedCartData;
	}

	public CartWsDTO getSavedCartData() 
	{
		return savedCartData;
	}
	

}