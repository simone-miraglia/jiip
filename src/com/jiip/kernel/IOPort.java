/**
 * 
 */
package com.jiip.kernel;

/**
 * @author Simone Miraglia
 *
 */
public class IOPort extends Port
{

	public IOPort() throws Exception
	{
		super();
		this.addAttribute(new Attribute("input", ""));
		this.addAttribute(new Attribute("output", ""));
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public IOPort(String name, String className)
	{
		super(name, className);
	}
	
	
	public boolean isInput()
	{
		//TODO
		return true;
	}
	
	public boolean isOutput()
	{
		//TODO
		return false;
	}
	/*
	public void export()
	{

	}
*/
}
