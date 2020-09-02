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

import com.ast.HealthCare.Pojo.FindingsPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class FindingsDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	FindingsDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("FindingsDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addFindings(final FindingsPojo findings) {
		// TODO Auto-generated method stub
		String sql1="INSERT INTO findingsmaster (FINDINGSNAME, FINDINGSCODE) values(?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, findings.getFindingsName());
				ps.setString(2, findings.getFindingsCode());
				
			    return ps.execute();
			    
			}
		});
	}

	public List<FindingsPojo> findingsAll() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM findingsmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<FindingsPojo>>() {
	        public List<FindingsPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<FindingsPojo> list = new  ArrayList<FindingsPojo>();
	            while(rs.next()) {
	            	FindingsPojo dt = new FindingsPojo();
	          
	                dt.setFindingsId(rs.getInt("FINDINGSID"));
	                dt.setFindingsCode(rs.getString("FINDINGSCODE"));
	                dt.setFindingsName(rs.getString("FINDINGSNAME"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });

	}

	public int findingsDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM findingsmaster WHERE FINDINGSID = ?", new Object[] { pid });
	}

	public boolean findingsUpdate(final FindingsPojo dt) {
		// TODO Auto-generated method stub
		System.out.println("vic "+dt);
		
		String query="UPDATE findingsmaster set FINDINGSNAME = ?, FINDINGSCODE = ? where FINDINGSID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getFindingsName());
			ps.setString(2, dt.getFindingsCode());
			ps.setInt(3, dt.getFindingsId());
		    return ps.execute();
		}
		});
	}

}
