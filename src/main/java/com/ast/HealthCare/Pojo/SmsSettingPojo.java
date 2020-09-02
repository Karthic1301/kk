package com.ast.HealthCare.Pojo;

public class SmsSettingPojo {
	
	//CREATE TABLE smssetting(SETTINGID SERIAL, SMSNAME TEXT, SMSPASSWORD TEXT, SMSURL TEXT, 
	//APIKEY TEXT, SMSTO TEXT,SMSSENDER TEXT, MESSAGE TEXT,PRIMARY KEY(SETTINGID));
	private String smsname;
	private String smspassword;
	private String smsurl;
	private String apikey;
	private String smsto;
	private String smssender;
	private String message;
	
	@Override
	public String toString() {
		return "SmsSettingDao [smsname=" + smsname + ", smspassword=" + smspassword + ", smsurl=" + smsurl + ", apikey="
				+ apikey + ", smsto=" + smsto + ", smssender=" + smssender + ", message=" + message + "]";
	}
	
	public String getSmsname() {
		return smsname;
	}
	public void setSmsname(String smsname) {
		this.smsname = smsname;
	}
	public String getSmspassword() {
		return smspassword;
	}
	public void setSmspassword(String smspassword) {
		this.smspassword = smspassword;
	}
	public String getSmsurl() {
		return smsurl;
	}
	public void setSmsurl(String smsurl) {
		this.smsurl = smsurl;
	}
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public String getSmsto() {
		return smsto;
	}
	public void setSmsto(String smsto) {
		this.smsto = smsto;
	}
	public String getSmssender() {
		return smssender;
	}
	public void setSmssender(String smssender) {
		this.smssender = smssender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	


}
