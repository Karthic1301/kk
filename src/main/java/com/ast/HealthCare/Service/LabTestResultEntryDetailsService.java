package com.ast.HealthCare.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.LabTestResultEntryDetailsDao;
import com.ast.HealthCare.Pojo.LabTestResultEntryDetailsPojo;

@Service
public class LabTestResultEntryDetailsService {

	@Autowired
	LabTestResultEntryDetailsDao TNDao;
	

	public boolean addTestResultEntryDetails(List<LabTestResultEntryDetailsPojo> drug) {
		// TODO Auto-generated method stub
		return TNDao.addTestResultEntryDetails(drug);
	}


	public List<LabTestResultEntryDetailsPojo> testResultEntryDetailsAll() {
		// TODO Auto-generated method stub
		return TNDao.testResultEntryDetailsAll();
	}


	public boolean testResultEntryDetailsUpdate(LabTestResultEntryDetailsPojo drug) {
		// TODO Auto-generated method stub
		return TNDao.testResultEntryDetailsUpdate(drug);
	}


	public int testResultEntryDetailsDelete(int did) {
		// TODO Auto-generated method stub
		return TNDao.testResultEntryDetailsDelete(did);
	}


	/*public LabTestResultEntryDetailsPojo testResultEntryDetailsSearchByName(String name) {
		// TODO Auto-generated method stub
		return TNDao.testResultEntryDetailsSearchByName(name);
	}*/
	
	public LabTestResultEntryDetailsPojo testResultEntryDetailsSearchById(int id) {
		// TODO Auto-generated method stub
		return TNDao.testResultEntryDetailsSearchById(id);
	}
	
	public List<LabTestResultEntryDetailsPojo> testResultEntryDetailsSearchByResultId(int id) {
		// TODO Auto-generated method stub
		return TNDao.testResultEntryDetailsSearchByResultId(id);
	}
	
	
}
