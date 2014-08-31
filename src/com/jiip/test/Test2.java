package com.jiip.test;
import com.jiip.kernel.CompositeActor;
import com.jiip.kernel.CompositeEntity;
import com.jiip.kernel.FSM;
import com.jiip.kernel.FSMState;
import com.jiip.kernel.FSMTransition;
import com.jiip.kernel.MoMLImporter;
import com.jiip.kernel.NamedObj;
import com.jiip.kernel.Port;
import com.jiip.kernel.Attribute;
import com.jiip.kernel.Relation;

/**
 * Read a model, visit this model and print FSM.
 * @author simone
 *
 */
public class Test2
{

	public Test2()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	

	
	public static void visitModel(NamedObj obj, String tab)
	{
		if (!(obj instanceof Attribute) && !(obj instanceof Relation))
			System.out.println(tab + obj.getName());
		if (obj instanceof Relation)
		{
			System.out.println(tab + obj.getName() + " > ");
			for(Port p : ((Relation)obj).linkedPortList())
				System.out.println(tab + p.getContainer().getName() + ": " + p.getName() );
		}
		for(NamedObj c : obj.containedList())
			visitModel(c, tab + "  ");
		
	}
	
	public static void main(String[] args) throws Exception
	{
		CompositeActor model = new CompositeActor();
		MoMLImporter importer = new MoMLImporter("/home/simone/Scrivania/RailroadControl.xml");
		model = (CompositeActor) importer.load();
		
		//visitModel(model, "");
		
		//CompositeEntity modalmodel = (CompositeEntity) model.getEntity("ModalModel");
		FSM fsm = (FSM) model.getEntity("Train");
		
		System.out.println("states:");
		for(FSMState s : fsm.stateList())
			System.out.println(s.getName());
		
		System.out.println("");
		
		System.out.println("transitions:");
		for(FSMTransition t : fsm.transitionList())
			System.out.println(t.getIncomingState().getName() + " -> " + t.getOutgoingState().getName() + "("+t.getGuardExpression()+")");
	}

}
