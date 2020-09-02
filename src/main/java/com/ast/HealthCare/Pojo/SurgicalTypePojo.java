package com.ast.HealthCare.Pojo;

public class SurgicalTypePojo {

	private int surgicalTypeId;
	private String surgicalType;
	private String prefix;
	
	public int getSurgicalTypeId() {
		return surgicalTypeId;
	}
	public void setSurgicalTypeId(int surgicalTypeId) {
		this.surgicalTypeId = surgicalTypeId;
	}
	public String getSurgicalType() {
		return surgicalType;
	}
	public void setSurgicalType(String surgicalType) {
		this.surgicalType = surgicalType;
	}
	
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	
	@Override
	public String toString() {
		return "SurgicalTypePojo [surgicalTypeId=" + surgicalTypeId + ", surgicalType=" + surgicalType + ", prefix="
				+ prefix + "]";
	}
	
	
	
}
