package com.jiip.kernel;

public class CompositeActor extends CompositeEntity implements Executable
{
	/**
	 * 
	 * */
	
	/**
	 * Default constructor.
	 * */
	public CompositeActor()
	{
		super();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public CompositeActor(String name, String className)
	{
		super(name, className);
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
}
