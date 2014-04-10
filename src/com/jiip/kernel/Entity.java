/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;

/**
 * @author simone
 *
 */
public abstract class Entity extends NamedObj
{
	/**
	 * Default constructor.
	 * */
	public Entity()
	{
		super();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public Entity(String name, String className)
	{
		super(name, className);
	}
	
	
	/**
	 * Returns a boolean value indicating whether this Entity is atomic or not
	 * @return True if entity is atomic, false otherwise
	 * */
	public abstract boolean isAtomic();
	
	/**
	 * Returns a boolean value indicating whether this Entity has a director or not
	 * @return True if entity has a director, false otherwise
	 * */
	public abstract boolean isOpaque();
	
	/**
	 * Add a new port to the set of ports. 
	 * @param port The port you want to add.
	 * @throws Exception If you add an existing port
	 * */
	public void addPort(Port port) throws Exception
	{
		/* remember: setContainer() */
	}
	
	/**
	 * Remove a port from the existing set of ports.
	 * @param name The name of the port you want to remove.
	 * @throws Exception If you remove a non-existing port
	 * */
	public void removePort(String name) throws Exception
	{
		/*remember: unset container*/
	}
	
	/**
	 * Get a Port from the existing set of Ports
	 * @param name Port name
	 * @return Port with the given name, null otherwise
	 * @throws Exception if there is no Port with the given name
	 * */
	public Port getPort(String name) throws Exception
	{
		return null;
	}

	/**
	 * Returns the set of Ports as an ArrayList
	 * @return ArrayList Port list
	 * */
	public ArrayList<Port> portList()
	{
		return null;
	}
	
	/**
	 * 
	 * */
	public void export()
	{
		/*
		 * dummy stuff
		 * */
	}

}
