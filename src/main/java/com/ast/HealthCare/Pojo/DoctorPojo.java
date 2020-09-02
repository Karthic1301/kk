package com.ast.HealthCare.Pojo;

import java.util.ArrayList;
import java.util.List;

import com.ast.HealthCare.Dao.SettingDao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DoctorPojo {

	//CREATE TABLE doctormaster(DOCTORID TEXT,DOCTORNAME TEXT,DOCTORCODE TEXT,DOCTORGENDER TEXT,
	//DOCTORDATEOFBIRTH TEXT,DOCTORSPECIALITY TEXT,DOCTORQUALIFICATION TEXT,DOCTORADDRESS TEXT,
	//DOCTORMOBILE1 TEXT,DOCTORMOBILE2 TEXT,DOCTORPANAME TEXT,DOCTORPAMOBILENO TEXT,HOSPITALCODE TEXT,
	//DOCTORSPECCODE TEXT,DOCTORPHOTO TEXT,DOCTORSERIALID SERIAL,TIMEPERPATIENT TIME,DOCTORMAILID TEXT,
	//DOCTORUNIQUEID TEXT,PRIMARY KEY(DOCTORID));

	private String doctorId;
	private String doctorName;
	private String doctorCode;
	private String doctorGender;
	private String doctorDateOfBirth;
	private String doctorSpeciality;
	private String doctorQualification;
	private String doctorAddress;
	private String doctorMobile1;
	private String doctorMobile2;
	private String doctorPAName;
	private String doctorPAMobileNo;
	private String doctorHospitalCode;
	private String doctorSpecCode;
	private String doctorPhoto;
	private int timePerPatient;
	private String doctorMailId;
	private String doctorUniqueId;
	private String doctorUserName;
	private String doctorPassword;
	private int doctorSerialId;
	private List<DoctorTestMasterPojo> doctorTestMaster = new ArrayList<DoctorTestMasterPojo>();
	private SettingPojo settingPojo = new SettingPojo();
	
	
	public SettingPojo getSettingPojo() {
		return settingPojo;
	}



	public void setSettingPojo(SettingPojo settingPojo) {
		this.settingPojo = settingPojo;
	}



	public List<DoctorTestMasterPojo> getDoctorTestMaster() {
		return doctorTestMaster;
	}



	public void setDoctorTestMaster(List<DoctorTestMasterPojo> doctorTestMaster) {
		this.doctorTestMaster = doctorTestMaster;
	}



	@Override
	public String toString() {
		return "DoctorPojo [doctorId=" + doctorId + ", doctorName=" + doctorName + ", doctorCode=" + doctorCode
				+ ", doctorGender=" + doctorGender + ", doctorDateOfBirth=" + doctorDateOfBirth + ", doctorSpeciality="
				+ doctorSpeciality + ", doctorQualification=" + doctorQualification + ", doctorAddress=" + doctorAddress
				+ ", doctorMobile1=" + doctorMobile1 + ", doctorMobile2=" + doctorMobile2 + ", doctorPAName="
				+ doctorPAName + ", doctorPAMobileNo=" + doctorPAMobileNo + ", doctorHospitalCode=" + doctorHospitalCode
				+ ", doctorSpecCode=" + doctorSpecCode + ", doctorPhoto=" + doctorPhoto + ", timePerPatient="
				+ timePerPatient + ", doctorMailId=" + doctorMailId + ", doctorUniqueId=" + doctorUniqueId
				+ ", doctorUserName=" + doctorUserName + ", doctorPassword=" + doctorPassword + ", doctorSerialId="
				+ doctorSerialId + ", doctorTestMaster=" + doctorTestMaster + ", settingPojo=" + settingPojo + "]";
	}



	public int getDoctorSerialId() {
		return doctorSerialId;
	}



	public void setDoctorSerialId(int doctorSerialId) {
		this.doctorSerialId = doctorSerialId;
	}



	public String getDoctorUserName() {
		return doctorUserName;
	}
	public void setDoctorUserName(String doctorUserName) {
		this.doctorUserName = doctorUserName;
	}
	public String getDoctorPassword() {
		return doctorPassword;
	}
	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
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
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	public String getDoctorGender() {
		return doctorGender;
	}
	public void setDoctorGender(String doctorGender) {
		this.doctorGender = doctorGender;
	}
	public String getDoctorDateOfBirth() {
		return doctorDateOfBirth;
	}
	public void setDoctorDateOfBirth(String doctorDateOfBirth) {
		this.doctorDateOfBirth = doctorDateOfBirth;
	}
	public String getDoctorSpeciality() {
		return doctorSpeciality;
	}
	public void setDoctorSpeciality(String doctorSpeciality) {
		this.doctorSpeciality = doctorSpeciality;
	}
	public String getDoctorQualification() {
		return doctorQualification;
	}
	public void setDoctorQualification(String doctorQualification) {
		this.doctorQualification = doctorQualification;
	}
	public String getDoctorAddress() {
		return doctorAddress;
	}
	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}
	public String getDoctorMobile1() {
		return doctorMobile1;
	}
	public void setDoctorMobile1(String doctorMobile1) {
		this.doctorMobile1 = doctorMobile1;
	}
	public String getDoctorMobile2() {
		return doctorMobile2;
	}
	public void setDoctorMobile2(String doctorMobile2) {
		this.doctorMobile2 = doctorMobile2;
	}
	public String getDoctorPAName() {
		return doctorPAName;
	}
	public void setDoctorPAName(String doctorPAName) {
		this.doctorPAName = doctorPAName;
	}
	public String getDoctorPAMobileNo() {
		return doctorPAMobileNo;
	}
	public void setDoctorPAMobileNo(String doctorPAMobileNo) {
		this.doctorPAMobileNo = doctorPAMobileNo;
	}
	public String getDoctorHospitalCode() {
		return doctorHospitalCode;
	}
	public void setDoctorHospitalCode(String doctorHospitalCode) {
		this.doctorHospitalCode = doctorHospitalCode;
	}
	public String getDoctorSpecCode() {
		return doctorSpecCode;
	}
	public void setDoctorSpecCode(String doctorSpecCode) {
		this.doctorSpecCode = doctorSpecCode;
	}
	public String getDoctorPhoto() {
		return doctorPhoto;
	}
	public void setDoctorPhoto(String doctorPhoto) {
		this.doctorPhoto = doctorPhoto;
	}
	public int getTimePerPatient() {
		return timePerPatient;
	}
	public void setTimePerPatient(int timePerPatient) {
		this.timePerPatient = timePerPatient;
	}
	public String getDoctorMailId() {
		return doctorMailId;
	}
	public void setDoctorMailId(String doctorMailId) {
		this.doctorMailId = doctorMailId;
	}
	public String getDoctorUniqueId() {
		return doctorUniqueId;
	}
	public void setDoctorUniqueId(String doctorUniqueId) {
		this.doctorUniqueId = doctorUniqueId;
	}
	
	
}
