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

import com.ast.HealthCare.Pojo.SurgicalHistoryPojo;
import com.ast.HealthCare.Service.SurgicalHistoryService;

@RestController
public class SurgicalHistoryController {

	@Autowired
	SurgicalHistoryService shs;
	
	@RequestMapping(value = "/AddSurgicalHistory", method = RequestMethod.POST)
	public boolean addSurgicalHistory(HttpServletRequest request, HttpServletResponse response, @RequestBody List<SurgicalHistoryPojo> imp ) 
	{
		System.out.println("con "+ imp);
		return shs.addSurgicalHistory(imp);
	}
		
	@RequestMapping(value = "/SurgicalHistoryAll", method = RequestMethod.GET)
	public List<SurgicalHistoryPojo> surgicalHistoryAll(HttpServletRequest request, HttpServletResponse response) {
		
		return shs.surgicalHistoryAll();
	}
	
	@RequestMapping(value = "/SurgicalHistorySearchByPatientId/{pid}",method = RequestMethod.GET)
	public List<SurgicalHistoryPojo> SurgicalHistorySearchByPatientId (HttpServletRequest request, HttpServletResponse response,@PathVariable("pid") String pid) 
	{
	return shs.SurgicalHistorySearchByPatientId(pid);
	}
	
	@RequestMapping(value = "/SurgicalHistoryUpdate", method = RequestMethod.PUT)
	public boolean surgicalHistoryUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody SurgicalHistoryPojo dt ) {
		return shs.surgicalHistoryUpdate(dt);
	}
	
	@RequestMapping(value = "/SurgicalHistoryDelete/{pid}", method = RequestMethod.DELETE)
	public int surgicalHistoryDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return shs.surgicalHistoryDelete(pid);
	}
}
