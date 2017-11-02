package com.ptc.techsales.edge;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinition;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinitions;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;

@ThingworxPropertyDefinitions(properties = {	
		@ThingworxPropertyDefinition(name="param_vac", description="param_vac", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-220
		@ThingworxPropertyDefinition(name="param_sealvoltage", description="param_sealvoltage", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-70
		@ThingworxPropertyDefinition(name="param_sealcurrent", description="param_sealcurrent", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-70
		@ThingworxPropertyDefinition(name="param_productpresent", description="param_productpresent", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-1
		@ThingworxPropertyDefinition(name="param_productcount", description="param_productcount", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-999999999
		@ThingworxPropertyDefinition(name="param_platennumber", description="param_platennumber", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 1-14
		@ThingworxPropertyDefinition(name="param_lifetimecycle", description="param_lifetimecycle", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-999999999
		@ThingworxPropertyDefinition(name="param_chambernumber", description="param_chambernumber", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 1-8
		@ThingworxPropertyDefinition(name="param_cycletime", description="param_cycletime", baseType="STRING", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // Time
		@ThingworxPropertyDefinition(name="param_date", description="param_date", baseType="STRING", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // Date
		@ThingworxPropertyDefinition(name="param_speedcpm", description="param_speedcpm", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-40
		@ThingworxPropertyDefinition(name="Alarm1", description="Alarm1", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-999999
		@ThingworxPropertyDefinition(name="Alarm2", description="Alarm2", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-999999
		@ThingworxPropertyDefinition(name="Alarm3", description="Alarm3", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-999999
		@ThingworxPropertyDefinition(name="state_all", description="param_speedcpm", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }) // 0-8
	})

public class SimulatedThing extends VirtualThing implements Runnable
{
	private static final long serialVersionUID = 8382712607084394970L;
	private static final Logger logger = LoggerFactory.getLogger(SimulatedThing.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");
	private Random rand = new Random();
	private int param_productcount = 1;
	private int param_productpresent = rand.nextInt(1);
	private int param_platennumber = 1 + rand.nextInt(13);
	private int param_lifetimecycle = 1;
	private int param_chambernumber = 1 + rand.nextInt(7);
	
	public SimulatedThing(String name, String description, ConnectedThingClient client) throws Exception
	{
		super(name, description, client);		
		// Populate the thing shape with the properties, services, and events that are annotated in this code 
		//super.initializeFromAnnotations();
		this.init();
		logger.debug("SimulatedThing '" + name + "' initalized!");
	}

	//JSONObject
	public SimulatedThing(String name, String description, ConnectedThingClient client, JSONObject json) throws Exception
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
		obj = json.get("param_lifetimecycle");
		if (obj != null)
		{
			param_lifetimecycle = ((Long) obj).intValue();
		}
		
		logger.debug("SimulatedThing '" + name + "' initalized!");
	}
	
	private void init() throws Exception 
	{
		initializeFromAnnotations();
		
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
		if(Math.random()>0.8) super.setProperty("param_vac", Math.round(100 + 20 * Math.random()));
		if(Math.random()>0.7) super.setProperty("param_sealvoltage", 20 + rand.nextInt(40));
		if(Math.random()>0.7) super.setProperty("param_sealcurrent", 30 + rand.nextInt(40));
		if(Math.random()>0.2) super.setProperty("param_productpresent", param_productpresent);
		if(Math.random()>0.2) super.setProperty("param_productcount", param_productcount++);
		if(Math.random()>0.9) super.setProperty("param_platennumber", param_platennumber);
		if(Math.random()>0.0) super.setProperty("param_lifetimecycle", param_lifetimecycle++);
		if(Math.random()>0.9) super.setProperty("param_chambernumber", param_chambernumber);
		if(Math.random()>0.0) super.setProperty("param_cycletime", timeFormat.format(now.getTime()));
		if(Math.random()>0.6) super.setProperty("param_date", dateFormat.format(now.getTime()));
		if(Math.random()>0.5) super.setProperty("param_speedcpm", rand.nextInt(40));
		
		if(Math.random()>0.3){
			super.setProperty("Alarm1", 0);
		}else{
			super.setProperty("Alarm1", 1);
		}
		if(Math.random()>0.3){
			super.setProperty("Alarm2", 0);
		}else{
			super.setProperty("Alarm2", 1);
		}
		if(Math.random()>0.3){
			super.setProperty("Alarm3", 0);		
		}else{
			super.setProperty("Alarm3", 1);
		}
		
		if(Math.random()>0.3) super.setProperty("state_all", rand.nextInt(8));
		
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