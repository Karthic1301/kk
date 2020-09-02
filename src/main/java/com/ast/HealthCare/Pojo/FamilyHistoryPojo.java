package com.ast.HealthCare.Pojo;

public class FamilyHistoryPojo {

	//CREATE TABLE  familyhistory(FHSERIALID SERIAL,PATIENTID TEXT,
	//RELATIONNAME TEXT,RELATIONSHIP TEXT,AGE TEXT,PRIMARY KEY(FHSERIALID));
	private int fhSerialId;
	private String patientId;
	private String relationName;
	private String relationship;
	private String illness;
	

	@Override
	public String toString() {
		return "FamilyHistoryPojo [fhSerialId=" + fhSerialId + ", patientId=" + patientId + ", relationName="
				+ relationName + ", relationship=" + relationship + ", illness=" + illness + "]";
	}
	public int getFhSerialId() {
		return fhSerialId;
	}
	public void setFhSerialId(int fhSerialId) {
		this.fhSerialId = fhSerialId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getIllness() {
		return illness;
	}
	public void setIllness(String illness) {
		this.illness = illness;
	}
	
}
