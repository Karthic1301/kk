package com.ast.HealthCare.Pojo;

public class AppointmentJsonPojo {

	private String title;
	private String start;
	private String end;
	private String color;
	private String patientId;
	
	
	@Override
	public String toString() {
		return "AppointmentJsonPojo [title=" + title + ", start=" + start + ", end=" + end + ", color=" + color
				+ ", patientId=" + patientId + "]";
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
}
