package com.jiip.test;
import com.jiip.kernel.*;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		Attribute a = new Attribute("foo", "class.foo");
		a.setValue("132.5");
		System.out.println(a.getName() + ", " + a.getClassName() + ", " + a.getValue());
		
		Attribute b = new Attribute ("x", "class.x");
		System.out.println(b.getName());
		a.addAttribute(b);
	}

}
