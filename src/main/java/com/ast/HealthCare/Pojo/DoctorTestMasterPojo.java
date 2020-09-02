package com.ast.HealthCare.Pojo;

public class DoctorTestMasterPojo {

	
	private int doctorTestId;
	private String doctorId;
	private int testMasterId;
	private String testMasterName;
	private double amount;
	private int orderNo;
	
	
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public int getDoctorTestId() {
		return doctorTestId;
	}
	public void setDoctorTestId(int doctorTestId) {
		this.doctorTestId = doctorTestId;
	}
	public int getTestMasterId() {
		return testMasterId;
	}
	public void setTestMasterId(int testMasterId) {
		this.testMasterId = testMasterId;
	}
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
	@Override
	public String toString() {
		return "DoctorTestMasterPojo [doctorTestId=" + doctorTestId + ", doctorId=" + doctorId + ", testMasterId="
				+ testMasterId + ", testMasterName=" + testMasterName + ", amount=" + amount + ", orderNo=" + orderNo
				+ "]";
	}
	
	
}
