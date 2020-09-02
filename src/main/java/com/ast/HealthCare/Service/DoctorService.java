package com.ast.HealthCare.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.DoctorDao;
import com.ast.HealthCare.Dao.DoctorTestMasterDao;
import com.ast.HealthCare.Dao.LoginDao;
import com.ast.HealthCare.Dao.PatientDao;
import com.ast.HealthCare.Dao.SettingDao;
import com.ast.HealthCare.Pojo.DoctorPojo;
import com.ast.HealthCare.Pojo.DoctorTestMasterPojo;
import com.ast.HealthCare.Pojo.LoginPojo;
import com.ast.HealthCare.Pojo.PatientPojo;

@Service
public class DoctorService {

	@Autowired
	LoginDao loginDao;
	@Autowired
	DoctorDao doctorDao;
	@Autowired
	DoctorTestMasterDao doctorTestMasterDao;
	@Autowired
	SettingDao settingDao;
	
	
	public boolean addDoctor(DoctorPojo doctor) {
		// TODO Auto-generated method stub
		LoginPojo login = new LoginPojo();
		List<DoctorTestMasterPojo> dtpojo = new ArrayList<DoctorTestMasterPojo>();
		System.out.println("ser "+doctor);
		
	    String sss = doctorDao.register(doctor);
	    
		System.out.println("newly added doctor "+sss);
		
		if(sss.equals("false"))
			return false;
		else
		{
			login.setUserName(doctor.getDoctorUserName());
			login.setPassword(doctor.getDoctorPassword());
			login.setUserId(sss);
			login.setUserCategory("D");
			login.setRoleId(3);
			if(loginDao.addUser(login))
				System.out.println("user not added");
			else 
				System.out.println("user added");
			
			dtpojo = doctor.getDoctorTestMaster();
			for(int i=0;i<dtpojo.size();i++)
			{
				dtpojo.get(i).setDoctorId(sss);
				System.out.println(dtpojo.get(i).toString());
				doctorTestMasterDao.addDoctorTestMaster(dtpojo.get(i));
				
			}
			if(doctor.getSettingPojo()!=null)
			{
				doctor.getSettingPojo().setDoctorId(sss);
				settingDao.addSetting(doctor.getSettingPojo());
			}
		}
		return true;
	}


	public List<DoctorPojo> doctorAll() {
		// TODO Auto-generated method stub
			return doctorDao.doctorAll();
	}


	public int doctorDelete(String pid) {
		// TODO Auto-generated method stub
		System.out.println("Doctor"+pid);
		if(loginDao.deleteUser(pid) == 1)
			System.out.println("deleted doctor");
		else
			System.out.println("doctor not deleted");
		doctorTestMasterDao.doctorTestMasterDeleteByDoctorId(pid);
		settingDao.settingDeleteByDoctorId(pid);
		
		return doctorDao.doctorDelete(pid);
	}


	public boolean doctorUpdate(DoctorPojo doctor) {
		// TODO Auto-generated method stub
		List<DoctorTestMasterPojo> dtpojo = new ArrayList<DoctorTestMasterPojo>();
		dtpojo = doctor.getDoctorTestMaster();
		for(int i=0;i<dtpojo.size();i++)
		{
			System.out.println(dtpojo.get(i).toString());
			doctorTestMasterDao.doctorTestMasterUpdate(dtpojo.get(i));
			
		}
		settingDao.settingUpdate(doctor.getSettingPojo());
		
		return doctorDao.doctorUpdate(doctor);
	}


	public DoctorPojo doctorSearchById(String pid) {
		// TODO Auto-generated method stub
		return doctorDao.doctorSearchById(pid);
	}


	public List<DoctorPojo> doctorSearchByName(String pname) {
		// TODO Auto-generated method stub
		return doctorDao.doctorSearchByName(pname);
	}


	public List<DoctorPojo> doctorSearchByPhoneNo(String pno) {
		// TODO Auto-generated method stub
		return doctorDao.doctorSearchByPhoneNo(pno);
	}


	public List<DoctorPojo> doctorSearchByAll(String all) {
		// TODO Auto-generated method stub
		return doctorDao.doctorSearchByAll(all);
	}


	public List<DoctorPojo> doctorSearchBySpec(String spec) {
		// TODO Auto-generated method stub
		return doctorDao.doctorSearchBySpec(spec);
	}
	
	public List<DoctorPojo> doctorSearchByAllForSMS(String all) {
		// TODO Auto-generated method stub
		return doctorDao.doctorSearchByAllForSMS(all);
	}
	
	public List<DoctorPojo> getBasicDetailDoctorAll() {
		// TODO Auto-generated method stub
			return doctorDao.getBasicDetailDoctorAll();
	}

}
