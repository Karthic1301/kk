package com.ast.HealthCare.Pojo;

public class PatientDiagnosisPojo {

	//patientdiagnosis(PRESCRIPTIONID INTEGER,PATIENTID INTEGER,DIAGNOSISID INTEGER,DIAGNOSISDESCRIPTION TEXT);
	private String prescriptionNo;
	private String patientId;
	private int diagnosisId;
	private String diagnosisName;
	private String diagnosisDescription;
	
	
	@Override
	public String toString() {
		return "PatientDiagnosisPojo [prescriptionNo=" + prescriptionNo + ", patientId=" + patientId + ", diagnosisId="
				+ diagnosisId + ", diagnosisName=" + diagnosisName + ", diagnosisDescription=" + diagnosisDescription
				+ "]";
	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public String getPrescriptionNo() {
		return prescriptionNo;
	}
	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public int getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	public String getDiagnosisDescription() {
		return diagnosisDescription;
	}
	public void setDiagnosisDescription(String diagnosisDescription) {
		this.diagnosisDescription = diagnosisDescription;
	}
	
}
