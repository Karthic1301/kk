package com.ast.HealthCare.Pojo;

public class LabTestReportEntryDetailsPojo {

	private int testReportEntryDetailsId;
	private int testReportEntryId;
	private int testMasterId;
	private String testMasterName;
	private int testGroupId;
	private double amount;
	private float discountPercent;
	private float discountAmount;
	private double totAmount;
	
	
	public int getTestReportEntryId() {
		return testReportEntryId;
	}
	public void setTestReportEntryId(int testReportEntryId) {
		this.testReportEntryId = testReportEntryId;
	}
	public int getTestReportEntryDetailsId() {
		return testReportEntryDetailsId;
	}
	public void setTestReportEntryDetailsId(int testReportEntryDetailsId) {
		this.testReportEntryDetailsId = testReportEntryDetailsId;
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
	public float getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}
	public float getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}
	public double getTotAmount() {
		return totAmount;
	}
	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}
	
	@Override
	public String toString() {
		return "LabTestReportEntryDetailsPojo [testReportEntryDetailsId=" + testReportEntryDetailsId
				+ ", testReportEntryId=" + testReportEntryId + ", testMasterId=" + testMasterId + ", testMasterName=" + testMasterName
				+ ", testGroupId=" + testGroupId + ", amount=" + amount + ", discountPercent=" + discountPercent
				+ ", discountAmount=" + discountAmount + ", totAmount=" + totAmount + "]";
	}
	
	
	
}
