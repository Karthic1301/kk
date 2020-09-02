package com.ast.HealthCare.Pojo;

public class SmsChildPojo {
	
	private String mobileno;
	private String userid;
	private int smsmasterid;
	private String username;
	private String usercategory;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsercategory() {
		return usercategory;
	}
	public void setUsercategory(String usercategory) {
		this.usercategory = usercategory;
	}
	public int getSmsmasterid() {
		return smsmasterid;
	}
	public void setSmsmasterid(int smsmasterid) {
		this.smsmasterid = smsmasterid;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return "SmsChildPojo [mobileno=" + mobileno + ", userid=" + userid + ", smsmasterid=" + smsmasterid
				+ ", username=" + username + ", usercategory=" + usercategory + "]";
	}

}
