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
		/*create a simple model*/
		/*
		CompositeActor E0 = new CompositeActor ("E0", "foo");
		CompositeActor E1 = new CompositeActor ("E1", "foo");
		AtomicActor E2 = new AtomicActor("E2", "foo");
		AtomicActor E3 = new AtomicActor("E3", "foo");
		AtomicActor E4 = new AtomicActor("E4", "foo");
		AtomicActor E5 = new AtomicActor("E5", "foo");

		IOPort P1 = new IOPort("P1","port");
		IOPort P2 = new IOPort("P2","port");
		IOPort P3 = new IOPort("P3","port");
		IOPort P4 = new IOPort("P4","port");
		IOPort P5 = new IOPort("P5","port");
		IOPort P6 = new IOPort("P6","port");
		
		Relation R1 = new Relation("R1", "relation");
		Relation R2 = new Relation("R2", "relation");
		Relation R3 = new Relation("R3", "relation");
		Relation R4 = new Relation("R4", "relation");
		
		E0.addEntity(E1); E0.addEntity(E4); E0.addEntity(E5);
		E0.addDirector(new Director("D0","CT"));
		
		E1.addEntity(E2); E1.addEntity(E3);
		E1.addDirector(new Director("D1","DT"));
		
		E2.addPort(P1);
		E3.addPort(P2);
		E1.addPort(P3);	E1.addPort(P4);
		E4.addPort(P5);
		E5.addPort(P6);
		
		P1.link(R1); P3.link(R1);
		P1.link(R2); P2.link(R2); P4.link(R2);
		P4.link(R3); P5.link(R3);
		P4.link(R4); P6.link(R4);

		E0.addRelation(R3); E0.addRelation(R4);
		E1.addRelation(R1); E1.addRelation(R2);
		*/
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