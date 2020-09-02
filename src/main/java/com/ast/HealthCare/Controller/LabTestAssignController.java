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

import com.ast.HealthCare.Pojo.LabTestAssignPojo;
import com.ast.HealthCare.Service.LabTestAssignService;

@RestController
public class LabTestAssignController {

	@Autowired
	LabTestAssignService TNService;
	
	
	@RequestMapping(value = "/AddTestAssign", method = RequestMethod.POST)
	public LabTestAssignPojo addTestAssign(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestAssignPojo pojo ) 
	{
		System.out.println("con "+ pojo);
		return TNService.addTestAssign(pojo);
	}
	
	@RequestMapping(value = "/TestAssignAll", method = RequestMethod.GET)
	public List<LabTestAssignPojo> testAssignAll(HttpServletRequest request, HttpServletResponse response) {
		
		return TNService.testAssignAll();
	}
	
	@RequestMapping(value = "/TestAssignSearchByName/{name}", method = RequestMethod.GET)
	public LabTestAssignPojo testAssignSearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return TNService.testAssignSearchByName(name);
	}
	
	@RequestMapping(value = "/TestAssignSearchById/{id}", method = RequestMethod.GET)
	public LabTestAssignPojo testAssignSearchById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testAssignSearchById(id);
	}
	
	@RequestMapping(value = "/TestAssignSearchByTestMasterId/{id}", method = RequestMethod.GET)
	public LabTestAssignPojo testAssignSearchByTestMasterId(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testAssignSearchByTestMasterId(id);
	}
	
	@RequestMapping(value = "/TestAssignUpdate", method = RequestMethod.PUT)
	public boolean testAssignUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestAssignPojo drug ) {
		return TNService.testAssignUpdate(drug);
	}
	
	@RequestMapping(value = "/TestAssignDelete/{did}", method = RequestMethod.DELETE)
	public int testAssignDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return TNService.testAssignDelete(did);
	}

	
}
