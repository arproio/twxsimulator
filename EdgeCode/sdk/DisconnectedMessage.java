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
*  Name          : DisconnectedMessage.java
*  Description   : This file is to set / get the value to data transfer object
*
*  Version     Date                 Author      Change Description
*  -----------------------------------------------------------------------------
*  1.0           08/07/2017     k.Jothi       Initial Version
*  
*  -----------------------------------------------------------------------------
*/
package com.remote.vjet.sdk;

import java.util.Date;

/**
 * The DisconnectedMessage program is to set/get the value purpose
 * to transport the data
 * @author k.jothi
 *
 */
public class DisconnectedMessage {
	
	//protected String serialNumbers;
	protected Date dateTime;
	protected String plantID;
	protected String companyID;
	protected String serialNumber;
	protected String token;
	
	/*public List<String> getSerialNumbers() {
		return serialNumbers;
	}
	public void setSerialNumbers(List<String> serialNumbers) {
		this.serialNumbers = serialNumbers;
	}*/
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getPlantID() {
		return plantID;
	}
	public void setPlantID(String plantID) {
		this.plantID = plantID;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
