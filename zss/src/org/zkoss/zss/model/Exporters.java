/* Exporters.java

	Purpose:
		
	Description:
		
	History:
		Dec 13, 2010 11:06:01 PM, Created by ashish

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

*/

package org.zkoss.zss.model;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.lang.Classes;
import org.zkoss.lang.Library;

/**
 * <p>Provides utility method to get instance of specific {@link Exporter} implementation
 * such as "excel" or "pdf". Other implementations of {@link Exporter} can be registered 
 * via library property in zk.xml
 * </p><p>
 * For example
 * <code>
 * 	<library-property>
 *		<name>org.zkoss.zss.model.Exporter.class</name>
 *		<value>csv=org.xyz.com.CSVExporter</value>
 *	</library-property>
 * </code>
 * <p>
 * Use same type-name to override default Importer such as ExcelExporter. 
 * Declare multiple type-name=class-name pairs in the library property 
 * value separated by comma.
 * </p> 
 * @author ashish
 * @author dennischen
 * @deprecated since 3.0.0, please use class in package {@code org.zkoss.zss.api}
 */
public class Exporters {	
	/**
	 * Returns specific (@link Exporter} implementation as identified by type.
	 * @param type
	 * @return Exporter instance or null if not found
	 */
	public static Exporter getExporter(String type) {
		throw new ModelException("the api was not support anymore, please use api in org.zkoss.zss.api");
	}
}
