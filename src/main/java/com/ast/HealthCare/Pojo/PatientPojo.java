package com.ast.HealthCare.Pojo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PatientPojo {

//	CREATE TABLE patientmaster(PATIENTID INTEGER,PATIENTFIRSTNAME TEXT,PATIENTMIDDLENAME TEXT,PATIENTLASTNAME TEXT,
//	PATIENTGENDER TEXT,PATIENTDATEOFBIRTH DATE,PATIENTBLOODGROUP TEXT,PATIENTADDRESS1 TEXT,PATIENTADDRESS2 TEXT,
//	PATIENTMOBILE1 TEXT,PATIENTMOBILE2 TEXT,HOSPITALCODE TEXT,YEAR SMALLINT,PATIENTSERIALID TEXT,PATIENTPHOTO BYTEA,
//	PATIENTRELATIONNAME TEXT,PATIENTRELATIONSHIP TEXT,PATIENTRELATIONID TEXT,PATIENTMAILID TEXT,PATIENTUNIQUEID TEXT,PRIMARY KEY(PATIENTID));

	private String patientId;
	private String patientFirstName;
	private String patientMiddleName;
	private String patientLastName;
	private String patientGender;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date patientDOB;
	private String patientBloodGroup;
	private String patientAddress1;
	private String patientAddress2;
	private String patientMobile1;
	private String patientMobile2;
	private String patientHospitalCode;
	private long patientSerialId;
	private String patientPhoto;
	private String patientRelationName;
	private String patientRelationship;
	private String patientRelationId;
	private String patientMailId;
	private String patientUniqueId;
	private String patientUserName;
	private String patientPassword;
	private Date patientRegisterDate;
	private int patientAge;
	
	
	
	
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	@Override
	public String toString() {
		return "PatientPojo [patientId=" + patientId + ", patientFirstName=" + patientFirstName + ", patientMiddleName="
				+ patientMiddleName + ", patientLastName=" + patientLastName + ", patientGender=" + patientGender
				+ ", patientDOB=" + patientDOB + ", patientBloodGroup=" + patientBloodGroup + ", patientAddress1="
				+ patientAddress1 + ", patientAddress2=" + patientAddress2 + ", patientMobile1=" + patientMobile1
				+ ", patientMobile2=" + patientMobile2 + ", patientHospitalCode=" + patientHospitalCode
				+ ", patientSerialId=" + patientSerialId + ", patientPhoto=" + patientPhoto + ", patientRelationName="
				+ patientRelationName + ", patientRelationship=" + patientRelationship + ", patientRelationId="
				+ patientRelationId + ", patientMailId=" + patientMailId + ", patientUniqueId=" + patientUniqueId
				+ ", patientUserName=" + patientUserName + ", patientPassword=" + patientPassword
				+ ", patientRegisterDate=" + patientRegisterDate + ", patientAge=" + patientAge + "]";
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientMiddleName() {
		return patientMiddleName;
	}
	public void setPatientMiddleName(String patientMiddleName) {
		this.patientMiddleName = patientMiddleName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public Date getPatientDOB() {
		return patientDOB;
	}
	public void setPatientDOB(Date patientDOB) {
		this.patientDOB = patientDOB;
	}
	public String getPatientBloodGroup() {
		return patientBloodGroup;
	}
	public void setPatientBloodGroup(String patientBloodGroup) {
		this.patientBloodGroup = patientBloodGroup;
	}
	public String getPatientAddress1() {
		return patientAddress1;
	}
	public void setPatientAddress1(String patientAddress1) {
		this.patientAddress1 = patientAddress1;
	}
	public String getPatientAddress2() {
		return patientAddress2;
	}
	public void setPatientAddress2(String patientAddress2) {
		this.patientAddress2 = patientAddress2;
	}
	public String getPatientMobile1() {
		return patientMobile1;
	}
	public void setPatientMobile1(String patientMobile1) {
		this.patientMobile1 = patientMobile1;
	}
	public String getPatientMobile2() {
		return patientMobile2;
	}
	public void setPatientMobile2(String patientMobile2) {
		this.patientMobile2 = patientMobile2;
	}
	public String getPatientHospitalCode() {
		return patientHospitalCode;
	}
	public void setPatientHospitalCode(String patientHospitalCode) {
		this.patientHospitalCode = patientHospitalCode;
	}
	public long getPatientSerialId() {
		return patientSerialId;
	}
	public void setPatientSerialId(long patientSerialId) {
		this.patientSerialId = patientSerialId;
	}
	public String getPatientPhoto() {
		return patientPhoto;
	}
	public void setPatientPhoto(String patientPhoto) {
		this.patientPhoto = patientPhoto;
	}
	public String getPatientRelationName() {
		return patientRelationName;
	}
	public void setPatientRelationName(String patientRelationName) {
		this.patientRelationName = patientRelationName;
	}
	public String getPatientRelationship() {
		return patientRelationship;
	}
	public void setPatientRelationship(String patientRelationship) {
		this.patientRelationship = patientRelationship;
	}
	public String getPatientRelationId() {
		return patientRelationId;
	}
	public void setPatientRelationId(String patientRelationId) {
		this.patientRelationId = patientRelationId;
	}
	public String getPatientMailId() {
		return patientMailId;
	}
	public void setPatientMailId(String patientMailId) {
		this.patientMailId = patientMailId;
	}
	public String getPatientUniqueId() {
		return patientUniqueId;
	}
	public void setPatientUniqueId(String patientUniqueId) {
		this.patientUniqueId = patientUniqueId;
	}
	public String getPatientUserName() {
		return patientUserName;
	}
	public void setPatientUserName(String patientUserName) {
		this.patientUserName = patientUserName;
	}
	public String getPatientPassword() {
		return patientPassword;
	}
	public void setPatientPassword(String patientPassword) {
		this.patientPassword = patientPassword;
	}
	public Date getPatientRegisterDate() {
		return patientRegisterDate;
	}
	public void setPatientRegisterDate(Date patientRegisterDate) {
		this.patientRegisterDate = patientRegisterDate;
	}
	

}