/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;

/**
 * TODO Entity description
 * @author simone
 *
 */
public abstract class Entity extends NamedObj
{
	/**
	 * List of ports.
	 */
	private ArrayList <? extends NamedObj> _portList;
	
	/**
	 * Entity director, if presents.
	 */
	private Director _director;
	
	
	/**
	 * Default constructor.
	 * */
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
	 * Returns a boolean value indicating whether this Entity is atomic or not.
	 * @return True if entity is atomic, false otherwise
	 * */
	public abstract boolean isAtomic();
	
	/**
	 * Returns a boolean value indicating whether this Entity has a director or not.
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
	 * @param obj The port you want to remove.
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
	 * @return true if the port exists
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
	 * Add a director to this entity.
	 * @param d director to add
	 * @throws Exception
	 */
	public void addDirector(Director d) throws Exception
	{
		_director = d;
		this.addAttribute(new Attribute(d.getName(), d.getClassName()));
	}
	
	/**
	 * Remove the added director.
	 * @return true if correctly removed
	 * @throws Exception if no director is present
	 */
	public boolean removeDirector() throws Exception
	{
		
		boolean t = this.removeAttribute(_director);
		_director = null;
		return t;
	}
	
	/**
	 * Get the director.
	 * @return entity director.
	 * @throws Exception if no director exists
	 */
	public Director getDirector() throws Exception
	{
		return (Director) this.getAttribute(_director.getName());
	}

}
