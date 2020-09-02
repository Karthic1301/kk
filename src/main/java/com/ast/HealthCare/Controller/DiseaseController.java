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

import com.ast.HealthCare.Pojo.DiseasePojo;
import com.ast.HealthCare.Service.DiseaseService;

@RestController
public class DiseaseController {

	@Autowired
	DiseaseService diseaseService;
	

	@RequestMapping(value = "/AddDisease", method = RequestMethod.POST)
	public boolean addDisease(HttpServletRequest request, HttpServletResponse response, @RequestBody DiseasePojo dis ) 
	{
		System.out.println("con "+ dis);
		return diseaseService.addDisease(dis);
	}
	
	@RequestMapping(value = "/DiseaseAll", method = RequestMethod.GET)
	public List<DiseasePojo> diseaseAll(HttpServletRequest request, HttpServletResponse response) {
		
		return diseaseService.diseaseAll();
	}
	
	@RequestMapping(value = "/DiseaseDelete/{pid}", method = RequestMethod.DELETE)
	public int diseaseDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return diseaseService.diseaseDelete(pid);
	}

	@RequestMapping(value = "/DiseaseUpdate", method = RequestMethod.PUT)
	public boolean diseaseUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody DiseasePojo dt ) {
		return diseaseService.diseaseUpdate(dt);
	}
	
}
