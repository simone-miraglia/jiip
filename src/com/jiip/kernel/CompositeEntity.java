/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;

/**
 * @author simone
 *
 */
public abstract class CompositeEntity extends Entity
{

	/**
	 * Default constructor.
	 * */
	public CompositeEntity()
	{
		super();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public CompositeEntity(String name, String className)
	{
		super(name, className);
	}
	
	
	/**
	 * Add a new Entity to the set of entities. 
	 * @param entity The Entity you want to add.
	 * @throws Exception If you add an existing Entity
	 * */
	public void addEntity(Entity entity) throws Exception
	{
		/* remember: setContainer() */
	}
	
	/**
	 * Remove an Entity from the existing set of entities.
	 * @param name The name of the Entity you want to remove.
	 * @throws Exception If you remove a non-existing Entity
	 * */
	public void removeEntity(String name) throws Exception
	{
		/*remember: unset container*/
	}
	
	/**
	 * Get an Entity from the existing set of entities
	 * @param name Entity name
	 * @return Entity with the given name, null otherwise
	 * @throws Exception if there is no Entity with the given name
	 * */
	public Entity getEntity(String name) throws Exception
	{
		return null;
	}

	/**
	 * Returns the set of entities as an ArrayList
	 * @return ArrayList Entity list
	 * */
	public ArrayList<Entity> entityList()
	{
		return null;
	}
}