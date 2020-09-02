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

import com.ast.HealthCare.Pojo.LabTestNormalDetailsPojo;
import com.ast.HealthCare.Service.LabTestNormalDetailsService;

@RestController
public class LabTestNormalDetailsController {

	@Autowired
	LabTestNormalDetailsService TNService;
	
	
	@RequestMapping(value = "/AddTestNormalDetails", method = RequestMethod.POST)
	public boolean addTestNormalDetails(HttpServletRequest request, HttpServletResponse response, @RequestBody List<LabTestNormalDetailsPojo> pojo ) 
	{
		System.out.println("con "+ pojo);
		return TNService.addTestNormalDetails(pojo);
	}
	
	@RequestMapping(value = "/TestNormalDetailsAll", method = RequestMethod.GET)
	public List<LabTestNormalDetailsPojo> testNormalDetailsAll(HttpServletRequest request, HttpServletResponse response) {
		
		return TNService.testNormalDetailsAll();
	}
	
	/*@RequestMapping(value = "/TestNormalDetailsSearchByName/{name}", method = RequestMethod.GET)
	public LabTestNormalDetailsPojo testNormalDetailsSearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return TNService.testNormalDetailsSearchByName(name);
	}*/
	
	@RequestMapping(value = "/TestNormalDetailsSearchById/{id}", method = RequestMethod.GET)
	public LabTestNormalDetailsPojo testNormalDetailsSearchById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testNormalDetailsSearchById(id);
	}
	
	@RequestMapping(value = "/TestNormalDetailsUpdate", method = RequestMethod.PUT)
	public boolean testNormalDetailsUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestNormalDetailsPojo drug ) {
		return TNService.testNormalDetailsUpdate(drug);
	}
	
	@RequestMapping(value = "/TestNormalDetailsDelete/{did}", method = RequestMethod.DELETE)
	public int testNormalDetailsDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return TNService.testNormalDetailsDelete(did);
	}
}
