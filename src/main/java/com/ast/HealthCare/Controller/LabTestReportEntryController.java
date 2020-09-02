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

import com.ast.HealthCare.Pojo.LabTestReportEntryPojo;
import com.ast.HealthCare.Service.LabTestReportEntryService;

@RestController
public class LabTestReportEntryController {
	
	@Autowired
	LabTestReportEntryService TNService;
	
	
	@RequestMapping(value = "/AddTestReportEntry", method = RequestMethod.POST)
	public LabTestReportEntryPojo addTestReportEntry(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestReportEntryPojo pojo ) 
	{
		System.out.println("con "+ pojo);
		return TNService.addTestReportEntry(pojo);
	}
	
	@RequestMapping(value = "/TestReportEntryAll", method = RequestMethod.GET)
	public List<LabTestReportEntryPojo> testReportEntryAll(HttpServletRequest request, HttpServletResponse response) {
		
		return TNService.testReportEntryAll();
	}
	
	@RequestMapping(value = "/TestReportEntryNotIssuedStatus", method = RequestMethod.GET)
	public List<LabTestReportEntryPojo> testReportEntryNotIssuedStatus(HttpServletRequest request, HttpServletResponse response) {
		
		return TNService.testReportEntryNotIssuedStatus();
	}
	
	@RequestMapping(value = "/GetAutoGenerateReportNo", method = RequestMethod.GET)
	public int getAutoGenerateReportNo(HttpServletRequest request, HttpServletResponse response) {
		
		return TNService.getAutoGenerateReportNo();
	}
	@RequestMapping(value = "/TestReportEntryListWithoutResult", method = RequestMethod.GET)
	public List<LabTestReportEntryPojo> testReportEntryListWithoutResult(HttpServletRequest request, HttpServletResponse response) {
		
		return TNService.testReportEntryListWithoutResult();
	}
	
	@RequestMapping(value = "/TestReportEntryListByIssueDate/{from}/{to}", method = RequestMethod.GET)
	public List<LabTestReportEntryPojo> testReportEntryListByIssueDate(HttpServletRequest request, HttpServletResponse response,@PathVariable("from") Date from,@PathVariable("to") Date to) {
		
		return TNService.testReportEntryListByIssueDate(from,to);
	}
	
	/*@RequestMapping(value = "/TestReportEntrySearchByName/{name}", method = RequestMethod.GET)
	public LabTestReportEntryPojo testReportEntrySearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return TNService.testReportEntrySearchByName(name);
	}*/
	
	@RequestMapping(value = "/TestReportEntrySearchById/{id}", method = RequestMethod.GET)
	public LabTestReportEntryPojo testReportEntrySearchById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TNService.testReportEntrySearchById(id);
	}
	
	@RequestMapping(value = "/TestReportEntrySearchByReportNo/{no}", method = RequestMethod.GET)
	public LabTestReportEntryPojo testReportEntrySearchByReportNo(HttpServletRequest request, HttpServletResponse response,@PathVariable("no") String no) {
		
		return TNService.testReportEntrySearchByReportNo(no);
	}
	
	
	
	@RequestMapping(value = "/TestReportEntryUpdate", method = RequestMethod.PUT)
	public boolean testReportEntryUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestReportEntryPojo drug ) {
		return TNService.testReportEntryUpdate(drug);
	}
	

	@RequestMapping(value = "/TestReportEntryUpdateBalance", method = RequestMethod.PUT)
	public boolean testReportEntryUpdateBalance(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestReportEntryPojo drug ) {
		return TNService.testReportEntryUpdateBalance(drug);
	}
	
	@RequestMapping(value = "/TestReportEntryDelete/{did}", method = RequestMethod.DELETE)
	public int testReportEntryDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return TNService.testReportEntryDelete(did);
	}

}
