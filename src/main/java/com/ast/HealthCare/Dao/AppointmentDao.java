package com.ast.HealthCare.Dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.AppointmentJsonPojo;
import com.ast.HealthCare.Pojo.AppointmentPojo;
import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.PatientWaitingListPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class AppointmentDao {

//	CREATE TABLE appointment(APPOINTMENTID SERIAL,CONSULTINGDOCTORID TEXT,APPOINTMENTDATE DATE,
//	APPOINTMENTSTARTTIME TIME,APPOINTMENTENDTIME TIME,PATIENTID TEXT,TOKENNO INTEGER,APPOINTMENTSTATUS TEXT,PRIMARY KEY(APPOINTMENTID));
	//String dkdk;
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	@Autowired
	PatientDao pdao;
	
	@Autowired
	MessageDao msg;
	
	//protected JdbcTemplate jdbcTemplate;
	JpaConfiguration jpa = new JpaConfiguration();
	AppointmentDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("AppointmentDao constructor jdbc "+this.jdbcTemplate);
	}
	
	
	public Boolean insertAppointment(final List<AppointmentPojo> appPojo) {
		System.out.println("dao size da "+appPojo.size());
		for(int i=0;i<appPojo.size();i++)
		{
		final int token;
		final String status = "V";
		String sql = "SELECT * FROM appointment where CONSULTINGDOCTORID = ? AND APPOINTMENTDATE = ? order by TOKENNO DESC";
		int check1 = jdbcTemplate.query(sql,  new Object[] { appPojo.get(i).getConsiultingDoctorId(), appPojo.get(i).getAppointmentDate() }, new ResultSetExtractor<Integer>() {
        public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
	               if(rs.next()){
	            	   return rs.getInt("TOKENNO");
	               }else {
	            	   return 0;
	               }
	        }
		  });
		if (check1 != 0) {
			token = check1 + 1;
		} else {
			token = 1;
		}
		int rr = i;
		String sql2 = "SELECT * FROM appointment where CONSULTINGDOCTORID = ? AND APPOINTMENTDATE = ? AND APPOINTMENTSTARTTIME = ? AND APPOINTMENTENDTIME = ? AND APPOINTMENTSTATUS = ? AND PATIENTID = ?";
		Boolean ver = jdbcTemplate.query(sql2,  new Object[] { appPojo.get(rr).getConsiultingDoctorId(), appPojo.get(rr).getAppointmentDate(),appPojo.get(rr).getAppointmentStartTime(),appPojo.get(rr).getAppointmentEndTime(),"V",appPojo.get(rr).getPatientId() }, new ResultSetExtractor<Boolean>() {
	        public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
		               if(rs.next()){
		            	   return true;
		               }
		               else
		            	   return false;	            	   
		          }
	        });
		if(ver) {}
		else
		{
			String rkr = "select * from appointment where consultingdoctorid = ? and appointmentdate = ? and appointmentstarttime = ?";
		
			Boolean vere = jdbcTemplate.query(rkr,  new Object[] { appPojo.get(rr).getConsiultingDoctorId(), appPojo.get(rr).getAppointmentDate(),appPojo.get(rr).getAppointmentStartTime()}, new ResultSetExtractor<Boolean>() {
		        public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
			               if(rs.next()){
			            	   return true;
			               }
			               else
			            	   return false;	            	   
			          }
		        });
			if(vere)
			{
				
			}
			else
			{
			String sql1 = "INSERT INTO appointment(CONSULTINGDOCTORID,APPOINTMENTDATE,APPOINTMENTSTARTTIME,APPOINTMENTENDTIME,TOKENNO,APPOINTMENTSTATUS,PATIENTID)values(?,?,?,?,?,?,?)";
		Boolean abc = jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1,appPojo.get(rr).getConsiultingDoctorId());
				ps.setDate(2, appPojo.get(rr).getAppointmentDate());
			    ps.setString(3, appPojo.get(rr).getAppointmentStartTime());
			    ps.setString(4, appPojo.get(rr).getAppointmentEndTime());
			    ps.setInt(5, token);
			    ps.setString(6, status);
				ps.setString(7, "p");
				System.out.println("done once");
				return ps.execute();
			}
		});
		}
		}
		}
		return true;
	}

