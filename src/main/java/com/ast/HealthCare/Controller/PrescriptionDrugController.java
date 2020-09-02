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

import com.ast.HealthCare.Pojo.PrescriptionDrugPojo;
import com.ast.HealthCare.Service.PrescriptionDrugService;


@RestController
public class PrescriptionDrugController {

	
	@Autowired
	PrescriptionDrugService prescriptionDrugService;
	
	@RequestMapping(value = "/AddPrescriptionDrug", method = RequestMethod.POST)
	public Boolean addPrescriptionDrug(HttpServletRequest request, HttpServletResponse response, @RequestBody List<PrescriptionDrugPojo> prescriptionDrug ) {
		return prescriptionDrugService.addPrescriptionDrug(prescriptionDrug);
	}
	
	
	@RequestMapping(value = "/PrescriptionDrugAll", method = RequestMethod.GET)
	public List<PrescriptionDrugPojo> PrescriptionDrugAll(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("con "+ patientNotes);
		return prescriptionDrugService.prescriptionDrugAll();
	}

	@RequestMapping(value = "/PrescriptionDrugSearchByPrescriptionId/{pid}", method = RequestMethod.GET)
	public PrescriptionDrugPojo prescriptionDrugSearchByPrescriptionId(HttpServletRequest request, HttpServletResponse response,@PathVariable("pid") String pid) {
		//System.out.println("con "+ patientNotes);
		return prescriptionDrugService.prescriptionDrugSearchByPrescriptionId(pid);
	}

	@RequestMapping(value = "/PrescriptionDrugSearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<PrescriptionDrugPojo> prescriptionDrugSearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		
		return prescriptionDrugService.prescriptionDrugSearchByPatientId(pid);
	}
	
	@RequestMapping(value = "/PrescriptionDrugDelete/{pid}", method = RequestMethod.DELETE)
	public int prescriptionDrugDelete(HttpServletRequest request, HttpServletResponse response,@PathVariable("pid") String pid) {
		//System.out.println("con "+ patientNotes);
		return prescriptionDrugService.prescriptionDrugDelete(pid);
	}

	
}
