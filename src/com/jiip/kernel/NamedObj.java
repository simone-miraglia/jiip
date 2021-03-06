/**
 * 
 */
package com.jiip.kernel;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <p>This class implements a NamedObj. It is an abstract class which has at least four main concrete objects:
 * Entity, Port, Attribute and Relation. A NamedObj has a containment relationship with another NamedObj.</p>
 * 
 * <p>It implements Nameable, Classable and Exportable interfaces.</p>
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
	 * Unique means two object - of the same type (Entity, Port, etc.) - with the same name
	 * are not allowed to co-exists in the same container.
	 * */
	private String _name;
	
	/**
	 * Valid class name of Ptolemy.
	 * */
	private String _className;
	
	/**
	 * Specifies the container.
	 * */
	private NamedObj _container;
	
	/**
	 * A list of contained NamedObjs. Search is by name.
	 */
	private ArrayList<NamedObj> _containedObjList;
	
	/**
	 * A container for attributes. Search is by name.
	 * */
	/*
	 * It is declared as <String, ? extends NamedObj> to allow
	 * usage of add/remove/get methods regardless of the concrete
	 * type (eg, Entity, Attribute, and so on).
	 * */
	private ArrayList<? extends NamedObj> _attributeList;
	
	
	/* ************************************************
	 * **************** Methods ***********************
	 * ************************************************
	 * */
	
	/**
	 * Check name existence within the contained NamedObj.
	 * Useful to grant uniqueness.
	 * @param NamedObj of which check the uniqueness
	 * @param container NamedObj in which check uniqueness
	 * @return true if name already exists, false otherwise
	 * */
	protected boolean checkNameExistence(NamedObj obj, NamedObj container)
	{
		/*
		 * To check name uniqueness, lookup in the list
		 * of contained NamedObjs.
		 * Return false (ie, a duplicate exists) iff two objects are of the same class
		 * and their name are equals.
		 * */
		
		for(NamedObj o : container._containedObjList)
		{
			if(o.getClass().equals(obj.getClass()))
				if (o.getName().equals(obj.getName()))
					return true;
		}
		return false;
	}
	
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
		 * */
		
		for(NamedObj o : container._containedObjList)
			if (o.getName().equals(name))
					return true;
		return false;
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
	protected void add(NamedObj obj, ArrayList<NamedObj> list) throws Exception
	{
		/*
		 * First check whether the given NamedObj is already added somewhere
		 * (ie, _container is not null) and raise an exception in that case
		 */		
		if (obj.getContainer() != null)
			throw new Exception (obj.getName() + " already contained in " + obj.getContainer().getName() + 
					". Cannot be added in " + this.getName() + ".");
		
		/*
		 * Then check uniqueness of the name. If it is, it can be added,
		 * otherwise raise an Exception.
		 * */
		if (!checkNameExistence(obj, this))
		{
			/*
			 * If it is unique, then add the given obj.
			 */
			list.add(obj);
			
			/*
			 * Make the reference between this object (container) and 
			 * the added NamedObj (contained)
			 * */
			_containedObjList.add(obj);
			obj.setContainer(this);
		}
		else
			throw new Exception("DuplicateNameException: the item you are trying to add already exists.");
	}
	
	/**
	 * Remove a NamedObj obj from a container of NamedObj.
	 * @param obj NamedObj you want to remove
	 * @param list the container
	 * @return Returns true if this list contained the specified element 
	 * @throws Exception if there is no element with such key inside the container
	 * */
	protected boolean remove(NamedObj obj, ArrayList<NamedObj> list) throws Exception
	{		
		/*
		 * First check if obj is actually contained, if it is not, raise an exception
		 * */
		if (list.contains(obj))
		{
			/*
			 * If it is contained, then remove
			 * */
			boolean t = list.remove(obj);
			
			/* and delete the reference between this NamedObj (container)
			 * and obj (contained)
			 * (If it is contained ==> it also contained in _containedObjList,
			 * so next remove is ok)
			 * */
			_containedObjList.remove(obj); 
			obj.setContainer(null);
			
			/*
			 * Eventually returns true
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
	protected NamedObj get(String key, ArrayList<NamedObj> list) throws Exception
	{
		/*
		 * Just lookup in the list.
		 * Raise exception if no such element has been found.
		 * */
		for(NamedObj o : list)
			if (o.getName().equals(key))
				return o;
		throw new Exception("Unable to get NamedObj. It does not exist.");
	}
	
	/**
	 * Check if a NamedObj is contained (without removing it)
	 * @param key name of the NamedObj you want to retrieve
	 * @param list the container
	 * @return the searched NamedObj
	 * @throws Exception if there is no element with such key inside the container
	 * */
	protected boolean contains(String key, ArrayList<NamedObj>list)
	{
		for(NamedObj o: list)
			if(o.getName().equals(key))
				return true;
		return false;
	}
	
	/**
	 * Default constructor.
	 * */
	public NamedObj()
	{
		_name = "";
		_container = null;
		_className = "";
		_attributeList = new ArrayList<Attribute>();
		_containedObjList = new ArrayList<NamedObj>();
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
		_attributeList = new ArrayList<Attribute>();
		_containedObjList = new ArrayList<NamedObj>();
	}
	
	/**
	 * Returns the name of the NamedObj. Implements Nameable getName().
	 * @see Nameable
	 * @return name of the NamedObj
	 * */
	public String getName()
	{
		return _name;
	}

	/**
	 * Set the name of the NamedObj. This must be unique inside the context of its container.
	 * Implements Nameable setName().
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
	 * Set the class name of the NamedObj. This should be a valid class name inside Ptolemy.
	 * Implements Classable setClassName().
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
	protected void setContainer(NamedObj container)
	{
		/*
		 * Protected because container is set/unset during
		 * add/remove operations, so users cannot use this
		 * function in order to preserve "integrity"
		 * */
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
		add(a, (ArrayList<NamedObj>) _attributeList);
	}
	
	/**
	 * Remove an attribute from the existing set of attributes.
	 * @param obj The attribute you want to remove.
	 * @return Returns true if this list contained the specified element
	 * @throws Exception If you remove a non-existing attribute
	 * */
	@SuppressWarnings("unchecked")
	public boolean removeAttribute(Attribute obj) throws Exception
	{
		return  remove (obj, (ArrayList<NamedObj>) _attributeList);
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
		return (Attribute) get(name, (ArrayList<NamedObj>) _attributeList);
	}
	
	/**
	 * Check if current object has a given attribute.
	 * @param name name of the attribute
	 * @return true if the attribute is present
	 */
	@SuppressWarnings("unchecked")
	public boolean hasAttribute(String name)
	{
		return contains(name, (ArrayList<NamedObj>) _attributeList);
	}
	
	
	/**
	 * Returns the set of attributes as an ArrayList
	 * @return The set of attributes
	 * */
	@SuppressWarnings("unchecked")
	public ArrayList<Attribute> attributeList()
	{
		return (ArrayList<Attribute>) _attributeList;
	}
	
	/**
	 * Returns the set of contained NamedObjs as an ArrayList
	 * @return The set of contained NamedObj
	 * */
	public ArrayList<NamedObj> containedList()
	{
		return _containedObjList;
	}
	
	
	/**
	 * Export a given NamedObj (and all its contained objects) to xml format with the MoML syntax.
	 * @param obj NamedObj to export
	 * @param xml xml document to which append the exported NamedObj
	 * @return an xml element with MoML syntax
	 * **/
	private Element exportObj(NamedObj obj, Document xml)
	{
		//TODO improvements...
		
		/*
		 * create an xml tag, depending of the type of
		 * the given NamedObj
		 * */
		Element root;
		if(obj instanceof Attribute)
		{
			root = xml.createElement("property");
			/*
			 * if obj is an Attribute, must add
			 * also attribute value iff obj is not
			 * a director (directors have no value)
			 * FIXME director still have value!
			 * */
			if (obj instanceof Director == false)
			{
				Attribute a = (Attribute)obj;
				root.setAttribute("value", a.getValue());
			}
		}
		else if(obj instanceof Entity)
			root = xml.createElement("entity");
		else if (obj instanceof Port)
			root = xml.createElement("port");
		else /*obj instanceof Relation*/
			root = xml.createElement("relation");
		
		root.setAttribute("class", obj.getClassName());
		root.setAttribute("name", obj.getName());
		
		/*
		 * foreach contained obj, call recursively
		 * this function, adding the result
		 * */
		for(Iterator<NamedObj> child = obj.containedList().iterator(); child.hasNext();)
			root.appendChild(exportObj(child.next(), xml));
		/*
		 * if obj is a CompositeEntity, then must add a <link>
		 * for each port-relation link
		 * */
		if (obj instanceof CompositeEntity)
		{
			CompositeEntity c = (CompositeEntity)obj;
			for(Iterator<Relation> k = c.relationList().iterator(); k.hasNext();)
			{
				/*
				 * get a relation
				 * */
				Relation r = k.next(); 
				for(Iterator<Port> j = r.linkedPortList().iterator(); j.hasNext();)
				{
					/*
					 * get a linked port from that relation
					 * */
					Port p = j.next();
					/*
					 * create a link tag and add to root
					 * */
					Element link = xml.createElement("link");
					link.setAttribute("relation", r.getName());
					link.setAttribute("port", p.getName());
					root.appendChild(link);
				}
			}
		}
		
		/*
		 * finally return tag to caller
		 * */
		return root;
	}
	
	/**
	 * Export the model to a MoML file. 
	 * Create a MoML document which contains the current obj.
	 * Should be used when current obj is at least a CompositeEntity.
	 */
	public Document exportMoML()
	{
		try 
		{
			/*
			 * Creates a new XML document (MoML)
			 * */
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document MoML = docBuilder.newDocument();
			
			/*
			 * launch exportObj of this NamedObj and add the result to the MoML
			 * */
			MoML.appendChild(exportObj(this, MoML));
			return MoML;
			
		} 
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}