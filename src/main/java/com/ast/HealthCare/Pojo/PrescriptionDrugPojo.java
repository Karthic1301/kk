package com.ast.HealthCare.Pojo;

import java.sql.Date;

public class PrescriptionDrugPojo {
//	CREATE TABLE  prescriptiondrug(PRESCRIPTIONID INTEGER,DRUGID INTEGER,DOSAGE INTEGER,DRUGTYPE TEXT,
//	DURATION INTEGER,QUANTITY INTEGER,MORNING TEXT,AFTERNOON TEXT,EVENING TEXT,NIGHT TEXT,TT_TYPE TEXT,
//	INTAKE TEXT,NEXTREVIEWDATE DATE);
	
	//change duration type to drug type;
	private String patientId;
	private String prescriptionNo;
	private int drugId;
	private String drugName;
	private double dosage;
	private String drugType;
	private int duration;
	private int quantity;
	private String morning;
	private String afternoon;
	private String evening;
	private String night;
	private String ttType;
	private String intake;
	private Date nextReviewDate;
	private int genericId;
	private String genericName;
	
	
	
	
	public int getGenericId() {
		return genericId;
	}
	public void setGenericId(int genericId) {
		this.genericId = genericId;
	}
	public String getGenericName() {
		return genericName;
	}
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	@Override
	public String toString() {
		return "PrescriptionDrugPojo [patientId=" + patientId + ", prescriptionNo=" + prescriptionNo + ", drugId="
				+ drugId + ", drugName=" + drugName + ", dosage=" + dosage + ", drugType=" + drugType + ", duration="
				+ duration + ", quantity=" + quantity + ", morning=" + morning + ", afternoon=" + afternoon
				+ ", evening=" + evening + ", night=" + night + ", ttType=" + ttType + ", intake=" + intake
				+ ", nextReviewDate=" + nextReviewDate + ", genericId=" + genericId + ", genericName=" + genericName
				+ "]";
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
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
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public double getDosage() {
		return dosage;
	}
	public void setDosage(double dosage) {
		this.dosage = dosage;
	}
	public String getDrugType() {
		return drugType;
	}
	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getMorning() {
		return morning;
	}
	public void setMorning(String morning) {
		this.morning = morning;
	}
	public String getAfternoon() {
		return afternoon;
	}
	public void setAfternoon(String afternoon) {
		this.afternoon = afternoon;
	}
	public String getEvening() {
		return evening;
	}
	public void setEvening(String evening) {
		this.evening = evening;
	}
	public String getNight() {
		return night;
	}
	public void setNight(String night) {
		this.night = night;
	}
	public String getTtType() {
		return ttType;
	}
	public void setTtType(String ttType) {
		this.ttType = ttType;
	}
	public String getIntake() {
		return intake;
	}
	public void setIntake(String intake) {
		this.intake = intake;
	}
	public Date getNextReviewDate() {
		return nextReviewDate;
	}
	public void setNextReviewDate(Date nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}
	
		
}
