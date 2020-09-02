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

import com.ast.HealthCare.Pojo.ReproductiveHistoryPojo;
import com.ast.HealthCare.Service.ReproductiveHistoryService;

@RestController
public class ReproductiveHistoryController {

	@Autowired
	ReproductiveHistoryService reproductiveHistoryService;
	
	@RequestMapping(value = "/AddReproductiveHistory", method = RequestMethod.POST)
	public Boolean addReproductiveHistory(HttpServletRequest request, HttpServletResponse response, @RequestBody ReproductiveHistoryPojo reproductiveHistory ) {
		return reproductiveHistoryService.addReproductiveHistory(reproductiveHistory);
	}
	
	@RequestMapping(value = "/ReproductiveHistoryAll", method = RequestMethod.GET)
	public List<ReproductiveHistoryPojo> reproductiveHistoryAll(HttpServletRequest request, HttpServletResponse response) {
		return reproductiveHistoryService.reproductiveHistoryAll();
	}
	
	@RequestMapping(value = "/ReproductiveHistorySearchByPatientId/{pid}", method = RequestMethod.GET)
	public ReproductiveHistoryPojo reproductiveHistorySearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid ) {
		return reproductiveHistoryService.reproductiveHistorySearchByPatientId(pid);
	}
	
	@RequestMapping(value = "/UpdateReproductiveHistory/{pid}", method = RequestMethod.PUT)
	public Boolean updateReproductiveHistory(HttpServletRequest request, HttpServletResponse response, @RequestBody ReproductiveHistoryPojo reproductiveHistory, @PathVariable("pid") String pid  ) {
		return reproductiveHistoryService.updateReproductiveHistory(reproductiveHistory,pid);
	}
	
	@RequestMapping(value = "/ReproductiveHistoryDelete/{pid}", method = RequestMethod.PUT)
	public int reproductiveHistoryDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid  ) {
		return reproductiveHistoryService.reproductiveHistoryDelete(pid);
	}
	

}
