package com.ast.HealthCare.Dao;

import java.sql.Date;
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

import com.ast.HealthCare.Pojo.LabTestReportEntryPojo;
import com.ast.HealthCare.Pojo.LabTestResultEntryPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class LabTestResultEntryDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	@Autowired()
	LabTestResultEntryDetailsDao detDao;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	LabTestResultEntryDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LabTestResultEntryDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public int getMaximumId()
	{
		String sql="SELECT MAX(TESTRESULTID) AS ID FROM LABTESTRESULTENTRY";
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
	
	public LabTestResultEntryPojo addTestResultEntry(final LabTestResultEntryPojo pojo) {
	      String sql="INSERT INTO LABTESTRESULTENTRY (REPORTNO,REPORTDATE,PATIENTNAME,INDICATIONS)values(?,?,?,?)";
			System.out.println(sql);
			return jdbcTemplate.execute(sql,new PreparedStatementCallback<LabTestResultEntryPojo>(){  
				public LabTestResultEntryPojo doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					ps.setString(1, pojo.getReportNo());
					ps.setDate(2, pojo.getReportDate());
					ps.setString(3, pojo.getPatientName());
					ps.setString(4, pojo.getIndications());
					ps.execute();
					LabTestResultEntryPojo pojo1 = new LabTestResultEntryPojo();
					pojo1 = testResultEntrySearchById(getMaximumId());
					return pojo1;
				}
			});
				}


		public List<LabTestResultEntryPojo> testResultEntryAll() {
			String sql = "SELECT * FROM LABTESTRESULTENTRY";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestResultEntryPojo>>() {
		        public List<LabTestResultEntryPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestResultEntryPojo> list = new  ArrayList<LabTestResultEntryPojo>();
		            while(rs.next()) {
		            	LabTestResultEntryPojo dm = new LabTestResultEntryPojo();
		            	dm.setTestResultId(rs.getInt("TESTRESULTID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setReportDate(rs.getDate("REPORTDATE"));
		            	dm.setPatientName(rs.getString("PATIENTNAME"));
		            	dm.setIndications(rs.getString("INDICATIONS"));
		            	dm.setTestResultDetails(detDao.testResultEntryDetailsSearchByResultId(rs.getInt("TESTRESULTID")));
		               	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}

		
		public List<LabTestResultEntryPojo> testResultEntryByReportDate(Date from,Date to) {
			String sql = "SELECT * FROM LABTESTRESULTENTRY WHERE REPORTDATE BETWEEN '"+ from + "' AND '"+to+"'";
			System.out.println(sql);
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestResultEntryPojo>>() {
		        public List<LabTestResultEntryPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestResultEntryPojo> list = new  ArrayList<LabTestResultEntryPojo>();
		            while(rs.next()) {
		            	LabTestResultEntryPojo dm = new LabTestResultEntryPojo();
		            	dm.setTestResultId(rs.getInt("TESTRESULTID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setReportDate(rs.getDate("REPORTDATE"));
		            	dm.setPatientName(rs.getString("PATIENTNAME"));
		            	dm.setIndications(rs.getString("INDICATIONS"));
		            	dm.setTestResultDetails(detDao.testResultEntryDetailsSearchByResultId(rs.getInt("TESTRESULTID")));
		               	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}
		
		public List<LabTestResultEntryPojo> testResultEntryByReportDateAndTest(Date from,Date to,int testid) {
			String sql = "SELECT * FROM LABTESTRESULTENTRY WHERE REPORTDATE BETWEEN '"+ from + "' AND '"+to+"' AND TESTRESULTID IN (SELECT TESTRESULTID FROM LABTESTRESULTENTRYDETAILS WHERE TESTMASTERID = "+ testid+")";
			System.out.println(sql);
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestResultEntryPojo>>() {
		        public List<LabTestResultEntryPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestResultEntryPojo> list = new  ArrayList<LabTestResultEntryPojo>();
		            while(rs.next()) {
		            	LabTestResultEntryPojo dm = new LabTestResultEntryPojo();
		            	dm.setTestResultId(rs.getInt("TESTRESULTID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setReportDate(rs.getDate("REPORTDATE"));
		            	dm.setPatientName(rs.getString("PATIENTNAME"));
		            	dm.setIndications(rs.getString("INDICATIONS"));
		            	dm.setTestResultDetails(detDao.testResultEntryDetailsSearchByResultId(rs.getInt("TESTRESULTID")));
		               	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}


	public boolean testResultEntryUpdate(final LabTestResultEntryPojo pojo) {
	System.out.println("dao " + pojo);
			
			
			String query="UPDATE LABTESTRESULTENTRY set REPORTNO = ?,REPORTDATE =?,PATIENTNAME =?,INDICATIONS=? where TESTRESULTID = ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, pojo.getReportNo());
				ps.setDate(2, pojo.getReportDate());
				ps.setString(3, pojo.getPatientName());
				ps.setString(4, pojo.getIndications());
				ps.setInt(5, pojo.getTestResultId());
				return ps.execute();
			}
			});
		}


		public int testResultEntryDelete(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTRESULTENTRY WHERE TESTRESULTID = ?", new Object[] { did });
		}


		/*public LabTestResultEntryPojo testResultEntrySearchByName(String name) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTRESULTENTRY WHERE TESTNAME = ?";
			return jdbcTemplate.query(sql,  new Object[] { name }, new ResultSetExtractor<LabTestResultEntryPojo>() {
		        public LabTestResultEntryPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestResultEntryPojo dm = new LabTestResultEntryPojo();
		        	LabTestResultEntryPojo dm1 = new LabTestResultEntryPojo();
		        	
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
		
		public LabTestResultEntryPojo testResultEntrySearchById(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTRESULTENTRY WHERE TESTRESULTID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestResultEntryPojo>() {
		        public LabTestResultEntryPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestResultEntryPojo dm = new LabTestResultEntryPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestResultId(rs.getInt("TESTRESULTID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setReportDate(rs.getDate("REPORTDATE"));
		            	dm.setPatientName(rs.getString("PATIENTNAME"));
		            	dm.setIndications(rs.getString("INDICATIONS"));
		            	dm.setTestResultDetails(detDao.testResultEntryDetailsSearchByResultId(rs.getInt("TESTRESULTID")));
		            }
		        	return dm;
		        }
		        	   });
			
		}

}
