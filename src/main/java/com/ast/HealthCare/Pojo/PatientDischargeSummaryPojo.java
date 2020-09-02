package com.ast.HealthCare.Pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PatientDischargeSummaryPojo {

	private int patientDischargeId;
	private String patientId;
	private String patientName;
	private String patientAddress;
	private int age;
	private String sex;
	private Date doa;
	private Date dod;
	private String ward;
	private String opNo;
	private String ipNo;
	private float weight;
	private float temperature;
	private int pulse;
	private String bp;
	private float bmi;
	private float height;
	private int surgicalTypeId;
	private String surgicalType;
	private String response;
	private String mobileNo;
	private Date dos;
	
	
	
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Date getDos() {
		return dos;
	}
	public void setDos(Date dos) {
		this.dos = dos;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getSurgicalTypeId() {
		return surgicalTypeId;
	}
	public void setSurgicalTypeId(int surgicalTypeId) {
		this.surgicalTypeId = surgicalTypeId;
	}
	public String getSurgicalType() {
		return surgicalType;
	}
	public void setSurgicalType(String surgicalType) {
		this.surgicalType = surgicalType;
	}
	public float getBmi() {
		return bmi;
	}
	public void setBmi(float bmi) {
		this.bmi = bmi;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public int getPulse() {
		return pulse;
	}
	public void setPulse(int pulse) {
		this.pulse = pulse;
	}
	public String getBp() {
		return bp;
	}
	public void setBp(String bp) {
		this.bp = bp;
	}
	private List<PatientDischargeContentPojo> contentPojo = new ArrayList<PatientDischargeContentPojo>();
	
	
	
	public String getOpNo() {
		return opNo;
	}
	public void setOpNo(String opNo) {
		this.opNo = opNo;
	}
	public String getIpNo() {
		return ipNo;
	}
	public void setIpNo(String ipNo) {
		this.ipNo = ipNo;
	}
	public List<PatientDischargeContentPojo> getContentPojo() {
		return contentPojo;
	}
	public void setContentPojo(List<PatientDischargeContentPojo> contentPojo) {
		this.contentPojo = contentPojo;
	}
	public int getPatientDischargeId() {
		return patientDischargeId;
	}
	public void setPatientDischargeId(int patientDischargeId) {
		this.patientDischargeId = patientDischargeId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getDoa() {
		return doa;
	}
	public void setDoa(Date doa) {
		this.doa = doa;
	}
	public Date getDod() {
		return dod;
	}
	public void setDod(Date dod) {
		this.dod = dod;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	@Override
	public String toString() {
		return "PatientDischargeSummaryPojo [patientDischargeId=" + patientDischargeId + ", patientId=" + patientId
				+ ", patientName=" + patientName + ", patientAddress=" + patientAddress + ", age=" + age + ", sex="
				+ sex + ", doa=" + doa + ", dod=" + dod + ", ward=" + ward + ", opNo=" + opNo + ", ipNo=" + ipNo
				+ ", weight=" + weight + ", temperature=" + temperature + ", pulse=" + pulse + ", bp=" + bp + ", bmi="
				+ bmi + ", height=" + height + ", surgicalTypeId=" + surgicalTypeId + ", surgicalType=" + surgicalType
				+ ", response=" + response + ", mobileNo=" + mobileNo + ", dos=" + dos + ", contentPojo=" + contentPojo
				+ "]";
	}
	
	
}
