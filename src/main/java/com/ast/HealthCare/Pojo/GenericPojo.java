package com.ast.HealthCare.Pojo;

public class GenericPojo {

	private int genericId;
	private String genericName;
	private String genericCode;
	
	@Override
	public String toString() {
		return "GenericPojo [genericId=" + genericId + ", genericName=" + genericName + ", genericCode=" + genericCode
				+ "]";
	}
	
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
	public String getGenericCode() {
		return genericCode;
	}
	public void setGenericCode(String genericCode) {
		this.genericCode = genericCode;
	}
	
}
