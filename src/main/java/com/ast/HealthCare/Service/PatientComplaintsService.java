package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.PatientComplaintsDao;
import com.ast.HealthCare.Pojo.PatientComplaintsPojo;

@Service
public class PatientComplaintsService {

	@Autowired
	PatientComplaintsDao pcd;

	public boolean addPatientComplaints(List<PatientComplaintsPojo> comppojo) {
		// TODO Auto-generated method stub
		return pcd.addPatientComplaints(comppojo,"");
	}

	public List<PatientComplaintsPojo> patientComplaintsAll() {
		// TODO Auto-generated method stub
		return pcd.patientComplaintsAll();
	}

	public int patientComplaintsDelete(String pid) {
		// TODO Auto-generated method stub
		return pcd.patientComplaintsDelete(pid);
	}

	public List<PatientComplaintsPojo> patientComplaintsSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return pcd.patientComplaintsSearchByPatientId(pid);
	}

	public PatientComplaintsPojo patientComplaintsSearchByPrescriptionNo(String pid) {
		// TODO Auto-generated method stub
		return pcd.patientComplaintsSearchByPrescriptionNo(pid);
	}

	public boolean patientComplaintsUpdate(PatientComplaintsPojo comppojo, String pid) {
		comppojo.setPrescriptionNo(pid);
		return pcd.patientComplaintsUpdate(comppojo);
	}
}
