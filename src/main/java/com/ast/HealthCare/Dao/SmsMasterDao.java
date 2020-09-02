package com.ast.HealthCare.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.BillDetailPojo;
import com.ast.HealthCare.Pojo.SmsChildPojo;
import com.ast.HealthCare.Pojo.SmsMasterPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class SmsMasterDao {
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	@Autowired
	SmsChildDao scdao;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	SmsMasterDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("SmsMasterDao constructor jdbc "+this.jdbcTemplate);
	}
	public int getMaxId() {
		String sql = "SELECT SMSID FROM smsmaster order by SMSID DESC";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
					public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
						if (rs.next()) {
							return rs.getInt("PRESCRIPTIONID");
						} else {
							return 0;
						}
					}
				});
	}
	public int addsmsmaster(final SmsMasterPojo smspojo) {
		// TODO Auto-generated method stub
		System.out.println("addsmsmasterdao");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
		cal.get(Calendar.DATE);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(cal.getTime());
		java.util.Date fd3 = null;
		try {
			fd3 = format1.parse(formatted);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date currentDate = new java.sql.Date(fd3.getTime());
		// Output "2012-09-26"
		System.out.println(currentDate);
		String sql1="INSERT INTO smsmaster (CREATEDATE,SMSNAME, SMSCONTENT,REPEATMODE,SENDDATE,NEXTSMSDATE,SENDTIME,REPEATCATEGORY,SMSSTATUS,SMSTYPE,PATIENTSELECTIONSTATUS,DOCTORSELECTIONSTATUS,STAFFSELECTIONSTATUS) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Integer>(){  
			public Integer doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setDate(1, currentDate);
				ps.setString(2, smspojo.getSmsname());
				ps.setString(3, smspojo.getSmscontent());
				ps.setString(4, smspojo.getRepeatemode());
				ps.setDate(5, smspojo.getSenddate());
				ps.setDate(6, smspojo.getNextsmsdate());
				ps.setString(7, smspojo.getSendtime());
				ps.setString(8, smspojo.getRepeatecategory());
				ps.setInt(9, smspojo.getSmsstatus());
				ps.setString(10, smspojo.getSmstype());
				ps.setInt(11, smspojo.getPatientselectionstatus());
				ps.setInt(12, smspojo.getDoctorselectionstatus());
				ps.setInt(13, smspojo.getStaffselectionstatus());
				ps.executeQuery();
				//ResultSet rs = ps.executeQuery();
				//rs.next();
				System.out.println("Serial id");
			    return getMaxId();
			}
		});

	}
	
	public List<SmsMasterPojo> SmsMasterAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM smsmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<SmsMasterPojo>>() {
	        public List<SmsMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<SmsMasterPojo> list = new  ArrayList<SmsMasterPojo>();
	            while(rs.next()) {
	            	SmsMasterPojo dt = new SmsMasterPojo();
	            	dt.setSmsserialid(rs.getInt("SMSID"));
	            	dt.setSmsname(rs.getString("SMSNAME"));
	            	dt.setCretaedate(rs.getDate("CREATEDATE"));
	                dt.setSmscontent(rs.getString("SMSCONTENT"));
	                dt.setRepeatemode(rs.getString("REPEATMODE"));
	                dt.setSenddate(rs.getDate("SENDDATE"));
	                dt.setNextsmsdate(rs.getDate("NEXTSMSDATE"));
	                dt.setSendtime(rs.getString("SENDTIME"));
	                dt.setRepeatecategory(rs.getString("REPEATCATEGORY"));
	                dt.setSmsstatus(rs.getInt("SMSSTATUS"));
	                dt.setSmstype(rs.getString("SMSTYPE"));
	                dt.setPatientselectionstatus(rs.getInt("PATIENTSELECTIONSTATUS"));
	                dt.setDoctorselectionstatus(rs.getInt("DOCTORSELECTIONSTATUS"));
	                dt.setStaffselectionstatus(rs.getInt("STAFFSELECTIONSTATUS"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}
	public int smsMasterDelete(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM smsmaster WHERE SMSID = ?", new Object[] { id });
	}

	public boolean smsMasterUpdate(final SmsMasterPojo smspojo) {
		// TODO Auto-generated method stub
		System.out.println("vic "+smspojo);
		
		String query="UPDATE smsmaster set  SMSNAME = ?, SMSCONTENT = ?, REPEATMODE = ?, SENDDATE = ?, NEXTSMSDATE = ?, SENDTIME = ?, REPEATCATEGORY = ?, SMSSTATUS = ?,SMSTYPE =?,PATIENTSELECTIONSTATUS = ?,DOCTORSELECTIONSTATUS = ?, STAFFSELECTIONSTATUS = ? where SMSID = ?";
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, smspojo.getSmsname());
			ps.setString(2, smspojo.getSmscontent());
			ps.setString(3, smspojo.getRepeatemode());
			ps.setDate(4, smspojo.getSenddate());
			ps.setDate(5, smspojo.getNextsmsdate());
			ps.setString(6, smspojo.getSendtime());
			ps.setString(7, smspojo.getRepeatecategory());
			ps.setInt(8, smspojo.getSmsstatus());
			ps.setString(9, smspojo.getSmstype());
			ps.setInt(10, smspojo.getPatientselectionstatus());
			ps.setInt(11, smspojo.getDoctorselectionstatus());
			ps.setInt(12, smspojo.getStaffselectionstatus());
			ps.setInt(13, smspojo.getSmsserialid());
			
		    return ps.execute();
		}
		});
	}

	public SmsMasterPojo smsMasterById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM smsmaster where SMSID = '"+id+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<SmsMasterPojo>() {
	        public SmsMasterPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	if (rs.next()) {
	            	SmsMasterPojo dt = new SmsMasterPojo();
	            	dt.setSmsserialid(rs.getInt("SMSID"));
	            	dt.setSmsname(rs.getString("SMSNAME"));
	            	dt.setCretaedate(rs.getDate("CREATEDATE"));
	                dt.setSmscontent(rs.getString("SMSCONTENT"));
	                dt.setRepeatemode(rs.getString("REPEATMODE"));
	                dt.setSenddate(rs.getDate("SENDDATE"));
	                dt.setNextsmsdate(rs.getDate("NEXTSMSDATE"));
	                dt.setSendtime(rs.getString("SENDTIME"));
	                dt.setRepeatecategory(rs.getString("REPEATCATEGORY"));
	                dt.setSmsstatus(rs.getInt("SMSSTATUS"));
	                dt.setSmstype(rs.getString("SMSTYPE"));
	                dt.setPatientselectionstatus(rs.getInt("PATIENTSELECTIONSTATUS"));
	                dt.setDoctorselectionstatus(rs.getInt("DOCTORSELECTIONSTATUS"));
	                dt.setStaffselectionstatus(rs.getInt("STAFFSELECTIONSTATUS"));
	            return dt;
	        	}
				return null;
	            }
	    });
	}

}
