package com.ast.HealthCare.Pojo;

public class PatientDischargeContentPojo {

	private int patientContentId;
	private String patientId;
	private int headingMasterId;
	private String description;
	private int patientDischargeId;
	private int orderNo;
	private BirthDetailsPojo birthPojo;
	
	
	
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public BirthDetailsPojo getBirthPojo() {
		return birthPojo;
	}
	public void setBirthPojo(BirthDetailsPojo birthPojo) {
		this.birthPojo = birthPojo;
	}
	public int getPatientContentId() {
		return patientContentId;
	}
	public void setPatientContentId(int patientContentId) {
		this.patientContentId = patientContentId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
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
	public int getPatientDischargeId() {
		return patientDischargeId;
	}
	public void setPatientDischargeId(int patientDischargeId) {
		this.patientDischargeId = patientDischargeId;
	}
	@Override
	public String toString() {
		return "PatientDischargeContentPojo [patientContentId=" + patientContentId + ", patientId=" + patientId
				+ ", headingMasterId=" + headingMasterId + ", description=" + description + ", patientDischargeId="
				+ patientDischargeId + ", orderNo=" + orderNo + ", birthPojo=" + birthPojo + "]";
	}
	
	
}
