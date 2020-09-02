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

import com.ast.HealthCare.Pojo.HeadingMasterPojo;
import com.ast.HealthCare.Service.HeadingMasterService;

@RestController
public class HeadingMasterController {

	@Autowired
	HeadingMasterService headingMasterService;
	

	@RequestMapping(value = "/AddHeadingMaster", method = RequestMethod.POST)
	public boolean addHeadingMasterMaster(HttpServletRequest request, HttpServletResponse response, @RequestBody HeadingMasterPojo dis ) 
	{
		System.out.println("con "+ dis);
		return headingMasterService.addHeadingMaster(dis);
	}
	
	@RequestMapping(value = "/HeadingMasterAll", method = RequestMethod.GET)
	public List<HeadingMasterPojo> doctorHeadingMasterMasterAll(HttpServletRequest request, HttpServletResponse response) {
		
		return headingMasterService.headingMasterAll();
	}
	
	@RequestMapping(value = "/HeadingMasterDelete/{pid}", method = RequestMethod.DELETE)
	public int doctorHeadingMasterDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return headingMasterService.headingMasterDelete(pid);
	}

	@RequestMapping(value = "/HeadingMasterUpdate", method = RequestMethod.PUT)
	public boolean doctorHeadingMasterUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody HeadingMasterPojo dt ) {
		return headingMasterService.headingMasterUpdate(dt);
	}
}
