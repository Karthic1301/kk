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


import com.ast.HealthCare.Pojo.ComplaintsPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class ComplaintsDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	ComplaintsDao(){
		/* this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("ComplaintsDao constructor jdbc "+this.jdbcTemplate);
	}
	
	
	public boolean addcomplaints(final ComplaintsPojo comppojo) {
		// TODO Auto-generated method stub
		
		String sql1="INSERT INTO complaintsmaster (complaintsNAME, complaintsCODE) values(?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, comppojo.getcomplaintsName());
				ps.setString(2, comppojo.getcomplaintsCode());
				
			    return ps.execute();
			}
		});

	}


	public List<ComplaintsPojo> complaintsAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM complaintsmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ComplaintsPojo>>() {
	        public List<ComplaintsPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ComplaintsPojo> list = new  ArrayList<ComplaintsPojo>();
	            while(rs.next()) {
	            	ComplaintsPojo dt = new ComplaintsPojo();
	          
	                dt.setcomplaintsId(rs.getInt("complaintsID"));
	                dt.setcomplaintsCode(rs.getString("complaintsCODE"));
	                dt.setcomplaintsName(rs.getString("complaintsNAME"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}


	public int complaintsDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM complaintsmaster WHERE complaintsID = ?", new Object[] { pid });
	}


	public boolean complaintsUpdate(final ComplaintsPojo dt) {
		// TODO Auto-generated method stub
		System.out.println("vic "+dt);
		
		String query="UPDATE complaintsmaster set complaintsNAME = ?, complaintsCODE = ? where complaintsID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getcomplaintsName());
			ps.setString(2, dt.getcomplaintsCode());
			ps.setInt(3, dt.getcomplaintsId());
		    return ps.execute();
		}
		});
	}

}
