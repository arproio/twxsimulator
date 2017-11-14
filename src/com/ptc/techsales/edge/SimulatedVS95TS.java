package com.ptc.techsales.edge;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thingworx.metadata.FieldDefinition;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinition;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinitions;
import com.thingworx.metadata.collections.FieldDefinitionCollection;
import com.thingworx.types.BaseTypes;
import com.thingworx.types.InfoTable;
import com.thingworx.types.collections.AspectCollection;
import com.thingworx.types.collections.ValueCollection;
import com.thingworx.types.constants.Aspects;
import com.thingworx.types.primitives.BooleanPrimitive;
import com.thingworx.types.primitives.InfoTablePrimitive;
import com.thingworx.types.primitives.IntegerPrimitive;
import com.thingworx.types.primitives.StringPrimitive;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;


@ThingworxPropertyDefinitions(properties = {
		@ThingworxPropertyDefinition(name="alarm_01", description="alarm_01", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_02", description="alarm_02", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_03", description="alarm_03", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_04", description="alarm_04", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_05", description="alarm_05", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_06", description="alarm_06", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_07", description="alarm_07", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_08", description="alarm_08", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_09", description="alarm_09", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_10", description="alarm_10", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_11", description="alarm_11", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_12", description="alarm_12", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_13", description="alarm_13", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_14", description="alarm_14", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_15", description="alarm_15", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_16", description="alarm_16", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_17", description="alarm_17", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_18", description="alarm_18", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_19", description="alarm_19", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_20", description="alarm_20", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_21", description="alarm_21", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_22", description="alarm_22", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_23", description="alarm_23", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_24", description="alarm_24", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_25", description="alarm_25", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_26", description="alarm_26", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_27", description="alarm_27", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_28", description="alarm_28", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_29", description="alarm_29", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_30", description="alarm_30", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_31", description="alarm_31", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_32", description="alarm_32", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_33", description="alarm_33", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_34", description="alarm_34", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_35", description="alarm_35", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_36", description="alarm_36", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_37", description="alarm_37", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_38", description="alarm_38", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_39", description="alarm_39", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_40", description="alarm_40", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_41", description="alarm_41", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_42", description="alarm_42", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_43", description="alarm_43", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_44", description="alarm_44", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_45", description="alarm_45", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_46", description="alarm_46", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_47", description="alarm_47", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_48", description="alarm_48", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_49", description="alarm_49", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_50", description="alarm_50", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_51", description="alarm_51", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_52", description="alarm_52", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_53", description="alarm_53", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_54", description="alarm_54", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_55", description="alarm_55", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_56", description="alarm_56", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_57", description="alarm_57", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_58", description="alarm_58", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_59", description="alarm_59", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_60", description="alarm_60", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_61", description="alarm_61", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_62", description="alarm_62", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_63", description="alarm_63", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="alarm_64", description="alarm_64", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		/*
		@ThingworxPropertyDefinition(name="param_lifetimecycles", description="param_lifetimecycles", baseType="LONG", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="param_speedCPM", description="param_speedCPM", baseType="NUMBER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="param_utillizationtotal", description="param_utillizationtotal", baseType="NUMBER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="param_vacreached", description="param_vacreached", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),*/
		@ThingworxPropertyDefinition(name="recipe_vactarget", description="recipe_vactarget", baseType="INTEGER", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="state_alarm", description="state_alarm", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="state_cleaning", description="state_cleaning", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="state_downstreamRTR", description="state_downstreamRTR", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="state_leaktest", description="state_leaktest", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="state_maintenance", description="state_maintenance", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="state_plcup", description="state_plcup", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="state_preemptivetest", description="state_preemptivetest", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="state_production", description="state_production", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="state_run", description="state_run", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="state_stop", description="state_stop", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="state_upstreamRTS", description="state_upstreamRTS", baseType="BOOLEAN", aspects={ "dataChangeType:VALUE", "dataChangeThreshold:0", "cacheTime:0",
                "isPersistent:FALSE", "isReadOnly:FALSE", "pushType:VALUE",
                "defaultValue:0" }),
		@ThingworxPropertyDefinition(name="infotable_VacComplete",description="", baseType="INFOTABLE", aspects={
				"dataChangeType:NEVER",
		          "dataChangeThreshold:0",
		          "cacheTime:-1",
		          "isPersistent:FALSE",
		          "isReadOnly:FALSE",
		          "pushType:VALUE",
		          "dataShape:DS_VS95TS_param_vaccomplete"
		}),
		@ThingworxPropertyDefinition(name="infotable_ProductLoadDone",description="", baseType="INFOTABLE", aspects={
				"dataChangeType:NEVER",
		          "dataChangeThreshold:0",
		          "cacheTime:-1",
		          "isPersistent:FALSE",
		          "isReadOnly:FALSE",
		          "pushType:VALUE",
		          "dataShape:DS_VS95TS_param_productloaddone"
		}),
		@ThingworxPropertyDefinition(name="infotable_LeakHoldActive",description="", baseType="INFOTABLE", aspects={
				"dataChangeType:NEVER",
		          "dataChangeThreshold:0",
		          "cacheTime:-1",
		          "isPersistent:FALSE",
		          "isReadOnly:FALSE",
		          "pushType:VALUE",
		          "dataShape:DS_VS95TS_leakholdactive"
		}),
		@ThingworxPropertyDefinition(name="infotable_bPreemptiveDataAvailable",description="", baseType="INFOTABLE", aspects={
				"dataChangeType:NEVER",
		          "dataChangeThreshold:0",
		          "cacheTime:-1",
		          "isPersistent:FALSE",
		          "isReadOnly:FALSE",
		          "pushType:VALUE",
		          "dataShape:DS_VS95TS_param_bpreemptivedataavailable"
		})
	})

public class SimulatedVS95TS extends VirtualThing implements Runnable
{
	private static final long serialVersionUID = 8382712607084394970L;
	private static final Logger logger = LoggerFactory.getLogger(SimulatedVS95TS.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");
	private Random rand = new Random();
	private int param_productcount = 1;
	private int param_productpresent = rand.nextInt(1);
	private int param_platennumber = 1 + rand.nextInt(13);
	private int param_lifetimecycles = 1;
	private int param_vaccycletime = 1;
	private int param_chambernumber = 1 + rand.nextInt(7);
	private String state_run = "state_run";
	private boolean state_run_value = true;
	private Dictionary<String, Boolean> booleanPropertiesValue = new Hashtable<String, Boolean>();
	
	private String[] booleanProperties={"state_alarm","state_cleaning","state_downstreamRTR","state_leaktest",
			"state_maintenance","state_plcup","state_preemptivetest","state_production","state_stop",
			"state_upstreamRTS"
	};
	
	private ArrayList<String> booleanArray = new ArrayList<String>();
	private Dictionary<String, Boolean> booleanValue = new Hashtable<String, Boolean>();
	
	public SimulatedVS95TS(String name, String description, ConnectedThingClient client) throws Exception
	{
		super(name, description, client);		
		// Populate the thing shape with the properties, services, and events that are annotated in this code 
		//super.initializeFromAnnotations();
		this.init();
		logger.debug("SimulatedVS95TS '" + name + "' initalized!");
		buildUpDatashapes();
		try {
            this.setPropertyValue("recipe_vactarget", new IntegerPrimitive(600));
            this.setPropertyValue(state_run, new BooleanPrimitive(true));
        } catch (Exception e) {
            logger.warn("Could not ser default value for recipe_vactarget");
        }
	}

	public SimulatedVS95TS(String name, String description, ConnectedThingClient client,JSONObject json) throws Exception
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
		
		obj = json.get("param_vaccycletime");
		if (obj != null)
		{
			param_vaccycletime = ((Long) obj).intValue();
		}
		
		logger.debug("SimulatedVS95TS '" + name + "' initalized!");
		buildUpDatashapes();
		try {
            this.setPropertyValue("recipe_vactarget", new IntegerPrimitive(600));
            this.setPropertyValue(state_run, new BooleanPrimitive(true));
            
            for(int i=0;i<booleanProperties.length;i++){
            	this.setPropertyValue(booleanProperties[i], new BooleanPrimitive(false));
    		}
        } catch (Exception e) {
            logger.warn("Could not ser default value for recipe_vactarget");
        }
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
		
		for (int i = 10; i <= 64; i ++) {
			booleanArray.add("alarm_"+i);
		}
		
		for(String property:booleanArray){
			booleanValue.put(property, Boolean.FALSE);
		}
		
		for(int i=0;i<booleanProperties.length;i++){
			booleanPropertiesValue.put(booleanProperties[i], Boolean.FALSE);
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

		if(Math.random()<= 0.95) super.setProperty("infotable_VacComplete", new InfoTablePrimitive(get_infotable_VacComplete(param_lifetimecycles++,param_vaccycletime++)));
		if(Math.random() <= 0.1 /*0.1*/) super.setProperty("infotable_ProductLoadDone", new InfoTablePrimitive(get_infotable_ProductLoadDone()));
		if(Math.random() <= 0.2 /*0.2*/) super.setProperty("infotable_LeakHoldActive", new InfoTablePrimitive(get_infotable_LeakHoldActive()));
		if(Math.random() <= 0.1 /*0.1*/) super.setProperty("infotable_bPreemptiveDataAvailable", new InfoTablePrimitive(get_infotable_bPreemptiveDataAvailable()));
		//super.setProperty("recipe_vactarget", 600);
		for(String booleanProperty : booleanArray){
			setBooleanTypeProperty(booleanProperty);
		}
		
		if(Math.random()>0.05){
			if(!state_run_value){
				state_run_value = true;
				super.setProperty(state_run, new BooleanPrimitive(true));
				for(int i=0;i<booleanProperties.length;i++){
					if(booleanPropertiesValue.get(booleanProperties[i]).booleanValue()){
						booleanPropertiesValue.put(booleanProperties[i], Boolean.FALSE);
						super.setProperty(booleanProperties[i], new BooleanPrimitive(false));
					}
					
				}
			}
		}else{
			if(state_run_value){
				state_run_value = false;
				super.setProperty(state_run, new BooleanPrimitive(false));
			}
			int randomSelect = ThreadLocalRandom.current().nextInt(booleanProperties.length-1);
			for(int i=0;i<booleanProperties.length;i++){
				if(i==randomSelect){
					if(!booleanPropertiesValue.get(booleanProperties[i]).booleanValue()){
						booleanPropertiesValue.put(booleanProperties[i], Boolean.TRUE);
						super.setProperty(booleanProperties[i], new BooleanPrimitive(true));
					}
				}else{
					if(booleanPropertiesValue.get(booleanProperties[i]).booleanValue()){
						booleanPropertiesValue.put(booleanProperties[i], Boolean.FALSE);
						super.setProperty(booleanProperties[i], new BooleanPrimitive(false));
					}
				}
			}
		}
		/*
		for (int i = 1; i <= 9; i ++) {
			if(Math.random() > 0.05 ){
				super.setProperty("alarm_0" + i, 0);
			}else{
				super.setProperty("alarm_0" + i, 1);
			}
		}
		
		for (int i = 10; i <= 64; i ++) {
			if(Math.random() > 0.05 ){
				super.setProperty("alarm_" + i, 0);
			}else{
				super.setProperty("alarm_" + i, 1);
			}
		}

		
		
		if(Math.random()> 0.05){
			super.setProperty("state_alarm", 0);
		}else{
			super.setProperty("state_alarm", 1);
		}
		if(Math.random()> 0.05){
			super.setProperty("state_cleaning", 0);
		}else{
			super.setProperty("state_cleaning", 1);
		}
		if(Math.random()> 0.05){
			super.setProperty("state_downstreamRTR", 0);
		}else{
			super.setProperty("state_downstreamRTR", 1);
		}
		if(Math.random()> 0.05){
			super.setProperty("state_leaktest", 0);
		}else{
			super.setProperty("state_leaktest", 1);
		}
		if(Math.random()> 0.05){
			super.setProperty("state_maintenance", 0);
		}else{
			super.setProperty("state_maintenance", 1);
		}
		if(Math.random()> 0.05){
			super.setProperty("state_plcup", 0);
		}else{
			super.setProperty("state_plcup", 1);
		}
		if(Math.random()> 0.05){
			super.setProperty("state_preemptivetest", 0);
		}else{
			super.setProperty("state_preemptivetest", 1);
		}
		if(Math.random()> 0.05){
			super.setProperty("state_production", 0);
		}else{
			super.setProperty("state_production", 1);
		}
		if(Math.random()> 0.05){
			super.setProperty("state_run", 0);
		}else{
			super.setProperty("state_run", 1);
		}
		if(Math.random()> 0.05){
			super.setProperty("state_stop", 0);
		}else{
			super.setProperty("state_stop", 1);
		}
		if(Math.random()> 0.05){
			super.setProperty("state_upstreamRTS", 0);
		}else{
			super.setProperty("state_upstreamRTS", 1);
		}*/

		
		logger.debug("Updating properties for thing " + getName());
		// Update the subscribed properties to send any updates to Thingworx
		// Without calling these methods, the property and event updates will not be sent
		super.updateSubscribedProperties(1000);
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

	private InfoTable get_infotable_bPreemptiveDataAvailable(){
		InfoTable table = new InfoTable(getDataShapeDefinition("DS_VS95TS_param_bpreemptivedataavailable"));

        ValueCollection entry = new ValueCollection();

        DateTime now = DateTime.now();
        
        try {
            // entry 1
            entry.clear();
            entry.SetDateTimeValue("dtProcessed", now);
            entry.SetNumberValue("rAirflow_BasePosition", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetNumberValue("rAirflow_Chambercylinders", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetNumberValue("rAirflow_Precutcylinders", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetNumberValue("rAirflow_ReentryValve", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetNumberValue("rAirflow_Sealing", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetNumberValue("rAirflow_Tensioning", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetNumberValue("rAirflow_VacuumValve", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetNumberValue("rLeak_VacuumChamber_NoAction", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetNumberValue("rLeak_VacuumChamber_Precut", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetNumberValue("rLeak_VacuumChamber_Sealing", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetNumberValue("rLeak_VacuumPumpSide", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetNumberValue("tVacuumDrawingTimeSeconds", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetIntegerValue("tVacuumLeaktestDuration", ThreadLocalRandom.current().nextInt(5,10));
            
            table.addRow(entry.clone());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return table;
	}
	
	private InfoTable get_infotable_LeakHoldActive(){
		InfoTable table = new InfoTable(getDataShapeDefinition("DS_VS95TS_leakholdactive"));

        ValueCollection entry = new ValueCollection();

        
        try {
            // entry 1
            entry.clear();
            entry.SetNumberValue("param_leakmeasured", ThreadLocalRandom.current().nextDouble(10,20));
            entry.SetIntegerValue("param_leakscantime", ThreadLocalRandom.current().nextInt(5,10));
            
            table.addRow(entry.clone());
            
            //entry 2
            //entry.clear();
            //entry.SetNumberValue("param_leakmeasured", ThreadLocalRandom.current().nextDouble(10,20));
            //entry.SetIntegerValue("param_leakscantime", ThreadLocalRandom.current().nextInt(5,10));
            
            //table.addRow(entry.clone());
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return table;
	}
	
	private InfoTable get_infotable_ProductLoadDone(){
		InfoTable table = new InfoTable(getDataShapeDefinition("DS_VS95TS_param_productloaddone"));

        ValueCollection entry = new ValueCollection();

        
        try {
            // entry 1
        	//for(int index=0;index<ThreadLocalRandom.current().nextInt(1,5);index++){
	            entry.clear();
	            entry.SetNumberValue("param_utillizationleft", ThreadLocalRandom.current().nextDouble(0,1));
	            entry.SetNumberValue("param_utillizationright", ThreadLocalRandom.current().nextDouble(0,1));
	            entry.SetNumberValue("param_utillizationtotal", ThreadLocalRandom.current().nextDouble(0,1));
	            
	            table.addRow(entry.clone());
        	//}
        } catch (Exception e) {
            e.printStackTrace();
        }

        return table;
	}
	
	private InfoTable get_infotable_VacComplete(long param_lifetimecycles, long param_vaccycletime){
		InfoTable table = new InfoTable(getDataShapeDefinition("DS_VS95TS_param_vaccomplete"));

        ValueCollection entry = new ValueCollection();

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(now);
        
        try {
            // entry 1
            entry.clear();
            entry.SetStringValue("param_cycletimestamp", timestamp);
            entry.SetLongValue("param_lifetimecycles", param_lifetimecycles);
            entry.SetNumberValue("param_speedCPM", ThreadLocalRandom.current().nextDouble(0,60));
            entry.SetIntegerValue("param_vacabort", ThreadLocalRandom.current().nextInt(5));
            entry.SetLongValue("param_vaccycletime", param_vaccycletime);
            entry.SetIntegerValue("param_vacreached", ThreadLocalRandom.current().nextInt(5,10));
            
            entry.SetIntegerValue("Sealbar1_Current", ThreadLocalRandom.current().nextInt(5,20));
            entry.SetIntegerValue("Sealbar1_SealTime", ThreadLocalRandom.current().nextInt(15,25));
            entry.SetIntegerValue("Sealbar1_TempMax", ThreadLocalRandom.current().nextInt(300,320));
            entry.SetIntegerValue("Sealbar1_TempOver", ThreadLocalRandom.current().nextInt(330,350));
            entry.SetIntegerValue("Sealbar1_TempReac", ThreadLocalRandom.current().nextInt(250,260));
            entry.SetIntegerValue("Sealbar1_TempSett", ThreadLocalRandom.current().nextInt(300,310));
            entry.SetIntegerValue("Sealbar1_Voltage", ThreadLocalRandom.current().nextInt(210,228));
            
            entry.SetIntegerValue("Sealbar2_Current", ThreadLocalRandom.current().nextInt(5,20));
            entry.SetIntegerValue("Sealbar2_SealTime", ThreadLocalRandom.current().nextInt(15,25));
            entry.SetIntegerValue("Sealbar2_TempMax", ThreadLocalRandom.current().nextInt(300,320));
            entry.SetIntegerValue("Sealbar2_TempOver", ThreadLocalRandom.current().nextInt(330,350));
            entry.SetIntegerValue("Sealbar2_TempReac", ThreadLocalRandom.current().nextInt(250,260));
            entry.SetIntegerValue("Sealbar2_TempSett", ThreadLocalRandom.current().nextInt(300,310));
            entry.SetIntegerValue("Sealbar2_Voltage", ThreadLocalRandom.current().nextInt(210,228));
            
            table.addRow(entry.clone());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("vaccomplete:"+table.ToJSON());
        //System.out.print("vaccomplete:"+table.ToJSON());
        return table;
	}
	
	private void buildUpDatashapes(){
		/* DS_VS95TS_param_vaccomplete */
		FieldDefinitionCollection fields = new FieldDefinitionCollection();
        fields.addFieldDefinition(new FieldDefinition("param_cycletimestamp", BaseTypes.STRING));
        fields.addFieldDefinition(new FieldDefinition("param_lifetimecycles", BaseTypes.LONG));
        fields.addFieldDefinition(new FieldDefinition("param_speedCPM", BaseTypes.NUMBER));
        fields.addFieldDefinition(new FieldDefinition("param_vacabort", BaseTypes.INTEGER));
        
        fields.addFieldDefinition(new FieldDefinition("param_vaccycletime", BaseTypes.LONG));
        fields.addFieldDefinition(new FieldDefinition("param_vacreached", BaseTypes.INTEGER));
        
        AspectCollection aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("A"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar1_Current", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("ms"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar1_SealTime", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("°C"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar1_TempMax", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("°C"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar1_TempOver", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("°C"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar1_TempReac", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("°C"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar1_TempSett", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("V"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar1_Voltage", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("A"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar2_Current", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("ms"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar2_SealTime", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("°C"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar2_TempMax", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("°C"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar2_TempOver", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("°C"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar2_TempReac", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("°C"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar2_TempSett", BaseTypes.INTEGER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("V"));
        fields.addFieldDefinition(new FieldDefinition("Sealbar2_Voltage", BaseTypes.INTEGER,aspects));
        
        defineDataShapeDefinition("DS_VS95TS_param_vaccomplete", fields);
        /* DS_VS95TS_param_vaccomplete Done */
        
        /* DS_VS95TS_param_productloaddone */
        fields = new FieldDefinitionCollection();
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("%"));
        fields.addFieldDefinition(new FieldDefinition("param_utillizationleft", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("%"));
        fields.addFieldDefinition(new FieldDefinition("param_utillizationright", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("%"));
        fields.addFieldDefinition(new FieldDefinition("param_utillizationtotal", BaseTypes.NUMBER,aspects));
        
        defineDataShapeDefinition("DS_VS95TS_param_productloaddone", fields);
        /* DS_VS95TS_param_productloaddone Done */
        
        /* DS_VS95TS_leakholdactive */
        fields = new FieldDefinitionCollection();
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("mbar"));
        fields.addFieldDefinition(new FieldDefinition("param_leakmeasured", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("sec"));
        fields.addFieldDefinition(new FieldDefinition("param_leakscantime", BaseTypes.INTEGER,aspects));
        
        defineDataShapeDefinition("DS_VS95TS_leakholdactive", fields);
        /* DS_VS95TS_leakholdactive Done */
        
        /* DS_VS95TS_param_bpreemptivedataavailable */
        fields = new FieldDefinitionCollection();
        fields.addFieldDefinition(new FieldDefinition("dtProcessed", BaseTypes.DATETIME));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("lt"));
        fields.addFieldDefinition(new FieldDefinition("rAirflow_BasePosition", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("lt"));
        fields.addFieldDefinition(new FieldDefinition("rAirflow_Chambercylinders", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("lt"));
        fields.addFieldDefinition(new FieldDefinition("rAirflow_Precutcylinders", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("lt"));
        fields.addFieldDefinition(new FieldDefinition("rAirflow_ReentryValve", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("lt"));
        fields.addFieldDefinition(new FieldDefinition("rAirflow_Sealing", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("lt"));
        fields.addFieldDefinition(new FieldDefinition("rAirflow_Tensioning", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("lt"));
        fields.addFieldDefinition(new FieldDefinition("rAirflow_VacuumValve", BaseTypes.NUMBER,aspects));
        
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("mbar"));
        fields.addFieldDefinition(new FieldDefinition("rLeak_VacuumChamber_NoAction", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("mbar"));
        fields.addFieldDefinition(new FieldDefinition("rLeak_VacuumChamber_Precut", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("mbar"));
        fields.addFieldDefinition(new FieldDefinition("rLeak_VacuumChamber_Sealing", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("mbar"));
        fields.addFieldDefinition(new FieldDefinition("rLeak_VacuumPumpSide", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("mbar"));
        fields.addFieldDefinition(new FieldDefinition("tVacuumDrawingTimeSeconds", BaseTypes.NUMBER,aspects));
        
        aspects = new AspectCollection();
        aspects.put(Aspects.ASPECT_UNITS, new StringPrimitive("mbar"));
        fields.addFieldDefinition(new FieldDefinition("tVacuumLeaktestDuration", BaseTypes.NUMBER,aspects));
        
        defineDataShapeDefinition("DS_VS95TS_param_bpreemptivedataavailable", fields);
        
        /* DS_VS95TS_param_bpreemptivedataavailable Done */
        
	}
}