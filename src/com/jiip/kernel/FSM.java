package com.jiip.kernel;

import java.util.ArrayList;

/**
 * 
 * @author simone
 *
 */
public class FSM extends CompositeEntity implements Executable
{

	/**
	 * Default constructor.
	 */
	public FSM()
	{
		super();
	}

	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 */
	public FSM(String name, String className)
	{
		super(name, className);
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

	@Override
	public boolean isAtomic()
	{
		return false;
	}
}
