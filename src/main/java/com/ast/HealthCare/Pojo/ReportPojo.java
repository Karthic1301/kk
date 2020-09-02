package com.ast.HealthCare.Pojo;

import java.sql.Date;

public class ReportPojo {

	private int ruid;
	private String prescriptionNo;
	private Date prescriptionDate;
	private String patientId;
	private String patientName;
	private String doctorName;
	private String patientGender;
	private Date patientAge;
	private float dia;
	
	@Override
	public String toString() {
		return "ReportPojo [ruid=" + ruid + ", prescriptionNo=" + prescriptionNo + ", prescriptionDate="
				+ prescriptionDate + ", patientId=" + patientId + ", patientName=" + patientName + ", doctorName="
				+ doctorName + ", patientGender=" + patientGender + ", patientAge=" + patientAge + ", dia=" + dia + "]";
	}
	public int getRuid() {
		return ruid;
	}
	public void setRuid(int ruid) {
		this.ruid = ruid;
	}
	public String getPrescriptionNo() {
		return prescriptionNo;
	}
	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}
	public Date getPrescriptionDate() {
		return prescriptionDate;
	}
	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public Date getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(Date patientAge) {
		this.patientAge = patientAge;
	}
	public float getDia() {
		return dia;
	}
	public void setDia(float dia) {
		this.dia = dia;
	}
	
}
