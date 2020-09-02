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

import com.ast.HealthCare.Pojo.DoctorTestMasterPojo;
import com.ast.HealthCare.Service.DoctorTestMasterService;


@RestController
public class DoctorTestMasterController {

	@Autowired
	DoctorTestMasterService doctorTestMasterService;
	

	@RequestMapping(value = "/AddDoctorTestMaster", method = RequestMethod.POST)
	public boolean addDoctorTestMasterMaster(HttpServletRequest request, HttpServletResponse response, @RequestBody DoctorTestMasterPojo dis ) 
	{
		System.out.println("con "+ dis);
		return doctorTestMasterService.addDoctorTestMaster(dis);
	}
	
	@RequestMapping(value = "/DoctorTestMasterAll", method = RequestMethod.GET)
	public List<DoctorTestMasterPojo> doctorDoctorTestMasterMasterAll(HttpServletRequest request, HttpServletResponse response) {
		
		return doctorTestMasterService.doctorTestMasterAll();
	}
	
	@RequestMapping(value = "/GetAmountByDoctorIdAndTestId/{did}/{tid}", method = RequestMethod.GET)
	public DoctorTestMasterPojo getAmountByDoctorIdAndTestId(HttpServletRequest request, HttpServletResponse response,@PathVariable String did ,@PathVariable int tid ) {
		
		return doctorTestMasterService.getAmountByDoctorIdAndTestId(did,tid);
	}
	
	@RequestMapping(value = "/DoctorTestMasterDelete/{pid}", method = RequestMethod.DELETE)
	public int doctorDoctorTestMasterDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return doctorTestMasterService.doctorTestMasterDelete(pid);
	}

	@RequestMapping(value = "/DoctorTestMasterUpdate", method = RequestMethod.PUT)
	public boolean doctorDoctorTestMasterUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody DoctorTestMasterPojo dt ) {
		return doctorTestMasterService.doctorTestMasterUpdate(dt);
	}
	
}
