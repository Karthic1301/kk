package com.ast.HealthCare.Pojo;

import java.util.List;

public class LabTestNormalPojo {
	
	private int testNormalId;
	private int testMasterId;
	private String testMasterName;
	private String testNormalName;
	private int testGroupId;
	private String unit;
	private String valueType;
	private double accurateValue;
	private double minValue;
	private double maxValue;
	private String detailStatus;
	private String userDefined;
	private List<LabTestNormalDetailsPojo> testnormaldetail;
	
	
	
	public String getUserDefined() {
		return userDefined;
	}
	public void setUserDefined(String userDefined) {
		this.userDefined = userDefined;
	}
	public String getTestNormalName() {
		return testNormalName;
	}
	public void setTestNormalName(String testNormalName) {
		this.testNormalName = testNormalName;
	}
	public List<LabTestNormalDetailsPojo> getTestnormaldetail() {
		return testnormaldetail;
	}
	public void setTestnormaldetail(List<LabTestNormalDetailsPojo> testnormaldetail) {
		this.testnormaldetail = testnormaldetail;
	}
	public String getTestMasterName() {
		return testMasterName;
	}
	public void setTestMasterName(String testMasterName) {
		this.testMasterName = testMasterName;
	}
	
	public int getTestNormalId() {
		return testNormalId;
	}
	public void setTestNormalId(int testNormalId) {
		this.testNormalId = testNormalId;
	}
	public int getTestMasterId() {
		return testMasterId;
	}
	public void setTestMasterId(int testMasterId) {
		this.testMasterId = testMasterId;
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
	
	public String getDetailStatus() {
		return detailStatus;
	}
	public void setDetailStatus(String detailStatus) {
		this.detailStatus = detailStatus;
	}
	
	@Override
	public String toString() {
		return "LabTestNormalPojo [testNormalId=" + testNormalId + ", testMasterId=" + testMasterId
				+ ", testMasterName=" + testMasterName + ", testNormalName=" + testNormalName + ", testGroupId="
				+ testGroupId + ", unit=" + unit + ", valueType=" + valueType + ", accurateValue=" + accurateValue
				+ ", minValue=" + minValue + ", maxValue=" + maxValue + ", detailStatus="
				+ detailStatus + ", userDefined=" + userDefined + ", testnormaldetail=" + testnormaldetail + "]";
	}
	
	
	
	
	

}
