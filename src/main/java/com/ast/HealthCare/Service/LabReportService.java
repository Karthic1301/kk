package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.LabReportDao;
import com.ast.HealthCare.Pojo.LabReportPojo;

@Service
public class LabReportService {

	@Autowired
	LabReportDao TNDao;
	

	public boolean addReport(LabReportPojo drug) {
		// TODO Auto-generated method stub
		return TNDao.addReport(drug);
	}


	public List<LabReportPojo> ReportAll() {
		// TODO Auto-generated method stub
		return TNDao.ReportAll();
	}


	public boolean ReportUpdate(LabReportPojo drug) {
		// TODO Auto-generated method stub
		return TNDao.ReportUpdate(drug);
	}


	public int ReportDelete(int did) {
		// TODO Auto-generated method stub
		return TNDao.ReportDelete(did);
	}


	/*public LabReportPojo ReportSearchByName(String name) {
		// TODO Auto-generated method stub
		return TNDao.ReportSearchByName(name);
	}*/
	
	public LabReportPojo ReportSearchById(int id) {
		// TODO Auto-generated method stub
		return TNDao.ReportSearchById(id);
	}
}
