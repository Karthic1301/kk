package com.ast.HealthCare.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Pojo.ContentMasterPojo;
import com.ast.HealthCare.Service.ContentMasterService;

@RestController
public class ContentMasterController {

	@Autowired
	ContentMasterService contentMasterService;
	

	@RequestMapping(value = "/AddContentMaster", method = RequestMethod.POST)
	public boolean addContentMasterMaster(HttpServletRequest request, HttpServletResponse response, @RequestBody ContentMasterPojo dis ) 
	{
		System.out.println("con "+ dis);
		return contentMasterService.addContentMaster(dis);
	}
	
	@RequestMapping(value = "/ContentMasterAll", method = RequestMethod.GET)
	public List<ContentMasterPojo> doctorContentMasterMasterAll(HttpServletRequest request, HttpServletResponse response) {
		
		return contentMasterService.contentMasterAll();
	}
	
	@RequestMapping(value = "/ContentMasterByHeadingMasterId/{id}", method = RequestMethod.GET)
	public ContentMasterPojo contentMasterByHeadingMasterId(HttpServletRequest request, HttpServletResponse response, @PathVariable int id ) {
		
		return contentMasterService.contentMasterByHeadingMasterId(id);
	}
	
	@RequestMapping(value = "/ContentMasterDelete/{pid}", method = RequestMethod.DELETE)
	public int doctorContentMasterDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return contentMasterService.contentMasterDelete(pid);
	}

	@RequestMapping(value = "/ContentMasterUpdate", method = RequestMethod.PUT)
	public boolean doctorContentMasterUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody ContentMasterPojo dt ) {
		return contentMasterService.contentMasterUpdate(dt);
	}
}
