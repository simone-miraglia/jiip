/**
 * 
 */
package com.jiip.kernel;

import org.w3c.dom.Document;

/**
 * This is an interface for objects that can be exported. 
 * TODO: additional description
 * 
 * @author Simone Miraglia
 * @see PtolemyObj
 */

public interface Exportable
{
	public abstract Document exportMoML();

}
