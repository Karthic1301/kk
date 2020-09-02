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

import com.ast.HealthCare.Pojo.SurgicalHistoryPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class SurgicalHistoryDao {


	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	SurgicalHistoryDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("SurgicalHistoryDao jdbc "+this.jdbcTemplate);
	}
	
	public boolean addSurgicalHistory(List<SurgicalHistoryPojo> imp) {
		// TODO Auto-generated method stub
		System.out.println("dei nayeeee");
		//CREATE TABLE  surgicalhistory(SURGERYID INTEGER,PATIENTID TEXT,SURGERYNAME TEXT,SURGERYDATE DATE,SURGERYDESCRIPTION TEXT);
		String sql1="INSERT INTO surgicalhistory (PATIENTID, SURGERYNAME, SURGERYDATE, SURGERYDESCRIPTION) values(?,?,?,?)";
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {
				for(int i=0;i<imp.size();i++)
				{
				ps.setString(1, imp.get(i).getPatientId());
				ps.setString(2, imp.get(i).getSurgeryName());
				ps.setDate(3, imp.get(i).getSurgeryDate());
				ps.setString(4, imp.get(i).getSurgeryDescription());
			    ps.execute();
				}
				return true;
			}
		});
	}
	public List<SurgicalHistoryPojo> surgicalHistoryAll() {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM surgicalhistory";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<SurgicalHistoryPojo>>() {
	        public List<SurgicalHistoryPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<SurgicalHistoryPojo> list = new  ArrayList<SurgicalHistoryPojo>();
	            while(rs.next()) {
	            	SurgicalHistoryPojo dt = new SurgicalHistoryPojo();
	          //surgicalhistory(SURGERYID INTEGER,PATIENTID TEXT,SURGERYNAME TEXT,SURGERYDATE DATE,SURGERYDESCRIPTION TEXT);
	                dt.setSurgeryId(rs.getInt("SURGERYID"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setSurgeryName(rs.getString("SURGERYNAME"));
	                dt.setSurgeryDate(rs.getDate("SURGERYDATE"));
	                dt.setSurgeryDescription(rs.getString("SURGERYDESCRIPTION"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}
	public boolean surgicalHistoryUpdate(SurgicalHistoryPojo dt) {
		// TODO Auto-generated method stub
		System.out.println("vic "+dt);
		String query="UPDATE surgicalhistory set SURGERYNAME = ?, SURGERYDATE = ?, SURGERYDESCRIPTION = ? where SURGERYID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getSurgeryName());
			ps.setDate(2, dt.getSurgeryDate());
			ps.setString(3, dt.getSurgeryDescription());
			ps.setInt(4, dt.getSurgeryId());
			return ps.execute();
		}
		});

	}
	public int surgicalHistoryDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM surgicalhistory WHERE SURGERYID = ?", new Object[] { pid });
	}

	public List<SurgicalHistoryPojo> SurgicalHistorySearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		String sql= "SELECT * FROM surgicalhistory WHERE PATIENTID = ?";
		return jdbcTemplate.query(sql, new Object[] { pid }, new ResultSetExtractor<List<SurgicalHistoryPojo>>() {
		       public List<SurgicalHistoryPojo> extractData(ResultSet rs) throws SQLException,
		               DataAccessException {
		       	List<SurgicalHistoryPojo> list = new  ArrayList<SurgicalHistoryPojo>();
		           while(rs.next()) {
		           	SurgicalHistoryPojo dt = new SurgicalHistoryPojo();
		         //surgicalhistory(SURGERYID INTEGER,PATIENTID TEXT,SURGERYNAME TEXT,SURGERYDATE DATE,SURGERYDESCRIPTION TEXT);
		               dt.setSurgeryId(rs.getInt("SURGERYID"));
		               dt.setPatientId(rs.getString("PATIENTID"));
		               dt.setSurgeryName(rs.getString("SURGERYNAME"));
		               dt.setSurgeryDate(rs.getDate("SURGERYDATE"));
		               dt.setSurgeryDescription(rs.getString("SURGERYDESCRIPTION"));
		               list.add(dt);
		           }
		           return list;
		           
		           }
		   });	

	}
	
}
