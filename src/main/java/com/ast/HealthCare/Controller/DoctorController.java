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

import com.ast.HealthCare.Pojo.DoctorPojo;
import com.ast.HealthCare.Service.DoctorService;

@RestController
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@RequestMapping(value = "/AddDoctor", method = RequestMethod.POST)
	public boolean addDoctor(HttpServletRequest request, HttpServletResponse response, @RequestBody DoctorPojo doctor ) {
		System.out.println("con "+ doctor);
		return doctorService.addDoctor(doctor);
	}
	
	
	@RequestMapping(value = "/DoctorAll", method = RequestMethod.GET)
	public List<DoctorPojo> doctorAll(HttpServletRequest request, HttpServletResponse response) {
		
		return doctorService.doctorAll();
	}
	

	@RequestMapping(value = "/DoctorDelete/{pid}", method = RequestMethod.DELETE)
	public int doctorDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid ) {
		System.out.println("con "+pid);
		return doctorService.doctorDelete(pid);
	}
	

	@RequestMapping(value = "/DoctorUpdate/{pid}", method = RequestMethod.PUT)
	public boolean doctorUpdate(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid ,@RequestBody DoctorPojo doctor ) {
		System.out.println("con "+pid);
		doctor.setDoctorId(pid);
		return doctorService.doctorUpdate(doctor);
	}
	

	@RequestMapping(value = "/DoctorSearchById/{pid}", method = RequestMethod.GET)
	public DoctorPojo doctorSearchById(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		System.out.println("con "+pid);
		return doctorService.doctorSearchById(pid);
	}
	
	@RequestMapping(value = "/DoctorSearchByName/{pname}", method = RequestMethod.GET)
	public List<DoctorPojo> doctorSearchByName(HttpServletRequest request, HttpServletResponse response, @PathVariable("pname") String pname) {
		System.out.println("con "+pname);
		return doctorService.doctorSearchByName(pname);
	}
	
	@RequestMapping(value = "/DoctorSearchBySpec/{spec}", method = RequestMethod.GET)
	public List<DoctorPojo> doctorSearchBySpec(HttpServletRequest request, HttpServletResponse response, @PathVariable("spec") String spec) {
		System.out.println("con "+spec);
		return doctorService.doctorSearchBySpec(spec);
	}
	
	@RequestMapping(value = "/DoctorSearchByPhoneNo/{pno}", method = RequestMethod.GET)
	public List<DoctorPojo> doctorSearchByPhoneNo(HttpServletRequest request, HttpServletResponse response, @PathVariable("pno") String pno) {
		System.out.println("con "+pno);
		return doctorService.doctorSearchByPhoneNo(pno);
	}
	
	@RequestMapping(value = "/DoctorSearchByAll/{all}", method = RequestMethod.GET)
	public List<DoctorPojo> doctorSearchByAll(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
		System.out.println("con "+all);
		return doctorService.doctorSearchByAll(all);
	}
	
	@RequestMapping(value = "/DoctorSearchByAllForSMS/{all}", method = RequestMethod.GET)
	public List<DoctorPojo> doctorSearchByAllForSMS(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
		System.out.println("con "+all);
		return doctorService.doctorSearchByAllForSMS(all);
	}
	
	@RequestMapping(value = "/GetBasicDetailDoctorAll", method = RequestMethod.GET)
	public List<DoctorPojo> getBasicDetailDoctorAll(HttpServletRequest request, HttpServletResponse response) {
		
		return doctorService.getBasicDetailDoctorAll();
	}
	
}
