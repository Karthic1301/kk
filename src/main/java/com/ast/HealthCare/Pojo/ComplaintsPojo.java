package com.ast.HealthCare.Pojo;

public class ComplaintsPojo {

	//CREATE TABLE  complaintsmaster(complaintsID SERIAL,
	//complaintsNAME TEXT,complaintsCODE TEXT,PRIMARY KEY(complaintsID));
	private int complaintsId;
	private String complaintsName;
	private String complaintsCode;
	
	
	@Override
	public String toString() {
		return "complaintsPojo [complaintsId=" + complaintsId + ", complaintsName=" + complaintsName
				+ ", complaintsCode=" + complaintsCode + "]";
	}
	
	public int getcomplaintsId() {
		return complaintsId;
	}
	public void setcomplaintsId(int complaintsId) {
		this.complaintsId = complaintsId;
	}
	public String getcomplaintsName() {
		return complaintsName;
	}
	public void setcomplaintsName(String complaintsName) {
		this.complaintsName = complaintsName;
	}
	public String getcomplaintsCode() {
		return complaintsCode;
	}
	public void setcomplaintsCode(String complaintsCode) {
		this.complaintsCode = complaintsCode;
	}
	
}
