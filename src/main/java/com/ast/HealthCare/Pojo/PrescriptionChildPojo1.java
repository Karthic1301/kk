package com.ast.HealthCare.Pojo;

import java.util.List;

public class PrescriptionChildPojo1 {

	private List<PatientDiseasePojo> disease;
	private List<PatientComplaintsPojo> comp;
	private List<PatientInvestigationPojo> inves;
	private List<PatientFindingsPojo> findings;
	private List<PatientDiagnosisPojo> diag;
	private List<PrescriptionDrugPojo> pres;
	private List<PatientNotesPojo> notes;
	
	
	@Override
	public String toString() {
		return "PrescriptionChildPojo [disease=" + disease + ", comp=" + comp + ", inves=" + inves + ", findings="
				+ findings + ", diag=" + diag + ", pres=" + pres + ", notes=" + notes + "]";
	}
	
	public List<PatientDiseasePojo> getDisease() {
		return disease;
	}
	public void setDisease(List<PatientDiseasePojo> disease) {
		this.disease = disease;
	}
	public List<PatientComplaintsPojo> getComp() {
		return comp;
	}
	public void setComp(List<PatientComplaintsPojo> comp) {
		this.comp = comp;
	}
	public List<PatientInvestigationPojo> getInves() {
		return inves;
	}
	public void setInves(List<PatientInvestigationPojo> inves) {
		this.inves = inves;
	}
	public List<PatientFindingsPojo> getFindings() {
		return findings;
	}
	public void setFindings(List<PatientFindingsPojo> findings) {
		this.findings = findings;
	}
	public List<PatientDiagnosisPojo> getDiag() {
		return diag;
	}
	public void setDiag(List<PatientDiagnosisPojo> diag) {
		this.diag = diag;
	}
	public List<PrescriptionDrugPojo> getPres() {
		return pres;
	}
	public void setPres(List<PrescriptionDrugPojo> pres) {
		this.pres = pres;
	}
	public List<PatientNotesPojo> getNotes() {
		return notes;
	}
	public void setNotes(List<PatientNotesPojo> notes) {
		this.notes = notes;
	}
	
	
}
