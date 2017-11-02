package com.remote.vjet.sdk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLErrorHandler implements ErrorHandler 
{
	private final static Logger LOGGER = LoggerFactory.getLogger(XMLErrorHandler.class);
	private StringBuilder message;
	
	public XMLErrorHandler()
	{
		message = new StringBuilder();
	}
	public void error(SAXParseException se) throws SAXException 
	{
		message.append("ERROR: " + MasterClientConfigurator.NL); 
        printError(se);
	}

	public void fatalError(SAXParseException se) throws SAXException 
	{
        message.append("FATAL: " + MasterClientConfigurator.NL); 
        printError(se);
	}

	public void warning(SAXParseException se) throws SAXException 
	{
        message.append("WARNING: " + MasterClientConfigurator.NL); 
        printError(se);
	}
    
	private void printError(SAXParseException e)
    {
		// Construct the message summary
		if( e.getSystemId().length() > 6 )
		{
			// Assumes that system id starts with file://
			message.append(e.getSystemId().substring(6) + MasterClientConfigurator.NL);
		}
		else
		{
			message.append(e.getSystemId() + MasterClientConfigurator.NL);
		}
		message.append("Line number: " + e.getLineNumber() + MasterClientConfigurator.NL);
		message.append("Column number: " + e.getColumnNumber() + MasterClientConfigurator.NL);
		message.append("Message: " + e.getMessage() + MasterClientConfigurator.NL);

		// Log the error...
		LOGGER.error(message.toString());
				
		// Terminate execution
		//System.exit(128);
     }
}
