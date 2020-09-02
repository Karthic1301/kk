package com.ast.HealthCare.Pojo;

import java.sql.Date;

public class BirthDetailsPojo {

	private int birthId;
	private int contentMasterId;
	private Date dob;
	private String birthTime;
	private String sex;
	private double weight;
	private String bloodGroup;
	
	
	
	
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public int getBirthId() {
		return birthId;
	}
	public void setBirthId(int birthId) {
		this.birthId = birthId;
	}
	public int getContentMasterId() {
		return contentMasterId;
	}
	public void setContentMasterId(int contentMasterId) {
		this.contentMasterId = contentMasterId;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getBirthTime() {
		return birthTime;
	}
	public void setBirthTime(String birthTime) {
		this.birthTime = birthTime;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "BirthDetailsPojo [birthId=" + birthId + ", contentMasterId=" + contentMasterId + ", dob=" + dob
				+ ", birthTime=" + birthTime + ", sex=" + sex + ", weight=" + weight + ", bloodGroup=" + bloodGroup
				+ "]";
	}
	
	
}
