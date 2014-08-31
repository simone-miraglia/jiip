/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;

/**
 * TODO Relation description
 * @author simone
 *
 */
public class Relation extends NamedObj
{
	/**
	 * List of ports linked with current relation.
	 * */
	private ArrayList <Port> _portList;
	
	/**
	 * Default constructor.
	 * */
	public Relation()
	{
		super();
		_portList = new ArrayList <Port>();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public Relation(String name, String className)
	{
		super(name, className);
		_portList = new ArrayList <Port>();
	}
	
	/**
	 * Add a new port to the set of linked ports. 
	 * @param p The port you want to add.
	 * @throws Exception If you add an existing port
	 * */
	//FIXME odd to have this method public
	public void addPort(Port p) throws Exception
	{
		_portList.add(p);
	}
	
	/**
	 * Remove a port from the existing set of linked ports.
	 * @param obj The port you want to remove.
	 * @return Returns true if this list contained the specified element 
	 * */
	//FIXME odd to have this method public
	public boolean removePort(Port obj)
	{
		return _portList.remove(obj);
	}

	/**
	 * Get a Port from the existing set of linked ports
	 * @param name Port name
	 * @return Port with the given name, null otherwise
	 * @throws Exception if there is no Port with the given name
	 * */
	public Port getPort(String name) throws Exception
	{
		for(Port p : _portList)
			if (p.getName().equals(name))
				return p;
		throw new Exception("Unable to get Port. It does not exist.");
	}

	/**
	 * Returns the set of linked ports as an ArrayList
	 * @return ArrayList linked port list
	 * */
	public ArrayList<Port> linkedPortList()
	{
		return _portList;
	}
}