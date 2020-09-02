package com.ast.HealthCare.Pojo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StaffPojo {

//	CREATE TABLE StaffMaster(STAFFID TEXT,STAFFNAME TEXT,STAFFMOBILE1 TEXT,STAFFMOBILE2 TEXT,STAFFADDRESS1 TEXT,STAFFDDRESS2 TEXT,
//	STAFFGENDER TEXT,STAFFDATEOFBIRTH DATE,STAFFEMAILID TEXT,STAFFSERIALID SERIAL,STAFFPHOTO TEXT,STAFFUNIQUEID TEXT,PRIMARY KEY (STAFFID));

	String staffId;
	String staffName;
	String staffMobile1;
	String staffMobile2;
	String staffAddress1;
	String staffAddress2;
	String staffGender;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy") 
	private Date staffDOB;
	String staffMailId;
	int staffSerialId;
	String staffphoto;
	String staffUniqueId;
	String staffQualification;
	String staffUserName;
	String staffPassword;
	
	@Override
	public String toString() {
		return "StaffPojo [staffId=" + staffId + ", staffName=" + staffName + ", staffMobile1=" + staffMobile1
				+ ", staffMobile2=" + staffMobile2 + ", staffAddress1=" + staffAddress1 + ", staffAddress2="
				+ staffAddress2 + ", staffGender=" + staffGender + ", staffDOB=" + staffDOB + ", staffMailId="
				+ staffMailId + ", staffSerialId=" + staffSerialId + ", staffphoto=" + staffphoto + ", staffUniqueId="
				+ staffUniqueId + ", staffQualification=" + staffQualification + ", staffUserName=" + staffUserName
				+ ", staffPassword=" + staffPassword + "]";
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffMobile1() {
		return staffMobile1;
	}
	public void setStaffMobile1(String staffMobile1) {
		this.staffMobile1 = staffMobile1;
	}
	public String getStaffMobile2() {
		return staffMobile2;
	}
	public void setStaffMobile2(String staffMobile2) {
		this.staffMobile2 = staffMobile2;
	}
	public String getStaffAddress1() {
		return staffAddress1;
	}
	public void setStaffAddress1(String staffAddress1) {
		this.staffAddress1 = staffAddress1;
	}
	public String getStaffAddress2() {
		return staffAddress2;
	}
	public void setStaffAddress2(String staffAddress2) {
		this.staffAddress2 = staffAddress2;
	}
	public String getStaffGender() {
		return staffGender;
	}
	public void setStaffGender(String staffGender) {
		this.staffGender = staffGender;
	}
	public Date getStaffDOB() {
		return staffDOB;
	}
	public void setStaffDOB(@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy") Date staffDOB) {
		this.staffDOB = staffDOB;
	}
	public String getStaffMailId() {
		return staffMailId;
	}
	public void setStaffMailId(String staffMailId) {
		this.staffMailId = staffMailId;
	}
	public int getStaffSerialId() {
		return staffSerialId;
	}
	public void setStaffSerialId(int staffSerialId) {
		this.staffSerialId = staffSerialId;
	}
	public String getStaffphoto() {
		return staffphoto;
	}
	public void setStaffphoto(String staffphoto) {
		this.staffphoto = staffphoto;
	}
	public String getStaffUniqueId() {
		return staffUniqueId;
	}
	public void setStaffUniqueId(String staffUniqueId) {
		this.staffUniqueId = staffUniqueId;
	}
	public String getStaffQualification() {
		return staffQualification;
	}
	public void setStaffQualification(String staffQualification) {
		this.staffQualification = staffQualification;
	}
	public String getStaffUserName() {
		return staffUserName;
	}
	public void setStaffUserName(String staffUserName) {
		this.staffUserName = staffUserName;
	}
	public String getStaffPassword() {
		return staffPassword;
	}
	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}

}
