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
*  Name          : VideoJetGatewayEdit.java
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
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.ConnectionException;
import com.thingworx.relationships.RelationshipTypes.ThingworxEntityTypes;
import com.thingworx.types.InfoTable;
import com.thingworx.types.collections.ValueCollection;
import com.thingworx.types.primitives.StringPrimitive;

import okhttp3.OkHttpClient;
/**
 * The VideoJetGatewayEdit program is to connect the 
 * Thingworx Platform 
 * @author k.jothi
 *
 */
public class VideoJetGatewayEdit extends ConnectedThingClient
{
	private final static Logger LOGGER = LoggerFactory.getLogger(VideoJetGatewayEdit.class);
	private static ConcurrentHashMap<String, PrinterThingEdit> printerHashMap;
	private MasterClientConfigurator masterConfig;
	//static DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS:SSSSSS");
	private static boolean moveFile =true;
	
	public VideoJetGatewayEdit(MasterClientConfigurator mcConfig) throws Exception
	{
		super(mcConfig.config);
		masterConfig = mcConfig;
		printerHashMap = new ConcurrentHashMap<String, PrinterThingEdit>();
	}
	
	/** Sorting the file
	 * @param filesList
	 * @return filesLisy
	 */
	private static List<File> getSortedFilesList(List<File[]> finalList)
	{
		List<File> sortedFilesList = new ArrayList<File>();
		for(File[] filesList: finalList){
			for (int i = 0; i < filesList.length; i++)
			{
				sortedFilesList.add(filesList[i]);
			}
		}
		Collections.sort(sortedFilesList, XMLFileFilter.XML_ORDER);
		return sortedFilesList;
	}
	/** Based on the device selecting file type
	 * @param file  FileTypes
	 * @param gatewayType  deviceType
	 * @return file type
	 */
	public static String fileContentType(File file, String gatewayType)
	{
		String contentType = PrinterConstants.DATA;
		if( gatewayType.equalsIgnoreCase(PrinterConstants.WINDOWS) )
		{
			if( file.getName().startsWith("D_") )
			{
				contentType = PrinterConstants.DATA;
			}
			else if( file.getName().startsWith("E_") )
			{
				contentType = PrinterConstants.EVENT;
			}
			else if( file.getName().startsWith("A_") )
			{
				contentType = PrinterConstants.DISCONNECTED;
			}
		}
		else
		{
			String prefixToken = file.getName().split("_")[1];		
			if( prefixToken.equals("D") )
			{
				contentType = PrinterConstants.DATA;
			}
			else if( prefixToken.equals("E") )
			{
				contentType = PrinterConstants.EVENT;
			}
			else if( prefixToken.equals("A") )
			{
				contentType = PrinterConstants.DISCONNECTED;
			}
		}
		return contentType;
	}
	
