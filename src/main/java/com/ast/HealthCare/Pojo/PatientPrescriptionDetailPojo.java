package com.ast.HealthCare.Pojo;

import java.util.List;

public class PatientPrescriptionDetailPojo {

	private int code;
	private String response;
	private List<PatientTestPojo> testpojo;
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public List<PatientTestPojo> getTestpojo() {
		return testpojo;
	}
	public void setTestpojo(List<PatientTestPojo> testpojo) {
		this.testpojo = testpojo;
	}
	@Override
	public String toString() {
		return "PatientPrescriptionDetailPojo [code=" + code + ", response=" + response + ", testpojo=" + testpojo
				+ "]";
	}
	
	
	
}
