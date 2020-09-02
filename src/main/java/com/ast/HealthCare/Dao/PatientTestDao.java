package com.ast.HealthCare.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.DoctorTestMasterPojo;
import com.ast.HealthCare.Pojo.PatientTestPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PatientTestDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	@Autowired()
	DoctorTestMasterDao dtmDao;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	PatientTestDao()
	{
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("PatientTestDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public Boolean addPatientTest(final List<PatientTestPojo> dis,String prescriptionNo)
	{
		// TODO Auto-generated method stub

		patientTestDeleteByPrescriptionNo(prescriptionNo);

		String sql1="INSERT INTO PatientTest (PATIENTID,DOCTORID,TESTMASTERID,STATUS,PRESCRIPTIONNO) values(?,?,?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>()
		{  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException { 
				for(int i=0;i<dis.size();i++)
				{
				ps.setString(1, dis.get(i).getPatientId());
				ps.setString(2, dis.get(i).getDoctorId());
				ps.setInt(3, dis.get(i).getTestMasterId());
				ps.setBoolean(4, dis.get(i).getStatus());
				ps.setString(5, prescriptionNo);				
				ps.execute();
				}
				return true;
			}
		});
	}

	public List<PatientTestPojo> patientTestAll() 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientTest";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientTestPojo>>()
		{
	        public List<PatientTestPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientTestPojo> list = new  ArrayList<PatientTestPojo>();
	            while(rs.next()) {
	            	PatientTestPojo dt = new PatientTestPojo();
	                dt.setPatientTestId(rs.getInt("PATIENTTESTID"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setDoctorId(rs.getString("DOCTORID"));
	                dt.setTestMasterId(rs.getInt("TESTMASTERID"));
	                dt.setStatus(rs.getBoolean("STATUS"));
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	
	public List<PatientTestPojo> patientTestByPrescriptionNo(String pno) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientTest where PRESCRIPTIONNO = '"+pno+"' AND STATUS = 1 AND PRESCRIPTIONNO IN (SELECT PRESCRIPTIONNO FROM PRESCRIPTIONMASTER WHERE BILLID =0)";
		
		System.out.println(sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientTestPojo>>()
		{
	        public List<PatientTestPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientTestPojo> list = new  ArrayList<PatientTestPojo>();
	        	DoctorTestMasterPojo dpojo = new DoctorTestMasterPojo();
	            while(rs.next()) {
	            	
	            	PatientTestPojo dt = new PatientTestPojo();
	                dt.setPatientTestId(rs.getInt("PATIENTTESTID"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setDoctorId(rs.getString("DOCTORID"));
	                dt.setTestMasterId(rs.getInt("TESTMASTERID"));
	                dt.setStatus(rs.getBoolean("STATUS"));
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dpojo = dtmDao.getAmountByDoctorIdAndTestId(rs.getString("DOCTORID"),rs.getInt("TESTMASTERID"));
	                dt.setTestMasterName(dpojo.getTestMasterName());
	                dt.setAmount(dpojo.getAmount());
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	public PatientTestPojo patientTestById(int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientTest WHERE PATIENTTESTID"+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<PatientTestPojo>()
		{
	        public PatientTestPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	PatientTestPojo dt = new PatientTestPojo();
	            while(rs.next()) {
	            	dt.setPatientTestId(rs.getInt("PATIENTTESTID"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setDoctorId(rs.getString("DOCTORID"));
	                dt.setTestMasterId(rs.getInt("TESTMASTERID"));
	                dt.setStatus(rs.getBoolean("STATUS"));
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	            }
	            return dt;
	            
	            }
	    });
	
	}

	public int patientTestDelete(int pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM PatientTest WHERE DOCTORTESTID = ? ", new Object[] { pid });
	}
	
	public int patientTestDeleteByPrescriptionNo(String pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM PatientTest WHERE PRESCRIPTIONNO = ? ", new Object[] { pid });
	}

	public boolean patientTestUpdate(final PatientTestPojo dt) 
	{
		// TODO Auto-generated method stub	
		String query="UPDATE PatientTest set PATIENTID =?,DOCTORID =?,TESTMASTERID =?,STATUS =?,PRESCRIPTIONNO=? where PATIENTTESTID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>()
		{  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getPatientId());
			ps.setString(2, dt.getDoctorId());
			ps.setInt(3, dt.getTestMasterId());
			ps.setBoolean(4, dt.getStatus());
			ps.setString(5, dt.getPrescriptionNo());
			ps.setInt(6, dt.getPatientTestId());
		    return ps.execute();
		}
		});
	}

}
