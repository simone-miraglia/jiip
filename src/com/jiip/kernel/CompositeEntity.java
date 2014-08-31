/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;

/**
 * TODO CompositeEntity description
 * @author simone
 *
 */
public abstract class CompositeEntity extends Entity
{
	/**
	 * A container for contained entities
	 * */
	private ArrayList <? extends NamedObj> _entityList;
	
	/**
	 * A container for contained relations
	 * */
	private ArrayList <? extends NamedObj> _relList;
	
	/**
	 * Default constructor.
	 * */
	public CompositeEntity()
	{
		super();
		_entityList = new ArrayList <Entity>();
		_relList =  new ArrayList <Relation>();
	}
	
	/**
	 * Constructor with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public CompositeEntity(String name, String className)
	{
		super(name, className);
		_entityList = new ArrayList <Entity>();
		_relList =  new ArrayList <Relation>();
	}
	
	/**
	 * Add a new Entity to the set of entities. 
	 * @param e The Entity you want to add.
	 * @throws Exception If you add an existing Entity
	 * */
	@SuppressWarnings("unchecked")
	public void addEntity(Entity e) throws Exception
	{
		add(e, (ArrayList<NamedObj>) _entityList);
	}
	
	/**
	 * Remove an Entity from the existing set of entities.
	 * @param obj The Entity you want to remove.
	 * @throws Exception If you remove a non-existing Entity
	 * */
	@SuppressWarnings("unchecked")
	public boolean removeEntity(Entity obj) throws Exception
	{
		return remove(obj, (ArrayList<NamedObj>) _entityList);
	}
	
	/**
	 * Get an Entity from the existing set of entities
	 * @param name Entity name
	 * @return Entity with the given name, null otherwise
	 * @throws Exception if you get a non-existing Entity
	 * */
	@SuppressWarnings("unchecked")
	public Entity getEntity(String name) throws Exception 
	{
		return (Entity) get(name, (ArrayList<NamedObj>) _entityList);
	}

	/**
	 * Returns the set of entities as an ArrayList
	 * @return ArrayList Entity list
	 * */
	@SuppressWarnings("unchecked")
	public ArrayList<Entity> entityList()
	{
		return  (ArrayList<Entity>) _entityList;
	}
	
	/**
	 * Add a Relation to the set of relations. 
	 * @param r The Relation you want to add.
	 * @throws Exception If you add an existing Relation
	 * */
	@SuppressWarnings("unchecked")
	public void addRelation(Relation r) throws Exception
	{
		add(r,  (ArrayList<NamedObj>) _relList);
	}
	
	/**
	 * Remove a Relation from the existing set of relations.
	 * @param obj The Relation you want to remove.
	 * @throws Exception If you remove a non-existing Relation
	 * */
	@SuppressWarnings("unchecked")
	public boolean removeRelation(Relation obj) throws Exception
	{
		return remove(obj,  (ArrayList<NamedObj>) _relList);
	}
	
	/**
	 * Get a Relation from the existing set of relations
	 * @param name Relation name
	 * @return Relation with the given name
	 * @throws Exception if you get a non-existing Relation
	 * */
	@SuppressWarnings("unchecked")
	public Relation getRelation(String name) throws Exception 
	{
		return (Relation) get(name,  (ArrayList<NamedObj>) _relList);
	}

	/**
	 * Returns the set of Relation as an ArrayList
	 * @return ArrayList Relation list
	 * */
	@SuppressWarnings("unchecked")
	public ArrayList<Relation> relationList()
	{
		return (ArrayList<Relation>) _relList;
	}
	
	/**
	 * Connect two ports and returns the relation created
	 * @param p a port to connect
	 * @param q a port to connect
	 * @return the relation created
	 * @throws Exception (should not)
	 * */
	/*public Relation connect (Port p, Port q) throws Exception
	{
	//TODO is it really necessary?
		//TODO create unique relation name, now just static
		*
		 * Create a new unique relation, then link p and q to this
		 * relation and finally add r to the set of relation.
		 * 
		Relation r = new Relation("dummy", "ptolemy.actor.TypedIORelation");
		p.link(r);
		q.link(r);
		addRelation(r);
		return r;
	}*/
	
	/**
	 * Disconnect two ports and delete the relation between them
	 * @param p port to disconnect
	 * @param q port to disconnect 
	 * @return deleted relation
	 * */
	/*public Relation disconnect (Port p, Port q) throws Exception
	{
		//TODO
		//find the relation that connect p and q!
		return null;
	}*/
}