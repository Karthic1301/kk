package com.ast.HealthCare.Pojo;

public class NotesPojo {

	private int notesId;
	private String notesName;
	private String notesCode;
	
	
	@Override
	public String toString() {
		return "NotesPojo [notesId=" + notesId + ", notesName=" + notesName + ", notesCode=" + notesCode + "]";
	}
	
	public int getNotesId() {
		return notesId;
	}
	public void setNotesId(int notesId) {
		this.notesId = notesId;
	}
	public String getNotesName() {
		return notesName;
	}
	public void setNotesName(String notesName) {
		this.notesName = notesName;
	}
	public String getNotesCode() {
		return notesCode;
	}
	public void setNotesCode(String notesCode) {
		this.notesCode = notesCode;
	}
		
}
