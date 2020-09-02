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

import com.ast.HealthCare.Pojo.LabTestMasterPojo;
import com.ast.HealthCare.Service.LabTestMasterService;

@RestController
public class LabTestMasterController {

	@Autowired
	LabTestMasterService TMService;
	
	
	@RequestMapping(value = "/AddTestMaster", method = RequestMethod.POST)
	public String addTestMaster(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestMasterPojo pojo ) 
	{
		System.out.println("con "+ pojo);
		return TMService.addTestMaster(pojo);
	}
	
	@RequestMapping(value = "/TestMasterAll", method = RequestMethod.GET)
	public List<LabTestMasterPojo> testMasterAll(HttpServletRequest request, HttpServletResponse response) {
		
		return TMService.testMasterAll();
	}
	
	@RequestMapping(value = "/TestMasterSearchByName/{name}", method = RequestMethod.GET)
	public LabTestMasterPojo testMasterSearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return TMService.testMasterSearchByName(name);
	}
	
	@RequestMapping(value = "/TestMasterSearchById/{id}", method = RequestMethod.GET)
	public LabTestMasterPojo testMasterSearchById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TMService.testMasterSearchById(id);
	}
	
	@RequestMapping(value = "/TestMasterUpdate", method = RequestMethod.PUT)
	public String testMasterUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestMasterPojo drug ) {
		return TMService.testMasterUpdate(drug);
	}
	
	@RequestMapping(value = "/TestMasterDelete/{did}", method = RequestMethod.DELETE)
	public int testMasterDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return TMService.testMasterDelete(did);

	}
	
	@RequestMapping(value = "/TestMasterNotInNormal", method = RequestMethod.GET)
	public List<LabTestMasterPojo> testMasterNotInNormal(HttpServletRequest request, HttpServletResponse response) {
		
		return TMService.testMasterNotInNormal();
	}
	
	@RequestMapping(value = "/TestMasterNotInAssign", method = RequestMethod.GET)
	public List<LabTestMasterPojo> testMasterNotInAssign(HttpServletRequest request, HttpServletResponse response) {
		
		return TMService.testMasterNotInAssign();
	}
}
