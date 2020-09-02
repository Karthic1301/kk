package com.ast.HealthCare.Pojo;

import java.sql.Date;

public class BillDetailFeeHeadTypeReportPojo {
	
	private int feeid;
	private String doctorid;
	private Date date1;
	private Date date2;
	
	public int getFeeid() {
		return feeid;
	}

	public void setFeeid(int feeid) {
		this.feeid = feeid;
	}

	public String getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	
	
	@Override
	public String toString() {
		return "BillDetailFeeHeadTypeReportPojo [feeid=" + feeid + ", doctorid=" + doctorid + ", date1=" + date1
				+ ", date2=" + date2 + "]";
	}

}
