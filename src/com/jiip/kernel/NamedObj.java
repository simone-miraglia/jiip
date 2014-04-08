/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * <p>This class implements a NamedObj. It is an abstract class which has at least four main concrete objects:
 * Actor, Port, Attribute and Relation. A NamedObj can have a containment relationship with another NamedObj; furthermore
 * it's defined by a unique name (in the context of the same container, which means two NamedObj can have the same name
 * iff are contained in different containers) and a class. </p>
 * 
 * <p>
 * Since there is a containment relationship, a list of contained NamedObj should be needed in this class.
 * Truth is not every concrete NamedObj can have other contained NamedObj; so we explicit represent a list of contained
 * object only where is needed. Since every NamedObj has attributes, a <i>list of attributes</i> is defined at this level.
 * 
 * </p>
 * @author Simone Miraglia
 * @see Actor
 * @see Port
 * @see Attribute
 * @see Relation
 */
public abstract class NamedObj implements Nameable, Exportable, Classable
{

	/**
	 * Unique name identifier inside its container.
	 * */
	private String _name;
	
	/**
	 * Valid class name of Ptolemy.
	 * */
	private String _className;
	
	
	/**
	 * Specifies the object which contains this.
	 * */
	private NamedObj _container;
	
	/**
	 * A container for attributes. Hashtable should grant fast access to attributes.
	 * Key value is the attribute name itself.
	 * */
	private Hashtable<String, Attribute> _attributeList;
	
	/**
	 * Default constructor.
	 * */
	public NamedObj()
	{
		_name = "";
		_container = null;
		_className = "";
	}
	
	/**
	 * Constructr with name and class
	 * @param name Name of the object
	 * @param className class of the object
	 * */
	public NamedObj(String name, String className)
	{
		_name = name;
		_className = className;
		_container = null;
	}
	
	/**
	 * Returns the name of the NamedObj. Implements Nameable getName()
	 * @see Nameable
	 * @return The name of the NamedObj
	 * */
	public String getName()
	{
		return _name;
	}

	/**
	 * Set the name of the NamedObj. This must be unique inside the context of its container.
	 * Implements Nameable setName()
	 * @param name Name of the NamedObj
	 * @see Nameable
	 * */
	public void setName(String name)
	{
		/*
		 * TODO: a routine is needed to check if the given name is unique inside this NamedObj container.
		 * If name is not unique, an exception should be thrown
		 * */
		_name = name;
	}
	
	/**
	 * Returns the class name of the NamedObj. Implements Classable getClassName().
	 * @see Classable
	 * @return The name of the NamedObj
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
		/*
		 * no check is done to test if the given name is a valid class name. Requires a sort of valid class list.
		 * */
		_className = name;
	}

	/**
	 * Every NamedObj is potentially contained in another NamedObj. 
	 * Returns its container. If this is a top level NamedObj, null value is returned.
	 * @return The NamedObj container
	 * */
	public NamedObj getContainer()
	{
		return _container;
	}
	
	/**
	 * Every NamedObj is potentially contained in another NamedObj. 
	 * Set its container. Null value means no container. The scope is protected because
	 * this function is expected to be call indirectly other classes. 
	 * @param container Parent container of this object
	 * */
	protected void setContainer(NamedObj container)
	{
		/*
		 * TODO: should i be able to change container?
		 * */
		_container = container;
	}
	
	/**
	 * Add a new attribute to the set of attributes. 
	 * @param attribute The attribute you want to add.
	 * @throws Exception If you add an existing attribute
	 * */
	public void addAttribute(Attribute attribute) throws Exception
	{
		/*
		 * First of all, a check is needed to test if the given attribute is already present.
		 * No attribute found means the given attribute can be added.
		 * Otherwise an exception is raised. 
		 * 
		 * */
		/*FIXME: does not work! always get a null ptr exception*/
		if (!_attributeList.containsKey(((attribute.getName()))))
		{
			//Add the attribute
			_attributeList.put(attribute.getName(), attribute);
			
			/*
			 * If i add an attribute to this specific NamedObj, then i must set this
			 * object to be its container
			*/
			attribute.setContainer(this);
		}
		else
			throw new Exception("The attribute you are trying to add already exists.");
	}
	
	/**
	 * Remove an attribute from the existing set of attributes.
	 * @param attribute The name of attribute you want to remove.
	 * @throws Exception If you remove a non-existing attribute
	 * */
	public void removeAttribute(String attribute) throws Exception
	{
		/*
		 * First of all, a check is needed to test if the given attribute is already present.
		 * No attribute found means the given attribute can't be removed and an exception is raised.
		 * Otherwise go ahead removing. 
		 * */
		if (!_attributeList.containsKey((attribute)))
			throw new Exception("Can't remove given attribute. Attribute not found!");
		else
		{
			//Remove the attribute
			Attribute a = _attributeList.remove(attribute);
			
			/*
			 * If i remove an attribute to this specific NamedObj, then i must unset this
			 * object to be its container
			*/
			a.setContainer(null);
		}
	}
	
	/**
	 * Get an attribute from the existing set of attributes.
	 * @param name The attribute name
	 * @throws Exception If you search a non-existing attribute
	 * @return The searched attribute
	 * */
	public Attribute getAttribute(String name) throws Exception
	{
		//Find the attribute
		Attribute a = _attributeList.get(name);
		
		// if no attribute is found, then throw an exception
		if (a == null)
			throw new Exception("Attribute not found!");
		
		return a;
	}
	
	/**
	 * Returns the set of attributes as an ArrayList
	 * @see ArrayList
	 * @return The set of attributes
	 * */
	public ArrayList<Attribute> getAttributeList()
	{
		return new ArrayList<Attribute> (_attributeList.values());
	}
}
