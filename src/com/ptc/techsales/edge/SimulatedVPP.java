package com.ptc.techsales.edge;

import java.util.Random;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ThreadLocalRandom;

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
		@ThingworxPropertyDefinition(name = "CenterSealTempPV", description = "The device speed",
                baseType = "NUMBER",
                aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                        "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                        "isFolded:FALSE", "defaultValue:0" }),	//0-40
        
		@ThingworxPropertyDefinition(name = "Speed", description = "The device speed",
        baseType = "NUMBER",
        aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "isFolded:FALSE", "defaultValue:0" }),	//0-40

		@ThingworxPropertyDefinition(name = "PumpInvSpeed", description = "The device speed",
        baseType = "NUMBER",
        aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "isFolded:FALSE", "defaultValue:0" }),	//0-40

		@ThingworxPropertyDefinition(name = "param_totalpackcount", description = "The device speed",
        baseType = "INTEGER",
        aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "isFolded:FALSE", "defaultValue:0" }),	//0-40

		@ThingworxPropertyDefinition(name = "CenterSealTime", description = "The device speed",
        baseType = "NUMBER",
        aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "isFolded:FALSE", "defaultValue:0" }),	//0-40

		@ThingworxPropertyDefinition(name = "EndRemainingSealTemp", description = "The device speed",
        baseType = "NUMBER",
        aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "isFolded:FALSE", "defaultValue:0" }),	//0-40

		@ThingworxPropertyDefinition(name = "EndSealTemperature", description = "The device speed",
        baseType = "NUMBER",
        aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "isFolded:FALSE", "defaultValue:0" }),	//0-40

		@ThingworxPropertyDefinition(name = "EndSealTime", description = "The device speed",
        baseType = "NUMBER",
        aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "isFolded:FALSE", "defaultValue:0" }),	//0-40

		@ThingworxPropertyDefinition(name = "FilmFeedLength", description = "The device speed",
                baseType = "NUMBER",
                aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                        "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                        "isFolded:FALSE", "defaultValue:0" }),	//0-40
		
		@ThingworxPropertyDefinition(name = "ChannelNumber", description = "incremental product count",
    	baseType = "INTEGER",
    	aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
    			"isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
    			"isFolded:FALSE", "defaultValue:0" }),
		
		@ThingworxPropertyDefinition(name = "LastPackageWeight", description = "The device speed",
        baseType = "NUMBER",
        aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "isFolded:FALSE", "defaultValue:0" }),	//0-40
		
		@ThingworxPropertyDefinition(name = "Reject", description = "incremental product count",
    	baseType = "INTEGER",
    	aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
    			"isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
    			"isFolded:FALSE", "defaultValue:0" }),
		
		@ThingworxPropertyDefinition(name = "ProductTargetWeight", description = "The device speed",
        baseType = "NUMBER",
        aspects = { "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "isFolded:FALSE", "defaultValue:0" }),	//0-40
		
        
        @ThingworxPropertyDefinition(name="Alarms1", description="Alarm1", baseType="LONG", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-4294967295
		@ThingworxPropertyDefinition(name="Alarms2", description="Alarm2", baseType="LONG", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }), // 0-4294967295
		@ThingworxPropertyDefinition(name="Alarms3", description="Alarm3", baseType="LONG", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" })	,// 0-4294967295
		@ThingworxPropertyDefinition(name="Alarms4", description="Alarm3", baseType="LONG", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" })	// 0-4294967295
})

/**
 * A very basic VirtualThing with two properties and a service implementation. It also implements
 * processScanRequest to handle periodic actions.
 */
public class SimulatedVPP extends VirtualThing implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(SimulatedVPP.class);
    private Random rand = new Random();
    private int param_totalpackcount = 1;
    
    /**
     * A custom constructor. We implement this so we can call initializeFromAnnotations, which
     * processes all of the VirtualThing's annotations and applies them to the object.
     * 
     * @param name The name of the thing.
     * @param description A description of the thing.
     * @param client The client that this thing is associated with.
     */
    public SimulatedVPP(String name, String description, ConnectedThingClient client)
            throws Exception {

        super(name, description, client);
        this.initializeFromAnnotations();
        logger.debug("SimulatedThing '" + name + "' initalized!");
        
    }
    
    public SimulatedVPP(String name, String description, ConnectedThingClient client,JSONObject json)
            throws Exception {

        super(name, description, client);
        this.initializeFromAnnotations();
        
        Object obj = json.get("param_totalpackcount");
		if (obj != null)
		{
			param_totalpackcount = ((Long) obj).intValue();
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
 		if(Math.random() <= 0.1) super.setProperty("CenterSealTempPV", ThreadLocalRandom.current().nextDouble(0,200));
 		if(Math.random() <= 0.1) super.setProperty("Speed", ThreadLocalRandom.current().nextDouble(0,50));
 		if(Math.random() <= 0.1) super.setProperty("PumpInvSpeed", ThreadLocalRandom.current().nextDouble(0,50));
 		
 		if(Math.random() <= 1.0) {
 			//logger.info("name:"+ this.getName()+" param_totalpackcount:"+param_totalpackcount);
 			
 			super.setProperty("param_totalpackcount", param_totalpackcount++);
 		}else{
 			logger.info("bypass param_totalpackcount for:" + this.getName());
 		}
 		
 		if(Math.random() <= 0.1) super.setProperty("CenterSealTime", ThreadLocalRandom.current().nextDouble(0,10));
 		if(Math.random() <= 0.1) super.setProperty("EndRemainingSealTemp", ThreadLocalRandom.current().nextDouble(0,200));
 		if(Math.random() <= 0.1) super.setProperty("EndSealTemperature", ThreadLocalRandom.current().nextDouble(0,200));
 		if(Math.random() <= 0.1) super.setProperty("EndSealTime", ThreadLocalRandom.current().nextDouble(0,10));
 		if(Math.random() <= 0.1) super.setProperty("FilmFeedLength", ThreadLocalRandom.current().nextDouble(0,400));
 		if(Math.random() <= 0.1){
 			super.setProperty("ChannelNumber", ThreadLocalRandom.current().nextInt(10));
 			super.setProperty("ProductTargetWeight", ThreadLocalRandom.current().nextDouble(0,200));
 		}
 		if(Math.random() <= 0.1) super.setProperty("LastPackageWeight", ThreadLocalRandom.current().nextDouble(0,200));
 		//if(Math.random() <= 0.1) super.setProperty("recipe_ProductTargetWeight", ThreadLocalRandom.current().nextDouble(0,200));
 		
 		if(Math.random() <= 0.01 /*0.010*/) super.setProperty("Reject", ThreadLocalRandom.current().nextInt(2));
 		
 		for (int i = 1; i <= 4; i ++) {
			if(Math.random() > 0.05 /*0.05*/){
				super.setProperty("Alarms" + i, 0);
			}else{
				super.setProperty("Alarms" + i, ThreadLocalRandom.current().nextLong(4294967295L));
			}
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
