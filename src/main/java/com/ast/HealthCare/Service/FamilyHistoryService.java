package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.FamilyHistoryDao;
import com.ast.HealthCare.Pojo.FamilyHistoryPojo;

@Service
public class FamilyHistoryService {

	@Autowired
	FamilyHistoryDao fhDao;

	public boolean addFamilyHistory(List<FamilyHistoryPojo> family) {
		// TODO Auto-generated method stub
		return fhDao.addFamilyHistory(family);
	}

	public List<FamilyHistoryPojo> fhAll() {
		// TODO Auto-generated method stub
		return fhDao.fhAll();
	}

	public int fhDelete(int pid) {
		// TODO Auto-generated method stub
		return fhDao.fhDelete(pid);
	}

	public boolean fhUpdate(FamilyHistoryPojo dt) {
		// TODO Auto-generated method stub
		return fhDao.fhUpdate(dt);
	}

	public List<FamilyHistoryPojo> fhSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return fhDao.fhSearchByPatientId(pid);
	}
	
	
}
