package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.LabTestNormalDetailsDao;
import com.ast.HealthCare.Pojo.LabTestNormalDetailsPojo;


@Service
public class LabTestNormalDetailsService {

	@Autowired
	LabTestNormalDetailsDao TNDao;
	

	public boolean addTestNormalDetails(List<LabTestNormalDetailsPojo> drug) {
		// TODO Auto-generated method stub
		return TNDao.addTestNormalDetails(drug);
	}


	public List<LabTestNormalDetailsPojo> testNormalDetailsAll() {
		// TODO Auto-generated method stub
		return TNDao.testNormalDetailsAll();
	}


	public boolean testNormalDetailsUpdate(LabTestNormalDetailsPojo drug) {
		// TODO Auto-generated method stub
		return TNDao.testNormalDetailsUpdate(drug);
	}


	public int testNormalDetailsDelete(int did) {
		// TODO Auto-generated method stub
		return TNDao.testNormalDetailsDelete(did);
	}
	
	


	/*public LabTestNormalDetailsDetailsPojo testNormalDetailsSearchByName(String name) {
		// TODO Auto-generated method stub
		return TNDao.testNormalDetailsSearchByName(name);
	}*/
	
	public LabTestNormalDetailsPojo testNormalDetailsSearchById(int id) {
		// TODO Auto-generated method stub
		return TNDao.testNormalDetailsSearchById(id);
	}
	
	public List<LabTestNormalDetailsPojo> testNormalDetailsSearchByTestNormalId(int id) {
		// TODO Auto-generated method stub
		return TNDao.testNormalDetailsSearchByTestNormalId(id);
	}
	
	
}
