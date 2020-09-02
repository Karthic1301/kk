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

import com.ast.HealthCare.Pojo.PatientFindingsPojo;
import com.ast.HealthCare.Service.PatientFindingsService;

@RestController
public class PatientFindingsController {

	@Autowired
	PatientFindingsService fService;


	@RequestMapping(value = "/AddPatientFindings", method = RequestMethod.POST)
	public boolean addPatientFindings(HttpServletRequest request, HttpServletResponse response, @RequestBody List<PatientFindingsPojo> findings ) 
	{
		System.out.println("con "+ findings);
		return fService.addPatientFindings(findings);
	}
	
	@RequestMapping(value = "/PatientFindingsAll", method = RequestMethod.GET)
	public List<PatientFindingsPojo> patientFindingsAll(HttpServletRequest request, HttpServletResponse response) {
		return fService.patientFindingsAll();
	}
	
	@RequestMapping(value = "/PatientFindingsSearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<PatientFindingsPojo> patientFindingsSearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		
		return fService.patientFindingsSearchByPatientId(pid);
	}
	
	@RequestMapping(value = "/PatientFindingsDelete/{pid}", method = RequestMethod.DELETE)
	public int patientFindingsDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		System.out.println("con "+pid);
		return fService.patientFindingsDelete(pid);
	}

}
