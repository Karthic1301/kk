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

import com.ast.HealthCare.Pojo.FamilyHistoryPojo;
import com.ast.HealthCare.Service.FamilyHistoryService;

@RestController
public class FamilyHistoryController {

	@Autowired
	FamilyHistoryService fhService;

	@RequestMapping(value = "/AddFamilyHistory", method = RequestMethod.POST)
	public boolean addFamilyHistory(HttpServletRequest request, HttpServletResponse response, @RequestBody List<FamilyHistoryPojo> family ) 
	{
		System.out.println("con "+ family);
		return fhService.addFamilyHistory(family);
	}
	
	@RequestMapping(value = "/FamilyHistoryAll", method = RequestMethod.GET)
	public List<FamilyHistoryPojo> fhAll(HttpServletRequest request, HttpServletResponse response) {
		return fhService.fhAll();
	}


	@RequestMapping(value = "/FamilyHistorySearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<FamilyHistoryPojo> familyHistorySearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		System.out.println("con "+pid);
		return fhService.fhSearchByPatientId(pid);
	}
	
	@RequestMapping(value = "/FamilyHistoryDelete/{pid}", method = RequestMethod.DELETE)
	public int fhDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return fhService.fhDelete(pid);
	}
	
	@RequestMapping(value = "/FamilyHistoryUpdate", method = RequestMethod.PUT)
	public boolean fhUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody FamilyHistoryPojo dt ) {
		return fhService.fhUpdate(dt);
	}

}
