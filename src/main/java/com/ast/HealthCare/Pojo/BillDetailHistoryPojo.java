package com.ast.HealthCare.Pojo;

public class BillDetailHistoryPojo {
	
	private int billdetailid;
	private String doctorid;
	private int billserailid;
	private String billno;
	private int feeid;
	private float amount;
	private float cgstpercentage;
	private float cgstamount;
	private float sgstpercentage;
	private float sgstamount;
	private float gstamount;
	private float netamount;
	private String logusername;
	private String logpassword;
	private int testId;
	private String testMasterName;
	private int orderNo;
	
	
	
	
	

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getTestMasterName() {
		return testMasterName;
	}

	public void setTestMasterName(String testMasterName) {
		this.testMasterName = testMasterName;
	}

	@Override
	public String toString() {
		return "BillDetailHistoryPojo [billdetailid=" + billdetailid + ", doctorid=" + doctorid + ", billserailid="
				+ billserailid + ", billno=" + billno + ", feeid=" + feeid + ", amount=" + amount + ", cgstpercentage="
				+ cgstpercentage + ", cgstamount=" + cgstamount + ", sgstpercentage=" + sgstpercentage + ", sgstamount="
				+ sgstamount + ", gstamount=" + gstamount + ", netamount=" + netamount + ", logusername=" + logusername
				+ ", logpassword=" + logpassword + ", testId=" + testId + ", testMasterName=" + testMasterName
				+ ", orderNo=" + orderNo + "]";
	}
	
	public int getBilldetailid() {
		return billdetailid;
	}
	public void setBilldetailid(int billdetailid) {
		this.billdetailid = billdetailid;
	}
	public String getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}
	public int getBillserailid() {
		return billserailid;
	}
	public void setBillserailid(int billserailid) {
		this.billserailid = billserailid;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public int getFeeid() {
		return feeid;
	}
	public void setFeeid(int feeid) {
		this.feeid = feeid;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getCgstpercentage() {
		return cgstpercentage;
	}
	public void setCgstpercentage(float cgstpercentage) {
		this.cgstpercentage = cgstpercentage;
	}
	public float getCgstamount() {
		return cgstamount;
	}
	public void setCgstamount(float cgstamount) {
		this.cgstamount = cgstamount;
	}
	public float getSgstpercentage() {
		return sgstpercentage;
	}
	public void setSgstpercentage(float sgstpercentage) {
		this.sgstpercentage = sgstpercentage;
	}
	public float getSgstamount() {
		return sgstamount;
	}
	public void setSgstamount(float sgstamount) {
		this.sgstamount = sgstamount;
	}
	public float getGstamount() {
		return gstamount;
	}
	public void setGstamount(float gstamount) {
		this.gstamount = gstamount;
	}
	public float getNetamount() {
		return netamount;
	}
	public void setNetamount(float netamount) {
		this.netamount = netamount;
	}
	public String getLogusername() {
		return logusername;
	}
	public void setLogusername(String logusername) {
		this.logusername = logusername;
	}
	public String getLogpassword() {
		return logpassword;
	}
	public void setLogpassword(String logpassword) {
		this.logpassword = logpassword;
	}
	

}
