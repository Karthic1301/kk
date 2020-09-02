package com.ast.HealthCare.Pojo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReproductiveHistoryPojo {

	// CREATE TABLE reproductivehistory(PATIENTID TEXT,MATUREDDATE
	// DATE,REGULARPERIODS TEXT,MENOPAUSEAGE INTEGER,
	// MISCARRIAGETIMES INTEGER,ABORTIONSTIMES INTEGER);

	private String patientId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date matureDate;
	private String regularPeriods;
	private int menopauseAge;
	private int miscarriageTimes;
	private int abortionsTimes;

	@Override
	public String toString() {
		return "ReproductiveHistoryPojo [patientId=" + patientId + ", matureDate=" + matureDate + ", regularPeriods="
				+ regularPeriods + ", menopauseAge=" + menopauseAge + ", miscarriageTimes=" + miscarriageTimes
				+ ", abortionsTimes=" + abortionsTimes + "]";
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Date getMatureDate() {
		return matureDate;
	}

	public void setMatureDate(Date matureDate) {
		this.matureDate = matureDate;
	}

	public String getRegularPeriods() {
		return regularPeriods;
	}

	public void setRegularPeriods(String regularPeriods) {
		this.regularPeriods = regularPeriods;
	}

	public int getMenopauseAge() {
		return menopauseAge;
	}

	public void setMenopauseAge(int menopauseAge) {
		this.menopauseAge = menopauseAge;
	}

	public int getMiscarriageTimes() {
		return miscarriageTimes;
	}

	public void setMiscarriageTimes(int miscarriageTimes) {
		this.miscarriageTimes = miscarriageTimes;
	}

	public int getAbortionsTimes() {
		return abortionsTimes;
	}

	public void setAbortionsTimes(int abortionsTimes) {
		this.abortionsTimes = abortionsTimes;
	}
}
