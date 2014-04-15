/**
 * 
 */
package com.jiip.kernel;

/**
 * @author simone
 *
 */
public class Director extends Attribute implements Executable
{

	/**
	 * Default constructor.
	 * */
	public Director()
	{
		super();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public Director(String name, String className)
	{
		super(name, className);
	}

}
