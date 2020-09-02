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

import com.ast.HealthCare.Pojo.TestMasterPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class TestMasterDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	TestMasterDao()
	{
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("TestMasterDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addTestMaster(final TestMasterPojo dis)
	{
		// TODO Auto-generated method stub
		String sql1="INSERT INTO TestMaster (TESTMASTERNAME,PRESCRIPTIONFLAG,AMOUNT,INOUTFLAG,ORDERNO) values(?,?,?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>()
		{  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, dis.getTestMasterName());
				ps.setString(2, dis.getPrescriptionFlag());
				ps.setDouble(3,dis.getAmount());
				ps.setString(4, dis.getInOutFlag());
				ps.setInt(5, dis.getOrderNo());
				return ps.execute();
			    
			}
		});
	}

	public List<TestMasterPojo> testMasterAll() 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM TestMaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<TestMasterPojo>>()
		{
	        public List<TestMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<TestMasterPojo> list = new  ArrayList<TestMasterPojo>();
	            while(rs.next()) {
	            	TestMasterPojo dt = new TestMasterPojo();
	            	dt.setTestMasterId(rs.getInt("TESTMASTERID"));
	                dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	                dt.setPrescriptionFlag(rs.getString("PRESCRIPTIONFLAG"));
	                dt.setAmount(rs.getDouble("AMOUNT"));
	                dt.setInOutFlag(rs.getString("INOUTFLAG"));
	                dt.setOrderNo(rs.getInt("ORDERNO"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	public List<TestMasterPojo> testAllByPrescriptionFlag() 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM TestMaster where PRESCRIPTIONFLAG = 'TRUE'";
		System.out.println(sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<TestMasterPojo>>()
		{
	        public List<TestMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<TestMasterPojo> list = new  ArrayList<TestMasterPojo>();
	            while(rs.next()) {
	            	TestMasterPojo dt = new TestMasterPojo();
	            	dt.setTestMasterId(rs.getInt("TESTMASTERID"));
	                dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	                dt.setPrescriptionFlag(rs.getString("PRESCRIPTIONFLAG"));
	                dt.setAmount(rs.getDouble("AMOUNT"));
	                dt.setInOutFlag(rs.getString("INOUTFLAG"));
	                dt.setOrderNo(rs.getInt("ORDERNO"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	public List<TestMasterPojo> testAllByInOutFlag(String status) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM TestMaster where INOUTFLAG = '"+status+"' ORDER BY ORDERNO ASC";
		System.out.println(sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<TestMasterPojo>>()
		{
	        public List<TestMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<TestMasterPojo> list = new  ArrayList<TestMasterPojo>();
	            while(rs.next()) {
	            	TestMasterPojo dt = new TestMasterPojo();
	            	dt.setTestMasterId(rs.getInt("TESTMASTERID"));
	                dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	                dt.setPrescriptionFlag(rs.getString("PRESCRIPTIONFLAG"));
	                dt.setAmount(rs.getDouble("AMOUNT"));
	                dt.setInOutFlag(rs.getString("INOUTFLAG"));
	                dt.setOrderNo(rs.getInt("ORDERNO"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	
	public TestMasterPojo testMasterById(int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM TestMaster WHERE TESTMASTERID="+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<TestMasterPojo>()
		{
	        public TestMasterPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	TestMasterPojo dt = new TestMasterPojo();
	            while(rs.next()) {
	            	
	            	dt.setTestMasterId(rs.getInt("TESTMASTERID"));
	                dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	                dt.setPrescriptionFlag(rs.getString("PRESCRIPTIONFLAG"));
	                dt.setAmount(rs.getDouble("AMOUNT"));
	                dt.setInOutFlag(rs.getString("INOUTFLAG"));
	                dt.setOrderNo(rs.getInt("ORDERNO"));
	            }
	            return dt;
	            
	            }
	    });
	
	}

	public int testMasterDelete(int pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM TestMaster WHERE TESTMASTERID = ?", new Object[] { pid });
	}

	public boolean testMasterUpdate(final TestMasterPojo dt) 
	{
		// TODO Auto-generated method stub	
		String query="UPDATE TestMaster set TESTMASTERNAME=?,PRESCRIPTIONFLAG=?,AMOUNT=?,INOUTFLAG=?,ORDERNO =? where TESTMASTERID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>()
		{  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getTestMasterName());
			ps.setString(2, dt.getPrescriptionFlag());
			ps.setDouble(3,dt.getAmount());
			ps.setString(4, dt.getInOutFlag());
			ps.setInt(5,dt.getOrderNo());
			ps.setInt(6,  dt.getTestMasterId());
		    return ps.execute();
		}
		});
	}
}
