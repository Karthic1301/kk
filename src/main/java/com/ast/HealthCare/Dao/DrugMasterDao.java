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

import com.ast.HealthCare.Pojo.DrugMasterPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class DrugMasterDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	DrugMasterDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("DrugMasterDao constructor jdbc "+this.jdbcTemplate);
	}
	
	
//CREATE TABLE  drugmaster(DRUGID SERIAL,DRUGNAME TEXT,GENERICID INT,DRUGTYPEID INT,DRUGCODE TEXT,PRIMARY KEY(DRUGID));

	public boolean addDrug(final DrugMasterPojo drug) {
      String sql="INSERT INTO drugmaster (DRUGNAME,GENERICID,DRUGTYPEID, DRUGCODE) values(?,?,?,?)";
		
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, drug.getDrugName());
				ps.setInt(2, drug.getGenericId());
				ps.setInt(3, drug.getDrugTypeId());
			    ps.setString(4, drug.getDrugCode());
		
			   return ps.execute();
			}
		});
			}


	public List<DrugMasterPojo> drugAll() {
		String sql = "SELECT * FROM drugmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<DrugMasterPojo>>() {
	        public List<DrugMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<DrugMasterPojo> list = new  ArrayList<DrugMasterPojo>();
	            while(rs.next()) {
	            	DrugMasterPojo dm = new DrugMasterPojo();
	            	dm.setDrugId(rs.getInt("DRUGID"));
	            	dm.setDrugName(rs.getString("DRUGNAME"));
	            	dm.setGenericId(rs.getInt("GENERICID"));
	            	dm.setDrugTypeId(rs.getInt("DRUGTYPEID"));
	            	dm.setDrugCode(rs.getString("DRUGCODE"));
	            	String sql = "SELECT GENERICNAME FROM genericmaster WHERE GENERICID="+rs.getInt("GENERICID");
	            	System.out.println(sql);
	            	String name = jdbcTemplate.queryForObject(sql, String.class);
	            	dm.setGenericName(name);
	            	list.add(dm);
	            }
	            return list;
	            
	            }
	    });
}


public boolean drugUpdate(final DrugMasterPojo drug) {
System.out.println("dao " + drug);
		//CREATE TABLE  drugmaster(DRUGID SERIAL,DRUGNAME TEXT,GENERICID INT,DRUGTYPEID INT,DRUGCODE TEXT,PRIMARY KEY(DRUGID));
		
		String query="UPDATE drugmaster set DRUGNAME = ?, GENERICID = ?, DRUGTYPEID = ?, DRUGCODE = ? where DRUGID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, drug.getDrugName());
			ps.setInt(2, drug.getGenericId());
			ps.setInt(3, drug.getDrugTypeId());
			ps.setString(4, drug.getDrugCode());
		    ps.setInt(5, drug.getDrugId());
			return ps.execute();
		}
		});
	}


	public int drugDelete(int did) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM drugmaster WHERE DRUGID = ?", new Object[] { did });
	}


	public DrugMasterPojo drugSearchByName(String name) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM drugmaster WHERE DRUGNAME = ?";
		return jdbcTemplate.query(sql,  new Object[] { name }, new ResultSetExtractor<DrugMasterPojo>() {
	        public DrugMasterPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	DrugMasterPojo dm = new DrugMasterPojo();
	        	DrugMasterPojo dm1 = new DrugMasterPojo();
	        	
	        	if(rs.next()) {
	            	System.out.println("oii1");    
	            	dm.setDrugId(rs.getInt("DRUGID"));
	            	dm.setDrugName(rs.getString("DRUGNAME"));
	            	dm.setGenericId(rs.getInt("GENERICID"));
	            	dm.setDrugTypeId(rs.getInt("DRUGTYPEID"));
	            	dm.setDrugCode(rs.getString("DRUGCODE"));
	            	
	            }
	        	if(dm.getDrugName().equals(name))
	        		return dm;
	        	else
	        	{
	        		dm1.setDrugName("No name found");
	        		return dm1;
	        	}
	            
	            }
	    });
	}

}
