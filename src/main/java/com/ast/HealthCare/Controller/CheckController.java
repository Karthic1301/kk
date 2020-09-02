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

import com.ast.HealthCare.Pojo.CheckPojo;
import com.ast.HealthCare.Service.CheckService;

@RestController
public class CheckController {

	@Autowired()
	CheckService checkObj;
	
	@RequestMapping(value = "/AddCheck", method = RequestMethod.POST)
	public boolean addCheck(HttpServletRequest request, HttpServletResponse response, @RequestBody CheckPojo summa) 
	{
		System.out.println("con "+ summa);
		return checkObj.addCheck(summa);
	}
	@RequestMapping(value = "/ViewCheck", method = RequestMethod.GET)
	public List<CheckPojo> viewCheck(HttpServletRequest request, HttpServletResponse response) 
	{
		return checkObj.viewCheck();
	}
	
	@RequestMapping(value = "/CheckDelete/{cid}", method = RequestMethod.DELETE)
	public int checkDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("cid") String pid ) {
		System.out.println("con "+pid);
		return checkObj.checkDelete(pid);
	}
	@RequestMapping(value = "/CheckUpdate", method = RequestMethod.PUT)
	public boolean checkUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody CheckPojo dt ) {
		return checkObj.checkUpdate(dt);
	}
}
