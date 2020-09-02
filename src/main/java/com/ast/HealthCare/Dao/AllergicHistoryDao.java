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

import com.ast.HealthCare.Pojo.AllergicHistoryPojo;

@Repository
public class AllergicHistoryDao {
	
	 @Autowired()
	 JdbcTemplate jdbcTemplate;

	//CREATE TABLE allergichistory(ALLERGYID SERIAL,
	//PATIENTID TEXT,ALLERGYNAME TEXT,ALLERGYDESCRIPTION TEXT);
	// protected JdbcTemplate jdbcTemplate;
	AllergicHistoryDao(){
		/* this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("AllergicHistoryDao constructer jdbc "+this.jdbcTemplate);
	}
	
	
	public boolean addAllergicHistory(final List<AllergicHistoryPojo> allergic) {
		String sql="INSERT INTO allergichistory (PATIENTID, ALLERGYNAME ,ALLERGYDESCRIPTION) values(?,?,?)";		
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				for(int i=0;i<allergic.size();i++)
				{
				
				ps.setString(1, allergic.get(i).getPatientId());
				ps.setString(2, allergic.get(i).getAllergyName());
				ps.setString(3, allergic.get(i).getAllergyDescription());
				ps.execute();
				
				}
			    return true;
			}
		});
	}


	public List<AllergicHistoryPojo> allergicHistoryAll() {
		String sql = "SELECT * FROM allergichistory";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<AllergicHistoryPojo>>() {
	        public List<AllergicHistoryPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<AllergicHistoryPojo> list = new  ArrayList<AllergicHistoryPojo>();
	            while(rs.next()) {
	            	AllergicHistoryPojo ah = new AllergicHistoryPojo();
	            	ah.setAllergyId(rs.getInt("ALLERGYID"));
	            	ah.setPatientId(rs.getString("PATIENTID"));
	                ah.setAllergyName(rs.getString("ALLERGYNAME"));
	                ah.setAllergyDescription(rs.getString("ALLERGYDESCRIPTION"));
	            	list.add(ah);
	            }
	            return list;
	            }
	    });
	}


	public boolean allergicHistoryUpdate(final AllergicHistoryPojo allergic) {
		
		System.out.println("Update "+allergic);
		
		String query="UPDATE allergichistory set ALLERGYNAME = ?, ALLERGYDESCRIPTION = ? where PATIENTID = ? and ALLERGYID = ?";
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, allergic.getAllergyName());
			ps.setString(2, allergic.getAllergyDescription());
			ps.setString(3, allergic.getPatientId());
			ps.setInt(4, allergic.getAllergyId());
		    return ps.execute();
		}
		});
	}

	public int allergicHistoryDelete(int pid) {
		
		return jdbcTemplate.update("DELETE FROM allergichistory WHERE ALLERGYID = ?", new Object[] { pid });
	}


	public List<AllergicHistoryPojo> allergicHistorySearchByPatientId(String pid) {
		
		String sql = "SELECT * FROM allergichistory WHERE PATIENTID = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<AllergicHistoryPojo>>() {
	        public List<AllergicHistoryPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("allergic history search by patient id");
	        	List<AllergicHistoryPojo> list = new ArrayList<AllergicHistoryPojo>();
	        	
            	while(rs.next()) {
            		AllergicHistoryPojo ah = new AllergicHistoryPojo();
            		ah.setAllergyId(rs.getInt("ALLERGYID"));
	            	ah.setPatientId(rs.getString("PATIENTID"));
	                ah.setAllergyName(rs.getString("ALLERGYNAME"));
	                ah.setAllergyDescription(rs.getString("ALLERGYDESCRIPTION"));
	                list.add(ah);
	            }
	            return list;
	            }
	    });
	}


	public int allergicHistoryDelete1(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM allergichistory WHERE PATIENTID = ?", new Object[] { pid });
	}
	
}
