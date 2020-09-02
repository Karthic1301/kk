package com.ast.HealthCare.Pojo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SurgicalHistoryPojo {

	//CREATE TABLE  surgicalhistory(SURGERYID SERIAL,PATIENTID TEXT,SURGERYNAME TEXT,
	//SURGERYDATE DATE,SURGERYDESCRIPTION TEXT);
	private int surgeryId;
	private String patientId;
	private String surgeryName;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date surgeryDate;
	private String surgeryDescription;
	
	@Override
	public String toString() {
		return "SurgicalHistoryPojo [surgeryId=" + surgeryId + ", patientId=" + patientId + ", surgeryName="
				+ surgeryName + ", surgeryDate=" + surgeryDate + ", surgeryDescription=" + surgeryDescription + "]";
	}
	
	public int getSurgeryId() {
		return surgeryId;
	}
	public void setSurgeryId(int surgeryId) {
		this.surgeryId = surgeryId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}
	public Date getSurgeryDate() {
		return surgeryDate;
	}
	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}
	public String getSurgeryDescription() {
		return surgeryDescription;
	}
	public void setSurgeryDescription(String surgeryDescription) {
		this.surgeryDescription = surgeryDescription;
	}
	
}
