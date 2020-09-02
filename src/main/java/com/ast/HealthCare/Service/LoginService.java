package com.ast.HealthCare.Service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.DoctorDao;
import com.ast.HealthCare.Dao.LoginDao;
import com.ast.HealthCare.Dao.PatientDao;
import com.ast.HealthCare.Dao.StaffDao;
import com.ast.HealthCare.Pojo.AdminPojo;
import com.ast.HealthCare.Pojo.DoctorPojo;
import com.ast.HealthCare.Pojo.LoginPojo;
import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.StaffPojo;

@Service
public class LoginService implements Serializable{
	@Autowired
	LoginDao loginDao;
	@Autowired
	PatientDao patientDao;
	@Autowired
	StaffDao staffDao;
	@Autowired
	DoctorDao doctorDao;
	public LoginPojo doLogin(LoginPojo login) {
		System.out.println(login.getPassword());
		System.out.println(login.getUserName());
		return loginDao.loginDao(login.getUserName(),login.getPassword());
		  //lLogin;
	 }

	public Boolean checkUserName(String userName) {
		// TODO Auto-generated method stub
		return loginDao.checkUserName(userName);
	}

	public Boolean addUser(LoginPojo login) {
		// TODO Auto-generated method stub 
		 return loginDao.addUser(login);
	}

	public List<LoginPojo> userAll() {
		// TODO Auto-generated method stub
		return loginDao.userAll();
	}

	public LoginPojo userSearchByAll(String all) {
		// TODO Auto-generated method stub
		LoginPojo login = new LoginPojo();
		login = loginDao.userSearchByAll(all);
		System.out.println(login);
		String s = login.getUserCategory();
		System.out.println(s);
		if(s.equals("P")) {
		PatientPojo patient = new PatientPojo();
		patient = patientDao.patientSearchById(login.getUserId());
		System.out.println(patient);
			login.setMobile1(patient.getPatientMobile1());
			login.setMobile2(patient.getPatientMobile2());
		}
		else if(s.equals("S")) {
			StaffPojo staff = new StaffPojo();
			staff = staffDao.staffSearchById(login.getUserId());
			login.setMobile1(staff.getStaffMobile1());
			login.setMobile2(staff.getStaffMobile2());
		}
		else if(s.equals("D")) {
			DoctorPojo doctor = new DoctorPojo();
			doctor = doctorDao.doctorSearchById(login.getUserId());
			login.setMobile1(doctor.getDoctorMobile1());
			login.setMobile2(doctor.getDoctorMobile2());
		}
		return login;
	}

	public boolean userUpdate(LoginPojo login) {
		// TODO Auto-generated method stub
		return loginDao.userUpdate(login);
	}

	public Boolean addUserAdmin(AdminPojo login) {
		// TODO Auto-generated method stub
		return loginDao.addUserAdmin(login);
	}

	public boolean userUpdateAdmin(AdminPojo login) {
		// TODO Auto-generated method stub
		return loginDao.userUpdateAdmin(login);
	}

	public boolean userDeleteAdmin(AdminPojo login) {
		// TODO Auto-generated method stub
		return loginDao.userDeleteAdmin(login);
	}
}
