package com.ast.HealthCare.Pojo;

public class BillHeadMasterPojo {
	//CREATE TABLE billheadmaster(FEEHEADID SERIAL, FEEHEAD TEXT,
			//CGST INTEGER, SGST INTEGER,PRIMARY KEY(FEEHEADID));
			private int feeheadid;
			private String feehead;
			private float cgst;
			private float sgst;
			
			@Override
			public String toString() {
				return "BillMasterPojo [feeheadid=" + feeheadid + ", feehead=" + feehead + ", cgst=" + cgst + ", sgst=" + sgst
						+ "]";
			}
			
			public int getFeeheadid() {
				return feeheadid;
			}
			public void setFeeheadid(int feeheadid) {
				this.feeheadid = feeheadid;
			}
			public String getFeehead() {
				return feehead;
			}
			public void setFeehead(String feehead) {
				this.feehead = feehead;
			}
			public float getCgst() {
				return cgst;
			}
			public void setCgst(float cgst) {
				this.cgst = cgst;
			}
			public float getSgst() {
				return sgst;
			}
			public void setSgst(float sgst) {
				this.sgst = sgst;
			}
			
		}

