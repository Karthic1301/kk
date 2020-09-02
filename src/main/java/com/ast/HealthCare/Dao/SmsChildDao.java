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

import com.ast.main.JpaConfiguration;
import com.ast.HealthCare.Pojo.SmsChildPojo;

@Repository
public class SmsChildDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
int id;
	
	JpaConfiguration jpa = new JpaConfiguration();
	SmsChildDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("SmsChildDao constructor jdbc "+this.jdbcTemplate);
	}
	

	public boolean addSmsChild(final List<SmsChildPojo> smcpojo, int smsid) {
		/*String sid = "Select SMSID  from smsmaster order by SMSID DESC";
		System.out.println(sid);
		jdbcTemplate.query(sid, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs1) throws SQLException, DataAccessException {
				System.out.println("sid kulla");
				if (rs1.next()) {
					System.out.println("sid if kulla");
					id = rs1.getInt("SMSID");
					System.out.println(id);
				} else {
					System.out.println("else");
					String paa = new String();
				}
				return null;
			}
		});*/
		
		String sql1="INSERT INTO smschild (MOBILENO, USERID, SMSMASTERID, USERNAME, USERCATEGORY) values(?,?,?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				for(int i=0;i<smcpojo.size();i++)
				{
					ps.setString(1, smcpojo.get(i).getMobileno());
					ps.setString(2, smcpojo.get(i).getUserid());
					ps.setInt(3, smsid);
					ps.setString(4, smcpojo.get(i).getUsername());
					ps.setString(5, smcpojo.get(i).getUsercategory());
					System.out.println(smcpojo);
				    ps.execute();
				}
				return true;
			    
			}
		});

	}

	public List<SmsChildPojo> smsChildAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM smschild";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<SmsChildPojo>>() {
	        public List<SmsChildPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<SmsChildPojo> list = new  ArrayList<SmsChildPojo>();
	            while(rs.next()) {
	            	SmsChildPojo dt = new SmsChildPojo();
	            	dt.setMobileno(rs.getString("MOBILENO"));
	                dt.setUserid(rs.getString("USERID"));
	                dt.setSmsmasterid(rs.getInt("SMSMASTERID"));
	                dt.setUsername(rs.getString("USERNAME"));
	                dt.setUsercategory(rs.getString("USERCATEGORY"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}
	public boolean SmsChildUpdate(SmsChildPojo smcpojo) {
		// TODO Auto-generated method stub
		System.out.println("vic "+smcpojo);
		
		String query="UPDATE smachild set MOBILENO = ?, USERID = ?,USERNAME = ?,USERCATEGORY = ? where SERIALID = ?";
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, smcpojo.getMobileno());
			ps.setString(2, smcpojo.getUserid());
			ps.setString(3, smcpojo.getUsername());
			ps.setString(4, smcpojo.getUsercategory());
		    return ps.execute();
		}
		});
	}


	public int smsChildDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM SMSCHILD WHERE SMSMASTERID = ?", new Object[] { pid });
	}
	
	public List<SmsChildPojo> smsChildById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM smschild WHERE SMSMASTERID = '"+id+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<SmsChildPojo>>() {
	        public List<SmsChildPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<SmsChildPojo> list = new  ArrayList<SmsChildPojo>();
	            while(rs.next()) {
	            	SmsChildPojo dt = new SmsChildPojo();
	            	dt.setMobileno(rs.getString("MOBILENO"));
	                dt.setUserid(rs.getString("USERID"));
	                dt.setSmsmasterid(rs.getInt("SMSMASTERID"));
	                dt.setUsername(rs.getString("USERNAME"));
	                dt.setUsercategory(rs.getString("USERCATEGORY"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

}
