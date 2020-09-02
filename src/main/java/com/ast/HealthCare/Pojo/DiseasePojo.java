package com.ast.HealthCare.Pojo;

public class DiseasePojo {

	private int diseaseId;
	private String diseaseName;
	private String diseaseCode;
	
	
	
	@Override
	public String toString() {
		return "DiseasePojo [diseaseId=" + diseaseId + ", diseaseName=" + diseaseName + ", diseaseCode=" + diseaseCode
				+ "]";
	}
	public int getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(int diseaseId) {
		this.diseaseId = diseaseId;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	
	
	
}
