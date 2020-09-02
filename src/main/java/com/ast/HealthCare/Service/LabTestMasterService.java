package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.LabTestMasterDao;
import com.ast.HealthCare.Pojo.LabTestMasterPojo;

@Service
public class LabTestMasterService {

	@Autowired
	LabTestMasterDao TMDao;
	

	public String addTestMaster(LabTestMasterPojo drug) {
		// TODO Auto-generated method stub
		return TMDao.addTestMaster(drug);
	}


	public List<LabTestMasterPojo> testMasterAll() {
		// TODO Auto-generated method stub
		return TMDao.testMasterAll();
	}


	public String testMasterUpdate(LabTestMasterPojo drug) {
		// TODO Auto-generated method stub
		return TMDao.testMasterUpdate(drug);
	}


	public int testMasterDelete(int did) {
		// TODO Auto-generated method stub
		return TMDao.testMasterDelete(did);
	}


	public LabTestMasterPojo testMasterSearchByName(String name) {
		// TODO Auto-generated method stub
		return TMDao.testMasterSearchByName(name);
	}
	
	public LabTestMasterPojo testMasterSearchById(int id) {
		// TODO Auto-generated method stub
		return TMDao.testMasterSearchById(id);
	}
	
	public List<LabTestMasterPojo> testMasterNotInNormal() {
		// TODO Auto-generated method stub
		return TMDao.testMasterNotInNormal();
	}
	
	public List<LabTestMasterPojo> testMasterNotInAssign() {
		// TODO Auto-generated method stub
		return TMDao.testMasterNotInAssign();
	}
}
