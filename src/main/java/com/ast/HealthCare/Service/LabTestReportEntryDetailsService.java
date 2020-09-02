package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.LabTestReportEntryDetailsDao;
import com.ast.HealthCare.Pojo.LabTestReportEntryDetailsPojo;

@Service
public class LabTestReportEntryDetailsService {

	@Autowired
	LabTestReportEntryDetailsDao TNDao;
	

	public boolean addTestReportEntryDetails(List<LabTestReportEntryDetailsPojo> drug) {
		// TODO Auto-generated method stub
		return TNDao.addTestReportEntryDetails(drug);
	}


	public List<LabTestReportEntryDetailsPojo> testReportEntryDetailsAll() {
		// TODO Auto-generated method stub
		return TNDao.testReportEntryDetailsAll();
	}


	public boolean testReportEntryDetailsUpdate(LabTestReportEntryDetailsPojo drug) {
		// TODO Auto-generated method stub
		return TNDao.testReportEntryDetailsUpdate(drug);
	}


	public int testReportEntryDetailsDelete(int did) {
		// TODO Auto-generated method stub
		return TNDao.testReportEntryDetailsDelete(did);
	}


	/*public LabTestReportEntryDetailsPojo testReportEntryDetailsSearchByName(String name) {
		// TODO Auto-generated method stub
		return TNDao.testReportEntryDetailsSearchByName(name);
	}*/
	
	public LabTestReportEntryDetailsPojo testReportEntryDetailsSearchById(int id) {
		// TODO Auto-generated method stub
		return TNDao.testReportEntryDetailsSearchById(id);
	}
	
	public List<LabTestReportEntryDetailsPojo> testReportEntryDetailsSearchByReportId(int id) {
		// TODO Auto-generated method stub
		return TNDao.testReportEntryDetailsSearchByReportId(id);
	}
	
}
