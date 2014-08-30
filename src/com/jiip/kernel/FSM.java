package com.jiip.kernel;

import java.util.ArrayList;

public class FSM extends CompositeActor
{

	public FSM()
	{
		super();
	}

	public FSM(String name)
	{
		super(name, "ptolemy.domains.modal.modal.ModalController");
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public FSMState getState(String name)
	{
		try
		{
			return (FSMState) this.getEntity(name);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<FSMState> stateList()
	{
		ArrayList<FSMState> states = new ArrayList<FSMState>();
		for (Entity e : this.entityList())
			states.add((FSMState) e);
		return states;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<FSMTransition> transitionList()
	{
		ArrayList<FSMTransition> transitions = new ArrayList<FSMTransition>();
		for (Relation r : this.relationList())
			transitions.add((FSMTransition) r);
		return transitions;
	}
}
