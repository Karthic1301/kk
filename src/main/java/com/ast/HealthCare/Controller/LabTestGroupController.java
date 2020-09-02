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

import com.ast.HealthCare.Pojo.LabTestGroupPojo;
import com.ast.HealthCare.Service.LabTestGroupService;


@RestController
public class LabTestGroupController {

	@Autowired
	LabTestGroupService TGService;
	
	
	@RequestMapping(value = "/AddTestGroup", method = RequestMethod.POST)
	public String addTestGroup(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestGroupPojo pojo ) 
	{
		System.out.println("con "+ pojo);
		return TGService.addTestGroup(pojo);
	}
	
	@RequestMapping(value = "/TestGroupAll", method = RequestMethod.GET)
	public List<LabTestGroupPojo> testGroupAll(HttpServletRequest request, HttpServletResponse response) {
		
		return TGService.testGroupAll();
	}
	
	@RequestMapping(value = "/TestGroupSearchByName/{name}", method = RequestMethod.GET)
	public LabTestGroupPojo testGroupSearchByName(HttpServletRequest request, HttpServletResponse response,@PathVariable("name") String name) {
		
		return TGService.testGroupSearchByName(name);
	}
	
	@RequestMapping(value = "/TestGroupSearchById/{id}", method = RequestMethod.GET)
	public LabTestGroupPojo testGroupSearchById(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") int id) {
		
		return TGService.testGroupSearchById(id);
	}
	
	@RequestMapping(value = "/TestGroupUpdate", method = RequestMethod.PUT)
	public String testGroupUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LabTestGroupPojo drug ) {
		return TGService.testGroupUpdate(drug);
	}
	
	@RequestMapping(value = "/TestGroupDelete/{did}", method = RequestMethod.DELETE)
	public int testGroupDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") int did ) {
		System.out.println("con "+did);
		return TGService.testGroupDelete(did);
	}
	
}
