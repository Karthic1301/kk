package com.ast.HealthCare.Pojo;

public class SettingPojo {

	private int settingId;
	private String pageSize;
	private boolean pageHeader;
	private int marginLeft;
	private int marginRight;
	private int marginTop;
	private int marginBottom;
	private String doctorId;
	
	public String getDoctorId() {
		return doctorId;
	}	
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	public int getSettingId() {
		return settingId;
	}
	
	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public boolean getPageHeader() {
		return pageHeader;
	}
	public void setPageHeader(boolean pageHeader) {
		this.pageHeader = pageHeader;
	}
	public int getMarginLeft() {
		return marginLeft;
	}
	public void setMarginLeft(int marginLeft) {
		this.marginLeft = marginLeft;
	}
	public int getMarginRight() {
		return marginRight;
	}
	public void setMarginRight(int marginRight) {
		this.marginRight = marginRight;
	}
	public int getMarginTop() {
		return marginTop;
	}
	public void setMarginTop(int marginTop) {
		this.marginTop = marginTop;
	}
	public int getMarginBottom() {
		return marginBottom;
	}
	public void setMarginBottom(int marginBottom) {
		this.marginBottom = marginBottom;
	}
	@Override
	public String toString() {
		return "SettingPojo [settingId=" + settingId + ", pageSize=" + pageSize + ", pageHeader=" + pageHeader
				+ ", marginLeft=" + marginLeft + ", marginRight=" + marginRight + ", marginTop=" + marginTop
				+ ", marginBottom=" + marginBottom + ", doctorId=" + doctorId + "]";
	}
	
	
	
}
