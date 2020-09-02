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

import com.ast.HealthCare.Pojo.SpecialisationPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class SpecialisationDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;

	//protected JdbcTemplate jdbcTemplate;

	JpaConfiguration jpa = new JpaConfiguration();
	SpecialisationDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("SpecialisationDao constructor jdbc "+this.jdbcTemplate);
	}
	

	public List<SpecialisationPojo> specialisationAll() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM specialisationmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<SpecialisationPojo>>() {
	        public List<SpecialisationPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<SpecialisationPojo> list = new  ArrayList<SpecialisationPojo>();
	            while(rs.next()) {
	            	System.out.println("oii1");
	                SpecialisationPojo sp = new SpecialisationPojo();
	              
	                ///CREATE TABLE specialisationmaster(SPECIALISATIONID SERIAL,
	            	//SPECIALISATIONNAME TEXT,SPECIALISATIONCODE TEXT,PRIMARY KEY(SPECIALISATIONID));
	            	sp.setSpecialisationId(rs.getInt("SPECIALISATIONID"));
	            	sp.setSpecialisationName(rs.getString("SPECIALISATIONNAME"));
	            	sp.setSpecialisationCode(rs.getString("SPECIALISATIONCODE"));
	                list.add(sp);
	            }
	            return list;
	            
	            }
	    });

		
		
	}


	public boolean addSpecialisation(final SpecialisationPojo sppojo) {
		// TODO Auto-generated method stub
		//specialisationmaster(SPECIALISATIONID SERIAL,SPECIALISATIONNAME TEXT,SPECIALISATIONCODE TEXT
		String sql1 = "INSERT INTO specialisationmaster (SPECIALISATIONNAME, SPECIALISATIONCODE) values(?,?)";
	
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, sppojo.getSpecialisationName());
				ps.setString(2, sppojo.getSpecialisationCode());
				
			    return ps.execute();
			    
			}
		});
	}


	public List<SpecialisationPojo> specialisationSearchByAll(String all) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM specialisationmaster WHERE SPECIALISATIONID LIKE ?  or SPECIALISATIONNAME LIKE ? or SPECIALISATIONCODE LIKE ?";
		return jdbcTemplate.query(sql,  new Object[] { all,all,all }, new ResultSetExtractor<List<SpecialisationPojo>>() {
	        public List<SpecialisationPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<SpecialisationPojo> list = new  ArrayList<SpecialisationPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	        		
	            	System.out.println("oii1");
	            	SpecialisationPojo dt = new SpecialisationPojo();
	                dt.setSpecialisationId(rs.getInt("SPECIALISATIONID"));
	            	dt.setSpecialisationName(rs.getString("SPECIALISATIONNAME"));
	            	dt.setSpecialisationCode(rs.getString("SPECIALISATIONCODE"));
	                
	                list.add(dt);
	                
	            }
	            return list;
	            
	            }
	    });
	}


	public int specDelete(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM specialisationmaster WHERE SPECIALISATIONID = ?", new Object[] { pid });
	}

}
