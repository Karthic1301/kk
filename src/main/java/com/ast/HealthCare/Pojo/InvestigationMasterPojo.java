package com.ast.HealthCare.Pojo;

public class InvestigationMasterPojo {

	//CREATE TABLE  investigationmaster(INVESTIGATIONID SERIAL,INVESTIGATIONNAME TEXT,
	//INVESTIGATIONDURATION TEXT,INVESTIGATIONCODE TEXT,PRIMARY KEY(INVESTIGATIONID));
	private int investigationId;
	private String investigationName;
	private String investigationDuration;
	private String investigationCode;
	
	@Override
	public String toString() {
		return "InvestigationMasterPojo [investigationId=" + investigationId + ", investigationName="
				+ investigationName + ", investigationDuration=" + investigationDuration + ", investigationCode="
				+ investigationCode + "]";
	}
	
	public int getInvestigationId() {
		return investigationId;
	}
	public void setInvestigationId(int investigationId) {
		this.investigationId = investigationId;
	}
	public String getInvestigationName() {
		return investigationName;
	}
	public void setInvestigationName(String investigationName) {
		this.investigationName = investigationName;
	}
	public String getInvestigationDuration() {
		return investigationDuration;
	}
	public void setInvestigationDuration(String investigationDuration) {
		this.investigationDuration = investigationDuration;
	}
	public String getInvestigationCode() {
		return investigationCode;
	}
	public void setInvestigationCode(String investigationCode) {
		this.investigationCode = investigationCode;
	}
	
}
