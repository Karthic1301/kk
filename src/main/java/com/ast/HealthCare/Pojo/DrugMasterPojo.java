package com.ast.HealthCare.Pojo;

public class DrugMasterPojo {
	
	//CREATE TABLE  drugmaster(DRUGID SERIAL,DRUGNAME TEXT,GENERICID INT,DRUGTYPEID INT,DRUGCODE TEXT,PRIMARY KEY(DRUGID));

		private int drugId;
		private String drugName;
		private int genericId;
		private int drugTypeId;
		private String drugCode;
		private String genericName;
		
		
		
		
		
		
		public String getGenericName() {
			return genericName;
		}
		public void setGenericName(String genericName) {
			this.genericName = genericName;
		}
		@Override
		public String toString() {
			return "DrugMasterPojo [drugId=" + drugId + ", drugName=" + drugName + ", genericId=" + genericId
					+ ", drugTypeId=" + drugTypeId + ", drugCode=" + drugCode + "]";
		}
		public int getDrugId() {
			return drugId;
		}
		public void setDrugId(int drugId) {
			this.drugId = drugId;
		}
		public String getDrugName() {
			return drugName;
		}
		public void setDrugName(String drugName) {
			this.drugName = drugName;
		}
		public int getGenericId() {
			return genericId;
		}
		public void setGenericId(int genericId) {
			this.genericId = genericId;
		}
		public int getDrugTypeId() {
			return drugTypeId;
		}
		public void setDrugTypeId(int drugTypeId) {
			this.drugTypeId = drugTypeId;
		}
		public String getDrugCode() {
			return drugCode;
		}
		public void setDrugCode(String drugCode) {
			this.drugCode = drugCode;
		}

}
