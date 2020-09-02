package com.ast.HealthCare.Pojo;

public class LabTestAssignDetailsPojo {

	private int labTestDetailsId;
	private int labTestId;
	private int testId;
	private String testName;
	private int testGroupId;
	private int priority;
	
	public int getLabTestDetailsId() {
		return labTestDetailsId;
	}
	public void setLabTestDetailsId(int labTestDetailsId) {
		this.labTestDetailsId = labTestDetailsId;
	}
	public int getLabTestId() {
		return labTestId;
	}
	public void setLabTestId(int labTestId) {
		this.labTestId = labTestId;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public int getTestGroupId() {
		return testGroupId;
	}
	public void setTestGroupId(int testGroupId) {
		this.testGroupId = testGroupId;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "LabTestAssignDetailsPojo [labTestDetailsId=" + labTestDetailsId + ", labTestId=" + labTestId
				+ ", testId=" + testId + ", testName=" + testName + ", testGroupId=" + testGroupId + ", priority="
				+ priority + "]";
	}
	
	
	
	
}
