package com.ast.HealthCare.Service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.LabTestReportEntryDao;
import com.ast.HealthCare.Dao.LabTestReportEntryDetailsDao;
import com.ast.HealthCare.Pojo.LabTestNormalPojo;
import com.ast.HealthCare.Pojo.LabTestReportEntryPojo;

@Service
public class LabTestReportEntryService {
	
	@Autowired
	LabTestReportEntryDao TNDao;
	@Autowired
	LabTestReportEntryDetailsDao TREDao;
	
	

	

	public LabTestReportEntryPojo addTestReportEntry(LabTestReportEntryPojo drug) {
		// TODO Auto-generated method stub
		LabTestReportEntryPojo pojo = new LabTestReportEntryPojo();
		pojo = TNDao.addTestReportEntry(drug);
		for(int i=0; i< drug.getLabTestEntryReportDetail().size();i++)
		{
			
			drug.getLabTestEntryReportDetail().get(i).setTestReportEntryId(pojo.getTestReportEntryId());
		}
		TREDao.addTestReportEntryDetails(drug.getLabTestEntryReportDetail());
		return pojo;
	}


	public List<LabTestReportEntryPojo> testReportEntryAll() {
		// TODO Auto-generated method stub        
		
		return TNDao.testReportEntryAll();
	}

	public List<LabTestReportEntryPojo> testReportEntryNotIssuedStatus() {
		// TODO Auto-generated method stub        
		
		return TNDao.testReportEntryNotIssuedStatus();
	}
	
	public int getAutoGenerateReportNo() {
		// TODO Auto-generated method stub        
		
		return TNDao.getAutoGenerateReportNo();
	}
	
	
	public List<LabTestReportEntryPojo> testReportEntryListWithoutResult() {
		// TODO Auto-generated method stub        
		
		return TNDao.testReportEntryListWithoutResult();
	}
	
	public List<LabTestReportEntryPojo> testReportEntryListByIssueDate(Date from,Date to) {
		// TODO Auto-generated method stub        
		
		return TNDao.testReportEntryListByIssueDate(from,to);
	}

	public boolean testReportEntryUpdate(LabTestReportEntryPojo drug) {
		
		LabTestReportEntryPojo pojo=new LabTestReportEntryPojo();
		TREDao.testReportEntryDetailsDeleteList(drug.getTestReportEntryId());
		boolean flag=TNDao.testReportEntryUpdate(drug);
		for(int i=0; i< drug.getLabTestEntryReportDetail().size();i++)
		{
			
			drug.getLabTestEntryReportDetail().get(i).setTestReportEntryId(drug.getTestReportEntryId());
		}
		TREDao.addTestReportEntryDetails(drug.getLabTestEntryReportDetail());
		
		return flag;
	
	}
	
	public boolean testReportEntryUpdateBalance(LabTestReportEntryPojo drug) {
		
		
		
		boolean flag=TNDao.testReportEntryUpdateBalance(drug);
		return flag;
	
	}



	public int testReportEntryDelete(int did) {
		// TODO Auto-generated method stub
		return TNDao.testReportEntryDelete(did);
	}


	/*public LabTestReportEntryPojo testReportEntrySearchByName(String name) {
		// TODO Auto-generated method stub
		return TNDao.testReportEntrySearchByName(name);
	}*/
	
	public LabTestReportEntryPojo testReportEntrySearchById(int id) {
		// TODO Auto-generated method stub
		return TNDao.testReportEntrySearchById(id);
	}
	
	public LabTestReportEntryPojo testReportEntrySearchByReportNo(String no) {
		// TODO Auto-generated method stub
		return TNDao.testReportEntrySearchByReportNo(no);
	}

}
