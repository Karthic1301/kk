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

import com.ast.HealthCare.Pojo.StaffPojo;
import com.ast.HealthCare.Service.StaffService;

@RestController
public class StaffController {

	@Autowired
	StaffService staffService;
	
	@RequestMapping(value = "/AddStaff", method = RequestMethod.POST)
	public Boolean addStaff(HttpServletRequest request, HttpServletResponse response, @RequestBody StaffPojo staff ) {
		return staffService.addStaff(staff);
	}
	
	@RequestMapping(value = "/StaffAll", method = RequestMethod.GET)
	public List<StaffPojo> viewAllStaff(HttpServletRequest request, HttpServletResponse response){
		return staffService.viewAllStaff();
	}
	
	@RequestMapping(value = "/StaffSearchById/{sid}", method = RequestMethod.GET)
	public StaffPojo staffSearchById(HttpServletRequest request, HttpServletResponse response, @PathVariable("sid") String sid) {
		System.out.println("con "+sid);
		return staffService.staffSearchById(sid);
	}
	
	@RequestMapping(value = "/StaffSearchByName/{sname}", method = RequestMethod.GET)
	public List<StaffPojo> staffSearchByName(HttpServletRequest request, HttpServletResponse response, @PathVariable("sname") String sname) {
		System.out.println("con "+sname);
		return staffService.staffSearchByName(sname);
	}
	
	@RequestMapping(value = "/StaffSearchByPhoneNo/{sphone}", method = RequestMethod.GET)
	public List<StaffPojo> staffSearchByPhoneNo(HttpServletRequest request, HttpServletResponse response, @PathVariable("sphone") String sphone) {
		System.out.println("con "+sphone);
		return staffService.staffSearchByPhoneNo(sphone);
	}
	
	@RequestMapping(value = "/StaffDelete/{sid}", method = RequestMethod.DELETE)
	public int staffDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("sid") String sid ) {
		System.out.println("con "+sid);
		return staffService.staffDelete(sid);
	}
	
	@RequestMapping(value = "/StaffUpdate/{sid}", method = RequestMethod.PUT)
	public boolean patientUpdate(HttpServletRequest request, HttpServletResponse response, @PathVariable("sid") String sid ,@RequestBody StaffPojo staff ) {
		System.out.println("con "+sid);
		staff.setStaffId(sid);
		return staffService.staffUpdate(staff);
	}

	@RequestMapping(value = "/StaffSearchByAll/{all}", method = RequestMethod.GET)
	public List<StaffPojo> staffSearchByAll(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
		System.out.println("con "+all);
		return staffService.staffSearchByAll(all);
	}
	
	@RequestMapping(value = "/StaffSearchByAllForSMS/{all}", method = RequestMethod.GET)
	public List<StaffPojo> staffSearchByAllForSMS(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
		System.out.println("con "+all);
		return staffService.staffSearchByAllForSMS(all);
	}
	
}
