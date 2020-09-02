package com.ast.HealthCare.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.AppointmentDao;
import com.ast.HealthCare.Pojo.AppointmentJsonPojo;
import com.ast.HealthCare.Pojo.AppointmentPojo;
import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.PatientWaitingListPojo;

@Service
public class AppointmentService {

	@Autowired
	AppointmentDao appDao;
	
	public Boolean insertAppointment(List<AppointmentPojo> appPojo) {
		// TODO Auto-generated method stub
		return appDao.insertAppointment(appPojo);
	}

	public List<PatientWaitingListPojo> appAll(Date dat) {
		// TODO Auto-generated method stub
		return appDao.appAll(dat);
	}

	public List<AppointmentPojo> appByDoctor(String pname) {
		// TODO Auto-generated method stub
		return appDao.appByDoctor(pname);
	}

	public List<AppointmentPojo> appByDoctorAndDate(String pname, Date appdate) {
		// TODO Auto-generated method stub
		return appDao.appByDoctorAndDate(pname,appdate);
	}

	public List<AppointmentPojo> appByDate(Date appdate) {
		// TODO Auto-generated method stub
		return appDao.appByDate(appdate);
	}

	public boolean appUpdate(AppointmentPojo appPojo) throws ParseException {
		// TODO Auto-generated method stub
		return appDao.appUpdate(appPojo);
	}

	@SuppressWarnings("rawtypes")
	public String appByDoctorJson(String did) {
		// TODO Auto-generated method stub
		return appDao.appByDoctorJson(did);
	}

	public List<AppointmentJsonPojo> appByDoctorJson1(String pname) {
		// TODO Auto-generated method stub
		return appDao.appByDoctorJson1(pname);
	}

	public int appDelete(AppointmentPojo appPojo) {
		// TODO Auto-generated method stub
		return appDao.appDelete(appPojo);
	}

	public List<PatientPojo> appAllDoctor(String did, Date date) {
		// TODO Auto-generated method stub
		return appDao.appAllDoctor(did, date);
	}

	public int appDelete1(String did, Date adate) {
		// TODO Auto-generated method stub
		return appDao.appDelete1(did,adate);
	}
	
	public List<AppointmentPojo> appByPatientId(String pname) {
		// TODO Auto-generated method stub
		return appDao.appByPatientId(pname);
	}


}
