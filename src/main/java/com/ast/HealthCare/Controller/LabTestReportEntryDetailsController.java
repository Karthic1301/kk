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

import com.ast.HealthCare.Pojo.LabTestReportEntryDetailsPojo;
import com.ast.HealthCare.Service.LabTestReportEntryDetailsService;

@RestController
public class LabTestReportEntryDetailsController {
	
	@Autowired
	LabTestReportEntryDetailsService TNService;
	
	
	@RequestMapping(value = "/AddTestReportEntryDetails", method = RequestMethod.POST)
	public boolean addTestReportEntryDetails(HttpServletRequest request, HttpServletResponse response, @RequestBody List<LabTestReportEntryDetailsPojo> pojo ) 
	{
		System.out.println("con "+ pojo);
		return TNService.addTestReportEntryDetails(pojo);
	}
	
	@RequestMapping(value = "/TestReportEntryDetailsAll", method = RequestMethod.GET)
	public List<LabTestReportEntryDetailsPojo> testReportEntryDetailsAll(HttpServletRequest request, HttpServletResponse response) {
		
		return TNService.testReportEntryDetailsAll();
	}
	
	/*@RequestMapping(value = "/TestReportEntryDetailsSearchByName/{name}", method = RequestMethod.GET)
	public LabTestReportEntryDetailsPojo testReportEntryDetailsSearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return TNService.testReportEntryDetailsSearchByName(name);
	}*/
	
	@RequestMapping(value = "/TestReportEntryDetailsSearchById/{id}", method = RequestMethod.GET)
	public LabTestReportEntryDetailsPojo testReportEntryDetailsSearchById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testReportEntryDetailsSearchById(id);
	}
	@RequestMapping(value = "/TestReportEntryDetailsSearchByReportId/{id}", method = RequestMethod.GET)
	public List<LabTestReportEntryDetailsPojo> testReportEntryDetailsSearchByReportId(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testReportEntryDetailsSearchByReportId(id);
	}
	
	@RequestMapping(value = "/TestReportEntryDetailsUpdate", method = RequestMethod.PUT)
	public boolean testReportEntryDetailsUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestReportEntryDetailsPojo drug ) {
		return TNService.testReportEntryDetailsUpdate(drug);
	}
	
	@RequestMapping(value = "/TestReportEntryDetailsDelete/{did}", method = RequestMethod.DELETE)
	public int testReportEntryDetailsDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return TNService.testReportEntryDetailsDelete(did);
	}

}
