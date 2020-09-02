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

import com.ast.HealthCare.Pojo.DrugMasterPojo;
import com.ast.HealthCare.Service.DrugMasterService;

@RestController
public class DrugMasterController {

	@Autowired
	DrugMasterService DMService;
	
	
	@RequestMapping(value = "/AddDrug", method = RequestMethod.POST)
	public boolean addDrug(HttpServletRequest request, HttpServletResponse response, @RequestBody DrugMasterPojo drug ) 
	{
		System.out.println("con "+ drug);
		return DMService.addDrug(drug);
	}
	
	@RequestMapping(value = "/DrugAll", method = RequestMethod.GET)
	public List<DrugMasterPojo> drugAll(HttpServletRequest request, HttpServletResponse response) {
		
		return DMService.drugAll();
	}
	
	@RequestMapping(value = "/DrugSearchByName/{name}", method = RequestMethod.GET)
	public DrugMasterPojo drugSearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return DMService.drugSearchByName(name);
	}
	
	@RequestMapping(value = "/DrugUpdate", method = RequestMethod.PUT)
	public boolean drugUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody DrugMasterPojo drug ) {
		return DMService.drugUpdate(drug);
	}
	
	@RequestMapping(value = "/DrugDelete/{did}", method = RequestMethod.DELETE)
	public int drugDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return DMService.drugDelete(did);
	}

	
}