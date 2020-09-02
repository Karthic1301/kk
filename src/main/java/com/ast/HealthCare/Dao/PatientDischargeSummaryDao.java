package com.ast.HealthCare.Dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.PatientDischargeSummaryPojo;

import com.ast.main.JpaConfiguration;

@Repository
public class PatientDischargeSummaryDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	@Autowired()
	PatientDischargeContentDao contentDao;
	@Autowired()
	SurgicalTypeDao sDao;
	@Value("${const.dataBaseName}")
    private String dbName;
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	PatientDischargeSummaryDao()
	{
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("PatientDischargeSummaryDao constructor jdbc "+this.jdbcTemplate);
	}
	public int getMaximumId() {
		String sql = "SELECT MAX(PATIENTDISCHARGEID) As ID from PatientDischargeSummary";
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
	
	public PatientDischargeSummaryPojo getAutoGenerateIPNo(int typeid)
	{
		PatientDischargeSummaryPojo pojo=new PatientDischargeSummaryPojo();
		int count=0;
		Calendar now = Calendar.getInstance(); // Gets the current date and time
		final int year = now.get(Calendar.YEAR);
		String sqlauto="SELECT count(*)+1 from PatientDischargeSummary where SURGICALTYPEID="+typeid;
		System.out.println("SQLAUTO"+sqlauto);

		int autoIncrementValue = jdbcTemplate.queryForObject(sqlauto,Integer.class);
		do
		{
		String sql="SELECT COUNT(*) FROM PatientDischargeSummary WHERE PATIENTDISCHARGEID="+autoIncrementValue+" AND  SURGICALTYPEID="+typeid;
		System.out.println("SQL"+sql);
		count= jdbcTemplate.queryForObject(sql,Integer.class);
		System.out.println("count"+count);
		if(count>0)
		{
			autoIncrementValue=autoIncrementValue+1;
			System.out.println("autoIncrementValue"+autoIncrementValue);
		}
		System.out.println("countafterif"+count);
		}while(count>0);
		System.out.println(" Final Value"+autoIncrementValue);
		String prefix = sDao.getPrefixById(typeid);
		 int num1=autoIncrementValue;
		 System.out.println("max="+num1);
		
		  String num2; 
		  if(num1<10) { num2="0000000"+num1; } 
		  else if(num1<100) {
		 num2="000000"+num1; } 
		  else if(num1<1000)
		  { num2="00000"+num1; } 
		  else
		  if(num1<10000) { num2="00000"+num1; } 
		  else if(num1<100000) {
		 num2="0000"+num1; } else if(num1<1000000) { num2="000"+num1; }else
		 if(num1<10000000) { num2="00"+num1; } else if(num1<100000000) {
		 num2="0"+num1; } else num2=""+num1;
		 System.out.println(num2);
		 
		String ipno=prefix+year+num2;		
		pojo.setIpNo(ipno);
		return pojo;
	}

	
	
	
	/*
	 * public PatientDischargeSummaryPojo getAutoGenerateIPNo(int typeid) {
	 * PatientDischargeSummaryPojo pojo = new PatientDischargeSummaryPojo(); long
	 * num1; Calendar now = Calendar.getInstance(); // Gets the current date and
	 * time final int year = now.get(Calendar.YEAR);
	 * 
	 * String
	 * sql1="	SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'PatientDischargeSummary' AND TABLE_SCHEMA ='"
	 * + dbName +"'"; System.out.println(sql1); int rec =
	 * jdbcTemplate.queryForObject(sql1,Integer.class);
	 * System.out.println("first entry in table"); num1=rec;
	 * System.out.println("max="+num1);
	 * 
	 * String num2; if(num1<10) { num2="0000000"+num1; } else if(num1<100) {
	 * num2="000000"+num1; } else if(num1<1000) { num2="00000"+num1; } else
	 * if(num1<10000) { num2="00000"+num1; } else if(num1<100000) {
	 * num2="0000"+num1; } else if(num1<1000000) { num2="000"+num1; }else
	 * if(num1<10000000) { num2="00"+num1; } else if(num1<100000000) {
	 * num2="0"+num1; } else num2=""+num1;
	 * 
	 * System.out.println(num2); final String pid=hoscode+year+num2;
	 * System.out.println(hoscode); System.out.println(pid); pojo.setIpNo(pid);
	 * return pojo;
	 * 
	 * }
	 */

	
	public Integer addPatientDischargeSummary(final PatientDischargeSummaryPojo dis)
	{
		// TODO Auto-generated method stub
		String sql1="INSERT INTO PatientDischargeSummary (PATIENTID,PATIENTNAME,PATIENTADDRESS,AGE,SEX,DOA,DOD,WARD,OPNO,IPNO,WEIGHT,TEMPERATURE,PULSE,BP,BMI,HEIGHT,SURGICALTYPEID,SURGICALTYPE,MOBILENO,DOS) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Integer>()
		{  
			public Integer doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, dis.getPatientId());
				ps.setString(2, dis.getPatientName());
				ps.setString(3, dis.getPatientAddress());
				ps.setInt(4, dis.getAge());
				ps.setString(5, dis.getSex());
				ps.setDate(6, dis.getDoa());
				ps.setDate(7, dis.getDod());
				ps.setString(8,dis.getWard());	
				ps.setString(9,dis.getOpNo());
				ps.setString(10, dis.getIpNo());
				ps.setFloat(11, dis.getWeight());
				ps.setFloat(12,dis.getTemperature());
				ps.setFloat(13, dis.getPulse());
				ps.setString(14, dis.getBp());
				ps.setFloat(15,dis.getBmi());
				ps.setFloat(16, dis.getHeight());	
				ps.setInt(17, dis.getSurgicalTypeId());
				ps.setString(18, dis.getSurgicalType());
				ps.setString(19, dis.getMobileNo());
				ps.setDate(20, dis.getDos());
				ps.execute();
				int disId= getMaximumId();
				return disId;
			}
		});
	}

	
	
	public List<PatientDischargeSummaryPojo> patientDischargeSummaryAll() 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientDischargeSummary";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientDischargeSummaryPojo>>()
		{
	        public List<PatientDischargeSummaryPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientDischargeSummaryPojo> list = new  ArrayList<PatientDischargeSummaryPojo>();
	            while(rs.next()) {
	            	PatientDischargeSummaryPojo dt = new PatientDischargeSummaryPojo();
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setPatientDischargeId(rs.getInt("PATIENTDISCHARGEID"));
	            	dt.setPatientName(rs.getString("PATIENTNAME"));
	            	dt.setPatientAddress(rs.getString("PATIENTADDRESS"));
	            	dt.setAge(rs.getInt("AGE"));
	            	dt.setDoa(rs.getDate("DOA"));
	            	dt.setDod(rs.getDate("DOD"));
	            	dt.setWard(rs.getString("WARD"));
	            	dt.setIpNo(rs.getString("IPNO"));            	
	            	dt.setOpNo(rs.getString("OPNO"));
	            	dt.setSex(rs.getString("SEX"));
	            	dt.setWeight(rs.getFloat("WEIGHT"));
	            	dt.setTemperature(rs.getFloat("TEMPERATURE"));
	            	dt.setPulse(rs.getInt("PULSE"));
	            	dt.setBp(rs.getString("BP"));
	            	dt.setBmi(rs.getFloat("BMI"));
	            	dt.setHeight(rs.getFloat("HEIGHT"));
	            	dt.setSurgicalTypeId(rs.getInt("SURGICALTYPEID"));
	            	dt.setSurgicalType(rs.getString("SURGICALTYPE"));
	            	dt.setMobileNo(rs.getString("MOBILENO"));
	            	dt.setDos(rs.getDate("DOS"));
	            	dt.setContentPojo(contentDao.patientDischargeContentByDischargeId(rs.getInt("PATIENTDISCHARGEID")));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	public List<PatientDischargeSummaryPojo> patientDischargeSummaryByDischargeDate(Date from, Date to) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientDischargeSummary where DOD BETWEEN '"+from+"' and '"+to+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientDischargeSummaryPojo>>()
		{
	        public List<PatientDischargeSummaryPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientDischargeSummaryPojo> list = new  ArrayList<PatientDischargeSummaryPojo>();
	            while(rs.next()) {
	            	PatientDischargeSummaryPojo dt = new PatientDischargeSummaryPojo();
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setPatientDischargeId(rs.getInt("PATIENTDISCHARGEID"));
	            	dt.setPatientName(rs.getString("PATIENTNAME"));
	            	dt.setPatientAddress(rs.getString("PATIENTADDRESS"));
	            	dt.setAge(rs.getInt("AGE"));
	            	dt.setDoa(rs.getDate("DOA"));
	            	dt.setDod(rs.getDate("DOD"));
	            	dt.setWard(rs.getString("WARD"));
	            	dt.setIpNo(rs.getString("IPNO"));            	
	            	dt.setOpNo(rs.getString("OPNO"));
	            	dt.setSex(rs.getString("SEX"));
	            	dt.setWeight(rs.getFloat("WEIGHT"));
	            	dt.setTemperature(rs.getFloat("TEMPERATURE"));
	            	dt.setPulse(rs.getInt("PULSE"));
	            	dt.setBp(rs.getString("BP"));
	            	dt.setBmi(rs.getFloat("BMI"));
	            	dt.setHeight(rs.getFloat("HEIGHT"));
	            	dt.setSurgicalTypeId(rs.getInt("SURGICALTYPEID"));
	            	dt.setSurgicalType(rs.getString("SURGICALTYPE"));
	            	dt.setMobileNo(rs.getString("MOBILENO"));
	            	dt.setDos(rs.getDate("DOS"));
	            	dt.setContentPojo(contentDao.patientDischargeContentByDischargeId(rs.getInt("PATIENTDISCHARGEID")));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	
	}
	
	public PatientDischargeSummaryPojo patientDischargeSummaryById(int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientDischargeSummary WHERE PATIENTDISCHARGEID = "+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<PatientDischargeSummaryPojo>()
		{
	        public PatientDischargeSummaryPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	PatientDischargeSummaryPojo dt = new PatientDischargeSummaryPojo();
	            while(rs.next()) {
	            	
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setPatientDischargeId(rs.getInt("PATIENTDISCHARGEID"));
	            	dt.setPatientName(rs.getString("PATIENTNAME"));
	            	dt.setPatientAddress(rs.getString("PATIENTADDRESS"));
	            	dt.setAge(rs.getInt("AGE"));
	            	dt.setDoa(rs.getDate("DOA"));
	            	dt.setDod(rs.getDate("DOD"));
	            	dt.setWard(rs.getString("WARD"));
	            	dt.setIpNo(rs.getString("IPNO"));            	
	            	dt.setOpNo(rs.getString("OPNO"));
	            	dt.setSex(rs.getString("SEX"));
	            	dt.setWeight(rs.getFloat("WEIGHT"));
	            	dt.setTemperature(rs.getFloat("TEMPERATURE"));
	            	dt.setPulse(rs.getInt("PULSE"));
	            	dt.setBp(rs.getString("BP"));
	            	dt.setBmi(rs.getFloat("BMI"));
	            	dt.setHeight(rs.getFloat("HEIGHT"));
	            	dt.setSurgicalTypeId(rs.getInt("SURGICALTYPEID"));
	            	dt.setSurgicalType(rs.getString("SURGICALTYPE"));
	            	dt.setMobileNo(rs.getString("MOBILENO"));
	            	dt.setDos(rs.getDate("DOS"));
	            	dt.setContentPojo(contentDao.patientDischargeContentByDischargeId(rs.getInt("PATIENTDISCHARGEID")));
	               
	            }
	            return dt;
	            
	            }
	    });
	
	}
	
	public PatientDischargeSummaryPojo patientDischargeSummaryByIPNo(String no) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientDischargeSummary WHERE IPNO = '"+no+"'";
		System.out.println(sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<PatientDischargeSummaryPojo>()
		{
	        public PatientDischargeSummaryPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	PatientDischargeSummaryPojo dt = new PatientDischargeSummaryPojo();
	            while(rs.next()) {
	            	
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setPatientDischargeId(rs.getInt("PATIENTDISCHARGEID"));
	            	dt.setPatientName(rs.getString("PATIENTNAME"));
	            	dt.setPatientAddress(rs.getString("PATIENTADDRESS"));
	            	dt.setAge(rs.getInt("AGE"));
	            	dt.setDoa(rs.getDate("DOA"));
	            	dt.setDod(rs.getDate("DOD"));
	            	dt.setWard(rs.getString("WARD"));
	            	dt.setIpNo(rs.getString("IPNO"));            	
	            	dt.setOpNo(rs.getString("OPNO"));
	            	dt.setSex(rs.getString("SEX"));
	            	dt.setWeight(rs.getFloat("WEIGHT"));
	            	dt.setTemperature(rs.getFloat("TEMPERATURE"));
	            	dt.setPulse(rs.getInt("PULSE"));
	            	dt.setBp(rs.getString("BP"));
	            	dt.setBmi(rs.getFloat("BMI"));
	            	dt.setHeight(rs.getFloat("HEIGHT"));
	            	dt.setSurgicalTypeId(rs.getInt("SURGICALTYPEID"));
	            	dt.setSurgicalType(rs.getString("SURGICALTYPE"));
	            	dt.setMobileNo(rs.getString("MOBILENO"));
	            	dt.setDos(rs.getDate("DOS"));
	            	dt.setContentPojo(contentDao.patientDischargeContentByDischargeId(rs.getInt("PATIENTDISCHARGEID")));
	               
	            }
	            return dt;
	            
	            }
	    });
	
	}
	
	public PatientDischargeSummaryPojo patientDischargeSummaryByIPNoAndNotId(String no,int id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientDischargeSummary WHERE IPNO = '"+no+"' AND PATIENTDISCHARGEID !="+id;
		System.out.println(sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<PatientDischargeSummaryPojo>()
		{
	        public PatientDischargeSummaryPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	PatientDischargeSummaryPojo dt = new PatientDischargeSummaryPojo();
	            while(rs.next()) {
	            	
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setPatientDischargeId(rs.getInt("PATIENTDISCHARGEID"));
	            	dt.setPatientName(rs.getString("PATIENTNAME"));
	            	dt.setPatientAddress(rs.getString("PATIENTADDRESS"));
	            	dt.setAge(rs.getInt("AGE"));
	            	dt.setDoa(rs.getDate("DOA"));
	            	dt.setDod(rs.getDate("DOD"));
	            	dt.setWard(rs.getString("WARD"));
	            	dt.setIpNo(rs.getString("IPNO"));            	
	            	dt.setOpNo(rs.getString("OPNO"));
	            	dt.setSex(rs.getString("SEX"));
	            	dt.setWeight(rs.getFloat("WEIGHT"));
	            	dt.setTemperature(rs.getFloat("TEMPERATURE"));
	            	dt.setPulse(rs.getInt("PULSE"));
	            	dt.setBp(rs.getString("BP"));
	            	dt.setBmi(rs.getFloat("BMI"));
	            	dt.setHeight(rs.getFloat("HEIGHT"));
	            	dt.setSurgicalTypeId(rs.getInt("SURGICALTYPEID"));
	            	dt.setSurgicalType(rs.getString("SURGICALTYPE"));
	            	dt.setMobileNo(rs.getString("MOBILENO"));
	            	dt.setDos(rs.getDate("DOS"));
	            	dt.setContentPojo(contentDao.patientDischargeContentByDischargeId(rs.getInt("PATIENTDISCHARGEID")));
	               
	            }
	            return dt;
	            
	            }
	    });
	
	}
	
	public PatientDischargeSummaryPojo patientDischargeSummaryByPatientId(String id) 
	{
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PatientDischargeSummary WHERE PATIENTID = '"+id+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<PatientDischargeSummaryPojo>()
		{
	        public PatientDischargeSummaryPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	PatientDischargeSummaryPojo dt = new PatientDischargeSummaryPojo();
	            while(rs.next()) {
	            	
	            	dt.setPatientId(rs.getString("PATIENTID"));
	            	dt.setPatientDischargeId(rs.getInt("PATIENTDISCHARGEID"));
	            	dt.setPatientName(rs.getString("PATIENTNAME"));
	            	dt.setPatientAddress(rs.getString("PATIENTADDRESS"));
	            	dt.setAge(rs.getInt("AGE"));
	            	dt.setDoa(rs.getDate("DOA"));
	            	dt.setDod(rs.getDate("DOD"));
	            	dt.setWard(rs.getString("WARD"));
	            	dt.setIpNo(rs.getString("IPNO"));            	
	            	dt.setOpNo(rs.getString("OPNO"));
	            	dt.setSex(rs.getString("SEX"));
	            	dt.setWeight(rs.getFloat("WEIGHT"));
	            	dt.setTemperature(rs.getFloat("TEMPERATURE"));
	            	dt.setPulse(rs.getInt("PULSE"));
	            	dt.setBp(rs.getString("BP"));
	            	dt.setBmi(rs.getFloat("BMI"));
	            	dt.setHeight(rs.getFloat("HEIGHT"));
	            	dt.setSurgicalTypeId(rs.getInt("SURGICALTYPEID"));
	            	dt.setSurgicalType(rs.getString("SURGICALTYPE"));
	            	dt.setMobileNo(rs.getString("MOBILENO"));
	            	dt.setDos(rs.getDate("DOS"));
	            	dt.setContentPojo(contentDao.patientDischargeContentByDischargeId(rs.getInt("PATIENTDISCHARGEID")));
	               
	            }
	            return dt;
	            
	            }
	    });
	
	}

	public int patientDischargeSummaryDelete(int pid)
	{
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM PatientDischargeSummary WHERE PATIENTDISCHARGEID = ? ", new Object[] { pid });
	}

	
	
	public boolean patientDischargeSummaryUpdate(final PatientDischargeSummaryPojo dt) 
	{
		// TODO Auto-generated method stub	
		String query="UPDATE PatientDischargeSummary set PATIENTID =?,PATIENTNAME =?,PATIENTADDRESS =?,AGE =?,SEX =?,DOA =?,DOD =?,WARD =?,OPNO=?,IPNO=?,WEIGHT=?,TEMPERATURE=?,PULSE=?,BP=?,BMI=?,HEIGHT=?,SURGICALTYPEID=?,SURGICALTYPE=?,MOBILENO=?,DOS=? where PATIENTDISCHARGEID=?";
		System.out.println(query);
		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>()
		{  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getPatientId());
			ps.setString(2, dt.getPatientName());
			ps.setString(3, dt.getPatientAddress());
			ps.setInt(4, dt.getAge());
			ps.setString(5, dt.getSex());
			ps.setDate(6, dt.getDoa());
			ps.setDate(7, dt.getDod());
			ps.setString(8,dt.getWard());	
			ps.setString(9,dt.getOpNo());
			ps.setString(10, dt.getIpNo());	
			ps.setFloat(11, dt.getWeight());
			ps.setFloat(12,dt.getTemperature());
			ps.setFloat(13, dt.getPulse());
			ps.setString(14, dt.getBp());
			ps.setFloat(15,dt.getBmi());
			ps.setFloat(16, dt.getHeight());	
			ps.setInt(17, dt.getSurgicalTypeId());
			ps.setString(18, dt.getSurgicalType());
			ps.setString(19, dt.getMobileNo());
			ps.setDate(20, dt.getDos());
			ps.setInt(21, dt.getPatientDischargeId());			
		    return ps.execute();
		}
		});
	}
}
