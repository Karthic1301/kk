/*package com.ast.HealthCare.Controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ast.HealthCare.Pojo.FileUploadPojo;
import com.ast.HealthCare.Service.FileUploadService;

@RestController
public class FileUploadController {

	@Autowired
	FileUploadService fileService;
	
	@RequestMapping(value = "/AddFileUpload", method = RequestMethod.POST)
	public boolean addFileUpload(HttpServletRequest request, HttpServletResponse response, @RequestBody FileUploadPojo upload ) 
	{
		System.out.println("con "+ upload);
		return fileService.addFileUpload(upload);
	}
	
	@RequestMapping(value = "/FileUploadAll", method = RequestMethod.GET)
	public List<FileUploadPojo> fileUploadAll(HttpServletRequest request, HttpServletResponse response) {
		
		return fileService.fileUploadAll();
	}
	@RequestMapping(value = "/FileUploadSearchById/{id}", method = RequestMethod.GET)
	public FileUploadPojo fileUploadSearchById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id) {
		
		return fileService.fileUploadSearchById(id);
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	public Data continueFileUpload(HttpServletRequest request, HttpServletResponse response)
	{
	 MultipartHttpServletRequest mRequest;
	 String filename = "upload.xlsx";
	 try 	{
	 	mRequest = (MultipartHttpServletRequest) request;
		mRequest.getParameterMap();
		Iterator itr = mRequest.getFileNames(); 
		while (itr.hasNext()) 
		{
		 MultipartFile mFile = mRequest.getFile(itr.next());
		 String fileName = mFile.getOriginalFilename();
		 System.out.println(fileName);
		 java.nio.file.Path path = Paths.get("C:/Data/DemoUpload/" + filename);
		 Files.deleteIfExists(path);
		 InputStream in = mFile.getInputStream();
		 Files.copy(in, path);
		 }
		}
	catch (Exception e) {
		 e.printStackTrace();
		 }
	return null;
	}
}
*/