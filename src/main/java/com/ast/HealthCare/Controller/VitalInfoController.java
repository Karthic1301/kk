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

import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.VitalInfoPojo;
import com.ast.HealthCare.Service.VitalInfoService;

@RestController
public class VitalInfoController {

	@Autowired
	VitalInfoService vitalService;
	
	@RequestMapping(value = "/AddVitalInfo", method = RequestMethod.POST)
	public boolean addVitalInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody VitalInfoPojo vital ) 
	{
		System.out.println("con "+ vital);
		return vitalService.addVitalInfo(vital);
	}
	

	@RequestMapping(value = "/VitalInfoAll/{vdate}", method = RequestMethod.GET)
	public List<PatientPojo> vitalInfoAll(HttpServletRequest request, HttpServletResponse response,@PathVariable("vdate") Date vdate) {	
		return vitalService.vitalInfoAll(vdate);
	}
	

	@RequestMapping(value = "/VitalInfoUpdate", method = RequestMethod.PUT)
	public boolean vitalInfoUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody VitalInfoPojo dt ) {
		return vitalService.vitalInfoUpdate(dt);
	}

	@RequestMapping(value = "/VitalInfoSearchByPatientIdAndDate/{pid}/{vitaldate}", method = RequestMethod.GET)
	public VitalInfoPojo searchByPidAndDate(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid, @PathVariable("vitaldate") Date vitaldate) {
		System.out.println("con "+ vitaldate);
		return vitalService.searchByPidAndDate(pid,vitaldate);
	}
	
	@RequestMapping(value = "/VitalInfoSearchByPatientIdAndLatestDate/{pid}", method = RequestMethod.GET)
	public VitalInfoPojo searchByPidLatestDate(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		System.out.println("con "+pid);
		return vitalService.searchByPidLatestDate(pid);
	}
	

	@RequestMapping(value = "/VitalInfoSearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<VitalInfoPojo> searchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		System.out.println("con "+pid);
		return vitalService.searchByPatientId(pid);
	}
	
	@RequestMapping(value = "/VitalInfoSearchByVitalInfoId/{vid}", method = RequestMethod.GET)
	public VitalInfoPojo searchByVitalInfoId(HttpServletRequest request, HttpServletResponse response, @PathVariable("vid") String vid) {
		System.out.println("con "+vid);
		return vitalService.searchByVitalInfoId(vid);
	}

	@RequestMapping(value = "/VitalInfoDelete/{pno}", method = RequestMethod.DELETE)
	public int vitalInfoDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pno") String pno) {
		System.out.println("con "+pno);
		return vitalService.vitalInfoDelete(pno);
	}
	//SEARCH BY DATE AND PID, VITALINFOID, PID, THEN UPDATE, GET BY PID IN PRESCRIPTION ORDER BY DATE = PRESCRIPTIONID AND PDATE AS OUTPUT
	
}
