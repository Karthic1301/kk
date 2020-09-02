package com.ast.HealthCare.Pojo;

public class PatientNotesPojo {
//	CREATE TABLE  patientnotes(PRESCRIPTIONID INTEGER,NOTESID INTEGER,NOTESDESCRIPTION TEXT);
	private String patientId;
	private String prescriptionNo;
	private int notesId;
	private String notesName;
	private String notesDescripition;
	
	
	@Override
	public String toString() {
		return "PatientNotesPojo [patientId=" + patientId + ", prescriptionNo=" + prescriptionNo + ", notesId="
				+ notesId + ", notesName=" + notesName + ", notesDescripition=" + notesDescripition + "]";
	}
	public String getNotesName() {
		return notesName;
	}
	public void setNotesName(String notesName) {
		this.notesName = notesName;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPrescriptionNo() {
		return prescriptionNo;
	}
	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}
	public int getNotesId() {
		return notesId;
	}
	public void setNotesId(int notesId) {
		this.notesId = notesId;
	}
	public String getNotesDescripition() {
		return notesDescripition;
	}
	public void setNotesDescripition(String notesDescripition) {
		this.notesDescripition = notesDescripition;
	}
	
	
}
