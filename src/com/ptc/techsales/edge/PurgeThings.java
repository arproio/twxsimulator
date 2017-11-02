package com.ptc.techsales.edge;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PurgeThings
{
	private final static Logger logger = LoggerFactory.getLogger(PurgeThings.class);
	
	public static void main(String[] args)
	{
		MasterClientConfigurator masterConfig = MasterClientConfigurator.getInstance(args);
		for( int thingNumber = masterConfig.getStartDeviceNumber(); thingNumber <= masterConfig.getEndDeviceNumber(); thingNumber++ ) 
		{
			String thingName = masterConfig.getThingNamePrefix() + ClientGateway.getDisplayPadding(thingNumber);
			deleteThing(masterConfig, thingName);
		}
	}
	
	public static int deleteThing(MasterClientConfigurator masterConfig, String thingName)
	{
		int exitCode = 200;
		OkHttpClient client = ClientGateway.getUnsafeOkHttpClient();		
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody requestBody = RequestBody.create(mediaType, "{\r\n\t\"name\": \"" + thingName + "\"\r\n}");
		
	
		StringBuilder urlBuf = new StringBuilder(masterConfig.getSecurity() ? "https://" : "http://");
		urlBuf.append(masterConfig.getServerLocation());
		urlBuf.append(":" + masterConfig.getPort());
		//urlBuf.append("/Thingworx/Resources/EntityServices/Services/DeleteThing?appKey=");
		urlBuf.append("/Thingworx/Resources/EntityServices/Services/DeleteThing");
		//urlBuf.append(masterConfig.getApplicationKey());
		
		Request request = new Request.Builder()
		  .url(urlBuf.toString())
		  .post(requestBody)
		  .addHeader("content-type", "application/json")
		  .addHeader("accept", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("appKey", masterConfig.getApplicationKey())
		  .build();

		Response response = null;
		try
		{
			logger.info("Deleting thing '" + thingName + "'...");
			response = client.newCall(request).execute();
			exitCode = response.code();
			String returnedMessage = response.body().string();
			logger.debug("Delete thing '" + thingName + "' executed with status: " + exitCode);
			logger.debug("Delete thing '" + thingName + "' returned message: " + returnedMessage);
		}
		catch (IOException ioe)
		{
			logger.error("IOException", ioe);
		}
		finally
		{
			if( null != response ){ response.close(); }
		}
		
		// Return the response of the HTTP Post
		if( null != response )
		{
			return exitCode;
		}
		else
		{
			return -1;
		}
	}
}
