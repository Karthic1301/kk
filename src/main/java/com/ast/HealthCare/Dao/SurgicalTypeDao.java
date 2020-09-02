
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

import com.ast.HealthCare.Pojo.SurgicalTypePojo;
import com.ast.main.JpaConfiguration;

@Repository
public class SurgicalTypeDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	SurgicalTypeDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("SurgicalTypeDao constructor jdbc "+this.jdbcTemplate);
	}
	
	
	public boolean addSurgicalType(final SurgicalTypePojo dt) {
		// TODO Auto-generated method stub
		//CREATE TABLE  SurgicalType(DRUGTYPEID SERIAL,DRUGTYPENAME TEXT,DRUGTYPECODE TEXT,PRIMARY KEY(DRUGTYPEID));
		
		String sql1="INSERT INTO SurgicalType (SURGICALTYPE, PREFIX) values(?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, dt.getSurgicalType());
				ps.setString(2, dt.getPrefix());
				
			    return ps.execute();
			    
			}
		});
	}
	
	public String getPrefixById(int typeid)
	{
		
		String sql="SELECT PREFIX FROM SurgicalType WHERE SURGICALTYPEID="+typeid;
		System.out.println("SQL"+sql);
		String prefix= jdbcTemplate.queryForObject(sql,String.class);
		System.out.println("TYPEID"+prefix);
		return prefix;
	}


	public List<SurgicalTypePojo> surgicalAll() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM SurgicalType";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<SurgicalTypePojo>>() {
	        public List<SurgicalTypePojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<SurgicalTypePojo> list = new  ArrayList<SurgicalTypePojo>();
	            while(rs.next()) {
	            	SurgicalTypePojo dt = new SurgicalTypePojo();
	          
	                dt.setSurgicalTypeId(rs.getInt("SURGICALTYPEID"));
	                dt.setSurgicalType(rs.getString("SURGICALTYPE"));
	                dt.setPrefix(rs.getString("PREFIX"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
		
	}


	/*
	 * public List<SurgicalTypePojo> surgicalSearchByAll(String all) { // TODO
	 * Auto-generated method stub
	 * 
	 * String sql =
	 * "SELECT * FROM SurgicalType WHERE DRUGTYPEID LIKE ?  or DRUGTYPENAME LIKE ? or DRUGTYPECODE LIKE ?"
	 * ; return jdbcTemplate.query(sql, new Object[] { Integer.parseInt(all),all,all
	 * }, new ResultSetExtractor<List<SurgicalTypePojo>>() { public
	 * List<SurgicalTypePojo> extractData(ResultSet rs) throws SQLException,
	 * DataAccessException { System.out.println("oii"); List<SurgicalTypePojo> list
	 * = new ArrayList<SurgicalTypePojo>(); //PatientPojo patient = new
	 * PatientPojo();
	 * 
	 * while(rs.next()) {
	 * 
	 * System.out.println("oii1"); SurgicalTypePojo dt = new SurgicalTypePojo();
	 * dt.setSurgicalTypeId(rs.getInt("DRUGTYPEID"));
	 * dt.setSurgicalTypeCode(rs.getString("DRUGTYPECODE"));
	 * dt.setSurgicalTypeName(rs.getString("DRUGTYPENAME"));
	 * 
	 * list.add(dt);
	 * 
	 * } return list;
	 * 
	 * } }); }
	 */


	public boolean surgicalUpdate(final SurgicalTypePojo dt) {
		// TODO Auto-generated method stub
		
		System.out.println("vic "+dt);
		
		String query="UPDATE SurgicalType set SURGICALTYPE = ?, PREFIX = ? where SURGICALTYPEID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getSurgicalType());
			ps.setString(2, dt.getPrefix());
			ps.setInt(3, dt.getSurgicalTypeId());
		    return ps.execute();
		}
		});
	
	}

	
	public int surgicalDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM SurgicalType WHERE SURGICALTYPEID = ?", new Object[] { pid });
	}


	public SurgicalTypePojo surgicalSearchById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM SurgicalType WHERE DRUGTYPEID = ?";
		return jdbcTemplate.query(sql,  new Object[] { id }, new ResultSetExtractor<SurgicalTypePojo>() {
	        public SurgicalTypePojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	//List<SurgicalTypePojo> list = new  ArrayList<SurgicalTypePojo>();
	        	//PatientPojo patient = new PatientPojo();
	        	SurgicalTypePojo dt = new SurgicalTypePojo();
	        	while(rs.next()) {
	        		
	            	System.out.println("oii1");
	            	
	            	 dt.setSurgicalTypeId(rs.getInt("SURGICALTYPEID"));
		                dt.setSurgicalType(rs.getString("SURGICALTYPE"));
		                dt.setPrefix(rs.getString("PREFIX"));
	                
	               // list.add(dt);
	                
	            }
	            return dt;
	            
	            }
	    });
	}
	
	
	
	
	
	
}
