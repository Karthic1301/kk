package com.ast.HealthCare.Pojo;

public class PatientTestPojo {

	private int patientTestId;
	private String prescriptionNo;
	private String patientId;
	private String doctorId;
	private int testMasterId;
	private boolean status;
	private String testMasterName;
	private double amount;



	public String getTestMasterName() {
		return testMasterName;
	}

	public void setTestMasterName(String testMasterName) {
		this.testMasterName = testMasterName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPrescriptionNo() {
		return prescriptionNo;
	}

	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}

	@Override
	public String toString() {
		return "PatientTestPojo [patientTestId=" + patientTestId + ", prescriptionNo=" + prescriptionNo + ", patientId="
				+ patientId + ", doctorId=" + doctorId + ", testMasterId=" + testMasterId + ", status=" + status
				+ ", testMasterName=" + testMasterName + ", amount=" + amount + "]";
	}
	
	public int getPatientTestId() {
		return patientTestId;
	}
	public void setPatientTestId(int patientTestId) {
		this.patientTestId = patientTestId;
	}
	
	public int getTestMasterId() {
		return testMasterId;
	}
	public void setTestMasterId(int testMasterId) {
		this.testMasterId = testMasterId;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
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
	
	
	
	
	
}
