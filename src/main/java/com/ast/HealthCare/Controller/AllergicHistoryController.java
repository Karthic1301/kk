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

import com.ast.HealthCare.Pojo.AllergicHistoryPojo;
import com.ast.HealthCare.Service.AllergicHistoryService;

@RestController
public class AllergicHistoryController {

	//CREATE TABLE allergichistory(ALLERGYID SERIAL,PATIENTID TEXT,
	//ALLERGYNAME TEXT,ALLERGYDESCRIPTION TEXT);
	
	@Autowired
	AllergicHistoryService AHService;
	
	
	@RequestMapping(value = "/AddAllergicHistory", method = RequestMethod.POST)
	public boolean addAllergicHistory(HttpServletRequest request, HttpServletResponse response, @RequestBody List<AllergicHistoryPojo> allergic ) 
	{
		System.out.println("con "+ allergic);
		return AHService.addAllergicHistory(allergic);
	}
	
	@RequestMapping(value = "/AllergicHistoryAll", method = RequestMethod.GET)
	public List<AllergicHistoryPojo> allergicHistoryAll(HttpServletRequest request, HttpServletResponse response) {
		
		return AHService.allergicHistoryAll();
	}
	
	@RequestMapping(value = "/AllergicHistorySearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<AllergicHistoryPojo> allergicHistorySearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		System.out.println("con "+pid);
		return AHService.appByDoctor(pid);
	}
	
	
	@RequestMapping(value = "/AllergicHistoryUpdate", method = RequestMethod.PUT)
	public boolean drugTypeUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody AllergicHistoryPojo allergic ) {
		return AHService.allergicHistoryUpdate(allergic);
	}
	
	@RequestMapping(value = "/AllergicHistoryDeleteByAllergyId/{pid}", method = RequestMethod.DELETE)
	public int drugTypeDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return AHService.allergicHistoryDelete(pid);
	}
	
	@RequestMapping(value = "/AllergicHistoryDeleteByPatientId/{pid}", method = RequestMethod.DELETE)
	public int drugTypeDelete1(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid ) {
		System.out.println("con "+pid);
		return AHService.allergicHistoryDelete1(pid);
	}
	
}
