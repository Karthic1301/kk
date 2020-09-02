package com.ast.HealthCare.Pojo;

import java.sql.Date;
import java.util.List;

public class LabTestResultEntryPojo {

	private int testResultId;
	private String reportNo;
	private Date reportDate;
	private String patientName;
	private String indications;
	private String labTechName;
	private List<LabTestResultEntryDetailsPojo> testResultDetails;
	
	
	public List<LabTestResultEntryDetailsPojo> getTestResultDetails() {
		return testResultDetails;
	}
	public void setTestResultDetails(List<LabTestResultEntryDetailsPojo> testResultDetails) {
		this.testResultDetails = testResultDetails;
	}
	public int getTestResultId() {
		return testResultId;
	}
	public void setTestResultId(int testResultId) {
		this.testResultId = testResultId;
	}
	
	
	public String getIndications() {
		return indications;
	}
	public void setIndications(String indications) {
		this.indications = indications;
	}
	public String getLabTechName() {
		return labTechName;
	}
	public void setLabTechName(String labTechName) {
		this.labTechName = labTechName;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@Override
	public String toString() {
		return "LabTestResultEntryPojo [testResultId=" + testResultId + ", reportNo=" + reportNo + ", reportDate="
				+ reportDate + ", patientName=" + patientName + ", indications=" + indications + ", labTechName="
				+ labTechName + ", testResultDetails=" + testResultDetails + "]";
	}
	
	
	
}
