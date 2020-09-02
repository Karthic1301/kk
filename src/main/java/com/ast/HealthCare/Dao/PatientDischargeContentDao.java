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

import com.ast.HealthCare.Pojo.PatientDischargeContentPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PatientDischargeContentDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	@Autowired()
	BirthDetailsDao bDao;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	PatientDischargeContentDao()
	{
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("PatientDischargeContentDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public int getMaximumIdForBirthDetails() {
		String sql = "SELECT MAX(PATIENTCONTENTID) As ID from PatientDischargeContent WHERE HEADINGMASTERID=14 ";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					System.out.println("Serial Id" + rs.getInt("ID"));
					return rs.getInt("ID");
				}
				return 0;
			}
		});

	}
	
	public boolean addPatientDischargeContent(final List<PatientDischargeContentPojo> dis)
	{
		// TODO Auto-generated method stub
		String sql1="INSERT INTO PatientDischargeContent (PATIENTID,HEADINGMASTERID,DESCRIPTION,PATIENTDISCHARGEID,ORDERNO) values(?,?,?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>()
		{  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				for(int i=0;i<dis.size();i++)
				{
				ps.setString(1, dis.get(i).getPatientId());
				ps.setInt(2, dis.get(i).getHeadingMasterId());
				ps.setString(3, dis.get(i).getDescription());
				ps.setInt(4, dis.get(i).getPatientDischargeId());
				ps.setInt(5, dis.get(i).getOrderNo());
				 ps.execute();
				}
				return true;
			}
		});
	}

	public List<PatientDischargeContentPojo> patientDischargeContentAll() 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientDischargeContent";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientDischargeContentPojo>>()
		{
	        public List<PatientDischargeContentPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientDischargeContentPojo> list = new  ArrayList<PatientDischargeContentPojo>();
	            while(rs.next()) {
	            	PatientDischargeContentPojo dt = new PatientDischargeContentPojo();
	            	dt.setPatientContentId(rs.getInt("PATIENTCONTENTID"));
	            	dt.setHeadingMasterId(rs.getInt("HEADINGMASTERID"));
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setDescription(rs.getString("DESCRIPTION"));
	            	dt.setPatientDischargeId(rs.getInt("PATIENTDISCHARGEID"));
	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	public List<PatientDischargeContentPojo> patientDischargeContentByDischargeId(int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientDischargeContent where PATIENTDISCHARGEID ="+id+" ORDER BY ORDERNO ASC";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientDischargeContentPojo>>()
		{
	        public List<PatientDischargeContentPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientDischargeContentPojo> list = new  ArrayList<PatientDischargeContentPojo>();
	            while(rs.next()) {
	            	PatientDischargeContentPojo dt = new PatientDischargeContentPojo();
	            	dt.setPatientContentId(rs.getInt("PATIENTCONTENTID"));
	            	dt.setHeadingMasterId(rs.getInt("HEADINGMASTERID"));
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setDescription(rs.getString("DESCRIPTION"));
	            	dt.setPatientDischargeId(rs.getInt("PATIENTDISCHARGEID"));
	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	            	dt.setBirthPojo(bDao.birthDetailsByContentMasterId(rs.getInt("PATIENTCONTENTID")));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	public PatientDischargeContentPojo patientDischargeContentById(int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientDischargeContent WHERE HEADINGMASTERID = "+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<PatientDischargeContentPojo>()
		{
	        public PatientDischargeContentPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	PatientDischargeContentPojo dt = new PatientDischargeContentPojo();
	            while(rs.next()) {
	            	
	            	dt.setPatientContentId(rs.getInt("PATIENTCONTENTID"));
	            	dt.setHeadingMasterId(rs.getInt("HEADINGMASTERID"));
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setDescription(rs.getString("DESCRIPTION"));
	            	dt.setPatientDischargeId(rs.getInt("PATIENTDISCHARGEID"));
	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	            	dt.setBirthPojo(bDao.birthDetailsByContentMasterId(rs.getInt("PATIENTCONTENTID")));
	            }
	            return dt;
	            
	            }
	    });
	
	}

	public int patientDischargeContentDelete(int pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM PatientDischargeContent WHERE PATIENTCONTENTID = ? ", new Object[] { pid });
	}
	
	public int patientDischargeContentDeleteByDischargeId(int pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM PatientDischargeContent WHERE PATIENTDISCHARGEID = ? ", new Object[] { pid });
	}

	
	
	public boolean patientDischargeContentUpdate(final PatientDischargeContentPojo dt) 
	{
		// TODO Auto-generated method stub	
		String query="UPDATE PatientDischargeContent set HEADINGMASTERNAME =?,ORDERNO=? where HEADINGMASTERID=?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>()
		{  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getPatientId());
			ps.setInt(2, dt.getHeadingMasterId());
			ps.setString(3, dt.getDescription());
			ps.setInt(5, dt.getPatientDischargeId());			
		    return ps.execute();
		}
		});
	}
}
