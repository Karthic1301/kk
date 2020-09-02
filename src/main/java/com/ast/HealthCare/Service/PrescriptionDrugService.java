package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.PrescriptionDrugDao;
import com.ast.HealthCare.Pojo.PrescriptionDrugPojo;

@Service
public class PrescriptionDrugService {

	@Autowired
	PrescriptionDrugDao prescriptionDrugDao;
	
	public Boolean addPrescriptionDrug(List<PrescriptionDrugPojo> prescriptionDrug) {
		// TODO Auto-generated method stub
		return prescriptionDrugDao.addPrescriptionDrug(prescriptionDrug,"");
	}

	public List<PrescriptionDrugPojo> prescriptionDrugAll() {
		// TODO Auto-generated method stub
		return prescriptionDrugDao.prescriptionDrugAll();
	}

	public PrescriptionDrugPojo prescriptionDrugSearchByPrescriptionId(String pid) {
		// TODO Auto-generated method stub
		return prescriptionDrugDao.prescriptionDrugSearchByPrescriptionId(pid);
	}

	public int prescriptionDrugDelete(String pid) {
		// TODO Auto-generated method stub
		return prescriptionDrugDao.prescriptionDrugDelete(pid);
	}

	public List<PrescriptionDrugPojo> prescriptionDrugSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return prescriptionDrugDao.prescriptionDrugSearchByPatientId(pid);
	}

}
