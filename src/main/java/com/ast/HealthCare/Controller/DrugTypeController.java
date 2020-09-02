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

import com.ast.HealthCare.Pojo.DrugTypePojo;
import com.ast.HealthCare.Service.DrugTypeService;

@RestController
public class DrugTypeController {

		@Autowired
		DrugTypeService DTService;
		
		@RequestMapping(value = "/AddDrugType", method = RequestMethod.POST)
		public boolean addDrugType(HttpServletRequest request, HttpServletResponse response, @RequestBody DrugTypePojo drugtype ) 
		{
			System.out.println("con "+ drugtype);
			return DTService.addDrugType(drugtype);
		}
		
		@RequestMapping(value = "/DrugTypeAll", method = RequestMethod.GET)
		public List<DrugTypePojo> drugTypeAll(HttpServletRequest request, HttpServletResponse response) {
			
			return DTService.drugTypeAll();
		}
		
		@RequestMapping(value = "/DrugTypeSearchByAll/{all}", method = RequestMethod.GET)
		public List<DrugTypePojo> drugTypeSearchByAll(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
	    System.out.println("working");
	    System.out.println(all);
		return DTService.drugTypeSearchByAll(all);
		}
		
		@RequestMapping(value = "/DrugTypeSearchById/{id}", method = RequestMethod.GET)
		public DrugTypePojo drugTypeSearchById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id) {
	    System.out.println("working");
	    System.out.println(id);
		return DTService.drugTypeSearchById(id);
		}
		
		@RequestMapping(value = "/DrugTypeUpdate", method = RequestMethod.PUT)
		public boolean drugTypeUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody DrugTypePojo dt ) {
			return DTService.drugTypeUpdate(dt);
		}
		
		@RequestMapping(value = "/DrugTypeDelete/{pid}", method = RequestMethod.DELETE)
		public int drugTypeDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
			System.out.println("con "+pid);
			return DTService.drugTypeDelete(pid);
		}
		
}
