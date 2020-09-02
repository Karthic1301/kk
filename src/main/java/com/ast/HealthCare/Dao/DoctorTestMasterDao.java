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

import com.ast.HealthCare.Pojo.DoctorTestMasterPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class DoctorTestMasterDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	DoctorTestMasterDao()
	{
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("DoctorTestMasterDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addDoctorTestMaster(final DoctorTestMasterPojo dis)
	{
		// TODO Auto-generated method stub
		String sql1="INSERT INTO DoctorTestMaster (DOCTORID,TESTMASTERID,TESTMASTERNAME,AMOUNT) values(?,?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>()
		{  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, dis.getDoctorId());
				ps.setInt(2, dis.getTestMasterId());
				ps.setString(3, dis.getTestMasterName());
				ps.setDouble(4, dis.getAmount());
				
				return ps.execute();
			    
			}
		});
	}

	public List<DoctorTestMasterPojo> doctorTestMasterAll() 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM DoctorTestMaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<DoctorTestMasterPojo>>()
		{
	        public List<DoctorTestMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<DoctorTestMasterPojo> list = new  ArrayList<DoctorTestMasterPojo>();
	            while(rs.next()) {
	            	DoctorTestMasterPojo dt = new DoctorTestMasterPojo();

	                dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	                dt.setDoctorTestId(rs.getInt("DOCTORTESTID"));
	                dt.setDoctorId(rs.getString("DOCTORID"));
	                dt.setTestMasterId(rs.getInt("TESTMASTERID"));
	                dt.setAmount(rs.getDouble("AMOUNT"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	public DoctorTestMasterPojo getAmountByDoctorIdAndTestId(String did,int tid)
	{
		String sql="SELECT * FROM DoctorTestMaster WHERE DOCTORID='"+did+"' AND TESTMASTERID="+tid;
		System.out.println(sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<DoctorTestMasterPojo>()
		{
	        public DoctorTestMasterPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	DoctorTestMasterPojo dt = new DoctorTestMasterPojo();

	            while(rs.next()) {
	            
	                dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	                dt.setDoctorTestId(rs.getInt("DOCTORTESTID"));
	                dt.setDoctorId(rs.getString("DOCTORID"));
	                dt.setTestMasterId(rs.getInt("TESTMASTERID"));
	                dt.setAmount(rs.getDouble("AMOUNT"));
	              
	            }
	            return dt;
	            
	            }
	    });
	
	}
	public List<DoctorTestMasterPojo> doctorTestMasterByDoctorId(String id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM DoctorTestMaster WHERE DOCTORID = '"+id+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<DoctorTestMasterPojo>>()
		{
	        public List<DoctorTestMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<DoctorTestMasterPojo> list = new  ArrayList<DoctorTestMasterPojo>();
	            while(rs.next()) {
	            	DoctorTestMasterPojo dt = new DoctorTestMasterPojo();

	                dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	                dt.setDoctorTestId(rs.getInt("DOCTORTESTID"));
	                dt.setDoctorId(rs.getString("DOCTORID"));
	                dt.setTestMasterId(rs.getInt("TESTMASTERID"));
	                dt.setAmount(rs.getDouble("AMOUNT"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}

	public int doctorTestMasterDelete(int pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM DoctorTestMaster WHERE DOCTORTESTID = ? ", new Object[] { pid });
	}

	public int doctorTestMasterDeleteByDoctorId(String id)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM DoctorTestMaster WHERE DOCTORID = ? ", new Object[] { id });
	}

	
	public boolean doctorTestMasterUpdate(final DoctorTestMasterPojo dt) 
	{
		// TODO Auto-generated method stub	
		String query="UPDATE DoctorTestMaster set AMOUNT =? where TESTMASTERID = ? AND DOCTORID=?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>()
		{  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setDouble(1, dt.getAmount());
			ps.setInt(2, dt.getTestMasterId());
			ps.setString(3, dt.getDoctorId());
			
		    return ps.execute();
		}
		});
	}
}
