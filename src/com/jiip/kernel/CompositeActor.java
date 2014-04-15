package com.jiip.kernel;

public class CompositeActor extends CompositeEntity implements Executable
{
	/**
	 * 
	 * */
	Director _director;
	
	/**
	 * Default constructor.
	 * */
	public CompositeActor()
	{
		super();
		_director = null;
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public CompositeActor(String name, String className)
	{
		super(name, className);
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
	 * @return True wheter this CompositeEntity has a director, false otherwise
	 * */
	public boolean isOpaque()
	{
		return _director != null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * */
	public void addDirector(Director d) throws Exception
	{
		_director = d;
	}
	
	public Director removeDirector() throws Exception
	{
		Director d = _director;
		_director = null;
		return d;
	}
}
