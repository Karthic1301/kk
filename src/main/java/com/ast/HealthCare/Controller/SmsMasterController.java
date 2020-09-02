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

import com.ast.HealthCare.Dao.SMSSettingDao;
import com.ast.HealthCare.Dao.SmsMasterDao;
import com.ast.HealthCare.Pojo.SmsMasterPojo;
import com.ast.HealthCare.Service.SmsMasterService;

@RestController
public class SmsMasterController {
	
	@Autowired
	SmsMasterService sms;
	@Autowired
	SMSSettingDao ssd;
	
	@RequestMapping(value = "/Addsmsmaster", method = RequestMethod.POST)
	public int addsmsmaster(HttpServletRequest request, HttpServletResponse response, @RequestBody SmsMasterPojo smp ) 
	{
		System.out.println("con "+ smp);
		return sms.addsmsmaster(smp);
	}
	
	@RequestMapping(value = "/SmsMasterAll", method = RequestMethod.GET)
	public List<SmsMasterPojo> smsMasterAll(HttpServletRequest request, HttpServletResponse response) {
		//String content = "hello";
		ssd.birthdaysms();
		return sms.smsMasterAll();
	}
	
	@RequestMapping(value = "/SmsMasterById/{id}", method = RequestMethod.GET)
	public SmsMasterPojo smsMasterById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id ) {
		
		return sms.smsMasterById(id);
	}
	
	@RequestMapping(value = "/SmsMasterUpdate", method = RequestMethod.PUT)
	public boolean smsMasterUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody SmsMasterPojo dt ) {
		return sms.smsMasterUpdate(dt);
	}
		
	@RequestMapping(value = "/SmsMasterDelete/{id}", method = RequestMethod.DELETE)
	public int smsMasterDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id ) {
		System.out.println("con "+id);
		return sms.smsMasterDelete(id);
	}


}
