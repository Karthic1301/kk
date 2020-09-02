package com.ast.HealthCare.Pojo;

public class DrugTypePojo {

	private int drugTypeId;
	private String drugTypeName;
	private String drugTypeCode;
	
	@Override
	public String toString() {
		return "DrugTypePojo [drugTypeId=" + drugTypeId + ", drugTypeName=" + drugTypeName + ", drugTypeCode="
				+ drugTypeCode + "]";
	}
	public int getDrugTypeId() {
		return drugTypeId;
	}
	public void setDrugTypeId(int drugTypeId) {
		this.drugTypeId = drugTypeId;
	}
	public String getDrugTypeName() {
		return drugTypeName;
	}
	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
	}
	public String getDrugTypeCode() {
		return drugTypeCode;
	}
	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}
	
}
