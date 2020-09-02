package com.ast.HealthCare.Pojo;

import java.sql.Date;

public class PatientFindingsPojo {
	
	//patientfindings(PATIENTID INTEGER,DISEASEID INTEGER,
	//DISEASESTARTDATE DATE,DISEASEENDDATE DATE,STATUS TEXT);
	private String patientId;
	private String prescriptionNo;
	private int findingsId;
	private String findingsName;
	private String findingsDescription;
	
	
	
	@Override
	public String toString() {
		return "PatientFindingsPojo [patientId=" + patientId + ", prescriptionNo=" + prescriptionNo + ", findingsId="
				+ findingsId + ", findingsName=" + findingsName + ", findingsDescription=" + findingsDescription + "]";
	}
	public String getFindingsName() {
		return findingsName;
	}
	public void setFindingsName(String findingsName) {
		this.findingsName = findingsName;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPrescriptionNo() {
		return prescriptionNo;
	}
	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}
	public int getFindingsId() {
		return findingsId;
	}
	public void setFindingsId(int findingsId) {
		this.findingsId = findingsId;
	}
	public String getFindingsDescription() {
		return findingsDescription;
	}
	public void setFindingsDescription(String findingsDescription) {
		this.findingsDescription = findingsDescription;
	}
	
	
		
}
