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


import com.ast.HealthCare.Pojo.PatientComplaintsPojo;
import com.ast.HealthCare.Service.PatientComplaintsService;

@RestController
public class PatientComplaintsController {

	@Autowired
	PatientComplaintsService pcs;
	

	@RequestMapping(value = "/AddPatientComplaints", method = RequestMethod.POST)
	public boolean addPatientComplaints(HttpServletRequest request, HttpServletResponse response, @RequestBody List<PatientComplaintsPojo> comppojo ) 
	{
		System.out.println("con "+ comppojo);
		return pcs.addPatientComplaints(comppojo);
	}
	
	@RequestMapping(value = "/PatientComplaintsAll", method = RequestMethod.GET)
	public List<PatientComplaintsPojo> patientComplaintsAll(HttpServletRequest request, HttpServletResponse response) {
		
		return pcs.patientComplaintsAll();
	}
	
	@RequestMapping(value = "/PatientComplaintsSearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<PatientComplaintsPojo> patientComplaintsSearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		
		return pcs.patientComplaintsSearchByPatientId(pid);
	}
	
	@RequestMapping(value = "/PatientComplaintsSearchByPrescriptionNo/{pid}", method = RequestMethod.GET)
	public PatientComplaintsPojo patientComplaintsSearchByPrescriptionNo(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		
		return pcs.patientComplaintsSearchByPrescriptionNo(pid);
	}
	
	@RequestMapping(value = "/PatientComplaintsUpdate/{pid}", method = RequestMethod.PUT)
	public boolean patientComplaintsUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody PatientComplaintsPojo comppojo, @PathVariable("pid")String pid ) 
	{
		System.out.println("con "+ comppojo);
		return pcs.patientComplaintsUpdate(comppojo,pid);
	}
	
	
	@RequestMapping(value = "/PatientComplaintsDelete/{pid}", method = RequestMethod.DELETE)
	public int patientComplaintsDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid ) {
		System.out.println("con "+pid);
		return pcs.patientComplaintsDelete(pid);
	}
}