	/** Sending data to Thingworx platform
	 *  @param args main method
	 */
	public static void main(String[] args)  
	{
		MasterClientConfigurator masterConfig = MasterClientConfigurator.getInstance(args);
		VideoJetGatewayEdit client;
		try {
			client = new VideoJetGatewayEdit(masterConfig);
			File[] dataFile = masterConfig.getXMLDataDir().listFiles(new XMLFileFilter("D_", ".xml",0));
			//List<File> sortedDataFilesList = getSortedFilesList(dataFile);
			File[] eventFile = masterConfig.getXMLEventDir().listFiles(new XMLFileFilter("E_", ".xml",0));
			
			//For Disconnected File   
			
			File[] disconnectFile = masterConfig.getXmlDisconectDir().listFiles(new XMLFileFilter("A_", ".xml",0));

			List<File[]> finalList = new ArrayList<File[]>();
			finalList.add(dataFile);
			finalList.add(eventFile);
			finalList.add(disconnectFile);
			
			List<File> sortedFilesList =getSortedFilesList(finalList);
			
			//System.setProperty("javax.net.ssl.keyStoreType", "pkcs12");
			System.setProperty("javax.net.ssl.trustStore", "/usr/lib/jvm/jdk1.8.0_131/jre/lib/security/root_store/keystore.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "thingworx");
			
			client.start();
			
		
			
			/*System.setProperty("javax.net.ssl.keyStoreType", "pkcs12");
			System.setProperty("javax.net.ssl.keyStore", "/usr/lib/jvm/jdk1.8.0_131/jre/lib/security/root_store/root_store");
			System.setProperty("javax.net.ssl.keyStorePassword", "thingworx");*/
			
/*			Path dir = Paths.get(masterConfig.getXmlDataPathDir());
			new VjetWatcher(dir,true).processEvents();
			
			System.out.println("filenbjhhjhj name"+dir.getFileName());*/
			
			while (!client.isShutdown())
			{
				if (client.isConnected())
				{
				/**
				 * Parse all XML files and define its properties and events
				 */
				//long thingStartTime =System.currentTimeMillis();
				//System.out.println("Before Invoking Thing properties start time ******"+df.format(thingStartTime));
				Iterator<File> fIter = sortedFilesList.iterator();
				File f =null;
				if(sortedFilesList.size()>0){
				while(fIter.hasNext()){
					f= fIter.next();
					SAXParserFactory parseFac = SAXParserFactory.newInstance();
				    SAXParser parser = parseFac.newSAXParser();

				    try{
					String contentType = fileContentType(f, masterConfig.getGatewayType());
					PrinterThingEdit pt = null;
					//TODO If there is no exception then only goes here
					if ( contentType.equals(PrinterConstants.DATA))
					{
					    XMLMessageHandler handler = new XMLMessageHandler();
					    parser.parse(f, handler);
						Message msg = handler.msgTmp;
						if (msg!=null && msg.getSerialNumber() != null && msg.getSerialNumber() != "")
						{
							if (printerHashMap.containsKey(msg.getSerialNumber()))
							{
								pt = printerHashMap.get(msg.getSerialNumber());
								pt.defineProperties(msg);
								client.bindThing(pt);
							}
							else
							{
								pt = new PrinterThingEdit(msg,null,client);
								client.invokePlatformService(msg,client);
								printerHashMap.put(msg.getSerialNumber(), pt);
								pt.defineProperties(msg);
								client.bindThing(pt);
								
							}
						}
						else
						{
							moveFile = false;
							LOGGER.error("File doesn't have serial number" + f.getName());
						}
						// Update property values for this Data/Event message
						if (null != pt)
						{
							pt.loadPropertySubscriptions();
						   	pt.processScanRequest();
						}
						
					}else if(contentType.equals(PrinterConstants.EVENT)){
						EventValueFilter evntHandler = new EventValueFilter();
						parser.parse(f, evntHandler);
						EventMessage eventObj = evntHandler.eventObj;
						if(eventObj!=null && eventObj.getSerialNo()!=null && eventObj.getSerialNo()!=""){
							if (printerHashMap.containsKey(eventObj.getSerialNo()))
							{
								pt = printerHashMap.get(eventObj.getSerialNo());
								pt.defineEvent();
								client.bindThing(pt);
								pt.scanConnectedEvent(eventObj);
							}
							else 
							{
								//System.out.println("Enters into Event folder");
								//client.invokePlatformForBind(eventObj.getSerialNo(),client);
								//moveFile = false;
								LOGGER.error("These event Printers date are too early / Not available in the System  : "+eventObj.getSerialNo() +" Event Date:"+eventObj.getEventDateTime());;
								//TODO Move those files to unprocessed folder
							}
						}
						eventObj =null;
					}
					else if(contentType.equals(PrinterConstants.DISCONNECTED)){
						DisconnectedValueFilter dishandler = new DisconnectedValueFilter();
						parser.parse(f, dishandler);
						DisconnectedMessage disObj = dishandler.disconObj;
						if(disObj!= null && disObj.getSerialNumber()!=null && disObj.getSerialNumber()!=""){
							if (printerHashMap.containsKey(disObj.getSerialNumber()))
								{
									pt = printerHashMap.get(disObj.getSerialNumber());
									pt.defineProperties(disObj);
									client.bindThing(pt);
									pt.loadPropertySubscriptions();
									pt.processScanRequest();
								}else{
								
									//moveFile = false;
									LOGGER.error("These disconnected Printers date are too early / Not available in the System  : "+disObj.getSerialNumber() +" Diconnected Date:"+disObj.getDateTime());;
									//TODO Move those files to unprocessed folder
						}
						}
						disObj =null;
					}
					else
					{
						moveFile = false;
						LOGGER.warn("Currently not setup to process - " + f.getName() + " files!");
					}
					
					}catch(FileNotFoundException e){
						e.printStackTrace();
						moveFile = false;
						LOGGER.error("File not avalibale"+e);
					}catch(Exception e){
						e.printStackTrace();
						moveFile = false;
						//e.printStackTrace();
						LOGGER.error("Generic Exception"+e);
						
					}
					//TODO  Move the file to processed when done
					//TODO  If any exception occurs file should not be processed
					if(moveFile){
						File movedFile = new File(f.getAbsolutePath() + "_processed");
						boolean moveSuccess = f.renameTo(movedFile);
						if (!moveSuccess)
						{
							LOGGER.error("Failed to move file " + f.getName() + " to directory " + movedFile.getParentFile().getAbsolutePath());
						}else{
							//LOGGER.info("file Processing continues");
						}
					}else{
						//TODO Move files to the unprocessed folder
					}
					moveFile =true;
				 }
				//long thingEndTime =System.currentTimeMillis();
				//LOGGER.info("Invoking completed ******"+(thingEndTime-thingStartTime) +"ms *** Date and time******"+df.format(thingEndTime));
				}
				
				//Implement Watcher solves here
				sortedFilesList.clear();
				finalList = new ArrayList<>();
				finalList.add(masterConfig.getXMLDataDir().listFiles(new XMLFileFilter("D_", ".xml",0)));
				finalList.add(masterConfig.getXMLEventDir().listFiles(new XMLFileFilter("E_", ".xml",0)));
				finalList.add(masterConfig.getXmlDisconectDir().listFiles(new XMLFileFilter("A_", ".xml",0)));
				sortedFilesList =getSortedFilesList(finalList);
				//TODO  Add the list of files after sorting 
			}
		}
		}catch (NullPointerException e1) {
			//e1.printStackTrace();
			// TODO Auto-generated catch block
			LOGGER.error("Initial Start Failed : " + e1.getMessage());
		} 
		catch (Exception e2) {
			// TODO Auto-generated catch block
			LOGGER.error("Initial Start Failed : " + e2.getMessage());
		}
		printerHashMap.clear();
	}
	

	/**
	 * @param serialNumber
	 * @param client 
	 * @throws Exception 
	 * @throws ConnectionException 
	 * @throws TimeoutException 
	 */
	private boolean invokePlatformForBind(String serialNumber, VideoJetGatewayEdit client) {
		boolean chck =false;
		ValueCollection vc = new ValueCollection();
		vc.put(PrinterConstants.SERIAL_NUMBER, new StringPrimitive(serialNumber));
		try
		{
		InfoTable table =client.invokeService(ThingworxEntityTypes.Things, masterConfig.getPlatformServicesThing(), PrinterConstants.VJ_CHECKPRINTERSERVICE, vc, true, Integer.valueOf(2000));
		System.out.println("Result Tbale value"+table.toString());
		}
		catch (TimeoutException e)
		{
			moveFile = false;
			LOGGER.error("TimeoutException", e);
			
		}
		catch (ConnectionException e)
		{
			moveFile = false;
			LOGGER.error("ConnectionException", e);
		}
		catch (Exception e)
		{
			moveFile = false;
			LOGGER.error("GenricException", e);
		}
		return chck;
	}

	/** Involves dynamic thing creation
	 * @param msg
	 * @param client
	 * @return boolean
	 * @throws Exception 
	 */
	private void invokePlatformService(Message msg, VideoJetGatewayEdit client) throws Exception
	{
		//OkHttpClient httpclient = getUnsafeOkHttpClient();	
		ValueCollection vc = new ValueCollection();
		vc.put(PrinterConstants.MODEL_ID, new StringPrimitive(msg.getModelID() + ""));
		vc.put(PrinterConstants.SERIAL_NUMBER, new StringPrimitive(msg.getSerialNumber()));
		vc.put(PrinterConstants.PLANT_ID, new StringPrimitive("Plant_"+msg.getPlantID()));
		vc.put(PrinterConstants.COMPANY_ID, new StringPrimitive("Company_"+msg.getCompanyID()));
		try
		{
			//Need to do for Plant and company Thing
			if(msg.getCompanyID()!=null && !msg.getCompanyID().isEmpty()){
				invokeService(ThingworxEntityTypes.Things, masterConfig.getPlatformServicesThing(), PrinterConstants.VJ_COMPANYSERVICE, vc, true, Integer.valueOf(2000));
			}
			if(msg.getPlantID()!=null && !msg.getPlantID().isEmpty()){
				invokeService(ThingworxEntityTypes.Things, masterConfig.getPlatformServicesThing(), PrinterConstants.VJ_PLANTSERVICE, vc, true, Integer.valueOf(2000));
			}
			client.invokeService(ThingworxEntityTypes.Things, masterConfig.getPlatformServicesThing(), PrinterConstants.VJ_PRINTERSERVICE, vc, true, Integer.valueOf(2000));
		}
		catch (TimeoutException e)
		{
			moveFile = false;
			LOGGER.error("TimeoutException", e);
			
		}
		catch (ConnectionException e)
		{
			moveFile = false;
			LOGGER.error("ConnectionException", e);
		}
		catch (Exception e)
		{
			moveFile = false;
			LOGGER.error("GenricException", e);
		}
	}
	
	
}
