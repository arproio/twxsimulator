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
*  Name          : EventMessage.java
*  Description   : This file is to set / get the value to event transfer object
*
*  Version     Date                 Author      Change Description
*  -----------------------------------------------------------------------------
*  1.0           09/09/2017     k.Jothi       Initial Version
*  
*  -----------------------------------------------------------------------------
*/
package com.remote.vjet.sdk;

import java.util.Date;
import java.util.HashMap;

/**
 * The EventMessage program is to set/get the value purpose
 * to transport the data
 * @author k.jothi
 *
 */
public class EventMessage {
	
	protected Date eventDateTime;
	protected HashMap<String,String> events;
	
	protected String serialNo;
	
	public Date getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public HashMap<String, String> getEvents() {
		return events;
	}
	public void setEvents(HashMap<String, String> events) {
		this.events = events;
	}
	
	
	
}
