package com.jiip.kernel;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MoMLImporter
{

	private String _pathName;

	
	
	
	
	/**
	 * 
	 * @param pathName
	 */
	public MoMLImporter(String pathName)
	{
		_pathName = pathName;
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private NamedObj createNamedObj(Element node)
	{
		NamedObj object = null;
		String name = node.getAttribute("name");
		String className = node.getAttribute("class");
		
		//entity tag
		if (node.getNodeName().equals("entity"))
		{
			
			//it is a composite actor
			if (className.endsWith("TypedCompositeActor"))
				object = new CompositeActor(name, className);
			
			//it is a modal model
			else if(className.endsWith("ModalModel"))
				object = new CompositeActor(name, className);
			
			//it is a modal controller
			else if(className.endsWith("ModalController") || className.endsWith("FSMActor"))
				object = new FSM(name);
			
			//it is a refinement
			else if(className.endsWith("Refinement"))
				object = new CompositeActor(name, className);
			
			//it is a state of a state machine
			else if(className.endsWith("State"))
				object = new FSMState(name);
			
			//else it is an atomic actor
			else
				object = new AtomicActor(name, className);
			
		}
		
		//property tag
		else if(node.getNodeName().equals("property") && !name.startsWith("_"))
		{			
			//director
			if(className.endsWith("Director"))
				object = new Director(name, className);
			
			//attribute
			else
			{
				String value = node.getAttribute("value");
				object = new Attribute(name, className, value);
			}
		}
			
		//relation tag
		else if (node.getNodeName().equals("relation"))
		{
			if (className.endsWith("Transition"))
				object = new FSMTransition(name);
			else
				object = new Relation(name, className);
		}
		
		//port tag
		else if (node.getNodeName().equals("port"))
			object = new IOPort(name, className);
		
		return object;
	}
	
	/**
	 * 
	 * @param node
	 * @param parent
	 * @return
	 * @throws Exception
	 */
	private NamedObj visitMoML(Element node)
	{
		//Determine which is current MoML tag
		//and create linked ptolemy object
		NamedObj current = createNamedObj(node);
		
		//Null object can be returned whether current sub node
		//is an hidden property (ie, _createdBy) or a link
		if (current == null)
			return null;
		
		//At this point, current obj is created
		//Then we have to explore all its sub named objs
		//and add them to current obj
		
		//Explore properties, entities, ports and relations related to this tag
		NodeList childNodes = node.getChildNodes();
		for(int i = 0; i < childNodes.getLength(); ++i)
		{
			Node n = childNodes.item(i);
			
			//Make sure it is an element node
			if (n.getNodeType() == Node.ELEMENT_NODE)	
			{
				//This return a well-formed sub namedobj, ie an instanced named obj
				//whit all its sub object added (and instanced)
				NamedObj object = visitMoML( (Element) n);
				
				//Null object can be returned whether current sub node
				//is an hidden property (ie, _createdBy) or a link
				if(object != null)
				{
					//Then add the returned object to its parent
					
					try
					{
						//Check if is a simple attribute (but not a director)
						if (object instanceof Attribute && !(object instanceof Director))
								current.addAttribute((Attribute) object);
						else
						{
							//If sub node is not a simple attribute
							//its parent (ie current node) is surely an entity
							Entity container = (Entity)current;
								
							//if obj is an entity, current is a composite entity (so cast is needed)
							if (object instanceof Entity)
							{

								((CompositeEntity) container).addEntity((Entity) object);
							}
							
							//if obj is a port, current could be atomic or composite
							else if (object instanceof Port)
							{

								container.addPort((Port)object);
							}
								
							//if obj is a relation, current is a composite entity (so cast is needed)
							else if (object instanceof Relation)
								((CompositeEntity) container).addRelation((Relation) object);
								
							//if obj is a director, current could be atomic or composite
							else if (object instanceof Director)
								container.addDirector((Director) object);
						}
					} 
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		return current;
		
	}
	
	private void createLink(Element node, CompositeEntity container)
	{
		try
		{
			String portName = node.getAttribute("port");
			String relationName = node.getAttribute("relation");			
			
			//get actual relation from container
			Relation r = container.getRelation(relationName);
			Port p;
			
			//two cases: port name = "actor.port" or port name = "port"
			//first case, the port is from on of the contained actors ports
			//second case, the port is from the container set of ports
			if(portName.contains("."))
			{
				//get actor and port
				String actorName = portName.substring(0, portName.indexOf("."));
				portName = portName.substring(portName.indexOf(".") + 1);
	
				//find actor
				Entity actor = container.getEntity(actorName);
				
				//check if actor has the port because when you add atomic actor in Ptolemy
				//ports are not added in MoML file 
				
				if (!actor.hasPort(portName))
				{
					//if no port has been found (ie, actor is an atomic actor)
					//add a new port (in order to link)
					p = new IOPort(portName, "ptolemy.actor.TypedIOPort"); //TODO CHECK if right
					actor.addPort(p);
				}
				else
					p = actor.getPort(portName);
				
			}
			else //simply get port from container
				p = container.getPort(portName);
			
			//Finally, link port and relation
			p.link(r);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param node
	 * @param object
	 */
	private void setLinks(Element node, Entity object)
	{
		try
		{
			//Iterate
			NodeList childNodes = node.getChildNodes();
			for(int i = 0; i < childNodes.getLength(); ++i)
			{
				Node n = childNodes.item(i);
				
				//Make sure it is an element node and it is a proper tag (ie, name is not null)
				if (n.getNodeType() == Node.ELEMENT_NODE)
				{
					//links are only in <entity> tag
					if(n.getNodeName().equals("entity"))
					{
						String entityName = ((Element)n).getAttribute("name");
						Entity e = ((CompositeEntity)object).getEntity(entityName);
						if (!e.isAtomic())
							setLinks(((Element)n), e);
					}
					else if (n.getNodeName().equals("link"))
						createLink(((Element)n), (CompositeEntity) object);
				}
			}		
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	public NamedObj load()
	{		
		File fXmlFile = new File(_pathName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc;
		try
		{
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			Element root = doc.getDocumentElement();
			NamedObj model = visitMoML(root);
			setLinks(root, (Entity) model);
			return model;
		}
		catch (SAXException | IOException | ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}