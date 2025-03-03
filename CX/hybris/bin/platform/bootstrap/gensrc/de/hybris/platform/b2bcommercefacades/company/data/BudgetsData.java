/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:35
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.b2bcommercefacades.company.data;

import java.io.Serializable;
import de.hybris.platform.b2bcommercefacades.company.data.B2BBudgetData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.search.pagedata.SortData;
import java.util.List;


import java.util.Objects;
public  class BudgetsData  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>BudgetsData.budgets</code> property defined at extension <code>b2bwebservicescommons</code>. */
	
	private List<B2BBudgetData> budgets;

	/** <i>Generated property</i> for <code>BudgetsData.sorts</code> property defined at extension <code>b2bwebservicescommons</code>. */
	
	private List<SortData> sorts;

	/** <i>Generated property</i> for <code>BudgetsData.pagination</code> property defined at extension <code>b2bwebservicescommons</code>. */
	
	private PaginationData pagination;
	
	public BudgetsData()
	{
		// default constructor
	}
	
	public void setBudgets(final List<B2BBudgetData> budgets)
	{
		this.budgets = budgets;
	}

	public List<B2BBudgetData> getBudgets() 
	{
		return budgets;
	}
	
	public void setSorts(final List<SortData> sorts)
	{
		this.sorts = sorts;
	}

	public List<SortData> getSorts() 
	{
		return sorts;
	}
	
	public void setPagination(final PaginationData pagination)
	{
		this.pagination = pagination;
	}

	public PaginationData getPagination() 
	{
		return pagination;
	}
	

}