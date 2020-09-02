package com.ast.HealthCare.Pojo;

public class DiagnosisPojo {
	
	//CREATE TABLE diagnosismaster(DIAGNOSISID SERIAL,
	//DIAGNOSISNAME TEXT,DIAGNOSISCODE TEXT,PRIMARY KEY(DIAGNOSISID));

	private int diagnosisId;
	private String diagnosisName;
	private String diagnosisCode;
	
	@Override
	public String toString() {
		return "DiagnosisPojo [diagnosisId=" + diagnosisId + ", diagnosisName=" + diagnosisName + ", diagnosisCode="
				+ diagnosisCode + "]";
	}
	public int getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public String getDiagnosisCode() {
		return diagnosisCode;
	}
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}
	
	
}
