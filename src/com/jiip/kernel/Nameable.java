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
	 * Returns the name of the NamedObj
	 * @see NamedObj
	 * @return The name of the NamedObj
	 * */
	public String getName();
	
	/**
	 * Set the name of the NamedObj. This must be unique inside the context of its container.
	 * @param name Name of the NamedObj
	 * @throws Exception if name it is not unique
	 * @see NamedObj
	 * */
	public void setName(String name) throws Exception;
}
