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

import com.ast.HealthCare.Pojo.DiseasePojo;
import com.ast.main.JpaConfiguration;

@Repository
public class DiseaseDao 
{

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	DiseaseDao()
	{
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("DiseaseDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addDisease(final DiseasePojo dis)
	{
		// TODO Auto-generated method stub
		String sql1="INSERT INTO diseasemaster (DISEASENAME, DISEASECODE) values(?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>()
		{  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, dis.getDiseaseName());
				ps.setString(2, dis.getDiseaseCode());
				
			    return ps.execute();
			    
			}
		});
	}

	public List<DiseasePojo> diseaseAll() 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM diseasemaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<DiseasePojo>>()
		{
	        public List<DiseasePojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<DiseasePojo> list = new  ArrayList<DiseasePojo>();
	            while(rs.next()) {
	            	DiseasePojo dt = new DiseasePojo();
	          
	                dt.setDiseaseId(rs.getInt("DISEASEID"));
	                dt.setDiseaseCode(rs.getString("DISEASECODE"));
	                dt.setDiseaseName(rs.getString("DISEASENAME"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}

	public int diseaseDelete(int pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM diseasemaster WHERE DISEASEID = ?", new Object[] { pid });
	}

	public boolean diseaseUpdate(final DiseasePojo dt) 
	{
		// TODO Auto-generated method stub	
		String query="UPDATE diseasemaster set DISEASENAME = ?, DISEASECODE = ? where DISEASEID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>()
		{  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getDiseaseName());
			ps.setString(2, dt.getDiseaseCode());
			ps.setInt(3, dt.getDiseaseId());
		    return ps.execute();
		}
		});
	}
}
