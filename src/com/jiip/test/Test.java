package com.jiip.test;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.jiip.kernel.*;

public class Test {

	public Test() 
	{

	}

	/**
	 * @param args command line parameters (none expected)
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		/*
		 * Create a simple model with two numeric costant added and displayed
		 * */
		CompositeActor sum = new CompositeActor("sum", "ptolemy.actor.TypedCompositeActor");
		Director D = new Director("SRDirector", "ptolemy.domains.sr.kernel.SRDirector");
		sum.addDirector(D);
		
		AtomicActor const1 = new AtomicActor("const1", "ptolemy.actor.lib.Const");
		const1.addAttribute(new Attribute("firingCountLimit", "ptolemy.data.expr.Parameter", "1"));
		const1.addAttribute(new Attribute("value", "ptolemy.data.expr.Parameter", "20"));
		Port P1 = new IOPort("const1.output", "ptolemy.actor.TypedIOPort");
		P1.addAttribute(new Attribute("output", ""));
		//const1.addPort(P1);
		
		AtomicActor const2 = new AtomicActor("const2", "ptolemy.actor.lib.Const");
		const2.addAttribute(new Attribute("firingCountLimit", "ptolemy.data.expr.Parameter", "1"));
		const2.addAttribute(new Attribute("value", "ptolemy.data.expr.Parameter", "10"));
		Port P2 = new IOPort("const2.output", "ptolemy.actor.TypedIOPort");
		P2.addAttribute(new Attribute("output", ""));
		//const2.addPort(P2);
		
		AtomicActor adder = new AtomicActor("adder", "ptolemy.actor.lib.AddSubtract");
		Port P3 = new IOPort("adder.plus", "ptolemy.actor.TypedIOPort");
		P3.addAttribute(new Attribute("input", ""));
		Port P4 = new IOPort("adder.minus", "ptolemy.actor.TypedIOPort");
		P4.addAttribute(new Attribute("input", ""));
		Port P5 = new IOPort("adder.output", "ptolemy.actor.TypedIOPort");
		P5.addAttribute(new Attribute("output", ""));
		//adder.addPort(P3); adder.addPort(P4); adder.addPort(P5);
		
		AtomicActor display = new AtomicActor("Display", "ptolemy.actor.lib.gui.Display");
		Port P6 = new IOPort("Display.input", "ptolemy.actor.TypedIOPort");
		P6.addAttribute(new Attribute("input", ""));
	//	display.addPort(P6);
		
		sum.addEntity(const1);
		sum.addEntity(const2);
		sum.addEntity(adder);
		sum.addEntity(display);
		
		Relation R1 = new Relation("rel1", "ptolemy.actor.TypedIORelation");
		Relation R2 = new Relation("rel2", "ptolemy.actor.TypedIORelation");
		Relation R3 = new Relation("rel3", "ptolemy.actor.TypedIORelation");
		
		P1.link(R1); P3.link(R1);
		P2.link(R2); P3.link(R2);
		P5.link(R3); P6.link(R3);
		
		sum.addRelation(R1); sum.addRelation(R2); sum.addRelation(R3);
		
		Document MoML = sum.exportMoML();
		
		/*write xml*/
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(MoML);
		StreamResult result = new StreamResult(new File("/home/simone/Scrivania/model.xml"));
		transformer.transform(source, result);
		System.out.println("File saved!");
		/*
		 * PS: do not work straightforward
		 * 1. must add this header to the created xml:
		 * 
		 * <?xml version="1.0" standalone="no"?>
			<!DOCTYPE entity PUBLIC "-//UC Berkeley//DTD MoML 1//EN"
    			"http://ptolemy.eecs.berkeley.edu/xml/dtd/MoML_1.dtd">
		 * 
		 * 2. Troubles with AtomicActors ports
		 * 	  By default, Ptolemy do not specifies their ports with <port> tag, but
		 *    only in <link> with a dot notation (adder.plus for instance)
		 *    I could add a <port> tag within the atomic actor specification, but i cannot do
		 *    using dot notation (not allowed by Ptolemy)
		 *    I think i can fix..
		 *    (now i have not added ports to their atomic actor in order to not receive
		 *    run time errors in ptolemy, work!)
		 *    
		 * Once you add the right header, Ptolemy shows model (still gives an error regarding 
		 * value attribute of the director, skippable) without graphical stuffs. If you save within Ptolemy and
		 * reload, everythings appears (graphically) ok!
		 * */
	}
}