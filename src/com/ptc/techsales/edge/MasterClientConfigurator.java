package com.ptc.techsales.edge;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.thingworx.common.RESTAPIConstants;
import com.thingworx.communications.client.ClientConfigurator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MasterClientConfigurator
{
	private final static Logger logger = LoggerFactory.getLogger(MasterClientConfigurator.class);
	public static final String NL = System.getProperty("line.separator");
	public ClientConfigurator config;
	private boolean security;
	private long port;
	private String appKey;
	private String serverLocation;
	private String url;
	private String configfileName;
	private int startDeviceNumber;
	private int endDeviceNumber;
	private String thingNamePrefix;
	private String thingTemplate;
	private int scanRate;
	private String thingValueStream;
	private String loadClass;
	private JSONObject json;
	
	private static MasterClientConfigurator instance;
	
	public static MasterClientConfigurator getInstance(String[] args)
	{
		if( null == instance )
		{
			instance = new MasterClientConfigurator(args);
		}

		return instance;
	}
	
	private MasterClientConfigurator(String[] args)
	{
		security = true;
		port = 443;
		serverLocation = "gateway.desheng.io";
		appKey = "4532513e-e50c-4de6-b68b-c0f3223efc60";
		configfileName = "SASimulator.json";
		thingTemplate = "TT_RTTFT_8600E_V_0_0_1";
		thingNamePrefix = "Simulated_8600E_";
		startDeviceNumber = 1;
		endDeviceNumber = 10;
		scanRate = 1000;
		loadClass=null;
		json = null;
		
		setThingValueStream(null);
		url = (security ? "wss://" : "ws://") + serverLocation + ":" + port + "/Thingworx/WS";
		config = new ClientConfigurator();
		config.ignoreSSLErrors(true);
		config.setReconnectInterval(60000);
		config.setAsSDKType();

		// check arguments
		if (args.length > 0)
		{
			configfileName = args[0];
		}else{
			logger.error("FATAL: configuration file must be provided as first argument in command line!");
			System.exit(0);
		}
		
		if ( parseConfig() )
		{
			url = (security ? "wss://" : "ws://") + serverLocation + ":" + port + "/Thingworx/WS";
		}
		
		config.setUri(url);
		config.getSecurityClaims().addClaim(RESTAPIConstants.PARAM_APPKEY, appKey);
		return;
	}

	private boolean parseConfig()
	{
		JSONParser parser = new JSONParser();
		try
		{
			Object obj = parser.parse(new FileReader(configfileName));

			JSONObject jsonObject = (JSONObject) obj;
			
			json = jsonObject;
			
			obj = jsonObject.get("ServerLocation");
			if (obj != null)
			{
				serverLocation = (String) obj;	
			}

			obj = jsonObject.get("Port");
			if (obj != null)
			{
				port = ((Long) obj).longValue();
			}
			
			obj = jsonObject.get("Security");
			if (obj != null)
			{
				security = (Boolean) obj;
			}

			obj = jsonObject.get("AppKey");
			if (obj != null)
			{
				appKey = (String) obj;
			}
			
			obj = jsonObject.get("ThingTemplate");
			if (obj != null)
			{
				thingTemplate = (String) obj;
			}
			
			obj = jsonObject.get("ThingNamePrefix");
			if (obj != null)
			{
				thingNamePrefix = (String) obj;
			}
						
			obj = jsonObject.get("StartDeviceNumber");
			if (obj != null)
			{
				startDeviceNumber = ((Long) obj).intValue();
			}
			
			obj = jsonObject.get("EndDeviceNumber");
			if (obj != null)
			{
				endDeviceNumber = ((Long) obj).intValue();
			}			
			
			obj = jsonObject.get("ScanRate");
			if (obj != null)
			{
				scanRate = ((Long) obj).intValue();
			}
			obj = jsonObject.get("ValueStream");
			if(obj != null){
				thingValueStream = (String)obj;
			}
			
			obj = jsonObject.get("className");
			if(obj != null){
				loadClass = (String)obj;
			}
			if( startDeviceNumber > endDeviceNumber )
			{
				logger.error("FATAL: The start device number must be less than the end device number for simulating connected devices!");
				System.exit(0);
			}
		}
		catch (FileNotFoundException e)
		{
			logger.error("FATAL: File " + configfileName + " not found, Default Value is not allowed to use.");
			System.exit(0);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (ParseException e)
		{
			logger.error("Parsing error in " + configfileName + " using default values");
			return false;
		}

		return true;
	}
	
	public JSONObject getJSONObject(){
		return json;
	}
	
	public void setJSONObject(JSONObject jsonObject){
		json=jsonObject;
	}
	public String getServerLocation()
	{
		return serverLocation;
	}
	
	public boolean getSecurity()
	{
		return security;
	}
	
	public long getPort()
	{
		return port;
	}
	
	public int getStartDeviceNumber()
	{
		return startDeviceNumber;
	}
	
	public int getEndDeviceNumber()
	{
		return endDeviceNumber;
	}
	
	public String getApplicationKey()
	{
		return appKey;
	}
	
	public String getThingNamePrefix()
	{
		return thingNamePrefix;
	}
	
	public String getThingTemplate()
	{
		return thingTemplate;
	}	

	public int getScanRate()
	{
		return scanRate;
	}
	
	public ClientConfigurator getClientConfigurator()
	{
		return config;
	}
	
	@Override
	public String toString()
	{
		return "URL: " + url + NL + "AppKey: " + appKey;
	}

	public String getThingValueStream() {
		return thingValueStream;
	}

	public void setThingValueStream(String thingValueStream) {
		this.thingValueStream = thingValueStream;
	}
	
	public String getLoadClass(){
		return loadClass;
	}
	
	public void setLoadClass(String loadClass){
		this.loadClass = loadClass;
	}
}
