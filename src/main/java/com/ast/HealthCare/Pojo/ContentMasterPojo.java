package com.ast.HealthCare.Pojo;

public class ContentMasterPojo {

	private int contentMasterId;
	private int headingMasterId;
	private String description;
	private int diseaseId;
	private BirthDetailsPojo birthPojo;
	
	public BirthDetailsPojo getBirthPojo() {
		return birthPojo;
	}
	public void setBirthPojo(BirthDetailsPojo birthPojo) {
		this.birthPojo = birthPojo;
	}
	public int getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(int diseaseId) {
		this.diseaseId = diseaseId;
	}
	public int getContentMasterId() {
		return contentMasterId;
	}
	public void setContentMasterId(int contentMasterId) {
		this.contentMasterId = contentMasterId;
	}
	public int getHeadingMasterId() {
		return headingMasterId;
	}
	public void setHeadingMasterId(int headingMasterId) {
		this.headingMasterId = headingMasterId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ContentMasterPojo [contentMasterId=" + contentMasterId + ", headingMasterId=" + headingMasterId
				+ ", description=" + description + ", diseaseId=" + diseaseId + ", birthPojo=" + birthPojo + "]";
	}
	
	
}
