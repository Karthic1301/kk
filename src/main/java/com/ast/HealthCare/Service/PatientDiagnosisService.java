package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.PatientDiagnosisDao;
import com.ast.HealthCare.Pojo.PatientDiagnosisPojo;

@Service
public class PatientDiagnosisService {

	@Autowired
	PatientDiagnosisDao pdd;

	public boolean addDiag(List<PatientDiagnosisPojo> diag) {
		// TODO Auto-generated method stub
		return pdd.addDiag(diag,"");
	}

	public List<PatientDiagnosisPojo> diagnosisAll() {
		// TODO Auto-generated method stub
		return pdd.diagnosisAll();
	}

	public int diagnosisDelete(String pid) {
		// TODO Auto-generated method stub
		return pdd.diagnosisDelete(pid);
	}

	public List<PatientDiagnosisPojo> patientDiagnosisSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return pdd.patientDiagnosisSearchByPatientId(pid);
	}

	public PatientDiagnosisPojo patientDiagnosisSearchByPrescriptionId(int pid) {
		// TODO Auto-generated method stub
		return pdd.patientDiagnosisSearchByPrescriptionId(pid);
	}

	public int diagnosisDeleteByDignosisId(int did) {
		// TODO Auto-generated method stub
		return pdd.diagnosisDeleteByDiagnosisId(did);
	}
}
