/**
 * 
 */
package com.jiip.kernel;

/**
 * @author simone
 *
 */
public class Attribute extends PtolemyObj
{
	private String _value;

	/**
	 * Defautl constructor
	 */
	public Attribute()
	{
		super();
		_value = "";
	}
	
	/**
	 * Constructr with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public Attribute(String name, String className)
	{
		super(name, className);
		_value = "";
	}
	
	/**
	 * Constructr with name, class and value
	 * @param name Name of the attribute
	 * @param className Class of the attribute
	 * @param value Value of the attribute
	 * */
	public Attribute(String name, String className, String value)
	{
		super(name, className);
		_value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return _value;
	}

	/**
	 * @param value the _value to set
	 */
	public void setValue(String value)
	{
		_value = value;
	}
}
