package com.blueice.domain;

import java.io.Serializable;
import com.sun.jmx.snmp.Timestamp;

/**
 *资源类. 
 */

public class Resource implements Serializable{
	private int id;
	private String uuidName;
	private String realName;
	private String savePath;
	private String ip;
	private Timestamp uploadTimes;
	private String description;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuidName() {
		return uuidName;
	}
	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String reslName) {
		this.realName = reslName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Timestamp getUploadTimes() {
		return uploadTimes;
	}
	public void setUploadTimes(Timestamp uploadTimes) {
		this.uploadTimes = uploadTimes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
