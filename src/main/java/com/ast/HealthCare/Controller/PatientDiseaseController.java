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

import com.ast.HealthCare.Pojo.PatientDiseasePojo;
import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Service.PatientDiseaseService;

@RestController
public class PatientDiseaseController {

	@Autowired
	PatientDiseaseService patientDiseaseService; 	
	
	@RequestMapping(value = "/AddPatientDisease", method = RequestMethod.POST)
	public Boolean addPatientDisease(HttpServletRequest request, HttpServletResponse response, @RequestBody List<PatientDiseasePojo> patientDiease ) {
		return patientDiseaseService.addPatientDisease(patientDiease);
	}
	
	@RequestMapping(value = "/PatientDiseaseAll", method = RequestMethod.GET)
	public List<PatientDiseasePojo> patientDiseaseAll(HttpServletRequest request, HttpServletResponse response) {
		return patientDiseaseService.patientDiseaseAll();
	}
	
	@RequestMapping(value = "/PatientDiseaseSearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<PatientDiseasePojo> patientDiseaseSearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		return patientDiseaseService.patientDiseaseSearchByPatientId(pid);
	}
	
	@RequestMapping(value = "/PatientDiseaseSearchByPatientIdAndDiseaseId/{pid}/{did}", method = RequestMethod.GET)
	public List<PatientDiseasePojo> patientDiseaseSearchByPatientIdAndDiseaseId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid, @PathVariable("did") int did) {
		return patientDiseaseService.patientDiseaseSearchByPatientIdAndDiseaseId(pid,did);
	}
	

	@RequestMapping(value = "/PatientDiseaseDelete/{pid}/{did}", method = RequestMethod.DELETE)
	public int patientDiseaseDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid,@PathVariable("did") int did) {
		return patientDiseaseService.patientDiseaseDelete(pid,did);
	}
	
	@RequestMapping(value = "/PatientDiseaseSearchByDiseaseIdForSMS/{pid}", method = RequestMethod.GET)
	public List<PatientPojo> patientDiseaseSearchByDiseaseIdForSMS(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid) {
		System.out.println(pid);
		return patientDiseaseService.patientDiseaseSearchByDiseaseIdForSMS(pid);
	}
	
	
}
