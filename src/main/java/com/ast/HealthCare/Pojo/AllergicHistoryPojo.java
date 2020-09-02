package com.ast.HealthCare.Pojo;

public class AllergicHistoryPojo {

	//CREATE TABLE allergichistory(ALLERGYID SERIAL,PATIENTID TEXT,
	//ALLERGYNAME TEXT,ALLERGYDESCRIPTION TEXT);
	
	private String patientId;
	private String allergyName;
	private String allergyDescription;
	private int allergyId;
	
	
	@Override
	public String toString() {
		return "AllergicHistoryPojo [patientId=" + patientId + ", allergyName=" + allergyName + ", allergyDescription="
				+ allergyDescription + ", allergyId=" + allergyId + "]";
	}
	
	public int getAllergyId() {
		return allergyId;
	}
	public void setAllergyId(int allergyId) {
		this.allergyId = allergyId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	public String getAllergyDescription() {
		return allergyDescription;
	}
	public void setAllergyDescription(String allergyDescription) {
		this.allergyDescription = allergyDescription;
	}
		
}
