package com.ast.HealthCare.Dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.LabTestReportEntryPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class LabTestReportEntryDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	@Autowired()
	LabTestReportEntryDetailsDao detDao;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	@Value("${const.dataBaseName}")
    private String dbName;
	
	LabTestReportEntryDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LabTestReportEntryDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public int getMaximumId()
	{
		String sql="SELECT MAX(TESTREPORTENTRYID) AS ID FROM LABTESTREPORTENTRY";
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
	
	public int getAutoGenerateReportNo()
	{
		int count=0;
		String sqlauto="SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'LABTESTREPORTENTRY' AND TABLE_SCHEMA ='"+ dbName +"'";
		//String sqlauto="select MAX(TESTREPORTENTRYID) AS MAXID FROM LABTESTREPORTENTRY";
		int autoIncrementValue = jdbcTemplate.queryForObject(sqlauto,Integer.class);
		
		{
		String sql="SELECT COUNT(*) FROM LABTESTREPORTENTRY WHERE TESTREPORTENTRYID="+autoIncrementValue;
		count= jdbcTemplate.queryForObject(sql,Integer.class);
		System.out.println("count"+count);
		if(count>0)
		{
			autoIncrementValue=autoIncrementValue+1;
			System.out.println("autoIncrementValue"+autoIncrementValue);
		}
		}while(count>0);
		System.out.println("Value"+autoIncrementValue);
		return autoIncrementValue;
		
	}

	
	public LabTestReportEntryPojo addTestReportEntry(final LabTestReportEntryPojo pojo) {
	      String sql="INSERT INTO LABTESTREPORTENTRY (REPORTNO,REPORTDATE,DOCTORID,DOCTORNAME,PATIENTID,PATIENTFIRSTNAME,FINALTOTAMOUNT,FINALDISCOUNTPERCENT,PAIDAMOUNT,NETAMOUNT,BALANCE,STATUS,ISSUEDATE)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			return jdbcTemplate.execute(sql,new PreparedStatementCallback<LabTestReportEntryPojo>(){  
				public LabTestReportEntryPojo doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					ps.setString(1, pojo.getReportNo());
					ps.setDate(2, pojo.getReportDate());
					ps.setString(3, pojo.getDoctorId());
					ps.setString(4, pojo.getDoctorName());
					ps.setString(5,pojo.getPatientId());
					ps.setString(6, pojo.getPatientFirstName());
					ps.setDouble(7, pojo.getFinalTotAmount());
					ps.setDouble(8,pojo.getFinaldiscountPercent());
					ps.setDouble(9, pojo.getPaidAmount());
					ps.setDouble(10,pojo.getNetAmount());
					ps.setDouble(11,pojo.getBalance());
					ps.setString(12,"NOTISSUED");
					ps.setDate(13,pojo.getIssueDate());
					ps.execute();
					LabTestReportEntryPojo pojo1 = new LabTestReportEntryPojo();
					pojo1 = testReportEntrySearchById(getMaximumId());
					pojo1.setReportNo(getMaximumId()+"/19");
					return pojo1;
				}
			});
				}


		public List<LabTestReportEntryPojo> testReportEntryAll() {
			String sql = "SELECT * FROM LABTESTREPORTENTRY";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestReportEntryPojo>>() {
		        public List<LabTestReportEntryPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestReportEntryPojo> list = new  ArrayList<LabTestReportEntryPojo>();
		            while(rs.next()) {
		            	LabTestReportEntryPojo dm = new LabTestReportEntryPojo();
		            	dm.setTestReportEntryId(rs.getInt("TESTREPORTENTRYID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setReportDate(rs.getDate("REPORTDATE"));
		            	dm.setDoctorId(rs.getString("DOCTORID"));
		            	dm.setDoctorName(rs.getString("DOCTORNAME"));
		               	dm.setPatientId(rs.getString("PATIENTID"));
		               	dm.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
		               	dm.setFinalTotAmount(rs.getDouble("FINALTOTAMOUNT"));
		               	dm.setFinaldiscountPercent(rs.getDouble("FINALDISCOUNTPERCENT"));
		               	dm.setPaidAmount(rs.getDouble("PAIDAMOUNT"));
		               	dm.setNetAmount(rs.getDouble("NETAMOUNT"));
		               	dm.setBalance(rs.getDouble("BALANCE"));
		               	dm.setStatus(rs.getString("STATUS"));
		               	dm.setIssueDate(rs.getDate("ISSUEDATE"));
		               	dm.setLabTestEntryReportDetail(detDao.testReportEntryDetailsSearchByReportId(rs.getInt("TESTREPORTENTRYID")));
		               	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}

		public List<LabTestReportEntryPojo> testReportEntryNotIssuedStatus() {
			String sql = "SELECT * FROM LABTESTREPORTENTRY WHERE STATUS != 'ISSUED'";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestReportEntryPojo>>() {
		        public List<LabTestReportEntryPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestReportEntryPojo> list = new  ArrayList<LabTestReportEntryPojo>();
		            while(rs.next()) {
		            	LabTestReportEntryPojo dm = new LabTestReportEntryPojo();
		            	dm.setTestReportEntryId(rs.getInt("TESTREPORTENTRYID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setReportDate(rs.getDate("REPORTDATE"));
		            	dm.setDoctorId(rs.getString("DOCTORID"));
		            	dm.setDoctorName(rs.getString("DOCTORNAME"));
		               	dm.setPatientId(rs.getString("PATIENTID"));
		               	dm.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
		               	dm.setFinalTotAmount(rs.getDouble("FINALTOTAMOUNT"));
		               	dm.setFinaldiscountPercent(rs.getDouble("FINALDISCOUNTPERCENT"));
		               	dm.setPaidAmount(rs.getDouble("PAIDAMOUNT"));
		               	dm.setNetAmount(rs.getDouble("NETAMOUNT"));
		               	dm.setBalance(rs.getDouble("BALANCE"));
		               	dm.setStatus(rs.getString("STATUS"));
		               	dm.setIssueDate(rs.getDate("ISSUEDATE"));
		               	dm.setLabTestEntryReportDetail(detDao.testReportEntryDetailsSearchByReportId(rs.getInt("TESTREPORTENTRYID")));
		               	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}
		
		public List<LabTestReportEntryPojo> testReportEntryListWithoutResult() {
			String sql = "SELECT * FROM LABTESTREPORTENTRY WHERE REPORTNO NOT IN(SELECT REPORTNO FROM LABTESTRESULTENTRY)";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestReportEntryPojo>>() {
		        public List<LabTestReportEntryPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestReportEntryPojo> list = new  ArrayList<LabTestReportEntryPojo>();
		            while(rs.next()) {
		            	LabTestReportEntryPojo dm = new LabTestReportEntryPojo();
		            	dm.setTestReportEntryId(rs.getInt("TESTREPORTENTRYID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setReportDate(rs.getDate("REPORTDATE"));
		            	dm.setDoctorId(rs.getString("DOCTORID"));
		            	dm.setDoctorName(rs.getString("DOCTORNAME"));
		               	dm.setPatientId(rs.getString("PATIENTID"));
		               	dm.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
		               	dm.setFinalTotAmount(rs.getDouble("FINALTOTAMOUNT"));
		               	dm.setFinaldiscountPercent(rs.getDouble("FINALDISCOUNTPERCENT"));
		               	dm.setPaidAmount(rs.getDouble("PAIDAMOUNT"));
		               	dm.setNetAmount(rs.getDouble("NETAMOUNT"));
		               	dm.setBalance(rs.getDouble("BALANCE"));
		               	dm.setStatus(rs.getString("STATUS"));
		               	dm.setIssueDate(rs.getDate("ISSUEDATE"));
		               	dm.setLabTestEntryReportDetail(detDao.testReportEntryDetailsSearchByReportId(rs.getInt("TESTREPORTENTRYID")));
		               	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}

		public List<LabTestReportEntryPojo> testReportEntryListByIssueDate(Date from,Date to) {
			String sql = "SELECT * FROM LABTESTREPORTENTRY WHERE ISSUEDATE BETWEEN '"+ from + "' AND '"+ to + "'";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabTestReportEntryPojo>>() {
		        public List<LabTestReportEntryPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabTestReportEntryPojo> list = new  ArrayList<LabTestReportEntryPojo>();
		            while(rs.next()) {
		            	LabTestReportEntryPojo dm = new LabTestReportEntryPojo();
		            	dm.setTestReportEntryId(rs.getInt("TESTREPORTENTRYID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setReportDate(rs.getDate("REPORTDATE"));
		            	dm.setDoctorId(rs.getString("DOCTORID"));
		            	dm.setDoctorName(rs.getString("DOCTORNAME"));
		               	dm.setPatientId(rs.getString("PATIENTID"));
		               	dm.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
		               	dm.setFinalTotAmount(rs.getDouble("FINALTOTAMOUNT"));
		               	dm.setFinaldiscountPercent(rs.getDouble("FINALDISCOUNTPERCENT"));
		               	dm.setPaidAmount(rs.getDouble("PAIDAMOUNT"));
		               	dm.setNetAmount(rs.getDouble("NETAMOUNT"));
		               	dm.setBalance(rs.getDouble("BALANCE"));
		               	dm.setStatus(rs.getString("STATUS"));
		               	dm.setIssueDate(rs.getDate("ISSUEDATE"));
		               	dm.setLabTestEntryReportDetail(detDao.testReportEntryDetailsSearchByReportId(rs.getInt("TESTREPORTENTRYID")));
		               	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}
		

	public boolean testReportEntryUpdate(final LabTestReportEntryPojo pojo) {
	System.out.println("dao " + pojo);
			
			
			String query="UPDATE LABTESTREPORTENTRY set REPORTNO = ?,REPORTDATE=?,DOCTORID=?,DOCTORNAME=?,PATIENTID=?,PATIENTFIRSTNAME=?,FINALTOTAMOUNT=?,FINALDISCOUNTPERCENT=?,PAIDAMOUNT=?,NETAMOUNT=?,BALANCE=?,STATUS=?,ISSUEDATE=? where TESTREPORTENTRYID = ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, pojo.getReportNo());
				ps.setDate(2, pojo.getReportDate());
				ps.setString(3, pojo.getDoctorId());
				ps.setString(4, pojo.getDoctorName());
				ps.setString(5,pojo.getPatientId());
				ps.setString(6, pojo.getPatientFirstName());
				ps.setDouble(7, pojo.getFinalTotAmount());
				ps.setDouble(8,pojo.getFinaldiscountPercent());
				ps.setDouble(9, pojo.getPaidAmount());
				ps.setDouble(10,pojo.getNetAmount());
				ps.setDouble(11,pojo.getBalance());
				ps.setString(12, pojo.getStatus());
				ps.setDate(13,pojo.getIssueDate());
				ps.setInt(14,pojo.getTestReportEntryId());
				return ps.execute();
			}
			});
		}
	
	
	public boolean testReportEntryUpdateBalance(final LabTestReportEntryPojo pojo) {
		System.out.println("dao " + pojo);
				
				
				String query="UPDATE LABTESTREPORTENTRY set ISSUEDATE=?,STATUS=?, BALANCE=? where REPORTNO = ?";

				return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
				public Boolean doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					ps.setDate(1, pojo.getIssueDate());
					ps.setString(2, pojo.getStatus());
					ps.setDouble(3,pojo.getBalance());
					ps.setString(4,pojo.getReportNo());
					return ps.execute();
				}
				});
			}


		public int testReportEntryDelete(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABTESTREPORTENTRY WHERE TESTREPORTENTRYID = ?", new Object[] { did });
		}


		/*public LabTestReportEntryPojo testReportEntrySearchByName(String name) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTREPORTENTRY WHERE TESTNAME = ?";
			return jdbcTemplate.query(sql,  new Object[] { name }, new ReportSetExtractor<LabTestReportEntryPojo>() {
		        public LabTestReportEntryPojo extractData(ReportSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestReportEntryPojo dm = new LabTestReportEntryPojo();
		        	LabTestReportEntryPojo dm1 = new LabTestReportEntryPojo();
		        	
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
		
		public LabTestReportEntryPojo testReportEntrySearchById(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTREPORTENTRY WHERE TESTREPORTENTRYID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabTestReportEntryPojo>() {
		        public LabTestReportEntryPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestReportEntryPojo dm = new LabTestReportEntryPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestReportEntryId(rs.getInt("TESTREPORTENTRYID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setReportDate(rs.getDate("REPORTDATE"));
		            	dm.setDoctorId(rs.getString("DOCTORID"));
		            	dm.setDoctorName(rs.getString("DOCTORNAME"));
		               	dm.setPatientId(rs.getString("PATIENTID"));
		               	dm.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
		               	dm.setFinalTotAmount(rs.getDouble("FINALTOTAMOUNT"));
		               	dm.setFinaldiscountPercent(rs.getDouble("FINALDISCOUNTPERCENT"));
		               	dm.setPaidAmount(rs.getDouble("PAIDAMOUNT"));
		               	dm.setNetAmount(rs.getDouble("NETAMOUNT"));
		               	dm.setBalance(rs.getDouble("BALANCE"));
		               	dm.setStatus(rs.getString("STATUS"));
		               	dm.setIssueDate(rs.getDate("ISSUEDATE"));
		               	dm.setLabTestEntryReportDetail(detDao.testReportEntryDetailsSearchByReportId(rs.getInt("TESTREPORTENTRYID")));
		            }
		        	return dm;
		        }
		        	   });
			
		}
		
		public LabTestReportEntryPojo testReportEntrySearchByReportNo(String no) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABTESTREPORTENTRY WHERE REPORTNO = ?";
			return jdbcTemplate.query(sql,  new Object[] { no }, new ResultSetExtractor<LabTestReportEntryPojo>() {
		        public LabTestReportEntryPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabTestReportEntryPojo dm = new LabTestReportEntryPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setTestReportEntryId(rs.getInt("TESTREPORTENTRYID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setReportDate(rs.getDate("REPORTDATE"));
		            	dm.setDoctorId(rs.getString("DOCTORID"));
		            	dm.setDoctorName(rs.getString("DOCTORNAME"));
		               	dm.setPatientId(rs.getString("PATIENTID"));
		               	dm.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
		               	dm.setFinalTotAmount(rs.getDouble("FINALTOTAMOUNT"));
		               	dm.setFinaldiscountPercent(rs.getDouble("FINALDISCOUNTPERCENT"));
		               	dm.setPaidAmount(rs.getDouble("PAIDAMOUNT"));
		               	dm.setNetAmount(rs.getDouble("NETAMOUNT"));
		               	dm.setBalance(rs.getDouble("BALANCE"));
		               	dm.setStatus(rs.getString("STATUS"));
		               	dm.setIssueDate(rs.getDate("ISSUEDATE"));
		               	dm.setLabTestEntryReportDetail(detDao.testReportEntryDetailsSearchByReportId(rs.getInt("TESTREPORTENTRYID")));
		            }
		        	return dm;
		        }
		        	   });
			
		}

}
