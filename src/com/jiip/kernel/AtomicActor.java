/**
 * 
 */
package com.jiip.kernel;

/**
 * @author simone
 *
 */
public class AtomicActor extends Entity implements Executable
{

	/**
	 * 
	 */
	public AtomicActor()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Implements isAtomic() of Entity class.
	 * @see Entity
	 * @return True
	 * */
	public boolean isAtomic()
	{
		return true;
	}

	/**
	 * Implements isOpaque() of Entity class.
	 * @see Entity
	 * @return False
	 * */
	public boolean isOpaque()
	{
		/*what is the value?*/
		return false;
	}

}
