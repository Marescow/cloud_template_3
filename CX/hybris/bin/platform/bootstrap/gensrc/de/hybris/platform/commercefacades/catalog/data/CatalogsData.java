/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:38
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercefacades.catalog.data;

import java.io.Serializable;
import de.hybris.platform.commercefacades.catalog.data.CatalogData;
import java.util.List;


import java.util.Objects;
public  class CatalogsData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>CatalogsData.catalogs</code> property defined at extension <code>commercefacades</code>. */
	
	private List<CatalogData> catalogs;
	
	public CatalogsData()
	{
		// default constructor
	}
	
	public void setCatalogs(final List<CatalogData> catalogs)
	{
		this.catalogs = catalogs;
	}

	public List<CatalogData> getCatalogs() 
	{
		return catalogs;
	}
	

}