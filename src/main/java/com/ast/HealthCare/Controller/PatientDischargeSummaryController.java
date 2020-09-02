package com.ast.HealthCare.Controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Pojo.PatientDischargeSummaryPojo;
import com.ast.HealthCare.Service.PatientDischargeSummaryService;

@RestController
public class PatientDischargeSummaryController {

	@Autowired
	PatientDischargeSummaryService patientDischargeSummaryService;
	

	@RequestMapping(value = "/AddPatientDischargeSummary", method = RequestMethod.POST)
	public PatientDischargeSummaryPojo addPatientDischargeSummaryMaster(HttpServletRequest request, HttpServletResponse response, @RequestBody PatientDischargeSummaryPojo dis ) 
	{
		System.out.println("con "+ dis);
		return patientDischargeSummaryService.addPatientDischargeSummary(dis);
	}
	
	@RequestMapping(value = "/PatientDischargeSummaryAll", method = RequestMethod.GET)
	public List<PatientDischargeSummaryPojo> doctorPatientDischargeSummaryMasterAll(HttpServletRequest request, HttpServletResponse response) {
		
		return patientDischargeSummaryService.patientDischargeSummaryAll();
	}
	
	@RequestMapping(value = "/PatientDischargeSummaryByDischargeDate/{from}/{to}", method = RequestMethod.GET)
	public List<PatientDischargeSummaryPojo> patientDischargeSummaryByDischargeDate(HttpServletRequest request, HttpServletResponse response,@PathVariable Date from,@PathVariable Date to) {
		
		return patientDischargeSummaryService.patientDischargeSummaryByDischargeDate(from,to);
	}
	
	@RequestMapping(value = "/GetAutoGenerateIPNo/{typeid}", method = RequestMethod.GET)
	public PatientDischargeSummaryPojo getAutoGenerateIPNo(HttpServletRequest request, HttpServletResponse response,@PathVariable int typeid) {
		
		return patientDischargeSummaryService.getAutoGenerateIPNo(typeid);
	}
	
	@RequestMapping(value = "/PatientDischargeSummaryByPatientId/{id}", method = RequestMethod.GET)
	public PatientDischargeSummaryPojo patientDischargeSummaryByPatientId(HttpServletRequest request, HttpServletResponse response,@PathVariable String id) {
		
		return patientDischargeSummaryService.patientDischargeSummaryByPatientId(id);
	}
	
	@RequestMapping(value = "/PatientDischargeSummaryById/{id}", method = RequestMethod.GET)
	public PatientDischargeSummaryPojo patientDischargeSummaryById(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) {
		
		return patientDischargeSummaryService.patientDischargeSummaryById(id);
	}
	
	@RequestMapping(value = "/PatientDischargeSummaryDelete/{pid}", method = RequestMethod.DELETE)
	public int doctorPatientDischargeSummaryDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return patientDischargeSummaryService.patientDischargeSummaryDelete(pid);
	}

	@RequestMapping(value = "/PatientDischargeSummaryUpdate", method = RequestMethod.PUT)
	public PatientDischargeSummaryPojo doctorPatientDischargeSummaryUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody PatientDischargeSummaryPojo dt ) {
		return patientDischargeSummaryService.patientDischargeSummaryUpdate(dt);
	}
}
