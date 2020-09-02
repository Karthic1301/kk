package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.PatientTestDao;
import com.ast.HealthCare.Dao.PrescriptionMasterDao;
import com.ast.HealthCare.Pojo.PatientPrescriptionDetailPojo;
import com.ast.HealthCare.Pojo.PatientTestPojo;
import com.ast.HealthCare.Pojo.PrescriptionMasterPojo;

@Service
public class PatientTestService {

	@Autowired
	PatientTestDao patientTestDao;
	@Autowired
	PrescriptionMasterDao pmDao;

	public boolean addPatientTest(List<PatientTestPojo> dis) {
		// TODO Auto-generated method stub
		return patientTestDao.addPatientTest(dis,"");
	}

	public List<PatientTestPojo> patientTestAll() {
		// TODO Auto-generated method stub
		return patientTestDao.patientTestAll();
	}
	
	public PatientPrescriptionDetailPojo patientTestByPrescriptionNo(String no) {
		// TODO Auto-generated method stub
		PatientPrescriptionDetailPojo res = new PatientPrescriptionDetailPojo();
		PrescriptionMasterPojo ppojo = pmDao.prescriptionMasterSearchByPrescriptionNo(no);
		int pid = ppojo.getPrescriptionId();
		System.out.println("Prescription ID"+pid);
		List<PatientTestPojo> ptpojo = patientTestDao.patientTestByPrescriptionNo(no);
		if(pid !=0)
		{
			PrescriptionMasterPojo pmpojo = pmDao.prescriptionMasterCheckPrescriptionByBillId(no);
			int preid =pmpojo.getPrescriptionId();
			System.out.println("Prescription ID For BILLID"+preid);
			if(preid !=0)
			{
				res.setTestpojo(ptpojo);
				res.setCode(3);
				res.setResponse("Success");
				
			}
			else
			{
				res.setCode(2);
				res.setResponse("Already Billed");
			}
		}
		else
		{
			res.setCode(1);
			res.setResponse("InValid Prescription Number");
		}
		
		return res;
	}
	
	public PatientTestPojo patientTestById(int id) {
		// TODO Auto-generated method stub
		return patientTestDao.patientTestById(id);
	}

	public int patientTestDelete(int pid) {
		// TODO Auto-generated method stub
		return patientTestDao.patientTestDelete(pid);
	}

	public boolean patientTestUpdate(PatientTestPojo dt) {
		// TODO Auto-generated method stub
		return patientTestDao.patientTestUpdate(dt);
	}
}
