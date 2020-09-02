package com.ast.HealthCare.Controller;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Service.PatientService;

@RestController
public class PatientController implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	PatientService patientService;
	
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, HttpServletResponse response, @RequestBody PatientPojo patient ) throws ParseException {
		System.out.println("con "+patient);
		return patientService.register(patient);
	}
	
	
	@RequestMapping(value = "/PatientDelete/{pid}", method = RequestMethod.DELETE)
	public int patientDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid ) {
		System.out.println("con "+pid);
		return patientService.patientDelete(pid);
	}
	
	@RequestMapping(value = "/PatientSearchById/{pid}", method = RequestMethod.GET)
	public PatientPojo patientSearchById(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		System.out.println("con "+pid);
		return patientService.patientSearchById(pid);
	}
	
	@RequestMapping(value = "/PatientSearchByName/{pname}", method = RequestMethod.GET)
	public List<PatientPojo> patientSearchByName(HttpServletRequest request, HttpServletResponse response, @PathVariable("pname") String pname) {
		System.out.println("con "+pname);
		return patientService.patientSearchByName(pname);
	}
	
	@RequestMapping(value = "/PatientSearchByPhoneNo/{pno}", method = RequestMethod.GET)
	public List<PatientPojo> patientSearchByPhoneNo(HttpServletRequest request, HttpServletResponse response, @PathVariable("pno") String pno) {
		System.out.println("con "+pno);
		return patientService.patientSearchByPhoneNo(pno);
	}
	
	
	@RequestMapping(value = "/PatientUpdate/{pid}", method = RequestMethod.PUT)
	public boolean patientUpdate(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid ,@RequestBody PatientPojo patient ) {
		System.out.println("con "+pid);
		patient.setPatientId(pid);
		return patientService.patientUpdate(patient);
	}
	
	@RequestMapping(value = "/PatientAll", method = RequestMethod.GET)
	public List<PatientPojo> patientAll(HttpServletRequest request, HttpServletResponse response) {
		
		return patientService.patientAll();
	}
	
	@RequestMapping(value = "/PatientSearchByAll/{all}", method = RequestMethod.GET)
	public List<PatientPojo> patientSearchByAll(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
		System.out.println("con "+ all);
		return patientService.patientSearchByAll(all);
	}
	
	@RequestMapping(value = "/PatientSearchByDate/{date1}/{date2}", method = RequestMethod.GET)
	public List<PatientPojo> patientSearchByDate(HttpServletRequest request, HttpServletResponse response,@PathVariable("date1") Date date1,@PathVariable("date2") Date date2) {
		System.out.println("con by date ");
		return patientService.patientSearchByDate(date1,date2);
	}
	
	@RequestMapping(value = "/PatientSearchByAllForSMS/{all}", method = RequestMethod.GET)
	public List<PatientPojo> patientSearchByAllForSMS(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
		System.out.println("con "+ all);
		return patientService.patientSearchByAllForSMS(all);
	}
}
