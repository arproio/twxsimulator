package com.remote.vjet.sdk;

import java.io.File;
import java.io.FileFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class XMLFileFilter implements FileFilter 
{
	//private final static Logger LOGGER = LoggerFactory.getLogger(XMLFileFilter.class);
	private String gatewayType;
	private List<String> filePrefixList;
	private String ext;
	
	// Custom Comparator to sort the xml data files in order
    public static final Comparator<File> XML_ORDER = new Comparator<File>()
    { 
    	public int compare(File f1, File f2) 
    	{
    		SimpleDateFormat dtFormatWin = new SimpleDateFormat("yyyy-MM-dd'T'HH^mm^ss.SSSSSSS'Z'");
    		dtFormatWin.setTimeZone(TimeZone.getTimeZone("UTC"));

    		SimpleDateFormat dtFormatPi = new SimpleDateFormat("yyyyMMddHHmmss");
    		dtFormatPi.setTimeZone(TimeZone.getTimeZone("UTC"));

    		Date dt1 = new Date();
    		Date dt2 = new Date();
			try
			{
				String gatewayType = MasterClientConfigurator.getInstance(null).getGatewayType();
				if( gatewayType.equalsIgnoreCase("windows") )
				{
					dt1 = dtFormatWin.parse(f1.getName().split("_")[2]);
					dt2 = dtFormatWin.parse(f2.getName().split("_")[2]);
				}
				else
				{
					// Assuming pi file format
					dt1 = dtFormatPi.parse(f1.getName().split("-")[0]);
					dt2 = dtFormatPi.parse(f2.getName().split("-")[0]);
					
				}
			}
			catch (ParseException e)
			{
				//LOGGER.warn("File format is wrong Parsing may fails here");
			}

    		return dt1.compareTo(dt2);
    	}
    };
    
	public XMLFileFilter(String prefix, String ext,int delay)
	{
		gatewayType = MasterClientConfigurator.getInstance(null).getGatewayType();
		this.filePrefixList = new ArrayList<String>();
		this.ext = ext;
		//this.delay=delay;
		
		if( null != prefix )
		{
			String[] tokens = prefix.split(",");
			for( int i = 0; i < tokens.length; i++ )
			{
				filePrefixList.add(tokens[i].trim());
			}
		}
	}
	
	public boolean accept(File file)
	{
		if( file.isDirectory() ){ return false; }

		// Check to see if a delay is in effect
		/*if( delay > 0 )
		{
    		SimpleDateFormat dtFormatWin = new SimpleDateFormat("yyyy-MM-dd'T'HH^mm^ss.SSSSSSS'Z'");
    		dtFormatWin.setTimeZone(TimeZone.getTimeZone("UTC"));

    		SimpleDateFormat dtFormatPi = new SimpleDateFormat("yyyyMMddHHmmss");
    		dtFormatPi.setTimeZone(TimeZone.getTimeZone("UTC"));

			Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			now.add(Calendar.MINUTE, (-1*delay));
			try
			{
				if( gatewayType.equalsIgnoreCase("windows") )
				{
					Date dt = dtFormatWin.parse(file.getName().split("_")[2]);
					if( dt.after(now.getTime()) ){ return false; }
				}
				else
				{
					// Assuming pi file format
					Date dt = dtFormatPi.parse(file.getName().split("-")[0]);
					if( dt.after(now.getTime()) ){ return false; }
				}
			}
			catch(ParseException pe)
			{
				logger.error("ParseException", pe);
			}
		}*/
		
		// First check to see if a prefix must be employed here
		if( null != filePrefixList && filePrefixList.size() > 0 )
		{			
			// Return files with prefix...
			for( String filePrefix : filePrefixList )
			{
				if( gatewayType.equalsIgnoreCase("windows") )
				{
					if( file.getName().startsWith(filePrefix) )
					{
						// ...and that end with .xml			
						return acceptXMLFiles(file);
					}
				}
				else
				{
					String prefixToken = file.getName().split("_")[1] + '_';					
					if( prefixToken.equals(filePrefix) )
					{
						// ...and that end with .xml			
						return acceptXMLFiles(file);
					}
				}
			}

			// Not a valid prefix, this file is ignored!
			return false;
		}
		else
		{
			// No prefix in use, just return files ending with .xml
			return acceptXMLFiles(file);	
		}
	}
		
	private boolean acceptXMLFiles(File file)
	{
		String fileExt = file.getName();
		if( fileExt.lastIndexOf('.') > 0 && fileExt.lastIndexOf('.') != fileExt.length()-1 )
		{
			fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
		}
		return fileExt.equalsIgnoreCase(ext);		
	}	
}
