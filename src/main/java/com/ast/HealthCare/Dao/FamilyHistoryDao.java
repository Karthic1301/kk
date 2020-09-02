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

import com.ast.HealthCare.Pojo.FamilyHistoryPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class FamilyHistoryDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;

	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	FamilyHistoryDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("FamilyHistoryDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addFamilyHistory(final List<FamilyHistoryPojo> family) {
		// TODO Auto-generated method stub
		//CREATE TABLE  familyhistory(FHSERIALID SERIAL,PATIENTID TEXT,
		//RELATIONNAME TEXT,RELATIONSHIP TEXT,AGE TEXT,PRIMARY KEY(FHSERIALID));
		
		String sql1="INSERT INTO familyhistory(PATIENTID, RELATIONNAME, RELATIONSHIP, AGE) values(?,?,?,?)";
		
		jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				for(int i=0;i<family.size();i++)
				{
				ps.setString(1, family.get(i).getPatientId());
				ps.setString(2, family.get(i).getRelationName());
				ps.setString(3, family.get(i).getRelationship());
				ps.setString(4, family.get(i).getIllness());
				ps.execute();
				}
			    return true;
			}
		});
			return true;
	}
	public List<FamilyHistoryPojo> fhAll() {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM familyhistory";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<FamilyHistoryPojo>>() {
	        public List<FamilyHistoryPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<FamilyHistoryPojo> list = new  ArrayList<FamilyHistoryPojo>();
	            while(rs.next()) {
	            	FamilyHistoryPojo dt = new FamilyHistoryPojo();

	        		//CREATE TABLE  familyhistory(FHSERIALID SERIAL,PATIENTID TEXT,
	        		//RELATIONNAME TEXT,RELATIONSHIP TEXT,AGE TEXT,PRIMARY KEY(FHSERIALID));
	                dt.setFhSerialId(rs.getInt("FHSERIALID"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setRelationName(rs.getString("RELATIONNAME"));
	                dt.setRelationship(rs.getString("RELATIONSHIP"));
	                dt.setIllness(rs.getString("AGE"));
	                
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });

	}
	public int fhDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM familyhistory WHERE FHSERIALID = ?", new Object[] { pid });
	}

	public boolean fhUpdate(final FamilyHistoryPojo dt) {
		// TODO Auto-generated method stub
		System.out.println("vic "+dt);
		
		String query="UPDATE familyhistory set RELATIONNAME = ?, RELATIONSHIP = ?, AGE = ? where PATIENTID = ? and FHSERIALID = ?";


		//CREATE TABLE  familyhistory(FHSERIALID SERIAL,PATIENTID TEXT,
		//RELATIONNAME TEXT,RELATIONSHIP TEXT,AGE TEXT,PRIMARY KEY(FHSERIALID));

		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getRelationName());
			ps.setString(2, dt.getRelationship());
			ps.setString(3, dt.getIllness());
			ps.setString(4, dt.getPatientId());
			ps.setInt(5, dt.getFhSerialId());
		    return ps.execute();
		}
		});
	}

	public List<FamilyHistoryPojo> fhSearchByPatientId(String pid) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM familyhistory WHERE PATIENTID = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<FamilyHistoryPojo>>() {
	        public List<FamilyHistoryPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("family history search by patient id");
	        	List<FamilyHistoryPojo> list = new ArrayList<FamilyHistoryPojo>();
	        	
            	while(rs.next()) {
            		FamilyHistoryPojo dt = new FamilyHistoryPojo();
            		dt.setFhSerialId(rs.getInt("FHSERIALID"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setRelationName(rs.getString("RELATIONNAME"));
	                dt.setRelationship(rs.getString("RELATIONSHIP"));
	                dt.setIllness(rs.getString("AGE"));
	                System.out.println(dt);
	                list.add(dt);
	            }
            	System.out.println(list);
	            return list;
	            }
	    });
	}
	
	
}
