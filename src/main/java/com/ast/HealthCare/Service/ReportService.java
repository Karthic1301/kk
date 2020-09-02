package com.ast.HealthCare.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.ReportDao;
import com.ast.HealthCare.Pojo.ReportPojo;

@Service
public class ReportService {

	@Autowired
	ReportDao repdao;

	public List<ReportPojo> reportByDate(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return repdao.reportByDate(date1,date2);
	}

	public List<ReportPojo> reportByDisease(int diseasename, Date date1, Date date2) {
		// TODO Auto-generated method stub
		return repdao.reportByDisease(diseasename,date1,date2);
	}

	public List<ReportPojo> reportByDrug(int drugname, Date date1, Date date2) {
		// TODO Auto-generated method stub
		return repdao.reportByDrug(drugname,date1,date2);
	}

	public List<ReportPojo> reportByDoctor(String doctorid, Date date1) {
		// TODO Auto-generated method stub
		return repdao.reportByDoctor(doctorid,date1);
	}

	public List<ReportPojo> reportByDoctor1(String doctorid) {
		// TODO Auto-generated method stub
		return repdao.reportByDoctor1(doctorid);
	}

	public List<ReportPojo> reportByAll() {
		// TODO Auto-generated method stub
		return repdao.reportByAll();
	}

	public List<ReportPojo> reportByDoctor(String doctorid, Date date1, Date date2) {
		// TODO Auto-generated method stub
		return repdao.reportByDoctor(doctorid,date1,date2);
	}

	public List<ReportPojo> reportByDisease(int diseasename) {
		// TODO Auto-generated method stub
		return repdao.reportByDisease(diseasename);
	}

	public List<ReportPojo> reportByDrug(int drugname) {
		// TODO Auto-generated method stub
		return repdao.reportByDrug(drugname);
	}
	
	public List<ReportPojo> reportByDoctorIdAndDrug(String doctorid, int drugname) {
		// TODO Auto-generated method stub
		return repdao.reportByDoctorIdAndDrug(doctorid,drugname);
	}
	
	public List<ReportPojo> reportByDoctorIdAndDrug(String doctorid,int drugname, Date date1, Date date2) {
		// TODO Auto-generated method stub
		return repdao.reportByDoctorIdAndDrug(doctorid,drugname,date1,date2);
	}
	
	public List<ReportPojo> reportByDoctorIdAndDiseases(String doctorid,int diseasename) {
		// TODO Auto-generated method stub
		return repdao.reportByDoctorIdAndDiseases(doctorid,diseasename);
	}
	
	public List<ReportPojo> reportByDoctorIdAndDiseases(String doctorid,int diseasename, Date date1, Date date2) {
		// TODO Auto-generated method stub
		return repdao.reportByDoctorIdAndDiseases(doctorid,diseasename,date1,date2);
	}
	
}
