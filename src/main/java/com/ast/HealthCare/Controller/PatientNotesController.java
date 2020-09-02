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

import com.ast.HealthCare.Pojo.PatientNotesPojo;
import com.ast.HealthCare.Service.PatientNotesService;

@RestController
public class PatientNotesController {

	@Autowired
	PatientNotesService pnService;
	
	@RequestMapping(value = "/AddPatientNotes", method = RequestMethod.POST)
	public boolean addPatientNotes(HttpServletRequest request, HttpServletResponse response, @RequestBody List<PatientNotesPojo> patientNotes ) {
		System.out.println("con "+ patientNotes);
		return pnService.addPatientNotes(patientNotes);
	}

	@RequestMapping(value = "/PatientNotesAll", method = RequestMethod.POST)
	public List<PatientNotesPojo> patientNotesAll(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("con "+ patientNotes);
		return pnService.patientNotesAll();
	}
	
	@RequestMapping(value = "/PatientNotesSearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<PatientNotesPojo> patientNotesSearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		
		return pnService.patientNotesSearchByPatientId(pid);
	}

	@RequestMapping(value = "/PatientNotesSearchByPrescriptionId/{pid}", method = RequestMethod.POST)
	public PatientNotesPojo patientNotesSearchByPrescriptionId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		//System.out.println("con "+ patientNotes);
		return pnService.patientNotesSearchByPrescriptionId(pid);
	}

	
}
