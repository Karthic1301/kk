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

import com.ast.HealthCare.Pojo.SocialAndPreventiveHistoryPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class SocialAndPreventiveHistoryDao {

	//protected JdbcTemplate jdbcTemplate;
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	SocialAndPreventiveHistoryDao(){
		/* this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("SocialAndPreventiveHistoryDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addsph(SocialAndPreventiveHistoryPojo imp) {
		//CREATE TABLE  socialandpreventivehistory(SOCIALID SERIAL,PATIENTID TEXT,MARITALSTATUS TEXT,WORKSTATUS TEXT,OCCUPATION TEXT,
		//SMOKING TEXT,PACKSPERDAY INTEGER,
		//SMOKINGDURATION TEXT,ALCOHOL TEXT,DRINKSPERWEEK INTEGER,DRINKSDURATION INTEGER,COFFEEORTEA TEXT,
		//CUPSPERDAY INTEGER,EXCERCISE TEXT,HOURSPERDAY INTEGER,SEATBELTS TEXT,HELMETS TEXT,
		//TWOWHEELERDRIVINGHOURSPERDAY INTEGER,FOURWHEELERDRIVINGHOURSPERDAY INTEGER);
		String sqlh = "SELECT * FROM socialandpreventivehistory WHERE PATIENTID = ?";
	    return jdbcTemplate.query(sqlh,  new Object[] { imp.getPatientId() }, new ResultSetExtractor<Boolean>() {
	        public Boolean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("search by doctor id");
	        	//List<PatientPojo> list = new  ArrayList<PatientPojo>();
            	    
	        	if(rs.next()) {
	        		
	        		return sphUpdate(imp);	        			                
	            } else {
	            

		String sql="INSERT INTO socialandpreventivehistory (SOCIALID,PATIENTID,MARITALSTATUS,WORKSTATUS,OCCUPATION,SMOKING,PACKSPERDAY,SMOKINGDURATION,ALCOHOL,DRINKSPERWEEK,DRINKSDURATION,COFFEEORTEA,CUPSPERDAY,EXCERCISE,HOURSPERDAY,SEATBELTS,HELMETS,TWOWHEELERDRIVINGHOURSPERDAY,FOURWHEELERDRIVINGHOURSPERDAY) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setInt(1, imp.getSocialId());
				ps.setString(2, imp.getPatientId());
				ps.setString(3, imp.getMaritalStatus());
				ps.setString(4, imp.getWorkStatus());
				ps.setString(5, imp.getOccupation());
				ps.setString(6, imp.getSmoking());
				ps.setInt(7, imp.getPacksPerDay());
				ps.setString(8, imp.getSmokingDuration());
				ps.setString(9, imp.getAlcohol());
				ps.setInt(10, imp.getDrinksPerWeek());
				ps.setInt(11, imp.getDrinksDuration());
				ps.setString(12, imp.getCoffeeOrTea());
				ps.setInt(13, imp.getCupsPerDay());
				ps.setString(14, imp.getExcercise());
				ps.setInt(15, imp.getHoursPerDay());
				ps.setString(16, imp.getSeatBelts());
				ps.setString(17, imp.getHelmets());
				ps.setInt(18, imp.getTwoWheelerDrivingHoursPerDay());
				ps.setInt(19, imp.getFourWheelerDrivingHoursPerDay());
			    return ps.execute();
			}
		});
	            }
        
            }
    });
	}
	//CREATE TABLE  socialandpreventivehistory(SOCIALID SERIAL,PATIENTID TEXT,MARITALSTATUS TEXT,WORKSTATUS TEXT,OCCUPATION TEXT,
	//SMOKING TEXT,PACKSPERDAY INTEGER,
	//SMOKINGDURATION TEXT,ALCOHOL TEXT,DRINKSPERWEEK INTEGER,DRINKSDURATION INTEGER,COFFEEORTEA TEXT,
	//CUPSPERDAY INTEGER,EXCERCISE TEXT,HOURSPERDAY INTEGER,SEATBELTS TEXT,HELMETS TEXT,
	//TWOWHEELERDRIVINGHOURSPERDAY INTEGER,FOURWHEELERDRIVINGHOURSPERDAY INTEGER)

	public List<SocialAndPreventiveHistoryPojo> sphAll() {
		String sql = "SELECT * FROM socialandpreventivehistory";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<SocialAndPreventiveHistoryPojo>>() {
	        public List<SocialAndPreventiveHistoryPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<SocialAndPreventiveHistoryPojo> list = new  ArrayList<SocialAndPreventiveHistoryPojo>();
	            while(rs.next()) {
	            	SocialAndPreventiveHistoryPojo sp = new SocialAndPreventiveHistoryPojo();
	            	sp.setSocialId(rs.getInt("SOCIALID"));
	            	sp.setPatientId(rs.getString("PATIENTID"));
	            	sp.setMaritalStatus(rs.getString("MARITALSTATUS"));
	            	sp.setWorkStatus(rs.getString("WORKSTATUS"));
	            	sp.setOccupation(rs.getString("OCCUPATION"));
	            	sp.setSmoking(rs.getString("SMOKING"));
	            	sp.setPacksPerDay(rs.getInt("PACKSPERDAY"));
	            	sp.setSmokingDuration(rs.getString("SMOKINGDURATION"));
	            	sp.setAlcohol(rs.getString("ALCOHOL"));
	            	sp.setDrinksPerWeek(rs.getInt("DRINKSPERWEEK"));
	            	sp.setDrinksDuration(rs.getInt("DRINKSDURATION"));
	            	sp.setCoffeeOrTea(rs.getString("COFFEEORTEA"));
	            	sp.setCupsPerDay(rs.getInt("CUPSPERDAY"));
	            	sp.setExcercise(rs.getString("EXCERCISE"));
	            	sp.setHoursPerDay(rs.getInt("HOURSPERDAY"));
	            	sp.setSeatBelts(rs.getString("SEATBELTS"));
	            	sp.setHelmets(rs.getString("HELMETS"));
	            	sp.setTwoWheelerDrivingHoursPerDay(rs.getInt("TWOWHEELERDRIVINGHOURSPERDAY"));
	            	sp.setFourWheelerDrivingHoursPerDay(rs.getInt("FOURWHEELERDRIVINGHOURSPERDAY"));
	                list.add(sp);
	            }
	            return list;
	            
	            }
	    });
	}
	public boolean sphUpdate(SocialAndPreventiveHistoryPojo imp) {
		String query="UPDATE socialandpreventivehistory set MARITALSTATUS = ? ,WORKSTATUS = ? ,OCCUPATION = ? ,SMOKING = ? ,PACKSPERDAY = ? ,SMOKINGDURATION = ? ,ALCOHOL = ? ,DRINKSPERWEEK = ? ,DRINKSDURATION = ? ,COFFEEORTEA = ? ,CUPSPERDAY = ? ,EXCERCISE = ? ,HOURSPERDAY = ? ,SEATBELTS = ? ,HELMETS = ? ,TWOWHEELERDRIVINGHOURSPERDAY = ? ,FOURWHEELERDRIVINGHOURSPERDAY  = ? where PATIENTID = ?";
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
			ps.setString(1, imp.getMaritalStatus());
			ps.setString(2, imp.getWorkStatus());
			ps.setString(3, imp.getOccupation());
			ps.setString(4, imp.getSmoking());
			ps.setInt(5, imp.getPacksPerDay());
			ps.setString(6, imp.getSmokingDuration());
			ps.setString(7, imp.getAlcohol());
			ps.setInt(8, imp.getDrinksPerWeek());
			ps.setInt(9, imp.getDrinksDuration());
			ps.setString(10, imp.getCoffeeOrTea());
			ps.setInt(11, imp.getCupsPerDay());
			ps.setString(12, imp.getExcercise());
			ps.setInt(13, imp.getHoursPerDay());
			ps.setString(14, imp.getSeatBelts());
			ps.setString(15, imp.getHelmets());
			ps.setInt(16, imp.getTwoWheelerDrivingHoursPerDay());
			ps.setInt(17, imp.getFourWheelerDrivingHoursPerDay());
			ps.setString(18, imp.getPatientId());
			return ps.execute();			    
	}
		});
	}
	public int sphDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM socialandpreventivehistory WHERE PATIENTID = ?", new Object[] { pid });
	}

	public SocialAndPreventiveHistoryPojo sphSearchByPatientId(String pid) {
		String sql = "SELECT * FROM socialandpreventivehistory WHERE PATIENTID = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<SocialAndPreventiveHistoryPojo>() {
	        public SocialAndPreventiveHistoryPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("search by doctor id");
	        	//List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	SocialAndPreventiveHistoryPojo sp = new SocialAndPreventiveHistoryPojo();
            	    
	        	while(rs.next()) {
	        		
	        		sp.setSocialId(rs.getInt("SOCIALID"));
	            	sp.setPatientId(rs.getString("PATIENTID"));
	            	sp.setMaritalStatus(rs.getString("MARITALSTATUS"));
	            	sp.setWorkStatus(rs.getString("WORKSTATUS"));
	            	sp.setOccupation(rs.getString("OCCUPATION"));
	            	sp.setSmoking(rs.getString("SMOKING"));
	            	sp.setPacksPerDay(rs.getInt("PACKSPERDAY"));
	            	sp.setSmokingDuration(rs.getString("SMOKINGDURATION"));
	            	sp.setAlcohol(rs.getString("ALCOHOL"));
	            	sp.setDrinksPerWeek(rs.getInt("DRINKSPERWEEK"));
	            	sp.setDrinksDuration(rs.getInt("DRINKSDURATION"));
	            	sp.setCoffeeOrTea(rs.getString("COFFEEORTEA"));
	            	sp.setCupsPerDay(rs.getInt("CUPSPERDAY"));
	            	sp.setExcercise(rs.getString("EXCERCISE"));
	            	sp.setHoursPerDay(rs.getInt("HOURSPERDAY"));
	            	sp.setSeatBelts(rs.getString("SEATBELTS"));
	            	sp.setHelmets(rs.getString("HELMETS"));
	            	sp.setTwoWheelerDrivingHoursPerDay(rs.getInt("TWOWHEELERDRIVINGHOURSPERDAY"));
	            	sp.setFourWheelerDrivingHoursPerDay(rs.getInt("FOURWHEELERDRIVINGHOURSPERDAY"));
	                
	            }
	            return sp;
	            
	            }
	    });
		}
}
