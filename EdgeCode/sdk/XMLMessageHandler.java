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
*  Name          : XMLMessageHandler.java
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.thingworx.types.primitives.ImagePrimitive;
/**
 * The XMLMessageHandler program is to parse the data and event xml file and helps
 * to transport the data in message
 * @author k.jothi
 *
 */
public class XMLMessageHandler extends DefaultHandler {
	
	public String xmlValue;
	public Message msgTmp =null;
	public DataValue dv = null;
	public  List<DataValue> valuesList=new ArrayList<>();
	public SimpleDateFormat dtFormat = new SimpleDateFormat(PrinterConstants.TYPE_FORMAT2);
	 private final static Logger LOGGER = LoggerFactory.getLogger(DisconnectedValueFilter.class);
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
    	 if (qName.equalsIgnoreCase(PrinterConstants.SERIAL)) {
    		 msgTmp = new Message();
         }
    	 if (qName.equalsIgnoreCase(PrinterConstants.VALUE)) {
        	dv = new DataValue();
         }
    }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
    	xmlValue = new String(ch, start, length);
    }
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
    	try{
    	  dtFormat.setTimeZone(TimeZone.getTimeZone(PrinterConstants.UTC));
    	  if (qName.equalsIgnoreCase(PrinterConstants.SERIAL)) {
    		  msgTmp.setSerialNumber(xmlValue);
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.DATETIME)) {
    		  msgTmp.setDateTime(dtFormat.parse(xmlValue));
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.MODEL)) {
    		  msgTmp.setModel(xmlValue);
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.MODEL_ID)) {
    		  msgTmp.setModelID(Integer.parseInt(xmlValue));
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.FRIENDLY_NAME)) {
    		  msgTmp.setFriendlyName(xmlValue);
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.TOKEN)) {
    		  msgTmp.setToken(xmlValue);
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.ID)) {
    		  msgTmp.setId(Integer.parseInt(xmlValue));
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.TAG)) {
        	  dv.setDataTagID(xmlValue);
           }
    	  if (qName.equalsIgnoreCase(PrinterConstants.ACTUAL)) {
    		  //To do Check condition with data type and convert 
        	  dv.setStringValue(xmlValue);
           }
          if (qName.equalsIgnoreCase(PrinterConstants.VALUE)) {
        	  valuesList.add(dv);
           }
          if (qName.equalsIgnoreCase(PrinterConstants.VALUES)) {
        	  msgTmp.setValuesList(valuesList);
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.LAST_IMAGE)) {
    		  msgTmp.setLastImage(new ImagePrimitive(xmlValue));
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.MSG_NAME)) {
    		  msgTmp.setMessageName(xmlValue);
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.PLANT_CREATION_DATE)) {
    		  msgTmp.setPlantCreationDateTime(dtFormat.parse(xmlValue));
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.PLANT_ID)) {
    		  msgTmp.setPlantID(xmlValue);
          }
    	  if (qName.equalsIgnoreCase(PrinterConstants.COMPANY_ID)) {
    		  msgTmp.setCompanyID(xmlValue);
          }
    	}catch(ParseException pe){
    		LOGGER.warn("PARSE FAILS"+pe.getMessage());
    		
    	}
         
    	/*
    	 * (non-Javadoc)
    	 * 
    	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
    	 */
    }
    @Override
    public void endDocument() throws SAXException {
    	super.endDocument();
    }
    
}