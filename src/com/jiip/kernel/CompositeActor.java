package com.jiip.kernel;

public class CompositeActor extends CompositeEntity implements Executable
{
	/**
	 * 
	 * */
	Director _director;
	
	public CompositeActor()
	{
		_director = null;
	}
	
	/**
	 * Implements isAtomic() of Entity class.
	 * @see Entity
	 * @return False
	 * */
	public boolean isAtomic()
	{
		return false;
	}

	/**
	 * Implements isOpaque() of Entity class.
	 * @see Entity
	 * @return False
	 * */
	public boolean isOpaque()
	{
		return false;
	}
}
