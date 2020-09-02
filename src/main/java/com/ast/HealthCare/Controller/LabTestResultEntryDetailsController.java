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

import com.ast.HealthCare.Pojo.LabTestResultEntryDetailsPojo;
import com.ast.HealthCare.Service.LabTestResultEntryDetailsService;


@RestController
public class LabTestResultEntryDetailsController {

	@Autowired
	LabTestResultEntryDetailsService TNService;
	
	
	@RequestMapping(value = "/AddTestResultEntryDetails", method = RequestMethod.POST)
	public boolean addTestResultEntryDetails(HttpServletRequest request, HttpServletResponse response, @RequestBody List<LabTestResultEntryDetailsPojo> pojo ) 
	{
		System.out.println("con "+ pojo);
		return TNService.addTestResultEntryDetails(pojo);
	}
	
	@RequestMapping(value = "/TestResultEntryDetailsAll", method = RequestMethod.GET)
	public List<LabTestResultEntryDetailsPojo> testResultEntryDetailsAll(HttpServletRequest request, HttpServletResponse response) {
		
		return TNService.testResultEntryDetailsAll();
	}
	
	/*@RequestMapping(value = "/TestResultEntryDetailsDetailsSearchByName/{name}", method = RequestMethod.GET)
	public LabTestResultEntryDetailsDetailsDetailsPojo testResultEntrySearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return TNService.testResultEntrySearchByName(name);
	}*/
	
	@RequestMapping(value = "/TestResultEntryDetailsSearchById/{id}", method = RequestMethod.GET)
	public LabTestResultEntryDetailsPojo testResultEntrySearchById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testResultEntryDetailsSearchById(id);
	}
	
	
	

	
	@RequestMapping(value = "/TestResultEntryDetailsUpdate", method = RequestMethod.PUT)
	public boolean testResultEntryDetailsUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestResultEntryDetailsPojo drug ) {
		return TNService.testResultEntryDetailsUpdate(drug);
	}
	
	@RequestMapping(value = "/TestResultEntryDetailsDelete/{did}", method = RequestMethod.DELETE)
	public int testResultEntryDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return TNService.testResultEntryDetailsDelete(did);
	}
}
