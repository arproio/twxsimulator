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
*  Name          : DisconnectedValueFilter.java
*  Description   : This file is to set / get the value to data transfer object
*
*  Version     Date                 Author      Change Description
*  -----------------------------------------------------------------------------
*  1.0           08/07/2017     k.Jothi       Initial Version
*  
*  -----------------------------------------------------------------------------
*/
package com.remote.vjet.sdk;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * The DisconnectedValueFilter program is to parse the disconnected xml file and helps
 * to transport the data in message
 * @author k.jothi
 *
 */
public class DisconnectedValueFilter extends DefaultHandler {
			
	 private final static Logger LOGGER = LoggerFactory.getLogger(DisconnectedValueFilter.class);
	 String xmlValue;
	 DisconnectedMessage disconObj= null;
	// List<String> serialNos=new ArrayList<>();
	// String serailNo;
	  @Override
	    public void startElement(String uri, String localName, String qName,
	            Attributes attributes) throws SAXException {
	    	//System.out.println("qname"+qName);
	    	 if (qName.equalsIgnoreCase(PrinterConstants.SERIAL)) {
	    		 disconObj = new DisconnectedMessage();
	         }
	    	 /*if (qName.equalsIgnoreCase("Serials")) {
	    		 serailNo="";
	         }*/
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
	    		
	    	  if (qName.equalsIgnoreCase(PrinterConstants.PLANT_CREATION_DATE)) {
	    		  disconObj.setDateTime(PrinterConstants.dateToTimeZone(PrinterConstants.TYPE_FORMAT2,xmlValue));
	          }
	    	  if (qName.equalsIgnoreCase(PrinterConstants.SITE_ID)) {
	    		  disconObj.setPlantID(xmlValue);
	          }
	    	  if (qName.equalsIgnoreCase(PrinterConstants.COMPANY_ID)) {
	    		  disconObj.setCompanyID(xmlValue);
	          }
	    	  if (qName.equalsIgnoreCase(PrinterConstants.SERIAL)) {
	    		  disconObj.setSerialNumber(xmlValue);
	          }
	    	  if (qName.equalsIgnoreCase(PrinterConstants.TOKEN)) {
	    		  disconObj.setToken(xmlValue);
	          }
	    	 /* if (qName.equalsIgnoreCase("Serials")) {
	    		  disconObj.setSerialNumbers(serialNos);
	          }*/
	    	  
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
