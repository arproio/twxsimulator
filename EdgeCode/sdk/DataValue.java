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

import java.util.Date;

import com.thingworx.types.primitives.BlobPrimitive;
/**
 * <h1>Value object</h1> The DataValue program is to set/get the value purpose
 * to transport the data
 * 
 * @author K.Jothi
 * @version 1.0
 * @since 2014-06-12
 */
public class DataValue
{
	private String dataTagID;
	private String dataType;
	private String stringValue;
	private Date dateValue;
	private int intValue;
	private long longValue;
	private double numberValue;
	private BlobPrimitive blobValue;

	public String getDataTagID()
	{
		return this.dataTagID;
	}

	public String getDataType()
	{
		return this.dataType;
	}

	public String getStringValue()
	{
		return this.stringValue;
	}

	public Date getDateValue()
	{
		return this.dateValue;
	}

	public int getIntValue()
	{
		return this.intValue;
	}

	public long getLongValue()
	{
		return this.longValue;
	}

	public double getNumberValue()
	{
		return this.numberValue;
	}

	public BlobPrimitive getBlobValue()
	{
		return this.blobValue;
	}
	public void setDataTagID(String dataTagID) {
		this.dataTagID = dataTagID;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public void setNumberValue(double numberValue) {
		this.numberValue = numberValue;
	}

	public void setBlobValue(BlobPrimitive blobValue) {
		this.blobValue = blobValue;
	}
	
}
