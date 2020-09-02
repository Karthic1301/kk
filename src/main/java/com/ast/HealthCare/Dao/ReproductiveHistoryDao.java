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

import com.ast.HealthCare.Pojo.ReproductiveHistoryPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class ReproductiveHistoryDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;

	JpaConfiguration jpa = new JpaConfiguration();

	ReproductiveHistoryDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("ReproductiveHistoryDao cosntructor jdbc "+this.jdbcTemplate);
	}


	public Boolean addReproductiveHistory(ReproductiveHistoryPojo reproductiveHistory) {
		
		String sql1 = "SELECT * FROM reproductivehistory WHERE PATIENTID = ?";
	 return jdbcTemplate.query(sql1,  new Object[] { reproductiveHistory.getPatientId() }, new ResultSetExtractor<Boolean>() {
	        public Boolean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("search by doctor id");
	        	//List<PatientPojo> list = new  ArrayList<PatientPojo>();
            	
	        	if(rs.next()) {
	        		
	        		return updateReproductiveHistory(reproductiveHistory);
	            	
	            } else {
		String sql="INSERT INTO reproductivehistory (PATIENTID,MATUREDDATE,REGULARPERIODS,MENOPAUSEAGE,MISCARRIAGETIMES,ABORTIONSTIMES) values(?,?,?,?,?,?)";
		
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, reproductiveHistory.getPatientId());
				ps.setDate(2, reproductiveHistory.getMatureDate());
				ps.setString(3, reproductiveHistory.getRegularPeriods());
				ps.setInt(4, reproductiveHistory.getMenopauseAge());
				ps.setInt(5, reproductiveHistory.getMiscarriageTimes());
				ps.setInt(6, reproductiveHistory.getAbortionsTimes());
			    return ps.execute();		    
			}
		});
	            }
	        }
	    });
	}

	public List<ReproductiveHistoryPojo> reproductiveHistoryAll() {
		String sql = "SELECT * FROM reproductivehistory";
	
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ReproductiveHistoryPojo>>() {
	        public List<ReproductiveHistoryPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ReproductiveHistoryPojo> list = new  ArrayList<ReproductiveHistoryPojo>();
	           // if(rs.wasNull())
	        	while(rs.next()) {
	            	ReproductiveHistoryPojo rh = new ReproductiveHistoryPojo();
	            	rh.setPatientId(rs.getString("PATIENTID"));
	            	rh.setMatureDate(rs.getDate("MATUREDDATE"));
	            	rh.setRegularPeriods(rs.getString("REGULARPERIODS"));
	            	rh.setMenopauseAge(rs.getInt("MENOPAUSEAGE"));
	            	rh.setMiscarriageTimes(rs.getInt("MISCARRIAGETIMES"));
	            	rh.setAbortionsTimes(rs.getInt("ABORTIONSTIMES"));
	            	list.add(rh);
	            }
	            return list;
	            }
	    });	
		
	}


	public ReproductiveHistoryPojo reproductiveHistorySearchByPatientId(String pid) {
		String sql = "SELECT * FROM reproductivehistory WHERE PATIENTID = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<ReproductiveHistoryPojo>() {
	        public ReproductiveHistoryPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("search by doctor id");
	        	//List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	ReproductiveHistoryPojo rh = new ReproductiveHistoryPojo();
            	
	        	while(rs.next()) {
	        		rh.setPatientId(rs.getString("PATIENTID"));
	            	rh.setMatureDate(rs.getDate("MATUREDDATE"));
	            	rh.setRegularPeriods(rs.getString("REGULARPERIODS"));
	            	rh.setMenopauseAge(rs.getInt("MENOPAUSEAGE"));
	            	rh.setMiscarriageTimes(rs.getInt("MISCARRIAGETIMES"));
	            	rh.setAbortionsTimes(rs.getInt("ABORTIONSTIMES"));
	            	
	            }
	            return rh;
	            
	            }
	    });
	}

	// CREATE TABLE reproductivehistory(PATIENTID TEXT,MATUREDDATE
	// DATE,REGULARPERIODS TEXT,MENOPAUSEAGE INTEGER,
	// MISCARRIAGETIMES INTEGER,ABORTIONSTIMES INTEGER);

	public Boolean updateReproductiveHistory(ReproductiveHistoryPojo reproductiveHistory) {
		String query="UPDATE reproductivehistory set MATUREDDATE = ? ,REGULARPERIODS = ? ,MENOPAUSEAGE = ? ,MISCARRIAGETIMES = ? ,ABORTIONSTIMES = ? WHERE PATIENTID = ?";
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
			ps.setDate(1, reproductiveHistory.getMatureDate());
			ps.setString(2, reproductiveHistory.getRegularPeriods());
			ps.setInt(3, reproductiveHistory.getMenopauseAge());
			ps.setInt(4, reproductiveHistory.getMiscarriageTimes());
			ps.setInt(5, reproductiveHistory.getAbortionsTimes());
			ps.setString(6, reproductiveHistory.getPatientId());
			return ps.execute();
		}
		});
}


	public int reproductiveHistoryDelete(String pid) {
		return jdbcTemplate.update("DELETE FROM reproductivehistory WHERE PATIENTID = ?", new Object[] { pid });
}

}
