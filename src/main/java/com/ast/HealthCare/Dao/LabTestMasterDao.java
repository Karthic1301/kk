package com.ast.HealthCare.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.ast.HealthCare.Pojo.LabTestMasterPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class LabTestMasterDao {

	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	LabTestMasterDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LabTestMasterDao constructor jdbc "+this.jdbcTemplate);
	}
	
		public boolean validateMaster(final LabTestMasterPojo agpojo) {
		
		String master=agpojo.getTestMasterName();
		String sql="SELECT * FROM LABTESTMASTER where TESTMASTERNAME ='" + master + "'";
		List<LabTestMasterPojo> obj=jdbcTemplate.query(sql, new MasterMapper());
		if(obj.size()==0)
		{
			return false;
		}
		else
		{
			return true;
		}
		    }
		

		public boolean validateUpdateMaster(final LabTestMasterPojo agpojo) {
		
		String group=agpojo.getTestMasterName();
		String sql="SELECT * FROM LABTESTMASTER where TESTMASTERNAME='" + group + "' and TESTMASTERID !="+ agpojo.getTestMasterId() +"";
		List<LabTestMasterPojo> obj=jdbcTemplate.query(sql, new MasterMapper());
		if(obj.size()==0)
		{
			return false;
		}
		else
		{
			return true;
		}
		    }
		class MasterMapper implements RowMapper<LabTestMasterPojo> {
			  public LabTestMasterPojo mapRow(ResultSet rs, int arg1) throws SQLException {
			 	  String master = rs.getString("TESTMASTERNAME");
			        
			    return new LabTestMasterPojo();
			  }
	}
	
	public String addTestMaster(final LabTestMasterPojo pojo) {
		
		boolean flag=validateMaster(pojo);
		if(flag==true)
		{
			return "EXISTS";
		}
		else
		{
		
	      String sql="INSERT INTO LABTESTMASTER (TESTMASTERNAME,TESTGROUPID,AMOUNT) values(?,?,?)";
			
			return jdbcTemplate.execute(sql,new PreparedStatementCallback<String>(){  
				public String doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					ps.setString(1, pojo.getTestMasterName());
					ps.setInt(2, pojo.getTestGroupId());
					ps.setDouble(3, pojo.getAmount());
					ps.execute();
					return "NOTEXISTS";
				}
			});
		}
				}


		public List<LabTestMasterPojo> testMasterAll() {
			String sql = "SELECT * FROM LABTESTMASTER";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestMasterPojo>>() {
		        public List<LabTestMasterPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestMasterPojo> list = new  ArrayList<LabTestMasterPojo>();
		            while(rs.next()) {
		            	LabTestMasterPojo dm = new LabTestMasterPojo();
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setAmount(rs.getDouble("AMOUNT"));
		            	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}


	public String testMasterUpdate(final LabTestMasterPojo pojo) {
	System.out.println("dao " + pojo);
	boolean flag=validateUpdateMaster(pojo);
	if(flag==true)
	{
		return "EXISTS";
	}
	else
	{
			
			
			String query="UPDATE LABTESTMASTER set TESTMASTERNAME = ?,TESTGROUPID=?,AMOUNT=? where TESTMASTERID = ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<String>(){  
			public String doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
			  	ps.setString(1, pojo.getTestMasterName());
				ps.setInt(2, pojo.getTestGroupId());
				ps.setDouble(3, pojo.getAmount());
				ps.setInt(4, pojo.getTestMasterId());				
				ps.execute();
				return "NOTEXISTS";
			}
			});
	}
		}


		public int testMasterDelete(int did) {
			// TODO Auto-generated method stub
			try
			{
				return jdbcTemplate.update("DELETE FROM LABTESTMASTER WHERE TESTMASTERID = ?", new Object[] { did });
			}
			catch(Exception e)
			{
				if(e instanceof DuplicateKeyException ) {
			         System.out.println("Error");
			    }
				
			}
			return 101;
			
		}


		public LabTestMasterPojo testMasterSearchByName(String name) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTMASTER WHERE TESTMASTERNAME = ?";
			return jdbcTemplate.query(sql,  new Object[] { name }, new ResultSetExtractor<LabTestMasterPojo>() {
		        public LabTestMasterPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestMasterPojo dm = new LabTestMasterPojo();
		        	LabTestMasterPojo dm1 = new LabTestMasterPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setAmount(rs.getDouble("AMOUNT"));
		            }
		        	if(dm.getTestMasterName().equals(name))
		        		return dm;
		        	else
		        	{
		        		dm1.setTestMasterName("No name found");
		        		return dm1;
		        	}
		            
		            }
		    });
		}
		
		public LabTestMasterPojo testMasterSearchById(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTMASTER WHERE TESTMASTERID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestMasterPojo>() {
		        public LabTestMasterPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestMasterPojo dm = new LabTestMasterPojo();
		        	LabTestMasterPojo dm1 = new LabTestMasterPojo();
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setAmount(rs.getDouble("AMOUNT"));
		            }
		        	return dm;
		        }
		        	   });
			
		}
		
		public List<LabTestMasterPojo> testMasterNotInNormal() {
			String sql = "SELECT * FROM LABTESTMASTER WHERE TESTMASTERID NOT IN (SELECT TESTMASTERID FROM LABTESTNORMAL)";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestMasterPojo>>() {
		        public List<LabTestMasterPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestMasterPojo> list = new  ArrayList<LabTestMasterPojo>();
		            while(rs.next()) {
		            	LabTestMasterPojo dm = new LabTestMasterPojo();
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setAmount(rs.getDouble("AMOUNT"));
		            	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}
		
		
		public List<LabTestMasterPojo> testMasterNotInAssign() {
			String sql = "SELECT * FROM LABTESTMASTER WHERE TESTMASTERID NOT IN (SELECT TESTMASTERID FROM LABTESTASSIGN)";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestMasterPojo>>() {
		        public List<LabTestMasterPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestMasterPojo> list = new  ArrayList<LabTestMasterPojo>();
		            while(rs.next()) {
		            	LabTestMasterPojo dm = new LabTestMasterPojo();
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setAmount(rs.getDouble("AMOUNT"));
		            	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}
		
		
}
