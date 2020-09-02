package com.ast.HealthCare.Pojo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PatientDiseasePojo {
	// CREATE TABLE patientdisease(PATIENTID INTEGER,DISEASEID
	// INTEGER,DISEASESTARTDATE DATE,DISEASEENDDATE DATE,STATUS TEXT);

	private String patientId;
	private String prescriptionNo;
	private int diseaseId;
	private String diseaseName;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date diseaseStartDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date diseaseEndDate;
	private String status;

	

	@Override
	public String toString() {
		return "PatientDiseasePojo [patientId=" + patientId + ", prescriptionNo=" + prescriptionNo + ", diseaseId="
				+ diseaseId + ", diseaseName=" + diseaseName + ", diseaseStartDate=" + diseaseStartDate
				+ ", diseaseEndDate=" + diseaseEndDate + ", status=" + status + "]";
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getPrescriptionNo() {
		return prescriptionNo;
	}

	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public int getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(int diseaseId) {
		this.diseaseId = diseaseId;
	}

	public Date getDiseaseStartDate() {
		return diseaseStartDate;
	}

	public void setDiseaseStartDate(Date diseaseStartDate) {
		this.diseaseStartDate = diseaseStartDate;
	}

	public Date getDiseaseEndDate() {
		return diseaseEndDate;
	}

	public void setDiseaseEndDate(Date diseaseEndDate) {
		this.diseaseEndDate = diseaseEndDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
