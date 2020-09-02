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

import com.ast.HealthCare.Pojo.PatientFindingsPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PatientFindingsDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	PatientFindingsDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("PatientFindingsDao constructor jdbc "+this.jdbcTemplate);
	}
	public boolean addPatientFindings(List<PatientFindingsPojo> findings,String prescriptionno) {
		// TODO Auto-generated method stub
		//patientfindings(PATIENTID INTEGER,DISEASEID INTEGER,
		//DISEASESTARTDATE DATE,DISEASEENDDATE DATE,STATUS TEXT);
		patientFindingsDeleteByPrescription(prescriptionno);
		String sql1="INSERT INTO patientfindings (PATIENTID, PRESCRIPTIONNO, FINDINGSDESCRIPTION,FINDINGSID) values(?,?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException { 
				for(int i=0;i<findings.size();i++)
				{
				ps.setString(1, findings.get(i).getPatientId());
				ps.setString(2, findings.get(i).getPrescriptionNo());
				ps.setString(3, findings.get(i).getFindingsDescription());
				ps.setInt(4, findings.get(i).getFindingsId());
			    ps.execute();
				}
				return true;
			}
		});
	}
	public List<PatientFindingsPojo> patientFindingsAll() {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM patientfindings";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientFindingsPojo>>() {
	        public List<PatientFindingsPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientFindingsPojo> list = new  ArrayList<PatientFindingsPojo>();
	            while(rs.next()) {
	            	PatientFindingsPojo dt = new PatientFindingsPojo();
	          
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setFindingsId(rs.getInt("FINDINGSID"));
	                String sql1 ="select * from FINDINGSMASTER where FINDINGSID = ?";
	                String fname = jdbcTemplate.query(sql1, new ResultSetExtractor<String>() {
	                	public String extractData(ResultSet rs) throws SQLException
	                	{
	                		if(rs.next())
	                		{
	  
	                		return rs.getString("FINDINGSNAME");
	                		}
	                		else 
	                		{
	                			System.out.println("findings name not found");
	                			return null;
	                		}
	                	}
	                });
	                if(fname != null)
	                {
	                	System.out.println("findings name found: "+ fname);
	                    dt.setFindingsName(fname);
	                }
	                dt.setFindingsDescription(rs.getString("FINDINGSDESCRIPTION"));
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}
	
	public int patientFindingsDelete(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientfindings WHERE PATIENTID = ?", new Object[] { pid });
	}
	public int patientFindingsDeleteByPrescription(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientfindings WHERE PRESCRIPTIONNO = ?", new Object[] { pid });
	}
	public List<PatientFindingsPojo> patientFindingsSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM patientfindings WHERE PATIENTID = ? ";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<PatientFindingsPojo>>() {
	        public List<PatientFindingsPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PatientFindingsPojo> list = new  ArrayList<PatientFindingsPojo>();
	        	
	        	while(rs.next()) {
	        		PatientFindingsPojo dt = new PatientFindingsPojo();
		  	        
	        		dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setFindingsId(rs.getInt("FINDINGSID"));
	                Integer param = rs.getInt("FINDINGSID");
	                String sql1 ="select * from FINDINGSMASTER where FINDINGSID = ?";
	                String fname = jdbcTemplate.query(sql1,new Object[] {param}, new ResultSetExtractor<String>() {
	                	public String extractData(ResultSet rs) throws SQLException
	                	{
	                		if(rs.next())
	                		{
	  
	                		return rs.getString("FINDINGSNAME");
	                		}
	                		else 
	                		{
	                			System.out.println("findings name not found");
	                			return null;
	                		}
	                	}
	                });
	                if(fname != null)
	                {
	                	System.out.println("findings name found: "+ fname);
	                    dt.setFindingsName(fname);
	                }
	                dt.setFindingsDescription(rs.getString("FINDINGSDESCRIPTION"));
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}
	
	public List<PatientFindingsPojo> patientFindingsSearchByPrescriptionNo1(String pid) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM patientfindings WHERE PRESCRIPTIONNO = ? ";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<PatientFindingsPojo>>() {
	        public List<PatientFindingsPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PatientFindingsPojo> list = new  ArrayList<PatientFindingsPojo>();
	        	
	        	while(rs.next()) {
	        		PatientFindingsPojo dt = new PatientFindingsPojo();
		  	        
	        		dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setFindingsId(rs.getInt("FINDINGSID"));
	                Integer param = rs.getInt("FINDINGSID");
	                String sql1 ="select * from FINDINGSMASTER where FINDINGSID = ?";
	                String fname = jdbcTemplate.query(sql1, new Object[] {param},new ResultSetExtractor<String>() {
	                	public String extractData(ResultSet rs) throws SQLException
	                	{
	                		if(rs.next())
	                		{
	  
	                		return rs.getString("FINDINGSNAME");
	                		}
	                		else 
	                		{
	                			System.out.println("findings name not found");
	                			return null;
	                		}
	                	}
	                });
	                if(fname != null)
	                {
	                	System.out.println("findings name found: "+ fname);
	                    dt.setFindingsName(fname);
	                }
	  
	               
	                dt.setFindingsDescription(rs.getString("FINDINGSDESCRIPTION"));
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                
	                list.add(dt);
	            }
	            return list;
	            }
	    });
	}
	//patientfindings(PATIENTID INTEGER,DISEASEID INTEGER,
	//DISEASESTARTDATE DATE,DISEASEENDDATE DATE,STATUS TEXT);

	
	
}
