package com.ast.HealthCare.Pojo;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.ast.HealthCare.utils.*;
public class AppointmentPojo {

//	CREATE TABLE appointment(APPOINTMENTID SERIAL,CONSULTINGDOCTORID TEXT,APPOINTMENTDATE DATE,
//	APPOINTMENTSTARTTIME TIME,APPOINTMENTENDTIME TIME,PATIENTID TEXT,TOKENNO INTEGER,APPOINTMENTSTATUS TEXT,PRIMARY KEY(APPOINTMENTID));

	
	private long appointmentID;
	private String consiultingDoctorId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy") 
	private Date appointmentDate;
	
	private String appointmentStartTime;
	
	private String appointmentEndTime;
	
	private String patientId;
	private int tokenNo;
	private String appointmentStatus;
	
	@Override
	public String toString() {
		return "AppointmentPojo [appointmentID=" + appointmentID + ", consiultingDoctorId=" + consiultingDoctorId
				+ ", appointmentDate=" + appointmentDate + ", appointmentStartTime=" + appointmentStartTime
				+ ", appointmentEndTime=" + appointmentEndTime + ", patientId=" + patientId + ", tokenNo=" + tokenNo
				+ ", appointmentStatus=" + appointmentStatus + "]";
	}
	
	public long getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(long appointmentID) {
		this.appointmentID = appointmentID;
	}
	public String getConsiultingDoctorId() {
		return consiultingDoctorId;
	}
	public void setConsiultingDoctorId(String consiultingDoctorId) {
		this.consiultingDoctorId = consiultingDoctorId;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}
	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}
	public String getAppointmentEndTime() {
		return appointmentEndTime;
	}
	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public int getTokenNo() {
		return tokenNo;
	}
	public void setTokenNo(int tokenNo) {
		this.tokenNo = tokenNo;
	}
	public String getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	
		
}
