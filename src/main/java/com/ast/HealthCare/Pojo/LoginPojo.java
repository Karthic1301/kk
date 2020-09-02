package com.ast.HealthCare.Pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class LoginPojo {

	// CREATE TABLE  usermaster(USERID TEXT PRIMARY KEY,USERNAME TEXT,ROLEID INT,USERPASSWORD TEXT,USERSERIALIID SERIAL,USERCATEGORY TEXT);

	String userId;
	int RoleId;
	String UserSerialId;
	String userName;
	String password;
	String userCategory;
	String mobile1;
	String mobile2;
	

	@Override
	public String toString() {
		return "LoginPojo [userId=" + userId + ", RoleId=" + RoleId + ", UserSerialId=" + UserSerialId + ", userName="
				+ userName + ", password=" + password + ", userCategory=" + userCategory + ", mobile1=" + mobile1
				+ ", mobile2=" + mobile2 + "]";
	}

	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return RoleId;
	}
	public void setRoleId(int roleId) {
		RoleId = roleId;
	}
	public String getUserSerialId() {
		return UserSerialId;
	}
	public void setUserSerialId(String userSerialId) {
		UserSerialId = userSerialId;
	}
	public String getUserCategory() {
		return userCategory;
	}
	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
