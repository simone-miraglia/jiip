package com.jiip.kernel;

import java.util.ArrayList;

/**
 * TODO FSMState description
 * @author simone
 *
 */
public class FSMState extends CompositeEntity implements Executable
{
	/**
	 * Refinement
	 * */
	private CompositeEntity _refinement;

	/**
	 * Default constructor.
	 */
	public FSMState()
	{
		super();
	}

	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 */
	public FSMState(String name, String className)
	{
		super(name, className);
	}
	
	/**
	 * Return the set of outgoing transitions from this state.
	 * @return set of outgoing transitions
	 */
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
	
	/**
	 * Return the set of incoming transitions from this state.
	 * @return set of incoming transitions
	 */
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

	@Override
	public boolean isAtomic()
	{
		return _refinement == null;
	}

	/**
	 * Check if this state has a refinement.
	 * @return true if this state has a refinement
	 */
	public boolean hasRefinement()
	{
		return _refinement != null;
	}
	
	/**
	 * Get refinement of this state, if any
	 * @return the refinement
	 */
	public CompositeEntity getRefinement()
	{
		return _refinement;
	}

	/**
	 * Set refinement of this state. Null means no refinement.
	 * @param refinement to set
	 */
	public void setRefinement(CompositeEntity refinement)
	{
		this._refinement = refinement;
	}
}