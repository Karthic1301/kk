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

import com.ast.HealthCare.Pojo.ContentMasterPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class ContentMasterDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	@Autowired()
	BirthDetailsDao bDao;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	ContentMasterDao()
	{
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("ContentMasterDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public int getMaximumId() {
		String sql = "SELECT MAX(CONTENTMASTERID) As ID from ContentMaster";
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
	
	public int addContentMaster(final ContentMasterPojo dis)
	{
		// TODO Auto-generated method stub
		String sql1="INSERT INTO ContentMaster (HEADINGMASTERID,DESCRIPTION,DISEASEID) values(?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Integer>()
		{  
			public Integer doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setInt(1, dis.getHeadingMasterId());
				ps.setString(2, dis.getDescription());
				ps.setInt(3, dis.getDiseaseId());
				ps.execute();
				int disId= getMaximumId();
				return disId;
			    
			}
		});
	}

	public List<ContentMasterPojo> contentMasterAll() 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM ContentMaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<ContentMasterPojo>>()
		{
	        public List<ContentMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<ContentMasterPojo> list = new  ArrayList<ContentMasterPojo>();
	            while(rs.next()) {
	            	ContentMasterPojo dt = new ContentMasterPojo();
	            	dt.setContentMasterId(rs.getInt("CONTENTMASTERID"));
	            	dt.setDescription(rs.getString("DESCRIPTION"));
	            	dt.setDiseaseId(rs.getInt("DISEASEID"));
	            	dt.setHeadingMasterId(rs.getInt("HEADINGMASTERID"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	public ContentMasterPojo contentMasterById(int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM ContentMaster WHERE CONTENTMASTERID = "+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<ContentMasterPojo>()
		{
	        public ContentMasterPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	ContentMasterPojo dt = new ContentMasterPojo();
	            while(rs.next()) {
	            	
	            	dt.setContentMasterId(rs.getInt("CONTENTMASTERID"));
	            	dt.setDescription(rs.getString("DESCRIPTION"));
	            	dt.setDiseaseId(rs.getInt("DISEASEID"));
	            	dt.setHeadingMasterId(rs.getInt("HEADINGMASTERID"));
	            	//dt.setBirthPojo(bDao.birthDetailsByContentMasterId(rs.getInt("CONTENTMASTERID")));
	            }
	            return dt;
	            
	            }
	    });
	
	}
	
	public ContentMasterPojo contentMasterByHeadingMasterId(int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM ContentMaster WHERE HEADINGMASTERID = "+id;
		System.out.println(sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<ContentMasterPojo>()
		{
	        public ContentMasterPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	ContentMasterPojo dt = new ContentMasterPojo();
	            while(rs.next()) {
	            	
	            	dt.setContentMasterId(rs.getInt("CONTENTMASTERID"));
	            	dt.setDescription(rs.getString("DESCRIPTION"));
	            	dt.setDiseaseId(rs.getInt("DISEASEID"));
	            	dt.setHeadingMasterId(rs.getInt("HEADINGMASTERID"));
	            	//dt.setBirthPojo(bDao.birthDetailsByContentMasterId(rs.getInt("CONTENTMASTERID")));
	               
	            }
	            return dt;
	            
	            }
	    });
	
	}

	public int contentMasterDelete(int pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM ContentMaster WHERE CONTENTMASTERID = ? ", new Object[] { pid });
	}

	
	
	public boolean contentMasterUpdate(final ContentMasterPojo dt) 
	{
		// TODO Auto-generated method stub	
		String query="UPDATE ContentMaster set HEADINGMASTERID =?,DESCRIPTION=?,DISEASEID =? where CONTENTMASTERID=?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>()
		{  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setInt(1, dt.getHeadingMasterId());
			ps.setString(2, dt.getDescription());
			ps.setInt(3, dt.getDiseaseId());
			ps.setInt(4,dt.getContentMasterId());
		    return ps.execute();
		}
		});
	}
	
}
