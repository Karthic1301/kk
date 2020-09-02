package com.ast.HealthCare.Pojo;

import java.sql.Date;
import java.util.List;

public class SmsMasterPojo {
	
	private int smsserialid;
	private String smsname;
	private Date cretaedate;
	private String smscontent;
	private String repeatemode;
	private Date senddate;
	private Date nextsmsdate;
	private String sendtime;
	private String repeatecategory;
	private int smsstatus;
	private String smstype;
	private int patientselectionstatus;
	private int doctorselectionstatus;
	private int staffselectionstatus;
	private List<SmsChildPojo> smschildpojo;
	
	
	public Date getNextsmsdate() {
		return nextsmsdate;
	}

	public void setNextsmsdate(Date nextsmsdate) {
		this.nextsmsdate = nextsmsdate;
	}
	
	public List<SmsChildPojo> getSmschildpojo() {
		return smschildpojo;
	}

	public void setSmschildpojo(List<SmsChildPojo> smschildpojo) {
		this.smschildpojo = smschildpojo;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getSmsname() {
		return smsname;
	}

	public void setSmsname(String smsname) {
		this.smsname = smsname;
	}
	public int getPatientselectionstatus() {
		return patientselectionstatus;
	}

	public void setPatientselectionstatus(int patientselectionstatus) {
		this.patientselectionstatus = patientselectionstatus;
	}

	public int getDoctorselectionstatus() {
		return doctorselectionstatus;
	}

	public void setDoctorselectionstatus(int doctorselectionstatus) {
		this.doctorselectionstatus = doctorselectionstatus;
	}

	public int getStaffselectionstatus() {
		return staffselectionstatus;
	}

	public void setStaffselectionstatus(int staffselectionstatus) {
		this.staffselectionstatus = staffselectionstatus;
	}
	
	public int getSmsserialid() {
		return smsserialid;
	}

	public void setSmsserialid(int smsserialid) {
		this.smsserialid = smsserialid;
	}

	public Date getCretaedate() {
		return cretaedate;
	}

	public void setCretaedate(Date cretaedate) {
		this.cretaedate = cretaedate;
	}

	public String getSmscontent() {
		return smscontent;
	}

	public void setSmscontent(String smscontent) {
		this.smscontent = smscontent;
	}

	public String getRepeatemode() {
		return repeatemode;
	}

	public void setRepeatemode(String repeatemode) {
		this.repeatemode = repeatemode;
	}

	public Date getSenddate() {
		return senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}

	public String getRepeatecategory() {
		return repeatecategory;
	}

	public void setRepeatecategory(String repeatecategory) {
		this.repeatecategory = repeatecategory;
	}

	public int getSmsstatus() {
		return smsstatus;
	}

	public void setSmsstatus(int smsstatus) {
		this.smsstatus = smsstatus;
	}

	public String getSmstype() {
		return smstype;
	}

	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}

	
	
	@Override
	public String toString() {
		return "SmsMasterPojo [smsserialid=" + smsserialid + ", smsname=" + smsname + ", cretaedate=" + cretaedate
				+ ", smscontent=" + smscontent + ", repeatemode=" + repeatemode + ", senddate=" + senddate
				+ ", nextsmsdate=" + nextsmsdate + ", sendtime=" + sendtime + ", repeatecategory=" + repeatecategory
				+ ", smsstatus=" + smsstatus + ", smstype=" + smstype + ", patientselectionstatus="
				+ patientselectionstatus + ", doctorselectionstatus=" + doctorselectionstatus
				+ ", staffselectionstatus=" + staffselectionstatus + ", smschildpojo=" + smschildpojo + "]";
	}

}
