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

import com.ast.HealthCare.Pojo.PatientPrescriptionDetailPojo;
import com.ast.HealthCare.Pojo.PatientTestPojo;
import com.ast.HealthCare.Service.PatientTestService;

@RestController
public class PatientTestController {

	@Autowired
	PatientTestService patientTestService;
	

	@RequestMapping(value = "/AddPatientTest", method = RequestMethod.POST)
	public boolean addPatientTest(HttpServletRequest request, HttpServletResponse response, @RequestBody List<PatientTestPojo> dis ) 
	{
		System.out.println("con "+ dis);
		return patientTestService.addPatientTest(dis);
	}
	
	@RequestMapping(value = "/PatientTestAll", method = RequestMethod.GET)
	public List<PatientTestPojo> patientTestAll(HttpServletRequest request, HttpServletResponse response) {
		
		return patientTestService.patientTestAll();
	}
	
	
	@RequestMapping(value = "/PatientTestByPrescriptionNo/{no}", method = RequestMethod.GET)
	public PatientPrescriptionDetailPojo patientTestByPrescriptionNo(HttpServletRequest request, HttpServletResponse response, @PathVariable String no) {
		
		return patientTestService.patientTestByPrescriptionNo(no);
	}
	
	@RequestMapping(value = "/PatientTestId/{id}", method = RequestMethod.GET)
	public PatientTestPojo patientTestById(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
		
		return patientTestService.patientTestById(id);
	}
	
	@RequestMapping(value = "/PatientTestDelete/{pid}", method = RequestMethod.DELETE)
	public int patientTestDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return patientTestService.patientTestDelete(pid);
	}

	@RequestMapping(value = "/PatientTestUpdate", method = RequestMethod.PUT)
	public boolean patientTestUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody PatientTestPojo dt ) {
		return patientTestService.patientTestUpdate(dt);
	}
}
