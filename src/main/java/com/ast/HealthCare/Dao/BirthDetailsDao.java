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

import com.ast.HealthCare.Pojo.BirthDetailsPojo;
import com.ast.HealthCare.Pojo.ContentMasterPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class BirthDetailsDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	BirthDetailsDao()
	{
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("BirthDetailsDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public int getMaximumId() {
		String sql = "SELECT MAX(BIRTHID) As ID from BirthDetails";
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
	
	
	public int addBirthDetails(final BirthDetailsPojo dis)
	{
		// TODO Auto-generated method stub
		String sql1="INSERT INTO BirthDetails (CONTENTMASTERID,DOB,BIRTHTIME,SEX,WEIGHT,BLOODGROUP) values(?,?,?,?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Integer>()
		{  
			public Integer doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setInt(1, dis.getContentMasterId());
				ps.setDate(2, dis.getDob());
				ps.setString(3, dis.getBirthTime());
				ps.setString(4, dis.getSex());
				ps.setDouble(5, dis.getWeight());
				ps.setString(6, dis.getBloodGroup());
				ps.execute();
				int disId= getMaximumId();
				return disId;
			   
			    
			}
		});
	}

	public List<BirthDetailsPojo> BirthDetailsAll() 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM BirthDetails";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BirthDetailsPojo>>()
		{
	        public List<BirthDetailsPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<BirthDetailsPojo> list = new  ArrayList<BirthDetailsPojo>();
	            while(rs.next()) {
	            	BirthDetailsPojo dt = new BirthDetailsPojo();
	          dt.setBirthId(rs.getInt("BIRTHID"));
	          dt.setDob(rs.getDate("DOB"));
	          dt.setContentMasterId(rs.getInt("CONTENTMASTERID"));
	          dt.setBirthTime(rs.getString("BIRTHTIME"));
	          dt.setSex(rs.getString("SEX"));
	          dt.setWeight(rs.getDouble("WEIGHT"));
	          dt.setBloodGroup(rs.getString("BLOODGROUP"));
	          list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}

	public BirthDetailsPojo birthDetailsById(int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM BirthDetails WHERE BIRTHID = "+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<BirthDetailsPojo>()
		{
	        public BirthDetailsPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	BirthDetailsPojo dt = new BirthDetailsPojo();
	            while(rs.next()) {
	            	
	            	 dt.setBirthId(rs.getInt("BIRTHID"));
	   	          dt.setDob(rs.getDate("DOB"));
	   	          dt.setContentMasterId(rs.getInt("CONTENTMASTERID"));
	   	          dt.setBirthTime(rs.getString("BIRTHTIME"));
	   	          dt.setSex(rs.getString("SEX"));
	   	          dt.setWeight(rs.getDouble("WEIGHT"));
	   	          dt.setBloodGroup(rs.getString("BLOODGROUP"));
	            }
	            return dt;
	            
	            }
	    });
	
	}
	
	public BirthDetailsPojo birthDetailsByContentMasterId(int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM BirthDetails WHERE CONTENTMASTERID = "+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<BirthDetailsPojo>()
		{
	        public BirthDetailsPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	BirthDetailsPojo dt = new BirthDetailsPojo();
	            while(rs.next()) {
	            	
	            	 dt.setBirthId(rs.getInt("BIRTHID"));
	   	          dt.setDob(rs.getDate("DOB"));
	   	          dt.setContentMasterId(rs.getInt("CONTENTMASTERID"));
	   	          dt.setBirthTime(rs.getString("BIRTHTIME"));
	   	          dt.setSex(rs.getString("SEX"));
	   	          dt.setWeight(rs.getDouble("WEIGHT"));
	   	          dt.setBloodGroup(rs.getString("BLOODGROUP"));
	            }
	            return dt;
	            
	            }
	    });
	
	}
	
	
	public int BirthDetailsDelete(int pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM BirthDetails WHERE BirthID = ?", new Object[] { pid });
	}
	
	public int BirthDetailsDeleteByContentId(int cid)
	{
		// TODO Auto-generated method stub
		System.out.println("DELETE CONTENT"+cid);
		return jdbcTemplate.update("DELETE FROM BirthDetails WHERE CONTENTMASTERID = ?", new Object[] { cid });
	}

	public boolean BirthDetailsUpdate(final BirthDetailsPojo dt) 
	{
		// TODO Auto-generated method stub	
		String query="UPDATE BirthDetails set CONTENTMASTERID=?,DOB=?,BIRTHTIME=?,SEX=?,WEIGHT=?,BLOODGROUP=? where BIRTHID = ?";

		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>()
		{  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
			ps.setInt(1, dt.getContentMasterId());
			ps.setDate(2, dt.getDob());
			ps.setString(3, dt.getBirthTime());
			ps.setString(4, dt.getSex());
			ps.setDouble(5, dt.getWeight());
			ps.setString(6, dt.getBloodGroup());
			ps.setInt(7, dt.getBirthId());
		    return ps.execute();
		}
		});
	}
	
	
}
