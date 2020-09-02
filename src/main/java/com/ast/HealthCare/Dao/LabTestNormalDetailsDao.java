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

import com.ast.HealthCare.Pojo.LabTestNormalDetailsPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class LabTestNormalDetailsDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	LabTestNormalDetailsDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LabTestNormalDetailsDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addTestNormalDetails(final List<LabTestNormalDetailsPojo> pojo) {
	      String sql="INSERT INTO LABTESTNORMALDETAILS (TESTNORMALID,TESTID,TESTNAME,TESTGROUPID,UNIT,VALUETYPE,ACCURATEVALUE,`MINVALUE`,`MAXVALUE`,USERDEFINED) values(?,?,?,?,?,?,?,?,?,?)";
			System.out.println(pojo.toString());
			return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>(){  
				public Boolean doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException { 
					for(int i=0; i<pojo.size();i++)
					{
					ps.setInt(1, pojo.get(i).getTestNormalId());
					ps.setInt(2, pojo.get(i).getTestId());
					ps.setString(3,pojo.get(i).getTestName());
					ps.setInt(4, pojo.get(i).getTestGroupId());
					ps.setString(5, pojo.get(i).getUnit());
					ps.setString(6, pojo.get(i).getValueType());
					ps.setDouble(7, pojo.get(i).getAccurateValue());
					ps.setDouble(8, pojo.get(i).getMinValue());
					ps.setDouble(9, pojo.get(i).getMaxValue());
					ps.setString(10,pojo.get(i).getUserDefined());
					ps.execute();
					}
					return true;
				}
			});
				}


		public List<LabTestNormalDetailsPojo> testNormalDetailsAll() {
			String sql = "SELECT * FROM LABTESTNORMALDETAILS";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestNormalDetailsPojo>>() {
		        public List<LabTestNormalDetailsPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestNormalDetailsPojo> list = new  ArrayList<LabTestNormalDetailsPojo>();
		            while(rs.next()) {
		            	LabTestNormalDetailsPojo dm = new LabTestNormalDetailsPojo();
		            	dm.setTestNormalDetailsId(rs.getInt("TESTNORMALDETAILSID"));
		            	dm.setTestNormalId(rs.getInt("TESTNORMALID"));
		            	dm.setTestId(rs.getInt("TESTID"));
		            	dm.setTestName(rs.getString("TESTNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setUnit(rs.getString("UNIT"));
		            	dm.setValueType(rs.getString("VALUE TYPE"));
		            	dm.setAccurateValue(rs.getDouble("ACCURATEVALUE"));
		            	dm.setMinValue(rs.getDouble("MINVALUE"));
		            	dm.setMaxValue(rs.getDouble("MAXVALUE"));
		            	dm.setUserDefined(rs.getString("USERDEFINED"));
		            	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}


	public boolean testNormalDetailsUpdate(final LabTestNormalDetailsPojo pojo) {
	System.out.println("dao " + pojo);
			
			
			String query="UPDATE LABTESTNORMALDETAILS set TESTNORMALID=?,TESTID=?,TESTNAME=?,TESTGROUPID=?,UNIT=?,VALUETYPE = ?,ACCURATEVALUE =? ,`MINVALUE`=?,`MAXVALUE`=?,USERDEFINED=? where TESTNORMALDETAILSID = ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setInt(1, pojo.getTestId());
				ps.setString(2, pojo.getTestName());
				ps.setInt(3, pojo.getTestGroupId());
				ps.setString(4, pojo.getUnit());
				ps.setString(5, pojo.getValueType());
				ps.setDouble(6, pojo.getAccurateValue());
				ps.setDouble(7, pojo.getMinValue());
				ps.setDouble(8, pojo.getMaxValue());
				ps.setString(9,pojo.getUserDefined());
				ps.setInt(10, pojo.getTestNormalDetailsId());
				return ps.execute();
			}
			});
		}


		public int testNormalDetailsDelete(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTNORMALDETAILS WHERE TESTNORMALDETAILSID = ?", new Object[] { did });
		}

		
		public int testNormalDetailsDeleteList(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTNORMALDETAILS WHERE TESTNORMALID = ?", new Object[] { did });
		}
		

		/*public LabTestNormalDetailsPojo testNormalDetailsSearchByName(String name) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTNormalDetails WHERE TESTNAME = ?";
			return jdbcTemplate.query(sql,  new Object[] { name }, new ResultSetExtractor<LabTestNormalDetailsPojo>() {
		        public LabTestNormalDetailsPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestNormalDetailsPojo dm = new LabTestNormalDetailsPojo();
		        	LabTestNormalDetailsPojo dm1 = new LabTestNormalDetailsPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestId(rs.getInt("TESTID"));
		            	dm.setTestName(rs.getString("TESTNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setAmount(rs.getDouble("AMOUNT"));
		            }
		        	if(dm.getTestName().equals(name))
		        		return dm;
		        	else
		        	{
		        		dm1.setTestName("No name found");
		        		return dm1;
		        	}
		            
		            }
		    });
		}*/
		
		public LabTestNormalDetailsPojo testNormalDetailsSearchById(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTNORMALDETAILS WHERE TESTNORMALDETAILSID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestNormalDetailsPojo>() {
		        public LabTestNormalDetailsPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestNormalDetailsPojo dm = new LabTestNormalDetailsPojo();
		        	
		        	while(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestNormalDetailsId(rs.getInt("TESTNORMALDETAILSID"));
		            	dm.setTestNormalId(rs.getInt("TESTNORMALID"));
		            	dm.setTestId(rs.getInt("TESTID"));
		            	dm.setTestName(rs.getString("TESTNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setUnit(rs.getString("UNIT"));
		            	dm.setValueType(rs.getString("VALUETYPE"));
		            	dm.setAccurateValue(rs.getDouble("ACCURATEVALUE"));
		            	dm.setMinValue(rs.getDouble("MINVALUE"));
		            	dm.setMaxValue(rs.getDouble("MAXVALUE"));
		            	dm.setUserDefined(rs.getString("USERDEFINED"));
		            }
		        	return dm;
		        }
		        	   });
			
		}
		
		public List<LabTestNormalDetailsPojo> testNormalDetailsSearchByTestNormalId(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTNORMALDETAILS WHERE TESTNORMALID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<List<LabTestNormalDetailsPojo>>() {
		        public List<LabTestNormalDetailsPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	List<LabTestNormalDetailsPojo> dt = new ArrayList<LabTestNormalDetailsPojo>();
		        	
		        	while(rs.next()) {
		            	System.out.println("oii1"); 
		            	LabTestNormalDetailsPojo dm = new LabTestNormalDetailsPojo();
		            	dm.setTestNormalDetailsId(rs.getInt("TESTNORMALDETAILSID"));
		            	dm.setTestNormalId(rs.getInt("TESTNORMALID"));
		            	dm.setTestId(rs.getInt("TESTID"));
		            	dm.setTestName(rs.getString("TESTNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setUnit(rs.getString("UNIT"));
		            	dm.setValueType(rs.getString("VALUETYPE"));
		            	dm.setAccurateValue(rs.getDouble("ACCURATEVALUE"));
		            	dm.setMinValue(rs.getDouble("MINVALUE"));
		            	dm.setMaxValue(rs.getDouble("MAXVALUE"));
		            	dm.setUserDefined(rs.getString("USERDEFINED"));
		            	dt.add(dm);
		            	
		            }
		        	return dt;
		        }
		        	   });
			
		}

}
