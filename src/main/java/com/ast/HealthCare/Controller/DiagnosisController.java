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

import com.ast.HealthCare.Pojo.DiagnosisPojo;
import com.ast.HealthCare.Service.DiagnosisService;

@RestController
public class DiagnosisController {

		@Autowired
		DiagnosisService DiagService;

		@RequestMapping(value = "/AddDiagnosis", method = RequestMethod.POST)
		public boolean addDiag(HttpServletRequest request, HttpServletResponse response, @RequestBody DiagnosisPojo diag ) 
		{
			System.out.println("con "+ diag);
			return DiagService.addDiag(diag);
		}
		
		@RequestMapping(value = "/DiagnosisAll", method = RequestMethod.GET)
		public List<DiagnosisPojo> diagnosisAll(HttpServletRequest request, HttpServletResponse response) {
			
			return DiagService.diagnosisAll();
		}
		
		@RequestMapping(value = "/DiagnosisSearchByAll/{all}", method = RequestMethod.GET)
		public List<DiagnosisPojo> diagnosisSearchByAll(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
	    System.out.println("working");
	    System.out.println(all);
		return DiagService.diagnosisSearchByAll(all);
		}

		@RequestMapping(value = "/DiagnosisUpdate", method = RequestMethod.PUT)
		public boolean diagnosisUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody DiagnosisPojo dt ) {
			return DiagService.diagnosisUpdate(dt);
		}
		
		@RequestMapping(value = "/DiagnosisDelete/{pid}", method = RequestMethod.DELETE)
		public int diagnosisDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
			System.out.println("con "+pid);
			return DiagService.diagnosisDelete(pid);
		}
	
}
