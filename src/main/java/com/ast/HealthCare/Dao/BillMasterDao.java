
package com.ast.HealthCare.Dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.BillDetailHistoryPojo;
import com.ast.HealthCare.Pojo.BillDetailPojo;
import com.ast.HealthCare.Pojo.BillMasterPojo;
import com.ast.HealthCare.Pojo.BillMasterReasonPojo;
import com.ast.HealthCare.Pojo.LoginPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class BillMasterDao {
	// CREATE TABLE billmaster(BILLNO SERIAL,PATIENTID TEXT,BILLSTATUS TEXT,
	// MODIFIEDUSERID TEXT,BILLDATE DATE,MODIFIEDTIME TEXT,TOTALAMOUNT INTEGER,
	// SGST INTEGER,CGST INTEGER,NETAMOUNT INTEGER,PRIMARY KEY(BILLNO));
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	@Autowired
	BillDetailDao bddao;
	@Autowired
	LoginDao login;
	LoginPojo loggeduser;
	BillMasterPojo bm;
	BillDetailHistoryPojo bdhpojo;
	
	int id;
	@Value("${const.inBillIdPrefix}")
	String inhoscode;
	@Value("${const.outBillIdPrefix}")
	String outhoscode;
	
	
	String status = "NC";
	String reason = "Newly Created";

	//protected JdbcTemplate jdbcTemplate;
	JpaConfiguration jpa = new JpaConfiguration();
	
	public int getMaximumId() {
		String sql = "SELECT MAX(BILLSERIALID) As ID from billmaster";
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

	BillMasterDao() {
		/*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
		System.out.println("BillMasterDao constructor jdbc " + this.jdbcTemplate);
	}

	public BillMasterPojo addbillmaster(final BillMasterPojo billmasterpojo) throws ParseException {
		// TODO Auto-generated method stub
		long num1;
		System.out.println("sgad");
		Calendar now = Calendar.getInstance(); // Gets the current date and time
		final int year = now.get(Calendar.YEAR);

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
		cal.get(Calendar.DATE);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(cal.getTime());
		java.util.Date fd3 = format1.parse(formatted);
		java.sql.Date currtentDate = new java.sql.Date(fd3.getTime());
		SimpleDateFormat format2 = new SimpleDateFormat("h:mm a");
		String fd2 = format2.format(cal.getTime());
		System.out.println(formatted);
		System.out.println(fd2);
		// Output "2012-09-26"

		String sql1 = "Select BILLNO  from billmaster WHERE INOUTFLAG = '"+ billmasterpojo.getInOutFlag()+"' order by BILLSERIALID DESC";
		List<String> rs = jdbcTemplate.query(sql1, new ResultSetExtractor<List<String>>() {
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<String> list = new ArrayList<String>();

				if (rs.next()) {
					System.out.println("oii1");
					list.add(rs.getString("BILLNO"));
					return list;
				} else {
					System.out.println("else");
					List<String> paa = new ArrayList<String>();
					// paa.add();
					return paa;
				}
			}
		});
		if (!rs.isEmpty()) {
			System.out.println("resultset not empty so if kula ");
			String prevpid = rs.get(0);
			System.out.println(prevpid);
			int prevyear = Integer.parseInt(prevpid.substring(4, 8));
			System.out.println("previous patient year: " + prevyear);
			if (prevyear == year) {
				System.out.println("same year");
				int prevnum = Integer.parseInt(prevpid.substring(9));
				System.out.println("prevnum: " + prevnum);
				num1 = prevnum + 1;
				System.out.println("max=" + num1);
			} else {
				System.out.println("new year");
				// int prevnum = Integer.parseInt(prevpid.substring(7));
				num1 = 1;
				System.out.println("max=" + num1);
			}
			String num2;
			if (num1 < 10) {
				num2 = "0000" + num1;
			} else if (num1 < 100) {
				num2 = "000" + num1;
			} else if (num1 < 1000) {
				num2 = "00" + num1;
			} else if (num1 < 10000) {
				num2 = "0" + num1;
			} /*
				 * else if (num1 < 100000) { num2 = "0000" + num1; } else if (num1 < 1000000) {
				 * num2 = "000" + num1; } else if (num1 < 10000000) { num2 = "00" + num1; } else
				 * if (num1 < 100000000) { num2 = "0" + num1; }
				 */ else
				num2 = "" + num1;
			System.out.println(num2);
			String pid ="";
			if(billmasterpojo.getInOutFlag().equals("IN"))
			{
			pid = inhoscode + year +'-'+ num2;
			}
			else
			{
			 pid = outhoscode + year + '-'+num2;	
			}
			final String billno = pid;
			String query = "INSERT INTO billmaster (BILLNO,PATIENTID,BILLSTATUS ,MODIFIEDUSERID ,BILLDATE,MODIFIEDTIME ,TOTALAMOUNT,SGST,CGST,NETAMOUNT,REASON,PATIENTNAME,MOBILENO,DOCTORNAME,PRESCRIPTIONNO,DOCTORID,BILLINGFLAG,INOUTFLAG,DOA,DOD,WARDNO,IPNO,LESSADVANCEDPAID,FINALTOTAL,AGE,GENDER) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			return jdbcTemplate.execute(query, new PreparedStatementCallback<BillMasterPojo>() {
				public BillMasterPojo doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					BillMasterPojo bpojo = new BillMasterPojo();
					ps.setString(1, billno);
					ps.setString(2, billmasterpojo.getPatientid());
					ps.setString(3, status);
					ps.setString(4, billmasterpojo.getModifieduserid());
					ps.setDate(5, currtentDate);
					ps.setString(6, fd2);
					ps.setFloat(7, billmasterpojo.getTotalamount());
					ps.setFloat(8, billmasterpojo.getCgst());
					ps.setFloat(9, billmasterpojo.getSgst());
					ps.setFloat(10, billmasterpojo.getNetamount());
					ps.setString(11, reason);
					ps.setString(12, billmasterpojo.getPatientName());
					ps.setString(13,billmasterpojo.getMobileNo());
					ps.setString(14, billmasterpojo.getDoctorName());
					ps.setString(15, billmasterpojo.getPrescriptionNo());
					ps.setString(16, billmasterpojo.getDoctorId());
					ps.setString(17, billmasterpojo.getBillingFlag());
					ps.setString(18, billmasterpojo.getInOutFlag());
					ps.setDate(19,billmasterpojo.getDoa());
					ps.setDate(20, billmasterpojo.getDod());
					ps.setString(21,billmasterpojo.getWardNo());
					ps.setString(22, billmasterpojo.getIpNo());
					ps.setDouble(23, billmasterpojo.getLessAdvancePaid());
					ps.setDouble(24, billmasterpojo.getFinalTotal());
					ps.setInt(25, billmasterpojo.getAge());
					ps.setString(26, billmasterpojo.getGender());
					if (ps.execute()) {
						
						return bpojo;
					} else {
						bpojo = BillMasterSearchById(getMaximumId());
						System.out.println(bpojo.toString());
						System.out.println("ELSE__"+bpojo.toString());
						return bpojo;

					}
					
				}
			});
		} else {
			System.out.println("first entry in table");
			num1 = 1;
			System.out.println("max=" + num1);

			String num2;
			if (num1 < 10) {
				num2 = "0000" + num1;
			} else if (num1 < 100) {
				num2 = "000" + num1;
			} else if (num1 < 1000) {
				num2 = "00" + num1;
			} else if (num1 < 10000) {
				num2 = "0" + num1;
			} /*
				 * else if (num1 < 100000) { num2 = "0000" + num1; } else if (num1 < 1000000) {
				 * num2 = "000" + num1; } else if (num1 < 10000000) { num2 = "00" + num1; } else
				 * if (num1 < 100000000) { num2 = "0" + num1; }
				 */ else
				num2 = "" + num1;

			System.out.println(num2);
			String pid ="";
			if(billmasterpojo.getInOutFlag().equals("IN"))
			{
			pid = inhoscode + year +'-'+ num2;
			}
			else
			{
			 pid = outhoscode + year +'-'+ num2;	
			}
			final String billno=pid;
			String query = "INSERT INTO billmaster (BILLNO,PATIENTID,BILLSTATUS ,MODIFIEDUSERID ,BILLDATE,MODIFIEDTIME ,TOTALAMOUNT,SGST,CGST,NETAMOUNT,REASON,PATIENTNAME,MOBILENO,DOCTORNAME,PRESCRIPTIONNO,DOCTORID,BILLINGFLAG,INOUTFLAG,DOA,DOD,WARDNO,IPNO,LESSADVANCEDPAID,FINALTOTAL,AGE,GENDER) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			return jdbcTemplate.execute(query, new PreparedStatementCallback<BillMasterPojo>() {
				public BillMasterPojo doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					BillMasterPojo bpojo = new BillMasterPojo();
					ps.setString(1, billno);
					ps.setString(2, billmasterpojo.getPatientid());
					ps.setString(3, status);
					ps.setString(4, billmasterpojo.getModifieduserid());
					ps.setDate(5, currtentDate);
					ps.setString(6, fd2);
					ps.setFloat(7, billmasterpojo.getTotalamount());
					ps.setFloat(8, billmasterpojo.getCgst());
					ps.setFloat(9, billmasterpojo.getSgst());
					ps.setFloat(10, billmasterpojo.getNetamount());
					ps.setString(11, reason);
					ps.setString(12, billmasterpojo.getPatientName());
					ps.setString(13,billmasterpojo.getMobileNo());
					ps.setString(14, billmasterpojo.getDoctorName());
					ps.setString(15, billmasterpojo.getPrescriptionNo());
					ps.setString(16, billmasterpojo.getDoctorId());
					ps.setString(17, billmasterpojo.getBillingFlag());
					ps.setString(18, billmasterpojo.getInOutFlag());
					ps.setDate(19,billmasterpojo.getDoa());
					ps.setDate(20, billmasterpojo.getDod());
					ps.setString(21,billmasterpojo.getWardNo());
					ps.setString(22, billmasterpojo.getIpNo());
					ps.setDouble(23, billmasterpojo.getLessAdvancePaid());
					ps.setDouble(24, billmasterpojo.getFinalTotal());
					ps.setInt(25, billmasterpojo.getAge());
					ps.setString(26, billmasterpojo.getGender());
					if (ps.execute()) {
						
						return bpojo;
					} else {
						bpojo = BillMasterSearchById(getMaximumId());
						System.out.println(bpojo.toString());
						System.out.println("ELSE__"+bpojo.toString());
						return bpojo;

					}
				}
			});
		}
	}

	public List<BillMasterPojo> billMasterAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM billmaster ORDER BY BILLNO DESC LIMIT 10";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillMasterPojo>>() {
			public List<BillMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<BillMasterPojo> list = new ArrayList<BillMasterPojo>();
				while (rs.next()) {
					BillMasterPojo dt = new BillMasterPojo();

					dt.setBillno(rs.getString("BILLNO"));
					dt.setPatientid(rs.getString("PATIENTID"));
					dt.setBillstatus(rs.getString("BILLSTATUS"));
					dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
					dt.setBilldate(rs.getDate("BillDate"));
					dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
					dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
					dt.setCgst(rs.getFloat("CGST"));
					dt.setSgst(rs.getFloat("SGST"));
					dt.setNetamount(rs.getFloat("NETAMOUNT"));
					dt.setReason(rs.getString("REASON"));
					dt.setPatientName(rs.getString("PATIENTNAME"));
					dt.setMobileNo(rs.getString("MOBILENO"));
					dt.setDoctorName(rs.getString("DOCTORNAME"));
					dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					dt.setDoctorId(rs.getString("DOCTORID"));
					dt.setBillingFlag(rs.getString("BILLINGFLAG"));
					dt.setInOutFlag(rs.getString("INOUTFLAG"));
					dt.setDoa(rs.getDate("DOA"));
					dt.setDod(rs.getDate("DOD"));
					dt.setWardNo(rs.getString("WARDNO"));
					dt.setIpNo(rs.getString("IPNO"));
					dt.setLessAdvancePaid(rs.getDouble("LESSADVANCEDPAID"));
					dt.setFinalTotal(rs.getDouble("FINALTOTAL"));
					dt.setAge(rs.getInt("AGE"));
					dt.setGender(rs.getString("GENDER"));
					list.add(dt);
				}
				return list;
			}
		});
	}

	public int billMasterDelete(int bid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM billmaster WHERE BILLNO = ?", new Object[] { bid });
	}

	public boolean billMasterUpdate(final BillMasterPojo dt) {
		loggeduser = login.loginDao(dt.getLogusername(), dt.getLogpassword());
		if (loggeduser.getUserName().equals(dt.getLogusername()) && loggeduser.getPassword().equals(dt.getLogpassword())
				&& !loggeduser.getUserCategory().equals("P")) {

			// TODO Auto-generated method stub
			System.out.println("vic " + dt);

			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
			cal.get(Calendar.DATE);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format2 = new SimpleDateFormat("h:mm a");
			String fd = format1.format(cal.getTime());
			String fd2 = format2.format(cal.getTime());
			System.out.println(fd);
			System.out.println(fd2);
			// Output "2012-09-26"

			String sql1 = "Select BILLDATE  from billmaster WHERE BILLNO='" + dt.getBillno() + "'";
			return jdbcTemplate.query(sql1, new ResultSetExtractor<Boolean>() {
				public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
					System.out.println("oii");
					String text;
					if (rs.next()) {
						System.out.println("oii1");
						Date dtt = rs.getDate("BILlDATE");
						SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
						text = ff.format(dtt);
						System.out.println("text" + text);
						System.out.println("fd" + fd);
						if (text.equals(fd)) {
							System.out.println("hari" + text.equals(fd));
							System.out.println("hari");
							String query = "UPDATE billmaster set BILLSTATUS = 'CHANGED' ,MODIFIEDUSERID = ? ,BILLDATE = '"
									+ fd + "',MODIFIEDTIME = '" + fd2
									+ "' ,TOTALAMOUNT = ?,SGST = ?,CGST = ?,NETAMOUNT = ? where BILLNO = ?";

							return jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
								public Boolean doInPreparedStatement(PreparedStatement ps)
										throws SQLException, DataAccessException {
									ps.setString(1, dt.getModifieduserid());
									ps.setFloat(2, dt.getTotalamount());
									ps.setFloat(3, dt.getCgst());
									ps.setFloat(4, dt.getSgst());
									ps.setFloat(5, dt.getNetamount());
									ps.setString(6, dt.getBillno());
									return ps.execute();
								}
							});
						} else {
							return true;
						}

					} else {
						return true;
					}
				}
			});
		}
		return true;
	}

	/*public boolean changeBillStatusCancel(String bid, String uname, String pwd) {
		loggeduser = login.loginDao(uname, pwd);
		if (loggeduser.getUserName().equals(uname) && loggeduser.getPassword().equals(pwd)
				&& !loggeduser.getUserCategory().equals("P")) {

			// TODO Auto-generated method stub
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
			cal.get(Calendar.DATE);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
			String fd = format1.format(cal.getTime());
			String fd2 = format2.format(cal.getTime());
			System.out.println(fd);
			// Output "2012-09-26"

			String sql1 = "Select BILLDATE  from billmaster WHERE BILLNO='" + bid + "'";
			int rs = jdbcTemplate.query(sql1, new ResultSetExtractor<Integer>() {
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					System.out.println("oii");
					String text;
					if (rs.next()) {
						System.out.println("oii1");
						Date dt = rs.getDate("BILlDATE");
						SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
						text = ff.format(dt);
						System.out.println(text);

						if (text.equals(fd)) {
							return jdbcTemplate.update("UPDATE billmaster set BILLSTATUS ='CANCEL',MODIFIEDTIME = '"+ fd2 + "' WHERE BILLNO = ?", new Object[] { bid });

						} else {
							return 0;
						}
					} else {
						System.out.println("else");
						String paa = new String();
						return 0;
					}
				}
			});
			if (rs > 0) {
				return false;
			}
		}
		return true;
	}
*/
	public List<BillMasterPojo> BillMasterSearchByAll(String all) {
		String sql = "SELECT * FROM billmaster WHERE PATIENTID LIKE ? or BILLNO LIKE ?";
		return jdbcTemplate.query(sql, new Object[] { all, all }, new ResultSetExtractor<List<BillMasterPojo>>() {
			public List<BillMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<BillMasterPojo> list = new ArrayList<BillMasterPojo>();

				while (rs.next()) {
					System.out.println("oii1");
					BillMasterPojo dt = new BillMasterPojo();
					dt.setBillno(rs.getString("BILLNO"));
					dt.setPatientid(rs.getString("PATIENTID"));
					dt.setBillstatus(rs.getString("BILLSTATUS"));
					dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
					dt.setBilldate(rs.getDate("BillDate"));
					dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
					dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
					dt.setCgst(rs.getFloat("CGST"));
					dt.setSgst(rs.getFloat("SGST"));
					dt.setNetamount(rs.getFloat("NETAMOUNT"));
					dt.setReason(rs.getString("REASON"));
					dt.setPatientName(rs.getString("PATIENTNAME"));
					dt.setMobileNo(rs.getString("MOBILENO"));
					dt.setDoctorName(rs.getString("DOCTORNAME"));
					dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					dt.setDoctorId(rs.getString("DOCTORID"));
					dt.setBillingFlag(rs.getString("BILLINGFLAG"));
					dt.setInOutFlag(rs.getString("INOUTFLAG"));
					dt.setDoa(rs.getDate("DOA"));
					dt.setDod(rs.getDate("DOD"));
					dt.setWardNo(rs.getString("WARDNO"));
					dt.setIpNo(rs.getString("IPNO"));
					dt.setLessAdvancePaid(rs.getDouble("LESSADVANCEDPAID"));
					dt.setFinalTotal(rs.getDouble("FINALTOTAL"));
					dt.setAge(rs.getInt("AGE"));
					dt.setGender(rs.getString("GENDER"));
					list.add(dt);
				}
				return list;
			}
		});
	}

	/*
	 * public List<BillMasterPojo> BillMasterSearchByAll(String pid) { String sql =
	 * "SELECT * FROM billmaster WHERE PATIENTID LIKE ?"; return
	 * jdbcTemplate.query(sql, new Object[] { pid }, new
	 * ResultSetExtractor<List<BillMasterPojo>>() { public List<BillMasterPojo>
	 * extractData(ResultSet rs) throws SQLException, DataAccessException {
	 * System.out.println("oii"); List<BillMasterPojo> list = new
	 * ArrayList<BillMasterPojo>();
	 * 
	 * while (rs.next()) { System.out.println("oii1"); BillMasterPojo dt = new
	 * BillMasterPojo(); dt.setBillno(rs.getString("BILLNO"));
	 * dt.setPatientid(rs.getString("PATIENTID"));
	 * dt.setBillstatus(rs.getString("BILLSTATUS"));
	 * dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
	 * dt.setBilldate(rs.getDate("BillDate"));
	 * dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
	 * dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
	 * dt.setCgst(rs.getFloat("CGST")); dt.setSgst(rs.getFloat("SGST"));
	 * dt.setNetamount(rs.getFloat("NETAMOUNT")); list.add(dt); } return list; } });
	 * }
	 */ 
	 public BillMasterPojo BillMasterSearchByBillNo(String pid) { 
		 String sql = "SELECT * FROM billmaster WHERE BILLNO LIKE ?"; 
		 return jdbcTemplate.query(sql, new Object[] { pid }, new ResultSetExtractor <BillMasterPojo>() { 
			 public BillMasterPojo extractData(ResultSet rs) throws SQLException, DataAccessException { 
				 System.out.println("oii"); 
	 if (rs.next()) { 
		 System.out.println("oii1"); 
		 BillMasterPojo dt = new BillMasterPojo(); 
		 dt.setBillno(rs.getString("BILLNO"));
	 dt.setPatientid(rs.getString("PATIENTID"));
	 dt.setBillstatus(rs.getString("BILLSTATUS"));
	 dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
	 dt.setBilldate(rs.getDate("BillDate"));
	 dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
	 dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
	 dt.setCgst(rs.getFloat("CGST")); 
	 dt.setSgst(rs.getFloat("SGST"));
	 dt.setNetamount(rs.getFloat("NETAMOUNT"));
	 dt.setReason(rs.getString("REASON"));
	 dt.setPatientName(rs.getString("PATIENTNAME"));
		dt.setMobileNo(rs.getString("MOBILENO"));
		dt.setDoctorName(rs.getString("DOCTORNAME"));
		dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		dt.setDoctorId(rs.getString("DOCTORID"));
		dt.setBillingFlag(rs.getString("BILLINGFLAG"));
		dt.setInOutFlag(rs.getString("INOUTFLAG"));
		dt.setDoa(rs.getDate("DOA"));
		dt.setDod(rs.getDate("DOD"));
		dt.setWardNo(rs.getString("WARDNO"));
		dt.setIpNo(rs.getString("IPNO"));
		dt.setLessAdvancePaid(rs.getDouble("LESSADVANCEDPAID"));
		dt.setFinalTotal(rs.getDouble("FINALTOTAL"));
		dt.setAge(rs.getInt("AGE"));
		dt.setGender(rs.getString("GENDER"));
		dt.setBilldetailpojo(bddao.billDetailSerialId(rs.getInt("BILLSERIALID")));
		System.out.println("BILL _______"+dt.toString()); 
	 return dt; 
	 }
	return null; 
	 } 
			 });
	 }
	 
	 public BillMasterPojo BillMasterSearchById(int id) { 
		 String sql = "SELECT * FROM billmaster WHERE BILLSERIALID = ?"; 
		 return jdbcTemplate.query(sql, new Object[] { id }, new ResultSetExtractor <BillMasterPojo>() { 
			 public BillMasterPojo extractData(ResultSet rs) throws SQLException, DataAccessException { 
				 
	 if (rs.next()) { 
		 System.out.println("oii1"); 
		 BillMasterPojo dt = new BillMasterPojo(); 
		 dt.setBillno(rs.getString("BILLNO"));
	 dt.setPatientid(rs.getString("PATIENTID"));
	 dt.setBillstatus(rs.getString("BILLSTATUS"));
	 dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
	 dt.setBilldate(rs.getDate("BillDate"));
	 dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
	 dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
	 dt.setCgst(rs.getFloat("CGST")); 
	 dt.setSgst(rs.getFloat("SGST"));
	 dt.setNetamount(rs.getFloat("NETAMOUNT"));
	 dt.setReason(rs.getString("REASON"));
	 dt.setPatientName(rs.getString("PATIENTNAME"));
		dt.setMobileNo(rs.getString("MOBILENO"));
		dt.setDoctorName(rs.getString("DOCTORNAME"));
		dt.setSerialid(rs.getInt("BILLSERIALID"));
		dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
		dt.setDoctorId(rs.getString("DOCTORID"));
		dt.setBillingFlag(rs.getString("BILLINGFLAG"));
		dt.setInOutFlag(rs.getString("INOUTFLAG"));
		dt.setDoa(rs.getDate("DOA"));
		dt.setDod(rs.getDate("DOD"));
		dt.setWardNo(rs.getString("WARDNO"));
		dt.setIpNo(rs.getString("IPNO"));
		dt.setLessAdvancePaid(rs.getDouble("LESSADVANCEDPAID"));
		dt.setFinalTotal(rs.getDouble("FINALTOTAL"));
		dt.setAge(rs.getInt("AGE"));
		dt.setGender(rs.getString("GENDER"));
		dt.setBilldetailpojo(bddao.billDetailSerialId(rs.getInt("BILLSERIALID")));
		System.out.println("BILL _______"+dt.toString()); 
	 return dt; 
	 }
	return null; 
	 } 
			 });
	 }
	 
	public List<BillMasterPojo> BillMasterSearchByBillStatusCancel() {
		String sql = "SELECT * FROM billmaster WHERE BILLSTATUS='CANCEL'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillMasterPojo>>() {
			public List<BillMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<BillMasterPojo> list = new ArrayList<BillMasterPojo>();

				while (rs.next()) {
					System.out.println("oii1");
					BillMasterPojo dt = new BillMasterPojo();
					dt.setBillno(rs.getString("BILLNO"));
					dt.setPatientid(rs.getString("PATIENTID"));
					dt.setBillstatus(rs.getString("BILLSTATUS"));
					dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
					dt.setBilldate(rs.getDate("BillDate"));
					dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
					dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
					dt.setCgst(rs.getFloat("CGST"));
					dt.setSgst(rs.getFloat("SGST"));
					dt.setNetamount(rs.getFloat("NETAMOUNT"));
					dt.setReason(rs.getString("REASON"));
					dt.setPatientName(rs.getString("PATIENTNAME"));
					dt.setMobileNo(rs.getString("MOBILENO"));
					dt.setDoctorName(rs.getString("DOCTORNAME"));
					dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					dt.setDoctorId(rs.getString("DOCTORID"));
					dt.setBillingFlag(rs.getString("BILLINGFLAG"));
					dt.setInOutFlag(rs.getString("INOUTFLAG"));
					dt.setDoa(rs.getDate("DOA"));
					dt.setDod(rs.getDate("DOD"));
					dt.setWardNo(rs.getString("WARDNO"));
					dt.setIpNo(rs.getString("IPNO"));
					dt.setLessAdvancePaid(rs.getDouble("LESSADVANCEDPAID"));
					dt.setFinalTotal(rs.getDouble("FINALTOTAL"));
					dt.setAge(rs.getInt("AGE"));
					dt.setGender(rs.getString("GENDER"));
					list.add(dt);
					
				}
				return list;
			}
		});
	}

	public List<BillMasterPojo> BillMasterSearchByBillStatusChanged() {
		String sql = "SELECT * FROM billmaster WHERE BILLSTATUS='CHANGED'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillMasterPojo>>() {
			public List<BillMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<BillMasterPojo> list = new ArrayList<BillMasterPojo>();

				while (rs.next()) {
					System.out.println("oii1");
					BillMasterPojo dt = new BillMasterPojo();
					dt.setBillno(rs.getString("BILLNO"));
					dt.setPatientid(rs.getString("PATIENTID"));
					dt.setBillstatus(rs.getString("BILLSTATUS"));
					dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
					dt.setBilldate(rs.getDate("BillDate"));
					dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
					dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
					dt.setCgst(rs.getFloat("CGST"));
					dt.setSgst(rs.getFloat("SGST"));
					dt.setNetamount(rs.getFloat("NETAMOUNT"));
					dt.setReason(rs.getString("REASON"));
					dt.setPatientName(rs.getString("PATIENTNAME"));
					dt.setMobileNo(rs.getString("MOBILENO"));
					dt.setDoctorName(rs.getString("DOCTORNAME"));
					dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					dt.setDoctorId(rs.getString("DOCTORID"));
					dt.setBillingFlag(rs.getString("BILLINGFLAG"));
					dt.setInOutFlag(rs.getString("INOUTFLAG"));
					dt.setDoa(rs.getDate("DOA"));
					dt.setDod(rs.getDate("DOD"));
					dt.setWardNo(rs.getString("WARDNO"));
					dt.setIpNo(rs.getString("IPNO"));
					dt.setLessAdvancePaid(rs.getDouble("LESSADVANCEDPAID"));
					dt.setFinalTotal(rs.getDouble("FINALTOTAL"));
					dt.setAge(rs.getInt("AGE"));
					dt.setGender(rs.getString("GENDER"));
					list.add(dt);
				}
				return list;
			}
		});
	}

	public List<BillMasterPojo> BillMasterSearchByDate(Date date1, Date date2) {
		String sql = "SELECT * FROM billmaster WHERE BILLDATE between '" + date1 + "' and '" + date2 + "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillMasterPojo>>() {
			public List<BillMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<BillMasterPojo> list = new ArrayList<BillMasterPojo>();

				while (rs.next()) {
					System.out.println("oii1");
					BillMasterPojo dt = new BillMasterPojo();
					dt.setBillno(rs.getString("BILLNO"));
					dt.setPatientid(rs.getString("PATIENTID"));
					dt.setBillstatus(rs.getString("BILLSTATUS"));
					dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
					dt.setBilldate(rs.getDate("BILLDATE"));
					dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
					dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
					dt.setCgst(rs.getFloat("CGST"));
					dt.setSgst(rs.getFloat("SGST"));
					dt.setNetamount(rs.getFloat("NETAMOUNT"));
					dt.setReason(rs.getString("REASON"));
					dt.setPatientName(rs.getString("PATIENTNAME"));
					dt.setMobileNo(rs.getString("MOBILENO"));
					dt.setDoctorName(rs.getString("DOCTORNAME"));
					dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					dt.setDoctorId(rs.getString("DOCTORID"));
					dt.setBillingFlag(rs.getString("BILLINGFLAG"));
					dt.setInOutFlag(rs.getString("INOUTFLAG"));
					dt.setDoa(rs.getDate("DOA"));
					dt.setDod(rs.getDate("DOD"));
					dt.setWardNo(rs.getString("WARDNO"));
					dt.setIpNo(rs.getString("IPNO"));
					dt.setLessAdvancePaid(rs.getDouble("LESSADVANCEDPAID"));
					dt.setFinalTotal(rs.getDouble("FINALTOTAL"));
					dt.setAge(rs.getInt("AGE"));
					dt.setGender(rs.getString("GENDER"));
					dt.setBilldetailpojo(bddao.billDetailSerialId(rs.getInt("BILLSERIALID")));
					list.add(dt);
					System.out.println(list);
				}
				return list;
			}
		});
	}

	public List<BillMasterPojo> billMasterByWOC() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM billmaster WHERE NOT BILLSTATUS='CANCEL' ORDER BY BILLNO";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillMasterPojo>>() {
			public List<BillMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<BillMasterPojo> list = new ArrayList<BillMasterPojo>();
				while (rs.next()) {
					BillMasterPojo dt = new BillMasterPojo();

					dt.setBillno(rs.getString("BILLNO"));
					dt.setPatientid(rs.getString("PATIENTID"));
					dt.setBillstatus(rs.getString("BILLSTATUS"));
					dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
					dt.setBilldate(rs.getDate("BillDate"));
					dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
					dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
					dt.setCgst(rs.getFloat("CGST"));
					dt.setSgst(rs.getFloat("SGST"));
					dt.setNetamount(rs.getFloat("NETAMOUNT"));
					dt.setReason(rs.getString("REASON"));
					dt.setPatientName(rs.getString("PATIENTNAME"));
					dt.setMobileNo(rs.getString("MOBILENO"));
					dt.setDoctorName(rs.getString("DOCTORNAME"));
					dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					dt.setDoctorId(rs.getString("DOCTORID"));
					dt.setBillingFlag(rs.getString("BILLINGFLAG"));
					dt.setInOutFlag(rs.getString("INOUTFLAG"));
					dt.setDoa(rs.getDate("DOA"));
					dt.setDod(rs.getDate("DOD"));
					dt.setWardNo(rs.getString("WARDNO"));
					dt.setIpNo(rs.getString("IPNO"));
					dt.setLessAdvancePaid(rs.getDouble("LESSADVANCEDPAID"));
					dt.setFinalTotal(rs.getDouble("FINALTOTAL"));
					dt.setAge(rs.getInt("AGE"));
					dt.setGender(rs.getString("GENDER"));
					dt.setBilldetailpojo(bddao.billDetailSerialId(rs.getInt("BILLSERIALID")));
					list.add(dt);
				}
				return list;
			}
		});
	}
	
	public boolean addBillMasterHistory(final BillMasterPojo bhpojo) {
		// TODO Auto-generated method stub
		String sql1="INSERT INTO billmasterhistory (BILLSERIALID,BILLNO,PATIENTID,BILLSTATUS ,MODIFIEDUSERID ,BILLDATE,MODIFIEDTIME ,TOTALAMOUNT,SGST,CGST,NETAMOUNT,REASON,PATIENTNAME,MOBILENO,DOCTORNAME,PRESCRIPTIONNO,DOCTORID,BILLINGFLAG,INOUTFLAG,DOA,DOD,WARDNO,IPNO,LESSADVANCEDPAID,FINALTOTAL,AGE,GENDER) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {
				ps.setInt(1, bhpojo.getSerialid());
				ps.setString(2, bhpojo.getBillno());
				ps.setString(3, bhpojo.getPatientid());
				ps.setString(4, bhpojo.getModifieduserid());
				ps.setString(5, bhpojo.getBillstatus());
				ps.setDate(6, bhpojo.getBilldate());
				ps.setString(7, bhpojo.getModifiedtime());
				ps.setFloat(8, bhpojo.getTotalamount());
				ps.setFloat(9, bhpojo.getCgst());
				ps.setFloat(10, bhpojo.getSgst());
				ps.setFloat(11, bhpojo.getNetamount());
				ps.setString(12, bhpojo.getReason());
				ps.setString(12, bhpojo.getPatientName());
				ps.setString(13,bhpojo.getMobileNo());
				ps.setString(14, bhpojo.getDoctorName());
				ps.setString(15, bhpojo.getPrescriptionNo());
				ps.setString(16,bhpojo.getDoctorId());
				ps.setString(17, bhpojo.getBillingFlag());
				ps.setString(18, bhpojo.getInOutFlag());
				ps.setDate(19,bhpojo.getDoa());
				ps.setDate(20, bhpojo.getDod());
				ps.setString(21,bhpojo.getWardNo());
				ps.setString(22, bhpojo.getIpNo());
				ps.setDouble(23, bhpojo.getLessAdvancePaid());
				ps.setDouble(24, bhpojo.getFinalTotal());
				ps.setInt(25, bhpojo.getAge());
				ps.setString(26, bhpojo.getGender());
			    return ps.execute();
			}
		});

	}
	

	public List<BillMasterPojo> billMasterhistoryAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM billmasterhistory ORDER BY BILLNO DESC LIMIT 10";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillMasterPojo>>() {
			public List<BillMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<BillMasterPojo> list = new ArrayList<BillMasterPojo>();
				while (rs.next()) {
					BillMasterPojo dt = new BillMasterPojo();

					dt.setBillno(rs.getString("BILLNO"));
					dt.setSerialid(rs.getInt("BILLSERIALID"));
					dt.setPatientid(rs.getString("PATIENTID"));
					dt.setBillstatus(rs.getString("BILLSTATUS"));
					dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
					dt.setBilldate(rs.getDate("BillDate"));
					dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
					dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
					dt.setCgst(rs.getFloat("CGST"));
					dt.setSgst(rs.getFloat("SGST"));
					dt.setNetamount(rs.getFloat("NETAMOUNT"));
					dt.setReason(rs.getString("REASON"));
					dt.setPatientName(rs.getString("PATIENTNAME"));
					dt.setMobileNo(rs.getString("MOBILENO"));
					dt.setDoctorName(rs.getString("DOCTORNAME"));
					dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					dt.setDoctorId(rs.getString("DOCTORID"));
					dt.setBillingFlag(rs.getString("BILLINGFLAG"));
					dt.setInOutFlag(rs.getString("INOUTFLAG"));
					dt.setDoa(rs.getDate("DOA"));
					dt.setDod(rs.getDate("DOD"));
					dt.setWardNo(rs.getString("WARDNO"));
					dt.setIpNo(rs.getString("IPNO"));
					dt.setLessAdvancePaid(rs.getDouble("LESSADVANCEDPAID"));
					dt.setFinalTotal(rs.getDouble("FINALTOTAL"));
					dt.setAge(rs.getInt("AGE"));
					dt.setGender(rs.getString("GENDER"));
					list.add(dt);
				}
				return list;
			}
		});
	}
	
	public boolean reasonUpdate(final BillMasterPojo data) {
		loggeduser = login.loginDao(data.getLogusername(), data.getLogpassword());
		if (loggeduser.getUserName().equals(data.getLogusername()) && loggeduser.getPassword().equals(data.getLogpassword())
				&& !loggeduser.getUserCategory().equals("P")) {

			// TODO Auto-generated method stub
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
			cal.get(Calendar.DATE);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String fd = format1.format(cal.getTime());
			System.out.println(fd);
			// Output "2012-09-26"

			String sql1 = "Select BILLDATE  from billmaster WHERE BILLNO='" + data.getBillno() + "'";
			int rs = jdbcTemplate.query(sql1, new ResultSetExtractor<Integer>() {
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					System.out.println("date kulla");
					String text;
					if (rs.next()) {
						System.out.println("oii1");
						Date dt = rs.getDate("BILlDATE");
						SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
						text = ff.format(dt);
						System.out.println(text);

						if (text.equals(fd)) {
							System.out.println("stage1");
							bm = BillMasterSearchByBillNo(data.getBillno());
							 
							String query = "INSERT INTO billmasterhistory (BILLNO,PATIENTID,BILLSTATUS ,MODIFIEDUSERID ,BILLDATE,MODIFIEDTIME ,TOTALAMOUNT,SGST,CGST,NETAMOUNT,REASON,PATIENTNAME,MOBILENO,DOCTORNAME,PRESCRIPTIONNO,DOCTORID,BILLINGFLAG,INOUTFLAG,DOA,DOD,WARDNO,IPNO,LESSADVANCEDPAID,FINALTOTAL) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

						 jdbcTemplate.execute(query, new PreparedStatementCallback<String>() {
								public String doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
									ps.setString(1, bm.getBillno());
									ps.setString(2, bm.getPatientid());
									ps.setString(3, bm.getBillstatus());
									ps.setString(4, bm.getModifieduserid());
									ps.setDate(5, bm.getBilldate());
									ps.setString(6, bm.getModifiedtime());
									ps.setFloat(7, bm.getTotalamount());
									ps.setFloat(8, bm.getSgst());
									ps.setFloat(9, bm.getCgst());
									ps.setFloat(10, bm.getNetamount());
									ps.setString(11, bm.getReason());
									ps.setString(12, bm.getPatientName());
									ps.setString(13,bm.getMobileNo());
									ps.setString(14, bm.getDoctorName());
									ps.setString(15, bm.getPrescriptionNo());
									ps.setString(16, bm.getDoctorId());
									ps.setString(17, bm.getBillingFlag());
									ps.setString(18, bm.getInOutFlag());
									ps.setDate(19,bm.getDoa());
									ps.setDate(20, bm.getDod());
									ps.setString(21,bm.getWardNo());
									ps.setString(22, bm.getIpNo());
									ps.setDouble(23, bm.getLessAdvancePaid());
									ps.setDouble(24, bm.getFinalTotal());
									
									System.out.println("Reason"+bm.getReason());
									if (ps.execute()) {
										System.out.println("if-stage2");
										return "false";
									} else {
										System.out.println("else-stage2");
										System.out.println(bm.getBillno());
										return "";
									}
								}
							});
						String sid = "Select BILLSERIALID  from billmasterhistory order by BILLSERIALID DESC";
						System.out.println(sid);
						jdbcTemplate.query(sid, new ResultSetExtractor<Integer>() {
							public Integer extractData(ResultSet rs1) throws SQLException, DataAccessException {
								System.out.println("sid kulla");
								if (rs1.next()) {
									System.out.println("sid if kulla");
									id = rs1.getInt("BILLSERIALID");
									System.out.println(id);
								} else {
									System.out.println("else");
									String paa = new String();
								}
								return null;
							}
						});

						 String sql2="INSERT INTO billdetailhistory (DOCTORID,BILLSERIALID,BILLNO,FEEID ,AMOUNT,CGSTPERCENTAGE,CGSTAMOUNT,SGSTPERCENTAGE,SGSTAMOUNT,GSTAMOUNT,NETAMOUNT,TESTID,TESTMASTERNAME,ORDERNO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							System.out.println(sql2);
						// List<BillDetailPojo>  a= new ArrayList<BillDetailPojo>();
						 System.out.println(bm.getBillno()+"Before set value"+bddao.billDetailGetByNo(bm.getBillno()).toString());
						 List<BillDetailPojo> a = bddao.billDetailGetByNo(bm.getBillno());
						// final List<BillDetailPojo>  a = b;*/
						 System.out.println("after tonton");
						 jdbcTemplate.execute(sql2,new PreparedStatementCallback<Boolean>(){  
								public Boolean doInPreparedStatement(PreparedStatement ps)  
								        throws SQLException, DataAccessException {
									for(int i=0;i<a.size();i++)
									{System.out.println("for kula");
									ps.setString(1, a.get(i).getDoctorid());
									ps.setInt(2, id);
									ps.setString(3, a.get(i).getBillno());
									ps.setInt(4, a.get(i).getFeeid());
									ps.setFloat(5, a.get(i).getAmount());
									ps.setFloat(6, a.get(i).getCgstpercentage());
									ps.setFloat(7, a.get(i).getCgstamount());
									ps.setFloat(8, a.get(i).getSgstpercentage());
									ps.setFloat(9, a.get(i).getSgstamount());
									ps.setFloat(10, a.get(i).getGstamount());
									ps.setFloat(11, a.get(i).getNetamount());
									ps.setInt(12, a.get(i).getTestid());
									ps.setString(13, a.get(i).getTestMasterName());
									ps.setInt(14, a.get(i).getOrderNo());
									ps.execute();
									System.out.println("stage3");
									}
									return false;
								}
								});
						 System.out.println("stage4");
							return jdbcTemplate.update("UPDATE billmaster set REASON ='"+data.getReason()+"' WHERE BILLNO = ?", new Object[] { data.getBillno() });
							
						} else {
							System.out.println("else stage4 0");
							return 0;
						}
					} else {
						System.out.println("else 0");
						String paa = new String();
						return 0;
					}
				}
			});
			if (rs > 0) {
				System.out.println("false");
				return false;
			}
		}
		System.out.println("true");
		return true;
	}
	
	public List<BillMasterPojo> BillMasterHistorySearchByDate(Date date1, Date date2) {
		String sql = "SELECT * FROM billmasterhistory WHERE BILLDATE between '" + date1 + "' and '" + date2 + "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillMasterPojo>>() {
			public List<BillMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<BillMasterPojo> list = new ArrayList<BillMasterPojo>();

				while (rs.next()) {
					System.out.println("oii1");
					BillMasterPojo dt = new BillMasterPojo();
					dt.setSerialid(rs.getInt("BILLSERIALID"));
					dt.setBillno(rs.getString("BILLNO"));
					dt.setPatientid(rs.getString("PATIENTID"));
					dt.setBillstatus(rs.getString("BILLSTATUS"));
					dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
					dt.setBilldate(rs.getDate("BILLDATE"));
					dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
					dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
					dt.setCgst(rs.getFloat("CGST"));
					dt.setSgst(rs.getFloat("SGST"));
					dt.setNetamount(rs.getFloat("NETAMOUNT"));
					dt.setReason(rs.getString("REASON"));
					dt.setPatientName(rs.getString("PATIENTNAME"));
					dt.setMobileNo(rs.getString("MOBILENO"));
					dt.setDoctorName(rs.getString("DOCTORNAME"));
					dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					dt.setDoctorId(rs.getString("DOCTORID"));
					dt.setBillingFlag(rs.getString("BILLINGFLAG"));
					dt.setInOutFlag(rs.getString("INOUTFLAG"));
					dt.setDoa(rs.getDate("DOA"));
					dt.setDod(rs.getDate("DOD"));
					dt.setWardNo(rs.getString("WARDNO"));
					dt.setIpNo(rs.getString("IPNO"));
					dt.setLessAdvancePaid(rs.getDouble("LESSADVANCEDPAID"));
					dt.setFinalTotal(rs.getDouble("FINALTOTAL"));
					dt.setAge(rs.getInt("AGE"));
					dt.setGender(rs.getString("GENDER"));
					list.add(dt);
				}
				return list;
			}
		});
	}
	
	public List<BillMasterPojo> BillMasterHistorySearchByAll(String all) {
		String sql = "SELECT * FROM billmaster WHERE PATIENTID LIKE ? or BILLNO LIKE ?";
		return jdbcTemplate.query(sql, new Object[] { all, all }, new ResultSetExtractor<List<BillMasterPojo>>() {
			public List<BillMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<BillMasterPojo> list = new ArrayList<BillMasterPojo>();

				while (rs.next()) {
					System.out.println("oii1");
					BillMasterPojo dt = new BillMasterPojo();
					dt.setBillno(rs.getString("BILLNO"));
					dt.setPatientid(rs.getString("PATIENTID"));
					dt.setBillstatus(rs.getString("BILLSTATUS"));
					dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
					dt.setBilldate(rs.getDate("BillDate"));
					dt.setModifiedtime(rs.getString("MODIFIEDTIME"));
					dt.setTotalamount(rs.getFloat("TOTALAMOUNT"));
					dt.setCgst(rs.getFloat("CGST"));
					dt.setSgst(rs.getFloat("SGST"));
					dt.setNetamount(rs.getFloat("NETAMOUNT"));
					dt.setReason(rs.getString("REASON"));
					dt.setPatientName(rs.getString("PATIENTNAME"));
					dt.setMobileNo(rs.getString("MOBILENO"));
					dt.setDoctorName(rs.getString("DOCTORNAME"));
					dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					dt.setDoctorId(rs.getString("DOCTORID"));
					dt.setBillingFlag(rs.getString("BILLINGFLAG"));
					dt.setInOutFlag(rs.getString("INOUTFLAG"));
					dt.setDoa(rs.getDate("DOA"));
					dt.setDod(rs.getDate("DOD"));
					dt.setWardNo(rs.getString("WARDNO"));
					dt.setIpNo(rs.getString("IPNO"));
					dt.setLessAdvancePaid(rs.getDouble("LESSADVANCEDPAID"));
					dt.setFinalTotal(rs.getDouble("FINALTOTAL"));
					dt.setAge(rs.getInt("AGE"));
					dt.setGender(rs.getString("GENDER"));
					list.add(dt); 
				}
				return list;
			}
		});
	}

	public List<BillMasterPojo> ModifiedUserid(String bn) {
		// TODO Auto-generated method stub
		String sql = "SELECT MODIFIEDUSERID FROM billmasterhistory where BILLNO = '"+bn+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<BillMasterPojo>>() {
			public List<BillMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<BillMasterPojo> list = new ArrayList<BillMasterPojo>();
				while (rs.next()) {
					BillMasterPojo dt = new BillMasterPojo();
					dt.setModifieduserid(rs.getString("MODIFIEDUSERID"));
					list.add(dt);
				}
				return list;
			}
		});
	}
	
	public boolean changeBillStatusCancel(final BillMasterReasonPojo data) {
		loggeduser = login.loginDao(data.getLogusername(), data.getLogpassword());
		if (loggeduser.getUserName().equals(data.getLogusername()) && loggeduser.getPassword().equals(data.getLogpassword())
				&& !loggeduser.getUserCategory().equals("P")) {

			// TODO Auto-generated method stub
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
			cal.get(Calendar.DATE);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format2 = new SimpleDateFormat("h:mm a");
			String fd = format1.format(cal.getTime());
			String fd2 = format2.format(cal.getTime());
			System.out.println(fd);
			// Output "2012-09-26"

			String sql1 = "Select BILLDATE  from billmaster WHERE BILLNO='" + data.getBillno() + "'";
			int rs = jdbcTemplate.query(sql1, new ResultSetExtractor<Integer>() {
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					System.out.println("date kulla");
					String text;
					if (rs.next()) {
						System.out.println("oii1");
						Date dt = rs.getDate("BILlDATE");
						SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
						text = ff.format(dt);
						System.out.println(text);

						if (text.equals(fd)) {
							
							int c=jdbcTemplate.update("UPDATE billmaster set BILLSTATUS ='CANCEL',MODIFIEDTIME = '"+ fd2 + "', REASON ='"+data.getReason()+"' WHERE BILLNO = ?", new Object[] { data.getBillno() });
							
							System.out.println("stage1");
							bm = BillMasterSearchByBillNo(data.getBillno());
							 
							String query = "INSERT INTO billmasterhistory (BILLNO,PATIENTID,BILLSTATUS ,MODIFIEDUSERID ,BILLDATE,MODIFIEDTIME ,TOTALAMOUNT,SGST,CGST,NETAMOUNT,REASON,PATIENTNAME,MOBILENO,DOCTORNAME,PRESCRIPTIONNO,DOCTORID,BILLINGFLAG,INOUTFLAG,DOA,DOD,WARDNO,IPNO,LESSADVANCEDPAID,FINALTOTAL) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

						 jdbcTemplate.execute(query, new PreparedStatementCallback<String>() { 
								public String doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
									ps.setString(1, bm.getBillno());
									ps.setString(2, bm.getPatientid());
									ps.setString(3, bm.getBillstatus());
									ps.setString(4, bm.getModifieduserid());
									ps.setDate(5, bm.getBilldate());
									ps.setString(6, bm.getModifiedtime());
									ps.setFloat(7, bm.getTotalamount());
									ps.setFloat(8, bm.getSgst());
									ps.setFloat(9, bm.getCgst());
									ps.setFloat(10, bm.getNetamount());
									ps.setString(11, bm.getReason());
									ps.setString(12, bm.getPatientName());
									ps.setString(13,bm.getMobileNo());
									ps.setString(14, bm.getDoctorName());
									ps.setString(15, bm.getPrescriptionNo());
									ps.setString(16, bm.getDoctorId());
									ps.setString(17, bm.getBillingFlag());
									ps.setString(18, bm.getInOutFlag());
									ps.setDate(19,bm.getDoa());
									ps.setDate(20, bm.getDod());
									ps.setString(21,bm.getWardNo());
									ps.setString(22, bm.getIpNo());
									ps.setDouble(23, bm.getLessAdvancePaid());
									ps.setDouble(24, bm.getFinalTotal());
									System.out.println("Reason"+bm.getReason());
									if (ps.execute()) {
										System.out.println("if-stage2");
										return "false";
									} else {
										System.out.println("else-stage2");
										System.out.println(bm.getBillno());
										return "";
									}
								}
							});
						String sid = "Select BILLSERIALID  from billmasterhistory order by BILLSERIALID DESC";
						System.out.println(sid);
						jdbcTemplate.query(sid, new ResultSetExtractor<Integer>() {
							public Integer extractData(ResultSet rs1) throws SQLException, DataAccessException {
								System.out.println("sid kulla");
								if (rs1.next()) {
									System.out.println("sid if kulla");
									id = rs1.getInt("BILLSERIALID");
									System.out.println(id);
								} else {
									System.out.println("else");
									String paa = new String();
								}
								return null;
							}
						});

						 String sql2="INSERT INTO billdetailhistory (DOCTORID,BILLSERIALID,BILLNO,FEEID ,AMOUNT,CGSTPERCENTAGE,CGSTAMOUNT,SGSTPERCENTAGE,SGSTAMOUNT,GSTAMOUNT,NETAMOUNT,TESTID,TESTMASTERNAME,ORDERNO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							System.out.println(sql2);
						// List<BillDetailPojo>  a= new ArrayList<BillDetailPojo>();
						 System.out.println(bm.getBillno()+"Before set value"+bddao.billDetailGetByNo(bm.getBillno()).toString());
						 List<BillDetailPojo> a = bddao.billDetailGetByNo(bm.getBillno());
						// final List<BillDetailPojo>  a = b;*/
						 System.out.println("after tonton");
						 jdbcTemplate.execute(sql2,new PreparedStatementCallback<Boolean>(){  
								public Boolean doInPreparedStatement(PreparedStatement ps)  
								        throws SQLException, DataAccessException {
									for(int i=0;i<a.size();i++)
									{System.out.println("for kula");
									ps.setString(1, a.get(i).getDoctorid());
									ps.setInt(2, id);
									ps.setString(3, a.get(i).getBillno());
									ps.setInt(4, a.get(i).getFeeid());
									ps.setFloat(5, a.get(i).getAmount());
									ps.setFloat(6, a.get(i).getCgstpercentage());
									ps.setFloat(7, a.get(i).getCgstamount());
									ps.setFloat(8, a.get(i).getSgstpercentage());
									ps.setFloat(9, a.get(i).getSgstamount());
									ps.setFloat(10, a.get(i).getGstamount());
									ps.setFloat(11, a.get(i).getNetamount());
									ps.setInt(12, a.get(i).getTestid());
									ps.setString(13, a.get(i).getTestMasterName());
									ps.setInt(14, a.get(i).getOrderNo());
									ps.execute();
									System.out.println("stage3");
									}
									return false;
								}
								});
						 System.out.println("stage4");
							return c;
							
						} else {
							System.out.println("else stage4 0");
							return 0;
						}
					} else {
						System.out.println("else 0");
						String paa = new String();
						return 0;
					}
				}
			});
			if (rs > 0) {
				System.out.println("false");
				return false;
			}
		}
		System.out.println("true");
		return true;
	}

	

	
}
