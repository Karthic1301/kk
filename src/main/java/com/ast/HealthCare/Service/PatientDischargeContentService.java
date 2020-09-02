package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.BirthDetailsDao;
import com.ast.HealthCare.Dao.PatientDischargeContentDao;
import com.ast.HealthCare.Pojo.PatientDischargeContentPojo;
@Service
public class PatientDischargeContentService {

	@Autowired
	PatientDischargeContentDao patientDischargeContentDao;
	@Autowired
	BirthDetailsDao bDao;
	
	public boolean addPatientDischargeContent(List<PatientDischargeContentPojo> dis) {
		
		// TODO Auto-generated method stub
		boolean res= patientDischargeContentDao.addPatientDischargeContent(dis);
		System.out.println("ContentMASTER"+dis.toString());
		for(int i=0;i<dis.size();i++)
		{
		if(dis.get(i).getHeadingMasterId() ==14)
		{
		int contentId= patientDischargeContentDao.getMaximumIdForBirthDetails();
		dis.get(i).getBirthPojo().setContentMasterId(contentId);
		bDao.addBirthDetails(dis.get(i).getBirthPojo());
		}
		}
		return res;
	}
	
	
	
	

	public List<PatientDischargeContentPojo> patientDischargeContentAll() {
		// TODO Auto-generated method stub
		return patientDischargeContentDao.patientDischargeContentAll();
	}
	public PatientDischargeContentPojo patientDischargeContentById(int id) {
		// TODO Auto-generated method stub
		return patientDischargeContentDao.patientDischargeContentById(id);
	}

	public int patientDischargeContentDelete(int pid) {
		// TODO Auto-generated method stub
		return patientDischargeContentDao.patientDischargeContentDelete(pid);
	}

	public boolean patientDischargeContentUpdate(PatientDischargeContentPojo dt) {
		// TODO Auto-generated method stub
		return patientDischargeContentDao.patientDischargeContentUpdate(dt);
	}
}
