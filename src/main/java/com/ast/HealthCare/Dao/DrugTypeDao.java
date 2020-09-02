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

import com.ast.HealthCare.Pojo.DrugTypePojo;
import com.ast.main.JpaConfiguration;

@Repository
public class DrugTypeDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	DrugTypeDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("DrugTypeDao constructor jdbc "+this.jdbcTemplate);
	}
	
	
	public boolean addDrugType(final DrugTypePojo drugtype) {
		// TODO Auto-generated method stub
		//CREATE TABLE  drugtypemaster(DRUGTYPEID SERIAL,DRUGTYPENAME TEXT,DRUGTYPECODE TEXT,PRIMARY KEY(DRUGTYPEID));
		
		String sql1="INSERT INTO drugtypemaster (DRUGTYPENAME, DRUGTYPECODE) values(?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, drugtype.getDrugTypeName());
				ps.setString(2, drugtype.getDrugTypeCode());
				
			    return ps.execute();
			    
			}
		});
	}


	public List<DrugTypePojo> drugTypeAll() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM drugtypemaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<DrugTypePojo>>() {
	        public List<DrugTypePojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<DrugTypePojo> list = new  ArrayList<DrugTypePojo>();
	            while(rs.next()) {
	            	DrugTypePojo dt = new DrugTypePojo();
	          
	                dt.setDrugTypeId(rs.getInt("DRUGTYPEID"));
	                dt.setDrugTypeCode(rs.getString("DRUGTYPECODE"));
	                dt.setDrugTypeName(rs.getString("DRUGTYPENAME"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
		
	}


	public List<DrugTypePojo> drugTypeSearchByAll(String all) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM drugtypemaster WHERE DRUGTYPEID LIKE ?  or DRUGTYPENAME LIKE ? or DRUGTYPECODE LIKE ?";
		return jdbcTemplate.query(sql,  new Object[] { Integer.parseInt(all),all,all }, new ResultSetExtractor<List<DrugTypePojo>>() {
	        public List<DrugTypePojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<DrugTypePojo> list = new  ArrayList<DrugTypePojo>();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	        		
	            	System.out.println("oii1");
	            	DrugTypePojo dt = new DrugTypePojo();
	                dt.setDrugTypeId(rs.getInt("DRUGTYPEID"));
	                dt.setDrugTypeCode(rs.getString("DRUGTYPECODE"));
	                dt.setDrugTypeName(rs.getString("DRUGTYPENAME"));
	                
	                list.add(dt);
	                
	            }
	            return list;
	            
	            }
	    });
	}


	public boolean drugTypeUpdate(final DrugTypePojo dt) {
		// TODO Auto-generated method stub
		
		System.out.println("vic "+dt);
		
		String query="UPDATE drugtypemaster set DRUGTYPENAME = ?, DRUGTYPECODE = ? where DRUGTYPEID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getDrugTypeName());
			ps.setString(2, dt.getDrugTypeCode());
			ps.setInt(3, dt.getDrugTypeId());
		    return ps.execute();
		}
		});
	
	}

	
	public int drugTypeDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM drugtypemaster WHERE DRUGTYPEID = ?", new Object[] { pid });
	}


	public DrugTypePojo drugTypeSearchById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM drugtypemaster WHERE DRUGTYPEID = ?";
		return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<DrugTypePojo>() {
	        public DrugTypePojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	//List<DrugTypePojo> list = new  ArrayList<DrugTypePojo>();
	        	//PatientPojo patient = new PatientPojo();
	        	DrugTypePojo dt = new DrugTypePojo();
	        	while(rs.next()) {
	        		
	            	System.out.println("oii1");
	            	
	                dt.setDrugTypeId(rs.getInt("DRUGTYPEID"));
	                dt.setDrugTypeCode(rs.getString("DRUGTYPECODE"));
	                dt.setDrugTypeName(rs.getString("DRUGTYPENAME"));
	                
	               // list.add(dt);
	                
	            }
	            return dt;
	            
	            }
	    });
	}
	
	

}
