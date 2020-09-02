package com.ast.HealthCare.Pojo;

public class BillMasterReasonPojo {
	
	private String billno;
	private String reason;
	private String logusername;
	private String logpassword;
	
	@Override
	public String toString() {
		return "BillMasterReasonPojo [billno=" + billno + ", reason=" + reason + ", logusername=" + logusername
				+ ", logpassword=" + logpassword + "]";
	}

	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
