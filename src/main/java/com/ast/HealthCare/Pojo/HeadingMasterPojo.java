package com.ast.HealthCare.Pojo;

public class HeadingMasterPojo {

	private int headingMasterId;
	private String headingMasterName;
	private int orderNo;
	public int getHeadingMasterId() {
		return headingMasterId;
	}
	public void setHeadingMasterId(int headingMasterId) {
		this.headingMasterId = headingMasterId;
	}
	public String getHeadingMasterName() {
		return headingMasterName;
	}
	public void setHeadingMasterName(String headingMasterName) {
		this.headingMasterName = headingMasterName;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "HeadingMasterPojo [headingMasterId=" + headingMasterId + ", headingMasterName=" + headingMasterName
				+ ", orderNo=" + orderNo + "]";
	}
	
	
	
}
