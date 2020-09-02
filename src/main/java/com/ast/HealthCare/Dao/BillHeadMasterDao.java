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


import com.ast.HealthCare.Pojo.BillHeadMasterPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class BillHeadMasterDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	BillHeadMasterDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("BillHeadMasterDao constructor jdbc "+this.jdbcTemplate);
	}
	
	//CREATE TABLE billheadmaster(FEEHEADID SERIAL, FEEHEAD TEXT,CGST INTEGER, SGST INTEGER,PRIMARY KEY(FEEHEADID));

	public boolean addbillheadmaster(final BillHeadMasterPojo billheadmasterpojo) {
		// TODO Auto-generated method stub
		String sql1="INSERT INTO billheadmaster (FEEHEAD,CGST, SGST) values(?,?,?)";
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, billheadmasterpojo.getFeehead());
				ps.setFloat(2, billheadmasterpojo.getCgst());
				ps.setFloat(3, billheadmasterpojo.getSgst());
			    return ps.execute();
			}
		});
	}
	
	public List<BillHeadMasterPojo> billHeadMasterAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM billheadmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillHeadMasterPojo>>() {
	        public List<BillHeadMasterPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<BillHeadMasterPojo> list = new  ArrayList<BillHeadMasterPojo>();
	            while(rs.next()) {
	            	BillHeadMasterPojo dt = new BillHeadMasterPojo();
	            	
	            	dt.setFeeheadid(rs.getInt("FEEHEADID"));
	            	dt.setFeehead(rs.getString("FEEHEAD"));
	            	dt.setCgst(rs.getFloat("CGST"));
	            	dt.setSgst(rs.getFloat("SGST"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}
	
	public int billHeadMasterDelete(int bid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM billheadmaster WHERE FEEHEADID = ?", new Object[] { bid });
	}
	
	public boolean billHeadMasterUpdate(final BillHeadMasterPojo dt) {
		// TODO Auto-generated method stub
		System.out.println("vic "+dt);
		
		String query="UPDATE billheadmaster set FEEHEAD = ?,CGST = ?, SGST = ? where FEEHEADID = ?";

		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getFeehead());
			ps.setFloat(2,dt.getCgst() );
			ps.setFloat(3, dt.getSgst());
			ps.setInt(4, dt.getFeeheadid());
		    return ps.execute();
		}
		});
	}

}
