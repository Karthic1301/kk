package com.ast.HealthCare.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.LabTestResultEntryDao;
import com.ast.HealthCare.Dao.LabTestResultEntryDetailsDao;
import com.ast.HealthCare.Pojo.LabTestReportEntryPojo;
import com.ast.HealthCare.Pojo.LabTestResultEntryPojo;

@Service
public class LabTestResultEntryService {
	
	@Autowired
	LabTestResultEntryDao TNDao;
	@Autowired
	LabTestResultEntryDetailsDao TREDao;
	

	public LabTestResultEntryPojo addTestResultEntry(LabTestResultEntryPojo drug) {
		// TODO Auto-generated method stub
		LabTestResultEntryPojo pojo = new LabTestResultEntryPojo();
		System.out.println(drug.toString());
		pojo = TNDao.addTestResultEntry(drug);
		System.out.println(drug.getTestResultDetails().size());
		for(int i=0; i< drug.getTestResultDetails().size();i++)
		{
			
			drug.getTestResultDetails().get(i).setTestResultId(pojo.getTestResultId());
		}
		TREDao.addTestResultEntryDetails(drug.getTestResultDetails());
		return pojo;
		
	}


	public List<LabTestResultEntryPojo> testResultEntryAll() {
		// TODO Auto-generated method stub
		return TNDao.testResultEntryAll();
	}


	public boolean testResultEntryUpdate(LabTestResultEntryPojo drug) {
		// TODO Auto-generated method stub
		LabTestResultEntryPojo pojo=new LabTestResultEntryPojo();
		TREDao.testResultEntryDetailsDeleteList(drug.getTestResultId());
		boolean flag=TNDao.testResultEntryUpdate(drug);
		for(int i=0; i< drug.getTestResultDetails().size();i++)
		{
			
			drug.getTestResultDetails().get(i).setTestResultId(drug.getTestResultId());
		}
		TREDao.addTestResultEntryDetails(drug.getTestResultDetails());
		
		return flag;
	}
	
	public List<LabTestResultEntryPojo> testResultEntryByReportDate(Date from,Date to) {
		// TODO Auto-generated method stub
		return TNDao.testResultEntryByReportDate(from,to);
	}

	public List<LabTestResultEntryPojo> testResultEntryByReportDateAndTest(Date from,Date to,int testid) {
		// TODO Auto-generated method stub
		return TNDao.testResultEntryByReportDateAndTest(from,to,testid);
	}


	public int testResultEntryDelete(int did) {
		// TODO Auto-generated method stub
		return TNDao.testResultEntryDelete(did);
	}


	/*public LabTestResultEntryPojo testResultEntrySearchByName(String name) {
		// TODO Auto-generated method stub
		return TNDao.testResultEntrySearchByName(name);
	}*/
	
	public LabTestResultEntryPojo testResultEntrySearchById(int id) {
		// TODO Auto-generated method stub
		return TNDao.testResultEntrySearchById(id);
	}


}
