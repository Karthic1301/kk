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

import com.ast.HealthCare.Pojo.SocialAndPreventiveHistoryPojo;
import com.ast.HealthCare.Service.SocialAndPreventiveHistoryService;

@RestController
public class SocialAndPreventiveHistoryController {

	@Autowired
	SocialAndPreventiveHistoryService sphs;

	@RequestMapping(value = "/AddSocialAndPreventiveHistory", method = RequestMethod.POST)
	public boolean addSocialAndPreventiveHistory(HttpServletRequest request, HttpServletResponse response, @RequestBody SocialAndPreventiveHistoryPojo imp ) 
	{
		System.out.println("con "+ imp);
		return sphs.addsph(imp);
	}
	
	@RequestMapping(value = "/SocialAndPreventiveHistoryAll", method = RequestMethod.GET)
	public List<SocialAndPreventiveHistoryPojo> sphAll(HttpServletRequest request, HttpServletResponse response) {
		
		return sphs.sphAll();
	}
	
	@RequestMapping(value = "/SocialAndPreventiveHistorySearchByPatientId/{pid}", method = RequestMethod.GET)
	public SocialAndPreventiveHistoryPojo sphSearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		return sphs.sphSearchByPatientId(pid);
	}
	
	
	@RequestMapping(value = "/SocialAndPreventiveHistoryUpdate", method = RequestMethod.PUT)
	public boolean sphUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody SocialAndPreventiveHistoryPojo dt ) {
		return sphs.sphUpdate(dt);
	}
	
	@RequestMapping(value = "/SocialAndPreventiveHistoryDelete/{pid}", method = RequestMethod.DELETE)
	public int sphDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return sphs.sphDelete(pid);
	}
}
