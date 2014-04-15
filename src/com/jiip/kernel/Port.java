package com.jiip.kernel;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Port extends NamedObj
{
	private HashMap <String, Relation> _linkedRelList;
	/**
	 * Default constructor.
	 * */
	public Port()
	{		
		super();
		_linkedRelList = new HashMap <String, Relation>();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public Port(String name, String className)
	{
		super(name, className);
		_linkedRelList = new HashMap <String, Relation>();
	}
	
	/**
	 * Add this port to an existing relation. 
	 * @param r The relation you want to add this port to.
	 * @throws Exception If you link twice with the same relation
	 * */
	public void link(Relation r) throws Exception
	{		
		/*
		 * First check whether relation is already contained.
		 * Cannot link twice with the same relation
		 * */
		Relation t = _linkedRelList.get(r.getName());
		if (t == null)
		{
			/*
			 * If it is unique, add relation to list
			 * and set this port to be in the relation
			 * */
		
			_linkedRelList.put(r.getName(), r);
			r.getLinkedPortHashMap().put(this.getName(), this);
		}
		else
			throw new Exception("DuplicateNameException: cannot add a port to the same relation.");
	}
	
	/**
	 * Remove this port from an existing relation.
	 * @param r The relation you want to unlink with.
	 * @throws Exception If you unlink with a relation without this port
	 * */
	public void unlink(Relation r) throws Exception
	{
		/*TODO: do not know if needed or should be implemented in Relation*/
		
		/*
		 * First check whether port is contained in relation r
		 * Cannot unlink from a Port non contained in a relation
		 * */
		Port t = r.getLinkedPortHashMap().get(this.getName());
		if (t != null)
		{
			/*
			 * If Port exists, then unlink
			 * remove relation from to linked relation list
			 * and remove port form relation linked port list
			 * */
			_linkedRelList.remove(r.getName());
			r.getLinkedPortHashMap().remove(this.getName());
		}
		else
			throw new Exception("DuplicateNameException: port non contained in given relation.");
	}
	
	/**
	 * Get a relation from the existing set of linked relations
	 * @param name Relation name
	 * @return Relation with the given name, null otherwise
	 * @throws Exception if there is no Relation with the given name
	 * */
	public Relation getRelation(String name) throws Exception
	{
		//TODO
		return null;
	}

	/**
	 * Returns the set of linked relations as an ArrayList
	 * @return ArrayList linked relation list
	 * */
	public ArrayList<Relation> linkedRelationList()
	{
		//TODO
		return null;
	}
}