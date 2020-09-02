package com.ast.HealthCare.Controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Pojo.LabTestResultEntryPojo;
import com.ast.HealthCare.Service.LabTestResultEntryService;

@RestController
public class LabTestResultEntryController {

	@Autowired
	LabTestResultEntryService TNService;
	
	
	@RequestMapping(value = "/AddTestResultEntry", method = RequestMethod.POST)
	public LabTestResultEntryPojo addTestResultEntry(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestResultEntryPojo pojo ) 
	{
		System.out.println("con "+ pojo);
		return TNService.addTestResultEntry(pojo);
	}
	
	@RequestMapping(value = "/TestResultEntryAll", method = RequestMethod.GET)
	public List<LabTestResultEntryPojo> testResultEntryAll(HttpServletRequest request, HttpServletResponse response) {
		
		return TNService.testResultEntryAll();
	}
	
	/*@RequestMapping(value = "/TestResultEntrySearchByName/{name}", method = RequestMethod.GET)
	public LabTestResultEntryPojo testResultEntrySearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return TNService.testResultEntrySearchByName(name);
	}*/
	
	@RequestMapping(value = "/TestResultEntrySearchById/{id}", method = RequestMethod.GET)
	public LabTestResultEntryPojo testResultEntrySearchById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testResultEntrySearchById(id);
	}
	
	@RequestMapping(value = "/TestResultEntryByReportDate/{from}/{to}", method = RequestMethod.GET)
	public List<LabTestResultEntryPojo> testResultEntryByReportDate(HttpServletRequest request, HttpServletResponse response,@PathVariable("from") Date from,@PathVariable("to") Date to) {
		
		return TNService.testResultEntryByReportDate(from,to);
	}
	
	@RequestMapping(value = "/TestResultEntryByReportDateAndTest/{from}/{to}/{testid}", method = RequestMethod.GET)
	public List<LabTestResultEntryPojo> testResultEntryByReportDateAndTest(HttpServletRequest request, HttpServletResponse response,@PathVariable("from") Date from,@PathVariable("to") Date to,@PathVariable("testid") int testid) {
		
		return TNService.testResultEntryByReportDateAndTest(from,to,testid);
	}

	
	@RequestMapping(value = "/TestResultEntryUpdate", method = RequestMethod.PUT)
	public boolean testResultEntryUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestResultEntryPojo drug ) {
		return TNService.testResultEntryUpdate(drug);
	}
	
	@RequestMapping(value = "/TestResultEntryDelete/{did}", method = RequestMethod.DELETE)
	public int testResultEntryDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return TNService.testResultEntryDelete(did);
	}
	
}
