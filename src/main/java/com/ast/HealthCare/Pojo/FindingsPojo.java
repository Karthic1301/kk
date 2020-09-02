package com.ast.HealthCare.Pojo;

public class FindingsPojo {

	private int findingsId;
	private String findingsName;
	private String findingsCode;
	
	@Override
	public String toString() {
		return "FindingsPojo [findingsId=" + findingsId + ", findingsName=" + findingsName + ", findingsCode="
				+ findingsCode + "]";
	}
	
	public int getFindingsId() {
		return findingsId;
	}
	public void setFindingsId(int findingsId) {
		this.findingsId = findingsId;
	}
	public String getFindingsName() {
		return findingsName;
	}
	public void setFindingsName(String findingsName) {
		this.findingsName = findingsName;
	}
	public String getFindingsCode() {
		return findingsCode;
	}
	public void setFindingsCode(String findingsCode) {
		this.findingsCode = findingsCode;
	}
	
	
}
