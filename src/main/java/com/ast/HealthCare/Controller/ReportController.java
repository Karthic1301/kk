package com.ast.HealthCare.Controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ast.HealthCare.Pojo.ReportPojo;
import com.ast.HealthCare.Service.ReportService;

@RestController
public class ReportController {
	
	@Autowired
	ReportService repservice;

	@RequestMapping(value = "/ConsultedReportByDate/{date1}/{date2}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDate(HttpServletRequest request, HttpServletResponse response,@PathVariable("date1") Date date1,@PathVariable("date2") Date date2) {
		
		return repservice.reportByDate(date1,date2);
	}
	
	@RequestMapping(value = "/ConsultedReportByDisease/{diseasename}/{date1}/{date2}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDisease(HttpServletRequest request, HttpServletResponse response,@PathVariable("date1") Date date1,@PathVariable("date2") Date date2, @PathVariable("diseasename") int diseasename) {
		
		return repservice.reportByDisease(diseasename,date1,date2);
	}
	
	@RequestMapping(value = "/ConsultedReportByDisease/{diseasename}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDisease(HttpServletRequest request, HttpServletResponse response,@PathVariable("diseasename") int diseasename) {
		
		return repservice.reportByDisease(diseasename);
	}
	
	@RequestMapping(value = "/ConsultedReportByDrug/{drugname}/{date1}/{date2}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDrug(HttpServletRequest request, HttpServletResponse response,@PathVariable("date1") Date date1,@PathVariable("date2") Date date2, @PathVariable("drugname") int drugname) {
		
		return repservice.reportByDrug(drugname,date1,date2);
	}
	
	@RequestMapping(value = "/ConsultedReportByDrug/{drugname}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDrug(HttpServletRequest request, HttpServletResponse response, @PathVariable("drugname") int drugname) {
		
		return repservice.reportByDrug(drugname);
	}
	
	@RequestMapping(value = "/ConsultedReportByDoctor/{doctorid}/{date1}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDoctor(HttpServletRequest request, HttpServletResponse response,@PathVariable("date1") Date date1, @PathVariable("doctorid") String doctorid) {
		
		return repservice.reportByDoctor(doctorid,date1);
	}
	
	@RequestMapping(value = "/ConsultedReportByDoctor/{doctorid}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDoctor1(HttpServletRequest request, HttpServletResponse response, @PathVariable("doctorid") String doctorid) {
		
		return repservice.reportByDoctor1(doctorid);
	}
	
	@RequestMapping(value = "/ConsultedReportByDoctor/{doctorid}/{date1}/{date2}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDoctor(HttpServletRequest request, HttpServletResponse response,@PathVariable("date1") Date date1, @PathVariable("date2") Date date2, @PathVariable("doctorid") String doctorid) {
		
		return repservice.reportByDoctor(doctorid,date1,date2);
	}
	
	@RequestMapping(value = "/ConsultedReportAll", method = RequestMethod.GET)
	public List<ReportPojo> reportAll(HttpServletRequest request, HttpServletResponse response) {
		
		return repservice.reportByAll();
	}
	
	@RequestMapping(value = "/ConsultedReportByDoctorIdAndDrug/{doctorid}/{drugname}/{date1}/{date2}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDoctorIdAndDrug(HttpServletRequest request, HttpServletResponse response,@PathVariable("date1") Date date1,@PathVariable("date2") Date date2, @PathVariable("drugname") int drugname, @PathVariable("doctorid") String doctorid) {
		
		return repservice.reportByDoctorIdAndDrug(doctorid,drugname,date1,date2);
	}
	
	@RequestMapping(value = "/ConsultedReportByDoctorIdAndDrug/{doctorid}/{drugname}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDoctorIdAndDrug(HttpServletRequest request, HttpServletResponse response, @PathVariable("drugname") int drugname, @PathVariable("doctorid") String doctorid) {
		
		return repservice.reportByDoctorIdAndDrug(doctorid,drugname);
	}
	
	@RequestMapping(value = "/ConsultedReportByDoctorIdAndDiseases/{doctorid}/{diseasename}/{date1}/{date2}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDoctorIdAndDiseases(HttpServletRequest request, HttpServletResponse response,@PathVariable("date1") Date date1,@PathVariable("date2") Date date2, @PathVariable("diseasename") int diseasename, @PathVariable("doctorid") String doctorid) {
		
		return repservice.reportByDoctorIdAndDiseases(doctorid,diseasename,date1,date2);
	}
	
	@RequestMapping(value = "/ConsultedReportByDoctorIdAndDiseases/{doctorid}/{diseasename}", method = RequestMethod.GET)
	public List<ReportPojo> reportByDoctorIdAndDiseases(HttpServletRequest request, HttpServletResponse response,@PathVariable("diseasename") int diseasename, @PathVariable("doctorid") String doctorid) {
		
		return repservice.reportByDoctorIdAndDiseases(doctorid,diseasename);
	}
}
