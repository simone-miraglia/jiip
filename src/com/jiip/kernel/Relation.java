/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author simone
 *
 */
public class Relation extends NamedObj
{
	private HashMap <String, ? extends NamedObj> _portList;
	
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
	/*@SuppressWarnings("unchecked")
	public void addPort(Port p) throws Exception
	{
		add(p, (HashMap<String, NamedObj>) _portList);
	}
	*/
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
	@SuppressWarnings("unchecked")
	public ArrayList<Port> linkedPortList()
	{
		return (ArrayList<Port>) _portList.values();
	}
	
	/**
	 * Returns the set of linked ports as a HashMap
	 * @return HashMap linked port hashmap
	 * */
	/*
	 * do not like this solution..it is just for port link/unlink
	 * */
	@SuppressWarnings("unchecked")
	public HashMap<String, Port> getLinkedPortHashMap()
	{
		return (HashMap<String, Port>) _portList;
	}
	

	/**
	 * 
	 * */
	public void export()
	{
		/*dummy stuff here*/
	}

}
