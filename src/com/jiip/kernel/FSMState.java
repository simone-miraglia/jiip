package com.jiip.kernel;

import java.util.ArrayList;

public class FSMState extends CompositeActor
{

	public FSMState()
	{
		
	}

	public FSMState(String name)
	{
		super(name, "ptolemy.domains.modal.kernel.State");
	}

	/*
	 * TODO refinements......
	 * */
	
	public ArrayList<FSMTransition> outgoingTransitions()
	{
		ArrayList<FSMTransition> transitions = new ArrayList<FSMTransition>();
		try
		{
			for(Relation t : ((FSM)this.getContainer()).relationList())
			{
				FSMTransition ft = (FSMTransition)t;
				if(ft.getIncomingState().getName().equals(this.getName()))
					transitions.add(ft);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return transitions;
	}
	
	public ArrayList<FSMTransition> incomingTransitions()
	{
		ArrayList<FSMTransition> transitions = new ArrayList<FSMTransition>();
		try
		{
			for(Relation t : ((FSM)this.getContainer()).relationList())
			{
				FSMTransition ft = (FSMTransition)t;
				if(ft.getOutgoingState().getName().equals(this.getName()))
					transitions.add(ft);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return transitions;
	}
}
