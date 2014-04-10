/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;

/**
 * @author simone
 *
 */
public class Relation extends NamedObj
{

	/**
	 * Default constructor.
	 * */
	public Relation()
	{
		super();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public Relation(String name, String className)
	{
		super(name, className);
	}
	
	/**
	 * Add a new port to the set of linked ports. 
	 * @param port The port you want to add.
	 * @throws Exception If you add an existing port
	 * */
	public void addPort(Port p) throws Exception
	{
		/* remember: setContainer() */
	}
	
	/**
	 * Remove a port from the existing set of linked ports.
	 * @param name The name of the port you want to remove.
	 * @throws Exception If you remove a non-existing port
	 * */
	public void removePort(String name) throws Exception
	{
		/*remember: unset container*/
	}
	
	/**
	 * Get a Port from the existing set of linked ports
	 * @param name Port name
	 * @return Port with the given name, null otherwise
	 * @throws Exception if there is no Port with the given name
	 * */
	public Port getPort(String name) throws Exception
	{
		return null;
	}

	/**
	 * Returns the set of linked ports as an ArrayList
	 * @return ArrayList linked port list
	 * */
	public ArrayList<Port> linkedPortList()
	{
		return null;
	}

	/**
	 * 
	 * */
	public void export()
	{
		/*dummy stuff here*/
	}

}
