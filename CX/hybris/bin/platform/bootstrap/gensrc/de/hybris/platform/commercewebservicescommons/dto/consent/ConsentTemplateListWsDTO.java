/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:35
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicescommons.dto.consent;

import java.io.Serializable;
import de.hybris.platform.commercewebservicescommons.dto.consent.ConsentTemplateWsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;


import java.util.Objects;
/**
 * Representation of a Consent Template List
 */
@ApiModel(value="ConsentTemplateList", description="Representation of a Consent Template List")
public  class ConsentTemplateListWsDTO  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** List of consent templates<br/><br/><i>Generated property</i> for <code>ConsentTemplateListWsDTO.consentTemplates</code> property defined at extension <code>commercewebservicescommons</code>. */
@ApiModelProperty(name="consentTemplates", value="List of consent templates") 	
	private List<ConsentTemplateWsDTO> consentTemplates;
	
	public ConsentTemplateListWsDTO()
	{
		// default constructor
	}
	
	public void setConsentTemplates(final List<ConsentTemplateWsDTO> consentTemplates)
	{
		this.consentTemplates = consentTemplates;
	}

	public List<ConsentTemplateWsDTO> getConsentTemplates() 
	{
		return consentTemplates;
	}
	

}