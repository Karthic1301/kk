package com.ast.HealthCare.Dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.PrescriptionChildPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PrescriptionChildDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	@Autowired
	PatientComplaintsDao compdao;
	
	@Autowired
	PatientDiagnosisDao diagdao;
	
	@Autowired
	PatientDiseaseDao diseasedao;

	@Autowired
	PatientFindingsDao finddao;
	
	@Autowired
	PatientInvestigationDao invdao;
	
	@Autowired
	PatientNotesDao notesdao;

	@Autowired
	PrescriptionDrugDao predao;
	
	@Autowired
	AppointmentDao appdao;
	
	@Autowired
	PatientTestDao ptDao;

	//protected JdbcTemplate jdbcTemplate;
	JpaConfiguration jpa = new JpaConfiguration();
	PrescriptionChildDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("PrescriptionChildDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public PrescriptionChildPojo childAll(String pid) {
		// TODO Auto-generated method stub
		System.out.println("prescriptionchild all dao");
		PrescriptionChildPojo pcp = new PrescriptionChildPojo();
		
		pcp.setComp(compdao.patientComplaintsSearchByPatientId(pid));
		pcp.setDiag(diagdao.patientDiagnosisSearchByPatientId(pid));
		pcp.setDisease(diseasedao.patientDiseaseSearchByPatientId(pid));
		pcp.setFindings(finddao.patientFindingsSearchByPatientId(pid));
		pcp.setInves(invdao.patientInvestigationSearchByPatientId(pid));
		pcp.setNotes(notesdao.patientNotesSearchByPatientId(pid));
		pcp.setPres(predao.prescriptionDrugSearchByPatientId(pid));
		pcp.setTest(ptDao.patientTestByPrescriptionNo(pid));
		return pcp;
	}

	public Boolean insertChild(PrescriptionChildPojo preschild,String prescriptionno) {
		// TODO Auto-generated method stub
		System.out.println("insert child DAO");
		System.out.println("dei va "+ preschild);
		Boolean bb = compdao.addPatientComplaints(preschild.getComp(), prescriptionno);
		
		
		System.out.println("paradesi 1st ***");
		
		System.out.println("complaints "+preschild.getComp());
		System.out.println("notes "+preschild.getNotes());
		System.out.println("inves "+preschild.getInves());
		System.out.println("diagnosis "+preschild.getDiag());
		System.out.println("findings "+preschild.getFindings());
		System.out.println("Fees "+preschild.getTest());
		
		
		Boolean ee = notesdao.addPatientNotes(preschild.getNotes(),prescriptionno);
		System.out.println("paradesi 2nd ***");
		Boolean cc = null;
		try {
			cc = invdao.addPatientInvestigation(preschild.getInves(),prescriptionno);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("paradesi 3rd ***");
		Boolean aa = diagdao.addDiag(preschild.getDiag(),prescriptionno);
		
		System.out.println("paradesi 4th ***");
		Boolean dd =finddao.addPatientFindings(preschild.getFindings(),prescriptionno);
		System.out.println("paradesi 5th ***");
		
		Boolean ff = predao.addPrescriptionDrug(preschild.getPres(),prescriptionno);
		System.out.println("paradesi 6th ***");
		Boolean gg = diseasedao.addPatientDisease(preschild.getDisease(),prescriptionno);
		System.out.println("paradesi 7th ***");

		Boolean hh = ptDao.addPatientTest(preschild.getTest(),prescriptionno);
		System.out.println("Patient Test");

		
		System.out.println("final result "+aa +" "+ bb +" "+ cc +" "+ dd +" "+ ee+" "+ff+" "+gg+" ");
		
		return true;
	}
	/*public Boolean updatePrescriptionChild(PrescriptionChildPojo preschild) {
		// TODO Auto-generated method stub
		System.out.println("insert child DAO");
		System.out.println("dei va "+ preschild);
		Boolean bb = compdao.patientComplaintsUpdate1(preschild.getComp());
		
		
		System.out.println("paradesi 1st ***");
		
		System.out.println("complaints "+preschild.getComp());
		System.out.println("notes "+preschild.getNotes());
		System.out.println("inves "+preschild.getInves());
		System.out.println("diagnosis "+preschild.getDiag());
		System.out.println("findings "+preschild.getFindings());
		
		
		
		Boolean ee = notesdao.addPatientNotes(preschild.getNotes());
		System.out.println("paradesi 2nd ***");
		Boolean cc = null;
		try {
			cc = invdao.addPatientInvestigation(preschild.getInves());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("paradesi 3rd ***");
		Boolean aa = diagdao.addDiag(preschild.getDiag());
		
		System.out.println("paradesi 4th ***");
		Boolean dd =finddao.addPatientFindings(preschild.getFindings());
		System.out.println("paradesi 5th ***");
		
		Boolean ff = predao.addPrescriptionDrug(preschild.getPres());
		System.out.println("paradesi 6th ***");
		Boolean gg = diseasedao.addPatientDisease(preschild.getDisease());
		System.out.println("paradesi 7th ***");
		
		System.out.println("final result "+aa +" "+ bb +" "+ cc +" "+ dd +" "+ ee+" "+ff+" "+gg);
		
		return true;
	}
*/
	public PrescriptionChildPojo prescriptionChildSearchByPrescriptionNo(String pid) {
		// TODO Auto-generated method stub
		PrescriptionChildPojo pcp = new PrescriptionChildPojo();
		
		pcp.setComp(compdao.patientComplaintsSearchByPrescriptionNo1(pid));
		pcp.setDiag(diagdao.patientDiagnosisSearchByPrescriptionNo1(pid));
		pcp.setDisease(diseasedao.patientDiseaseSearchByPrescriptionNo1(pid));
		pcp.setFindings(finddao.patientFindingsSearchByPrescriptionNo1(pid));
		pcp.setInves(invdao.patientInvestigationSearchByPrescriptionNo1(pid));
		pcp.setNotes(notesdao.patientNotesSearchByPrescriptionNo1(pid));
		pcp.setPres(predao.prescriptionDrugSearchByPrescriptionNo1(pid));
		pcp.setTest(ptDao.patientTestByPrescriptionNo(pid));
		return pcp;
	}
}
