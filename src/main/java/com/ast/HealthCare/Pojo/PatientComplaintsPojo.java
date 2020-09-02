package com.ast.HealthCare.Pojo;

import java.sql.Date;

public class PatientComplaintsPojo {

	//CREATE TABLE  patientcomplaints(PRESCRIPTIONID INTEGER,
	//COMPLAINTSID INTEGER,COMPLAINTSDURATION DATE,COMPLAINTSDESCRIPTION TEXT,COMPLAINTSTYPE TEXT);
	private String prescriptionNo;
	private int complaintsId;
	private String patientId;
	private String complaintsDuration;
	private String complaintsDescription;
	private String complaintsType;
	private String complaintsName;
	
	
	@Override
	public String toString() {
		return "PatientComplaintsPojo [prescriptionNo=" + prescriptionNo + ", complaintsId=" + complaintsId
				+ ", patientId=" + patientId + ", complaintsDuration=" + complaintsDuration + ", complaintsDescription="
				+ complaintsDescription + ", complaintsType=" + complaintsType + ", complaintsName=" + complaintsName
				+ "]";
	}
	public String getComplaintsName() {
		return complaintsName;
	}
	public void setComplaintsName(String complaintsName) {
		this.complaintsName = complaintsName;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getComplaintsDuration() {
		return complaintsDuration;
	}
	public void setComplaintsDuration(String complaintsDuration) {
		this.complaintsDuration = complaintsDuration;
	}
	public String getPrescriptionNo() {
		return prescriptionNo;
	}
	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}
	public int getComplaintsId() {
		return complaintsId;
	}
	public void setComplaintsId(int complaintsId) {
		this.complaintsId = complaintsId;
	}
	
	public String getComplaintsDescription() {
		return complaintsDescription;
	}
	public void setComplaintsDescription(String complaintsDescription) {
		this.complaintsDescription = complaintsDescription;
	}
	public String getComplaintsType() {
		return complaintsType;
	}
	public void setComplaintsType(String complaintsType) {
		this.complaintsType = complaintsType;
	}
	
	
}
