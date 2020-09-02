package com.ast.HealthCare.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.PatientInvestigationDao;
import com.ast.HealthCare.Pojo.PatientInvestigationPojo;

@Service
public class PatientInvestigationService {

	@Autowired
	PatientInvestigationDao pid;

	public boolean addPatientInvestigation(List<PatientInvestigationPojo> imp) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		return pid.addPatientInvestigation(imp,"");
		
	}

	public List<PatientInvestigationPojo> patientInvestigationAll() {
		// TODO Auto-generated method stub
		return pid.patientInvestigationAll();
	}

	public int patientInvestigationDelete(int pid2, int inv) throws IOException {
		// TODO Auto-generated method stub
		return pid.patientInvestigationDelete(pid2,inv);
	}

	public List<PatientInvestigationPojo> patientInvestigationSearchByPatientId(String pid2) {
		// TODO Auto-generated method stub
		return pid.patientInvestigationSearchByPatientId(pid2);
	}

	public List<PatientInvestigationPojo> patientInvestigationReportByPatientId(String pid2) {
		// TODO Auto-generated method stub
		return pid.patientInvestigationReportByPatientId( pid2);
	}
}
