/**
*   --------------------------------------------------------------------------------
*   This source is part of the Videojet Remote Service , Videojet                                 
*   Copyright (c) 2017 Videojet          
*   All rights reserved.  No part of this source may be reproduced, stored in a       
*   retrieval system, adopted or transmitted in any form or by any means,             
*   electronic, mechanical, photographic, graphic, optic recording or otherwise,      
*   translated in any language or computer language, without the prior written        
*   permission of Videojet Technologies inc.                                                     
*  -------------------------------------------------------------------------------
*
*  ------------------------------------------------------------------------------
*  Name          : EventValueFilter.java
*  Description   : This file is to set / get the value to data transfer object
*
*  Version     Date                 Author      Change Description
*  -----------------------------------------------------------------------------
*  1.0           09/09/2017     k.Jothi       Initial Version
*  
*  -----------------------------------------------------------------------------
*/
package com.remote.vjet.sdk;

import java.text.ParseException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The EventValueFilter program is to parse the Event xml file and helps
 * to transport the events
 * @author k.jothi
 *
 */
public class EventValueFilter extends DefaultHandler {
	 private final static Logger LOGGER = LoggerFactory.getLogger(DisconnectedValueFilter.class);
	 String xmlValue;
	 EventMessage eventObj= null;
	 private HashMap<String,String> events;
	 private String key ="";
	 private String value ="";
	 @Override
	    public void startElement(String uri, String localName, String qName,
	            Attributes attributes) throws SAXException {
	    	
	    	 if (qName.equalsIgnoreCase(PrinterConstants.SERIAL)) {
	    		 eventObj = new EventMessage();
	    		 events =new HashMap<>();
	         } 
	    	 if(key!=null && key!="" && value!=null && value!=""){
	    		 events.put(key, value);
	    		 value="";
	    		 eventObj.setEvents(events);
	    	 }
	    	
	    }

	    @Override
	    public void characters(char[] ch, int start, int length)
	            throws SAXException {
	    	xmlValue = new String(ch, start, length);
	    }

	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException
	             {
	    	try{
			    	if (qName.equalsIgnoreCase(PrinterConstants.TAG)) {
			    		key =xmlValue;
			    		}
		    		  if (qName.equalsIgnoreCase(PrinterConstants.ACTUAL)) {
		        		  value =xmlValue;
		               }
		    	  if (qName.equalsIgnoreCase(PrinterConstants.DATETIME)) {
		    		  eventObj.setEventDateTime(PrinterConstants.dateToTimeZone(PrinterConstants.TYPE_FORMAT2,xmlValue));
		          }
		    	  if (qName.equalsIgnoreCase(PrinterConstants.SERIAL)) {
		    		  eventObj.setSerialNo(xmlValue);
		          }
		    	 
	    	}
	    	catch(ParseException pe){
	    		LOGGER.warn("PARSE FAILS"+pe.getMessage());
	    		//pe.printStackTrace();
	    	}
	         

	    }
	    @Override
	    public void endDocument() throws SAXException {
	    }
	   
}
