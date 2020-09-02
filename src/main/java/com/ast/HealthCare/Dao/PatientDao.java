package com.ast.HealthCare.Dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PatientDao implements Serializable{
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//	CREATE TABLE patientmaster(PATIENTID INTEGER,PATIENTFIRSTNAME TEXT,PATIENTMIDDLENAME TEXT,PATIENTLASTNAME TEXT,
//	PATIENTGENDER TEXT,PATIENTDATEOFBIRTH DATE,PATIENTBLOODGROUP TEXT,PATIENTADDRESS1 TEXT,PATIENTADDRESS2 TEXT,
//	PATIENTMOBILE1 TEXT,PATIENTMOBILE2 TEXT,HOSPITALCODE TEXT,YEAR SMALLINT,PATIENTSERIALID TEXT,PATIENTPHOTO BYTEA,
//	PATIENTRELATIONNAME TEXT,PATIENTRELATIONSHIP TEXT,PATIENTRELATIONID TEXT,PATIENTMAILID TEXT,PATIENTUNIQUEID TEXT,PRIMARY KEY(PATIENTID));

	
	//protected JdbcTemplate jdbcTemplate;
	@Value("${const.patientIdPrefix}")
	String hoscode;
	
	JpaConfiguration jpa = new JpaConfiguration();
	PatientDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("PatientDao constructor jdbc "+this.jdbcTemplate);
	}
	
	
	public String register(final PatientPojo patient) {
		long num1;
	    System.out.println("sgad"+patient);
        Calendar now = Calendar.getInstance();   // Gets the current date and time
        final int year = now.get(Calendar.YEAR);
     
        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
	    LocalDateTime nowdate = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
	    java.util.Date dateeee = new java.util.Date();
		try {
			dateeee = new SimpleDateFormat("dd-MM-yyyy").parse(dtf.format(nowdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    patient.setPatientRegisterDate(new java.sql.Date(dateeee.getTime()) );
    */    
        String sql1="Select PATIENTID  from patientmaster order by PATIENTSERIALID DESC";
         List<String> rs =jdbcTemplate.query(sql1 , new ResultSetExtractor<List<String>>() {
	        public List<String> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
    	System.out.println("oii");
    	List<String> list = new  ArrayList<String>();
    	
        if (rs.next()) {
        	System.out.println("oii1");
            list.add(rs.getString("PATIENTID"));
            return list;
        }
        else {
        	System.out.println("else");
        	List<String> paa = new  ArrayList<String>();
        	//paa.add();
             return paa;
    }
	        }
        });
       
        if(! rs.isEmpty())
        {
        	System.out.println("resultset not empty so if kula ");
       String prevpid= rs.get(0);
       System.out.println(prevpid);
       int prevyear = Integer.parseInt(prevpid.substring(2, 6));
       System.out.println("previous patient year: "+ prevyear);
       if(prevyear == year)
       {   
    	System.out.println("same year");   
        int prevnum = Integer.parseInt(prevpid.substring(8));  
        System.out.println("prevnum: "+ prevnum);
         num1=prevnum+1;
        System.out.println("max="+num1);
       }
       else
       {
    	   System.out.println("new year");
    	   //int prevnum = Integer.parseInt(prevpid.substring(7));       
           num1=1;
           System.out.println("max="+num1);   
       }
        String num2;
        if(num1<10)
        {
        	num2="0000"+num1;
        }
        else if(num1<100)
        {
        	 num2="000"+num1;
        }
        else if(num1<1000)
        {
        	 num2="00"+num1;
        }
        else if(num1<10000)
        {
        	 num2="0"+num1;
        }
			/*
			 * else if(num1<100000) { num2="0000"+num1; } else if(num1<1000000) {
			 * num2="000"+num1; }else if(num1<10000000) { num2="00"+num1; } else
			 * if(num1<100000000) { num2="0"+num1; }
			 */
       else
    	   num2=""+num1;
       
        System.out.println(num2);
        final String  pid=hoscode+year+'-'+num2;
       
		//String sql = "INSERT INTO paitentmaster (PATIENTID,PATIENTFIRSTNAME,PATIENTMIDDLENAME,PATIENTLASTNAME,PATIENTGENDER,PATIENTDATEOFBIRTH,PATIENTAGE,PATIENTBLOODGROUP,PATIENTADDRESS1,PATIENTADDRESS2,PATIENTMOBILENO,,PATIENTMAILID) VALUES "			+ "('"+firstname+"','"+middlename+"','"+lastname+"','"+gender+"','"+dob+"','"+age+"','"+bloodgroup+"','"+address1+"','"+address2+"','"+phoneno+"','"+username+"','"+password+"','"+mail+"');";
       /* String sql= "INSERT INTO paitentmaster (PAITENTID,PATIENTFIRSTNAME,PATIENTMIDDLENAME,PATIENTLASTNAME,PATIENTGENDER,PATIENTDATEOFBIRTH,"+
        		"PATIENTBLOODGROUP,PATIENTADDRESS1,PATIENTADDRESS2,PATIENTMOBILENO,PATIENTUNIQUEID,HOSPITALCODE,YEAR,PATIENTRELATIONNAME,PATIENTRELATIONID,PATIENTRELATIONSHIP,PATIENTMAILID,PATIENTPHOTO) VALUES "+
        		"('"+pid+"','"+firstname+"','"+middlename+"','"+lastname+"','"+gender+"','"+dob+"','"+bloodgroup+"','"+address1+"','"+address2+"','"+phoneno+"','"+aadhar+"','"+hoscode+"','"+year+"','"+relname+"','"+relid+"','"+relationship+"','"+mail+"','"+img+"');";
        		System.out.println("dsai");
		int ss = stmt.executeUpdate(sql);*/
		
	String query="INSERT INTO patientmaster (PATIENTID,PATIENTFIRSTNAME,PATIENTMIDDLENAME,PATIENTLASTNAME,PATIENTGENDER,PATIENTDATEOFBIRTH,"+
    		"PATIENTBLOODGROUP,PATIENTADDRESS1,PATIENTADDRESS2,PATIENTMOBILE1,PATIENTMOBILE2,PATIENTUNIQUEID,HOSPITALCODE,PATIENTREGISTERDATE,PATIENTRELATIONNAME,"+
			"PATIENTRELATIONID,PATIENTRELATIONSHIP,PATIENTMAILID,PATIENTPHOTO,PATIENTAGE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	      
    return jdbcTemplate.execute(query,new PreparedStatementCallback<String>(){  
    public String doInPreparedStatement(PreparedStatement ps)  
            throws SQLException, DataAccessException {  
              
        ps.setString(1,pid);  
        System.out.println("dao "+pid);
        ps.setString(2,patient.getPatientFirstName());
        ps.setString(3,patient.getPatientMiddleName());
        ps.setString(4,patient.getPatientLastName());
        ps.setString(5,patient.getPatientGender());
        /*SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date parsed;
        java.sql.Date sql = null;
    	try {
    		parsed = format.parse(patient.getPatientDOB());
    		sql = new java.sql.Date(parsed.getTime());
    	    
    	} catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}*/
        //System.out.println("date"+patient.getPatientDOB());
        //java.sql.Date sdob = java.sql.Date.valueOf(patient.getPatientDOB());
        //System.out.println(sdob);
        
        
        /*String rkrk = new SimpleDateFormat("dd-MM-yyyy").format(patient.getPatientDOB());
        System.out.println(rkrk);
        try {
			ps.setDate(6, (java.sql.Date) new SimpleDateFormat("dd/MM/yyyy").parse(rkrk));
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        ps.setDate(6,patient.getPatientDOB());
        ps.setString(7,patient.getPatientBloodGroup());
        ps.setString(8,patient.getPatientAddress1());
        ps.setString(9,patient.getPatientAddress2());
        ps.setString(10,patient.getPatientMobile1());
        ps.setString(11,patient.getPatientMobile2());
        ps.setString(12,patient.getPatientUniqueId());
        ps.setString(13,hoscode);
        ps.setDate(14,patient.getPatientRegisterDate());
        ps.setString(15,patient.getPatientRelationName());
        ps.setString(16,patient.getPatientRelationId());
        ps.setString(17,patient.getPatientRelationship());
        ps.setString(18,patient.getPatientMailId());
        ps.setString(19,patient.getPatientPhoto());
        ps.setInt(20, patient.getPatientAge());
        if(ps.execute())
        {
        	return "false";
        }
        else
        {
        	return pid;
        	
        }
              
    }
    });
}else
{
	System.out.println("first entry in table");
	num1=1;
    System.out.println("max="+num1);
    
    String num2;
    if(num1<10)
    {
    	num2="0000"+num1;
    }
    else if(num1<100)
    {
    	 num2="000"+num1;
    }
    else if(num1<1000)
    {
    	 num2="00"+num1;
    }
    else if(num1<10000)
    {
    	 num2="0"+num1;
    }
			/*
			 * else if(num1<100000) { num2="0000"+num1; } else if(num1<1000000) {
			 * num2="000"+num1; }else if(num1<10000000) { num2="00"+num1; } else
			 * if(num1<100000000) { num2="0"+num1; }
			 */
   else
	   num2=""+num1;
   
    System.out.println(num2);
    final String  pid=hoscode+year+'-'+num2;
	//String sql = "INSERT INTO paitentmaster (PATIENTID,PATIENTFIRSTNAME,PATIENTMIDDLENAME,PATIENTLASTNAME,PATIENTGENDER,PATIENTDATEOFBIRTH,PATIENTAGE,PATIENTBLOODGROUP,PATIENTADDRESS1,PATIENTADDRESS2,PATIENTMOBILENO,,PATIENTMAILID) VALUES "			+ "('"+firstname+"','"+middlename+"','"+lastname+"','"+gender+"','"+dob+"','"+age+"','"+bloodgroup+"','"+address1+"','"+address2+"','"+phoneno+"','"+username+"','"+password+"','"+mail+"');";
   /* String sql= "INSERT INTO paitentmaster (PAITENTID,PATIENTFIRSTNAME,PATIENTMIDDLENAME,PATIENTLASTNAME,PATIENTGENDER,PATIENTDATEOFBIRTH,"+
    		"PATIENTBLOODGROUP,PATIENTADDRESS1,PATIENTADDRESS2,PATIENTMOBILENO,PATIENTUNIQUEID,HOSPITALCODE,YEAR,PATIENTRELATIONNAME,PATIENTRELATIONID,PATIENTRELATIONSHIP,PATIENTMAILID,PATIENTPHOTO) VALUES "+
    		"('"+pid+"','"+firstname+"','"+middlename+"','"+lastname+"','"+gender+"','"+dob+"','"+bloodgroup+"','"+address1+"','"+address2+"','"+phoneno+"','"+aadhar+"','"+hoscode+"','"+year+"','"+relname+"','"+relid+"','"+relationship+"','"+mail+"','"+img+"');";
    		System.out.println("dsai");
	int ss = stmt.executeUpdate(sql);*/
	
String query="INSERT INTO patientmaster (PATIENTID,PATIENTFIRSTNAME,PATIENTMIDDLENAME,PATIENTLASTNAME,PATIENTGENDER,PATIENTDATEOFBIRTH,"+
		"PATIENTBLOODGROUP,PATIENTADDRESS1,PATIENTADDRESS2,PATIENTMOBILE1,PATIENTMOBILE2,PATIENTUNIQUEID,HOSPITALCODE,PATIENTREGISTERDATE,PATIENTRELATIONNAME,"+
		"PATIENTRELATIONID,PATIENTRELATIONSHIP,PATIENTMAILID,PATIENTPHOTO,PATIENTAGE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	      
return jdbcTemplate.execute(query,new PreparedStatementCallback<String>(){  
public String doInPreparedStatement(PreparedStatement ps)  
        throws SQLException, DataAccessException {  
          
	ps.setString(1,pid);  
	System.out.println(patient.getPatientFirstName());
    ps.setString(2,patient.getPatientFirstName());
    ps.setString(3,patient.getPatientMiddleName());
    ps.setString(4,patient.getPatientLastName());
    ps.setString(5,patient.getPatientGender());
    /*SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date parsed;
    java.sql.Date sql = null;
	try {
		parsed = format.parse(patient.getPatientDOB());
		sql = new java.sql.Date(parsed.getTime());
	    
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    *///System.out.println("date"+patient.getPatientDOB());
    //java.sql.Date sdob = java.sql.Date.valueOf(patient.getPatientDOB());
    //System.out.println(sdob);
    ps.setDate(6,patient.getPatientDOB());
    ps.setString(7,patient.getPatientBloodGroup());
    ps.setString(8,patient.getPatientAddress1());
    ps.setString(9,patient.getPatientAddress2());
    ps.setString(10,patient.getPatientMobile1());
    ps.setString(11,patient.getPatientMobile2());
    ps.setString(12,patient.getPatientUniqueId());
    ps.setString(13,hoscode);
    ps.setDate(14,patient.getPatientRegisterDate());
    ps.setString(15,patient.getPatientRelationName());
    ps.setString(16,patient.getPatientRelationId());
    ps.setString(17,patient.getPatientRelationship());
    ps.setString(18,patient.getPatientMailId());
    ps.setString(19, patient.getPatientPhoto());
    ps.setInt(20, patient.getPatientAge());
    if(ps.execute())
    	return "false";
    else
    	return pid;  
       
}
});
}
	}


	public int patientDelete(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientmaster WHERE PATIENTID = ?", new Object[] { pid });
		 
		//return false;
	}


	public PatientPojo patientSearchById(String pid) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM patientmaster WHERE PATIENTID = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<PatientPojo>() {
	        public PatientPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	//List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                //PatientPojo patient = new PatientPojo();
	                patient.setPatientId(rs.getString("PATIENTID"));
	                patient.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
	                patient.setPatientRegisterDate(rs.getDate("PATIENTREGISTERDATE"));
	                patient.setPatientMiddleName(rs.getString("PATIENTMIDDLENAME"));
	                patient.setPatientLastName(rs.getString("PATIENTLASTNAME"));
	                patient.setPatientGender(rs.getString("PATIENTGENDER"));
	                /*String rkrk = new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("PATIENTDATEOFBIRTH"));
	                System.out.println(rkrk);
	                try {
						patient.setPatientDOB(new SimpleDateFormat("dd/MM/yyyy").parse(rkrk));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	               */
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
	                patient.setPatientAge(rs.getInt("PATIENTAGE"));
	              //  list.add(patient);
	                
	            }
	            return patient;
	            
	            }
	    });
	}
	
	public List<PatientPojo> patientSearchByName(String pid) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM patientmaster WHERE PATIENTFIRSTNAME LIKE ?  or PATIENTMIDDLENAME LIKE ? or PATIENTLASTNAME LIKE ?";
		return jdbcTemplate.query(sql,  new Object[] { pid, pid, pid }, new ResultSetExtractor<List<PatientPojo>>() {
	        public List<PatientPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                PatientPojo patient = new PatientPojo();
	                patient.setPatientId(rs.getString("PATIENTID"));
	                patient.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
	                patient.setPatientRegisterDate(rs.getDate("PATIENTREGISTERDATE"));
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
	                patient.setPatientAge(rs.getInt("PATIENTAGE"));
	                list.add(patient);
	                
	            }
	            return list;
	            
	            }
	    });
	}
	
