package com.jiip.kernel;

import java.util.ArrayList;

public abstract class Port extends NamedObj
{
	/**
	 * Default constructor.
	 * */
	public Port()
	{
		super();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public Port(String name, String className)
	{
		super(name, className);
	}
	
	/**
	 * Add this port to an existing relation. 
	 * @param r The relation you want to add this port to.
	 * @throws Exception If you link twice with the same relation
	 * */
	public void link(Relation r) throws Exception
	{
		/* remember: setContainer() */
	}
	
	/**
	 * Remove this port from an existing relation.
	 * @param r The relation you want to unlink with.
	 * @throws Exception If you unlink with a relation without this port
	 * */
	public void unlink(Relation r) throws Exception
	{
		/*remember: unset container*/
	}
	
	/**
	 * Get a relation from the existing set of linked relations
	 * @param name Relation name
	 * @return Relation with the given name, null otherwise
	 * @throws Exception if there is no Relation with the given name
	 * */
	public Relation getRelation(String name) throws Exception
	{
		return null;
	}

	/**
	 * Returns the set of linked relations as an ArrayList
	 * @return ArrayList linked relation list
	 * */
	public ArrayList<Relation> linkedRelationList()
	{
		return null;
	}
}