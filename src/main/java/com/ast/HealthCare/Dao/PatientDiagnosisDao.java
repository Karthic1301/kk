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

import com.ast.HealthCare.Pojo.PatientDiagnosisPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PatientDiagnosisDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;

	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	PatientDiagnosisDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
		 System.out.println("PatientDiagnosisDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addDiag(List<PatientDiagnosisPojo> diag,String prescriptionno) {
		// TODO Auto-generated method stub
		//patientdiagnosis(PRESCRIPTIONID INTEGER,PATIENTID INTEGER,DIAGNOSISID INTEGER,DIAGNOSISDESCRIPTION TEXT);
		diagnosisDeleteByPrescription(prescriptionno);
		String sql1="INSERT INTO patientdiagnosis (PRESCRIPTIONNO, PATIENTID, DIAGNOSISDESCRIPTION, DIAGNOSISID) values(?,?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {
				for(int i=0;i<diag.size();i++)
				{
				ps.setString(1, diag.get(i).getPrescriptionNo());
				ps.setString(2, diag.get(i).getPatientId());
				ps.setString(3, diag.get(i).getDiagnosisDescription());
			    String sql1 ="select * from diagnosismaster where DIAGNOSISNAME= ?";
	              Integer diagid = jdbcTemplate.query(sql1,new Object[] {diag.get(i).getDiagnosisName()} ,new ResultSetExtractor<Integer>(){
	                	public Integer extractData(ResultSet rs) throws SQLException
	                	{
	                		if(rs.next())
	                		{
	                			return rs.getInt("DIAGNOSISID");
	                		}
	                		else
	                		{
	                			System.out.println("diagnosis id not found");
	                		 return null ;
	                		}
	                	}
	                });
	               if(diagid != null)
	               {
	            	   System.out.println("diagnosis id  found" + diagid);
	            	   ps.setInt(4,diagid);
	               }
	            
				// ps.setInt(4, diag.get(i).getDiagnosisId());
			    ps.execute();
				}
				return true;
			}
		});
	}
	
	public List<PatientDiagnosisPojo> diagnosisAll() {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM patientdiagnosis";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientDiagnosisPojo>>() {
	        public List<PatientDiagnosisPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientDiagnosisPojo> list = new  ArrayList<PatientDiagnosisPojo>();
	            while(rs.next()) {
	            	PatientDiagnosisPojo dt = new PatientDiagnosisPojo();
	          
	                dt.setDiagnosisId(rs.getInt("DIAGNOSISID"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                String sql1 ="select * from diagnosismaster where DIAGNOSISID= ?";
	               String diagname = jdbcTemplate.query(sql1,new Object[] {rs.getString("DIAGNOSISID")} ,new ResultSetExtractor<String>(){
	                	public String extractData(ResultSet rs) throws SQLException
	                	{
	                		if(rs.next())
	                		{
	                			return rs.getString("DIAGNOSISNAME");
	                		}
	                		else
	                		{
	                			System.out.println("diagnosis name not found");
	                		 return null ;
	                		}
	                	}
	                });
	               if(diagname != null)
	               {
	            	   System.out.println("diagnosis name  found" + diagname);
	            	   dt.setDiagnosisName(diagname);
	               }
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dt.setDiagnosisDescription(rs.getString("DIAGNOSISDESCRIPTION"));
	                list.add(dt);
	            }
	            return list;
	            }
	    });
	}
	
	public int diagnosisDelete(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientdiagnosis WHERE PATIENTID = ?", new Object[] { pid });
	}
	public int diagnosisDeleteByPrescription(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientdiagnosis WHERE PRESCRIPTIONNO = ?", new Object[] { pid });
	}

	public List<PatientDiagnosisPojo> patientDiagnosisSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM patientdiagnosis WHERE PATIENTID = ? ";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<PatientDiagnosisPojo>>() {
	        public List<PatientDiagnosisPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PatientDiagnosisPojo> list = new  ArrayList<PatientDiagnosisPojo>();
	        	PatientDiagnosisPojo dt = new PatientDiagnosisPojo();
	  	        
	        	while(rs.next()) {
	                dt.setDiagnosisId(rs.getInt("DIAGNOSISID"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                String sql1 ="select * from diagnosismaster where DIAGNOSISID= ?";
	                String diagname = jdbcTemplate.query(sql1,new Object[] {rs.getString("DIAGNOSISID")} ,new ResultSetExtractor<String>(){
	                	public String extractData(ResultSet rs) throws SQLException
	                	{
	                		if(rs.next())
	                		{
	                			return rs.getString("DIAGNOSISNAME");
	                		}
	                		else
	                		{
	                			System.out.println("diagnosis name not found" );
	                		 return null ;
	                		}
	                	}
	                });
	               if(diagname != null)
	               {
	            	   System.out.println("diagnosis name  found" + diagname);
	            	   dt.setDiagnosisName(diagname);
	               }
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dt.setDiagnosisDescription(rs.getString("DIAGNOSISDESCRIPTION"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

	public PatientDiagnosisPojo patientDiagnosisSearchByPrescriptionId(int pid) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM patientdiagnosis WHERE PRESCRIPTIONNO = ? ";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<PatientDiagnosisPojo>() {
	        public PatientDiagnosisPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	PatientDiagnosisPojo dt = new PatientDiagnosisPojo();
	  	        
	        	while(rs.next()) {
	                dt.setDiagnosisId(rs.getInt("DIAGNOSISID"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                String sql1 ="select * from diagnosismaster where DIAGNOSISID= ?";
	                String diagname = jdbcTemplate.query(sql1,new Object[] {rs.getString("DIAGNOSISID")} ,new ResultSetExtractor<String>(){
	                	public String extractData(ResultSet rs) throws SQLException
	                	{
	                		if(rs.next())
	                		{
	                			return rs.getString("DIAGNOSISNAME");
	                		}
	                		else
	                		{
	                			System.out.println("diagnosis name not found" );
	                		 return null ;
	                		}
	                	}
	                });
	               if(diagname != null)
	               {
	            	   System.out.println("diagnosis name  found" + diagname);
	            	   dt.setDiagnosisName(diagname);
	               }
	                dt.setDiagnosisDescription(rs.getString("DIAGNOSISDESCRIPTION"));
	            }
	            return dt;
	            
	            }
	    });
	}

	public List<PatientDiagnosisPojo> patientDiagnosisSearchByPrescriptionNo1(String pid) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM patientdiagnosis WHERE PRESCRIPTIONNO = ? ";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<PatientDiagnosisPojo>>() {
	        public List<PatientDiagnosisPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PatientDiagnosisPojo> lis = new ArrayList<PatientDiagnosisPojo>();
	        	
	  	        
	        	while(rs.next()) {
	        		PatientDiagnosisPojo dt = new PatientDiagnosisPojo();
	                dt.setDiagnosisId(rs.getInt("DIAGNOSISID"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                Integer param = rs.getInt("DIAGNOSISID");
	                String sql1 = "select * from diagnosismaster where DIAGNOSISID = ?";
	                String diagname = jdbcTemplate.query(sql1,new Object[] {param} ,new ResultSetExtractor<String>(){
	                	public String extractData(ResultSet rs) throws SQLException
	                	{
	                		if(rs.next())
	                		{
	                			return rs.getString("DIAGNOSISNAME");
	                		}
	                		else
	                		{
	                			System.out.println("diagnosis name not found" );
	                		 return null ;
	                		}
	                	}
	                });
	               if(diagname != null)
	               {
	            	   System.out.println("diagnosis name  found" + diagname);
	            	   dt.setDiagnosisName(diagname);
	               }
	                
	                dt.setDiagnosisDescription(rs.getString("DIAGNOSISDESCRIPTION"));
	                lis.add(dt);
	        	}
	            return lis;
	            
	            }
	    });
	}
	public boolean patientDiagnosisUpdate1(List<PatientDiagnosisPojo> comppojo) {
		String query="UPDATE patientdiagnosis set  PATIENTID = ? , DIAGNOSISDESCRIPTION = ?, DIAGNOSISID = ?  WHERE PRESCRIPTIONNO = ?";
			    
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
			for(int i=0;i<comppojo.size();i++)
			{
			ps.setString(1, comppojo.get(i).getPatientId());
			ps.setString(2, comppojo.get(i).getDiagnosisDescription());
			ps.setInt(3, comppojo.get(i).getDiagnosisId());
			ps.setString(4, comppojo.get(i).getPrescriptionNo());
			}
			return ps.execute();
		}
		});
		}
	public int diagnosisDeleteByDiagnosisId(int did) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientdiagnosis WHERE DIAGNOSISID = ?", new Object[] { did });
	}
	
}
