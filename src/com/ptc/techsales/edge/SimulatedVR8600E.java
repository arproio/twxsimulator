package com.ptc.techsales.edge;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;
import com.thingworx.metadata.PropertyDefinition;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinition;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinitions;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.types.primitives.IPrimitiveType;
import com.thingworx.types.primitives.IntegerPrimitive;

@ThingworxPropertyDefinitions(properties = {

        // This property is setup for collecting time series data. Each value
        // that is collected will be pushed to the platfrom from within the
        // processScanRequest() method.
        @ThingworxPropertyDefinition(name = "param_speedcpm", description = "The device speed",
                baseType = "NUMBER",
                aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                        "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                        "isFolded:FALSE", "defaultValue:0" }),	//0-40
        @ThingworxPropertyDefinition(name = "param_productcount", description = "incremental product count",
        	baseType = "INTEGER",
        	aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
        			"isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
        			"isFolded:FALSE", "defaultValue:0" }),
        @ThingworxPropertyDefinition(name = "param_lifetimecycle", description = "incremental lifetime cycle",
	    	baseType = "INTEGER",
	    	aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
	    			"isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
	    			"isFolded:FALSE", "defaultValue:0" }),
        
        @ThingworxPropertyDefinition(name = "state_all", description = "selective state_all",
	    	baseType = "INTEGER",
	    	aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
	    			"isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
	    			"isFolded:FALSE", "defaultValue:0" }),	//1,2,3,4,5,8,32


        
		@ThingworxPropertyDefinition(name="alarm_1", description="Alarm1", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-65535
		@ThingworxPropertyDefinition(name="alarm_2", description="Alarm2", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-65535
		@ThingworxPropertyDefinition(name="alarm_3", description="Alarm3", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" })	// 0-65535
})

/**
 * A very basic VirtualThing with two properties and a service implementation. It also implements
 * processScanRequest to handle periodic actions.
 */
public class SimulatedVR8600E extends VirtualThing implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(SimulatedVR8600E.class);
    private Random rand = new Random();
    private int param_lifetimecycle = 1;
    private double param_speedcpm = 0.0;
    private int param_productcount = 1;
    private int state_all = 1;
    private int[] states = {1,2,3,4,5,8,32};
    
    /**
     * A custom constructor. We implement this so we can call initializeFromAnnotations, which
     * processes all of the VirtualThing's annotations and applies them to the object.
     * 
     * @param name The name of the thing.
     * @param description A description of the thing.
     * @param client The client that this thing is associated with.
     */
    public SimulatedVR8600E(String name, String description, ConnectedThingClient client)
            throws Exception {

        super(name, description, client);
        this.initializeFromAnnotations();
        logger.debug("SimulatedThing '" + name + "' initalized!");
        
    }
    
    public SimulatedVR8600E(String name, String description, ConnectedThingClient client,JSONObject json)
            throws Exception {

        super(name, description, client);
        this.initializeFromAnnotations();
        
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

    /**
     * This method provides a common interface amongst VirtualThings for processing periodic
     * requests. It is an opportunity to access data sources, update property values, push new
     * values to the server, and take other actions.
     * @throws Exception 
     */
    @Override
    public void processScanRequest() throws Exception {

    	// Be sure to call the base classes scan request
    			super.processScanRequest();
    			// Execute the code for this simulation every scan
    			this.scanDevice();
    }

 // Performs the logic for the Gateway on every cycle
 	public void scanDevice() throws Exception
 	{
 		if(Math.random() <= 0.95) super.setProperty("param_productcount", param_productcount++);
 		if(Math.random() <= 0.95) super.setProperty("param_lifetimecycle", param_lifetimecycle++);
 		if(Math.random() <= 0.10) super.setProperty("param_speedcpm", ThreadLocalRandom.current().nextDouble(0,40));
 		if(Math.random() <= 0.10) super.setProperty("state_all", states[rand.nextInt(states.length)]);
 		
 		
 		if(Math.random() <= 0.05 /*0.05*/){
 			super.setProperty("alarm_1", ThreadLocalRandom.current().nextInt(65535));
 		}
 		if(Math.random() <= 0.05 /*0.05*/){
 			super.setProperty("alarm_2", ThreadLocalRandom.current().nextInt(65535));
 		}
 		if(Math.random() <= 0.05 /*0.05*/){
 			super.setProperty("alarm_3", ThreadLocalRandom.current().nextInt(65535));
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
