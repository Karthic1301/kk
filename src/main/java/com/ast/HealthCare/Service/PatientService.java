package com.ast.HealthCare.Service;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ast.HealthCare.Dao.LoginDao;
import com.ast.HealthCare.Dao.PatientDao;
import com.ast.HealthCare.Pojo.LoginPojo;
import com.ast.HealthCare.Pojo.PatientPojo;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;

@Service
public class PatientService implements Serializable{

	@Autowired
	PatientDao patientDao;
	@Autowired
	LoginDao loginDao;
	
	@Transactional
	public String register(PatientPojo patient) throws ParseException {
		
		LoginPojo login = new LoginPojo();
		//System.out.println(patient.getPatientUserName());
		
		String sss = patientDao.register(patient);
		System.out.println("ser"+sss);
		
		if(sss.equals("false"))
			return "false";
		else
		{
			login.setUserName(patient.getPatientUserName());
			login.setPassword(patient.getPatientPassword());
			login.setUserId(sss);
			login.setUserCategory("P");
			login.setRoleId(2);
			if(loginDao.addUser(login))
			{
				System.out.println("user not added");
				return "false";
			}
			else 
			{
				System.out.println("user added");
				System.out.println(sss);
				return sss;
			}
		}
	}

	public int patientDelete(String pid) {
		// TODO Auto-generated method stub
		if(loginDao.deleteUser(pid) == 1)
			System.out.println("deleted user");
		else
			System.out.println("not deleted");
		return patientDao.patientDelete(pid);
	}

	public PatientPojo patientSearchById(String pid) {
		// TODO Auto-generated method stub
		return patientDao.patientSearchById(pid);
		
	}

	public boolean patientUpdate(PatientPojo patient) {
		// TODO Auto-generated method stub
		return patientDao.patientUpdate(patient);
	}

	public List<PatientPojo> patientAll() {
		// TODO Auto-generated method stub
		return patientDao.patientAll();
	}

	public List<PatientPojo> patientSearchByName(String pname) {
		// TODO Auto-generated method stub
		return patientDao.patientSearchByName(pname);
	}

	public List<PatientPojo> patientSearchByPhoneNo(String pno) {
		// TODO Auto-generated method stub
		return patientDao.patientSearchByPhoneNo(pno);
	}


	public List<PatientPojo> patientSearchByAll(String all) {
		// TODO Auto-generated method stub
		return patientDao.patientSearchByAll(all);
	}

	public List<PatientPojo> patientSearchByDate(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return patientDao.patientSearchByDate(date1, date2);
	}
	
	public List<PatientPojo> patientSearchByAllForSMS(String all) {
		// TODO Auto-generated method stub
		return patientDao.patientSearchByAllForSMS(all);
	}
	
}
