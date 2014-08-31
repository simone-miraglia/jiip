package com.jiip.kernel;

import java.util.ArrayList;

/**
 * TODO Port description
 * @author simone
 *
 */
public abstract class Port extends NamedObj
{
	private ArrayList <? extends NamedObj> _linkedRelList;
	
	/**
	 * Default constructor.
	 * */
	public Port()
	{		
		super();
		_linkedRelList = new ArrayList <Relation>();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public Port(String name, String className)
	{
		super(name, className);
		_linkedRelList = new ArrayList <Relation>();
	}
	
	/**
	 * Add this port to an existing relation. 
	 * @param r The relation you want to add this port to.
	 * @throws Exception If you link twice with the same relation
	 * */
	@SuppressWarnings("unchecked")
	public void link(Relation r) throws Exception
	{		
		/*
		 * First check whether relation is already contained, ie
		 * avoid to link twice with the same relation.
		 * */
		if (!_linkedRelList.contains(r))
		{
			/*
			 * If it is unique, add relation to list
			 * and set this port to be in the relation
			 * */
			((ArrayList<Relation>)_linkedRelList).add(r);
			r.addPort(this);
		}
		else
			throw new Exception("DuplicateNameException: cannot add a port to the same relation.");
	}
	
	/**
	 * Remove this port from an existing relation.
	 * @param r The relation you want to unlink with.
	 * @throws Exception If you unlink a relation without this port
	 * */
	public void unlink(Relation r) throws Exception
	{
		//FIXME: where should i put this method? Port or Relation?
		
		/*
		 * First check whether port is contained in relation r
		 * Cannot unlink from a Port non contained in a relation
		 */
		if (r.linkedPortList().contains(this))
		{
			/*
			 * If Port exists, then unlink
			 * remove relation from to linked relation list
			 * and remove port form relation linked port list
			 */
			_linkedRelList.remove(r);
			r.removePort(this);
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
	@SuppressWarnings("unchecked")
	public Relation getRelation(String name) throws Exception
	{
		return (Relation) get(name, (ArrayList<NamedObj>) _linkedRelList);
	}

	/**
	 * Returns the set of linked relations as an ArrayList
	 * @return ArrayList linked relation list
	 * */
	@SuppressWarnings("unchecked")
	public ArrayList<Relation> linkedRelationList()
	{
		return (ArrayList<Relation>) _linkedRelList;
	}
}