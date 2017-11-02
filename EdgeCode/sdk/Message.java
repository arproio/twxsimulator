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
*  Name          : Message.java
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
import java.util.List;

import com.thingworx.types.primitives.ImagePrimitive;

/**
 * The Message program is to set/get the value purpose
 * to transport the data
 * @author k.jothi
 *
 */
public class Message
{
	protected boolean dataMessage;
	protected String serialNumber;
	protected Date dateTime;
	protected String model;
	protected int modelID;
	protected String friendlyName;
	protected String token;
	protected int id;
	protected Date plantCreationDateTime;
	protected String plantID;
	protected String companyID;
	protected ImagePrimitive lastImage;
	protected String messageName;	
	protected List<DataValue> valuesList;
	
	public String getSerialNumber()
	{
		return this.serialNumber;
	}
	
	public Date getDateTime()
	{
		return this.dateTime;
	}
	
	public String getModel()
	{
		return this.model;
	}
	
	public int getModelID()
	{
		return this.modelID;
	}
	
	public String getFriendlyName()
	{
		return this.friendlyName;
	}
	
	public String getToken()
	{
		return this.token;
	}
	
	public List<DataValue> getDataValues()
	{
		return this.valuesList;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public ImagePrimitive getLastImage()
	{
		return this.lastImage;
	}
	
	public String getMessageName()
	{
		return this.messageName;
	}	
	
	public Date getPlantCreationDateTime()
	{
		return this.plantCreationDateTime;
	}
	
	public String getPlantID()
	{
		return this.plantID;
	}
	
	public String getCompanyID()
	{
		return this.companyID;
	}


	public boolean isDataMessage() {
		return dataMessage;
	}


	public void setDataMessage(boolean dataMessage) {
		this.dataMessage = dataMessage;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<DataValue> getValuesList() {
		return valuesList;
	}


	public void setValuesList(List<DataValue> valuesList) {
		this.valuesList = valuesList;
	}


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public void setModelID(int modelID) {
		this.modelID = modelID;
	}


	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public void setPlantCreationDateTime(Date plantCreationDateTime) {
		this.plantCreationDateTime = plantCreationDateTime;
	}


	public void setPlantID(String plantID) {
		this.plantID = plantID;
	}


	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}


	public void setLastImage(ImagePrimitive lastImage) {
		this.lastImage = lastImage;
	}


	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

}
