package com.ast.HealthCare.Pojo;

import java.sql.Blob;

public class FileUploadPojo {

	private int fileId;
	private String fileName;
	private String fileData;
	
	@Override
	public String toString() {
		return "FileUploadPojo [fileId=" + fileId + ", fileName=" + fileName + ", fileData=" + fileData + "]";
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileData() {
		return fileData;
	}
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
	
	
}