/*	public List<PatientPojo> appAll(Date dat) {
		// TODO Auto-generated method stub
		
		//String sql2 = "SELECT * FROM appointment a,vitalinformation v where a.appointmentStatus = 'B' and a.appointmentdate = '"+dat+"' and v.vitalinfo_date = '"+dat+"' and v.patientid = 'AST201700000032'";
		String sql = "SELECT * FROM appointment where appointmentStatus = 'B' and appointmentdate = '"+dat+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientPojo>>() {
	        public List<PatientPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii appointment all da ithu ");
	        	
	        	List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	
            	PatientPojo app2 = new PatientPojo();
	            while(rs.next()) {
	            	
	            	
	            	String pid = rs.getString("PATIENTID");
	            	
	            	String sqlr = "SELECT * FROM vitalinformation v where v.vitalinfo_date = '"+dat+"' and v.patientid = '"+pid+"'";
	            	String dmk = jdbcTemplate.query(sqlr, new ResultSetExtractor<String>() {
		    	        public String extractData(ResultSet rs) throws SQLException,
		    	                DataAccessException {
		    	        	
		    	        	PatientPojo app = new PatientPojo();
		    	        	
		    	        	if(rs.next()) {
		    	        		return null;
		    	        	}
		    	        	else
		    	        	{
		    	        		String sql1 = "SELECT * FROM patientmaster where patientid = '"+pid+"'";
		    	            	app = jdbcTemplate.query(sql1, new ResultSetExtractor<PatientPojo>() {
		    	        	        public PatientPojo extractData(ResultSet rs) throws SQLException,
		    	        	                DataAccessException {
		    	        	        	PatientPojo patient = new PatientPojo();
		    	        	        	while(rs.next())
		    	        	        	{
		    	        	        		
		    	        	                patient.setPatientId(rs.getString("PATIENTID"));
		    	        	                patient.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
		    	        	                patient.setPatientMiddleName(rs.getString("PATIENTMIDDLENAME"));
		    	        	                patient.setPatientLastName(rs.getString("PATIENTLASTNAME"));
		    	        	                patient.setPatientGender(rs.getString("PATIENTGENDER"));
		    	        	                patient.setPatientDOB(rs.getDate("PATIENTDATEOFBIRTH"));
		    	        	                patient.setPatientMobile1(rs.getString("PATIENTMOBILE1"));
		    	        	                patient.setPatientMobile2(rs.getString("PATIENTMOBILE2"));
		    	        	                patient.setPatientAddress1(rs.getString("PATIENTADDRESS1"));
		    	        	                patient.setPatientAddress2(rs.getString("PATIENTADDRESS2"));
		    	        	                patient.setPatientMailId(rs.getString("PATIENTMAILID"));
		    	        	                patient.setPatientUniqueId(rs.getString("PATIENTUNIQUEID"));
		    	        	                patient.setPatientBloodGroup(rs.getString("PATIENTBLOODGROUP"));
		    	        	                patient.setPatientRelationship(rs.getString("PATIENTRELATIONSHIP"));
		    	        	                patient.setPatientRelationId(rs.getString("PATIENTRELATIONID"));
		    	        	                patient.setPatientRelationName(rs.getString("PATIENTRELATIONNAME"));
		    	        	                patient.setPatientPhoto(rs.getString("PATIENTPHOTO"));
		    	        	                System.out.println("if ulla varuthu patient ku"+patient);	        	                
		    	        	        	}
		    	        	        	return patient;
		    	        	        }
		    	        	        });
		    	            	return "true";
		    	        	}
		    	        	if(app.equals(app2)) {}
			            	else
			            		list.add(app);
			            	}
	            		}
		        	});
	        	
	            return list;
	        }
		});
	}*/
	
	public List<PatientWaitingListPojo> appAll(Date dat) {
		// TODO Auto-generated method stub
		
		//String sql2 = "SELECT * FROM appointment a,vitalinformation v where a.appointmentStatus = 'B' and a.appointmentdate = '"+dat+"' and v.vitalinfo_date = '"+dat+"' and v.patientid = 'AST201700000032'";
		String sql = "SELECT * FROM appointment where appointmentStatus = 'B' and appointmentdate = '"+dat+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientWaitingListPojo>>() {
	        public List<PatientWaitingListPojo> extractData(ResultSet rs1) throws SQLException,
	                DataAccessException {
	        	System.out.println("APPOINTMENT ALL CALLED");
	        	List<PatientWaitingListPojo> list = new  ArrayList<PatientWaitingListPojo>();
	        		while(rs1.next())
	        		{
	        			PatientWaitingListPojo pat = new PatientWaitingListPojo();
	        			String pid = rs1.getString("PATIENTID");
	        			String sqlr = "SELECT * FROM vitalinformation where vitalinfo_date = '"+dat+"' and patientid = '"+pid+"'";
		            	pat = jdbcTemplate.query(sqlr, new ResultSetExtractor<PatientWaitingListPojo>() {
			    	        public PatientWaitingListPojo extractData(ResultSet rs) throws SQLException,
			    	                DataAccessException {
			    	        	if(rs.next())
			    	        	{
			    	        		PatientWaitingListPojo dks = new PatientWaitingListPojo();
			    	        		dks.setPatientId("paithiyam");
			    	        		return dks;
			    	        	}
			    	        	else
			    	        	{
			    	        		String sql2 = "SELECT * FROM patientmaster where patientid = '"+pid+"'";
			    	            	return jdbcTemplate.query(sql2, new ResultSetExtractor<PatientWaitingListPojo>() {
			    	        	        public PatientWaitingListPojo extractData(ResultSet rs) throws SQLException,
			    	        	                DataAccessException {
			    	        	        	PatientWaitingListPojo patient = new PatientWaitingListPojo();
			    	        	        	if(rs.next())
			    	        	        	{
			    	        	        		
			    	        	                patient.setPatientId(rs.getString("PATIENTID"));
			    	        	                patient.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
			    	        	                patient.setPatientMiddleName(rs.getString("PATIENTMIDDLENAME"));
			    	        	                patient.setPatientLastName(rs.getString("PATIENTLASTNAME"));
			    	        	                patient.setPatientGender(rs.getString("PATIENTGENDER"));
			    	        	                patient.setPatientDOB(rs.getDate("PATIENTDATEOFBIRTH"));
			    	        	                patient.setPatientMobile1(rs.getString("PATIENTMOBILE1"));
			    	        	                patient.setPatientMobile2(rs.getString("PATIENTMOBILE2"));
			    	        	                patient.setPatientAddress1(rs.getString("PATIENTADDRESS1"));
			    	        	                patient.setPatientAddress2(rs.getString("PATIENTADDRESS2"));
			    	        	                patient.setPatientMailId(rs.getString("PATIENTMAILID"));
			    	        	                patient.setPatientUniqueId(rs.getString("PATIENTUNIQUEID"));
			    	        	                patient.setPatientBloodGroup(rs.getString("PATIENTBLOODGROUP"));
			    	        	                patient.setPatientRelationship(rs.getString("PATIENTRELATIONSHIP"));
			    	        	                patient.setPatientRelationId(rs.getString("PATIENTRELATIONID"));
			    	        	                patient.setPatientRelationName(rs.getString("PATIENTRELATIONNAME"));
			    	        	                patient.setPatientPhoto(rs.getString("PATIENTPHOTO"));
			    	        	                patient.setDoctorId(rs1.getString("CONSULTINGDOCTORID"));
			    	        	                patient.setAppointmentDate(rs1.getDate("APPOINTMENTDATE"));
			    	        	                patient.setAppointmentTime(rs1.getString("APPOINTMENTSTARTTIME"));
			    	        	                  
			    	        	                System.out.println("if ulla varuthu patient vangurathukku "+patient);	        	                
			    	        	        	}
			    	        	        	return patient;
			    	        	        }
			    	        	        });
			    	        	}
			    	        }
		            	});
		            	System.out.println("check null "+pat.getPatientId());
		            	System.out.println("check null "+pid);	
		            	if(pat.getPatientId().equals(pid))
		            		list.add(pat);
	        		}
	        		
	        return list;
	        }
		});
	
	}
	
	
	public List<AppointmentPojo> appByDoctor(String pname) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM appointment WHERE CONSULTINGDOCTORID = ?";
		return jdbcTemplate.query(sql,  new Object[] { pname }, new ResultSetExtractor<List<AppointmentPojo>>() {
	        public List<AppointmentPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<AppointmentPojo> list = new  ArrayList<AppointmentPojo>();
	        	while(rs.next()) {
	        		
	        		AppointmentPojo app = new AppointmentPojo();
	                app.setAppointmentID(rs.getLong("APPOINTMENTID"));
	                app.setConsiultingDoctorId(rs.getString("CONSULTINGDOCTORID"));
	                app.setAppointmentDate(rs.getDate("APPOINTMENTDATE"));
	                app.setAppointmentStartTime(rs.getString("APPOINTMENTSTARTTIME"));
	                app.setAppointmentEndTime(rs.getString("APPOINTMENTENDTIME"));
	                app.setPatientId(rs.getString("PATIENTID"));
	                app.setTokenNo(rs.getInt("TOKENNO"));
	                app.setAppointmentStatus(rs.getString("APPOINTMENTSTATUS"));
	                
	                list.add(app);
	            }
	            return list;
	            
	            }
	    });

	}


	public List<AppointmentPojo> appByDoctorAndDate(String pname, Date appdate) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM appointment WHERE CONSULTINGDOCTORID = ? and APPOINTMENTDATE = ?";
		return jdbcTemplate.query(sql,  new Object[] { pname, appdate }, new ResultSetExtractor<List<AppointmentPojo>>() {
	        public List<AppointmentPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<AppointmentPojo> list = new  ArrayList<AppointmentPojo>();
	        	while(rs.next()) {
	        		
	        		AppointmentPojo app = new AppointmentPojo();
	                app.setAppointmentID(rs.getLong("APPOINTMENTID"));
	                app.setConsiultingDoctorId(rs.getString("CONSULTINGDOCTORID"));
	                app.setAppointmentDate(rs.getDate("APPOINTMENTDATE"));
	                app.setAppointmentStartTime(rs.getString("APPOINTMENTSTARTTIME"));
	                app.setAppointmentEndTime(rs.getString("APPOINTMENTENDTIME"));
	                app.setPatientId(rs.getString("PATIENTID"));
	                app.setTokenNo(rs.getInt("TOKENNO"));
	                app.setAppointmentStatus(rs.getString("APPOINTMENTSTATUS"));
	                
	                list.add(app);
	            }
	            return list;
	            
	            }
	    });
		
	}


	public List<AppointmentPojo> appByDate(Date appdate) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM appointment WHERE APPOINTMENTDATE = ? and APPOINTMENTSTATUS = 'B'";
		return jdbcTemplate.query(sql,  new Object[] { appdate }, new ResultSetExtractor<List<AppointmentPojo>>() {
	        public List<AppointmentPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<AppointmentPojo> list = new  ArrayList<AppointmentPojo>();
	        	while(rs.next()) {
	        		
	        		AppointmentPojo app = new AppointmentPojo();
	                
	        		app.setAppointmentID(rs.getLong("APPOINTMENTID"));
	                app.setConsiultingDoctorId(rs.getString("CONSULTINGDOCTORID"));
	                app.setAppointmentDate(rs.getDate("APPOINTMENTDATE"));
	                app.setAppointmentStartTime(rs.getString("APPOINTMENTSTARTTIME"));
	                app.setAppointmentEndTime(rs.getString("APPOINTMENTENDTIME"));
	                app.setPatientId(rs.getString("PATIENTID"));
	                app.setTokenNo(rs.getInt("TOKENNO"));
	                app.setAppointmentStatus(rs.getString("APPOINTMENTSTATUS"));
	                
	                list.add(app);
	            }
	            return list;
	            
	            }
	    });
		
	}

	public boolean clearapp(String pid,Date date)
	{
		 jdbcTemplate.update("DELETE FROM appointment WHERE PATIENTID = ? and APPOINTMENTDATE = ?", new Object[] {pid, date});
        return false;
		/*String sql = "SELECT * FROM appointment WHERE PATIENTID = ?";
		
		return jdbcTemplate.query(sql,  new Object[] {pid}, new ResultSetExtractor<Boolean>() {
	        public Boolean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        		if(rs.next())
	        		{
	        			String sql1 = "UPDATE appointment set APPOINTMENTSTATUS = ?, PATIENTID = ? WHERE PATIENTID =  ?";
    	        		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
    	        			public Boolean doInPreparedStatement(PreparedStatement ps)  
    	        			        throws SQLException, DataAccessException {  
    	        			
    	        				ps.setString(1, "V");
    	        			    ps.setString(2, "p");
    	        			    ps.setString(3, pid);
    	        			   
    	        			    return ps.execute();
    	        			}
    	        		});
	        		}
	        		else
	        			return true;
	        	
	        	}
		});*/
	}
	
	public boolean appUpdate(final AppointmentPojo appPojo) throws ParseException {
		// TODO Auto-generated method stub
		
		
	//	dkdk = "hai";
		/*DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
		java.util.Date today = new java.util.Date();
	    //System.out.println(new java.sql.Timestamp(today.getTime()));Asia/Kolkata
	    //Timestamp now = new java.sql.Timestamp(today.getTime());
		DateFormat formatter = new SimpleDateFormat("HH:mm");
	    java.sql.Time now = new java.sql.Time(formatter.parse(appPojo.getAppointmentStartTime()).getTime());
	    System.out.println("current time: " + now);
	  	System.out.println("time date da");
		System.out.println("app dao "+ appPojo);
		
		java.sql.Time apptime = new java.sql.Time(formatter.parse(appPojo.getAppointmentStartTime()).getTime());
		System.out.println("appointment time: " + apptime);
		int check= apptime.compareTo( now);
		System.out.println("time comparison =" + check);*/
		//
		System.out.println("-----Current time of a different time zone using LocalTime-----");
	    /*ZoneId zoneId = ZoneId.of("Asia/Kolkata");
	    LocalTime localTime=LocalTime.now(zoneId);
	    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("YYYY/MM/DD HH:mm:ss");
	    String currentTime=localTime.format(formatter1);*/
		
	    String appdate =appPojo.getAppointmentDate()+ " " +appPojo.getAppointmentStartTime();
	    System.out.println("Current time of the day in kolcatta: ");
	    System.out.println("apointment time: " + appdate);
	    java.util.Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                .parse(appdate);
	    System.out.println(start);
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
	    String currentTime = dtf.format(now);
	    LocalDateTime now1 = LocalDateTime.now();
	    String currentTime1 = dtf.format(now1);
	    System.out.println(dtf.format(now) +" utc"+currentTime1);  
        java.util.Date end = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH)
                .parse(currentTime);
		if(start.compareTo(end) <= 0)
		{
			return false;
		}
		else {
		String sql = "SELECT * FROM appointment WHERE APPOINTMENTDATE = ? and CONSULTINGDOCTORID = ? and PATIENTID = ?";
		
		String ls = jdbcTemplate.query(sql,  new Object[] { appPojo.getAppointmentDate(), appPojo.getConsiultingDoctorId(), appPojo.getPatientId()}, new ResultSetExtractor<String>() {
	        public String extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("va da dei va da");	
	        	if(rs.next()) {
	        		
	        		System.out.println("already appointed");
	        		
	        		String sqle = "SELECT * FROM appointment WHERE APPOINTMENTSTARTTIME = ? and APPOINTMENTDATE = ? and CONSULTINGDOCTORID = ? and PATIENTID = ?";
	        		
	        		Boolean lss = jdbcTemplate.query(sqle,  new Object[] { appPojo.getAppointmentStartTime(), appPojo.getAppointmentDate(), appPojo.getConsiultingDoctorId(), appPojo.getPatientId()}, new ResultSetExtractor<Boolean>() {
	        	        public Boolean extractData(ResultSet rs) throws SQLException,
	        	                DataAccessException {
	 
	        	        	if(rs.next())
	        	        	{
	        	        		String sql1 = "UPDATE appointment set APPOINTMENTSTATUS = ? , PATIENTID =  ? WHERE CONSULTINGDOCTORID = ? and APPOINTMENTDATE = ? and APPOINTMENTSTARTTIME = ?";
	        	        		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
	        	        			public Boolean doInPreparedStatement(PreparedStatement ps)  
	        	        			        throws SQLException, DataAccessException {  
	        	        			
	        	        				if(appPojo.getAppointmentStatus().equalsIgnoreCase("Booked"))
	        	        				{
	        	        					ps.setString(1, "V");
	        	        				}
	        	        				else
	        	        				{
	        	        					ps.setString(1, "B");
	        	        				}
	        	        				ps.setString(2, "p");
	        	        			    ps.setString(3, appPojo.getConsiultingDoctorId());
	        	        			    ps.setDate(4, appPojo.getAppointmentDate());
	        	        			    ps.setString(5, appPojo.getAppointmentStartTime());
	        	        				
	        	        			    return ps.execute();
	        	        			}
	        	        		});
	        	        	}
	        	        	else
	        	        	{
	        	        		return true;
	        	        	}
	        	        }
	        		});
	        		if(lss)
	        			return "false";
	        		else
	        			return "true"; //empty list da
	            	
	        	}
	        	else
	        	{
	        		System.out.println("going to book"+appPojo.getAppointmentStatus());
	        		String sql1 = "UPDATE appointment set APPOINTMENTSTATUS = ? , PATIENTID =  ? WHERE CONSULTINGDOCTORID = ? and APPOINTMENTDATE = ? and APPOINTMENTSTARTTIME = ?";
	        		Boolean ch = jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
	        			public Boolean doInPreparedStatement(PreparedStatement ps)  
	        			        throws SQLException, DataAccessException {  
	        				//System.out.println("in update "+rs);
	        			//	rs.next();
	        				
	        				/*if(rs.getString("APPOINTMENTSTATUS").equalsIgnoreCase("V"))
	        				{
	        					ps.setString(1, "B");
	       // 					dkdk = "Booked";
	        				}
	        				else
	        				{
	        					ps.setString(1, "V");
	     //   					dkdk = "Open";
	        				}*/
	        				if(appPojo.getAppointmentStatus().equalsIgnoreCase("OpenAppointment"))
	        				{
	        					System.out.println(" set b");
	        					ps.setString(1, "B");
	        				}
	        				else
	        				{
	        					System.out.println(" set v");
	        					ps.setString(1, "V");
	        				}
	        				System.out.println(appPojo.toString());
	        				ps.setString(2, appPojo.getPatientId());
	        			    ps.setString(3, appPojo.getConsiultingDoctorId());
	        			    ps.setDate(4, appPojo.getAppointmentDate());
	        			    ps.setString(5, appPojo.getAppointmentStartTime());
	        				
	        			    return ps.execute();
	        			}
	        		});
	        		System.out.println("ch value:"+ch);
	        		if(ch)
	        			return "false";
	        		else
	        		{
	        			System.out.println("if false");
	        			return "true";	        			
	        		}
	            }
	        	
	            }
	    });
		
		if(ls.equals("false"))
		{
		/*	if(dkdk.equals("Booked"))
				return true;
			else*/
				return false;// not updated
		}
		else
			return true; // Updated
		}
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String appByDoctorJson(String did) {
		// TODO Auto-generated method stub
		String a = "[{title:'OpenAppointment'}]";
		 String b = null;
		 String c, d;
		

			String sql = "SELECT * FROM appointment WHERE CONSULTINGDOCTORID = ?";
			return jdbcTemplate.query(sql,  new Object[] { did }, new ResultSetExtractor<String>() {
		        public String extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		        	List<AppointmentPojo> list = new  ArrayList<AppointmentPojo>();
		        	while(rs.next()) {
		        		
		        		AppointmentPojo app = new AppointmentPojo();
		                
		        		app.setAppointmentID(rs.getLong("APPOINTMENTID"));
		                app.setConsiultingDoctorId(rs.getString("CONSULTINGDOCTORID"));
		                app.setAppointmentDate(rs.getDate("APPOINTMENTDATE"));
		                app.setAppointmentStartTime(rs.getString("APPOINTMENTSTARTTIME"));
		                app.setAppointmentEndTime(rs.getString("APPOINTMENTENDTIME"));
		                app.setPatientId(rs.getString("PATIENTID"));
		                app.setTokenNo(rs.getInt("TOKENNO"));
		                app.setAppointmentStatus(rs.getString("APPOINTMENTSTATUS"));
		                
		                list.add(app);
		        		
		            }
		        	
		        	System.out.println("today "+ list);
		        	System.out.println("size "+ list.size());
		        	System.out.println("string "+ list.toString());
		        	System.out.println(""+ list.get(0));
		        	int i;
		        	String title = "title";String obrace ="{";String cbrace="}";String colon = ":";String sq="'";
		        	String osbracket="[";String csbracket="]";String comma=",";String start="start";String end="end";String col="color";
		        	String res = osbracket;
		        	for(i=0;i<list.size();i++)
		        	{
		        		Date dat = list.get(i).getAppointmentDate();
		        		String st = list.get(i).getAppointmentStartTime();
		        		String et = list.get(i).getAppointmentEndTime();
		        		System.out.println(dat.toString());
		        		if(list.get(i).getAppointmentStatus().equalsIgnoreCase("B"))
		        			res = res + obrace+title+colon+sq+"Booked"+sq+comma+start+colon+sq+dat.toString()+"T"+st+sq+comma+end+colon+sq+dat.toString()+"T"+et+sq+comma+col+colon+sq+"Green"+sq+cbrace;
		        		if(list.get(i).getAppointmentStatus().equalsIgnoreCase("v"))
		        			res = res + obrace+title+colon+sq+"OpenAppointment"+sq+comma+start+colon+sq+dat.toString()+"T"+st+sq+comma+end+colon+sq+dat.toString()+"T"+et+sq+comma+col+colon+sq+"Blue"+sq+cbrace;
		        		if(list.size() == 1) {
		        			res = res + csbracket;
		        		}
		        		else if(list.size() > 1) {
		        			if(i == list.size()-1) {
		        				res = res + csbracket;
		        			}
		        			else
		        				res = res + comma;
		        		}
		        		
		        		System.out.println(res);
		        		System.out.println("poda naye");
		        	}
		            return res;
		            
		            }
		    });
	}


	public List<AppointmentJsonPojo> appByDoctorJson1(String pname) {
		// TODO Auto-generated method stub
		
		System.out.println("working");
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
  	  LocalDateTime now = LocalDateTime.now();  
  	  System.out.println(dtf.format(now));
  	System.out.println("time date da");
		int eke = 0;
		try {
			eke = jdbcTemplate.update("DELETE from appointment where appointmentdate < ?", new Object[] {df.parse(dtf.format(now))});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("check it "+eke);
		String sql = "SELECT * FROM appointment WHERE CONSULTINGDOCTORID = ?";
		return jdbcTemplate.query(sql,  new Object[] { pname }, new ResultSetExtractor<List<AppointmentJsonPojo>>() {
	        public List<AppointmentJsonPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii in show scheduler of doctor");
	        	
	        	List<AppointmentJsonPojo> list = new  ArrayList<AppointmentJsonPojo>();
	        	while(rs.next()) {
	        		
	        		AppointmentJsonPojo app = new AppointmentJsonPojo();
	                
	        		if(rs.getString("APPOINTMENTSTATUS").equalsIgnoreCase("v"))
	        		{
	        			app.setTitle("OpenAppointment");
	        			app.setColor("Blue");
	        			//app.setPatientId(null);
	        		}
	        		if(rs.getString("APPOINTMENTSTATUS").equalsIgnoreCase("B"))
	        		{
	        			app.setTitle("Booked");
	        			app.setColor("Green");
	        			app.setPatientId(rs.getString("PATIENTID"));
	        		}
	        		Date appdate = rs.getDate("APPOINTMENTDATE");
	        		String st = rs.getString("APPOINTMENTSTARTTIME");
	        		String et = rs.getString("APPOINTMENTENDTIME");
	        		
	        			app.setStart(appdate+"T"+st);
	        			app.setEnd(appdate+"T"+et);
	        		
	        		list.add(app);
	            }
	            return list;
	            
	            }
	    });
	}


	public int appDelete(AppointmentPojo appPojo) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM appointment WHERE CONSULTINGDOCTORID = ? and APPOINTMENTDATE = ? and APPOINTMENTSTARTTIME = ?", new Object[] { appPojo.getConsiultingDoctorId() , appPojo.getAppointmentDate() , appPojo.getAppointmentStartTime()});
	}
	public int clearAppointmentByDoctorPatientAndDate(String did, String pid, Date date) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM appointment WHERE CONSULTINGDOCTORID = ? and APPOINTMENTDATE = ? and PATIENTID = ?", new Object[] { did , date , pid});
	}


	public List<PatientPojo> appAllDoctor(String did,Date date) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM appointment where appointmentStatus = 'B' and APPOINTMENTDATE = '"+date+"' and CONSULTINGDOCTORID = '"+did+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientPojo>>() {
	        public List<PatientPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii appointment by doctor da ithu ");
	        	List<PatientPojo> list = new  ArrayList<PatientPojo>();
	            while(rs.next()) {
	            	
	            	PatientPojo app = new PatientPojo();
	            	PatientPojo app2 = new PatientPojo();
	            	String pid = rs.getString("PATIENTID");
	            	//check vital da
	            	String sqq = "SELECT * FROM vitalinformation where patientid = '"+pid+"' AND vitalinfo_date = '"+date+"'";
	            	
	            	Boolean dkd = jdbcTemplate.query(sqq, new ResultSetExtractor<Boolean>() {
	        	        public Boolean extractData(ResultSet rs1) throws SQLException,
	        	                DataAccessException {
	        	        	
	        	        		if(rs1.next()) {return true;}
	        	        		else
	        	        			return false;
	        	        }
	        	       });
	            	
	            	if(dkd)
	            	{
	            		System.out.println("patientid... no patient found" + pid);
	            	String sql1 = "SELECT * FROM patientmaster where patientid = '"+pid+"'";
	            	
	        		app = jdbcTemplate.query(sql1, new ResultSetExtractor<PatientPojo>() {
	        	        public PatientPojo extractData(ResultSet rs12) throws SQLException,
	        	                DataAccessException {
	        	        	PatientPojo patient = new PatientPojo();
	        	        	if(rs12.next())
	        	        	{
	        	        		
	        	                patient.setPatientId(rs12.getString("PATIENTID"));
	        	                System.out.println("patientid... no patient found1" + rs12.getString("PATIENTID"));
	        	                patient.setPatientFirstName(rs12.getString("PATIENTFIRSTNAME"));
	        	                patient.setPatientMiddleName(rs12.getString("PATIENTMIDDLENAME"));
	        	                patient.setPatientLastName(rs12.getString("PATIENTLASTNAME"));
	        	                patient.setPatientGender(rs12.getString("PATIENTGENDER"));
	        	                patient.setPatientDOB(rs12.getDate("PATIENTDATEOFBIRTH"));
	        	                patient.setPatientMobile1(rs12.getString("PATIENTMOBILE1"));
	        	                patient.setPatientMobile2(rs12.getString("PATIENTMOBILE2"));
	        	                patient.setPatientAddress1(rs12.getString("PATIENTADDRESS1"));
	        	                patient.setPatientAddress2(rs12.getString("PATIENTADDRESS2"));
	        	                patient.setPatientMailId(rs12.getString("PATIENTMAILID"));
	        	                patient.setPatientUniqueId(rs12.getString("PATIENTUNIQUEID"));
	        	                patient.setPatientBloodGroup(rs12.getString("PATIENTBLOODGROUP"));
	        	                patient.setPatientRelationship(rs12.getString("PATIENTRELATIONSHIP"));
	        	                patient.setPatientRelationId(rs12.getString("PATIENTRELATIONID"));
	        	                patient.setPatientRelationName(rs12.getString("PATIENTRELATIONNAME"));
	        	                patient.setPatientPhoto(rs12.getString("PATIENTPHOTO"));
	        	                System.out.println("if ulla varuthu patient ku"+patient);
	        	                list.add(patient);
	        	                return patient;
	        	        	}
	        	        	else
	        	        	{
	        	        		System.out.println("else vantu... no patient found");
	        	        		return patient;
	        	        	}
	        	        }
	        	        });
	            	}
	        		   if(app.equals(app2))
	        		   {System.out.println("if vantu... no patient found");}
	        		   else {
	        			   System.out.println("else vantu..." + app.toString());
	        		   }
	            }
	            return list;
	            
	            }
	    });
	}


	public int appDelete1(String did, Date adate) {
		// TODO Auto-generated method stub
		
		String sqq = "SELECT * FROM appointment where CONSULTINGDOCTORID = '"+did+"' and APPOINTMENTSTATUS = 'B' and APPOINTMENTDATE = '"+adate+"'";
    	
    	Boolean dkd = jdbcTemplate.query(sqq, new ResultSetExtractor<Boolean>() {
	        public Boolean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	
	        	while(rs.next())
	        	{
	        		String pid = rs.getString("PATIENTID");
	        		System.out.println(pid);
	        		PatientPojo ppojo = new PatientPojo();
	        		ppojo = pdao.patientSearchById(pid);
	        		String mob1 = ppojo.getPatientMobile1();
	        		if(mob1.length() == 10)
	        		{
	        			msg.Message1(mob1);
	        			System.out.println("message sent to mobile 1");
	        		}
	        	}
	        	return true;
	        }
	       });
		return jdbcTemplate.update("DELETE FROM appointment WHERE CONSULTINGDOCTORID = ? and APPOINTMENTDATE = ?", new Object[] {did, adate});
	}
	
	public List<AppointmentPojo> appByPatientId(String pname) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM appointment WHERE PATIENTID = ? AND APPOINTMENTSTATUS='B'";
		return jdbcTemplate.query(sql,  new Object[] { pname }, new ResultSetExtractor<List<AppointmentPojo>>() {
	        public List<AppointmentPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<AppointmentPojo> list = new  ArrayList<AppointmentPojo>();
	        	while(rs.next()) {
	        		
	        		AppointmentPojo app = new AppointmentPojo();
	                app.setAppointmentID(rs.getLong("APPOINTMENTID"));
	                app.setConsiultingDoctorId(rs.getString("CONSULTINGDOCTORID"));
	                app.setAppointmentDate(rs.getDate("APPOINTMENTDATE"));
	                app.setAppointmentStartTime(rs.getString("APPOINTMENTSTARTTIME"));
	                app.setAppointmentEndTime(rs.getString("APPOINTMENTENDTIME"));
	                app.setPatientId(rs.getString("PATIENTID"));
	                app.setTokenNo(rs.getInt("TOKENNO"));
	                app.setAppointmentStatus(rs.getString("APPOINTMENTSTATUS"));
	                
	                list.add(app);
	            }
	            return list;
	            
	            }
	    });

	}



	
}