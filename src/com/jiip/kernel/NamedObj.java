/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>This class implements a NamedObj. It is an abstract class which has at least four main concrete objects:
 * Entity, Port, Attribute and Relation. A NamedObj has a containment relationship with another NamedObj.</p>
 * 
 * <p>It implements Nameable, Classable and Exportable interfaces.
 * Every NamedObj has a unique name in the context of the same container,
 * which means two NamedObj can have the same name iff are contained in different containers, and a class.</p>
 * 
 * @author Simone Miraglia
 * @see Entity
 * @see Port
 * @see Attribute
 * @see Relation
 * @see Nameable
 * @see Classable
 * @see Exportable
 */
public abstract class NamedObj implements Nameable, Exportable, Classable
{
	/* ************************************************
	 * *************** Attributes *********************
	 * ************************************************
	 * */
	
	/**
	 * Unique name identifier inside its container.
	 * */
	/*
	 * Must check uniqueness
	 * */
	private String _name;
	
	/**
	 * Valid class name of Ptolemy.
	 * */
	private String _className;
	
	/**
	 * Specifies the container NamedObj.
	 * */
	private NamedObj _container;
	
	/**
	 * A list of contained NamedObjs. Search is by name.
	 */
	/*
	 * Every time a NamedObj is added to some collection within
	 * an istance of this class is also added to this collection.
	 * Could be useful both for check uniqueness of a given contained
	 * NamedObj or to access fast all the contained NamedObjs
	 * */
	private HashMap<String, NamedObj> _containedObjList;
	
	/**
	 * A container for attributes. Search is by name.
	 * */
	/*
	 * HashMap should grant fast access to attributes.
	 * Key value is the attribute name itself.
	 * It is declared as <String, ? extends NamedObj> to allow
	 * usage of add/remove/get methods regardless of the concrete
	 * type (eg, Entity, Attribute, and so on).
	 * */
	private HashMap<String, ? extends NamedObj> _attributeList;
	
	
	/* ************************************************
	 * **************** Methods ***********************
	 * ************************************************
	 * */
	
