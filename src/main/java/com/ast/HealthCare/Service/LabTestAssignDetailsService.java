package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.LabTestAssignDetailsDao;
import com.ast.HealthCare.Pojo.LabTestAssignDetailsPojo;

@Service
public class LabTestAssignDetailsService {

	@Autowired
	LabTestAssignDetailsDao TMDao;
	

	public boolean addTestAssignDetails(List<LabTestAssignDetailsPojo> drug) {
		// TODO Auto-generated method stub
		return TMDao.addTestAssignDetails(drug);
	}


	public List<LabTestAssignDetailsPojo> testAssignDetailsAll() {
		// TODO Auto-generated method stub
		return TMDao.testAssignDetailsAll();
	}


	public boolean testAssignDetailsUpdate(LabTestAssignDetailsPojo drug) {
		// TODO Auto-generated method stub
		return TMDao.testAssignDetailsUpdate(drug);
	}


	public int testAssignDetailsDelete(int did) {
		// TODO Auto-generated method stub
		return TMDao.testAssignDetailsDelete(did);
	}


	/*public LabTestAssignDetailsPojo testAssignDetailsSearchByName(String name) {
		// TODO Auto-generated method stub
		return TMDao.testAssignDetailsSearchByName(name);
	}*/
	
	public LabTestAssignDetailsPojo testAssignDetailsSearchById(int id) {
		// TODO Auto-generated method stub
		return TMDao.testAssignDetailsSearchById(id);
	}
	
	public List<LabTestAssignDetailsPojo> testAssignDetailsSearchByLabTestId(int id) {
		// TODO Auto-generated method stub
		return TMDao.testAssignDetailsSearchByLabTestId(id);
	}
}
