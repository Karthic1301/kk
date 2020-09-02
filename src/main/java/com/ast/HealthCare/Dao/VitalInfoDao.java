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

import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.VitalInfoPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class VitalInfoDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	@Autowired
	PatientDao patDao;
	
	@Autowired
	AppointmentDao appDao;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	VitalInfoDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
		 System.out.println("VitalInfoDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addVitalInfo(final VitalInfoPojo vital) {
		// TODO Auto-generated method stub
		
		//CREATE TABLE  vitalinformation(VITALINFOID SERIAL,BP TEXT,HEIGHT INTEGER,WEIGHT NUMERIC,
		//BMI NUMERIC,WC INTEGER,TEMPERATURE NUMERIC,PULSE NUMERIC,DIA NUMERIC,SYS NUMERIC,
		//DOA NUMERIC,DOS NUMERIC,PATIENTID TEXT,VITALINFO_DATE DATE);
		String sql1="INSERT INTO vitalinformation (BP, HEIGHT, WEIGHT, BMI, WC, TEMPERATURE,"
				+ " PULSE, DIA, SYS, DOA, DOS, PATIENTID, VITALINFO_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Boolean eke = jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, vital.getBp());
				ps.setFloat(2, vital.getHeight());
				ps.setFloat(3, vital.getWeight());
				ps.setFloat(4, vital.getBmi());
				ps.setInt(5, vital.getWc());
				ps.setFloat(6, vital.getTemperature());
				ps.setInt(7, vital.getPulse());
				ps.setInt(8, vital.getDia());
				ps.setInt(9, vital.getSys());
				ps.setInt(10, vital.getDoa());
				ps.setInt(11, vital.getDos());
				ps.setString(12, vital.getPatientId());
				ps.setDate(13, vital.getVitalInfo_Date());
				
			    return ps.execute();
			    
			}
		});
		// appDao.clearapp(vital.getPatientId(),vital.getVitalInfo_Date());
		return eke;
	}

	public List<PatientPojo> vitalInfoAll(Date vdate) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM vitalinformation where vitalinfo_date = '"+vdate+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientPojo>>() {
	        public List<PatientPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientPojo> list = new  ArrayList<PatientPojo>();
	            while(rs.next()) {
	            	String pid = rs.getString("PATIENTID");
	            	
	                String sqlq = "SELECT * FROM prescriptionmaster where patientid = '"+pid+"' and consultingdate = '"+vdate+"'";
	            	Boolean dss = jdbcTemplate.query(sqlq, new ResultSetExtractor<Boolean>() {
	        	        public Boolean extractData(ResultSet rs) throws SQLException,
	        	                DataAccessException {
	        	        	if(rs.next())
	        	        		return false;
	        	        	else
	        	        		return true;
	        	        }
	            	});
	            	
	            	if(dss)
	            	{        	
	            	PatientPojo dt = new PatientPojo();
	            	dt = patDao.patientSearchById(pid);
	                list.add(dt);
	            	}
	            }
	            return list;
	            }
	    });
	}

	public boolean vitalInfoUpdate(final VitalInfoPojo vital) {
		// TODO Auto-generated method stub

		System.out.println("vic "+vital);

		//CREATE TABLE  vitalinformation(VITALINFOID SERIAL,BP TEXT,HEIGHT INTEGER,WEIGHT NUMERIC,
		//BMI NUMERIC,WC INTEGER,TEMPERATURE NUMERIC,PULSE NUMERIC,DIA NUMERIC,SYS NUMERIC,
		//DOA NUMERIC,DOS NUMERIC,PATIENTID TEXT,VITALINFO_DATE DATE);
		String ek = "SELECT * FROM vitalinformation where PATIENTID = '"+vital.getPatientId()+"' and VITALINFO_DATE = '"+vital.getVitalInfo_Date()+"'";
		Boolean ekk = jdbcTemplate.query(ek, new ResultSetExtractor<Boolean>() {
	        public Boolean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	
	        	if(rs.next())
	        		return true;
	        	else
	        		return false;
	        }
		});
		
		if(ekk)
		{
		String query="UPDATE vitalinformation set BP = ?, HEIGHT = ?, WEIGHT = ?, BMI = ?, WC = ?, TEMPERATURE = ?, PULSE = ?, DIA = ?, SYS = ?, DOA = ?, DOS = ?, VITALINFO_DATE = ? WHERE VITALINFOID = ? and PATIENTID = ?";
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, vital.getBp());
			ps.setFloat(2, vital.getHeight());
			ps.setFloat(3, vital.getWeight());
			ps.setFloat(4, vital.getBmi());
			ps.setInt(5, vital.getWc());
			ps.setFloat(6, vital.getTemperature());
			ps.setInt(7, vital.getPulse());
			ps.setInt(8, vital.getDia());
			ps.setInt(9, vital.getSys());
			ps.setInt(10, vital.getDoa());
			ps.setInt(11, vital.getDos());
			ps.setDate(12, vital.getVitalInfo_Date());
			ps.setInt(13, vital.getVitalInfoId());
			ps.setString(14, vital.getPatientId());
		    return ps.execute();
		}
		});
		}
		else
		{
			return addVitalInfo(vital);
		}
	}

	public VitalInfoPojo searchByPidAndDate(String pid, Date vitaldate) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM vitalinformation WHERE PATIENTID = ? and VITALINFO_DATE = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid, vitaldate }, new ResultSetExtractor<VitalInfoPojo>() {
	        public VitalInfoPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii vital");
	        	//List<VitalInfoPojo> list = new  ArrayList<VitalInfoPojo>();
	        	//PatientPojo patient = new PatientPojo();
	        	VitalInfoPojo dt = new VitalInfoPojo();
                
	        	while(rs.next()) {
	        		
	        		dt.setVitalInfoId(rs.getInt("VITALINFOID"));
	            	dt.setBp(rs.getString("BP"));
	            	dt.setHeight(rs.getFloat("HEIGHT"));
	            	dt.setWeight(rs.getFloat("WEIGHT"));
	            	dt.setBmi(rs.getFloat("BMI"));
	            	dt.setWc(rs.getInt("WC"));
	            	dt.setTemperature(rs.getFloat("TEMPERATURE"));
	            	dt.setPulse(rs.getInt("PULSE"));
	            	dt.setDia(rs.getInt("DIA"));
	            	dt.setSys(rs.getInt("SYS"));
	            	dt.setDoa(rs.getInt("DOA"));
	            	dt.setDos(rs.getInt("DOS"));
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setVitalInfo_Date(rs.getDate("VITALINFO_DATE"));
	            }     
            	return dt;
                }
	    });
	}
	
	public VitalInfoPojo searchByPidLatestDate(String pid) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM vitalinformation WHERE PATIENTID = ? ORDER BY VITALINFO_DATE DESC LIMIT 1";
		System.out.println(sql);
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<VitalInfoPojo>() {
	        public VitalInfoPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii vital");
	        	//List<VitalInfoPojo> list = new  ArrayList<VitalInfoPojo>();
	        	//PatientPojo patient = new PatientPojo();
	        	VitalInfoPojo dt = new VitalInfoPojo();
                
	        	while(rs.next()) {
	        		
	        		dt.setVitalInfoId(rs.getInt("VITALINFOID"));
	            	dt.setBp(rs.getString("BP"));
	            	dt.setHeight(rs.getFloat("HEIGHT"));
	            	dt.setWeight(rs.getFloat("WEIGHT"));
	            	dt.setBmi(rs.getFloat("BMI"));
	            	dt.setWc(rs.getInt("WC"));
	            	dt.setTemperature(rs.getFloat("TEMPERATURE"));
	            	dt.setPulse(rs.getInt("PULSE"));
	            	dt.setDia(rs.getInt("DIA"));
	            	dt.setSys(rs.getInt("SYS"));
	            	dt.setDoa(rs.getInt("DOA"));
	            	dt.setDos(rs.getInt("DOS"));
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setVitalInfo_Date(rs.getDate("VITALINFO_DATE"));
	            }     
            	return dt;
                }
	    });
	}
	
	

	public List<VitalInfoPojo> searchByPatientId(String pid) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM vitalinformation WHERE PATIENTID = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<VitalInfoPojo>>() {
	        public List<VitalInfoPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<VitalInfoPojo> list = new  ArrayList<VitalInfoPojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {

	        		VitalInfoPojo dt = new VitalInfoPojo();
	                
	        		dt.setVitalInfoId(rs.getInt("VITALINFOID"));
	            	dt.setBp(rs.getString("BP"));
	            	dt.setHeight(rs.getFloat("HEIGHT"));
	            	dt.setWeight(rs.getFloat("WEIGHT"));
	            	dt.setBmi(rs.getFloat("BMI"));
	            	dt.setWc(rs.getInt("WC"));
	            	dt.setTemperature(rs.getFloat("TEMPERATURE"));
	            	dt.setPulse(rs.getInt("PULSE"));
	            	dt.setDia(rs.getInt("DIA"));
	            	dt.setSys(rs.getInt("SYS"));
	            	dt.setDoa(rs.getInt("DOA"));
	            	dt.setDos(rs.getInt("DOS"));
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setVitalInfo_Date(rs.getDate("VITALINFO_DATE"));
	             
	                list.add(dt);
	            }
	            return list;
	            }
	    });
	}

	public VitalInfoPojo searchByVitalInfoId(String vid) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM vitalinformation WHERE VITALINFOID = ?";
		return jdbcTemplate.query(sql,  new Object[] { vid }, new ResultSetExtractor<VitalInfoPojo>() {
	        public VitalInfoPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");

        		VitalInfoPojo dt = new VitalInfoPojo();

        		if(rs.next()) {

	        		dt.setVitalInfoId(rs.getInt("VITALINFOID"));
	            	dt.setBp(rs.getString("BP"));
	            	dt.setHeight(rs.getFloat("HEIGHT"));
	            	dt.setWeight(rs.getFloat("WEIGHT"));
	            	dt.setBmi(rs.getFloat("BMI"));
	            	dt.setWc(rs.getInt("WC"));
	            	dt.setTemperature(rs.getFloat("TEMPERATURE"));
	            	dt.setPulse(rs.getInt("PULSE"));
	            	dt.setDia(rs.getInt("DIA"));
	            	dt.setSys(rs.getInt("SYS"));
	            	dt.setDoa(rs.getInt("DOA"));
	            	dt.setDos(rs.getInt("DOS"));
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setVitalInfo_Date(rs.getDate("VITALINFO_DATE"));
	            	
	            	return dt;
	        	}
	        	else
	        		return dt;
	            }
	    });
	}

	public int vitalInfoDelete(String pno) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM vitalinformation WHERE VITALINFOID = ?", new Object[] { pno });
	}
	
	public int vitalInfoDelete1(String pid) {
		// TODO Auto-generated method stub
		System.out.println("vitalinfor del");
		return jdbcTemplate.update("DELETE FROM vitalinformation WHERE PATIENTID ='"+pid+"'");
	}
	
	
}
