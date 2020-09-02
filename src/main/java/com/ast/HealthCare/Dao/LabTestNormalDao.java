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

import com.ast.HealthCare.Pojo.LabTestNormalPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class LabTestNormalDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	@Autowired()
	LabTestNormalDetailsDao detDao;
	
	
//protected JdbcTemplate jdbcTemplate;
	LabTestNormalDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LabTestNormalDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public int getMaximumId()
	{
		String sql="SELECT MAX(TESTNORMALID) AS ID FROM LABTESTNORMAL";
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
	
	public LabTestNormalPojo addTestNormal(final LabTestNormalPojo pojo) {
	      String sql="INSERT INTO LABTESTNORMAL (TESTMASTERID,TESTMASTERNAME,TESTGROUPID,UNIT,VALUETYPE,ACCURATEVALUE,`MINVALUE`,`MAXVALUE`,DETAILSTATUS,TESTNORMALNAME,USERDEFINED) values(?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println(sql);
			return jdbcTemplate.execute(sql,new PreparedStatementCallback<LabTestNormalPojo>(){  
				public LabTestNormalPojo doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					ps.setInt(1, pojo.getTestMasterId());
					ps.setString(2,pojo.getTestMasterName());
					ps.setInt(3, pojo.getTestGroupId());
					ps.setString(4, pojo.getUnit());
					ps.setString(5, pojo.getValueType());
					ps.setDouble(6, pojo.getAccurateValue());
					ps.setDouble(7, pojo.getMinValue());
					ps.setDouble(8, pojo.getMaxValue());
					ps.setString(9,pojo.getDetailStatus());
					ps.setString(10,pojo.getTestNormalName());
					ps.setString(11,pojo.getUserDefined());
					ps.execute();
					return testNormalSearchById(getMaximumId());
				}
			});
				}


		public List<LabTestNormalPojo> testNormalAll() {
			String sql = "SELECT * FROM LABTESTNORMAL";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestNormalPojo>>() {
		        public List<LabTestNormalPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestNormalPojo> list = new  ArrayList<LabTestNormalPojo>();
		            while(rs.next()) {
		            	LabTestNormalPojo dm = new LabTestNormalPojo();
		            	dm.setTestNormalId(rs.getInt("TESTNORMALID"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setUnit(rs.getString("UNIT"));
		            	dm.setValueType(rs.getString("VALUETYPE"));
		            	dm.setAccurateValue(rs.getDouble("ACCURATEVALUE"));
		            	dm.setMinValue(rs.getDouble("MINVALUE"));
		            	dm.setMaxValue(rs.getDouble("MAXVALUE"));
		               	dm.setDetailStatus(rs.getString("DETAILSTATUS"));
		            	dm.setTestNormalName(rs.getString("TESTNORMALNAME"));
		            	dm.setUserDefined(rs.getString("USERDEFINED"));
		            	dm.setTestnormaldetail(detDao.testNormalDetailsSearchByTestNormalId(rs.getInt("TESTNORMALID")));
		            	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}


	public boolean testNormalUpdate(final LabTestNormalPojo pojo) {
	System.out.println("dao " + pojo);
			
			
			String query="UPDATE LABTESTNORMAL set TESTMASTERID=?,TESTMASTERNAME=?,TESTGROUPID=?,UNIT=?,VALUETYPE = ?,ACCURATEVALUE =? ,`MINVALUE`=?,`MAXVALUE`=?,DETAILSTATUS=?,TESTNORMALNAME=?,USERDEFINED=? where TESTNORMALID = ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setInt(1, pojo.getTestMasterId());
				ps.setString(2, pojo.getTestMasterName());
				ps.setInt(3, pojo.getTestGroupId());
				ps.setString(4, pojo.getUnit());
				ps.setString(5, pojo.getValueType());
				ps.setDouble(6, pojo.getAccurateValue());
				ps.setDouble(7, pojo.getMinValue());
				ps.setDouble(8, pojo.getMaxValue());
				ps.setString(9,pojo.getDetailStatus());	
				ps.setString(10,pojo.getTestNormalName());
				ps.setString(11,pojo.getUserDefined());
				ps.setInt(12, pojo.getTestNormalId());
				
				return ps.execute();
			}
			});
		}


		public int testNormalDelete(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTNORMAL WHERE TESTNORMALID = ?", new Object[] { did });
		}


		/*public LabTestNormalPojo testNormalSearchByName(String name) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTNORMAL WHERE TESTMASTERNAME = ?";
			return jdbcTemplate.query(sql,  new Object[] { name }, new ResultSetExtractor<LabTestNormalPojo>() {
		        public LabTestNormalPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestNormalPojo dm = new LabTestNormalPojo();
		        	LabTestNormalPojo dm1 = new LabTestNormalPojo();
		        	
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
		}*/
		
		public LabTestNormalPojo testNormalSearchById(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTNORMAL WHERE TESTNORMALID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestNormalPojo>() {
		        public LabTestNormalPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestNormalPojo dm = new LabTestNormalPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestNormalId(rs.getInt("TESTNORMALID"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setUnit(rs.getString("UNIT"));
		            	dm.setValueType(rs.getString("VALUETYPE"));
		            	dm.setAccurateValue(rs.getDouble("ACCURATEVALUE"));
		            	dm.setMinValue(rs.getDouble("MINVALUE"));
		            	dm.setMaxValue(rs.getDouble("MAXVALUE"));
		            	dm.setDetailStatus(rs.getString("DETAILSTATUS"));
		            	dm.setTestNormalName(rs.getString("TESTNORMALNAME"));
		            	dm.setUserDefined(rs.getString("USERDEFINED"));
		            	dm.setTestnormaldetail(detDao.testNormalDetailsSearchByTestNormalId(rs.getInt("TESTNORMALID")));
		            }
		        	System.out.println(dm.toString());
		        	return dm;
		        }
		        
		        	   });
			
			
		}
		
		public LabTestNormalPojo testNormalSearchByTestMasterId(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTNORMAL WHERE TESTMASTERID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestNormalPojo>() {
		        public LabTestNormalPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestNormalPojo dm = new LabTestNormalPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestNormalId(rs.getInt("TESTNORMALID"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setUnit(rs.getString("UNIT"));
		            	dm.setValueType(rs.getString("VALUETYPE"));
		            	dm.setAccurateValue(rs.getDouble("ACCURATEVALUE"));
		            	dm.setMinValue(rs.getDouble("MINVALUE"));
		            	dm.setMaxValue(rs.getDouble("MAXVALUE"));
		            	dm.setDetailStatus(rs.getString("DETAILSTATUS"));
		            	dm.setTestNormalName(rs.getString("TESTNORMALNAME"));
		            	dm.setUserDefined(rs.getString("USERDEFINED"));
		            	dm.setTestnormaldetail(detDao.testNormalDetailsSearchByTestNormalId(rs.getInt("TESTNORMALID")));
		            }
		        	System.out.println(dm.toString());
		        	return dm;
		        }
		        
		        	   });
			
			
		}
}
