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

import com.ast.HealthCare.Pojo.LabTestAssignPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class LabTestAssignDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	@Autowired()
	LabTestAssignDetailsDao ldDao;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	LabTestAssignDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LabTestAssignDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public int getMaximumId()
	{
		String sql="SELECT MAX(LABTESTID) AS ID FROM LABTESTASSIGN";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
	        public Integer extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	
	        	
	        	if(rs.next()) {
	            	System.out.println("oii1");    
	            	return rs.getInt("ID");
	        	}
		return 0;
	        }
		});
	}
	
	public LabTestAssignPojo addTestAssign(final LabTestAssignPojo pojo) {
	      String sql="INSERT INTO LABTESTASSIGN (TESTMASTERID,TESTMASTERNAME) values(?,?)";
			
			return jdbcTemplate.execute(sql,new PreparedStatementCallback<LabTestAssignPojo>(){  
				public LabTestAssignPojo doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					ps.setInt(1, pojo.getTestMasterId());
					ps.setString(2,pojo.getTestMasterName());
					 ps.execute();
					 return testAssignSearchById(getMaximumId());
				}
			});
				}


		public List<LabTestAssignPojo> testAssignAll() {
			String sql = "SELECT * FROM LABTESTASSIGN";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestAssignPojo>>() {
		        public List<LabTestAssignPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestAssignPojo> list = new  ArrayList<LabTestAssignPojo>();
		            while(rs.next()) {
		            	LabTestAssignPojo dm = new LabTestAssignPojo();
		            	dm.setLabTestId(rs.getInt("LABTESTID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setLabTestAssign(ldDao.testAssignDetailsSearchByLabTestId(rs.getInt("LABTESTID")));
		            	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}


	public boolean testAssignUpdate(final LabTestAssignPojo pojo) {
	System.out.println("dao " + pojo);
			
			
			String query="UPDATE LABTESTASSIGN set TESTMASTERID =?, TESTMASTERNAME=? where LABTESTID= ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setInt(1, pojo.getTestMasterId());
				ps.setString(2, pojo.getTestMasterName());
				ps.setInt(3, pojo.getLabTestId());
				return ps.execute();
			}
			});
		}


		public int testAssignDelete(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTASSIGN WHERE LABTESTID = ?", new Object[] { did });
		}


		public LabTestAssignPojo testAssignSearchByName(String name) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTASSIGN WHERE TESTNAME = ?";
			return jdbcTemplate.query(sql,  new Object[] { name }, new ResultSetExtractor<LabTestAssignPojo>() {
		        public LabTestAssignPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestAssignPojo dm = new LabTestAssignPojo();
		        	LabTestAssignPojo dm1 = new LabTestAssignPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setLabTestId(rs.getInt("LABTESTID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setLabTestAssign(ldDao.testAssignDetailsSearchByLabTestId(rs.getInt("LABTESTID")));
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
		
		public LabTestAssignPojo testAssignSearchById(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTASSIGN WHERE LABTESTID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestAssignPojo>() {
		        public LabTestAssignPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestAssignPojo dm = new LabTestAssignPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setLabTestId(rs.getInt("LABTESTID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setLabTestAssign(ldDao.testAssignDetailsSearchByLabTestId(rs.getInt("LABTESTID")));
		            }
		        	return dm;
		        }
		        	   });
			
		}
		
		public LabTestAssignPojo testAssignSearchByTestMasterId(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTASSIGN WHERE TESTMASTERID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestAssignPojo>() {
		        public LabTestAssignPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestAssignPojo dm = new LabTestAssignPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setLabTestId(rs.getInt("LABTESTID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setLabTestAssign(ldDao.testAssignDetailsSearchByLabTestId(rs.getInt("LABTESTID")));
		            }
		        	return dm;
		        }
		        	   });
			
		}

}
