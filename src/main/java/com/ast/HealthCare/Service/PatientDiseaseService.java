package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.PatientDiseaseDao;
import com.ast.HealthCare.Pojo.PatientDiseasePojo;
import com.ast.HealthCare.Pojo.PatientPojo;

@Service
public class PatientDiseaseService {

	@Autowired
	PatientDiseaseDao patientDieaseDao;
	
	public Boolean addPatientDisease(List<PatientDiseasePojo> patientDiease) {
		// TODO Auto-generated method stub
		return patientDieaseDao.addPatientDisease(patientDiease,"");
	}

	public List<PatientDiseasePojo> patientDiseaseAll() {
		// TODO Auto-generated method stub
		return patientDieaseDao.patientDiseaseAll();
	}

	public List<PatientDiseasePojo> patientDiseaseSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return patientDieaseDao.patientDiseaseSearchByPatientId(pid);
	}

	public int patientDiseaseDelete(String pid, int did) {
		// TODO Auto-generated method stub
		return patientDieaseDao.patientDiseaseDelete(pid,did);
	}

	public List<PatientDiseasePojo> patientDiseaseSearchByPatientIdAndDiseaseId(String pid, int did) {
		// TODO Auto-generated method stub
		return patientDieaseDao.patientDiseaseSearchByPatientIdAndDiseaseId(pid,did);
	}
	
	public List<PatientPojo> patientDiseaseSearchByDiseaseIdForSMS(int pid) {
		// TODO Auto-generated method stub
		return patientDieaseDao.patientDiseaseSearchByDiseaseIdForSMS(pid);
	}

}
