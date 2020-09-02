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

import com.ast.HealthCare.Pojo.FindingsPojo;
import com.ast.HealthCare.Service.FindingsService;

@RestController
public class FindingsController {

	@Autowired
	FindingsService fService;
	
	@RequestMapping(value = "/AddFindings", method = RequestMethod.POST)
	public boolean addFindings(HttpServletRequest request, HttpServletResponse response, @RequestBody FindingsPojo findings ) 
	{
		System.out.println("con "+ findings);
		return fService.addFindings(findings);
	}
	
	@RequestMapping(value = "/FindingsAll", method = RequestMethod.GET)
	public List<FindingsPojo> findingsAll(HttpServletRequest request, HttpServletResponse response) {
		return fService.findingsAll();
	}
	
	@RequestMapping(value = "/FindingsUpdate", method = RequestMethod.PUT)
	public boolean findingsUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody FindingsPojo dt ) {
		return fService.findingsUpdate(dt);
	}

	@RequestMapping(value = "/FindingsDelete/{pid}", method = RequestMethod.DELETE)
	public int FindingsDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return fService.findingsDelete(pid);
	}
	
	
}
