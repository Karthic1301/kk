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

import com.ast.HealthCare.Pojo.LabTestReportEntryDetailsPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class LabTestReportEntryDetailsDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	LabTestReportEntryDetailsDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LabTestReportEntryDetailsDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addTestReportEntryDetails(final List<LabTestReportEntryDetailsPojo> pojo) {
	      String sql="INSERT INTO LABTESTREPORTENTRYDETAILS (TESTREPORTENTRYID,TESTMASTERID,TESTMASTERNAME,TESTGROUPID,AMOUNT,DISCOUNTPERCENT,DISCOUNTAMOUNT,TOTAMOUNT)values(?,?,?,?,?,?,?,?)";
			
			return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>(){  
				public Boolean doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					for(int i=0; i<pojo.size();i++)
					{
					ps.setInt(1, pojo.get(i).getTestReportEntryId());
					ps.setInt(2, pojo.get(i).getTestMasterId());
					ps.setString(3, pojo.get(i).getTestMasterName());
					ps.setInt(4, pojo.get(i).getTestGroupId());
					ps.setDouble(5, pojo.get(i).getAmount());
					ps.setFloat(6, pojo.get(i).getDiscountPercent());
					ps.setDouble(7, pojo.get(i).getDiscountAmount());
					ps.setDouble(8, pojo.get(i).getTotAmount());
					
					ps.execute();
					}
					return true;
				}
			});
				}


		public List<LabTestReportEntryDetailsPojo> testReportEntryDetailsAll() {
			String sql = "SELECT * FROM LABTESTREPORTENTRYDETAILS";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestReportEntryDetailsPojo>>() {
		        public List<LabTestReportEntryDetailsPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestReportEntryDetailsPojo> list = new  ArrayList<LabTestReportEntryDetailsPojo>();
		            while(rs.next()) {
		            	LabTestReportEntryDetailsPojo dm = new LabTestReportEntryDetailsPojo();
		            	dm.setTestReportEntryDetailsId(rs.getInt("TESTREPORTENTRYDETAILSID"));
		            	dm.setTestReportEntryId(rs.getInt("TESTREPORTENTRYID"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setAmount(rs.getDouble("AMOUNT"));
		            	dm.setDiscountPercent(rs.getFloat("DISCOUNTPERCENT"));
		            	dm.setDiscountAmount(rs.getFloat("DISCOUNTAMOUNT"));
		            	dm.setTotAmount(rs.getDouble("TOTAMOUNT"));
		            	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}


	public boolean testReportEntryDetailsUpdate(final LabTestReportEntryDetailsPojo pojo) {
	System.out.println("dao " + pojo);
			
			
			String query="UPDATE LABTESTREPORTENTRYDETAILS set TESTREPORTENTRYID = ?,TESTMASTERID =?,TESTMASTERNAME =?,TESTGROUPID =?,AMOUNT =?,DISCOUNTPERCENT = ?,DISCOUNTAMOUNT =? ,TOTAMOUNT= ? where TESTREPORTENTRYDETAILSID = ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setInt(1, pojo.getTestReportEntryId());
				ps.setInt(2, pojo.getTestMasterId());
				ps.setString(3, pojo.getTestMasterName());
				ps.setInt(4, pojo.getTestGroupId());
				ps.setDouble(5, pojo.getAmount());
				ps.setFloat(6, pojo.getDiscountPercent());
				ps.setDouble(7, pojo.getDiscountAmount());
				ps.setDouble(8, pojo.getTotAmount());
				ps.setInt(9,pojo.getTestReportEntryDetailsId());
				return ps.execute();
			}
			});
		}


		public int testReportEntryDetailsDelete(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTREPORTENTRY WHERE TESTREPORTENTRYDETAILSID = ?", new Object[] { did });
		}
		
		public int testReportEntryDetailsDeleteList(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTREPORTENTRY WHERE TESTREPORTENTRYID = ?", new Object[] { did });
		}


		/*public LabTestReportEntryDetailsPojo testReportEntryDetailsSearchByName(String name) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTREPORTENTRY WHERE TESTMASTERNAME = ?";
			return jdbcTemplate.query(sql,  new Object[] { name }, new ReportSetExtractor<LabTestReportEntryDetailsPojo>() {
		        public LabTestReportEntryDetailsPojo extractData(ReportSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestReportEntryDetailsPojo dm = new LabTestReportEntryDetailsPojo();
		        	LabTestReportEntryDetailsPojo dm1 = new LabTestReportEntryDetailsPojo();
		        	
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
		
		public LabTestReportEntryDetailsPojo testReportEntryDetailsSearchById(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTREPORTENTRYDETAILS WHERE TESTREPORTENTRYDETAILSID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestReportEntryDetailsPojo>() {
		        public LabTestReportEntryDetailsPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestReportEntryDetailsPojo dm = new LabTestReportEntryDetailsPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestReportEntryDetailsId(rs.getInt("TESTREPORTENTRYDETAILSID"));
		            	dm.setTestReportEntryId(rs.getInt("TESTREPORTENTRYID"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setAmount(rs.getDouble("AMOUNT"));
		            	dm.setDiscountPercent(rs.getFloat("DISCOUNTPERCENT"));
		            	dm.setDiscountAmount(rs.getFloat("DISCOUNTAMOUNT"));
		            	dm.setTotAmount(rs.getDouble("TOTAMOUNT"));
		            }
		        	return dm;
		        }
		        	   });
			
		}
		
		public List<LabTestReportEntryDetailsPojo> testReportEntryDetailsSearchByReportId(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTREPORTENTRYDETAILS WHERE TESTREPORTENTRYID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<List<LabTestReportEntryDetailsPojo>>() {
		        public List<LabTestReportEntryDetailsPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	List<LabTestReportEntryDetailsPojo> dt = new ArrayList<LabTestReportEntryDetailsPojo>();
		        	
		        	while(rs.next()) {
		        		LabTestReportEntryDetailsPojo dm = new LabTestReportEntryDetailsPojo();
		            	System.out.println("oii1");    
		            	dm.setTestReportEntryDetailsId(rs.getInt("TESTREPORTENTRYDETAILSID"));
		            	dm.setTestReportEntryId(rs.getInt("TESTREPORTENTRYID"));
		            	dm.setTestMasterId(rs.getInt("TESTMASTERID"));
		            	dm.setTestMasterName(rs.getString("TESTMASTERNAME"));
		            	dm.setTestGroupId(rs.getInt("TESTGROUPID"));
		            	dm.setAmount(rs.getDouble("AMOUNT"));
		            	dm.setDiscountPercent(rs.getFloat("DISCOUNTPERCENT"));
		            	dm.setDiscountAmount(rs.getFloat("DISCOUNTAMOUNT"));
		            	dm.setTotAmount(rs.getDouble("TOTAMOUNT"));
		            	dt.add(dm);
		            }
		        	return dt;
		        }
		        	   });
			
		}


}
