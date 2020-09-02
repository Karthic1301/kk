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

import com.ast.HealthCare.Pojo.LabTestResultEntryDetailsPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class LabTestResultEntryDetailsDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	LabTestResultEntryDetailsDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LabTestResultEntryDetailsDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addTestResultEntryDetails(final List<LabTestResultEntryDetailsPojo> pojo) {
	      String sql="INSERT INTO LABTESTRESULTENTRYDETAILS (TESTRESULTID,TESTMASTERID,TESTMASTERNAME,TESTTYPEID,"
	      		+ "TESTTYPENAME,TESTTYPEDETAILSID,TESTTYPEDETAILS,RESULT,NORMALVALUE)values(?,?,?,?,?,?,?,?,?)";
			
			return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>(){  
				public Boolean doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					System.out.println(pojo.size());
					for(int i=0; i<pojo.size();i++)
					{
					ps.setInt(1, pojo.get(i).getTestResultId());
					ps.setInt(2, pojo.get(i).getTestMasterId());
					ps.setString(3, pojo.get(i).getTestMasterName());
					ps.setInt(4, pojo.get(i).getTestTypeId());
					ps.setString(5, pojo.get(i).getTestTypeName());
					ps.setInt(6, pojo.get(i).getTestTypeDetailsId());
					ps.setString(7, pojo.get(i).getTestTypeDetails());
					ps.setString(8, pojo.get(i).getResult());
					ps.setString(9, pojo.get(i).getNormalValue());
					ps.execute();
					}
					return true;
				}
			});
				}


		public List<LabTestResultEntryDetailsPojo> testResultEntryDetailsAll() {
			String sql = "SELECT * FROM LABTESTRESULTENTRYDETAILS";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestResultEntryDetailsPojo>>() {
		        public List<LabTestResultEntryDetailsPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestResultEntryDetailsPojo> list = new  ArrayList<LabTestResultEntryDetailsPojo>();
		            while(rs.next()) {
		            	LabTestResultEntryDetailsPojo dm = new LabTestResultEntryDetailsPojo();
		            	dm.setTestResultDetailsid(rs.getInt("TESTRESULTDETAILSID"));
		            	dm.setTestResultId(rs.getInt("TESTRESULTID"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestTypeId(rs.getInt("TESTTYPEID"));
		            	dm.setTestTypeName(rs.getString("TESTTYPENAME"));
		            	dm.setTestTypeDetailsId(rs.getInt("TESTTYPEDETAILSID"));
		            	dm.setTestTypeDetails(rs.getString("TESTTYPEDETAILS"));
		            	dm.setResult(rs.getString("RESULT"));
		            	dm.setNormalValue(rs.getString("NORMALVALUE"));
		            	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}


	public boolean testResultEntryDetailsUpdate(final LabTestResultEntryDetailsPojo pojo) {
	System.out.println("dao " + pojo);
			
			
			String query="UPDATE LABTESTRESULTENTRYDETAILS set TESTRESULTID =?,TESTMASTERID=?,TESTMASTERNAME=?,"
					+ "TESTTYPEID =?,TESTTYPENAME =?,TESTTYPEDETAILSID =?,TESTTYPEDETAILS =?,RESULT=?,NORMALVALUE =? "
					+ "where TESTRESULTENTRYDETAILSID = ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setInt(1, pojo.getTestResultId());
				ps.setInt(2, pojo.getTestMasterId());
				ps.setString(3, pojo.getTestMasterName());
				ps.setInt(4, pojo.getTestTypeId());
				ps.setString(5, pojo.getTestTypeName());
				ps.setInt(6, pojo.getTestTypeDetailsId());
				ps.setString(7, pojo.getTestTypeDetails());
				ps.setString(8, pojo.getResult());
				ps.setString(9, pojo.getNormalValue());
				ps.setInt(10,pojo.getTestResultDetailsid());
				return ps.execute();
			}
			});
		}


		public int testResultEntryDetailsDelete(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTREPORTENTRY WHERE TESTRESULTENTRYDETAILSID = ?", new Object[] { did });
		}
		
		public int testResultEntryDetailsDeleteList(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTREPORTENTRY WHERE TESTRESULTID = ?", new Object[] { did });
		}


		/*public LabTestResultEntryDetailsPojo testResultEntryDetailsSearchByName(String name) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTREPORTENTRY WHERE TESTMASTERNAME = ?";
			return jdbcTemplate.query(sql,  new Object[] { name }, new ResultSetExtractor<LabTestResultEntryDetailsPojo>() {
		        public LabTestResultEntryDetailsPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestResultEntryDetailsPojo dm = new LabTestResultEntryDetailsPojo();
		        	LabTestResultEntryDetailsPojo dm1 = new LabTestResultEntryDetailsPojo();
		        	
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
		
		public LabTestResultEntryDetailsPojo testResultEntryDetailsSearchById(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTRESULTENTRYDETAILS WHERE TESTRESULTENTRYDETAILSID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestResultEntryDetailsPojo>() {
		        public LabTestResultEntryDetailsPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestResultEntryDetailsPojo dm = new LabTestResultEntryDetailsPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestResultDetailsid(rs.getInt("TESTRESULTDETAILSID"));
		            	dm.setTestResultId(rs.getInt("TESTRESULTID"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestTypeId(rs.getInt("TESTTYPEID"));
		            	dm.setTestTypeName(rs.getString("TESTTYPENAME"));
		            	dm.setTestTypeDetailsId(rs.getInt("TESTTYPEDETAILSID"));
		            	dm.setTestTypeDetails(rs.getString("TESTTYPEDETAILS"));
		            	dm.setResult(rs.getString("RESULT"));
		            	dm.setNormalValue(rs.getString("NORMALVALUE"));
		            }
		        	return dm;
		        }
		        	   });
			
		}
		
		public List<LabTestResultEntryDetailsPojo> testResultEntryDetailsSearchByResultId(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTRESULTENTRYDETAILS WHERE TESTRESULTID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<List<LabTestResultEntryDetailsPojo>>() {
		        public List<LabTestResultEntryDetailsPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	List<LabTestResultEntryDetailsPojo> dt = new ArrayList<LabTestResultEntryDetailsPojo>();
		        	
		        	while(rs.next()) {
		        		LabTestResultEntryDetailsPojo dm = new LabTestResultEntryDetailsPojo();
		            	System.out.println("oii1");    
		            	dm.setTestResultDetailsid(rs.getInt("TESTRESULTDETAILSID"));
		            	dm.setTestResultId(rs.getInt("TESTRESULTID"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestTypeId(rs.getInt("TESTTYPEID"));
		            	dm.setTestTypeName(rs.getString("TESTTYPENAME"));
		            	dm.setTestTypeDetailsId(rs.getInt("TESTTYPEDETAILSID"));
		            	dm.setTestTypeDetails(rs.getString("TESTTYPEDETAILS"));
		            	dm.setResult(rs.getString("RESULT"));
		            	dm.setNormalValue(rs.getString("NORMALVALUE"));
		            	dt.add(dm);
		            }
		        	return dt;
		        }
		        	   });
			
		}


}
