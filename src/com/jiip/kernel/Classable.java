/**
 * 
 */
package com.jiip.kernel;

/**
 * This is an interface for objects with a class. A class identifies a valid class
 * inside the ones of Ptolemy.
 * @author Simone Miraglia
 * @see NamedObj
 *
 */
public interface Classable
{
	/**
	 * Returns the class name of the NamedObj
	 * @see NamedObj
	 * @return The name of the NamedObj
	 * */
	public String getClassName();
	
	/**
	 * Set the class name of the NamedObj. This must should be a valid class name inside Ptolemy.
	 * @param name Class name of the NamedObj
	 * @see NamedObj
	 * */
	public void setClassName(String name);
}