package com.ast.HealthCare.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.BirthDetailsDao;
import com.ast.HealthCare.Dao.PatientDischargeContentDao;
import com.ast.HealthCare.Dao.PatientDischargeSummaryDao;
import com.ast.HealthCare.Pojo.PatientDischargeSummaryPojo;

@Service
public class PatientDischargeSummaryService {

	@Autowired
	PatientDischargeSummaryDao patientDischargeSummaryDao;
	@Autowired
	PatientDischargeContentService patientContentService;
	@Autowired
	PatientDischargeContentService pcService;
	@Autowired
	PatientDischargeContentDao pcDao;
	@Autowired
	BirthDetailsDao bDao;

	public PatientDischargeSummaryPojo addPatientDischargeSummary(PatientDischargeSummaryPojo dis) {
		// TODO Auto-generated method stub
		PatientDischargeSummaryPojo pojo = new PatientDischargeSummaryPojo();
		PatientDischargeSummaryPojo ippojo = patientDischargeSummaryDao.patientDischargeSummaryByIPNo(dis.getIpNo());
		System.out.println(ippojo.toString());
		if(ippojo.getPatientDischargeId() == 0)
		{
			 int disId =  patientDischargeSummaryDao.addPatientDischargeSummary(dis);
			 for(int i=0;i<dis.getContentPojo().size();i++)
			 {
				 dis.getContentPojo().get(i).setPatientDischargeId(disId);
			 }
			 patientContentService.addPatientDischargeContent(dis.getContentPojo());
			 pojo = patientDischargeSummaryDao.patientDischargeSummaryById(disId);
			 pojo.setResponse("NOTEXISTS");
			 
		}
		else if(ippojo.getPatientDischargeId() != 0)
		{
			
		
		 pojo.setResponse("EXISTS");
		}
		 return pojo;
	}

	public List<PatientDischargeSummaryPojo> patientDischargeSummaryAll() {
		// TODO Auto-generated method stub
		return patientDischargeSummaryDao.patientDischargeSummaryAll();
	}
	
	public List<PatientDischargeSummaryPojo> patientDischargeSummaryByDischargeDate(Date from,Date to) {
		// TODO Auto-generated method stub
		return patientDischargeSummaryDao.patientDischargeSummaryByDischargeDate(from,to);
	}
	
	public PatientDischargeSummaryPojo patientDischargeSummaryById(int id) {
		// TODO Auto-generated method stub
		return patientDischargeSummaryDao.patientDischargeSummaryById(id);
	}
	
	public PatientDischargeSummaryPojo patientDischargeSummaryByPatientId(String id) {
		// TODO Auto-generated method stub
		return patientDischargeSummaryDao.patientDischargeSummaryByPatientId(id);
	}
	
	
	public PatientDischargeSummaryPojo getAutoGenerateIPNo(int typeid) {
		// TODO Auto-generated method stub
		return patientDischargeSummaryDao.getAutoGenerateIPNo(typeid);
	}

	public int patientDischargeSummaryDelete(int pid) {
		// TODO Auto-generated method stub
		return patientDischargeSummaryDao.patientDischargeSummaryDelete(pid);
	}

	public PatientDischargeSummaryPojo patientDischargeSummaryUpdate(PatientDischargeSummaryPojo dt) {
		// TODO Auto-generated method stub
		System.out.println(dt.toString());
		String res="";
		PatientDischargeSummaryPojo pojo = new PatientDischargeSummaryPojo();
		PatientDischargeSummaryPojo ippojo = patientDischargeSummaryDao.patientDischargeSummaryByIPNoAndNotId(dt.getIpNo(),dt.getPatientDischargeId());
		System.out.println(ippojo.toString());
		
		
		if(ippojo.getPatientDischargeId() == 0)
		{
			pcDao.patientDischargeContentDeleteByDischargeId(dt.getPatientDischargeId());
		for(int i=0;i<dt.getContentPojo().size();i++)
		{
			bDao.BirthDetailsDeleteByContentId(dt.getContentPojo().get(i).getPatientContentId());
			dt.getContentPojo().get(i).setPatientDischargeId(dt.getPatientDischargeId());
		}
		
		
		pcService.addPatientDischargeContent(dt.getContentPojo());
		boolean result= patientDischargeSummaryDao.patientDischargeSummaryUpdate(dt);
		pojo.setResponse("NOTEXISTS");
		}
		
		else if(ippojo.getPatientDischargeId() != 0)
		{
			pojo.setResponse("EXISTS");
		}
		
		return pojo;
	}
}
