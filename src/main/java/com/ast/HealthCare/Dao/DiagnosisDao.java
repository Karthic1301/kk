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

import com.ast.HealthCare.Pojo.DiagnosisPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class DiagnosisDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	DiagnosisDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("DiagnosisDao constructor jdbc "+this.jdbcTemplate);
	}

	public boolean addDiag(final DiagnosisPojo diag) {
		// TODO Auto-generated method stub
		//CREATE TABLE diagnosismaster(DIAGNOSISID SERIAL,
		//DIAGNOSISNAME TEXT,DIAGNOSISCODE TEXT,PRIMARY KEY(DIAGNOSISID));
		String sql1="INSERT INTO diagnosismaster (DIAGNOSISNAME, DIAGNOSISCODE) values(?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, diag.getDiagnosisName());
				ps.setString(2, diag.getDiagnosisCode());
				
			    return ps.execute();
			    
			}
		});
		
	}

	public List<DiagnosisPojo> diagnosisAll() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM diagnosismaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<DiagnosisPojo>>() {
	        public List<DiagnosisPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<DiagnosisPojo> list = new  ArrayList<DiagnosisPojo>();
	            while(rs.next()) {
	            	DiagnosisPojo dt = new DiagnosisPojo();
	          
	                dt.setDiagnosisId(rs.getInt("DIAGNOSISID"));
	                dt.setDiagnosisCode(rs.getString("DIAGNOSISCODE"));
	                dt.setDiagnosisName(rs.getString("DIAGNOSISNAME"));
	                
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });

	}

	public List<DiagnosisPojo> diagnosisSearchByAll(String all) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM diagnosismaster WHERE DIAGNOSISNAME LIKE ? or DIAGNOSISCODE LIKE ?";
		return jdbcTemplate.query(sql,  new Object[] {all,all }, new ResultSetExtractor<List<DiagnosisPojo>>() {
	        public List<DiagnosisPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	//
	        	System.out.println("oii");
	        	List<DiagnosisPojo> list = new  ArrayList<DiagnosisPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	        		
	            	System.out.println("oii1");
	            	DiagnosisPojo dt = new DiagnosisPojo();
	            	
	            	dt.setDiagnosisId(rs.getInt("DIAGNOSISID"));
	                dt.setDiagnosisCode(rs.getString("DIAGNOSISCODE"));
	                dt.setDiagnosisName(rs.getString("DIAGNOSISNAME"));
	                
	                list.add(dt);
	                
	            }
	            return list;
	            
	            }
	    });
	}

	public boolean diagnosisUpdate(final DiagnosisPojo dt) {
		// TODO Auto-generated method stub

		String query="UPDATE diagnosismaster set DIAGNOSISNAME = ?, DIAGNOSISCODE = ? where DIAGNOSISID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getDiagnosisName());
			ps.setString(2, dt.getDiagnosisCode());
			ps.setInt(3, dt.getDiagnosisId());
		    return ps.execute();
		}
		});

	}

	public int diagnosisDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM diagnosismaster WHERE DIAGNOSISID = ?", new Object[] { pid });
	}

}
