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

import com.ast.HealthCare.Pojo.PatientDiagnosisPojo;
import com.ast.HealthCare.Service.PatientDiagnosisService;

@RestController
public class PatientDiagnosisController {

	@Autowired
	PatientDiagnosisService pds;
	
	@RequestMapping(value = "/AddPatientDiagnosis", method = RequestMethod.POST)
	public boolean addDiag(HttpServletRequest request, HttpServletResponse response, @RequestBody List<PatientDiagnosisPojo> diag ) 
	{
		System.out.println("con "+ diag);
		return pds.addDiag(diag);
	}
	
	@RequestMapping(value = "/PatientDiagnosisAll", method = RequestMethod.GET)
	public List<PatientDiagnosisPojo> diagnosisAll(HttpServletRequest request, HttpServletResponse response) {
		
		return pds.diagnosisAll();
	}
	
	@RequestMapping(value = "/PatientDiagnosisSearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<PatientDiagnosisPojo> patientDiagnosisSearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		
		return pds.patientDiagnosisSearchByPatientId(pid);
	}
	
	@RequestMapping(value = "/PatientDiagnosisSearchByPrescriptionId/{pid}", method = RequestMethod.GET)
	public PatientDiagnosisPojo patientDiagnosisSearchByPrescriptionId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid) {
		
		return pds.patientDiagnosisSearchByPrescriptionId(pid);
	}

	@RequestMapping(value = "/PatientDiagnosisDeleteByPatientId/{pid}", method = RequestMethod.DELETE)
	public int diagnosisDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid ) {
		System.out.println("con "+pid);
		return pds.diagnosisDelete(pid);
	}
	
	@RequestMapping(value = "/PatientDiagnosisDeleteByDiagnosisId/{did}", method = RequestMethod.DELETE)
	public int diagnosisDeleteByDiagnosisId(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return pds.diagnosisDeleteByDignosisId(did);
	}
}
