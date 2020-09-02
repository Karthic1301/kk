package com.ast.HealthCare.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.ast.HealthCare.Pojo.LoginPojo;
import com.ast.HealthCare.Pojo.BillDetailFeeHeadTypeReportPojo;
import com.ast.HealthCare.Pojo.BillDetailHistoryPojo;
import com.ast.HealthCare.Pojo.BillDetailPojo;
import com.ast.HealthCare.Dao.LoginDao;
import com.ast.main.JpaConfiguration;

@Component
public class BillDetailDao {
	

	//CREATE TABLE billdetail(BILLDETAILID SERIAL,DOCTORID TEXT,BILLNO INTEGER,FEEID INTEGER,AMOUNT INTEGER,
	//CGSTPERCENTAGE INTEGER,CGSTAMOUNT INTEGER,SGSTPERCENTAGE INTEGER,SGSTAMOUNT INTEGER, GSTAMOUNT INTEGER,
	//NETAMOUNT INTEGER ,PRIMARY KEY(BILLDETAILID));
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	@Autowired()
	LoginDao login;
	LoginPojo loggeduser;

	//protected JdbcTemplate jdbcTemplate;
	JpaConfiguration jpa = new JpaConfiguration();
	BillDetailDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("BillDetailDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addBillDetail(final List<BillDetailPojo> bdpojo) {
		// TODO Auto-generated method stub
		
		/*loggeduser = login.loginDao(bdpojo.get(0).getLogusername(), bdpojo.get(0).getLogpassword());
		if(loggeduser.getUserName().equals(bdpojo.get(0).getLogusername()) && loggeduser.getPassword().equals(bdpojo.get(0).getLogpassword())) {*/
			
			String sql1="INSERT INTO billdetail (BILLNO,FEEID ,AMOUNT,CGSTPERCENTAGE,CGSTAMOUNT,SGSTPERCENTAGE,SGSTAMOUNT,GSTAMOUNT,NETAMOUNT,DOCTORID,BILLSERIALID,TESTID,TESTMASTERNAME,ORDERNO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
				public Boolean doInPreparedStatement(PreparedStatement ps)  
				        throws SQLException, DataAccessException {  
					for(int i=0;i<bdpojo.size();i++)
					{
					ps.setString(1, bdpojo.get(i).getBillno());
					ps.setInt(2, bdpojo.get(i).getFeeid());
					ps.setFloat(3, bdpojo.get(i).getAmount());
					ps.setFloat(4, bdpojo.get(i).getCgstpercentage());
					ps.setFloat(5, bdpojo.get(i).getCgstamount());
					ps.setFloat(6, bdpojo.get(i).getSgstpercentage());
					ps.setFloat(7, bdpojo.get(i).getSgstamount());
					ps.setFloat(8, bdpojo.get(i).getGstamount());
					ps.setFloat(9, bdpojo.get(i).getNetamount());
					ps.setString(10, bdpojo.get(i).getDoctorid());
					ps.setInt(11, bdpojo.get(i).getBillserailid());
					ps.setInt(12, bdpojo.get(i).getTestid());
					ps.setString(13, bdpojo.get(i).getTestMasterName());
					ps.setInt(14, bdpojo.get(i).getOrderNo());
					ps.execute();
					}
					return true;
				}
				});
			
		//}
	
		//return false;
	}

	
	public List<BillDetailPojo> billDetailAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM billdetail";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillDetailPojo>>() {
	        public List<BillDetailPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<BillDetailPojo> list = new  ArrayList<BillDetailPojo>();
	            while(rs.next()) {
	            	BillDetailPojo dt = new BillDetailPojo();
	            	dt.setDoctorid(rs.getString("DOCTORID"));
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
	            	dt.setBillserailid(rs.getInt("BILLSERIALID"));
	            	dt.setTestid(rs.getInt("TESTID"));
	            	dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

	public boolean billDetailUpdate(BillDetailPojo dt) {
		// TODO Auto-generated method stub
		loggeduser = login.loginDao(dt.getLogusername(), dt.getLogpassword());
		if(loggeduser.getUserName().equals(dt.getLogusername()) && loggeduser.getPassword().equals(dt.getLogpassword())) {
			String query="UPDATE billdetail set BILLNO = ?,FEEID = ? ,AMOUNT = ?,CGSTPERCENTAGE = ?,CGSTAMOUNT = ?,SGSTPERCENTAGE = ?,SGSTAMOUNT = ?,GSTAMOUNT = ?,NETAMOUNT = ?,DOCTORID = ?,BILLSERIALID =?,TESTID =?,TESTMASTERNAME =?,ORDERNO=? where BILLDETAILID = ?";

			return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
			   
				ps.setString(1, dt.getBillno());
				ps.setInt(2, dt.getFeeid() );
				ps.setFloat(3, dt.getAmount());
				ps.setFloat(4, dt.getCgstpercentage());
				ps.setFloat(5, dt.getCgstamount());
				ps.setFloat(6, dt.getSgstpercentage() );
				ps.setFloat(7, dt.getSgstamount());
				ps.setFloat(8, dt.getGstamount());
				ps.setFloat(9, dt.getNetamount());
				ps.setString(10, dt.getDoctorid());
				ps.setInt(11, dt.getOrderNo());
				ps.setInt(12, dt.getBilldetailid());
				
			    return ps.execute();
			}
			});
			
		}
		
		return false;
	}
	
	public List<BillDetailPojo> billDetailSerialId(int sid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM billdetail WHERE BILLSERIALID = '"+sid+"' ORDER BY ORDERNO ASC";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillDetailPojo>>() {
	        public List<BillDetailPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<BillDetailPojo> list = new  ArrayList<BillDetailPojo>();
	            while(rs.next()) {
	            	BillDetailPojo dt = new BillDetailPojo();
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
	            	dt.setTestid(rs.getInt("TESTID"));
	            	dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}



	
	public int billDetailDelete(int bid, String user, String pwd) {
		// TODO Auto-generated method stub
		loggeduser = login.loginDao(user,pwd);
		if(loggeduser.getUserName().equals(user) && loggeduser.getPassword().equals(pwd)) {
			return jdbcTemplate.update("DELETE FROM billdetail WHERE BILLDETAILID = ?", new Object[] { bid });
		}
		return 0;
		
	}
	
	
	public int billDetailDeleteByBillId(int billid, String user, String pwd) {
		// TODO Auto-generated method stub
		loggeduser = login.loginDao(user,pwd);
		if(loggeduser.getUserName().equals(user) && loggeduser.getPassword().equals(pwd)) {
			return jdbcTemplate.update("DELETE FROM billdetail WHERE BILLSERIALID = ?", new Object[] { billid });
		}
		return 0;
		
	}

	public List<BillDetailPojo> billDetailGetByNo(String no) {
		// TODO Auto-generated method stub// TODO Auto-generated method stub
		String sql = "SELECT * FROM billdetail WHERE BILLNO = '"+no+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillDetailPojo>>() {
	        public List<BillDetailPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<BillDetailPojo> list = new  ArrayList<BillDetailPojo>();
	            while(rs.next()) {
	            	BillDetailPojo dt = new BillDetailPojo();
	            	dt.setDoctorid(rs.getString("DOCTORID"));
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
	            	dt.setTestid(rs.getInt("TESTID"));
	            	dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	                list.add(dt);
	            }
	            System.out.println(list);
	            return list;
	            
	            }
	    });

	}
	
	public List<BillDetailPojo> billDetailGetByFeeId(int id, Date date1, Date date2) {
		// TODO Auto-generated method stub
		System.out.println(id);
		System.out.println(date1);
		System.out.println(date2);
		String sql = "SELECT * FROM billdetail WHERE FEEID = '"+id+"'";
		System.out.println(sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillDetailPojo>>() {
	        public List<BillDetailPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<BillDetailPojo> list = new  ArrayList<BillDetailPojo>();
	            while(rs.next()) {
	            	System.out.println("while kulla");
	            	BillDetailPojo dt = new BillDetailPojo();
	            	dt.setBillno(rs.getString("BILLNO"));
	            	System.out.println("dt->"+dt);
	            	String sql = "SELECT BILLDATE,PATIENTID FROM billMaster WHERE Billno = '"+dt.getBillno()+"' AND BILLDATE BETWEEN '"+date1+"' and '"+date2+"'";
	            	System.out.println("dt->"+sql);
	            	jdbcTemplate.query(sql, new ResultSetExtractor<List<BillDetailPojo>>() {
	        	        public List<BillDetailPojo> extractData(ResultSet rs1) throws SQLException,
	        	                DataAccessException {
	        	        	if(rs1.next()) {
	        	        		System.out.println("hi");
	        	        		dt.setDoctorid(rs.getString("DOCTORID"));
	        	            	dt.setBilldetailid(rs.getInt("BILLDETAILID"));
	        	            	dt.setBillno(rs.getString("BILLNO"));
	        	            	dt.setFeeid(rs.getInt("FEEID"));
	        	        		dt.setBilldate(rs1.getDate("BILLDATE"));
	        	        		dt.setPatientid(rs1.getString("PATIENTID"));
	        	        		dt.setAmount(rs.getFloat("AMOUNT"));
	        	            	dt.setCgstpercentage(rs.getFloat("CGSTPERCENTAGE"));
	        	            	dt.setCgstamount(rs.getFloat("CGSTAMOUNT"));
	        	            	dt.setSgstpercentage(rs.getFloat("SGSTPERCENTAGE"));
	        	            	dt.setSgstamount(rs.getFloat("SGSTAMOUNT"));
	        	            	dt.setGstamount(rs.getFloat("GSTAMOUNT"));
	        	            	dt.setNetamount(rs.getFloat("NETAMOUNT"));
	        	            	dt.setTestid(rs.getInt("TESTID"));
	        	            	dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	        	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	        	                list.add(dt);
	        	        	}
	        	        	return null;
	        	        }
	        		});
	            	
	            }
	            return list;
	            
	            }
	    });

	}

	public List<BillDetailPojo> billDetailGetByFeeIdAndDocId(final BillDetailFeeHeadTypeReportPojo data) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM billdetail WHERE FEEID = '"+data.getFeeid()+"' AND DOCTORID = '"+data.getDoctorid()+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillDetailPojo>>() {
	        public List<BillDetailPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<BillDetailPojo> list = new  ArrayList<BillDetailPojo>();
	            while(rs.next()) {
	            	BillDetailPojo dt = new BillDetailPojo();
	            	dt.setBillno(rs.getString("BILLNO"));
	            	String sql = "SELECT BILLDATE,PATIENTID FROM billMaster WHERE Billno = '"+dt.getBillno()+"' AND BILLDATE BETWEEN '"+data.getDate1()+"' and '"+data.getDate2()+"'";
	            	jdbcTemplate.query(sql, new ResultSetExtractor<List<BillDetailPojo>>() {
	        	        public List<BillDetailPojo> extractData(ResultSet rs1) throws SQLException,
	        	                DataAccessException {
	        	        	if(rs1.next()) {
	        	        		dt.setDoctorid(rs.getString("DOCTORID"));
	        	            	dt.setBilldetailid(rs.getInt("BILLDETAILID"));
	        	            	dt.setBillno(rs.getString("BILLNO"));
	        	            	dt.setFeeid(rs.getInt("FEEID"));
	        	        		dt.setBilldate(rs1.getDate("billdate"));
	        	        		dt.setPatientid(rs1.getString("PatientId"));
	        	        		dt.setAmount(rs.getFloat("AMOUNT"));
	        	            	dt.setCgstpercentage(rs.getFloat("CGSTPERCENTAGE"));
	        	            	dt.setCgstamount(rs.getFloat("CGSTAMOUNT"));
	        	            	dt.setSgstpercentage(rs.getFloat("SGSTPERCENTAGE"));
	        	            	dt.setSgstamount(rs.getFloat("SGSTAMOUNT"));
	        	            	dt.setGstamount(rs.getFloat("GSTAMOUNT"));
	        	            	dt.setNetamount(rs.getFloat("NETAMOUNT"));
	        	            	dt.setTestid(rs.getInt("TESTID"));
	        	            	dt.setTestMasterName(rs.getString("TESTMASTERNAME"));
	        	            	dt.setOrderNo(rs.getInt("ORDERNO"));
	        	                list.add(dt);
	        	        	}
	        	        	return null;
	        	        }
	        		});
	            	
	            }
	            return list;
	            
	            }
	    });

	}


	

}
