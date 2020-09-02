package com.ast.HealthCare.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.VitalInfoDao;
import com.ast.HealthCare.Pojo.AppointmentPojo;
import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.VitalInfoPojo;

@Service
public class VitalInfoService {

	@Autowired
	VitalInfoDao vitalDao;
	//SEARCH BY DATE AND PID, VITALINFOID, PID, THEN UPDATE, GET BY PID IN PRESCRIPTION ORDER BY DATE = PRESCRIPTIONID AND PDATE AS OUTPUT
	public boolean addVitalInfo(VitalInfoPojo vital) {
			// TODO Auto-generated method stub
			return vitalDao.addVitalInfo(vital);
	}

	public List<PatientPojo> vitalInfoAll(Date vdate) {
		// TODO Auto-generated method stub
		return vitalDao.vitalInfoAll(vdate);
	}

	public boolean vitalInfoUpdate(VitalInfoPojo dt) {
		// TODO Auto-generated method stub
		return vitalDao.vitalInfoUpdate(dt);
	}

	public VitalInfoPojo searchByPidAndDate(String pid, Date vitaldate) {
		// TODO Auto-generated method stub
		return vitalDao.searchByPidAndDate(pid,vitaldate);
	}
	
	public VitalInfoPojo searchByPidLatestDate(String pid) {
		// TODO Auto-generated method stub
		return vitalDao.searchByPidLatestDate(pid);
	}

	public List<VitalInfoPojo> searchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return vitalDao.searchByPatientId(pid);
	}

	public VitalInfoPojo searchByVitalInfoId(String vid) {
		// TODO Auto-generated method stub
		return vitalDao.searchByVitalInfoId(vid);
	}

	public int vitalInfoDelete(String pno) {
		// TODO Auto-generated method stub
		return vitalDao.vitalInfoDelete(pno);
	}
	
}
