package com.ast.HealthCare.Pojo;

public class TestMasterPojo {

	private int testMasterId;
	private String testMasterName;
	private String prescriptionFlag;
	private double amount;
	private String inOutFlag;
	private int orderNo;
	
	
	
	
	
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getInOutFlag() {
		return inOutFlag;
	}
	public void setInOutFlag(String inOutFlag) {
		this.inOutFlag = inOutFlag;
	}
	public String getPrescriptionFlag() {
		return prescriptionFlag;
	}
	public void setPrescriptionFlag(String prescriptionFlag) {
		this.prescriptionFlag = prescriptionFlag;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	
	@Override
	public String toString() {
		return "TestMasterPojo [testMasterId=" + testMasterId + ", testMasterName=" + testMasterName
				+ ", prescriptionFlag=" + prescriptionFlag + ", amount=" + amount + ", inOutFlag=" + inOutFlag
				+ ", orderNo=" + orderNo + "]";
	}
	
	
	
	
}
