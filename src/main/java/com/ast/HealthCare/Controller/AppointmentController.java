package com.ast.HealthCare.Controller;

import java.util.List;
import java.sql.Date;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Pojo.AppointmentJsonPojo;
import com.ast.HealthCare.Pojo.AppointmentPojo;
import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.PatientWaitingListPojo;
import com.ast.HealthCare.Service.AppointmentService;

@RestController
public class AppointmentController {

//	CREATE TABLE appointment(APPOINTMENTID SERIAL,CONSULTINGDOCTORID TEXT,APPOINTMENTDATE DATE,
//	APPOINTMENTSTARTTIME TIME,APPOINTMENTENDTIME TIME,PATIENTID TEXT,TOKENNO INTEGER,APPOINTMENTSTATUS TEXT,PRIMARY KEY(APPOINTMENTID));

	@Autowired
	AppointmentService appService;
	
	@RequestMapping(value = "/InsertAppointment", method = RequestMethod.POST)
	public Boolean insertAppointment(HttpServletRequest request, HttpServletResponse response, @RequestBody List<AppointmentPojo> appPojo) {
		System.out.println("ser "+appPojo);
		return appService.insertAppointment(appPojo);
	}
	
	@RequestMapping(value = "/AppointmentAll/{date}", method = RequestMethod.GET)
	public List<PatientWaitingListPojo> appAll(HttpServletRequest request, HttpServletResponse response,@PathVariable("date") Date dat) {
		System.out.println("date from front "+dat);
		return appService.appAll(dat);
	}
	
	@RequestMapping(value = "/AppointmentAllByDoctorId/{did}/{date}", method = RequestMethod.GET)
	public List<PatientPojo> appAllDoctor(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") String did,@PathVariable("date") Date date) {
		
		return appService.appAllDoctor(did, date);
	}
	
	@RequestMapping(value = "/AppointmentByDoctorId/{pname}", method = RequestMethod.GET)
	public List<AppointmentPojo> appByDoctor(HttpServletRequest request, HttpServletResponse response, @PathVariable("pname") String pname) {
		System.out.println("con "+pname);
		return appService.appByDoctor(pname);
	}
	
	@RequestMapping(value = "/AppointmentByDoctorJson1/{pname}", method = RequestMethod.GET)
	public List<AppointmentJsonPojo> appByDoctorJson1(HttpServletRequest request, HttpServletResponse response, @PathVariable("pname") String pname) {
		System.out.println("con "+pname);
		return appService.appByDoctorJson1(pname);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/AppointmentByDoctorJson/{did}", method = RequestMethod.GET)
	public String appByDoctorJson(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") String did) {
		System.out.println("con "+did);
		return appService.appByDoctorJson(did);
	}
	
	@RequestMapping(value = "/AppointmentByDoctorAndDate/{pname}/{appdate}", method = RequestMethod.GET)
	public List<AppointmentPojo> appByDoctorAndDate(HttpServletRequest request, HttpServletResponse response, @PathVariable("pname") String pname, @PathVariable("appdate") Date appdate) {
		System.out.println("con "+appdate);
		return appService.appByDoctorAndDate(pname,appdate);
	}
	
	@RequestMapping(value = "/AppointmentByDate/{appdate}", method = RequestMethod.GET)
	public List<AppointmentPojo> appByDate(HttpServletRequest request, HttpServletResponse response, @PathVariable("appdate") Date appdate) {
		System.out.println("con "+appdate);
		return appService.appByDate(appdate);
	}
	
	@RequestMapping(value = "/AppointmentUpdate", method = RequestMethod.PUT)
	public boolean appUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody AppointmentPojo appPojo) throws ParseException {	
		System.out.println("con "+appPojo);
		return appService.appUpdate(appPojo);
	}

	@RequestMapping(value = "/AppointmentDelete", method = RequestMethod.DELETE)
	public int appDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody AppointmentPojo appPojo) {	
		System.out.println("con "+appPojo);
		return appService.appDelete(appPojo);
	}
	
	@RequestMapping(value = "/AppointmentDeleteByDoctorIdAndDate/{did}/{adate}", method = RequestMethod.DELETE)
	public int appDelete1(HttpServletRequest request, HttpServletResponse response, @PathVariable("did") String did,@PathVariable("adate") Date adate) {	
		System.out.println("con "+adate+" "+did);
		return appService.appDelete1(did,adate);
	}
	
	@RequestMapping(value = "/AppointmentByPatientId/{pname}", method = RequestMethod.GET)
	public List<AppointmentPojo> appByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pname") String pname) {
		System.out.println("con "+pname);
		return appService.appByPatientId(pname);
	}

}
