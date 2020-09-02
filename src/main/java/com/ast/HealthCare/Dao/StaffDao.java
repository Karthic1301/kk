package com.ast.HealthCare.Dao;

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

import com.ast.HealthCare.Pojo.StaffPojo;
import com.ast.main.JpaConfiguration;

//CREATE TABLE StaffMaster(STAFFID TEXT,STAFFNAME TEXT,STAFFMOBILE1 TEXT,STAFFMOBILE2 TEXT,STAFFADDRESS1 TEXT,STAFFDDRESS2 TEXT,
//STAFFGENDER TEXT,STAFFDATEOFBIRTH DATE,STAFFEMAILID TEXT,STAFFSERIALID SERIAL,STAFFPHOTO BYTEA,STAFFUNIQUEID TEXT,PRIMARY KEY (STAFFID));
@Repository
public class StaffDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	@Value("${const.staffIdPrefix}")
    String staffcode;
	
	String  sid;
	//protected JdbcTemplate jdbcTemplate;
	JpaConfiguration jpa = new JpaConfiguration();
	StaffDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("StaffDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public String addStaff(final StaffPojo staff) {
		long num;
		   String sql="Select *  from staffmaster order by STAFFID DESC";
		   int check1 = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
		        public Integer extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		               if(rs.next()){
		            	   return rs.getInt("STAFFSERIALID");
		               }else {
		            	   return 0;
		               }
		        }
			  });
		   System.out.println("check "+check1);
			if (check1 != 0) {
				//token = ;
			num = check1 + 1;
			} else {
			num = 1;
		}
			System.out.println("num "+num);
		    System.out.println("max="+num);
	        String  hoscode="AST";
	        String num2;
	        if(num<10)
	        {
	        	num2="0"+staffcode+"0000000"+num;
	        }
	        else if(num<100)
	        {
	        	 num2="0"+staffcode+"000000"+num;
	        }
	        else if(num<1000)
	        {
	        	 num2="0"+staffcode+"00000"+num;
	        }
	        else if(num<10000)
	        {
	        	 num2="0"+staffcode+"00000"+num;
	        }
	        else if(num<100000)
	        {
	        	 num2="0"+staffcode+"0000"+num;
	        }
	        else if(num<1000000)
	        {
	        	 num2="0"+staffcode+"000"+num;
	        }else if(num<10000000)
	        {
	       	 num2="0"+staffcode+"00"+num;
	       }
	       else if(num<100000000)
	        {
	        	num2="0"+staffcode+"0"+num;
	        }
	       else
	    	   num2="0"+staffcode+num;
	       
	        System.out.println("num 2 "+num2);
	        sid=hoscode+num2;


		String sql1="INSERT INTO staffmaster (STAFFID,STAFFNAME,STAFFMOBILE1,STAFFMOBILE2,STAFFADDRESS1,STAFFADDRESS2,STAFFGENDER,STAFFDATEOFBIRTH,STAFFMAILID,STAFFPHOTO,STAFFUNIQUEID,STAFFQUALIFICATION) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<String>(){  
			public String doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, sid);
				ps.setString(2, staff.getStaffName());
			    ps.setString(3, staff.getStaffMobile1());
			    ps.setString(4, staff.getStaffMobile2());
			    ps.setString(5, staff.getStaffAddress1());
				ps.setString(6, staff.getStaffAddress2());
				ps.setString(7, staff.getStaffGender());
				ps.setDate(8, staff.getStaffDOB());
				ps.setString(9, staff.getStaffMailId());
				ps.setString(10, staff.getStaffphoto());
				ps.setString(11, staff.getStaffUniqueId());
				ps.setString(12, staff.getStaffQualification());
				// ps.execute();
				if(ps.execute())
					return "false";
			    else
			    	return sid;
			    	  
			    
			}
		});
	}

	public List<StaffPojo> viewAllStaff() {
		String sql="Select * from staffmaster;";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<StaffPojo>>() {
	        public List<StaffPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<StaffPojo> list = new  ArrayList<StaffPojo>();
	            while(rs.next()) {
	            	System.out.println("oii1");
	            	StaffPojo staff = new StaffPojo();
	            	staff.setStaffId(rs.getString("STAFFID"));
	            	staff.setStaffName(rs.getString("STAFFNAME"));
	            	staff.setStaffMobile1(rs.getString("STAFFMOBILE1"));
	            	staff.setStaffMobile2(rs.getString("STAFFMOBILE2"));
	            	staff.setStaffAddress1(rs.getString("STAFFADDRESS1"));
	            	staff.setStaffAddress2(rs.getString("STAFFADDRESS2"));
	            	staff.setStaffGender(rs.getString("STAFFGENDER"));
	            	staff.setStaffDOB(rs.getDate("STAFFDATEOFBIRTH"));
	            	staff.setStaffMailId(rs.getString("STAFFMAILID"));
	            	staff.setStaffSerialId(rs.getInt("STAFFSERIALID"));
	            	staff.setStaffphoto(rs.getString("STAFFPHOTO"));
	            	staff.setStaffUniqueId(rs.getString("STAFFUNIQUEID"));
	            	staff.setStaffQualification(rs.getString("STAFFQUALIFICATION"));
	            //	staff.setStaffUserName(rs.getString("STAFFUSERNAME"));
	            //	staff.setStaffPassword(rs.getString("STAFFPASSWORD"));
	            	list.add(staff);
	                
	            }
	            return list;
	            
	            }
	    });
	
		// TODO Auto-generated method stub
		// return null;
	}

	public StaffPojo staffSearchById(String sid) {
		String sql = "SELECT * FROM staffmaster WHERE STAFFID = ?";
		return jdbcTemplate.query(sql,  new Object[] { sid }, new ResultSetExtractor<StaffPojo>() {
	        public StaffPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	//List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	StaffPojo staff = new StaffPojo();
	        	while(rs.next()) {
	            	System.out.println("oii1");
	            	staff.setStaffId(rs.getString("STAFFID"));
	            	staff.setStaffName(rs.getString("STAFFNAME"));
	            	staff.setStaffMobile1(rs.getString("STAFFMOBILE1"));
	            	staff.setStaffMobile2(rs.getString("STAFFMOBILE2"));
	            	staff.setStaffAddress1(rs.getString("STAFFADDRESS1"));
	            	staff.setStaffAddress2(rs.getString("STAFFADDRESS2"));
	            	staff.setStaffGender(rs.getString("STAFFGENDER"));
	            	staff.setStaffDOB(rs.getDate("STAFFDATEOFBIRTH"));
	            	staff.setStaffMailId(rs.getString("STAFFMAILID"));
	            	staff.setStaffSerialId(rs.getInt("STAFFSERIALID"));
	            	staff.setStaffphoto(rs.getString("STAFFPHOTO"));
	            	staff.setStaffUniqueId(rs.getString("STAFFUNIQUEID"));
	            	staff.setStaffQualification(rs.getString("STAFFQUALIFICATION"));
	            //	staff.setStaffUserName(rs.getString("STAFFUSERNAME"));
	            //	staff.setStaffPassword(rs.getString("STAFFPASSWORD"));
	            	
	        	}
	            return staff;
	            
	            }
	    });

	}

	public List<StaffPojo> staffSearchByName(String sname) {
		String sql = "SELECT * FROM staffmaster WHERE STAFFNAME LIKE ? ";
		return jdbcTemplate.query(sql,  new Object[] { sname }, new ResultSetExtractor<List<StaffPojo>>() {
	        public List<StaffPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<StaffPojo> list = new  ArrayList<StaffPojo>();
	        	
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                StaffPojo staff = new StaffPojo();
	                staff.setStaffId(rs.getString("STAFFID"));
	            	staff.setStaffName(rs.getString("STAFFNAME"));
	            	staff.setStaffMobile1(rs.getString("STAFFMOBILE1"));
	            	staff.setStaffMobile2(rs.getString("STAFFMOBILE2"));
	            	staff.setStaffAddress1(rs.getString("STAFFADDRESS1"));
	            	staff.setStaffAddress2(rs.getString("STAFFADDRESS2"));
	            	staff.setStaffGender(rs.getString("STAFFGENDER"));
	            	staff.setStaffDOB(rs.getDate("STAFFDATEOFBIRTH"));
	            	staff.setStaffMailId(rs.getString("STAFFMAILID"));
	            	staff.setStaffSerialId(rs.getInt("STAFFSERIALID"));
	            	staff.setStaffphoto(rs.getString("STAFFPHOTO"));
	            	staff.setStaffQualification(rs.getString("STAFFQUALIFICATION"));
	            	staff.setStaffUniqueId(rs.getString("STAFFUNIQUEID"));
	            	list.add(staff);
	                
	            }
	            return list;
	            
	            }
	    });

	}

	public List<StaffPojo> staffSearchByPhoneNo(String sphone) {
		String sql = "SELECT * FROM staffmaster WHERE STAFFMOBILE1 LIKE ? OR STAFFMOBILE2 LIKE ?";
		return jdbcTemplate.query(sql,  new Object[] { sphone , sphone }, new ResultSetExtractor<List<StaffPojo>>() {
	        public List<StaffPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<StaffPojo> list = new  ArrayList<StaffPojo>();
	        	
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                StaffPojo staff = new StaffPojo();
	                staff.setStaffId(rs.getString("STAFFID"));
	            	staff.setStaffName(rs.getString("STAFFNAME"));
	            	staff.setStaffMobile1(rs.getString("STAFFMOBILE1"));
	            	staff.setStaffMobile2(rs.getString("STAFFMOBILE2"));
	            	staff.setStaffAddress1(rs.getString("STAFFADDRESS1"));
	            	staff.setStaffAddress2(rs.getString("STAFFADDRESS2"));
	            	staff.setStaffGender(rs.getString("STAFFGENDER"));
	            	staff.setStaffDOB(rs.getDate("STAFFDATEOFBIRTH"));
	            	staff.setStaffMailId(rs.getString("STAFFMAILID"));
	            	staff.setStaffSerialId(rs.getInt("STAFFSERIALID"));
	            	staff.setStaffphoto(rs.getString("STAFFPHOTO"));
	            	staff.setStaffQualification(rs.getString("STAFFQUALIFICATION"));
	            	staff.setStaffUniqueId(rs.getString("STAFFUNIQUEID"));
	            	list.add(staff);
	                
	            }
	            return list;
	            
	            }
	    });

	}

	public int staffDelete(String sid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM staffmaster WHERE STAFFID = ?", new Object[] { sid });
	}
	//CREATE TABLE StaffMaster(STAFFID TEXT,STAFFNAME TEXT,STAFFMOBILE1 TEXT,STAFFMOBILE2 TEXT,STAFFADDRESS1 TEXT,STAFFDDRESS2 TEXT,
	//STAFFGENDER TEXT,STAFFDATEOFBIRTH DATE,STAFFEMAILID TEXT,STAFFSERIALID SERIAL,STAFFPHOTO BYTEA,STAFFUNIQUEID TEXT,PRIMARY KEY (STAFFID));

	public boolean staffUpdate(final StaffPojo staff) {
		// TODO Auto-generated method stub
		System.out.println(staff);
		String query = "UPDATE staffmaster set STAFFNAME = ?, STAFFMOBILE1 = ?, STAFFMOBILE2 = ?, STAFFADDRESS1 = ?, STAFFADDRESS2 = ?,"
				+ " STAFFGENDER = ?, STAFFDATEOFBIRTH = ?, STAFFMAILID = ?, STAFFPHOTO = ?, STAFFUNIQUEID = ?, STAFFQUALIFICATION = ? WHERE STAFFID = ?"; 
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1,staff.getStaffName());
				ps.setString(2,staff.getStaffMobile1());
				ps.setString(3,staff.getStaffMobile2());
				ps.setString(4,staff.getStaffAddress1());
				ps.setString(5,staff.getStaffAddress2());
				ps.setString(6,staff.getStaffGender());
				ps.setDate(7,staff.getStaffDOB());
				ps.setString(8,staff.getStaffMailId());
				ps.setString(9,staff.getStaffphoto());
				ps.setString(10,staff.getStaffUniqueId());
				ps.setString(11,staff.getStaffQualification());
				ps.setString(12,staff.getStaffId());
				return ps.execute();
			}
			});
	}

	public List<StaffPojo> staffSearchByAll(String all) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM staffmaster WHERE STAFFNAME LIKE ? or STAFFID = ? or STAFFMOBILE1 LIKE ? or STAFFMOBILE2 LIKE ?";
		return jdbcTemplate.query(sql,  new Object[] { all,Integer.parseInt(all),all,all }, new ResultSetExtractor<List<StaffPojo>>() {
	        public List<StaffPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<StaffPojo> list = new  ArrayList<StaffPojo>();
	        	
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                StaffPojo staff = new StaffPojo();
	                staff.setStaffId(rs.getString("STAFFID"));
	            	staff.setStaffName(rs.getString("STAFFNAME"));
	            	staff.setStaffMobile1(rs.getString("STAFFMOBILE1"));
	            	staff.setStaffMobile2(rs.getString("STAFFMOBILE2"));
	            	staff.setStaffAddress1(rs.getString("STAFFADDRESS1"));
	            	staff.setStaffAddress2(rs.getString("STAFFADDRESS2"));
	            	staff.setStaffGender(rs.getString("STAFFGENDER"));
	            	staff.setStaffDOB(rs.getDate("STAFFDATEOFBIRTH"));
	            	staff.setStaffMailId(rs.getString("STAFFMAILID"));
	            	staff.setStaffSerialId(rs.getInt("STAFFSERIALID"));
	            	staff.setStaffphoto(rs.getString("STAFFPHOTO"));
	            	staff.setStaffQualification(rs.getString("STAFFQUALIFICATION"));
	            	staff.setStaffUniqueId(rs.getString("STAFFUNIQUEID"));
	            	list.add(staff);
	                
	            }
	            return list;
	            
	            }
	    });

	}

	public List<StaffPojo> staffSearchByAllForSMS(String all) {
		// TODO Auto-generated method stub
		String sql = "SELECT STAFFID,STAFFNAME,STAFFMOBILE1 FROM staffmaster WHERE STAFFNAME LIKE ? or STAFFID = ? or STAFFMOBILE1 LIKE ? or STAFFMOBILE2 LIKE ?";
		return jdbcTemplate.query(sql,  new Object[] { all,all,all,all }, new ResultSetExtractor<List<StaffPojo>>() {
	        public List<StaffPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<StaffPojo> list = new  ArrayList<StaffPojo>();
	        	
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                StaffPojo staff = new StaffPojo();
	                staff.setStaffId(rs.getString("STAFFID"));
	            	staff.setStaffName(rs.getString("STAFFNAME"));
	            	staff.setStaffMobile1(rs.getString("STAFFMOBILE1"));
	            	list.add(staff);
	                
	            }
	            return list;
	            
	            }
	    });

	}


}
