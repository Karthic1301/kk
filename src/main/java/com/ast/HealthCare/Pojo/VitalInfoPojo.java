
package com.ast.HealthCare.Pojo;
import java.sql.Date;

public class VitalInfoPojo {
	
	//CREATE TABLE  vitalinformation(VITALINFOID SERIAL,BP TEXT,HEIGHT INTEGER,WEIGHT NUMERIC,
	//BMI NUMERIC,WC INTEGER,TEMPERATURE NUMERIC,PULSE NUMERIC,DIA NUMERIC,SYS NUMERIC,
	//DOA NUMERIC,DOS NUMERIC,PATIENTID TEXT,VITALINFO_DATE DATE);
	
	private int vitalInfoId;
	private String bp;
	private float height;
	private float weight;
	private float bmi;
	private int wc;
	private float temperature;
	private int pulse;
	private int dia;
	private int sys;
	private int doa;
	private int dos;
	private String patientId;
	private Date vitalInfo_Date;
	
	
	
	@Override
	public String toString() {
		return "VitalInfoPojo [vitalInfoId=" + vitalInfoId + ", bp=" + bp + ", height=" + height + ", weight=" + weight
				+ ", bmi=" + bmi + ", wc=" + wc + ", temperature=" + temperature + ", pulse=" + pulse + ", dia=" + dia
				+ ", sys=" + sys + ", doa=" + doa + ", dos=" + dos + ", patientId=" + patientId + ", vitalInfo_Date="
				+ vitalInfo_Date + "]";
	}
	public int getVitalInfoId() {
		return vitalInfoId;
	}
	public void setVitalInfoId(int vitalInfoId) {
		this.vitalInfoId = vitalInfoId;
	}
	public String getBp() {
		return bp;
	}
	public void setBp(String bp) {
		this.bp = bp;
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
	public float getBmi() {
		return bmi;
	}
	public void setBmi(float bmi) {
		this.bmi = bmi;
	}
	public int getWc() {
		return wc;
	}
	public void setWc(int wc) {
		this.wc = wc;
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
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getSys() {
		return sys;
	}
	public void setSys(int sys) {
		this.sys = sys;
	}
	public int getDoa() {
		return doa;
	}
	public void setDoa(int doa) {
		this.doa = doa;
	}
	public int getDos() {
		return dos;
	}
	public void setDos(int dos) {
		this.dos = dos;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public Date getVitalInfo_Date() {
		return vitalInfo_Date;
	}
	public void setVitalInfo_Date(Date vitalInfo_Date) {
		this.vitalInfo_Date = vitalInfo_Date;
	}
	
	
}
