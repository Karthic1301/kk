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

import com.ast.HealthCare.Pojo.LabReportPojo;
import com.ast.HealthCare.Service.LabReportService;

@RestController
public class LabReportController {
	
	@Autowired
	LabReportService TMService;
	
	
	@RequestMapping(value = "/AddReport", method = RequestMethod.POST)
	public boolean addReport(HttpServletRequest request, HttpServletResponse response, @RequestBody LabReportPojo pojo ) 
	{
		System.out.println("con "+ pojo);
		return TMService.addReport(pojo);
	}
	
	@RequestMapping(value = "/ReportAll", method = RequestMethod.GET)
	public List<LabReportPojo> ReportAll(HttpServletRequest request, HttpServletResponse response) {
		
		return TMService.ReportAll();
	}
	
	/*@RequestMapping(value = "/ReportSearchByName/{name}", method = RequestMethod.GET)
	public LabReportPojo ReportSearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return TMService.ReportSearchByName(name);
	}
	*/
	@RequestMapping(value = "/ReportSearchById/{id}", method = RequestMethod.GET)
	public LabReportPojo ReportSearchById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TMService.ReportSearchById(id);
	}
	
	@RequestMapping(value = "/ReportUpdate", method = RequestMethod.PUT)
	public boolean ReportUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LabReportPojo drug ) {
		return TMService.ReportUpdate(drug);
	}
	
	@RequestMapping(value = "/ReportDelete/{did}", method = RequestMethod.DELETE)
	public int ReportDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return TMService.ReportDelete(did);
	}

}
