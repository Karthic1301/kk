package com.ast.HealthCare.Pojo;

public class LabTestNormalDetailsPojo {
	
	private int testNormalDetailsId;
	private int testNormalId;
	private int testId;
	private String testName;
	private int testGroupId;
	private String unit;
	private String valueType;
	private double accurateValue;
	private double minValue;
	private double maxValue;
	private String userDefined;
	
	
	public String getUserDefined() {
		return userDefined;
	}
	public void setUserDefined(String userDefined) {
		this.userDefined = userDefined;
	}
	public int getTestNormalDetailsId() {
		return testNormalDetailsId;
	}
	public void setTestNormalDetailsId(int testNormalDetailsId) {
		this.testNormalDetailsId = testNormalDetailsId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	public int getTestNormalId() {
		return testNormalId;
	}
	public void setTestNormalId(int testNormalId) {
		this.testNormalId = testNormalId;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getTestGroupId() {
		return testGroupId;
	}
	public void setTestGroupId(int testGroupId) {
		this.testGroupId = testGroupId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public double getAccurateValue() {
		return accurateValue;
	}
	public void setAccurateValue(double accurateValue) {
		this.accurateValue = accurateValue;
	}
	public double getMinValue() {
		return minValue;
	}
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	public double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}
	
	
	@Override
	public String toString() {
		return "LabTestNormalDetailsPojo [testNormalDetailsId=" + testNormalDetailsId + ", testNormalId=" + testNormalId
				+ ", testId=" + testId + ", testName=" + testName + ", testGroupId=" + testGroupId + ", unit=" + unit
				+ ", valueType=" + valueType + ", accurateValue=" + accurateValue + ", minValue=" + minValue
				+ ", maxValue=" + maxValue + ",  userDefined=" + userDefined + "]";
	}
}
