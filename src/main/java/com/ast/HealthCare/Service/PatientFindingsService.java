package com.ast.HealthCare.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.PatientFindingsDao;
import com.ast.HealthCare.Pojo.PatientFindingsPojo;

@Service
public class PatientFindingsService {

	@Autowired
	PatientFindingsDao pfd;

	public boolean addPatientFindings(List<PatientFindingsPojo> findings) {
		// TODO Auto-generated method stub
		return pfd.addPatientFindings(findings,"");
	}

	public List<PatientFindingsPojo> patientFindingsAll() {
		// TODO Auto-generated method stub
		return pfd.patientFindingsAll();
	}

	public int patientFindingsDelete(String pid) {
		// TODO Auto-generated method stub
		return pfd.patientFindingsDelete(pid);
	}

	public List<PatientFindingsPojo> patientFindingsSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return pfd.patientFindingsSearchByPatientId(pid);
	}
	
}
