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
	/**
	 * Default constructor.
	 */
	public IOPort()
	{
		super();
	}

	/**
	 * Default constructor. Allows to choose type of the port.
	 * @param type type of the port (INPUT, OUTPUT, INPUT_OUTPUT)
	 * @throws Exception 
	 */
	public IOPort(PortType type) throws Exception
	{
		super();
		if(type.equals(PortType.INPUT) || type.equals(PortType.INPUT_OUTPUT))
			this.addAttribute(new Attribute("input", ""));
		
		if(type.equals(PortType.OUTPUT) || type.equals(PortType.INPUT_OUTPUT))
			this.addAttribute(new Attribute("output", ""));
	}

	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * @throws Exception 
	 * */
	public IOPort(String name, String className)
	{
		super(name, className);
	}
	
	/**
	 * Constructor with name and class. Allows to choose type of the port.
	 * @param name Name of the object
	 * @param className class of the object
	 * @param type type of the port (INPUT, OUTPUT, INPUT_OUTPUT)
	 * @throws Exception 
	 * */
	public IOPort(String name, String className, PortType type) throws Exception
	{
		super(name, className);
		
		if(type.equals(PortType.INPUT) || type.equals(PortType.INPUT_OUTPUT))
			this.addAttribute(new Attribute("input", ""));
		
		if(type.equals(PortType.OUTPUT) || type.equals(PortType.INPUT_OUTPUT))
			this.addAttribute(new Attribute("output", ""));
	}
	
	/**
	 * Check if this is an input port.
	 * @return true if it is an input port
	 */
	public boolean isInput()
	{
		return this.hasAttribute("input");
	}
	
	/**
	 * Check if this is an output port.
	 * @return true if it is an output port
	 */
	public boolean isOutput()
	{
		return this.hasAttribute("output");
	}
}