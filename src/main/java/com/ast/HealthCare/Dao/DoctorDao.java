package com.ast.HealthCare.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.DoctorPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class DoctorDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	@Autowired()
	DoctorTestMasterDao dtmDao;
	@Autowired
	DoctorTestMasterDao doctorTestMasterDao;
	@Autowired
	SettingDao settingDao;
	
//	CREATE TABLE patientmaster(PATIENTID INTEGER,PATIENTFIRSTNAME TEXT,PATIENTMIDDLENAME TEXT,PATIENTLASTNAME TEXT,
//	PATIENTGENDER TEXT,PATIENTDATEOFBIRTH DATE,PATIENTBLOODGROUP TEXT,PATIENTADDRESS1 TEXT,PATIENTADDRESS2 TEXT,
//	PATIENTMOBILE1 TEXT,PATIENTMOBILE2 TEXT,HOSPITALCODE TEXT,YEAR SMALLINT,PATIENTSERIALID TEXT,PATIENTPHOTO BYTEA,
//	PATIENTRELATIONNAME TEXT,PATIENTRELATIONSHIP TEXT,PATIENTRELATIONID TEXT,PATIENTMAILID TEXT,PATIENTUNIQUEID TEXT,PRIMARY KEY(PATIENTID));
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	DoctorDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("DoctorDao constructor jdbc "+this.jdbcTemplate);
	}
	
	
	public String register(final DoctorPojo doctor) {
		
		System.out.println("coming dao doctor");
		System.out.println("in dao");
	    System.out.println("doc  "+doctor);
	    String hoscode="";
        Calendar now = Calendar.getInstance();   // Gets the current date and time
        final int year = now.get(Calendar.YEAR); 
        
        String sql1="Select DOCTORSERIALID  from doctormaster order by DOCTORSERIALID DESC";
        List<Long> rs =jdbcTemplate.query(sql1 , new ResultSetExtractor<List<Long>>() {
	        public List<Long> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
    	System.out.println("oiighhghg");
    	List<Long> list = new  ArrayList<Long>();
        if (rs.next()) {
        	System.out.println("oii1");
            list.add(rs.getLong("DOCTORSERIALID"));
            return list;
        }
        else {
        	System.out.println("else");
        	List<Long> paa = new  ArrayList<Long>();
        	paa.add((long) 0);
             return paa;
    }
	        }
        });
        
        if(!(rs.get(rs.size()-1) == 0))
        {
       
        Long num = rs.get(rs.size()-1);
        long num1=num+1;
        System.out.println("max="+num1);
        
        String num2;
        if(num1<10)
        {
        	num2="0000000"+num1;
        }
        else if(num1<100)
        {
        	 num2="000000"+num1;
        }
        else if(num1<1000)
        {
        	 num2="00000"+num1;
        }
        else if(num1<10000)
        {
        	 num2="00000"+num1;
        }
        else if(num1<100000)
        {
        	 num2="0000"+num1;
        }
        else if(num1<1000000)
        {
        	 num2="000"+num1;
        }else if(num1<10000000)
        {
       	 num2="00"+num1;
       }
       else if(num1<100000000)
        {
        	num2="0"+num1;
        }
       else
    	   num2=""+num1;
       
        System.out.println(num2);
        boolean check=true;
        String sql = "SELECT * FROM specialisationmaster";
	    check=jdbcTemplate.query(sql, new ResultSetExtractor <Boolean>() {
	        public Boolean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	            while(rs.next()) {
	            	System.out.println("oii1");
	                ///CREATE TABLE specialisationmaster(SPECIALISATIONID SERIAL,
	            	//SPECIALISATIONNAME TEXT,SPECIALISATIONCODE TEXT,PRIMARY KEY(SPECIALISATIONID));
	            	System.out.println("special"+rs.getString("SPECIALISATIONNAME").toLowerCase() +"and"+doctor.getDoctorSpeciality().toLowerCase());
	            	if(rs.getString("SPECIALISATIONNAME").equalsIgnoreCase(doctor.getDoctorSpeciality()))
	            	{
	            		System.out.println("special don't add");
	            		return false;
	            	}
	            }
	           
	            return true;
	            
	            }
	    });
	    if(check) 
        {
        	String sql12 = "INSERT INTO specialisationmaster (SPECIALISATIONNAME, SPECIALISATIONCODE) values(?,?)";
        	
    	    jdbcTemplate.execute(sql12,new PreparedStatementCallback<Boolean>(){  
    			public Boolean doInPreparedStatement(PreparedStatement ps)  
    			        throws SQLException, DataAccessException {  
    				ps.setString(1, doctor.getDoctorSpeciality());
    				ps.setString(2, null);
    				
    			    return ps.execute();
    			    
    			}
    		});
        }
        final String  pid=hoscode+year+num2;
		
	String query="INSERT INTO doctormaster (DOCTORID,DOCTORNAME,DOCTORCODE,DOCTORGENDER,DOCTORDATEOFBIRTH ,DOCTORSPECIALITY ,DOCTORQUALIFICATION ,DOCTORADDRESS ,DOCTORMOBILE1,DOCTORMOBILE2,DOCTORPANAME,DOCTORPAMOBILENO ,HOSPITALCODE,DOCTORSPECCODE,DOCTORPHOTO,TIMEPERPATIENT,DOCTORMAILID,DOCTORUNIQUEID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	      
    return jdbcTemplate.execute(query,new PreparedStatementCallback<String>(){  
    public String doInPreparedStatement(PreparedStatement ps)  
            throws SQLException, DataAccessException {  
        System.out.println("doctoroda pid "+pid);
        ps.setString(1,pid);  

        ps.setString(2,doctor.getDoctorName());
        ps.setString(3,doctor.getDoctorCode());
        ps.setString(4,doctor.getDoctorGender());
/* 
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date parsed;
        java.sql.Date sql = null;
    	try {
    		parsed = format.parse(doctor.getDoctorDateOfBirth());
    		sql = new java.sql.Date(parsed.getTime());
    	    
    	} catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        System.out.println("date"+doctor.getDoctorDateOfBirth());
        
        ps.setDate(5,sql); */
        System.out.println("date da hari"+doctor.getDoctorDateOfBirth());
		ps.setString(5, doctor.getDoctorDateOfBirth());
        //java.sql.Date sdob = java.sql.Date.valueOf(patient.getPatientDOB());
        //System.out.println(sdob);spec qua add mob1 mob2
        ps.setString(6, doctor.getDoctorSpeciality());
        ps.setString(7, doctor.getDoctorQualification());
        ps.setString(8, doctor.getDoctorAddress());
        ps.setString(9, doctor.getDoctorMobile1());
        ps.setString(10,doctor.getDoctorMobile2());
//DOCTORPANAME,DOCTORPAMOBILENO ,HOSPITALCODE,DOCTORSPECCODE,DOCTORPHOTO,TIMEPERPATIENT,DOCTORMAILID,DOCTORUNIQUEID
        ps.setString(11, doctor.getDoctorPAName());
        ps.setString(12, doctor.getDoctorPAMobileNo());
        ps.setString(13, doctor.getDoctorHospitalCode());
        ps.setString(14, doctor.getDoctorSpecCode());
        ps.setString(15, doctor.getDoctorPhoto());
        ps.setInt(16, doctor.getTimePerPatient());
        ps.setString(17, doctor.getDoctorMailId());
        ps.setString(18, doctor.getDoctorUniqueId());
        
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
	long num1=1;
    System.out.println("max="+num1);
    
    String num2;
    if(num1<10)
    {
    	num2="0000000"+num1;
    }
    else if(num1<100)
    {
    	 num2="000000"+num1;
    }
    else if(num1<1000)
    {
    	 num2="00000"+num1;
    }
    else if(num1<10000)
    {
    	 num2="00000"+num1;
    }
    else if(num1<100000)
    {
    	 num2="0000"+num1;
    }
    else if(num1<1000000)
    {
    	 num2="000"+num1;
    }else if(num1<10000000)
    {
   	 num2="00"+num1;
   }
   else if(num1<100000000)
    {
    	num2="0"+num1;
    }
   else
	   num2=""+num1;
   
    System.out.println(num2);
    boolean check=true;
    String sql = "SELECT * FROM specialisationmaster";
    check=jdbcTemplate.query(sql, new ResultSetExtractor <Boolean>() {
        public Boolean extractData(ResultSet rs) throws SQLException,
                DataAccessException {
        	System.out.println("oii");
            while(rs.next()) {
            	System.out.println("oii1");
                ///CREATE TABLE specialisationmaster(SPECIALISATIONID SERIAL,
            	//SPECIALISATIONNAME TEXT,SPECIALISATIONCODE TEXT,PRIMARY KEY(SPECIALISATIONID));
            	System.out.println("special"+rs.getString("SPECIALISATIONNAME").toLowerCase() +"and"+doctor.getDoctorSpeciality().toLowerCase());
            	if(rs.getString("SPECIALISATIONNAME").equalsIgnoreCase(doctor.getDoctorSpeciality()))
            	{
            		System.out.println("special don't add");
            		return false;
            	}
            }
           
            return true;
            
            }
    });
    if(check) 
    {
    	String sql12 = "INSERT INTO specialisationmaster (SPECIALISATIONNAME, SPECIALISATIONCODE) values(?,?)";
    	
	    jdbcTemplate.execute(sql12,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, doctor.getDoctorSpeciality());
				ps.setString(2, null);
				
			    return ps.execute();
			    
			}
		});
    }
    final String  pid=hoscode+year+num2;
    String query="INSERT INTO doctormaster (DOCTORID,DOCTORNAME,DOCTORCODE,DOCTORGENDER,DOCTORDATEOFBIRTH ,DOCTORSPECIALITY ,DOCTORQUALIFICATION ,DOCTORADDRESS ,DOCTORMOBILE1,DOCTORMOBILE2,DOCTORPANAME,DOCTORPAMOBILENO ,HOSPITALCODE,DOCTORSPECCODE,DOCTORPHOTO,TIMEPERPATIENT,DOCTORMAILID,DOCTORUNIQUEID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    return jdbcTemplate.execute(query,new PreparedStatementCallback<String>(){  
    public String doInPreparedStatement(PreparedStatement ps)  
            throws SQLException, DataAccessException {  
        System.out.println("doctoroda pid "+pid);
        ps.setString(1,pid);  

        ps.setString(2,doctor.getDoctorName());
        ps.setString(3,doctor.getDoctorCode());
        ps.setString(4,doctor.getDoctorGender());

/*         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date parsed;
        java.sql.Date sql = null;
    	try {
    		parsed = format.parse(doctor.getDoctorDateOfBirth());
    		sql = new java.sql.Date(parsed.getTime());
    	    
    	} catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        System.out.println("date"+doctor.getDoctorDateOfBirth());
        
        ps.setDate(5,sql);  */
        System.out.println("else date da hari"+doctor.getDoctorDateOfBirth());
		ps.setString(5,doctor.getDoctorDateOfBirth());
        //java.sql.Date sdob = java.sql.Date.valueOf(patient.getPatientDOB());
        //System.out.println(sdob);spec qua add mob1 mob2
        ps.setString(6, doctor.getDoctorSpeciality());
        ps.setString(7, doctor.getDoctorQualification());
        ps.setString(8, doctor.getDoctorAddress());
        ps.setString(9, doctor.getDoctorMobile1());
        ps.setString(10,doctor.getDoctorMobile2());
//DOCTORPANAME,DOCTORPAMOBILENO ,HOSPITALCODE,DOCTORSPECCODE,DOCTORPHOTO,TIMEPERPATIENT,DOCTORMAILID,DOCTORUNIQUEID
        ps.setString(11, doctor.getDoctorPAName());
        ps.setString(12, doctor.getDoctorPAMobileNo());
        ps.setString(13, doctor.getDoctorHospitalCode());
        ps.setString(14, doctor.getDoctorSpecCode());
        ps.setString(15, doctor.getDoctorPhoto());
        ps.setInt(16, doctor.getTimePerPatient());
        ps.setString(17, doctor.getDoctorMailId());
        ps.setString(18, doctor.getDoctorUniqueId());
        
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
}
	}


	public List<DoctorPojo> doctorAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM doctormaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<DoctorPojo>>() {
	        public List<DoctorPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<DoctorPojo> list = new  ArrayList<DoctorPojo>();
	            while(rs.next()) {
	            	System.out.println("oii1");
	                DoctorPojo doctor = new DoctorPojo();
	                //CREATE TABLE doctormaster(DOCTORID TEXT,DOCTORNAME TEXT,DOCTORCODE TEXT,
	                //DOCTORGENDER TEXT,DOCTORDATEOFBIRTH TEXT,DOCTORSPECIALITY TEXT,
	                //DOCTORQUALIFICATION TEXT,DOCTORADDRESS TEXT,DOCTORMOBILE1 TEXT,DOCTORMOBILE2 TEXT,
	                //DOCTORPANAME TEXT,DOCTORPAMOBILENO TEXT,HOSPITALCODE TEXT,
	                //DOCTORSPECCODE TEXT,DOCTORPHOTO TEXT,DOCTORSERIALID SERIAL,
	                //TIMEPERPATIENT INTEGER,DOCTORMAILID TEXT,DOCTORUNIQUEID TEXT,PRIMARY KEY(DOCTORID));

	                doctor.setDoctorId(rs.getString("DOCTORID"));
	                doctor.setDoctorName(rs.getString("DOCTORNAME"));
	                doctor.setDoctorCode(rs.getString("DOCTORCODE"));
	                doctor.setDoctorGender(rs.getString("DOCTORGENDER"));
	                doctor.setDoctorDateOfBirth(rs.getString("DOCTORDATEOFBIRTH"));
	                doctor.setDoctorSpeciality(rs.getString("DOCTORSPECIALITY"));
	                doctor.setDoctorQualification(rs.getString("DOCTORQUALIFICATION"));
	                doctor.setDoctorAddress(rs.getString("DOCTORADDRESS"));
	                doctor.setDoctorMobile1(rs.getString("DOCTORMOBILE1"));
	                doctor.setDoctorMobile2(rs.getString("DOCTORMOBILE2"));
	                doctor.setDoctorPAName(rs.getString("DOCTORPANAME"));
	                doctor.setDoctorPAMobileNo(rs.getString("DOCTORPAMOBILENO"));
	                doctor.setDoctorHospitalCode(rs.getString("HOSPITALCODE"));
	                doctor.setDoctorSpecCode(rs.getString("DOCTORSPECCODE"));
	                doctor.setDoctorPhoto(rs.getString("DOCTORPHOTO"));
	                doctor.setTimePerPatient(rs.getInt("TIMEPERPATIENT"));
	                doctor.setDoctorMailId(rs.getString("DOCTORMAILID"));
	                doctor.setDoctorUniqueId(rs.getString("DOCTORUNIQUEID"));
	                doctor.setDoctorSerialId(rs.getInt("DOCTORSERIALID"));
	                doctor.setDoctorTestMaster(doctorTestMasterDao.doctorTestMasterByDoctorId(rs.getString("DOCTORID")));
	                doctor.setSettingPojo(settingDao.settingByDoctorId(rs.getString("DOCTORID")));
	                list.add(doctor);
	            }
	            return list;
	            
	            }
	    });

	}


	public int doctorDelete(String pid) {
		// TODO Auto-generated method stub
		
		return jdbcTemplate.update("DELETE FROM doctormaster WHERE DOCTORID = ?", new Object[] { pid });
	}


	public boolean doctorUpdate(final DoctorPojo doctor) {
		// TODO Auto-generated method stub
		System.out.println("doc update "+doctor);
		
		//CREATE TABLE doctormaster(DOCTORID TEXT,DOCTORNAME TEXT,DOCTORCODE TEXT,
        //DOCTORGENDER TEXT,DOCTORDATEOFBIRTH TEXT,DOCTORSPECIALITY TEXT,
        //DOCTORQUALIFICATION TEXT,DOCTORADDRESS TEXT,DOCTORMOBILE1 TEXT,DOCTORMOBILE2 TEXT,
        //DOCTORPANAME TEXT,DOCTORPAMOBILENO TEXT,HOSPITALCODE TEXT,
        //DOCTORSPECCODE TEXT,DOCTORPHOTO TEXT,DOCTORSERIALID SERIAL,
        //TIMEPERPATIENT INTEGER,DOCTORMAILID TEXT,DOCTORUNIQUEID TEXT,PRIMARY KEY(DOCTORID));

		 boolean check=true;
	        String sql = "SELECT * FROM specialisationmaster";
		    check=jdbcTemplate.query(sql, new ResultSetExtractor <Boolean>() {
		        public Boolean extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	System.out.println("oii");
		            while(rs.next()) {
		            	System.out.println("oii1");
		                ///CREATE TABLE specialisationmaster(SPECIALISATIONID SERIAL,
		            	//SPECIALISATIONNAME TEXT,SPECIALISATIONCODE TEXT,PRIMARY KEY(SPECIALISATIONID));
		            	System.out.println("special"+rs.getString("SPECIALISATIONNAME").toLowerCase() +"and"+doctor.getDoctorSpeciality().toLowerCase());
		            	if(rs.getString("SPECIALISATIONNAME").equalsIgnoreCase(doctor.getDoctorSpeciality()))
		            	{
		            		System.out.println("special don't add");
		            		return false;
		            	}
		            }
		           
		            return true;
		            
		            }
		    });
		    if(check) 
	        {
	        	String sql12 = "INSERT INTO specialisationmaster (SPECIALISATIONNAME, SPECIALISATIONCODE) values(?,?)";
	        	
	    	    jdbcTemplate.execute(sql12,new PreparedStatementCallback<Boolean>(){  
	    			public Boolean doInPreparedStatement(PreparedStatement ps)  
	    			        throws SQLException, DataAccessException {  
	    				ps.setString(1, doctor.getDoctorSpeciality());
	    				ps.setString(2, null);
	    				
	    			    return ps.execute();
	    			    
	    			}
	    		});
	        }
		String query="UPDATE doctormaster set DOCTORNAME = ?, DOCTORCODE = ?,DOCTORGENDER = ?,DOCTORDATEOFBIRTH = ?,DOCTORSPECIALITY = ?,"+
				"DOCTORQUALIFICATION = ?,DOCTORADDRESS = ?,DOCTORMOBILE1 = ?,DOCTORMOBILE2 = ?,DOCTORPANAME = ?,DOCTORPAMOBILENO = ?,HOSPITALCODE = ?,"+
				"DOCTORSPECCODE = ?,DOCTORPHOTO = ?,TIMEPERPATIENT = ?,DOCTORMAILID = ?,DOCTORUNIQUEID = ? WHERE DOCTORID = ?";
			    
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, doctor.getDoctorName());
		    ps.setString(2, doctor.getDoctorCode());
		    ps.setString(3, doctor.getDoctorGender());
		    ps.setString(4, doctor.getDoctorDateOfBirth());
		    ps.setString(5, doctor.getDoctorSpeciality());
	//"DOCTORQUALIFICATION = ?,DOCTORADDRESS = ?,DOCTORMOBILE1 = ?,DOCTORMOBILE2 = ?,DOCTORPANAME = ?,DOCTORPAMOBILENO = ?,HOSPITALCODE = ?,"+
	//"DOCTORSPECCODE = ?,DOCTORPHOTO = ?,TIMEPERPATIENT = ?,DOCTORMAILID = ?,DOCTORUNIQUEID = ? WHERE DOCTORID = ?";    
		    ps.setString(6, doctor.getDoctorQualification());
		    ps.setString(7, doctor.getDoctorAddress());
		    ps.setString(8, doctor.getDoctorMobile1());
		    ps.setString(9, doctor.getDoctorMobile2());
		    ps.setString(10,doctor.getDoctorPAName());
		    ps.setString(11,doctor.getDoctorPAMobileNo());
		    ps.setString(12,doctor.getDoctorHospitalCode());
		    ps.setString(13,doctor.getDoctorSpecCode());
		    ps.setString(14,doctor.getDoctorPhoto());
		    ps.setInt(15,doctor.getTimePerPatient());
		    ps.setString(16,doctor.getDoctorMailId());
		    ps.setString(17,doctor.getDoctorUniqueId());
		    ps.setString(18,doctor.getDoctorId());
		    return ps.execute();
		}
		});
				
	}


	public DoctorPojo doctorSearchById(String pid) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM doctormaster WHERE DOCTORID = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<DoctorPojo>() {
	        public DoctorPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("search by doctor id");
	        	//List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	DoctorPojo doctor = new DoctorPojo();
                
	        	if(rs.next()) {
	        		
	            	System.out.println("doc search");
	                
	            	doctor.setDoctorId(rs.getString("DOCTORID"));
	                doctor.setDoctorName(rs.getString("DOCTORNAME"));
	                doctor.setDoctorCode(rs.getString("DOCTORCODE"));
	                doctor.setDoctorGender(rs.getString("DOCTORGENDER"));
	                doctor.setDoctorDateOfBirth(rs.getString("DOCTORDATEOFBIRTH"));
	                doctor.setDoctorSpeciality(rs.getString("DOCTORSPECIALITY"));
	                doctor.setDoctorQualification(rs.getString("DOCTORQUALIFICATION"));
	                doctor.setDoctorAddress(rs.getString("DOCTORADDRESS"));
	                doctor.setDoctorMobile1(rs.getString("DOCTORMOBILE1"));
	                doctor.setDoctorMobile2(rs.getString("DOCTORMOBILE2"));
	                doctor.setDoctorPAName(rs.getString("DOCTORPANAME"));
	                doctor.setDoctorPAMobileNo(rs.getString("DOCTORPAMOBILENO"));
	                doctor.setDoctorHospitalCode(rs.getString("HOSPITALCODE"));
	                doctor.setDoctorSpecCode(rs.getString("DOCTORSPECCODE"));
	                doctor.setDoctorPhoto(rs.getString("DOCTORPHOTO"));
	                doctor.setTimePerPatient(rs.getInt("TIMEPERPATIENT"));
	                doctor.setDoctorMailId(rs.getString("DOCTORMAILID"));
	                doctor.setDoctorUniqueId(rs.getString("DOCTORUNIQUEID"));
	                doctor.setDoctorSerialId(rs.getInt("DOCTORSERIALID"));
	                doctor.setDoctorTestMaster(dtmDao.doctorTestMasterByDoctorId(pid));
	                doctor.setSettingPojo(settingDao.settingByDoctorId(rs.getString("DOCTORID")));
	            }
	            return doctor;
	            
	            }
	    });
	
	}


	public List<DoctorPojo> doctorSearchByName(String pname) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM doctormaster WHERE DOCTORNAME LIKE ? ";
		return jdbcTemplate.query(sql,  new Object[] { pname }, new ResultSetExtractor<List<DoctorPojo>>() {
	        public List<DoctorPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<DoctorPojo> list = new  ArrayList<DoctorPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                DoctorPojo doctor = new DoctorPojo();
	                doctor.setDoctorId(rs.getString("DOCTORID"));
	                doctor.setDoctorName(rs.getString("DOCTORNAME"));
	                doctor.setDoctorCode(rs.getString("DOCTORCODE"));
	                doctor.setDoctorGender(rs.getString("DOCTORGENDER"));
	                doctor.setDoctorDateOfBirth(rs.getString("DOCTORDATEOFBIRTH"));
	                doctor.setDoctorSpeciality(rs.getString("DOCTORSPECIALITY"));
	                doctor.setDoctorQualification(rs.getString("DOCTORQUALIFICATION"));
	                doctor.setDoctorAddress(rs.getString("DOCTORADDRESS"));
	                doctor.setDoctorMobile1(rs.getString("DOCTORMOBILE1"));
	                doctor.setDoctorMobile2(rs.getString("DOCTORMOBILE2"));
	                doctor.setDoctorPAName(rs.getString("DOCTORPANAME"));
	                doctor.setDoctorPAMobileNo(rs.getString("DOCTORPAMOBILENO"));
	                doctor.setDoctorHospitalCode(rs.getString("HOSPITALCODE"));
	                doctor.setDoctorSpecCode(rs.getString("DOCTORSPECCODE"));
	                doctor.setDoctorPhoto(rs.getString("DOCTORPHOTO"));
	                doctor.setTimePerPatient(rs.getInt("TIMEPERPATIENT"));
	                doctor.setDoctorMailId(rs.getString("DOCTORMAILID"));
	                doctor.setDoctorUniqueId(rs.getString("DOCTORUNIQUEID"));
	                doctor.setDoctorSerialId(rs.getInt("DOCTORSERIALID"));
	                doctor.setDoctorTestMaster(dtmDao.doctorTestMasterByDoctorId(rs.getString("DOCTORID")));
	                doctor.setSettingPojo(settingDao.settingByDoctorId(rs.getString("DOCTORID")));
	                list.add(doctor);
	                
	            }
	            return list;
	            
	            }
	    });
	}


	public List<DoctorPojo> doctorSearchByPhoneNo(String pno) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM doctormaster WHERE DOCTORMOBILE1 LIKE ? OR DOCTORMOBILE2 LIKE ?";
		return jdbcTemplate.query(sql,  new Object[] { pno }, new ResultSetExtractor<List<DoctorPojo>>() {
	        public List<DoctorPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<DoctorPojo> list = new  ArrayList<DoctorPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                DoctorPojo doctor = new DoctorPojo();
	                doctor.setDoctorId(rs.getString("DOCTORID"));
	                doctor.setDoctorName(rs.getString("DOCTORNAME"));
	                doctor.setDoctorCode(rs.getString("DOCTORCODE"));
	                doctor.setDoctorGender(rs.getString("DOCTORGENDER"));
	                doctor.setDoctorDateOfBirth(rs.getString("DOCTORDATEOFBIRTH"));
	                doctor.setDoctorSpeciality(rs.getString("DOCTORSPECIALITY"));
	                doctor.setDoctorQualification(rs.getString("DOCTORQUALIFICATION"));
	                doctor.setDoctorAddress(rs.getString("DOCTORADDRESS"));
	                doctor.setDoctorMobile1(rs.getString("DOCTORMOBILE1"));
	                doctor.setDoctorMobile2(rs.getString("DOCTORMOBILE2"));
	                doctor.setDoctorPAName(rs.getString("DOCTORPANAME"));
	                doctor.setDoctorPAMobileNo(rs.getString("DOCTORPAMOBILENO"));
	                doctor.setDoctorHospitalCode(rs.getString("HOSPITALCODE"));
	                doctor.setDoctorSpecCode(rs.getString("DOCTORSPECCODE"));
	                doctor.setDoctorPhoto(rs.getString("DOCTORPHOTO"));
	                doctor.setTimePerPatient(rs.getInt("TIMEPERPATIENT"));
	                doctor.setDoctorMailId(rs.getString("DOCTORMAILID"));
	                doctor.setDoctorUniqueId(rs.getString("DOCTORUNIQUEID"));
	                doctor.setDoctorSerialId(rs.getInt("DOCTORSERIALID"));
	                doctor.setDoctorTestMaster(dtmDao.doctorTestMasterByDoctorId(rs.getString("DOCTORID")));
	                doctor.setSettingPojo(settingDao.settingByDoctorId(rs.getString("DOCTORID")));
	                list.add(doctor);
	                
	            }
	            return list;
	            
	            }
	    });

	}


	public List<DoctorPojo> doctorSearchByAll(String all) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM doctormaster WHERE DOCTORNAME LIKE ? or DOCTORID LIKE ? or DOCTORMOBILE1 LIKE ? or DOCTORMOBILE2 LIKE ? ";
		return jdbcTemplate.query(sql,  new Object[] { all,all,all,all }, new ResultSetExtractor<List<DoctorPojo>>() {
	        public List<DoctorPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<DoctorPojo> list = new  ArrayList<DoctorPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                DoctorPojo doctor = new DoctorPojo();
	                doctor.setDoctorId(rs.getString("DOCTORID"));
	                doctor.setDoctorName(rs.getString("DOCTORNAME"));
	                doctor.setDoctorCode(rs.getString("DOCTORCODE"));
	                doctor.setDoctorGender(rs.getString("DOCTORGENDER"));
	                doctor.setDoctorDateOfBirth(rs.getString("DOCTORDATEOFBIRTH"));
	                doctor.setDoctorSpeciality(rs.getString("DOCTORSPECIALITY"));
	                doctor.setDoctorQualification(rs.getString("DOCTORQUALIFICATION"));
	                doctor.setDoctorAddress(rs.getString("DOCTORADDRESS"));
	                doctor.setDoctorMobile1(rs.getString("DOCTORMOBILE1"));
	                doctor.setDoctorMobile2(rs.getString("DOCTORMOBILE2"));
	                doctor.setDoctorPAName(rs.getString("DOCTORPANAME"));
	                doctor.setDoctorPAMobileNo(rs.getString("DOCTORPAMOBILENO"));
	                doctor.setDoctorHospitalCode(rs.getString("HOSPITALCODE"));
	                doctor.setDoctorSpecCode(rs.getString("DOCTORSPECCODE"));
	                doctor.setDoctorPhoto(rs.getString("DOCTORPHOTO"));
	                doctor.setTimePerPatient(rs.getInt("TIMEPERPATIENT"));
	                doctor.setDoctorMailId(rs.getString("DOCTORMAILID"));
	                doctor.setDoctorUniqueId(rs.getString("DOCTORUNIQUEID"));
	                doctor.setDoctorSerialId(rs.getInt("DOCTORSERIALID"));
	                doctor.setDoctorTestMaster(dtmDao.doctorTestMasterByDoctorId(rs.getString("DOCTORID")));
	                doctor.setSettingPojo(settingDao.settingByDoctorId(rs.getString("DOCTORID")));
	                list.add(doctor);
	                
	            }
	            return list;
	            
	            }
	    });
	}


	public List<DoctorPojo> doctorSearchBySpec(String spec) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM doctormaster WHERE DOCTORSPECIALITY LIKE ? ";
		return jdbcTemplate.query(sql,  new Object[] { spec }, new ResultSetExtractor<List<DoctorPojo>>() {
	        public List<DoctorPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<DoctorPojo> list = new  ArrayList<DoctorPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                DoctorPojo doctor = new DoctorPojo();
	                doctor.setDoctorId(rs.getString("DOCTORID"));
	                doctor.setDoctorName(rs.getString("DOCTORNAME"));
	                doctor.setDoctorCode(rs.getString("DOCTORCODE"));
	                doctor.setDoctorGender(rs.getString("DOCTORGENDER"));
	                doctor.setDoctorDateOfBirth(rs.getString("DOCTORDATEOFBIRTH"));
	                doctor.setDoctorSpeciality(rs.getString("DOCTORSPECIALITY"));
	                doctor.setDoctorQualification(rs.getString("DOCTORQUALIFICATION"));
	                doctor.setDoctorAddress(rs.getString("DOCTORADDRESS"));
	                doctor.setDoctorMobile1(rs.getString("DOCTORMOBILE1"));
	                doctor.setDoctorMobile2(rs.getString("DOCTORMOBILE2"));
	                doctor.setDoctorPAName(rs.getString("DOCTORPANAME"));
	                doctor.setDoctorPAMobileNo(rs.getString("DOCTORPAMOBILENO"));
	                doctor.setDoctorHospitalCode(rs.getString("HOSPITALCODE"));
	                doctor.setDoctorSpecCode(rs.getString("DOCTORSPECCODE"));
	                doctor.setDoctorPhoto(rs.getString("DOCTORPHOTO"));
	                doctor.setTimePerPatient(rs.getInt("TIMEPERPATIENT"));
	                doctor.setDoctorMailId(rs.getString("DOCTORMAILID"));
	                doctor.setDoctorUniqueId(rs.getString("DOCTORUNIQUEID"));
	                doctor.setDoctorSerialId(rs.getInt("DOCTORSERIALID"));
	                doctor.setDoctorTestMaster(dtmDao.doctorTestMasterByDoctorId(rs.getString("DOCTORID")));
	                doctor.setSettingPojo(settingDao.settingByDoctorId(rs.getString("DOCTORID")));
	                list.add(doctor);
	                
	            }
	            return list;
	            
	            }
	    });

	}
	
	public List<DoctorPojo> doctorSearchByAllForSMS(String all) {
		// TODO Auto-generated method stub

		String sql = "SELECT DOCTORID,DOCTORNAME,DOCTORMOBILE1 FROM doctormaster WHERE DOCTORNAME LIKE ? or DOCTORID LIKE ? or DOCTORMOBILE1 LIKE ? or DOCTORMOBILE2 LIKE ? ";
		return jdbcTemplate.query(sql,  new Object[] { all,all,all,all }, new ResultSetExtractor<List<DoctorPojo>>() {
	        public List<DoctorPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<DoctorPojo> list = new  ArrayList<DoctorPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                DoctorPojo doctor = new DoctorPojo();
	                doctor.setDoctorId(rs.getString("DOCTORID"));
	                doctor.setDoctorName(rs.getString("DOCTORNAME"));
	                doctor.setDoctorMobile1(rs.getString("DOCTORMOBILE1"));
	                list.add(doctor);
	                
	            }
	            return list;
	            
	            }
	    });
	}

	public List<DoctorPojo> getBasicDetailDoctorAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM doctormaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<DoctorPojo>>() {
	        public List<DoctorPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<DoctorPojo> list = new  ArrayList<DoctorPojo>();
	            while(rs.next()) {
	            	System.out.println("oii1");
	                DoctorPojo doctor = new DoctorPojo();
	                //CREATE TABLE doctormaster(DOCTORID TEXT,DOCTORNAME TEXT,DOCTORCODE TEXT,
	                //DOCTORGENDER TEXT,DOCTORDATEOFBIRTH TEXT,DOCTORSPECIALITY TEXT,
	                //DOCTORQUALIFICATION TEXT,DOCTORADDRESS TEXT,DOCTORMOBILE1 TEXT,DOCTORMOBILE2 TEXT,
	                //DOCTORPANAME TEXT,DOCTORPAMOBILENO TEXT,HOSPITALCODE TEXT,
	                //DOCTORSPECCODE TEXT,DOCTORPHOTO TEXT,DOCTORSERIALID SERIAL,
	                //TIMEPERPATIENT INTEGER,DOCTORMAILID TEXT,DOCTORUNIQUEID TEXT,PRIMARY KEY(DOCTORID));

	                doctor.setDoctorId(rs.getString("DOCTORID"));
	                doctor.setDoctorName(rs.getString("DOCTORNAME"));
	                doctor.setDoctorCode(rs.getString("DOCTORCODE"));
	                doctor.setDoctorGender(rs.getString("DOCTORGENDER"));
	                doctor.setDoctorSpeciality(rs.getString("DOCTORSPECIALITY"));
	                list.add(doctor);
	            }
	            return list;
	            
	            }
	    });

	}

	
	
}
