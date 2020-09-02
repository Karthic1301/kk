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

import com.ast.HealthCare.Pojo.InvestigationMasterPojo;
import com.ast.HealthCare.Service.InvestigationMasterService;

@RestController
public class InvestigationMasterController {

	@Autowired
	InvestigationMasterService ims;
	
	@RequestMapping(value = "/AddInvestigation", method = RequestMethod.POST)
	public boolean addInvestigation(HttpServletRequest request, HttpServletResponse response, @RequestBody InvestigationMasterPojo imp ) 
	{
		System.out.println("con "+ imp);
		return ims.addInvestigation(imp);
	}
	
	@RequestMapping(value = "/InvestigationAll", method = RequestMethod.GET)
	public List<InvestigationMasterPojo> investigationAll(HttpServletRequest request, HttpServletResponse response) {
		
		return ims.investigationAll();
	}
	
	@RequestMapping(value = "/InvestigationSearchByAll/{all}", method = RequestMethod.GET)
	public List<InvestigationMasterPojo> investigationSearchByAll(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
    System.out.println("working");
    System.out.println(all);
	return ims.investigationSearchByAll(all);
	}
	
	@RequestMapping(value = "/InvestigationUpdate", method = RequestMethod.PUT)
	public boolean investigationUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody InvestigationMasterPojo dt ) {
		return ims.investigationUpdate(dt);
	}
	
	@RequestMapping(value = "/InvestigationDelete/{pid}", method = RequestMethod.DELETE)
	public int investigationDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return ims.investigationDelete(pid);
	}
}
