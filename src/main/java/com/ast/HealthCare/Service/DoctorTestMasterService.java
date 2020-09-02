package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.DoctorTestMasterDao;
import com.ast.HealthCare.Dao.TestMasterDao;
import com.ast.HealthCare.Pojo.DoctorTestMasterPojo;
import com.ast.HealthCare.Pojo.TestMasterPojo;

@Service
public class DoctorTestMasterService {

	@Autowired
	DoctorTestMasterDao doctorDoctorTestMasterDao;
	@Autowired
	TestMasterDao tDao;

	public boolean addDoctorTestMaster(DoctorTestMasterPojo dis) {
		// TODO Auto-generated method stub
		return doctorDoctorTestMasterDao.addDoctorTestMaster(dis);
	}

	public List<DoctorTestMasterPojo> doctorTestMasterAll() {
		// TODO Auto-generated method stub
		return doctorDoctorTestMasterDao.doctorTestMasterAll();
	}

	public int doctorTestMasterDelete(int pid) {
		// TODO Auto-generated method stub
		return doctorDoctorTestMasterDao.doctorTestMasterDelete(pid);
	}

	public boolean doctorTestMasterUpdate(DoctorTestMasterPojo dt) {
		// TODO Auto-generated method stub
		return doctorDoctorTestMasterDao.doctorTestMasterUpdate(dt);
	}
	
	public DoctorTestMasterPojo getAmountByDoctorIdAndTestId(String did,int tid)
	{
		DoctorTestMasterPojo res = new DoctorTestMasterPojo();
		if(did.equals("null"))
		{
			
			TestMasterPojo tpojo = tDao.testMasterById(tid);
			res.setAmount(tpojo.getAmount());
			res.setTestMasterId(tpojo.getTestMasterId());
			res.setTestMasterName(tpojo.getTestMasterName());
			res.setOrderNo(tpojo.getOrderNo());
			
		}
		else
		{
			res =doctorDoctorTestMasterDao.getAmountByDoctorIdAndTestId(did,tid);
			TestMasterPojo tpojo = tDao.testMasterById(tid);
			res.setOrderNo(tpojo.getOrderNo());
		}
		return res;
	}
	
}
