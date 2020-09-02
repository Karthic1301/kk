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

import com.ast.HealthCare.Pojo.TestMasterPojo;
import com.ast.HealthCare.Service.TestMasterService;

@RestController
public class TestMasterController {

	@Autowired
	TestMasterService testMasterService;
	

	@RequestMapping(value = "/AddTest", method = RequestMethod.POST)
	public boolean addTestMaster(HttpServletRequest request, HttpServletResponse response, @RequestBody TestMasterPojo dis ) 
	{
		System.out.println("con "+ dis);
		return testMasterService.addTestMaster(dis);
	}
	
	@RequestMapping(value = "/TestAll", method = RequestMethod.GET)
	public List<TestMasterPojo> testMasterAll(HttpServletRequest request, HttpServletResponse response) {
		
		return testMasterService.testMasterAll();
	}
	
	@RequestMapping(value = "/TestAllByInOutFlag/{status}", method = RequestMethod.GET)
	public List<TestMasterPojo> testAllByInOutFlag(HttpServletRequest request, HttpServletResponse response, @PathVariable String status) {
		
		return testMasterService.testAllByInOutFlag(status);
	}
	
	@RequestMapping(value = "/TestAllByPrescriptionFlag", method = RequestMethod.GET)
	public List<TestMasterPojo> testAllByPrescriptionFlag(HttpServletRequest request, HttpServletResponse response) {
		
		return testMasterService.testAllByPrescriptionFlag();
	}
	
	@RequestMapping(value = "/TestMasterId/{id}", method = RequestMethod.GET)
	public TestMasterPojo testMasterById(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
		
		return testMasterService.testMasterById(id);
	}
	
	@RequestMapping(value = "/TestDelete/{pid}", method = RequestMethod.DELETE)
	public int testMasterDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return testMasterService.testMasterDelete(pid);
	}

	@RequestMapping(value = "/TestUpdate", method = RequestMethod.PUT)
	public boolean testMasterUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody TestMasterPojo dt ) {
		return testMasterService.testMasterUpdate(dt);
	}
}
