/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * TODO: javadoc here
 * @author Simone Miraglia
 *
 */
public abstract class NamedObj implements Nameable
{

	/**
	 * 
	 * */
	private String _name;
	
	/**
	 * 
	 * */
	private NamedObj _container;
	
	/**
	 * A container for attributes. Hashtable should grant fast access to attributes.
	 * */
	private Hashtable<String, Attribute> _attributeList;
	
	/**
	 * TODO: javadoc
	 * */
	public NamedObj()
	{
		_name = "";
		_container = null;
	}
	
	public String getName()
	{
		return _name;
	}

	public void setName(String name)
	{
		_name = name;
	}

	public NamedObj getContainer()
	{
		return _container;
	}
	
	/**
	 * TODO: javadoc
	 * 
	 * FIXME: il nome di un attributo è univoco? anche nel senso attore1.attributo?
	 * @throws Exception If you add an existing attribute
	 * */
	public void addAttribute(Attribute attribute) throws Exception
	{
		Attribute a = _attributeList.get(attribute.getName());
		if (a == null)
			_attributeList.put(attribute.getName(), attribute);
		else
			throw new Exception("The attribute you are trying to add already exists.");
	}
	
	
	/**
	 * TODO: javadoc
	 * 
	 * FIXME: solita questione nome attributo
	 * @throws Exception If you remove a non-existing attribute
	 * */
	
	public void removeAttribute(Attribute attribute) throws Exception
	{
		Attribute a = _attributeList.remove(attribute.getName());
		if (a == null)
			throw new Exception("Can't remove given attribute. Attribute not found!");
	}
	
	
	/**
	 * TODO: javadoc
	 * 
	 * FIXME: solita questione nome attributo
	 * @throws Exception If you search a non-existing attribute
	 * */
	public Attribute getAttribute(String name) throws Exception
	{
		Attribute a = _attributeList.get(name);
		if (a == null)
			throw new Exception("Can't get searched attribute. Attribute not found!");
		return a;
	}
	
	/**
	 * TODO: javadoc
	 * */
	public ArrayList<Attribute> getAttributeList()
	{
		return new ArrayList<Attribute> (_attributeList.values());
	}
}
