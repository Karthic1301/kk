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

import com.ast.HealthCare.Pojo.LabReportPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class LabReportDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	LabReportDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LabReportDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addReport(final LabReportPojo pojo) {
	      String sql="INSERT INTO LABREPORT (REPORTNO,PATIENTNO,PATIENTNAME,BALANCE,ISSUEDATE) values(?,?,?,?,?)";
			
			return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>(){  
				public Boolean doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					ps.setString(1, pojo.getReportNo());
					ps.setString(2, pojo.getPatientNo());
					ps.setString(3,pojo.getPatientName());
					ps.setDouble(4,pojo.getBalance());
					ps.setDate(5,pojo.getIssueDate());
					return ps.execute();
				}
			});
				}


		public List<LabReportPojo> ReportAll() {
			String sql = "SELECT * FROM LABREPORT";
			return jdbcTemplate.query(sql, new ResultSetExtractor<List<LabReportPojo>>() {
		        public List<LabReportPojo> extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	List<LabReportPojo> list = new  ArrayList<LabReportPojo>();
		            while(rs.next()) {
		            	LabReportPojo dm = new LabReportPojo();
		            	dm.setReportId(rs.getInt("REPORTID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setPatientNo(rs.getString("PATIENTNO"));
		            	dm.setPatientName(rs.getString("PATIENTNAME"));
		            	dm.setBalance(rs.getDouble("BALANCE"));
		            	dm.setIssueDate(rs.getDate("ISSUEDATE"));
		            	list.add(dm);
		            }
		            return list;
		            
		            }
		    });
	}


	public boolean ReportUpdate(final LabReportPojo pojo) {
	System.out.println("dao " + pojo);
			
			
			String query="UPDATE LABREPORT set REPORTNO=?,PATIENTNO=?,PATIENTNAME=?,BALANCE=?,ISSUEDATE=? where REPORTID = ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, pojo.getReportNo());
				ps.setString(2, pojo.getPatientNo());
				ps.setString(3,pojo.getPatientName());
				ps.setDouble(4,pojo.getBalance());
				ps.setDate(5,pojo.getIssueDate());
				ps.setInt(6, pojo.getReportId());
				return ps.execute();
			}
			});
		}


		public int ReportDelete(int did) {
			// TODO Auto-generated method stub
			return jdbcTemplate.update("DELETE FROM LABREPORT WHERE REPORTID = ?", new Object[] { did });
		}


		/*public LabReportPojo ReportSearchByName(String name) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABReport WHERE TESTNAME = ?";
			return jdbcTemplate.query(sql,  new Object[] { name }, new ResultSetExtractor<LabReportPojo>() {
		        public LabReportPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabReportPojo dm = new LabReportPojo();
		        	LabReportPojo dm1 = new LabReportPojo();
		        	
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
		
		public LabReportPojo ReportSearchById(int id) {
			// TODO Auto-generated method stub
			String sql = "SELECT * FROM LABREPORT WHERE REPORTID = ?";
			return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<LabReportPojo>() {
		        public LabReportPojo extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	LabReportPojo dm = new LabReportPojo();
		        	
		        	if(rs.next()) {
		            	System.out.println("oii1");    
		            	dm.setReportId(rs.getInt("REPORTID"));
		            	dm.setReportNo(rs.getString("REPORTNO"));
		            	dm.setPatientNo(rs.getString("PATIENTNO"));
		            	dm.setPatientName(rs.getString("PATIENTNAME"));
		            	dm.setBalance(rs.getDouble("BALANCE"));
		            	dm.setIssueDate(rs.getDate("ISSUEDATE"));
		            }
		        	return dm;
		        }
		        	   });
			
		}

}
