/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 13 févr. 2025, 16:27:39
 * ----------------------------------------------------------------
 *
 * Copyright (c) 2025 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.ruleengineservices.compiler;

import java.io.Serializable;
import de.hybris.platform.ruleengineservices.compiler.RuleIrVariable;
import java.util.Map;


import java.util.Objects;
public  class RuleIrLocalVariablesContainer  implements Serializable 

{

	/** Default serialVersionUID value. */
 
	private static final long serialVersionUID = 1L;

	/** <i>Generated property</i> for <code>RuleIrLocalVariablesContainer.variables</code> property defined at extension <code>ruleengineservices</code>. */
	
	private Map<String,RuleIrVariable> variables;
	
	public RuleIrLocalVariablesContainer()
	{
		// default constructor
	}
	
	public void setVariables(final Map<String,RuleIrVariable> variables)
	{
		this.variables = variables;
	}

	public Map<String,RuleIrVariable> getVariables() 
	{
		return variables;
	}
	

}