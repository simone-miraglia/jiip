/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;

/**
 * TODO ModalModel description
 * @author simone
 *
 */
public class ModalModel extends CompositeEntity
{	
	/**
	 * Default constructor.
	 */
	public ModalModel()
	{
		super();
	}

	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 */
	public ModalModel(String name, String className)
	{
		super(name, className);
	}
	
	@Override
	public boolean isAtomic()
	{
		return false;
	}

	/**
	 * Return list of refinement entities.
	 * @return list of refinement entities
	 */
	public ArrayList<CompositeEntity> refinementList()
	{
		ArrayList<CompositeEntity> refinementList = new ArrayList<CompositeEntity>();
		
		for(Entity e : this.entityList())
			if(e.getClassName().endsWith("Refinement"))
				refinementList.add((CompositeEntity) e);
		
		return refinementList;
	}
	
	/**
	 * Get the associated finite state machine.
	 * @return the associated finite state machine
	 */
	public FSM getFSM()
	{
		for(Entity e : this.entityList())
			if(e.getClassName().endsWith("ModalController"))
				return (FSM) e;
		return null;
	}
}