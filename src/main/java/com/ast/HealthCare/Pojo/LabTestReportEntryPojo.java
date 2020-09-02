package com.ast.HealthCare.Pojo;

import java.sql.Date;
import java.util.List;

public class LabTestReportEntryPojo {

	private int testReportEntryId;
	private String reportNo;
	private Date reportDate;
	private String doctorId;
	private String doctorName;
	private String patientId;
	private String patientFirstName;
	private double finalTotAmount;
	private double finaldiscountPercent;
	private double finaldiscountAmount;
	private double paidAmount;
	private double netAmount;
	private double balance;
	private String status;
	private Date issueDate;
	private List<LabTestReportEntryDetailsPojo> labTestEntryReportDetail;
	
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public double getFinalTotAmount() {
		return finalTotAmount;
	}
	public void setFinalTotAmount(double finalTotAmount) {
		this.finalTotAmount = finalTotAmount;
	}
	public double getFinaldiscountPercent() {
		return finaldiscountPercent;
	}
	public void setFinaldiscountPercent(double finaldiscountPercent) {
		this.finaldiscountPercent = finaldiscountPercent;
	}
	public double getFinaldiscountAmount() {
		return finaldiscountAmount;
	}
	public void setFinaldiscountAmount(double finaldiscountAmount) {
		this.finaldiscountAmount = finaldiscountAmount;
	}
	public List<LabTestReportEntryDetailsPojo> getLabTestEntryReportDetail() {
		return labTestEntryReportDetail;
	}
	public void setLabTestEntryReportDetail(List<LabTestReportEntryDetailsPojo> labTestEntryReportDetail) {
		this.labTestEntryReportDetail = labTestEntryReportDetail;
	}
	public int getTestReportEntryId() {
		return testReportEntryId;
	}
	public void setTestReportEntryId(int testReportEntryId) {
		this.testReportEntryId = testReportEntryId;
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
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "LabTestReportEntryPojo [testReportEntryId=" + testReportEntryId + ", reportNo=" + reportNo
				+ ", reportDate=" + reportDate + ", doctorId=" + doctorId + ", doctorName=" + doctorName
				+ ", patientId=" + patientId + ", patientFirstName=" + patientFirstName + ", finalTotAmount="
				+ finalTotAmount + ", finaldiscountPercent=" + finaldiscountPercent + ", finaldiscountAmount="
				+ finaldiscountAmount + ", paidAmount=" + paidAmount + ", netAmount=" + netAmount + ", balance="
				+ balance + ", status=" + status + ", issueDate=" + issueDate + ", labTestEntryReportDetail="
				+ labTestEntryReportDetail + "]";
	}
	
	
	
}
