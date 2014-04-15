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
	 * Default constructor.
	 * */
	public AtomicActor()
	{
		super();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public AtomicActor(String name, String className)
	{
		super(name, className);
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
