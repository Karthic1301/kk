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

import com.ast.HealthCare.Pojo.InvestigationMasterPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class InvestigationMasterDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	InvestigationMasterDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("InvestigationMasterDao constructor jdbc "+this.jdbcTemplate);
	}
	public boolean addInvestigation(InvestigationMasterPojo imp) {
		// TODO Auto-generated method stub
		//CREATE TABLE  investigationmaster(INVESTIGATIONID SERIAL,INVESTIGATIONNAME TEXT,
		//INVESTIGATIONDURATION TEXT,INVESTIGATIONCODE TEXT,PRIMARY KEY(INVESTIGATIONID));
		String sql1="INSERT INTO investigationmaster (INVESTIGATIONNAME, INVESTIGATIONDURATION, INVESTIGATIONCODE) values(?,?,?)";
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, imp.getInvestigationName());
				ps.setString(2, imp.getInvestigationDuration());
				ps.setString(3, imp.getInvestigationCode());
			    return ps.execute();			    
			}
		});
	}

	public List<InvestigationMasterPojo> investigationAll() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM investigationmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<InvestigationMasterPojo>>() {
	        public List<InvestigationMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<InvestigationMasterPojo> list = new  ArrayList<InvestigationMasterPojo>();
	            while(rs.next()) {
	            	InvestigationMasterPojo dt = new InvestigationMasterPojo();
	          
	                dt.setInvestigationId(rs.getInt("INVESTIGATIONID"));
	                dt.setInvestigationName(rs.getString("INVESTIGATIONNAME"));
	                dt.setInvestigationDuration(rs.getString("INVESTIGATIONDURATION"));
	                dt.setInvestigationCode(rs.getString("INVESTIGATIONCODE"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

	public List<InvestigationMasterPojo> investigationSearchByAll(String all) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM investigationmaster WHERE INVESTIGATIONID LIKE ?  or INVESTIGATIONNAME LIKE ? or INVESTIGATIONCODE LIKE ?";
		return jdbcTemplate.query(sql,  new Object[] { Integer.parseInt(all),all,all }, new ResultSetExtractor<List<InvestigationMasterPojo>>() {
	        public List<InvestigationMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<InvestigationMasterPojo> list = new  ArrayList<InvestigationMasterPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	        		
	            	System.out.println("oii1");
	            	InvestigationMasterPojo dt = new InvestigationMasterPojo();

	                dt.setInvestigationId(rs.getInt("INVESTIGATIONID"));
	                dt.setInvestigationName(rs.getString("INVESTIGATIONNAME"));
	                dt.setInvestigationDuration(rs.getString("INVESTIGATIONDURATION"));
	                dt.setInvestigationCode(rs.getString("INVESTIGATIONCODE"));
	           
	                list.add(dt);
	                
	            }
	            return list;
	            
	            }
	    });
	}

	public boolean investigationUpdate(final InvestigationMasterPojo dt) {
		// TODO Auto-generated method stub
		
		System.out.println("vic "+dt);
		
		String query="UPDATE investigationmaster set INVESTIGATIONNAME = ?, INVESTIGATIONDURATION = ?, INVESTIGATIONCODE = ? where INVESTIGATIONID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getInvestigationName());
			ps.setString(2, dt.getInvestigationDuration());
			ps.setString(3, dt.getInvestigationCode());
			ps.setInt(4, dt.getInvestigationId());
			return ps.execute();
		}
		});

	}

	public int investigationDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM investigationmaster WHERE INVESTIGATIONID = ?", new Object[] { pid });
	}

	//CREATE TABLE  investigationmaster(INVESTIGATIONID SERIAL,INVESTIGATIONNAME TEXT,
	//INVESTIGATIONDURATION TEXT,INVESTIGATIONCODE TEXT,PRIMARY KEY(INVESTIGATIONID));
		
}
