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
*  Name          : PrinterThingEdit.java
*  Description   : This file is to set / get the value to data transfer object
*
*  Version     Date                 Author      Change Description
*  -----------------------------------------------------------------------------
*  1.0           08/07/2017     k.Jothi       Initial Version
*  
*  -----------------------------------------------------------------------------
*/
package com.remote.vjet.sdk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;
import com.thingworx.metadata.EventDefinition;
import com.thingworx.metadata.FieldDefinition;
import com.thingworx.metadata.collections.FieldDefinitionCollection;
import com.thingworx.types.BaseTypes;
import com.thingworx.types.collections.AspectCollection;
import com.thingworx.types.collections.ValueCollection;

/**
 * The PrinterThingEdit program is to transport the data to
 * Thingworx Platform with property values
 * @author k.jothi
 *
 */
public class PrinterThingEdit extends VirtualThing implements Runnable
{
	private static final long serialVersionUID = 9073867803861371423L;
	private final static Logger LOGGER = LoggerFactory.getLogger(PrinterThingEdit.class);
	private Message msg;
	private DisconnectedMessage dismsg;
	
	public PrinterThingEdit(Message msg, DisconnectedMessage disMsg, ConnectedThingClient client)
	{
		super(msg.getSerialNumber(), msg.getSerialNumber(), client);
		this.msg = msg;
		this.dismsg =disMsg;
		try {
			this.initializeFromAnnotations();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 
	 * Dynamically define the property names for the printer for data  files
	 * @param msg
	 */
	public void defineProperties(Message msg)
	{
		//long startTime =System.currentTimeMillis();
		this.msg =msg;
		String modelID =""+msg.modelID; 
		String id =""+msg.getID();
		AspectCollection aspects = new AspectCollection();
		aspects.setBooleanAspect("isReadOnly", true);
		aspects.setBooleanAspect("isPersistent", true);
		aspects.setStringAspect("dataChangeType", "VALUE");
		aspects.setStringAspect("pushType", "VALUE");
		aspects.setStringAspect("cacheTime", "-1");
		aspects.setBooleanAspect("isFolded", true);
		
		// Define the static properties
		this.defineProperty(PrinterConstants.SERIAL_NUMBER, "Serial Number", BaseTypes.STRING, aspects);
		this.defineProperty(PrinterConstants.DATETIME, "Date Time", BaseTypes.STRING, aspects);
		this.defineProperty(PrinterConstants.CONN_DATETIME, "Date Time", BaseTypes.DATETIME, aspects);
		defineProperty(PrinterConstants.MODEL, "Model", BaseTypes.STRING, aspects);
		defineProperty(PrinterConstants.MODEL_ID, "Model Data ID", BaseTypes.STRING, aspects);
		defineProperty(PrinterConstants.FRIENDLY_NAME, "Friendly Name", BaseTypes.STRING, aspects);
		defineProperty(PrinterConstants.TOKEN, "Token", BaseTypes.STRING, aspects);
		defineProperty(PrinterConstants.STATIC_ID, "Data ID", BaseTypes.STRING, aspects);
		defineProperty(PrinterConstants.PLANT_CREATION_DATE, "Plant Creation Date Time", BaseTypes.STRING, aspects);
		defineProperty(PrinterConstants.PLANT_ID, "Plant ID", BaseTypes.STRING, aspects);
		defineProperty(PrinterConstants.COMPANY_ID, "Company ID", BaseTypes.STRING, aspects);
		defineProperty(PrinterConstants.LAST_IMAGE, "Last Image", BaseTypes.STRING, aspects);
		defineProperty(PrinterConstants.MSG_NAME, "Message Name", BaseTypes.STRING, aspects);
		List<DataValue> dataValues = msg.getDataValues();
		for( DataValue val : dataValues )
		{
			defineProperty("Prop_" + modelID + '_' + id + '_' + val.getDataTagID(), "Data type " + val.getDataType(), BaseTypes.STRING, aspects);
			
		}
		this.definePropEvent();
	}
	/**
	 * Dynamically define the property names for the disconnected file 
	 * @param dismsg
	 */
	public void defineProperties(DisconnectedMessage dismsg)
	{
		//long startTime =System.currentTimeMillis();
		this.dismsg =dismsg;
		AspectCollection aspects = new AspectCollection();
		aspects.setBooleanAspect("isReadOnly", true);
		aspects.setBooleanAspect("isPersistent", true);
		aspects.setStringAspect("dataChangeType", "VALUE");
		aspects.setStringAspect("pushType", "VALUE");
		aspects.setStringAspect("cacheTime", "-1");
		aspects.setBooleanAspect("isFolded", true);
		
		//this.defineProperty(PrinterConstants.SERIAL_NUMBER, "Serial Number", BaseTypes.STRING, aspects);
		this.defineProperty(PrinterConstants.DISCONNECT_DATETIME, "Disconnect Date Time", BaseTypes.DATETIME, aspects);
		defineProperty(PrinterConstants.TOKEN, "Token", BaseTypes.STRING, aspects);
		//defineProperty(PrinterConstants.PLANT_ID, "Plant ID", BaseTypes.STRING, aspects);
		//defineProperty(PrinterConstants.COMPANY_ID, "Company ID", BaseTypes.STRING, aspects);
	}
	/**
	 * Dynamically define the remote property values  for the data file 
	 * 
	 */
	public void definePropEvent(){
		
		FieldDefinitionCollection faultFields = new FieldDefinitionCollection();
		//faultFields.addFieldDefinition(new FieldDefinition(CommonPropertyNames.PROP_MESSAGE,BaseTypes.STRING));
		faultFields.addFieldDefinition(new FieldDefinition("RemoteProps",BaseTypes.STRING));
		defineDataShapeDefinition("VRS_EdgeServiceEventDS", faultFields);
		//this.defineEvent("AddStreamHistoryData", "Stream Event Method", "VRS_EdgeServiceEventDS", null);
		EventDefinition eventDefinition = new EventDefinition();
		eventDefinition.setDataShapeName("VRS_EdgeServiceEventDS");
		eventDefinition.setInvocable(true);
		eventDefinition.setPropertyEvent(false);
		eventDefinition.setName("AddStreamHistory");
		eventDefinition.setCategory("Faults");
		this.defineEvent(eventDefinition);
		
	}
	/** 
	 * Dynamically define the event names for the printer for  Event files
	 * @param eventmsg
	 */
	public void defineEvent(){
		
		FieldDefinitionCollection faultFields = new FieldDefinitionCollection();
		faultFields.addFieldDefinition(new FieldDefinition("EventTimestamp",BaseTypes.DATETIME));
		faultFields.addFieldDefinition(new FieldDefinition("EventType",BaseTypes.STRING));
		faultFields.addFieldDefinition(new FieldDefinition("EventID",BaseTypes.STRING));
		faultFields.addFieldDefinition(new FieldDefinition("Source",BaseTypes.STRING));
		defineDataShapeDefinition("VRS_NotifyEventDS", faultFields);
		EventDefinition eventDefinition = new EventDefinition();
		eventDefinition.setDataShapeName("VRS_NotifyEventDS");
		eventDefinition.setInvocable(true);
		eventDefinition.setPropertyEvent(false);
		eventDefinition.setName("NotifyEvent");
		eventDefinition.setCategory("Faults");
		this.defineEvent(eventDefinition);
		
	}
	// From the VirtualThing class
	// This method will get called when a connect or reconnect happens
	// Need to send the values when this happens
	// This is more important for a solution that does not send its properties on a regular basis
	public void synchronizeState()
	{
		// Be sure to call the base class
		super.synchronizeState();
		// Send the property values to Thingworx when a synchronization is required
		super.syncProperties();
	}

	// The processScanRequest is called by the Gateway for every update cycle
	@Override
	public void processScanRequest() throws Exception
	{
		// Be sure to call the base classes scan request
		// Execute the code for this simulation every scan
		super.processScanRequest();
		if((dismsg!=null && dismsg.getSerialNumber()!=null)){
			this.scanDisconnectedDevice();
		}else{
			this.scanConnectedDevice();
		}
		
	}

	/**Performs the logic for the Gateway on every cycle
	 * @throws Exception
	 */
	public void scanConnectedDevice() 
	{
		StringBuffer sb =new StringBuffer();
		try{
		this.setProperty(PrinterConstants.SERIAL_NUMBER, msg.getSerialNumber());
		//System.out.println("Serail number"+msg.getSerialNumber());
		this.setProperty(PrinterConstants.DATETIME, msg.getDateTime());
		this.setProperty(PrinterConstants.CONN_DATETIME, msg.getDateTime());
		setProperty(PrinterConstants.MODEL, msg.getModel());
		setProperty(PrinterConstants.MODEL_ID, msg.getModelID());
		setProperty(PrinterConstants.FRIENDLY_NAME, msg.getFriendlyName());
		setProperty(PrinterConstants.TOKEN, msg.getToken());
		setProperty(PrinterConstants.STATIC_ID, msg.getID());
		setProperty(PrinterConstants.PLANT_CREATION_DATE, msg.getPlantCreationDateTime());
		setProperty(PrinterConstants.PLANT_ID, msg.getPlantID());
		setProperty(PrinterConstants.COMPANY_ID, msg.getCompanyID());
		if( null != msg.getLastImage() ){ setProperty(PrinterConstants.LAST_IMAGE, msg.getLastImage()); }
		if( null != msg.getMessageName() ){ setProperty(PrinterConstants.MSG_NAME, msg.getMessageName()); }
		if(dismsg!=null && dismsg.getSerialNumber()!=null &&dismsg.getSerialNumber()!=""){
			//System.out.println("Checking disconnect with other here");
			this.setProperty(PrinterConstants.DISCONNECT_DATETIME, dismsg.getDateTime());
			sb.append(PrinterConstants.DISCONNECT_DATETIME+",");
		}
		sb.append(PrinterConstants.SERIAL_NUMBER+",");
		sb.append(PrinterConstants.DATETIME+",");
		sb.append(PrinterConstants.CONN_DATETIME+",");
		sb.append(PrinterConstants.MODEL+",");
		sb.append(PrinterConstants.MODEL_ID+",");
		sb.append(PrinterConstants.FRIENDLY_NAME+",");
		sb.append(PrinterConstants.TOKEN+",");
		sb.append(PrinterConstants.STATIC_ID+",");sb.append(PrinterConstants.PLANT_CREATION_DATE+",");sb.append(PrinterConstants.PLANT_ID+",");
		sb.append(PrinterConstants.COMPANY_ID+",");sb.append(PrinterConstants.LAST_IMAGE+",");sb.append(PrinterConstants.MSG_NAME+",");
		// Update the platform the the changes found...
			List<DataValue> dataValues = msg.getDataValues();
		
			Iterator<DataValue> valuesIter = dataValues.iterator();
			DataValue val =null;
			while(valuesIter.hasNext()){
			val =valuesIter.next();
			setProperty("Prop_" + msg.modelID + '_' + msg.id + '_' + val.getDataTagID(), val.getStringValue());
			sb.append("Prop_" + msg.modelID + '_' + msg.id + '_' + val.getDataTagID()+",");
			//LOGGER.info("Setting property Prop_" + msg.modelID + '_' + msg.id + '_' + val.getDataTagID() + " = " + val.getStringValue());		
			}
		// Update the subscribed properties to send any updates to Thingworx
		// Without calling these methods, the property and event updates will not be sent
			this.loadEventSubscriptions();
			ValueCollection eventInfo = new ValueCollection();
			eventInfo.SetStringValue("RemoteProps",sb.toString());
			//TODO check for data and disconnect alone. ignore events
			super.queueEvent("AddStreamHistory", DateTime.now(), eventInfo);
			//System.out.println("evebt"+getEventSubscriptions().isSubscribed("AddStreamHistory"));
			//System.out.println("event infoe"+eventInfo.getStringValue("RemoteProps"));
			super.updateSubscribedProperties(-1);
			super.updateSubscribedEvents(-1);
		}catch(TimeoutException te){
			LOGGER.warn("Timeout Exception"+te);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.warn("Genric Exception"+e);
		}
		
	}
	/**
	 * Performs the logic for the Gateway on every cycle for the disconnected printer
	 * 
	 */
	public void scanDisconnectedDevice() 
	{
	try{
			//System.out.println("Checking disconnect with other here");
			this.setProperty(PrinterConstants.DISCONNECT_DATETIME, dismsg.getDateTime());
			setProperty(PrinterConstants.TOKEN, dismsg.getToken());
			super.updateSubscribedProperties(-1);
			this.dismsg =null;
		}catch(TimeoutException te){
			LOGGER.warn("Timeout Exception"+te);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.warn("Genric Exception"+e);
		}
		
	}
	
	public void scanConnectedEvent(EventMessage evnObj){
		try{
		this.loadEventSubscriptions();
		ValueCollection eventInfo = new ValueCollection();
		eventInfo.SetStringValue("Source",evnObj.getSerialNo());
		if(evnObj.getEvents()!=null){
			HashMap <String,String> events =evnObj.getEvents();
			eventInfo.SetStringValue("EventType",events.get(PrinterConstants.EVENT_TYPE));
			eventInfo.SetStringValue("EventID",events.get(PrinterConstants.EVENT_ID));
		}
		eventInfo.SetDateTimeValue("EventTimestamp",evnObj.getEventDateTime());
		super.queueEvent("NotifyEvent", DateTime.now(), eventInfo);
		super.updateSubscribedEvents(-1);
		}catch(TimeoutException te){
			LOGGER.warn("Timeout Exception"+te);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.warn("Genric Exception"+e);
		}
	}

	//@Override
	public void run()
	{
		try
		{
			// Delay for a period to verify that the Shutdown service will
			// return
			Thread.sleep(1000);
			// Shutdown the client
			this.getClient().shutdown();
		}
		catch (Exception x)
		{
			// Not much can be done if there is an exception here
			// In the case of production code should at least log the error
		}
	}
	
	@Override
	public String toString()
	{
		return getName() + "(" + getBindingName() + ")";
	}
	
	/*@Override
	public void loadPropertySubscriptions()
	  {
     if (true) {
    	 PropertySubscriptionCollection _subscribedProperties = new PropertySubscriptionCollection();
	    try
      {
	         InfoTable subscriptions = client.invokeService(ThingworxEntityTypes.Things, getBindingName(), "GetPropertySubscriptions", new ValueCollection(), Integer.valueOf(60000));
	        for (ValueCollection row : subscriptions.getRows()) {
	        String name = row.getStringValue("edgeName");
	         Double threshold = (Double)row.getValue("pushThreshold");
	         String type = row.getStringValue("pushType");
	          
	         _subscribedProperties.addSubscription(name, new PropertySubscription(threshold, type));
	      }
	    } catch (Exception e) {
	    	logger.warn("Unable to get property subscriptions.  {}", e.getMessage());
	       }
	    }
	   }*/
}
