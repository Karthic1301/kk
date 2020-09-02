package com.ast.HealthCare.Pojo;

public class LabTestMasterPojo {

	private int testMasterId;
	private String testMasterName;
	private int testGroupId;
	private double amount;
	
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
	public int getTestGroupId() {
		return testGroupId;
	}
	public void setTestGroupId(int testGroupId) {
		this.testGroupId = testGroupId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "LabTestMasterPojo [testId=" + testMasterId + ", testMasterName=" + testMasterName + ", testGroupId=" + testGroupId
				+ ", amount=" + amount + "]";
	}
	
	
	
}
