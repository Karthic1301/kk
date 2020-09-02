package com.ast.HealthCare.Controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ast.HealthCare.Service.UploadService;

@RestController
public class UploadController {

	@Autowired
	UploadService uploadservice;

	/*private final String filepath = "E:/uploads/"; // saved uploads
*/
	@RequestMapping(value = "UploadInvestigationFile", method = RequestMethod.POST)
	public Integer uploadDataFile(@RequestParam(value = "file", required = true) MultipartFile file)
			throws IOException {
		System.out.println("file123controller");
		return uploadservice.uploadDataFile(file);

	}

	//
	@RequestMapping(value = "ViewInvestigationFile/{invid}", method = RequestMethod.GET)
	public byte[] viewfile(HttpServletRequest request, HttpServletResponse response, @PathVariable("invid") Long invid)
			throws IOException {
		return uploadservice.viewfile(invid);
	}

	@RequestMapping(value = "ViewInvestigationFileByPatientId/{patientid}", method = RequestMethod.GET)
	public List<byte[]> viewFileByPatientId(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("patientid") String pid) throws IOException {
		return uploadservice.viewFileByPatientId(pid);
	}
	/*
	 * private MultipartFile MultipartFile(File file) { // TODO Auto-generated
	 * method stub return null; }
	 */ // MultipartFile file =(MultipartFile) f;
		// file.createNewFile();
		// FileInputStream fos = new FileInputStream(file);
	/*
	 * File[] files = file.listFiles(); ��������for(File f: files){
	 * ������������System.out.println(f.getName()); ������
	 */

}