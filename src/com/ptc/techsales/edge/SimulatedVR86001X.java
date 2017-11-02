package com.ptc.techsales.edge;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinition;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinitions;

@ThingworxPropertyDefinitions(properties = {
		@ThingworxPropertyDefinition(name="param_chambernumber", description="param_chambernumber", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 1-1
		@ThingworxPropertyDefinition(name="param_lifetimecycles", description="param_lifetimecycles", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-999999999
		@ThingworxPropertyDefinition(name="param_productcount", description="param_productcount", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-999999999
		@ThingworxPropertyDefinition(name="param_speedCPM", description="param_speedCPM", baseType="NUMBER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-60
		@ThingworxPropertyDefinition(name="state_all", description="param_speedcpm", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 1,2,3,4,5,8,32
		@ThingworxPropertyDefinition(name="alarm_01", description="alarm_01", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_02", description="alarm_02", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_03", description="alarm_03", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_04", description="alarm_04", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_05", description="alarm_05", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_06", description="alarm_06", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_07", description="alarm_07", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_08", description="alarm_08", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_09", description="alarm_09", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_10", description="alarm_10", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_11", description="alarm_11", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_12", description="alarm_12", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_13", description="alarm_13", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_14", description="alarm_14", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_15", description="alarm_15", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_16", description="alarm_16", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_17", description="alarm_17", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_18", description="alarm_18", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_19", description="alarm_19", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_20", description="alarm_20", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_21", description="alarm_21", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_22", description="alarm_22", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_23", description="alarm_23", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_24", description="alarm_24", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_25", description="alarm_25", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_26", description="alarm_26", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_27", description="alarm_27", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_28", description="alarm_28", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_29", description="alarm_29", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="alarm_30", description="alarm_30", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
	})

public class SimulatedVR86001X extends VirtualThing implements Runnable
{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SimulatedVR86001X.class);
	private Random rand = new Random();
	private int param_chambernumber = 1;
	private int param_lifetimecycles = 1;
	private int param_productcount = 1;
	private int[] states = {1,2,3,4,5,8,32};
	
	private String[] booleanProperties={};
	private ArrayList<String> booleanArray = new ArrayList<String>();
	private Dictionary<String, Boolean> booleanValue = new Hashtable<String, Boolean>();
	
	public SimulatedVR86001X(String name, String description, ConnectedThingClient client) throws Exception
	{
		super(name, description, client);		
		// Populate the thing shape with the properties, services, and events that are annotated in this code 
		//super.initializeFromAnnotations();
		this.init();
		logger.debug("SimulatedVR86001X '" + name + "' initalized!");
	}
	
	public SimulatedVR86001X(String name, String description, ConnectedThingClient client,JSONObject json) throws Exception
	{
		super(name, description, client);		
		// Populate the thing shape with the properties, services, and events that are annotated in this code 
		//super.initializeFromAnnotations();
		this.init();
		
		Object obj = json.get("param_productcount");
		if (obj != null)
		{
			param_productcount = ((Long) obj).intValue();
		}	
		obj = json.get("param_lifetimecycles");
		if (obj != null)
		{
			param_lifetimecycles = ((Long) obj).intValue();
		}
		
		obj = json.get("param_chambernumber");
		if (obj != null)
		{
			param_chambernumber = ((Long) obj).intValue();
		}
		
		logger.debug("SimulatedVR86001X '" + name + "' initalized!");
	}

	private void init() throws Exception 
	{
		initializeFromAnnotations();
		buildUpAlarmArray();
	}
	
	private void buildUpAlarmArray(){
		for (int i = 1; i <= 9; i ++) {
			booleanArray.add("alarm_0"+i);
		}
		
		for (int i = 10; i <= 30; i ++) {
			booleanArray.add("alarm_"+i);
		}
		
		for(int i=0;i<booleanProperties.length;i++){
			booleanArray.add(booleanProperties[i]);
		}
		for(String property:booleanArray){
			booleanValue.put(property, Boolean.FALSE);
		}
	}
	
	private void setBooleanTypeProperty(String propertyName){
		//if it's 0, then only 5% chance to be 1.
		//if it's 1, then 50% chance to be 0.
		try {
			if(! (Boolean)booleanValue.get(propertyName).booleanValue()){
				if(Math.random()<=0.05){
					super.setProperty(propertyName, 1);
					booleanValue.put(propertyName, Boolean.TRUE);
					
				}
			}else{
				if(Math.random()>0.5){
					super.setProperty(propertyName, 0);
					booleanValue.put(propertyName, Boolean.FALSE);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.info("Can't compare !"+propertyName);
			e.printStackTrace();
		}
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
		super.processScanRequest();
		// Execute the code for this simulation every scan
		this.scanDevice();
	}
	
	// Performs the logic for the Gateway on every cycle
	public void scanDevice() throws Exception
	{
		Calendar now = Calendar.getInstance();
	
		//if(Math.random()>0.9) super.setProperty("param_platennumber", param_platennumber);
		//if(Math.random()>0.8) super.setProperty("param_vac", Math.round(100 + 20 * Math.random()));
		//if(Math.random()>0.7) super.setProperty("param_sealvoltage", 20 + rand.nextInt(40));
		//if(Math.random()>0.7) super.setProperty("param_sealcurrent", 30 + rand.nextInt(40));
		//if(Math.random()>0.2) super.setProperty("param_productpresent", param_productpresent);
		
		super.setProperty("param_chambernumber", param_chambernumber); // There is only one chamber
		
		if(Math.random() <= 0.95) super.setProperty("param_lifetimecycles", param_lifetimecycles++);
		if(Math.random() <= 0.95) super.setProperty("param_productcount", param_productcount++);
		if(Math.random() <= 0.1 /*0.1*/) super.setProperty("param_speedCPM", ThreadLocalRandom.current().nextDouble(0,60));
		
		if(Math.random() <= 0.1 /*0.10*/) super.setProperty("state_all", states[rand.nextInt(states.length)]);
		
		for(String booleanProperty : booleanArray){
			setBooleanTypeProperty(booleanProperty);
		}
		
		logger.debug("Updating properties for thing " + getName());
		// Update the subscribed properties to send any updates to Thingworx
		// Without calling these methods, the property and event updates will not be sent
		super.updateSubscribedProperties(1000);
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

}