/**
 * 
 */
package com.jiip.kernel;

/**
 * This is an interface for objects with a class. A class identifies a valid class
 * inside the ones of Ptolemy.
 * @author Simone Miraglia
 * @see PtolemyObj
 *
 */
public interface Classable
{
	/**
	 * Returns the class name of the PtolemyObj
	 * @see PtolemyObj
	 * @return The name of the PtolemyObj
	 * */
	public String getClassName();
	
	/**
	 * Set the class name of the PtolemyObj. This must should be a valid class name inside Ptolemy.
	 * @param name Class name of the PtolemyObj
	 * @see PtolemyObj
	 * */
	public void setClassName(String name);
}