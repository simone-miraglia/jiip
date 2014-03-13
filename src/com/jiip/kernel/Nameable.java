/**
 * 
 */
package com.jiip.kernel;

/**
 * This is an interface for objects with a name and a container. A simple
 name is an arbitrary string that identifies the object in the context of
 its container. 
 * @author Simone Miraglia
 * @see NamedObj
 * 
 */
public interface Nameable
{
	/**
	 * Returns the NamedObj acting as container, if any.
	 * @see NamedObj
	 * @return The container
	 * */
	public NamedObj getContainer();
	
	
	/**
	 * Returns the name of the NamedObj
	 * @see NamedObj
	 * @return The name of the NamedObj
	 * */
	public String getName();
	
	
	/**
	 * Set the name of the NamedObj.
	 * @param name Name of the NamedObj
	 * @see NamedObj
	 * */
	public void setName(String name);
}
