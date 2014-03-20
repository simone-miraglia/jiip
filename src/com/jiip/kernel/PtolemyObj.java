/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * <p>This class implements a PtolemyObj. It is an abstract class which has at least four main concrete objects:
 * Actor, Port, Attribute and Relation. A PtolemyObj can have a containment relationship with another PtolemyObj; furthermore
 * it's defined by a unique name (in the context of the same container, which means two PtolemyObj can have the same name
 * iff are contained in different containers) and a class. </p>
 * 
 * <p>
 * Since there is a containment relationship, a list of contained PtolemyObj should be needed in this class.
 * Truth is not every concrete PtolemyObj can have other contained PtolemyObj; so we explicit represent a list of contained
 * object only where is needed. Since every PtolemyObj has attributes, a <i>list of attributes</i> is defined at this level.
 * 
 * </p>
 * @author Simone Miraglia
 * @see Actor
 * @see Port
 * @see Attribute
 * @see Relation
 */
public abstract class PtolemyObj implements Nameable, Exportable, Classable
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
	private PtolemyObj _container;
	
	/**
	 * A container for attributes. Hashtable should grant fast access to attributes.
	 * Key value is the attribute name itself.
	 * */
	private Hashtable<String, Attribute> _attributeList;
	
	/**
	 * Default constructor.
	 * */
	public PtolemyObj()
	{
		_name = "";
		_container = null;
	}
	
	/**
	 * Returns the name of the PtolemyObj. Implements Nameable getName()
	 * @see Nameable
	 * @return The name of the PtolemyObj
	 * */
	public String getName()
	{
		return _name;
	}

	/**
	 * Set the name of the PtolemyObj. This must be unique inside the context of its container.
	 * Implements Nameable setName()
	 * @param name Name of the PtolemyObj
	 * @see Nameable
	 * */
	public void setName(String name)
	{
		/*
		 * TODO: a routine is needed to check if the given name is unique inside this PtolemyObj container.
		 * If name is not unique, an exception should be thrown
		 * */
		_name = name;
	}
	
	/**
	 * Returns the class name of the PtolemyObj. Implements Classable getClassName().
	 * @see Classable
	 * @return The name of the PtolemyObj
	 * */
	public String getClassName()
	{
		return _className;
	}
	
	/**
	 * Set the class name of the PtolemyObj. This must should be a valid class name inside Ptolemy.
	 * Implements Classable setClassName()
	 * @param name Class name of the PtolemyObj
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
	 * Every PtolemyObj is potentially contained in another PtolemyObj. 
	 * Returns its container. If this is a top level PtolemyObj, null value is returned.
	 * @return The PtolemyObj container
	 * */
	public PtolemyObj getContainer()
	{
		return _container;
	}
	
	/**
	 * Every PtolemyObj is potentially contained in another PtolemyObj. 
	 * Set its container. Null value means no container. The scope is protected because
	 * this function is expected to be call indirectly other classes. 
	 * @param container Parent container of this object
	 * */
	protected void setContainer(PtolemyObj container)
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
		//First of all, a check is needed to test if the given attribute is already present.
		Attribute a = _attributeList.get(attribute.getName());
		
		/*
		 * No attribute found means the given attribute can be added.
		 * Otherwise an exception is raised. 
		 * */
		if (a == null)
		{
			//Add the attribute
			_attributeList.put(attribute.getName(), attribute);
			
			/*
			 * If i add an attribute to this specific PtolemyObj, then i must set this
			 * object to be its container
			*/
			attribute.setContainer(this);
		}
		else
			throw new Exception("The attribute you are trying to add already exists.");
	}
	
	/**
	 * Remove an attribute from the existing set of attributes.
	 * @param attribute The attribute you want to remove.
	 * @throws Exception If you remove a non-existing attribute
	 * */
	public void removeAttribute(Attribute attribute) throws Exception
	{
		//First of all, a check is needed to test if the given attribute is already present.
		Attribute a = _attributeList.get(attribute.getName());
		
		/*
		 * No attribute found means the given attribute can't be removed and an exception is raised.
		 * Otherwise go ahead removing. 
		 * */
		if (a == null)
			throw new Exception("Can't remove given attribute. Attribute not found!");
		else
		{
			//Remove the attribute
			a = _attributeList.remove(attribute.getName());
			
			/*
			 * If i remove an attribute to this specific PtolemyObj, then i must unset this
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