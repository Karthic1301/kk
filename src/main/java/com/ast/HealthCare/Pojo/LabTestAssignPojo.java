package com.ast.HealthCare.Pojo;

import java.util.List;

public class LabTestAssignPojo {

	private int labTestId;
	private String testMasterName;
	private int testMasterId;
	private List<LabTestAssignDetailsPojo> labTestAssignDet;
	
	
	
	public int getTestMasterId() {
		return testMasterId;
	}
	public void setTestMasterId(int testMasterId) {
		this.testMasterId = testMasterId;
	}
	public void setLabTestAssignDet(List<LabTestAssignDetailsPojo> labTestAssignDet) {
		this.labTestAssignDet = labTestAssignDet;
	}
	public List<LabTestAssignDetailsPojo> getLabTestAssignDet() {
		return labTestAssignDet;
	}
	public void setLabTestAssign(List<LabTestAssignDetailsPojo> labTestAssignDet) {
		this.labTestAssignDet = labTestAssignDet;
	}
	
	
	public int getLabTestId() {
		return labTestId;
	}
	public void setLabTestId(int labTestId) {
		this.labTestId = labTestId;
	}
	public String getTestMasterName() {
		return testMasterName;
	}
	public void setTestMasterName(String testMasterName) {
		this.testMasterName = testMasterName;
	}
	
	@Override
	public String toString() {
		return "LabTestAssignPojo [labTestId=" + labTestId + ", testMasterName=" + testMasterName + ", testMasterId=" + testMasterId
				+ ", labTestAssignDet=" + labTestAssignDet + "]";
	}
	
	
}
