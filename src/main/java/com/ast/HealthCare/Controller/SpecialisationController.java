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

import com.ast.HealthCare.Pojo.SpecialisationPojo;
import com.ast.HealthCare.Service.SpecialisationService;

@RestController
public class SpecialisationController {

		@Autowired
		SpecialisationService spService;

		@RequestMapping(value = "/SpecialisationAll", method = RequestMethod.GET)
		public List<SpecialisationPojo> specialisationAll(HttpServletRequest request, HttpServletResponse response) {
			
			return spService.specialisationAll();
		}
		
		@RequestMapping(value = "/AddSpecialisation", method = RequestMethod.POST)
		public boolean addSpecialisation(HttpServletRequest request, HttpServletResponse response, @RequestBody SpecialisationPojo sppojo ) 
		{
			System.out.println("con "+ sppojo);
			return spService.addSpecialisation(sppojo);
		}
		
		@RequestMapping(value = "/SpecialisationSearchByAll/{all}", method = RequestMethod.GET)
		public List<SpecialisationPojo> specialisationSearchByAll(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
	    System.out.println("working");
	    System.out.println(all);
		return spService.specialisationSearchByAll(all);
		}
		
		@RequestMapping(value = "/SpecialisationDelete/{pid}", method = RequestMethod.DELETE)
		public int specDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid ) {
			System.out.println("con "+pid);
			return spService.specDelete(pid);
		}
		
	
}
