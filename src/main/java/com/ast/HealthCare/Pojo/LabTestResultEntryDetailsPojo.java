package com.ast.HealthCare.Pojo;

public class LabTestResultEntryDetailsPojo {

	private int testResultDetailsid;
	private int testResultId;
	private int testMasterId;
	private String testMasterName;
	private int testTypeId;
	private String testTypeName;
	private int testTypeDetailsId;
	private String testTypeDetails;
	private String result;
	private String normalValue;
	
	
	public String getNormalValue() {
		return normalValue;
	}
	public void setNormalValue(String normalValue) {
		this.normalValue = normalValue;
	}
	public int getTestResultDetailsid() {
		return testResultDetailsid;
	}
	public void setTestResultDetailsid(int testResultDetailsid) {
		this.testResultDetailsid = testResultDetailsid;
	}
	public int getTestResultId() {
		return testResultId;
	}
	public void setTestResultId(int testResultId) {
		this.testResultId = testResultId;
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
	public int getTestTypeId() {
		return testTypeId;
	}
	public void setTestTypeId(int testTypeId) {
		this.testTypeId = testTypeId;
	}
	public String getTestTypeName() {
		return testTypeName;
	}
	public void setTestTypeName(String testTypeName) {
		this.testTypeName = testTypeName;
	}
	public int getTestTypeDetailsId() {
		return testTypeDetailsId;
	}
	public void setTestTypeDetailsId(int testTypeDetailsId) {
		this.testTypeDetailsId = testTypeDetailsId;
	}
	public String getTestTypeDetails() {
		return testTypeDetails;
	}
	public void setTestTypeDetails(String testTypeDetails) {
		this.testTypeDetails = testTypeDetails;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "LabTestResultEntryDetailsPojo [testResultDetailsid=" + testResultDetailsid + ", testResultId="
				+ testResultId + ", testMasterId=" + testMasterId + ", testMasterName=" + testMasterName
				+ ", testTypeId=" + testTypeId + ", testTypeName=" + testTypeName + ", testTypeDetailsId="
				+ testTypeDetailsId + ", testTypeDetails=" + testTypeDetails + ", result=" + result + ", normalValue="
				+ normalValue + "]";
	}
	
	
	
}
