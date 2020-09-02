package com.ast.HealthCare.Dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.DoctorPojo;
import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.PrescriptionMasterPojo;
import com.ast.HealthCare.Pojo.ReportPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class ReportDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	@Autowired
	PatientDao patdao;
	
	@Autowired
	DoctorDao docdao;
	
	@Autowired
	PrescriptionMasterDao presdao;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	ReportDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("ReportDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public List<ReportPojo> reportByDate(Date date1, Date date2) {
		// TODO Auto-generated method stub
		String sql = "select * from prescriptionmaster where consultingdate between '"+date1+"' and '"+date2+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	            while(rs.next()) {
	            	String pid = rs.getString("PATIENTID");
	            	PatientPojo patt = new PatientPojo();
	            	patt = patdao.patientSearchById(pid);
	            	DoctorPojo doc = new DoctorPojo();
	            	doc = docdao.doctorSearchById(rs.getString("DOCTORID"));
	            	ReportPojo dt = new ReportPojo();
	          
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dt.setPrescriptionDate(rs.getDate("CONSULTINGDATE"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setDoctorName(doc.getDoctorName());
	                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
	                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
	                dt.setPatientGender(patt.getPatientGender());
	                dt.setPatientAge(patt.getPatientDOB());
	                
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

	public List<ReportPojo> reportByDisease(int diseasename, Date date1, Date date2) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM patientdisease where diseaseid = "+diseasename+" and status = 'Active'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list1 = new  ArrayList<ReportPojo>();
	    		list1 = reportByDate(date1,date2);
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	        	System.out.println("list size "+list1.size());
	        	while(rs.next()) {
	            	int kek = 1;
	            	for(int i=0;i<list1.size();i++)
	            	{
	            		String presno = list1.get(i).getPrescriptionNo();
	            		if(presno.equals(rs.getString("PRESCRIPTIONNO")))
	            		{
	            			kek = 2;
	            		}
	            	}
	            	if(kek == 2)
	            	{
		            	String pid = rs.getString("PATIENTID");
		            	PatientPojo patt = new PatientPojo();
		            	patt = patdao.patientSearchById(pid);
		            	PrescriptionMasterPojo pres = new PrescriptionMasterPojo();
		            	pres = presdao.prescriptionMasterSearchByPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		            	DoctorPojo doc = new DoctorPojo();
		            	doc = docdao.doctorSearchById(pres.getDoctorId());
		            	
		            	ReportPojo dt = new ReportPojo();
		          
		                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		                dt.setPrescriptionDate(pres.getConsultingDate());
		                dt.setPatientId(rs.getString("PATIENTID"));
		                dt.setDoctorName(doc.getDoctorName());
		                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
		                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
		                dt.setPatientGender(patt.getPatientGender());
		                dt.setPatientAge(patt.getPatientDOB());
		                
		                list.add(dt);
	            	}
	            }
	            return list;
	            
	            }
	    });
	}

	public List<ReportPojo> reportByDrug(int drugname, Date date1, Date date2) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM prescriptiondrug where drugid = "+drugname;
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list1 = new  ArrayList<ReportPojo>();
	    		list1 = reportByDate(date1,date2);
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	        	System.out.println("list size "+list1.size());
	        	while(rs.next()) {
	            	int kek = 1;
	            	for(int i=0;i<list1.size();i++)
	            	{
	            		String presno = list1.get(i).getPrescriptionNo();
	            		if(presno.equals(rs.getString("PRESCRIPTIONNO")))
	            		{
	            			kek = 2;
	            		}
	            	}
	            	if(kek == 2)
	            	{
		            	String pid = rs.getString("PATIENTID");
		            	PatientPojo patt = new PatientPojo();
		            	patt = patdao.patientSearchById(pid);
		            	PrescriptionMasterPojo pres = new PrescriptionMasterPojo();
		            	pres = presdao.prescriptionMasterSearchByPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		            	DoctorPojo doc = new DoctorPojo();
		            	doc = docdao.doctorSearchById(pres.getDoctorId());
		            	
		            	ReportPojo dt = new ReportPojo();
		          
		                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		                dt.setPrescriptionDate(pres.getConsultingDate());
		                dt.setPatientId(rs.getString("PATIENTID"));
		                dt.setDoctorName(doc.getDoctorName());
		                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
		                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
		                dt.setPatientGender(patt.getPatientGender());
		                dt.setPatientAge(patt.getPatientDOB());
		                
		                list.add(dt);
	            	}
	            }
	            return list;
	            
	            }
	    });	
	}

	public List<ReportPojo> reportByDoctor(String doctorid, Date date1) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM prescriptionmaster where doctorid ='"+doctorid+"' and consultingdate ='"+date1+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	            while(rs.next()) {
	            	String pid = rs.getString("PATIENTID");
	            	PatientPojo patt = new PatientPojo();
	            	
	            	patt = patdao.patientSearchById(pid);
	            	
	            	ReportPojo dt = new ReportPojo();
	          
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dt.setPrescriptionDate(rs.getDate("CONSULTINGDATE"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
	                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
	                dt.setPatientGender(patt.getPatientGender());
	                dt.setPatientAge(patt.getPatientDOB());
	                
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

	public List<ReportPojo> reportByDoctor1(String doctorid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM prescriptionmaster where doctorid ='"+doctorid+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	            while(rs.next()) {
	            	String pid = rs.getString("PATIENTID");
	            	PatientPojo patt = new PatientPojo();
	            	
	            	patt = patdao.patientSearchById(pid);
	            	
	            	ReportPojo dt = new ReportPojo();
	          
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dt.setPrescriptionDate(rs.getDate("CONSULTINGDATE"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
	                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
	                dt.setPatientGender(patt.getPatientGender());
	                dt.setPatientAge(patt.getPatientDOB());
	                
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

	public List<ReportPojo> reportByAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM prescriptionmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	            while(rs.next()) {
	            	String pid = rs.getString("PATIENTID");
	            	PatientPojo patt = new PatientPojo();
	            	patt = patdao.patientSearchById(pid);
	            	
	            	DoctorPojo doc = new DoctorPojo();
	            	doc = docdao.doctorSearchById(rs.getString("DOCTORID"));
	            	
	            	ReportPojo dt = new ReportPojo();
	          
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dt.setPrescriptionDate(rs.getDate("CONSULTINGDATE"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setDoctorName(doc.getDoctorName());
	                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
	                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
	                dt.setPatientGender(patt.getPatientGender());
	                dt.setPatientAge(patt.getPatientDOB());
	                
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

	public List<ReportPojo> reportByDoctor(String doctorid, Date date1, Date date2) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM prescriptionmaster where doctorid ='"+doctorid+"' and consultingdate between '"+date1+"' and '"+date2+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	            while(rs.next()) {
	            	String pid = rs.getString("PATIENTID");
	            	PatientPojo patt = new PatientPojo();
	            	
	            	patt = patdao.patientSearchById(pid);
	            	
	            	ReportPojo dt = new ReportPojo();
	          
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dt.setPrescriptionDate(rs.getDate("CONSULTINGDATE"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
	                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
	                dt.setPatientGender(patt.getPatientGender());
	                dt.setPatientAge(patt.getPatientDOB());
	                
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

	public List<ReportPojo> reportByDisease(int diseasename) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM patientdisease where diseaseid = "+diseasename+" and status = 'Active'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	        	while(rs.next()) {
	            	
		            	String pid = rs.getString("PATIENTID");
		            	PatientPojo patt = new PatientPojo();
		            	patt = patdao.patientSearchById(pid);
		            	
		            	PrescriptionMasterPojo pres = new PrescriptionMasterPojo();
		            	pres = presdao.prescriptionMasterSearchByPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		            	
		            	DoctorPojo doc = new DoctorPojo();
		            	doc = docdao.doctorSearchById(pres.getDoctorId());
		            	
		            	ReportPojo dt = new ReportPojo();
		          
		                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		                dt.setPrescriptionDate(pres.getConsultingDate());
		                dt.setPatientId(rs.getString("PATIENTID"));
		                dt.setDoctorName(doc.getDoctorName());
		                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
		                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
		                dt.setPatientGender(patt.getPatientGender());
		                dt.setPatientAge(patt.getPatientDOB());
		                
		                list.add(dt);
	            	
	            }
	            return list;
	            
	            }
	    });
	}

	public List<ReportPojo> reportByDrug(int drugname) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM prescriptiondrug where drugid = "+drugname;
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	        	while(rs.next()) {
	            	
		            	String pid = rs.getString("PATIENTID");
		            	PatientPojo patt = new PatientPojo();
		            	patt = patdao.patientSearchById(pid);
		            	
		            	PrescriptionMasterPojo pres = new PrescriptionMasterPojo();
		            	pres = presdao.prescriptionMasterSearchByPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		            	
		            	DoctorPojo doc = new DoctorPojo();
		            	doc = docdao.doctorSearchById(pres.getDoctorId());
		            	
		            	ReportPojo dt = new ReportPojo();
		          
		                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		                dt.setPrescriptionDate(pres.getConsultingDate());
		                dt.setPatientId(rs.getString("PATIENTID"));
		                dt.setDoctorName(doc.getDoctorName());
		                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
		                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
		                dt.setPatientGender(patt.getPatientGender());
		                dt.setPatientAge(patt.getPatientDOB());
		                list.add(dt);
	            	
	            }
	            return list;
	            
	            }
	    });
	}

	public List<ReportPojo> reportByDoctorIdAndDiseases(String doctorid, int diseasename, Date date1, Date date2) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM patientdisease where diseaseid = "+diseasename+" and status = 'Active'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list1 = new  ArrayList<ReportPojo>();
	    		list1 = reportByDate(date1,date2);
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	        	System.out.println("list size "+list1.size());
	        	while(rs.next()) {
	            	int kek = 1;
	            	for(int i=0;i<list1.size();i++)
	            	{
	            		String presno = list1.get(i).getPrescriptionNo();
	            		if(presno.equals(rs.getString("PRESCRIPTIONNO")))
	            		{
	            			kek = 2;
	            		}
	            	}
	            	if(kek == 2)
	            	{
		            	String pid = rs.getString("PATIENTID");
		            	PatientPojo patt = new PatientPojo();
		            	patt = patdao.patientSearchById(pid);
		            	PrescriptionMasterPojo pres = new PrescriptionMasterPojo();
		            	pres = presdao.prescriptionMasterSearchByPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		            	
		            	if(doctorid.equals(pres.getDoctorId())) {
		            	DoctorPojo doc = new DoctorPojo();
		            	doc = docdao.doctorSearchById(pres.getDoctorId());
		            	
		            	ReportPojo dt = new ReportPojo();
		          
		                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		                dt.setPrescriptionDate(pres.getConsultingDate());
		                dt.setPatientId(rs.getString("PATIENTID"));
		                dt.setDoctorName(doc.getDoctorName());
		                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
		                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
		                dt.setPatientGender(patt.getPatientGender());
		                dt.setPatientAge(patt.getPatientDOB());
		                
		                list.add(dt);
		            	}
	            	}
	            }
	            return list;
	            
	            }
	    });

	}

	public List<ReportPojo> reportByDoctorIdAndDrug(String doctorid, int drugname, Date date1, Date date2) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM prescriptiondrug where drugid = "+drugname;
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list1 = new  ArrayList<ReportPojo>();
	    		list1 = reportByDate(date1,date2);
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	        	System.out.println("list size "+list1.size());
	        	while(rs.next()) {
	            	int kek = 1;
	            	for(int i=0;i<list1.size();i++)
	            	{
	            		String presno = list1.get(i).getPrescriptionNo();
	            		if(presno.equals(rs.getString("PRESCRIPTIONNO")))
	            		{
	            			kek = 2;
	            		}
	            	}
	            	if(kek == 2)
	            	{
		            	String pid = rs.getString("PATIENTID");
		            	PatientPojo patt = new PatientPojo();
		            	patt = patdao.patientSearchById(pid);
		            	PrescriptionMasterPojo pres = new PrescriptionMasterPojo();
		            	pres = presdao.prescriptionMasterSearchByPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		            	
		            	if(doctorid.equals(pres.getDoctorId())) {
		            	DoctorPojo doc = new DoctorPojo();
		            	doc = docdao.doctorSearchById(pres.getDoctorId());
		            	
		            	ReportPojo dt = new ReportPojo();
		          
		                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		                dt.setPrescriptionDate(pres.getConsultingDate());
		                dt.setPatientId(rs.getString("PATIENTID"));
		                dt.setDoctorName(doc.getDoctorName());
		                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
		                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
		                dt.setPatientGender(patt.getPatientGender());
		                dt.setPatientAge(patt.getPatientDOB());
		                
		                list.add(dt);
		            	}
	            	}
	            }
	            return list;
	            
	            }
	    });	
	}


	public List<ReportPojo> reportByDoctorIdAndDiseases(String doctorid, int diseasename)  {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM patientdisease where diseaseid = "+diseasename+" and status = 'Active'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	        	System.out.println("list size "+list.size());
	        	System.out.println("123");
	        	while(rs.next()) {
	            	
		            	String pid = rs.getString("PATIENTID");
		            	PatientPojo patt = new PatientPojo();
		            	patt = patdao.patientSearchById(pid);
		            	
		            	PrescriptionMasterPojo pres = new PrescriptionMasterPojo();
		            	pres = presdao.prescriptionMasterSearchByPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		            	System.out.println(doctorid);
		            	System.out.println(pres.getDoctorId());
		            	if(doctorid.equals(pres.getDoctorId())) {
		            		System.out.println("if in");
		            	DoctorPojo doc = new DoctorPojo();
		            	doc = docdao.doctorSearchById(pres.getDoctorId());
		            	
		            	ReportPojo dt = new ReportPojo();
		          
		                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		                dt.setPrescriptionDate(pres.getConsultingDate());
		                dt.setPatientId(rs.getString("PATIENTID"));
		                dt.setDoctorName(doc.getDoctorName());
		                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
		                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
		                dt.setPatientGender(patt.getPatientGender());
		                dt.setPatientAge(patt.getPatientDOB());
		                
		                list.add(dt);
		            	}
	            }
	            return list;
	            
	            }
	    });
	}

	public List<ReportPojo> reportByDoctorIdAndDrug(String doctorid, int drugname) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM prescriptiondrug where drugid = "+drugname;
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReportPojo>>() {
	        public List<ReportPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReportPojo> list = new  ArrayList<ReportPojo>();
	        	while(rs.next()) {
	            	
		            	String pid = rs.getString("PATIENTID");
		            	PatientPojo patt = new PatientPojo();
		            	patt = patdao.patientSearchById(pid);
		            	
		            	PrescriptionMasterPojo pres = new PrescriptionMasterPojo();
		            	pres = presdao.prescriptionMasterSearchByPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		            	if(doctorid.equals(pres.getDoctorId())) {
		            	DoctorPojo doc = new DoctorPojo();
		            	doc = docdao.doctorSearchById(pres.getDoctorId());
		            	
		            	ReportPojo dt = new ReportPojo();
		          
		                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		                dt.setPrescriptionDate(pres.getConsultingDate());
		                dt.setPatientId(rs.getString("PATIENTID"));
		                dt.setDoctorName(doc.getDoctorName());
		                //dt.setPatientName(patt.getPatientFirstName()+""+patt.getPatientMiddleName()+""+patt.getPatientLastName()+", "+patt.getPatientMobile1());
		                dt.setPatientName(patt.getPatientFirstName()+" "+patt.getPatientLastName());
		                dt.setPatientGender(patt.getPatientGender());
		                dt.setPatientAge(patt.getPatientDOB());
		                list.add(dt);
		            	}
	            }
	            return list;
	            
	            }
	    });
	}

	
}