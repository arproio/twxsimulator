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
*  Name          : DataValue.java
*  Description   : This file is to set / get the value to data transfer object
*
*  Version     Date                 Author      Change Description
*  -----------------------------------------------------------------------------
*  1.0           12/07/2017     k.Jothi       Initial Version
*  
*  -----------------------------------------------------------------------------
*/
package com.remote.vjet.sdk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * This class is used to maintain the constant value in videojet 
 * @author k.jothi
 *
 */
public class PrinterConstants {

	public final static String TAG ="Tag";
	public final static String ACTUAL ="Actual";
	public final static String URL_TYPE="http://www.w3.org/2001/XMLSchema-instance";
	public final static String TYPE="type";
	public final static String TYPE_UBYTE="xsd:unsignedByte";
	public final static String TYPE_INT="xsd:int";
	public final static String TYPE_USHORT="xsd:unsignedShort";
	public final static String TYPE_SHORT ="xsd:short";
	public final static String TYPE_UINT="xsd:unsignedInt";
	public final static String TYPE_STR="xsd:string";
	public final static String TYPE_FLOAT="xsd:float";
	public final static String TYPE_DBLE="xsd:double";
	public final static String TYPE_DATE="xsd:dateTime";
	public final static String TYPE_FORMAT1="yyyy-MM-dd'T'HH:mm:ss'Z'";
	public final static String TYPE_FORMAT2="yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'";
	public final static String UTC="UTC";
	public final static String TYPE_BIN="xsd:base64Binary";
	public final static String CONNECTED="Connected";
	public final static String SERIAL="Serial";
	public final static String DATETIME="DateTime";
	public final static String MODEL="Model";
	public final static String MODEL_ID="ModelID";
	public final static String FRIENDLY_NAME="FriendlyName";
	public final static String TOKEN="Token";
	public final static String ID="Id";
	public final static String VALUES="Values";
	public final static String VALUE="Value";
	public final static String LAST_IMAGE="LastImage";
	public final static String MSG_NAME="MessageName";
	public final static String PLANT_CREATION_DATE="PlantCreationDateTime";
	public final static String PLANT_ID="PlantID";
	public final static String SITE_ID="SiteID";
	public final static String COMPANY_ID="CompanyID";
	public final static String LINE_SEP="line.separator";
	public final static String LOCALHOST="localhost";
	public final static String GATEWAY_FILE="VideojetGateway.json";
	public final static String THINGWORX_REPO="/ThingworxRemoteRepository";
	public final static String PLANT_LITE="PlantServerLite";
	public final static String WINDOWS="Windows";
	public final static String VJ_SERVICE="VJServicesThing";
	public final static String SERVER_LOCATION="ServerLocation";
	public final static String PORT="Port";
	public final static String SECURITY="Security";
	public final static String APPKEY="AppKey";
	public final static String REMOTE_REPO="RemoteRepo";
	public final static String GATEWAY_NAME="GatewayName";
	public final static String GATEWAY_TYPE="GatewayType";
	public final static String SERVICE_THING="ServiceThing";
	public final static String XML_DATA_DIR="XMLDataDir";
	public final static String XML_EVENT_DIR="XMLEventDir";
	public final static String XML_DISCONNECT_DIR="XMLDisconnectDir";
	public final static String SERIAL_NUMBER="SerialNumber";
	public final static String STATIC_ID="ID";
	public final static String DATA="Data";
	public final static String EVENT="Event";
	public final static String HEARTBEAT="Heartbeat";
	public final static String DISCONNECTED="Disconnected";
	public final static String VJ_COMPANYSERVICE="CreateCompanyThing";
	public final static String VJ_PLANTSERVICE="CreatePlantThing";
	public final static String VJ_PRINTERSERVICE="CreatePrinterThing";
	public final static String VJ_CHECKPRINTERSERVICE="CheckPrinterThing";
	public final static String CONN_DATETIME ="Conn_DateTime" ;
	public final static String DISCONNECT_DATETIME ="Disconnect_DateTime";
	public final static String EVENT_TYPE ="251";
	public final static String EVENT_ID ="3";
	
	
	
	public static Date dateToTimeZone(String timeZone,String elem) throws ParseException{
		SimpleDateFormat dtFormat = new SimpleDateFormat(timeZone);
		dtFormat.setTimeZone(TimeZone.getTimeZone(PrinterConstants.UTC));
		return dtFormat.parse(elem);
	}
	
	
}
