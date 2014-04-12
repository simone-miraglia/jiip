package com.jiip.test;
import com.jiip.kernel.*;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args command line parameters (none expected)
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		Attribute top = new Attribute("Top", "class.main");
		top.setValue("param = 10;");
		
		Attribute a1 = new Attribute ("A1", "class.x");
		top.addAttribute(a1);
		
		Attribute a2 = new Attribute ("A2", "class.y");
		top.addAttribute(a2);
		
		Attribute b = new Attribute ("A1", "class.b");
		a1.addAttribute(b);
		//a1.addAttribute(a2);
		
	}

}
