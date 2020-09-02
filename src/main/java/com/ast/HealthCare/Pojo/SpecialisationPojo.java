package com.ast.HealthCare.Pojo;

public class SpecialisationPojo {

	///CREATE TABLE specialisationmaster(SPECIALISATIONID SERIAL,
	//SPECIALISATIONNAME TEXT,SPECIALISATIONCODE TEXT,PRIMARY KEY(SPECIALISATIONID));
	
	private int specialisationId;
	private String specialisationName;
	private String specialisationCode;
	
	@Override
	public String toString() {
		return "SpecialisationPojo [specialisationId=" + specialisationId + ", specialisationName=" + specialisationName
				+ ", specialisationCode=" + specialisationCode + "]";
	}
	public int getSpecialisationId() {
		return specialisationId;
	}
	public void setSpecialisationId(int specialisationId) {
		this.specialisationId = specialisationId;
	}
	public String getSpecialisationName() {
		return specialisationName;
	}
	public void setSpecialisationName(String specialisationName) {
		this.specialisationName = specialisationName;
	}
	public String getSpecialisationCode() {
		return specialisationCode;
	}
	public void setSpecialisationCode(String specialisationCode) {
		this.specialisationCode = specialisationCode;
	}

}
