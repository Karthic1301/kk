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

import com.ast.HealthCare.Pojo.SettingPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class SettingDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	SettingDao()
	{
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("SettingDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public Boolean addSetting(final SettingPojo dis)
	{
		// TODO Auto-generated method stub

		

		String sql1="INSERT INTO SETTING (PAGESIZE,PAGEHEADER,MARGINLEFT,MARGINRIGHT,MARGINTOP,MARGINBOTTOM,DOCTORID) values(?,?,?,?,?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>()
		{  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException { 
				
				ps.setString(1, dis.getPageSize());
				ps.setBoolean(2, dis.getPageHeader());
				ps.setInt(3, dis.getMarginLeft());
				ps.setInt(4, dis.getMarginRight());
				ps.setInt(5, dis.getMarginTop());
				ps.setInt(6,dis.getMarginBottom());
				ps.setString(7, dis.getDoctorId());
								
				return ps.execute();
				
			}
		});
	}

	public List<SettingPojo> settingAll() 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM SETTING";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<SettingPojo>>()
		{
	        public List<SettingPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<SettingPojo> list = new  ArrayList<SettingPojo>();
	            while(rs.next()) {
	            	SettingPojo dt = new SettingPojo();
	            	dt.setSettingId(rs.getInt("SETTINGID"));
	                dt.setPageSize(rs.getString("PAGESIZE"));
	                dt.setPageHeader(rs.getBoolean("PAGEHEADER"));
	                dt.setMarginLeft(rs.getInt("MARGINLEFT"));
	                dt.setMarginRight(rs.getInt("MARGINRIGHT"));
	                dt.setMarginTop(rs.getInt("MARGINTOP"));
	                dt.setMarginBottom(rs.getInt("MARGINBOTTOM"));
	                dt.setDoctorId(rs.getString("DOCTORID"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	
	
	public SettingPojo settingById(int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM SETTING WHERE SETTINGID="+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<SettingPojo>()
		{
	        public SettingPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	SettingPojo dt = new SettingPojo();
	            while(rs.next()) {
	            		dt.setSettingId(rs.getInt("SETTINGID"));
	            	 dt.setPageSize(rs.getString("PAGESIZE"));
		                dt.setPageHeader(rs.getBoolean("PAGEHEADER"));
		                dt.setMarginLeft(rs.getInt("MARGINLEFT"));
		                dt.setMarginRight(rs.getInt("MARGINRIGHT"));
		                dt.setMarginTop(rs.getInt("MARGINTOP"));
		                dt.setMarginBottom(rs.getInt("MARGINBOTTOM"));
		                dt.setDoctorId(rs.getString("DOCTORID"));
		                
	            }
	            return dt;
	            
	            }
	    });
	
	}
	
	public SettingPojo settingByDoctorId(String pid) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM SETTING WHERE DOCTORID = '"+pid+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<SettingPojo>()
		{
	        public SettingPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	SettingPojo dt = new SettingPojo();
	            while(rs.next()) {
	            		dt.setSettingId(rs.getInt("SETTINGID"));
	            	 dt.setPageSize(rs.getString("PAGESIZE"));
		                dt.setPageHeader(rs.getBoolean("PAGEHEADER"));
		                dt.setMarginLeft(rs.getInt("MARGINLEFT"));
		                dt.setMarginRight(rs.getInt("MARGINRIGHT"));
		                dt.setMarginTop(rs.getInt("MARGINTOP"));
		                dt.setMarginBottom(rs.getInt("MARGINBOTTOM"));
		                dt.setDoctorId(rs.getString("DOCTORID"));
	            }
	            return dt;
	            
	            }
	    });
	
	}

	public int settingDelete(int pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM SETTING WHERE SETTINGID = ? ", new Object[] { pid });
	}
	
	public int settingDeleteByDoctorId(String pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM SETTING WHERE DOCTORID = ? ", new Object[] { pid });
	}
	
	
	public boolean settingUpdate(final SettingPojo dt) 
	{
		// TODO Auto-generated method stub	
		String query="UPDATE SETTING set PAGESIZE =?,PAGEHEADER =?,MARGINLEFT =?,MARGINRIGHT =?,MARGINTOP=?, MARGINBOTTOM = ?  where SETTINGID = ?";
			//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>()
		{  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getPageSize());
			ps.setBoolean(2, dt.getPageHeader());
			ps.setInt(3, dt.getMarginLeft());
			ps.setInt(4, dt.getMarginRight());
			ps.setInt(5, dt.getMarginTop());
			ps.setInt(6,dt.getMarginBottom());
			ps.setInt(7, dt.getSettingId());
			
		    return ps.execute();
		}
		});
	}
}
