/**
 * 
 */
package com.jiip.kernel;

/**
 * This is an interface for objects with a name and a container. A simple
 name is an arbitrary string that identifies the object in the context of
 its container. 
 * @author Simone Miraglia
 * @see PtolemyObj
 * 
 */
public interface Nameable
{
	/**
	 * Returns the name of the PtolemyObj
	 * @see PtolemyObj
	 * @return The name of the PtolemyObj
	 * */
	public String getName();
	
	/**
	 * Set the name of the PtolemyObj. This must be unique inside the context of its container.
	 * @param name Name of the PtolemyObj
	 * @throws Exception if name it is not unique
	 * @see PtolemyObj
	 * */
	public void setName(String name) throws Exception;
}
