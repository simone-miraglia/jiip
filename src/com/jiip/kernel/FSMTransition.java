package com.jiip.kernel;

/**
 * TODO FSMTransition description
 * @author simone
 *
 */
public class FSMTransition extends Relation
{
	/**
	 * Name of the attribute "guard expression"
	 */
	private final String guardExpressionString = "guardExpression";
	
	/**
	 * Name of the "incoming port"
	 */
	private final String incomingPortString = "incomingPort";
	
	/**
	 * Name of the "outgoing port"
	 */
	private final String outgoingPortString = "outgoingPort";
	
	/**
	 * Default constructor.
	 */
	public FSMTransition()
	{
		super();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 */
	public FSMTransition(String name, String className)
	{
		super(name, className);
	}
	
	/**
	 * Get value of guard expression for this transition, if any.
	 * @return value of guard expression
	 */
	public String getGuardExpression()
	{
		try
		{
			if(this.hasAttribute(guardExpressionString))
				return this.getAttribute(guardExpressionString).getValue();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Set value of guard expression for this transition.
	 * @param guardExpression value of the guard expression to set
	 */
	public void setGuardExpression(String guardExpression)
	{
		try
		{
			if(this.hasAttribute(guardExpressionString))
				this.getAttribute(guardExpressionString).setValue(guardExpression);
			else
				this.addAttribute(new Attribute(guardExpressionString, "ptolemy.kernel.util.StringAttribute"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the incoming state of this transition.
	 * @return the incoming state
	 */
	public FSMState getIncomingState()
	{
		/*
		 * Two ports linked to this transition.
		 * Check which one is the outgoing port in order
		 * to get the incoming state (its container) 
		 */
		try
		{
			//just two ports
			for(Port p : this.linkedPortList())
				if (p.getName().equals(outgoingPortString))
					return (FSMState) p.getContainer();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get the outgoing state of this transition.
	 * @return the outgoing state
	 */
	public FSMState getOutgoingState()
	{
		try
		{
			/*
			 * Two ports linked to this transition.
			 * Check which one is the incoming port in order
			 * to get the outgoing state (its container) 
			 */
			for(Port p : this.linkedPortList())
				if (p.getName().equals(incomingPortString))
					return (FSMState) p.getContainer();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}