/*		PatientPojo customer = (PatientPojo)jdbcTemplate.queryForObject(
				sql, new Object[] { pid },
				new BeanPropertyRowMapper(PatientPojo.class));

		return customer;
		/*
		String sql1="Select * from patientmaster where PATIENTID = ? ";
       return jdbcTemplate.query(sql1 , new ResultSetExtractor<PatientPojo>() {
	        public PatientPojo extractData(ResultSet rs) throws SQLException,
            DataAccessException {
    	System.out.println("oii");
    	PatientPojo pa = new PatientPojo();
    	if (rs.next()) {
        	System.out.println("oii1");
        	
            return pa;
        }
        else {
        	System.out.println("else");
        	PatientPojo paa = new PatientPojo();
             return paa;
    }
	        }
        });
     	*/
	
	public boolean patientUpdate(final PatientPojo patient) {
		
		System.out.println("vic "+patient);
		
		String query="UPDATE patientmaster set PATIENTFIRSTNAME = ?, PATIENTMIDDLENAME = ?,PATIENTLASTNAME = ?,PATIENTGENDER = ?,PATIENTDATEOFBIRTH = ?,"+
				"PATIENTBLOODGROUP = ?,PATIENTADDRESS1 = ?,PATIENTADDRESS2 = ?,PATIENTMOBILE1 = ?,PATIENTMOBILE2 = ?,PATIENTUNIQUEID = ?,PATIENTRELATIONNAME = ?,"+
				"PATIENTRELATIONID = ?,PATIENTRELATIONSHIP = ?,PATIENTMAILID = ?,PATIENTPHOTO = ?,PATIENTAGE=?  WHERE PATIENTID = ?";
			    
		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			/*ps.setString(1, patient.getPatientFirstName());
			ps.setString(2, patient.getPatientId());
		    */
			
			ps.setString(1,patient.getPatientFirstName());
		    ps.setString(2,patient.getPatientMiddleName());
		    ps.setString(3,patient.getPatientLastName());
		    ps.setString(4,patient.getPatientGender());
		    //java.sql.Date sdob = java.sql.Date.valueOf(patient.getPatientDOB());
		    System.out.println(patient.getPatientDOB());
		    
		    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		    ps.setDate(5,patient.getPatientDOB());
		    
		    ps.setString(6,patient.getPatientBloodGroup());
		    ps.setString(7,patient.getPatientAddress1());
		    ps.setString(8,patient.getPatientAddress2());
		    ps.setString(9,patient.getPatientMobile1());
		    ps.setString(10,patient.getPatientMobile2());
		    ps.setString(11,patient.getPatientUniqueId());
		    //ps.setString(12,hoscode);
		    //ps.setInt(13,year);
		    ps.setString(12,patient.getPatientRelationName());
		    ps.setString(13,patient.getPatientRelationId());
		    ps.setString(14,patient.getPatientRelationship());
		    ps.setString(15,patient.getPatientMailId());
		    ps.setString(16, patient.getPatientPhoto());
		    ps.setInt(17, patient.getPatientAge());
		    ps.setString(18, patient.getPatientId());
		    return ps.execute();
		}
		});
	}
	
	
	public List<PatientPojo> patientAll()
	{
		String sql = "SELECT * FROM patientmaster ORDER BY PATIENTID DESC LIMIT 100";
		// String sql = "SELECT * FROM patientmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientPojo>>() {
	        public List<PatientPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PatientPojo> list = new  ArrayList<PatientPojo>();
	            while(rs.next()) {
	            	System.out.println("oii1");
	                PatientPojo patient = new PatientPojo();
	                patient.setPatientId(rs.getString("PATIENTID"));
	                patient.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
	                patient.setPatientRegisterDate(rs.getDate("PATIENTREGISTERDATE"));
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
	                patient.setPatientAge(rs.getInt("PATIENTAGE"));
	                list.add(patient);
	                
	            }
	            return list;
	            
	            }
	    });
	
	}


	public List<PatientPojo> patientSearchByPhoneNo(String pno) {
		String sql = "SELECT * FROM patientmaster WHERE PATIENTMOBILE1 LIKE ? OR PATIENTMOBILE2 LIKE ?";
		return jdbcTemplate.query(sql,  new Object[] { pno,pno }, new ResultSetExtractor<List<PatientPojo>>() {
	        public List<PatientPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                PatientPojo patient = new PatientPojo();
	                patient.setPatientId(rs.getString("PATIENTID"));
	                patient.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
	                patient.setPatientRegisterDate(rs.getDate("PATIENTREGISTERDATE"));
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
	                patient.setPatientAge(rs.getInt("PATIENTAGE"));
	                list.add(patient);
	                
	            }
	            return list;
	            
	            }
	    });
	}


	public List<PatientPojo> patientSearchByAll(String all) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM patientmaster WHERE PATIENTFIRSTNAME LIKE ?  or PATIENTMIDDLENAME LIKE ? or PATIENTLASTNAME LIKE ? or PATIENTID LIKE ? or PATIENTMOBILE1 LIKE ? or PATIENTMOBILE2 LIKE ?";
		System.out.println(sql);
		return jdbcTemplate.query(sql,  new Object[] { all,all,all,all,all,all }, new ResultSetExtractor<List<PatientPojo>>() {
	        public List<PatientPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                PatientPojo patient = new PatientPojo();
	                patient.setPatientId(rs.getString("PATIENTID"));
	                patient.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
	                patient.setPatientRegisterDate(rs.getDate("PATIENTREGISTERDATE"));
	                patient.setPatientMiddleName(rs.getString("PATIENTMIDDLENAME"));
	                patient.setPatientLastName(rs.getString("PATIENTLASTNAME"));
	                patient.setPatientGender(rs.getString("PATIENTGENDER"));
	                /*String rkrk = new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("PATIENTDATEOFBIRTH"));
	                System.out.println(rkrk);
	                try {
						patient.setPatientDOB(new SimpleDateFormat("dd-MM-yyyy").parse(rkrk));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
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
	                patient.setPatientAge(rs.getInt("PATIENTAGE"));
	                list.add(patient);
	                
	            }
	            return list;
	            
	            }
	    });
	}
	
	public List<PatientPojo> patientSearchByDate(Date date1, Date date2) {
		String sql = "select * from patientmaster where PATIENTREGISTERDATE between '"+date1+"' and '"+date2+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientPojo>>() {
	        public List<PatientPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                PatientPojo patient = new PatientPojo();
	                patient.setPatientId(rs.getString("PATIENTID"));
	                patient.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
	                patient.setPatientRegisterDate(rs.getDate("PATIENTREGISTERDATE"));
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
	                patient.setPatientAge(rs.getInt("PATIENTAGE"));
	                list.add(patient);
	                
	            }
	            return list;
	            
	            }
	    });
	}
	
	public List<PatientPojo> patientSearchByAllForSMS(String all) {
		// TODO Auto-generated method stub
		String sql = "SELECT PATIENTID,PATIENTFIRSTNAME,PATIENTMIDDLENAME,PATIENTLASTNAME,PATIENTGENDER,PATIENTDATEOFBIRTH,PATIENTMOBILE1,PATIENTBLOODGROUP FROM patientmaster WHERE PATIENTFIRSTNAME LIKE ?  or PATIENTMIDDLENAME LIKE ? or PATIENTLASTNAME LIKE ? or PATIENTID LIKE ? or PATIENTMOBILE1 LIKE ? or PATIENTMOBILE2 LIKE ?";
		return jdbcTemplate.query(sql,  new Object[] { all,all,all,all,all,all }, new ResultSetExtractor<List<PatientPojo>>() {
	        public List<PatientPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                PatientPojo patient = new PatientPojo();
	                patient.setPatientId(rs.getString("PATIENTID"));
	                patient.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
	                patient.setPatientMiddleName(rs.getString("PATIENTMIDDLENAME"));
	                patient.setPatientLastName(rs.getString("PATIENTLASTNAME"));
	                patient.setPatientGender(rs.getString("PATIENTGENDER"));
	                patient.setPatientDOB(rs.getDate("PATIENTDATEOFBIRTH"));
	                patient.setPatientMobile1(rs.getString("PATIENTMOBILE1"));
	                patient.setPatientBloodGroup(rs.getString("PATIENTBLOODGROUP"));
	                patient.setPatientAge(rs.getInt("PATIENTAGE"));
	                list.add(patient);
	                
	            }
	            return list;
	            
	            }
	    });
	}
	

	
}