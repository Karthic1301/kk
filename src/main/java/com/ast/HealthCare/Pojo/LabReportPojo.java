package com.ast.HealthCare.Pojo;

import java.sql.Date;

public class LabReportPojo {
	
	private int reportId;
	private String reportNo;
	private String patientNo;
	private String patientName;
	private double balance;
	private Date issueDate;
	
	
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	@Override
	public String toString() {
		return "LabReportPojo [reportId=" + reportId + ", reportNo=" + reportNo + ", patientNo=" + patientNo
				+ ", patientName=" + patientName + ", balance=" + balance + ", issueDate=" + issueDate + "]";
	}
	
	
	

}
