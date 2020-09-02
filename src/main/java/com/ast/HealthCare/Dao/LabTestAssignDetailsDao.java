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

import com.ast.HealthCare.Pojo.LabTestAssignDetailsPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class LabTestAssignDetailsDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	LabTestAssignDetailsDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LabTestAssignDetailsDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addTestAssignDetails(final List<LabTestAssignDetailsPojo> pojo) {
	      String sql="INSERT INTO LABTESTASSIGNDETAILS (LABTESTID,TESTID,TESTNAME,TESTGROUPID,PRIORITY) values(?,?,?,?,?)";
			
			return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>(){  
				public Boolean doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					for(int i=0; i<pojo.size(); i++)
					{
					ps.setInt(1,pojo.get(i).getLabTestId());
					ps.setInt(2, pojo.get(i).getTestId());
					ps.setString(3,pojo.get(i).getTestName());
					ps.setInt(4, pojo.get(i).getTestGroupId());
					ps.setInt(5, pojo.get(i).getPriority());
					ps.execute();
					}
					return true;
				}
			});
				}


		public List<LabTestAssignDetailsPojo> testAssignDetailsAll() {
			String sql = "SELECT * FROM LABTESTAssignDetails";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestAssignDetailsPojo>>() {
		        public List<LabTestAssignDetailsPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestAssignDetailsPojo> list = new  ArrayList<LabTestAssignDetailsPojo>();
		            while(rs.next()) {
		            	LabTestAssignDetailsPojo dm = new LabTestAssignDetailsPojo();
		            	dm.setLabTestDetailsId(rs.getInt("LABTESTDETAILSID"));
		            	dm.setLabTestId(rs.getInt("LABTESTID"));
		            	dm.setTestId(rs.getInt("TESTID"));
		            	dm.setTestName(rs.getString("TESTNAME"));
		            	dm.setPriority(rs.getInt("PRIORITY"));
		            	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}


	public boolean testAssignDetailsUpdate(final LabTestAssignDetailsPojo pojo) {
	System.out.println("dao " + pojo);
			
			
			String query="UPDATE LABTESTASSIGNDETAILS set LABTESTID=?,TESTID=?,TESTNAME=?,TESTGROUPID=?,PRIORITY=? where LABTESTDETAILSID= ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setInt(1,pojo.getLabTestId());
				ps.setInt(2, pojo.getTestId());
				ps.setString(3,pojo.getTestName());
				ps.setInt(4, pojo.getTestGroupId());
				ps.setInt(5, pojo.getPriority());
				ps.setInt(6, pojo.getLabTestDetailsId());
				return ps.execute();
			}
			});
		}


		public int testAssignDetailsDelete(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTASSIGNDETAILS WHERE LABTESTDETAILSID = ?", new Object[] { did });
		}
		
		public int testAssignDetailsDeleteList(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTASSIGNDETAILS WHERE LABTESTID = ?", new Object[] { did });
		}

/*
		public LabTestAssignDetailsPojo testAssignDetailsSearchByName(String name) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTAssignDetails WHERE TESTNAME = ?";
			return jdbcTemplate.query(sql,  new Object[] { name }, new ResultSetExtractor<LabTestAssignDetailsPojo>() {
		        public LabTestAssignDetailsPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestAssignDetailsPojo dm = new LabTestAssignDetailsPojo();
		        	LabTestAssignDetailsPojo dm1 = new LabTestAssignDetailsPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setLabTestId(rs.getInt("LABTESTID"));
		            	dm.setLabTestName(rs.getString("LABTESTNAME"));
		            }
		        	if(dm.getLabTestName().equals(name))
		        		return dm;
		        	else
		        	{
		        		dm1.setLabTestName("No name found");
		        		return dm1;
		        	}
		            
		            }
		    });
		}*/
		
		public LabTestAssignDetailsPojo testAssignDetailsSearchById(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTASSIGNDETAILS WHERE LABTESTDETAILSID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestAssignDetailsPojo>() {
		        public LabTestAssignDetailsPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestAssignDetailsPojo dm = new LabTestAssignDetailsPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setLabTestDetailsId(rs.getInt("LABTESTDETAILSID"));
		            	dm.setLabTestId(rs.getInt("LABTESTID"));
		            	dm.setTestId(rs.getInt("TESTID"));
		            	dm.setTestName(rs.getString("TESTNAME"));
		            	dm.setPriority(rs.getInt("PRIORITY"));
		            }
		        	return dm;
		        }
		        	   });
			
		}
		
		public List<LabTestAssignDetailsPojo> testAssignDetailsSearchByLabTestId(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTASSIGNDETAILS WHERE LABTESTID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<List<LabTestAssignDetailsPojo>>() {
		        public List<LabTestAssignDetailsPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	List<LabTestAssignDetailsPojo> dt = new ArrayList<LabTestAssignDetailsPojo>();
		        	
		        	while(rs.next()) {
		        		LabTestAssignDetailsPojo dm = new LabTestAssignDetailsPojo();
		            	System.out.println("oii1");    
		            	dm.setLabTestDetailsId(rs.getInt("LABTESTDETAILSID"));
		            	dm.setLabTestId(rs.getInt("LABTESTID"));
		            	dm.setTestId(rs.getInt("TESTID"));
		            	dm.setTestName(rs.getString("TESTNAME"));
		            	dm.setPriority(rs.getInt("PRIORITY"));
		            	dt.add(dm);
		            }
		        	return dt;
		        }
		        	   });
			
		}



}
