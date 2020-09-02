package com.ast.HealthCare.Pojo;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class BillMasterPojo {
	//CREATE TABLE billmaster(BILLNO SERIAL,PATIENTID TEXT,BILLSTATUS TEXT,
	//MODIFIEDUSERID TEXT,BILLDATE DATE,MODIFIEDTIME TEXT,TOTALAMOUNT INTEGER,
	//SGST INTEGER,CGST INTEGER,NETAMOUNT INTEGER,PRIMARY KEY(BILLNO));
	
	private int serialid;
	private String billno;
	private String patientid;
	private String billstatus;
	private String modifieduserid;
	private Date billdate;
	private String modifiedtime;
	private float totalamount;
	private float sgst;
	private float cgst;
	private float totaltax;
	private float netamount;
	private String reason;
	private String patientName;
	private String mobileNo;
	private String doctorId;
	private String doctorName;
	private String prescriptionNo;
	private String logusername;
	private String logpassword;
	private String billingFlag;
	private String inOutFlag;
	private Date doa;
	private Date dod;
	private String wardNo;
	private String ipNo;
	private double lessAdvancePaid;
	private double finalTotal;
	private int age;
	private String gender;
	private PatientPojo patientPojo = new PatientPojo();
	private List<BillDetailPojo> billdetailpojo;
	
	
	
	
	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getInOutFlag() {
		return inOutFlag;
	}

	public void setInOutFlag(String inOutFlag) {
		this.inOutFlag = inOutFlag;
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

	public String getWardNo() {
		return wardNo;
	}

	public void setWardNo(String wardNo) {
		this.wardNo = wardNo;
	}

	public String getIpNo() {
		return ipNo;
	}

	public void setIpNo(String ipNo) {
		this.ipNo = ipNo;
	}

	public double getLessAdvancePaid() {
		return lessAdvancePaid;
	}

	public void setLessAdvancePaid(double lessAdvancePaid) {
		this.lessAdvancePaid = lessAdvancePaid;
	}

	public double getFinalTotal() {
		return finalTotal;
	}

	public void setFinalTotal(double finalTotal) {
		this.finalTotal = finalTotal;
	}

	public PatientPojo getPatientPojo() {
		return patientPojo;
	}

	public void setPatientPojo(PatientPojo patientPojo) {
		this.patientPojo = patientPojo;
	}

	public String getBillingFlag() {
		return billingFlag;
	}

	public void setBillingFlag(String billingFlag) {
		this.billingFlag = billingFlag;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getLogusername() {
		return logusername;
	}

	public void setLogusername(String logusername) {
		this.logusername = logusername;
	}

	public String getLogpassword() {
		return logpassword;
	}

	public void setLogpassword(String logpassword) {
		this.logpassword = logpassword;
	}

	public String getPrescriptionNo() {
		return prescriptionNo;
	}

	public void setPrescriptionNo(String prescriptionNo) {
		this.prescriptionNo = prescriptionNo;
	}

	public List<BillDetailPojo> getBilldetailpojo() {
		return billdetailpojo;
	}

	public void setBilldetailpojo(List<BillDetailPojo> billdetailpojo) {
		this.billdetailpojo = billdetailpojo;
	}

	public float getTotaltax() {
		return totaltax;
	}

	public void setTotaltax(float totaltax) {
		this.totaltax = totaltax;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	@Override
	public String toString() {
		return "BillMasterPojo [serialid=" + serialid + ", billno=" + billno + ", patientid=" + patientid
				+ ", billstatus=" + billstatus + ", modifieduserid=" + modifieduserid + ", billdate=" + billdate
				+ ", modifiedtime=" + modifiedtime + ", totalamount=" + totalamount + ", sgst=" + sgst + ", cgst="
				+ cgst + ", totaltax=" + totaltax + ", netamount=" + netamount + ", reason=" + reason + ", patientName="
				+ patientName + ", mobileNo=" + mobileNo + ", doctorId=" + doctorId + ", doctorName=" + doctorName
				+ ", prescriptionNo=" + prescriptionNo + ", logusername=" + logusername + ", logpassword=" + logpassword
				+ ", billingFlag=" + billingFlag + ", inOutFlag=" + inOutFlag + ", doa=" + doa + ", dod=" + dod
				+ ", wardNo=" + wardNo + ", ipNo=" + ipNo + ", lessAdvancePaid=" + lessAdvancePaid + ", finalTotal="
				+ finalTotal + ", age=" + age + ", gender=" + gender + ", patientPojo=" + patientPojo
				+ ", billdetailpojo=" + billdetailpojo + "]";
	}
	
	public int getSerialid() {
		return serialid;
	}
	public void setSerialid(int serialid) {
		this.serialid = serialid;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getBillstatus() {
		return billstatus;
	}
	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}
	public String getModifieduserid() {
		return modifieduserid;
	}
	public void setModifieduserid(String modifieduserid) {
		this.modifieduserid = modifieduserid;
	}
	public Date getBilldate() {
		return billdate;
	}
	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}
	public String getModifiedtime() {
		return modifiedtime;
	}
	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}
	public float getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(float totalamount) {
		this.totalamount = totalamount;
	}
	public float getSgst() {
		return sgst;
	}
	public void setSgst(float sgst) {
		this.sgst = sgst;
	}
	public float getCgst() {
		return cgst;
	}
	public void setCgst(float cgst) {
		this.cgst = cgst;
	}
	public float getNetamount() {
		return netamount;
	}
	public void setNetamount(float netamount) {
		this.netamount = netamount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	
	


}
