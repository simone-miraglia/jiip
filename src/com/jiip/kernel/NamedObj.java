/**
 * 
 */
package com.jiip.kernel;

/**
 * @author Simone Miraglia
 *
 */
public abstract class NamedObj implements Nameable {

	/**
	 * 
	 * */
	private String _name;
	private NamedObj _container;
	
	public String getName()
	{
		return _name;
	}

	public void setName(String name)
	{
		_name = name;
	}

	public NamedObj getContainer()
	{
		return _container;
	}
}
