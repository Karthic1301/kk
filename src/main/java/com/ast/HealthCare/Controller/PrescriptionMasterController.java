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

import com.ast.HealthCare.Pojo.PrescriptionMasterPojo;
import com.ast.HealthCare.Service.PrescriptionMasterService;

@RestController
public class PrescriptionMasterController {

	@Autowired
	PrescriptionMasterService prescriptionMasterService;
	
	@RequestMapping(value = "/AddPrescriptionMaster", method = RequestMethod.POST)
	public PrescriptionMasterPojo addPrescriptionMaster(HttpServletRequest request, HttpServletResponse response, @RequestBody PrescriptionMasterPojo prescription ) 
	{
		System.out.println("con "+ prescription);
		return prescriptionMasterService.addPrescriptionMaster(prescription);
	}
	
	@RequestMapping(value = "/PrescriptionMasterAll", method = RequestMethod.GET)
	public List<PrescriptionMasterPojo> prescriptionMasterAll(HttpServletRequest request, HttpServletResponse response) {
		
		return prescriptionMasterService.prescriptionMasterAll();
	}
	
	@RequestMapping(value = "/PrescriptionMasterByBillId/{billid}", method = RequestMethod.GET)
	public List<PrescriptionMasterPojo> prescriptionMasterByBillId(HttpServletRequest request, HttpServletResponse response, @PathVariable int billid) {
		
		return prescriptionMasterService.prescriptionMasterByBillId(billid);
	}
	
	@RequestMapping(value = "/PrescriptionMasterSearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<PrescriptionMasterPojo> prescriptionMasterSearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		System.out.println("con "+pid);
		return prescriptionMasterService.prescriptionMasterSearchByPatientId(pid);
	}
	
	@RequestMapping(value = "/PrescriptionMasterSearchByDoctorId/{did}", method = RequestMethod.GET)
	public List<PrescriptionMasterPojo> prescriptionMasterSearchByDoctorId(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") String did) {
		System.out.println("con "+did);
		return prescriptionMasterService.prescriptionMasterSearchByDoctorId(did);
	}
	
	@RequestMapping(value = "/PrescriptionMasterSearchByPrescriptionNo/{pid}", method = RequestMethod.GET)
	public PrescriptionMasterPojo prescriptionMasterSearchByPrescriptionNo(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		System.out.println("con "+pid);
		return prescriptionMasterService.prescriptionMasterSearchByPrescriptionNo(pid);
	}
	@RequestMapping(value = "/PrescriptionMasterSearchByDoctorPatientAndDate/{did}/{pid}/{date}", method = RequestMethod.GET)
	public PrescriptionMasterPojo prescriptionMasterSearchByDoctorPatientAndDate(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") String did,
			 @PathVariable("pid") String pid, @PathVariable("date") Date date) {
		System.out.println("con "+pid);
		return prescriptionMasterService.prescriptionMasterSearchByDoctorPatientAndDate(did,pid,date);
	}
	
	@RequestMapping(value = "/PrescriptionMasterUpdate/{pno}", method = RequestMethod.PUT)
	public Boolean prescriptionMasterUpdate(HttpServletRequest request, HttpServletResponse response, @PathVariable("pno") String pno ,@RequestBody PrescriptionMasterPojo prescription) {
		System.out.println("con "+pno);
		return prescriptionMasterService.prescriptionMasterUpdate(pno,prescription);
	}
	
	@RequestMapping(value = "/PrescriptionMasterDelete/{pno}", method = RequestMethod.DELETE)
	public int prescriptionMasterDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pno") String pno) {
		System.out.println("con "+pno);
		return prescriptionMasterService.prescriptionMasterDelete(pno);
	}
	
	//GET BY PID IN PRESCRIPTION ORDER BY DATE = PRESCRIPTIONID AND PDATE AS OUTPUT
}
