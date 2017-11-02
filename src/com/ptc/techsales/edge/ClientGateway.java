package com.ptc.techsales.edge;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ClientGateway extends ConnectedThingClient
{
	private final static Logger logger = LoggerFactory.getLogger(ClientGateway.class);

	public ClientGateway(MasterClientConfigurator mcConfig) throws Exception
	{
		super(mcConfig.config);
	}
	
	public static OkHttpClient getUnsafeOkHttpClient()
	{
		try
		{
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager()
			{
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException
				{
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException
				{
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers()
				{
					return new java.security.cert.X509Certificate[] {};
				}
			} };

			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
			builder.hostnameVerifier(new HostnameVerifier()
			{
				@Override
				public boolean verify(String hostname, SSLSession session)
				{
					return true;
				}
			});

			OkHttpClient okHttpClient = builder.build();
			return okHttpClient;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static final String getDisplayPadding(int thingNumber)
	{
		return (thingNumber < 10 ? "00"+thingNumber : (thingNumber < 100 ? "0"+thingNumber : ""+thingNumber));
	}
	
	public int runThingServiceWithBody(MasterClientConfigurator masterConfig, String thingName, String serviceName,RequestBody requestBody){
		OkHttpClient client = getUnsafeOkHttpClient();
		
		StringBuilder urlBuf = new StringBuilder(masterConfig.getSecurity() ? "https://" : "http://");
		urlBuf.append(masterConfig.getServerLocation());
		urlBuf.append(":" + masterConfig.getPort());
		//urlBuf.append("/Thingworx/Things/" + thingName + "/Services/" + serviceName + "?appKey=");
		//urlBuf.append(masterConfig.getApplicationKey());
		urlBuf.append("/Thingworx/Things/" + thingName + "/Services/" + serviceName);
		
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
			response = client.newCall(request).execute();
			logger.debug("Service " + serviceName + " for thing '" + thingName + "' executed with status: " + response.code());
			logger.debug("Service " + serviceName + " for thing '" + thingName + "' returned message: " + response.message());
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
			return response.code();
		}
		else
		{
			return -1;
		}		
		
	}
	public int runThingService(MasterClientConfigurator masterConfig, String thingName, String serviceName)
	{
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody requestBody = RequestBody.create(mediaType, "");
		return runThingServiceWithBody(masterConfig,thingName,serviceName,requestBody);
	}
	
	public int registerThing(MasterClientConfigurator masterConfig, String thingName)
	{
		int exitCode = 200;
		OkHttpClient client = getUnsafeOkHttpClient();		
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody requestBody = RequestBody.create(mediaType, "{\r\n\t\"name\": \"" + thingName + "\"" +
														",\r\n\t\"description\": \"Load JAVA SDK Based testing simulated " + masterConfig.getThingTemplate() + " thing\"" +
														",\r\n\t\"thingTemplateName\": \"" + masterConfig.getThingTemplate() + "\"" +
														"\r\n}");
		
	
		StringBuilder urlBuf = new StringBuilder(masterConfig.getSecurity() ? "https://" : "http://");
		urlBuf.append(masterConfig.getServerLocation());
		urlBuf.append(":" + masterConfig.getPort());
		//urlBuf.append("/Thingworx/Resources/EntityServices/Services/CreateThing?appKey=");
		//urlBuf.append(masterConfig.getApplicationKey());
		urlBuf.append("/Thingworx/Resources/EntityServices/Services/CreateThing");
		
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
			response = client.newCall(request).execute();
			exitCode = response.code();
			String returnedMessage = response.body().string();
			logger.debug("Create thing '" + thingName + "' executed with status: " + exitCode);
			logger.debug("Create thing '" + thingName + "' returned message: " + returnedMessage);
			//logger.debug("exit code:"+exitCode +" and return message is:"+ returnedMessage);
			
			if( exitCode == 200 /*|| returnedMessage.indexOf("already exists") > 0 */ )
			{
				// Enable the thing...
				logger.debug("Start to enable thing:"+thingName);
				exitCode = runThingService(masterConfig, thingName, "EnableThing");
				logger.debug("Start to make it available for data ingestion:"+thingName);
				// Make it available for data ingestion...
				exitCode = runThingService(masterConfig, thingName, "RestartThing");
				logger.debug("Enabled and restarted:"+thingName);
				if(masterConfig.getThingValueStream() != null){
					logger.debug("Start to set value stream:"+masterConfig.getThingValueStream());
					requestBody = RequestBody.create(mediaType, "{\r\n\t\"name\": \"" + masterConfig.getThingValueStream() + "\"" +
							"\r\n}");
					exitCode = runThingServiceWithBody(masterConfig,thingName,"SetValueStream", requestBody);
					logger.debug("Reponse Code for SetValueStream:"+exitCode);
				}
			}
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
	
	
	public static void main(String[] args) throws Exception
	{
		MasterClientConfigurator masterConfig = MasterClientConfigurator.getInstance(args);
		ClientGateway client = new ClientGateway(masterConfig);
		try
		{
			logger.info("Connecting to server = " + masterConfig.getServerLocation());
			for( int thingNumber = masterConfig.getStartDeviceNumber(); thingNumber <= masterConfig.getEndDeviceNumber(); thingNumber++ ) 
			{
				String thingName = masterConfig.getThingNamePrefix() + getDisplayPadding(thingNumber);
				// First we need to make sure the platform has a surrogate for our remote virtual thing
				int responseCode = client.registerThing(masterConfig, thingName);
				logger.info("register result for "+thingName+" is:"+responseCode);
				VirtualThing remoteThing=null;
				String className="com.ptc.techsales.edge.SimulatedThing";
				if(masterConfig.getLoadClass() != null){
					className=masterConfig.getLoadClass();
				}
				
				try{
					Class<?> loadedClass = ClassLoader.getSystemClassLoader().loadClass(className);
					Constructor<?> ctor = loadedClass.getConstructor(String.class,String.class,ConnectedThingClient.class,JSONObject.class);
					remoteThing = (VirtualThing) (ctor.newInstance(thingName, "Load testing simulated " + masterConfig.getThingTemplate() + " thing", client,masterConfig.getJSONObject()));
					
				}catch(Exception e){
					e.printStackTrace();
					logger.info("FATAL: failed to load class:"+masterConfig.getLoadClass()+"; Please check className setting.");
					System.exit(0);
				}
			
				if( responseCode == 200 )
				{
					//SimulatedThing remoteThing = new SimulatedThing(thingName, "Load testing simulated " + masterConfig.getThingTemplate() + " thing", client);
					//SimulatedVR8600E remoteThing = new SimulatedVR8600E(thingName, "Load testing simulated " + masterConfig.getThingTemplate() + " thing", client);
					// Bind our remote virtual thing to the platform
					logger.info("Binding thing: " + remoteThing.getName());
					client.bindThing(remoteThing);
					logger.info("Successfully registered thing " + thingName);					
				}else if(responseCode == 406){
					logger.info("Thing:"+thingName+" may exist.");
					//exists already
					//SimulatedThing remoteThing = new SimulatedThing(thingName, "Load testing simulated " + masterConfig.getThingTemplate() + " thing", client);
					//SimulatedVR8600E remoteThing = new SimulatedVR8600E(thingName, "Load testing simulated " + masterConfig.getThingTemplate() + " thing", client);
					// Bind our remote virtual thing to the platform
					logger.info("Binding existing thing: " + remoteThing.getName());
					client.bindThing(remoteThing);
					logger.info("Successfully registered existing thing " + thingName);			
				}
				else
				{
					logger.error("FATAL: Failed to register thing " + thingName + "!");
					throw new Exception("Failed to register thing " + thingName);
				}
			}
			
			// Start the edge gateway client
			client.start();
		}
		catch (Exception eStart)
		{
			logger.error("Initial Start Failed", eStart);
			client.shutdown();
		}
	
		// As long as the client has not been shutdown, continue
		while( !client.isShutdown() ) 
		{
			// Only process the Virtual Things if the client is connected
			if( client.isConnected() ) 
			{
				// Loop over all the Virtual Things and process them
				for( VirtualThing thing : client.getThings().values()) 
				{
					try 
					{
						thing.processScanRequest();
					}
					catch( Exception eProcessing ) 
					{
						logger.error("Error Processing Scan Request for [" + thing.getName() + "] : " + eProcessing.getMessage());
						logger.error("Exception", eProcessing);
					}
				}
			}
			// Suspend processing at the scan rate interval
			Thread.sleep(masterConfig.getScanRate());
		}			
	}
}
