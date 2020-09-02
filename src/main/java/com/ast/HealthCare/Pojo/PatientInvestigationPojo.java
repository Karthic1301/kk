package com.ast.HealthCare.Pojo;

import java.io.File;
import java.sql.Date;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class PatientInvestigationPojo {

	//CREATE TABLE  patientinvestigation(PATIENTID INTEGER,INVESTIGATIONID INTEGER,
	//RESULTS TEXT,INVESTIGATIONIMAGE TEXT,INVESTIGATIONDATE DATE);
	private String patientId;
	private String prescriptionNo;
	private int investigationId;
	private String results;
	private String investigationName;
	private String investigationImage;
	private Date investigationDate;
	private MultipartFile investigationFile;
	private String investigationFileName;
	private byte[] investigationFileByte;
	private int investigationSerialId;
	

	
	
	public MultipartFile getInvestigationFile() {
		return investigationFile;
	}
	public void setInvestigationFile(File investigationFile) {
		this.investigationFile = (MultipartFile) investigationFile;
	}
	public int getInvestigationSerialId() {
		return investigationSerialId;
	}
	public void setInvestigationSerialId(int investigationSerialId) {
		this.investigationSerialId = investigationSerialId;
	}
	public String getInvestigationFileName() {
		return investigationFileName;
	}
	public void setInvestigationFileName(String investigationFileName) {
		this.investigationFileName = investigationFileName;
	}
	public String getInvestigationName() {
		return investigationName;
	}
	@Override
	public String toString() {
		return "PatientInvestigationPojo [patientId=" + patientId + ", prescriptionNo=" + prescriptionNo
				+ ", investigationId=" + investigationId + ", results=" + results + ", investigationName="
				+ investigationName + ", investigationImage=" + investigationImage + ", investigationDate="
				+ investigationDate + ", investigationFile=" + investigationFile + ", investigationFileName="
				+ investigationFileName + ", investigationFileByte=" + Arrays.toString(investigationFileByte)
				+ ", investigationSerialId=" + investigationSerialId + "]";
	}
	public void setInvestigationName(String investigationName) {
		this.investigationName = investigationName;
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
	public int getInvestigationId() {
		return investigationId;
	}
	public void setInvestigationId(int investigationId) {
		this.investigationId = investigationId;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	public String getInvestigationImage() {
		return investigationImage;
	}
	public void setInvestigationImage(String investigationImage) {
		this.investigationImage = investigationImage;
	}
	public Date getInvestigationDate() {
		return investigationDate;
	}
	public void setInvestigationDate(Date investigationDate) {
		this.investigationDate = investigationDate;
	}
	public byte[] getInvestigationFileByte() {
		return investigationFileByte;
	}
	public void setInvestigationFileByte(byte[] investigationFileByte) {
		this.investigationFileByte = investigationFileByte;
	}

}
