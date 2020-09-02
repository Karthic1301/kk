package com.ast.HealthCare.Service;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.PatientDao;
import com.ast.HealthCare.Dao.PrescriptionMasterDao;
import com.ast.HealthCare.Dao.VitalInfoDao;
import com.ast.HealthCare.Pojo.DrugTypePojo;
import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.PrescriptionMasterPojo;

@Service
public class PrescriptionMasterService {

	@Autowired
	PrescriptionMasterDao prescriptionMasterDao;
	@Autowired
	PatientDao pDao;
	@Autowired
	VitalInfoDao vDao;
	
	public PrescriptionMasterPojo addPrescriptionMaster(PrescriptionMasterPojo prescription) {
		// TODO Auto-generated method stub
		String pid="";
		System.out.println(prescription.toString());
		if(prescription.getPatientId()==null)
		{
			pid = pDao.register(prescription.getPatientPojo());
			vDao.addVitalInfo(prescription.getVitalPojo());
			prescription.setPatientId(pid);
		}
		
		
		return prescriptionMasterDao.addPrescriptionMaster(prescription);
	}

	public List<PrescriptionMasterPojo> prescriptionMasterAll() {
		// TODO Auto-generated method stub
		return prescriptionMasterDao.prescriptionMasterAll();
	}

	public List<PrescriptionMasterPojo> prescriptionMasterByBillId(int billid) {
		// TODO Auto-generated method stub
		return prescriptionMasterDao.prescriptionMasterByBillId(billid);
	}
	
	public List<PrescriptionMasterPojo> prescriptionMasterSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return prescriptionMasterDao.prescriptionMasterSearchByPatientId(pid);
	}

	public List<PrescriptionMasterPojo> prescriptionMasterSearchByDoctorId(String did) {
		// TODO Auto-generated method stub
		return prescriptionMasterDao.prescriptionMasterSearchByDoctorId(did);
	}

	public PrescriptionMasterPojo prescriptionMasterSearchByPrescriptionNo(String pid) {
		// TODO Auto-generated method stub
		return prescriptionMasterDao.prescriptionMasterSearchByPrescriptionNo(pid);
	}

	public Boolean prescriptionMasterUpdate(String pno, PrescriptionMasterPojo prescription) {
		// TODO Auto-generated method stub
		prescription.setPrescriptionNo(pno);
		return prescriptionMasterDao.prescriptionMasterUpdate(prescription);
	}

	public int prescriptionMasterDelete(String pno) {
		// TODO Auto-generated method stub
		return prescriptionMasterDao.prescriptionMasterDelete(pno);
	}

	public PrescriptionMasterPojo prescriptionMasterSearchByDoctorPatientAndDate(String did, String pid, Date date) {
		// TODO Auto-generated method stub
		return prescriptionMasterDao.prescriptionMasterSearchByDoctorPatientAndDate(did,pid,date);
	}

}

