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

import com.ast.HealthCare.Pojo.LabTestNormalPojo;
import com.ast.HealthCare.Service.LabTestNormalService;

@RestController
public class LabTestNormalController {
	
	@Autowired
	LabTestNormalService TNService;
	
	
	@RequestMapping(value = "/AddTestNormal", method = RequestMethod.POST)
	public LabTestNormalPojo addTestNormal(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestNormalPojo pojo ) 
	{
		System.out.println("con "+ pojo);
		return TNService.addTestNormal(pojo);
	}
	
	@RequestMapping(value = "/TestNormalAll", method = RequestMethod.GET)
	public List<LabTestNormalPojo> testNormalAll(HttpServletRequest request, HttpServletResponse response) {
		
		return TNService.testNormalAll();
	}
	
	/*@RequestMapping(value = "/TestNormalSearchByName/{name}", method = RequestMethod.GET)
	public LabTestNormalPojo testNormalSearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return TNService.testNormalSearchByName(name);
	}*/
	
	@RequestMapping(value = "/TestNormalSearchById/{id}", method = RequestMethod.GET)
	public LabTestNormalPojo testNormalSearchById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testNormalSearchById(id);
	}
	
	@RequestMapping(value = "/TestNormalSearchByTestMasterId/{id}", method = RequestMethod.GET)
	public LabTestNormalPojo testNormalSearchByTestMasterId(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testNormalSearchByTestMasterId(id);
	}
	
	@RequestMapping(value = "/TestNormalUpdate", method = RequestMethod.PUT)
	public boolean testNormalUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestNormalPojo drug ) {
		return TNService.testNormalUpdate(drug);
	}
	
	@RequestMapping(value = "/TestNormalDelete/{did}", method = RequestMethod.DELETE)
	public int testNormalDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return TNService.testNormalDelete(did);
	}

}
