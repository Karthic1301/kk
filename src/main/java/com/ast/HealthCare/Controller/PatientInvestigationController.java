package com.ast.HealthCare.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Pojo.PatientInvestigationPojo;
import com.ast.HealthCare.Service.PatientInvestigationService;

@RestController
public class PatientInvestigationController {

	@Autowired
	PatientInvestigationService pis;
	

	@RequestMapping(value = "/AddPatientInvestigation", method = RequestMethod.POST)
	public boolean addPatientInvestigation(HttpServletRequest request, HttpServletResponse response, @RequestBody List<PatientInvestigationPojo> imp ) throws FileNotFoundException, IOException 
	{
		System.out.println("con "+ imp);
		return pis.addPatientInvestigation(imp);
	}
	
	@RequestMapping(value = "/PatientInvestigationAll", method = RequestMethod.GET)
	public List<PatientInvestigationPojo> patientInvestigationAll(HttpServletRequest request, HttpServletResponse response) {
		
		return pis.patientInvestigationAll();
	}

	@RequestMapping(value = "/PatientInvestigationSearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<PatientInvestigationPojo> patientInvestigationSearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		
		return pis.patientInvestigationSearchByPatientId(pid);
	}
	
	@RequestMapping(value = "/PatientInvestigationReportByPatientId/{pid}", method = RequestMethod.GET)
	public List<PatientInvestigationPojo> patientInvestigationReportByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		
		return pis.patientInvestigationReportByPatientId(pid);
	}
	
	@RequestMapping(value = "/PatientInvestigationDelete/{pid}/{inv}", method = RequestMethod.DELETE)
	public int patientInvestigationDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid, @PathVariable("inv") int inv) throws IOException {
		System.out.println("con "+pid);
		return pis.patientInvestigationDelete(pid,inv);
	}
}
