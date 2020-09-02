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

import com.ast.HealthCare.Pojo.LabTestAssignDetailsPojo;
import com.ast.HealthCare.Service.LabTestAssignDetailsService;

@RestController
public class LabTestAssignDetailsController {

	@Autowired
	LabTestAssignDetailsService TNService;
	
	
	@RequestMapping(value = "/AddTestAssignDetails", method = RequestMethod.POST)
	public boolean addTestAssignDetails(HttpServletRequest request, HttpServletResponse response, @RequestBody List<LabTestAssignDetailsPojo> pojo ) 
	{
		System.out.println("con "+ pojo);
		return TNService.addTestAssignDetails(pojo);
	}
	
	@RequestMapping(value = "/TestAssignDetailsAll", method = RequestMethod.GET)
	public List<LabTestAssignDetailsPojo> testAssignDetailsAll(HttpServletRequest request, HttpServletResponse response) {
		
		return TNService.testAssignDetailsAll();
	}
	
	/*@RequestMapping(value = "/TestAssignDetailsSearchByName/{name}", method = RequestMethod.GET)
	public LabTestAssignDetailsPojo testAssignDetailsSearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return TNService.testAssignDetailsSearchByName(name);
	}*/
	
	@RequestMapping(value = "/TestAssignDetailsSearchById/{id}", method = RequestMethod.GET)
	public LabTestAssignDetailsPojo testAssignDetailsSearchById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testAssignDetailsSearchById(id);
	}
	
	@RequestMapping(value = "/TestAssignDetailsSearchByLabTestId/{id}", method = RequestMethod.GET)
	public List<LabTestAssignDetailsPojo> testAssignDetailsSearchByLabTestId(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testAssignDetailsSearchByLabTestId(id);
	}
	
	@RequestMapping(value = "/TestAssignDetailsUpdate", method = RequestMethod.PUT)
	public boolean testAssignDetailsUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestAssignDetailsPojo drug ) {
		return TNService.testAssignDetailsUpdate(drug);
	}
	
	@RequestMapping(value = "/TestAssignDetailsDelete/{did}", method = RequestMethod.DELETE)
	public int testAssignDetailsDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return TNService.testAssignDetailsDelete(did);
	}

}
