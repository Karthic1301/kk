package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.DrugMasterDao;
import com.ast.HealthCare.Dao.LabTestGroupDao;
import com.ast.HealthCare.Pojo.LabTestGroupPojo;

@Service
public class LabTestGroupService {

	@Autowired
	LabTestGroupDao TGDao;
	

	public String addTestGroup(LabTestGroupPojo drug) {
		// TODO Auto-generated method stub
		return TGDao.addTestGroup(drug);
	}


	public List<LabTestGroupPojo> testGroupAll() {
		// TODO Auto-generated method stub
		return TGDao.testGroupAll();
	}


	public String testGroupUpdate(LabTestGroupPojo drug) {
		// TODO Auto-generated method stub
		return TGDao.testGroupUpdate(drug);
	}


	public int testGroupDelete(int did) {
		// TODO Auto-generated method stub
		return TGDao.testGroupDelete(did);
	}


	public LabTestGroupPojo testGroupSearchByName(String name) {
		// TODO Auto-generated method stub
		return TGDao.testGroupSearchByName(name);
	}
	
	public LabTestGroupPojo testGroupSearchById(int id) {
		// TODO Auto-generated method stub
		return TGDao.testGroupSearchById(id);
	}
}
