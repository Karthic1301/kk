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

import com.ast.HealthCare.Pojo.LabTestGroupPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class LabTestGroupDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	LabTestGroupDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LabTestGroupDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean validateGroup(final LabTestGroupPojo agpojo) {
		
		String group=agpojo.getTestGroup();
		String sql="SELECT * FROM LABTESTGROUP where TESTGROUP ='" + group + "'";
		List<LabTestGroupPojo> obj=jdbcTemplate.query(sql, new GroupMapper());
		if(obj.size()==0)
		{
			return false;
		}
		else
		{
			return true;
		}
		    }
		

		public boolean validateUpdateGroup(final LabTestGroupPojo agpojo) {
		
		String group=agpojo.getTestGroup();
		String sql="SELECT * FROM LABTESTGROUP where TESTGROUP='" + group + "' and TESTGROUPID !="+ agpojo.getTestGroupId() +"";
		List<LabTestGroupPojo> obj=jdbcTemplate.query(sql, new GroupMapper());
		if(obj.size()==0)
		{
			return false;
		}
		else
		{
			return true;
		}
		    }
		class GroupMapper implements RowMapper<LabTestGroupPojo> {
			  public LabTestGroupPojo mapRow(ResultSet rs, int arg1) throws SQLException {
			 	  String group = rs.getString("TESTGROUP");
			        
			    return new LabTestGroupPojo();
			  }
	}
	
	public String addTestGroup(final LabTestGroupPojo pojo) {
		
		boolean flag=validateGroup(pojo);
		if(flag==true)
		{
			return "EXISTS";
		}
		else
		{
	      String sql="INSERT INTO LABTESTGROUP (TESTGROUP) values(?)";
			
			return jdbcTemplate.execute(sql,new PreparedStatementCallback<String>(){  
				public String doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					
					ps.setString(1, pojo.getTestGroup());
					ps.execute();
					return "NOTEXISTS";
				}
			});
		}
				}


		public List<LabTestGroupPojo> testGroupAll() {
			String sql = "SELECT * FROM LABTESTGROUP";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestGroupPojo>>() {
		        public List<LabTestGroupPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestGroupPojo> list = new  ArrayList<LabTestGroupPojo>();
		            while(rs.next()) {
		            	LabTestGroupPojo dm = new LabTestGroupPojo();
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setTestGroup(rs.getString("TESTGROUP"));
		            	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}


	public String testGroupUpdate(final LabTestGroupPojo pojo) {
	System.out.println("dao " + pojo);
	
	boolean flag=validateUpdateGroup(pojo);
	if(flag==true)
	{
		return "EXISTS";
	}
	else
	{
			
			
			String query="UPDATE LABTESTGROUP set TESTGROUP = ? where TESTGROUPID = ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<String>(){  
			public String doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
			   
				ps.setString(1, pojo.getTestGroup());
				ps.setInt(2, pojo.getTestGroupId());
				ps.execute();
				return "NOTEXISTS";
			}
			});
	}
		}


		public int testGroupDelete(int did) {
			try
			{
			return jdbcTemplate.update("DELETE FROM LABTESTGROUP WHERE TESTGROUPID = ?", new Object[] { did });
			}
			catch(Exception e)
			{
				if(e instanceof DuplicateKeyException ) {
			         System.out.println("Error");
			    }
				
			}
			return 101;
					}


		public LabTestGroupPojo testGroupSearchByName(String name) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTGROUP WHERE TESTGROUP = ?";
			return jdbcTemplate.query(sql,  new Object[] { name }, new ResultSetExtractor<LabTestGroupPojo>() {
		        public LabTestGroupPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestGroupPojo dm = new LabTestGroupPojo();
		        	LabTestGroupPojo dm1 = new LabTestGroupPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setTestGroup(rs.getString("TESTGROUP"));
		            }
		        	if(dm.getTestGroup().equals(name))
		        		return dm;
		        	else
		        	{
		        		dm1.setTestGroup("No name found");
		        		return dm1;
		        	}
		            
		            }
		    });
		}
		
		public LabTestGroupPojo testGroupSearchById(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTGROUP WHERE TESTGROUPID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestGroupPojo>() {
		        public LabTestGroupPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestGroupPojo dm = new LabTestGroupPojo();
		        	LabTestGroupPojo dm1 = new LabTestGroupPojo();
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setTestGroup(rs.getString("TESTGROUP"));
		            }
		        	return dm;
		        }
		        	   });
			
		}
		


}
