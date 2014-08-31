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
public class Relation extends NamedObj
{
	private HashMap <String, Port> _portList;
	
	/**
	 * Default constructor.
	 * */
	public Relation()
	{
		super();
		_portList = new HashMap <String, Port>();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public Relation(String name, String className)
	{
		super(name, className);
		_portList = new HashMap <String, Port>();
	}
	
	/**
	 * Add a new port to the set of linked ports. 
	 * @param p The port you want to add.
	 * @throws Exception If you add an existing port
	 * */
	public void addPort(Port p) throws Exception
	{
		/*
		 * port is saved as container.name to avoid collision due to 
		 * multiple name ports contained in different entites
		 * */
		_portList.put(p.getContainer().getName() + "." + p.getName(), p);
	}
	
	/**
	 * Remove a port from the existing set of linked ports.
	 * @param name The name of the port you want to remove.
	 * @throws Exception If you remove a non-existing port
	 * */
/*	@SuppressWarnings("unchecked")
	public Port removePort(String name) throws Exception
	{
		return (Port) remove(name, (HashMap<String, NamedObj>) _portList);
	}
*/
	/**
	 * Get a Port from the existing set of linked ports
	 * @param name Port name
	 * @return Port with the given name, null otherwise
	 * @throws Exception if there is no Port with the given name
	 * */
/*	@SuppressWarnings("unchecked")
	public Port getPort(String name) throws Exception
	{
		return (Port) get(name, (HashMap<String, NamedObj>) _portList);
	}
*/
	/**
	 * Returns the set of linked ports as an ArrayList
	 * @return ArrayList linked port list
	 * */
	public ArrayList<Port> linkedPortList()
	{
		return new ArrayList<Port>((Collection<? extends Port>) _portList.values());
	}

}
