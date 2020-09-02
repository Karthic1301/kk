package com.ast.HealthCare.Pojo;

public class LabTestGroupPojo {
	
	private int testGroupId;
	private String testGroup;
	
	
	public int getTestGroupId() {
		return testGroupId;
	}
	public void setTestGroupId(int testGroupId) {
		this.testGroupId = testGroupId;
	}
	public String getTestGroup() {
		return testGroup;
	}
	public void setTestGroup(String testGroup) {
		this.testGroup = testGroup;
	}
	
	
	@Override
	public String toString() {
		return "LabTestGroupPojo [testGroupId=" + testGroupId + ", testGroup=" + testGroup + "]";
	}
	
	

}
