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

import com.ast.HealthCare.Pojo.ComplaintsPojo;
import com.ast.HealthCare.Service.ComplaintsService;


@RestController
public class ComplaintsController {

	@Autowired
	ComplaintsService comp;

	
	@RequestMapping(value = "/AddComplaints", method = RequestMethod.POST)
	public boolean addcomplaints(HttpServletRequest request, HttpServletResponse response, @RequestBody ComplaintsPojo comppojo ) 
	{
		System.out.println("con "+ comppojo);
		return comp.addcomplaints(comppojo);
	}
	
	@RequestMapping(value = "/ComplaintsAll", method = RequestMethod.GET)
	public List<ComplaintsPojo> complaintsAll(HttpServletRequest request, HttpServletResponse response) {
		
		return comp.complaintsAll();
	}
	
	@RequestMapping(value = "/ComplaintsUpdate", method = RequestMethod.PUT)
	public boolean complaintsUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody ComplaintsPojo dt ) {
		return comp.complaintsUpdate(dt);
	}
		
	@RequestMapping(value = "/ComplaintsDelete/{pid}", method = RequestMethod.DELETE)
	public int complaintsDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return comp.complaintsDelete(pid);
	}

}
