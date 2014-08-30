package com.jiip.kernel;

import java.util.ArrayList;

public class FSMTransition extends Relation
{
/**
 * 
 */
	private final String guardExpressionString = "guardExpression";
	private final String incomingPortString = "incomingPort";
	private final String outgoingPortString = "outgoingPort";
	
	public FSMTransition()
	{
		
	}
	/**
	 * 
	 * @param name
	 */
	public FSMTransition(String name)
	{
		super(name, "ptolemy.domains.modal.kernel.Transition");
	}
	
	/**
	 * 
	 * @return
	 */
	public String getGuardExpression()
	{
		if(this.hasAttribute(guardExpressionString))
			try
			{
				return this.getAttribute(guardExpressionString).getValue();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return "";
	}
	
	/**
	 * 
	 * @param guardExpression
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
	 * 
	 * @return
	 */
	public FSMState getIncomingState()
	{
		try
		{
			//just two ports
			for(Port p : this.linkedPortList())
			{
				if (p.getName().equals(outgoingPortString))
				{
					return (FSMState) p.getContainer();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public FSMState getOutgoingState()
	{
		try
		{
			//just two ports
			for(Port p : this.linkedPortList())
			{
				if (p.getName().equals(incomingPortString)) //they are switched
				{
					return (FSMState) p.getContainer();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	

}
