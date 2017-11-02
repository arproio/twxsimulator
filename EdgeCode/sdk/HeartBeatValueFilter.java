package com.remote.vjet.sdk;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <h1>Value object</h1> The HeartBeatValueFilter program is to .....
 * 
 *
 * @author K.Jothi
 * @version 1.0
 * @since 2014-06-12
 */
public class HeartBeatValueFilter {
	

	/**
	 * This method is used to parse the HeartBeat file and returns true if the serialNo is connected
	 * @param xmlDom
	 * @param serailNo
	 * @return boolean
	 */
	public static boolean parseHeartBeatXML(Document xmlDom, String serailNo){
		List<String> valuesList =new ArrayList<String>();
		Element decodedMsg = xmlDom.getDocumentElement();
		NodeList valuesNode = decodedMsg.getElementsByTagName(PrinterConstants.CONNECTED);
		if( null != valuesNode.item(0) )
		{
			Element valuesElem = (Element) valuesNode.item(0);
			if( null != valuesElem )
			{
				// Get the <Value> tags - one or more
				NodeList valueTags = valuesElem.getElementsByTagName(PrinterConstants.SERIAL);
				for( int i = 0; i < valueTags.getLength(); i++ )
				{
					Element val = (Element) valueTags.item(i);
					if(val.getTextContent()!=""){
					 valuesList.add(val.getTextContent());
					}
				}
			}
		}
		if(valuesList.contains(serailNo)){
			return true;
		}
		return false;
	}
}
