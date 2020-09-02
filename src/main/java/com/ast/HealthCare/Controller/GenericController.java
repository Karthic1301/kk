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

import com.ast.HealthCare.Pojo.GenericPojo;
import com.ast.HealthCare.Service.GenericService;

@RestController
public class GenericController {

	@Autowired
	GenericService genService;
	


	@RequestMapping(value = "/AddGeneric", method = RequestMethod.POST)
	public boolean addGeneric(HttpServletRequest request, HttpServletResponse response, @RequestBody GenericPojo gen) 
	{
		System.out.println("con "+ gen);
		return genService.addGeneric(gen);
	}
	
	@RequestMapping(value = "/GenericAll", method = RequestMethod.GET)
	public List<GenericPojo> genericAll(HttpServletRequest request, HttpServletResponse response) {
		return genService.genericAll();
	}
	
	@RequestMapping(value = "/GenericUpdate", method = RequestMethod.PUT)
	public boolean genericUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody GenericPojo dt ) {
		return genService.genericUpdate(dt);
	}

	@RequestMapping(value = "/GenericDelete/{pid}", method = RequestMethod.DELETE)
	public int genericDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return genService.genericDelete(pid);
	}

	
}
