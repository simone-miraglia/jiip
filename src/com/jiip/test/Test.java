package com.jiip.test;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.jiip.kernel.*;

public class Test {

	public Test() 
	{

	}
	
	public static void visit (NamedObj E)
	{
		Queue<NamedObj>Q = new LinkedList<NamedObj>();
		Q.add(E);
		while (!Q.isEmpty())
		{
			NamedObj t = Q.remove();
			System.out.println(t.getName() + " - " + t.getClassName());
				for(Iterator<NamedObj> k = t.containedList().iterator(); k.hasNext();)
					Q.add(k.next());
		}
	}

	/**
	 * @param args command line parameters (none expected)
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
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
		
		visit(E0);
	}
}