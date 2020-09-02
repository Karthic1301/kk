package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.PatientNotesDao;
import com.ast.HealthCare.Pojo.PatientNotesPojo;

@Service
public class PatientNotesService {
	
	@Autowired
	PatientNotesDao pnDao;

	public boolean addPatientNotes(List<PatientNotesPojo> patientNotes) {
		return pnDao.addPatientNotes(patientNotes,"");
	}

	public List<PatientNotesPojo> patientNotesAll() {
		// TODO Auto-generated method stub
		return pnDao.patientNotesAll();
	}

	public PatientNotesPojo patientNotesSearchByPrescriptionId(String pid) {
		// TODO Aduto-generated method stub
		return pnDao.patientNotesSearchByPrescriptionId(pid);
	}

	public List<PatientNotesPojo> patientNotesSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return pnDao.patientNotesSearchByPatientId(pid);
	}

}
