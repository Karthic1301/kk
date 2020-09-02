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

import com.ast.HealthCare.Pojo.SettingPojo;
import com.ast.HealthCare.Service.SettingService;

@RestController
public class SettingController {

	@Autowired
	SettingService settingService;
	

	@RequestMapping(value = "/AddSetting", method = RequestMethod.POST)
	public boolean addSettingMaster(HttpServletRequest request, HttpServletResponse response, @RequestBody SettingPojo dis ) 
	{
		System.out.println("con "+ dis);
		return settingService.addSetting(dis);
	}
	
	@RequestMapping(value = "/SettingAll", method = RequestMethod.GET)
	public List<SettingPojo> doctorSettingMasterAll(HttpServletRequest request, HttpServletResponse response) {
		
		return settingService.settingAll();
	}
	
	@RequestMapping(value = "/SettingById/{id}", method = RequestMethod.GET)
	public SettingPojo settingById(HttpServletRequest request, HttpServletResponse response,@PathVariable int id ) {
		
		return settingService.settingById(id);
	}
	
	@RequestMapping(value = "/SettingDelete/{pid}", method = RequestMethod.DELETE)
	public int doctorSettingDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return settingService.settingDelete(pid);
	}

	@RequestMapping(value = "/SettingUpdate", method = RequestMethod.PUT)
	public boolean doctorSettingUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody SettingPojo dt ) {
		return settingService.settingUpdate(dt);
	}
}
