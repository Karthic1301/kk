package com.ast.HealthCare.Pojo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PrescriptionMasterPojo {

	//CREATE TABLE prescriptionmaster(PRESCRIPTIONID SERIAL,PRESCRIPTIONNO TEXT,PATIENTID TEXT,DOCTORID TEXT,
	//CONSULTINGDATE DATE,AGE INTEGER,PRIMARY KEY(PRESCRIPTIONID));

	private int prescriptionId;
	private String prescriptionNo;
	private String patientId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date nextReviewDate;
	private String doctorId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy") 
	private Date consultingDate;
	private int patientAge;
	private int billId;
	private PatientPojo patientPojo = new PatientPojo();
	private VitalInfoPojo vitalPojo = new VitalInfoPojo();
	
	
	
	
	
	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public VitalInfoPojo getVitalPojo() {
		return vitalPojo;
	}

	public void setVitalPojo(VitalInfoPojo vitalPojo) {
		this.vitalPojo = vitalPojo;
	}

	public PatientPojo getPatientPojo() {
		return patientPojo;
	}

	public void setPatientPojo(PatientPojo patientPojo) {
		this.patientPojo = patientPojo;
	}

	@Override
	public String toString() {
		return "PrescriptionMasterPojo [prescriptionId=" + prescriptionId + ", prescriptionNo=" + prescriptionNo
				+ ", patientId=" + patientId + ", nextReviewDate=" + nextReviewDate + ", doctorId=" + doctorId
				+ ", consultingDate=" + consultingDate + ", patientAge=" + patientAge + ", billId=" + billId
				+ ", patientPojo=" + patientPojo + ", vitalPojo=" + vitalPojo + "]";
	}
	
	public Date getNextReviewDate() {
		return nextReviewDate;
	}
	public void setNextReviewDate(Date nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}
	public int getPrescriptionId() {
		return prescriptionId;
	}
	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
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
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public Date getConsultingDate() {
		return consultingDate;
	}
	public void setConsultingDate(Date consultingDate) {
		this.consultingDate = consultingDate;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	
}
