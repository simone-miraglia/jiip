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
	 * 
	 */
	public IOPort()
	{
		
	}
	
	
	public boolean isInput()
	{
		return true;
	}
	
	public boolean isOutput()
	{
		return false;
	}
	
	public void export()
	{
		/*dummy stuff here*/
	}

}
