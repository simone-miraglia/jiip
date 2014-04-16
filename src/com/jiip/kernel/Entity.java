/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author simone
 *
 */
public abstract class Entity extends NamedObj
{
	private HashMap <String, ? extends NamedObj> _portList;
	
	/**
	 * Default constructor.
	 * */
	public Entity()
	{
		super();
		_portList = new HashMap <String, Port>();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public Entity(String name, String className)
	{
		super(name, className);
		_portList = new HashMap <String, Port>();
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
	 * @param p The port you want to add.
	 * @throws Exception If you add an existing port
	 * */
	@SuppressWarnings("unchecked")
	public void addPort(Port p) throws Exception
	{
		add(p, (HashMap<String, NamedObj>) _portList);
	}
	
	/**
	 * Remove a port from the existing set of ports.
	 * @param name The name of the port you want to remove.
	 * @throws Exception If you remove a non-existing port
	 * */
	@SuppressWarnings("unchecked")
	public Port removePort(String name) throws Exception
	{
		return (Port) remove (name, (HashMap<String, NamedObj>) _portList);
	}
	
	/**
	 * Get a Port from the existing set of Ports
	 * @param name Port name
	 * @return Port with the given name, null otherwise
	 * @throws Exception if there is no Port with the given name
	 * */
	@SuppressWarnings("unchecked")
	public Port getPort(String name) throws Exception
	{
		return (Port) get(name, (HashMap<String, NamedObj>) _portList);
	}

	/**
	 * Returns the set of Ports as an ArrayList
	 * @return ArrayList Port list
	 * */
	@SuppressWarnings("unchecked")
	public ArrayList<Port> portList()
	{
		return new ArrayList<Port>( (Collection<? extends Port>) _portList.values());
	}
	
	/**
	 * 
	 * *//*
	public void export()
	{
		
	}*/

}