	/**
	 * Check name existence within the contained NamedObj.
	 * Useful to grant uniqueness.
	 * @param name name to check
	 * @param container NamedObj in which check uniqueness
	 * @return true if name already exists, false otherwise
	 * */
	protected boolean checkNameExistence(String name, NamedObj container)
	{
		/*
		 * To check name uniqueness, lookup in the list
		 * of contained NamedObjs.
		 * If a null value is returned, no object with
		 * the given name exists (so a caller function
		 * could add an object with this name)
		 * */
		NamedObj obj = container._containedObjList.get(name);
		if (obj == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Add a NamedObj obj to a container of NamedObj.
	 * Also check uniqueness of the name and it is not already
	 * added to another NamedObj.
	 * @param obj the NamedObj you want to add
	 * @param list the container
	 * @throws Exception if an object of the container has the same
	 * name of the given NamedObj
	 * */
	protected void add(NamedObj obj, HashMap<String, NamedObj> list) throws Exception
	{
		/*
		 * First check whether the given NamedObj is already added somewhere
		 * (_container is not null) and raise an exception in case
		 */		
		if (obj.getContainer() != null)
			throw new Exception (obj.getName() + " already contained in " + obj.getContainer().getName() + 
					". Cannot be added in " + this.getName() + ".");
		
		/*
		 * Then check whether is already contained.
		 * Two or more NamedObj with the same Name are not allowed.
		 * To do so, check if already exists; if is not, can be added,
		 * otherwise raise an Exception.
		 * */
		if (!checkNameExistence(obj.getName(), this))
		{
			/*
			 * If it is unique, then add the given obj.
			 */
			list.put(obj.getName(), obj);
			
			/*
			 * Make the reference between this object (container) and 
			 * the added NamedObj (contained)
			 * */
			_containedObjList.put(obj.getName(), obj);
			obj.setContainer(this);
		}
		else
			throw new Exception("DuplicateNameException: the item you are trying to add already exists.");
	}
	
	/**
	 * Remove a NamedObj obj from a container of NamedObj.
	 * @param key the name of the NamedObj you want to remove
	 * @param list the container
	 * @return the removed NamedObj
	 * @throws Exception if there is no element with such key inside the container
	 * */
	protected NamedObj remove(String key, HashMap<String, NamedObj> list) throws Exception
	{
		/*
		 * First check whether a NamedObj with the given key actually exists.
		 * Cannot remove a non-contained element.
		 * */
		NamedObj t = list.get(key);
		
		/*
		 * If search returns a null value, then the given NamedObj is not contained
		 * so raise an Exception.
		 * Otherwise, it is and can be removed
		 * */
		if (t != null)
		{
			/*
			 * If it is contained, then remove
			 * */
			t = list.remove(key);
			
			/* and delete the reference between this NamedObj (container)
			 * and t (contained)
			 * (If it is contained ==> it also contained in _containedObjList,
			 * so next remove is ok)
			 * */
			_containedObjList.remove(key); 
			t.setContainer(null);
			
			/*
			 * Eventually returns the removed NamedObj
			 * */
			return t;
		}
		else
			throw new Exception("The item you are trying to remove do not exists.");
	}
	
	/**
	 * Get a NamedObj from a container of NamedObj (without removing it)
	 * @param key name of the NamedObj you want to retrieve
	 * @param list the container
	 * @return the searched NamedObj
	 * @throws Exception if there is no element with such key inside the container
	 * */
	protected NamedObj get(String key, HashMap<String, NamedObj> list) throws Exception
	{
		/*
		 * Just lookup in the list. Null value returned means there is
		 * no NamedObj with the given key (so raise an exception).
		 * */
		NamedObj obj = list.get(key);
		if (obj != null)
			return obj;
		else
			throw new Exception("No item found.");
	}
	
	/**
	 * Default constructor.
	 * */
	public NamedObj()
	{
		_name = "";
		_container = null;
		_className = "";
		_attributeList = new HashMap<String, Attribute>();
		_containedObjList = new HashMap<String, NamedObj>();
	}
	
	/**
	 * Constructor with name and class
	 * @param name name of the object
	 * @param className class of the object
	 * */
	public NamedObj(String name, String className)
	{
		_name = name;
		_className = className;
		_container = null;
		_attributeList = new HashMap<String, Attribute>();
		_containedObjList = new HashMap<String, NamedObj>();
	}
	
	/**
	 * Returns the name of the NamedObj. Implements Nameable getName()
	 * @see Nameable
	 * @return name of the NamedObj
	 * */
	public String getName()
	{
		return _name;
	}

	/**
	 * Set the name of the NamedObj. This must be unique inside the context of its container.
	 * Implements Nameable setName()
	 * @param name name of the NamedObj
	 * @throws Exception 
	 * @see Nameable
	 * */
	public void setName(String name) throws Exception
	{
		if (!checkNameExistence(name, _container))
			_name = name;
		else
			throw new Exception("DuplicateNameException. Name is not unique within this NamedObj.");
	}
	
	/**
	 * Returns class name of the NamedObj. Implements Classable getClassName().
	 * @see Classable
	 * @return the name of the NamedObj
	 * */
	public String getClassName()
	{
		return _className;
	}
	
	/**
	 * Set the class name of the NamedObj. This must should be a valid class name inside Ptolemy.
	 * Implements Classable setClassName()
	 * @param name Class name of the NamedObj
	 * @see Classable
	 * */
	public void setClassName(String name)
	{
		_className = name;
	}

	/**
	 * Every NamedObj is potentially contained in another NamedObj. 
	 * Returns its container. If this is a top level NamedObj, null value is returned.
	 * @return the container of this NamedObj
	 * */
	public NamedObj getContainer()
	{
		return _container;
	}
	
	/**
	 * Every NamedObj is potentially contained in another NamedObj. 
	 * Set its container. Null value means no container.  
	 * @param container parent container of this object
	 * */

	/*
	 * Protected because container is set/unset during
	 * add/remove operations, so users cannot use this
	 * function in order to preserve "integrity"
	 * */
	protected void setContainer(NamedObj container)
	{
		_container = container;
	}
	
	/**
	 * Add a new attribute to the set of attributes. 
	 * @param attribute The attribute you want to add.
	 * @throws Exception If you add an existing attribute
	 * */
	@SuppressWarnings("unchecked")
	public void addAttribute(Attribute a) throws Exception
	{
		add(a, (HashMap<String, NamedObj>) _attributeList);
	}
	
	/**
	 * Remove an attribute from the existing set of attributes.
	 * @param name The name of attribute you want to remove.
	 * @throws Exception If you remove a non-existing attribute
	 * */
	@SuppressWarnings("unchecked")
	public Attribute removeAttribute(String name) throws Exception
	{
		return (Attribute) remove (name, (HashMap<String, NamedObj>) _attributeList);
	}
	
	/**
	 * Get an attribute from the existing set of attributes.
	 * @param name The attribute name
	 * @throws Exception If you search a non-existing attribute
	 * @return The searched attribute
	 * */
	@SuppressWarnings("unchecked")
	public Attribute getAttribute(String name) throws Exception
	{
		return (Attribute) get(name, (HashMap<String, NamedObj>) _attributeList);
	}
	
	/**
	 * Returns the set of attributes as an ArrayList
	 * @see ArrayList
	 * @return The set of attributes
	 * */
	@SuppressWarnings("unchecked")
	public ArrayList<Attribute> attributeList()
	{
		return (ArrayList<Attribute>) _attributeList.values();
	}
	
	public ArrayList<NamedObj> containedList()
	{
		return new ArrayList<NamedObj>(_containedObjList.values());
	}
}