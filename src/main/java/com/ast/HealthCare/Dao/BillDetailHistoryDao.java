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
import org.springframework.stereotype.Component;

import com.ast.HealthCare.Pojo.BillDetailHistoryPojo;
import com.ast.HealthCare.Pojo.LoginPojo;
import com.ast.main.JpaConfiguration;

@Component
public class BillDetailHistoryDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	LoginDao login = new LoginDao();
	LoginPojo loggeduser;

	//protected JdbcTemplate jdbcTemplate;
	JpaConfiguration jpa = new JpaConfiguration();
	BillDetailHistoryDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("BillDetailHistoryDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addBillDetailHistory(final List<BillDetailHistoryPojo> bdhpojo) {
		// TODO Auto-generated method stub
		
		loggeduser = login.loginDao(bdhpojo.get(0).getLogusername(), bdhpojo.get(0).getLogpassword());
		if(loggeduser.getUserName().equals(bdhpojo.get(0).getLogusername()) && loggeduser.getPassword().equals(bdhpojo.get(0).getLogpassword())) {
			
			String sql1="INSERT INTO billdetailhistory (BILLSERIALID,BILLNO,FEEID ,AMOUNT,CGSTPERCENTAGE,CGSTAMOUNT,SGSTPERCENTAGE,SGSTAMOUNT,GSTAMOUNT,NETAMOUNT,DOCTORID,TESTID,TESTMASTERNAME,ORDERNO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
				public Boolean doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					for(int i=0;i<bdhpojo.size();i++)
					{
					ps.setInt(1, bdhpojo.get(i).getBillserailid());
					ps.setString(2, bdhpojo.get(i).getBillno());
					ps.setInt(3, bdhpojo.get(i).getFeeid());
					ps.setFloat(4, bdhpojo.get(i).getAmount());
					ps.setFloat(5, bdhpojo.get(i).getCgstpercentage());
					ps.setFloat(6, bdhpojo.get(i).getCgstamount());
					ps.setFloat(7, bdhpojo.get(i).getSgstpercentage());
					ps.setFloat(8, bdhpojo.get(i).getSgstamount());
					ps.setFloat(9, bdhpojo.get(i).getGstamount());
					ps.setFloat(10, bdhpojo.get(i).getNetamount());
					ps.setString(11, bdhpojo.get(i).getDoctorid());
					ps.setInt(12, bdhpojo.get(i).getTestId());
					ps.setString(13, bdhpojo.get(i).getTestMasterName());
					ps.setInt(14, bdhpojo.get(i).getOrderNo());
					ps.execute();
					}
					return true;
				}
				});
			
		}
	
		return false;
	}

	
	public List<BillDetailHistoryPojo> billDetailHistoryAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM billdetailhistory";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillDetailHistoryPojo>>() {
	        public List<BillDetailHistoryPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<BillDetailHistoryPojo> list = new  ArrayList<BillDetailHistoryPojo>();
	            while(rs.next()) {
	            	BillDetailHistoryPojo dt = new BillDetailHistoryPojo();
	            	dt.setDoctorid(rs.getString("DOCTORID"));
	            	dt.setBillserailid(rs.getInt("BILLSERIALID"));
	            	dt.setBilldetailid(rs.getInt("BILLDETAILID"));
	            	dt.setBillno(rs.getString("BILLNO"));
	            	dt.setFeeid(rs.getInt("FEEID"));
	            	dt.setAmount(rs.getFloat("AMOUNT"));
	            	dt.setCgstpercentage(rs.getFloat("CGSTPERCENTAGE"));
	            	dt.setCgstamount(rs.getFloat("CGSTAMOUNT"));
	            	dt.setSgstpercentage(rs.getFloat("SGSTPERCENTAGE"));
	            	dt.setSgstamount(rs.getFloat("SGSTAMOUNT"));
	            	dt.setGstamount(rs.getFloat("GSTAMOUNT"));
	            	dt.setNetamount(rs.getFloat("NETAMOUNT"));
	            	dt.setTestId(rs.getInt("TESTID"));
	            	dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

	public List<BillDetailHistoryPojo> billDetailHistoryBySerialId(int sid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM billdetailhistory WHERE BILLSERIALID = '"+sid+"' ";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillDetailHistoryPojo>>() {
	        public List<BillDetailHistoryPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<BillDetailHistoryPojo> list = new  ArrayList<BillDetailHistoryPojo>();
	            while(rs.next()) {
	            	BillDetailHistoryPojo dt = new BillDetailHistoryPojo();
	            	dt.setDoctorid(rs.getString("DOCTORID"));
	            	dt.setBillserailid(rs.getInt("BILLSERIALID"));
	            	dt.setBilldetailid(rs.getInt("BILLDETAILID"));
	            	dt.setBillno(rs.getString("BILLNO"));
	            	dt.setFeeid(rs.getInt("FEEID"));
	            	dt.setAmount(rs.getFloat("AMOUNT"));
	            	dt.setCgstpercentage(rs.getFloat("CGSTPERCENTAGE"));
	            	dt.setCgstamount(rs.getFloat("CGSTAMOUNT"));
	            	dt.setSgstpercentage(rs.getFloat("SGSTPERCENTAGE"));
	            	dt.setSgstamount(rs.getFloat("SGSTAMOUNT"));
	            	dt.setGstamount(rs.getFloat("GSTAMOUNT"));
	            	dt.setNetamount(rs.getFloat("NETAMOUNT"));
	            	dt.setTestId(rs.getInt("TESTID"));
	            	dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}


}
