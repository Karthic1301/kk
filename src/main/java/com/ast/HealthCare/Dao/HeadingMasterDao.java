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

import com.ast.HealthCare.Pojo.HeadingMasterPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class HeadingMasterDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	HeadingMasterDao()
	{
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("HeadingMasterDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addHeadingMaster(final HeadingMasterPojo dis)
	{
		// TODO Auto-generated method stub
		String sql1="INSERT INTO HeadingMaster (HEADINGMASTERNAME,ORDERNO) values(?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>()
		{  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, dis.getHeadingMasterName());
				ps.setInt(2, dis.getOrderNo());
				return ps.execute();
			    
			}
		});
	}

	public List<HeadingMasterPojo> headingMasterAll() 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM HeadingMaster ORDER BY ORDERNO ASC";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<HeadingMasterPojo>>()
		{
	        public List<HeadingMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<HeadingMasterPojo> list = new  ArrayList<HeadingMasterPojo>();
	            while(rs.next()) {
	            	HeadingMasterPojo dt = new HeadingMasterPojo();
	            	dt.setHeadingMasterId(rs.getInt("HEADINGMASTERID"));
	            	dt.setHeadingMasterName(rs.getString("HEADINGMASTERNAME"));
	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	public List<HeadingMasterPojo> headingMasterById(int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM HeadingMaster WHERE HEADINGMASTERID = "+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<HeadingMasterPojo>>()
		{
	        public List<HeadingMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<HeadingMasterPojo> list = new  ArrayList<HeadingMasterPojo>();
	            while(rs.next()) {
	            	HeadingMasterPojo dt = new HeadingMasterPojo();
	            	dt.setHeadingMasterId(rs.getInt("HEADINGMASTERID"));
	            	dt.setHeadingMasterName(rs.getString("HEADINGMASTERNAME"));
	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	               
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}

	public int headingMasterDelete(int pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM HeadingMaster WHERE HEADDINGMASTERID = ? ", new Object[] { pid });
	}

	
	
	public boolean headingMasterUpdate(final HeadingMasterPojo dt) 
	{
		// TODO Auto-generated method stub	
		String query="UPDATE HeadingMaster set HEADINGMASTERNAME =?,ORDERNO=? where HEADINGMASTERID=?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>()
		{  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getHeadingMasterName());
			ps.setInt(2, dt.getOrderNo());
			ps.setInt(3, dt.getHeadingMasterId());
			
		    return ps.execute();
		}
		});
	}
	
}
