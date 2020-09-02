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

import com.ast.HealthCare.Pojo.SmsChildPojo;
import com.ast.HealthCare.Service.SmsChlidService;;


@RestController
public class SmsChildController {

	@Autowired
	SmsChlidService smc;

	
	@RequestMapping(value = "/AddSmsChild/{smsid}", method = RequestMethod.POST)
	public boolean addSmsChild(HttpServletRequest request, HttpServletResponse response, @RequestBody List<SmsChildPojo> smcpojo, @PathVariable("smsid") int smsid ) 
	{
		System.out.println("con "+ smcpojo);
		return smc.addSmsChild(smcpojo, smsid);
	}
	
	@RequestMapping(value = "/SmsChildAll", method = RequestMethod.GET)
	public List<SmsChildPojo> smsChildAll(HttpServletRequest request, HttpServletResponse response) {
		
		return smc.smsChildAll();
	}
	
	@RequestMapping(value = "/SmsChildUpdate", method = RequestMethod.PUT)
	public boolean smsChildUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody SmsChildPojo dt ) {
		return smc.smsChildUpdate(dt);
	}
		
	@RequestMapping(value = "/SmsChildDelete/{pid}", method = RequestMethod.DELETE)
	public int smsChildDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return smc.smsChildDelete(pid);
	}
	
	@RequestMapping(value = "/SmsChildById/{id}", method = RequestMethod.GET)
	public List<SmsChildPojo> smsChildById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id) {
		
		return smc.smsChildById(id);
	}

}
