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

import com.ast.HealthCare.Pojo.SurgicalTypePojo;
import com.ast.HealthCare.Service.SurgicalTypeService;

@RestController
public class SurgicalTypeController {

	@Autowired
	SurgicalTypeService DTService;
	
	@RequestMapping(value = "/AddSurgicalType", method = RequestMethod.POST)
	public boolean addSurgicalType(HttpServletRequest request, HttpServletResponse response, @RequestBody SurgicalTypePojo drugtype ) 
	{
		System.out.println("con "+ drugtype);
		return DTService.addSurgicalType(drugtype);
	}
	
	@RequestMapping(value = "/SurgicalTypeAll", method = RequestMethod.GET)
	public List<SurgicalTypePojo> surgicalAll(HttpServletRequest request, HttpServletResponse response) {
		
		return DTService.surgicalTypeAll();
	}
	
	
	
	@RequestMapping(value = "/SurgicalTypeSearchById/{id}", method = RequestMethod.GET)
	public SurgicalTypePojo surgicalSearchById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id) {
    System.out.println("working");
    System.out.println(id);
	return DTService.surgicalTypeSearchById(id);
	}
	
	@RequestMapping(value = "/SurgicalTypeUpdate", method = RequestMethod.PUT)
	public boolean surgicalUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody SurgicalTypePojo dt ) {
		return DTService.surgicalTypeUpdate(dt);
	}
	
	@RequestMapping(value = "/SurgicalTypeDelete/{pid}", method = RequestMethod.DELETE)
	public int surgicalDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return DTService.surgicalTypeDelete(pid);
	}
	
}
