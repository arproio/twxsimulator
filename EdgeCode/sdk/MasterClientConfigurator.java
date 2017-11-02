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
*  Name          : MasterClientConfigurator.java
*  Description   : This file is to set / get the value to data transfer object
*
*  Version     Date                 Author      Change Description
*  -----------------------------------------------------------------------------
*  1.0           08/07/2017     k.Jothi       Initial Version
*  
*  -----------------------------------------------------------------------------
*/
package com.remote.vjet.sdk;

import java.io.File;
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
	private final static Logger LOGGER = LoggerFactory.getLogger(MasterClientConfigurator.class);
	public static final String NL = System.getProperty(PrinterConstants.LINE_SEP);
	public ClientConfigurator config;
	private boolean security;
	private long port;
	private String appKey;
	private String serverLocation;
	private String url;
	private String configfileName;
	private String repoLocation;
	private File xmlEventDir;
	private File xmlDataDir;
	private File xmlDisconectDir;
	private String gatewayName;
	private String gatewayType;
	private String platformServicesThing;
	private String xmlDataPathDir;
	
	private static MasterClientConfigurator instance;
	private FileReader freader;
	
	public static MasterClientConfigurator getInstance(String[] args)
	{
		if( null == instance )
		{
			instance = new MasterClientConfigurator(args);
		}

		return instance;
	}
	
	MasterClientConfigurator(){
		
	}
	private MasterClientConfigurator(String[] args)
	{
		security = false;
		port = 8080;
		serverLocation = PrinterConstants.LOCALHOST;
		configfileName = PrinterConstants.GATEWAY_FILE;
		repoLocation = PrinterConstants.THINGWORX_REPO;
		gatewayName = PrinterConstants.PLANT_LITE;
		gatewayType = PrinterConstants.WINDOWS;
		platformServicesThing = PrinterConstants.VJ_SERVICE;
		config = new ClientConfigurator();
		config.setReconnectInterval(15);
		//config.ignoreSSLErrors(true);
		config.tunnelsEnabled(true);
		config.setAsSDKType();

		// check arguments
		if (args.length == 1)
		{
			configfileName = args[0];
		}
		
		if ( parseConfig() )
		{
			config.getSecurityClaims().addClaim(RESTAPIConstants.PARAM_APPKEY, appKey);

			if (security)
			{
				url = "wss://";
			}
			else
			{
				url = "ws://";
			}
			url = url + serverLocation + ":" + port + "/Thingworx/WS";
		}
		
		config.setName(gatewayName);
		config.setUri(url);
		config.setAsSDKType();
		return;
	}

	private boolean parseConfig()
	{
		JSONParser parser = new JSONParser();
		try
		{
			freader = new FileReader(configfileName);
			Object obj = parser.parse(freader);

			JSONObject jsonObject = (JSONObject) obj;

			obj = jsonObject.get(PrinterConstants.SERVER_LOCATION);
			if (obj != null)
			{
				serverLocation = (String) obj;
			}

			obj = jsonObject.get(PrinterConstants.PORT);
			if (obj != null)
			{
				port = ((Long) obj).longValue();
			}

			obj = jsonObject.get(PrinterConstants.SECURITY);
			if (obj != null)
			{
				security = (Boolean) obj;
			}

			obj = jsonObject.get(PrinterConstants.APPKEY);
			if (obj != null)
			{
				appKey = (String) obj;
			}

			obj = jsonObject.get(PrinterConstants.REMOTE_REPO);
			if (obj != null)
			{
				repoLocation = (String) obj;
			}

			obj = jsonObject.get(PrinterConstants.GATEWAY_NAME);
			if (obj != null)
			{
				gatewayName = (String) obj;
			}

			obj = jsonObject.get(PrinterConstants.GATEWAY_TYPE);
			if (obj != null)
			{
				gatewayType = (String) obj;
			}

			obj = jsonObject.get(PrinterConstants.SERVICE_THING);
			if (obj != null)
			{
				platformServicesThing = (String) obj;
			}			
			
			
			obj = jsonObject.get(PrinterConstants.XML_DATA_DIR);
			if (obj != null)
			{
				xmlDataPathDir = (String) obj;
			}

			obj = jsonObject.get(PrinterConstants.XML_DATA_DIR);
			if (obj != null)
			{
				xmlDataDir = new File(String.valueOf(obj));
				if( !xmlDataDir.isDirectory() && !xmlDataDir.canRead() )
				{
					LOGGER.error("FATAL: XML Staging directory cannot be read or doesn't exist!  Program will terminate!");
					System.exit(0);
				}
			}
			else
			{
				LOGGER.error("FATAL: XML Data directory not specified!  Program will terminate!");
				System.exit(0);
				
			}
				
			obj = jsonObject.get(PrinterConstants.XML_EVENT_DIR);
			if (obj != null)
			{
				xmlEventDir = new File(String.valueOf(obj));
				if( !xmlEventDir.isDirectory() && !xmlEventDir.canRead() )
				{
					LOGGER.error("FATAL: XML Event directory cannot be read or doesn't exist!  Program will terminate!");
					System.exit(0);
				}
			}
			else
			{
				LOGGER.error("FATAL: XML xmlProcessedDir directory not specified!  Program will terminate!");
				System.exit(0);
			}
			obj = jsonObject.get(PrinterConstants.XML_DISCONNECT_DIR);
			if (obj != null)
			{
				xmlDisconectDir = new File(String.valueOf(obj));
				if( !xmlDisconectDir.isDirectory() && !xmlDisconectDir.canRead() )
				{
					LOGGER.error("FATAL: XML Disconnect directory cannot be read or doesn't exist!  Program will terminate!");
					System.exit(0);
				}
			}
			else
			{
				LOGGER.error("FATAL: XML xmlProcessedDir directory not specified!  Program will terminate!");
				System.exit(0);
			}
			freader.close();
		}
		catch (FileNotFoundException e)
		{
			LOGGER.error("File " + configfileName + " not found using default values");
			return false;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (ParseException e)
		{
			LOGGER.error("Parsing error in " + configfileName + " using default values");
			return false;
		}

		return true;
	}

	public String getRepoLocation()
	{
		return repoLocation;
	}

	public File getXMLEventDir()
	{
		return xmlEventDir;
	}

	public File getXMLDataDir()
	{
		return xmlDataDir;
	}
	public File getXmlDisconectDir()
	{
		return xmlDisconectDir;
	}
	
	public ClientConfigurator getClientConfigurator()
	{
		return config;
	}
	
	public String getGatewayName()
	{
		return gatewayName;
	}
	
	public String getGatewayType()
	{
		return gatewayType;
	}
	
	public String getPlatformServicesThing()
	{
		return platformServicesThing;
	}
	public String getXmlDataPathDir()
	{
		return xmlDataPathDir;
	}
	
	@Override
	public String toString()
	{
		return "URL: " + url + NL + "AppKey: " + appKey;
	}
}
