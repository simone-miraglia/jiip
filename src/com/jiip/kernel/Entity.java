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
	private ArrayList <? extends NamedObj> _portList;
	
	/**
	 * Default constructor.
	 * */
	
	Director _director;
	
	public Entity()
	{
		super();
		_portList = new ArrayList <Port>();
		_director = null;
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public Entity(String name, String className)
	{
		super(name, className);
		_portList = new ArrayList <Port>();
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
	public boolean isOpaque()
	{
		return _director != null;
	}
	
	/**
	 * Add a new port to the set of ports. 
	 * @param p The port you want to add.
	 * @throws Exception If you add an existing port
	 * */
	@SuppressWarnings("unchecked")
	public void addPort(Port p) throws Exception
	{
		add(p,  (ArrayList<NamedObj>) _portList);
	}
	
	/**
	 * Remove a port from the existing set of ports.
	 * @param name The name of the port you want to remove.
	 * @throws Exception If you remove a non-existing port
	 * */
	@SuppressWarnings("unchecked")
	public boolean removePort(Port obj) throws Exception
	{
		return remove (obj,  (ArrayList<NamedObj>) _portList);
	}
	
	/**
	 * Get a Port from the existing set of Ports
	 * @param name Port name
	 * @return Port with the given name, null otherwise
	 * @throws Exception 
	 * */
	@SuppressWarnings("unchecked")
	public Port getPort(String name) throws Exception
	{
		return (Port) get(name, (ArrayList<NamedObj>) _portList);
	}
	
	/**
	 * Return true if the given port exists.
	 * @param name Port name
	 * @return Port with the given name, null otherwise
	 * @throws Exception 
	 * */
	@SuppressWarnings("unchecked")
	public boolean hasPort(String name) throws Exception
	{
		return contains(name,  (ArrayList<NamedObj>) _portList);
	}

	/**
	 * Returns the set of Ports as an ArrayList
	 * @return ArrayList Port list
	 * */
	@SuppressWarnings("unchecked")
	public ArrayList<Port> portList()
	{
		return (ArrayList<Port>) _portList;
	}
	
	/**
	 * 
	 * *//*
	public void export()
	{
		
	}*/
	
	/**
	 * @throws Exception 
	 * 
	 * */
	public void addDirector(Director d) throws Exception
	{
		_director = d;
		this.addAttribute(new Attribute(d.getName(), d.getClassName()));
	}
	
	public boolean removeDirector() throws Exception
	{
		
		boolean t = this.removeAttribute(_director);
		_director = null;
		return t;
	}

}
