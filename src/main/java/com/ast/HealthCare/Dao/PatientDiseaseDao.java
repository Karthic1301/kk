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
import com.ast.HealthCare.Pojo.PatientDiseasePojo;
import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PatientDiseaseDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
	
	
	JpaConfiguration jpa = new JpaConfiguration();
	PatientDiseaseDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("PatientDiseaseDao constructor jdbc "+this.jdbcTemplate);
	}
	
	// CREATE TABLE patientdisease(PATIENTID INTEGER,DISEASEID
	// INTEGER,DISEASESTARTDATE DATE,DISEASEENDDATE DATE,STATUS TEXT);


	
	public Boolean addPatientDisease(List<PatientDiseasePojo> patientDisease, String prescriptionno) {
		/*
		for(int i=0;i<patientDisease.size();i++)
		{
		if(patientDisease.get(i).getStatus().equalsIgnoreCase("InActive")) {
			
			return updatePatientDisease(patientDisease.get(i));
		}
		}
		for(int i=0;i<patientDisease.size();i++)
		{
		List<PatientDiseasePojo> l1 =  patientDiseaseSearchByPatientIdAndDiseaseId(patientDisease.get(i).getPatientId(), patientDisease.get(i).getDiseaseId() );
		PatientDiseasePojo pd = new PatientDiseasePojo();
		if(!(l1.isEmpty())) {
			pd=l1.get(0);
			if(pd.getStatus().equalsIgnoreCase("active")){
				return true;
			}
			
		}
		}
						
		
		*/
		patientDiseaseDeleteByPrescription(prescriptionno);
		System.out.println("disease da" + patientDisease);
		
	   String sql="INSERT INTO patientdisease (PRESCRIPTIONNO,PATIENTID,DISEASESTARTDATE,DISEASEENDDATE,STATUS,DISEASEID) values(?,?,?,?,?,?)";		
		return jdbcTemplate.execute(sql,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {
				for(int i=0;i<patientDisease.size();i++)
				{
				ps.setString(1, patientDisease.get(i).getPrescriptionNo());
				ps.setString(2, patientDisease.get(i).getPatientId());
				ps.setDate(3, patientDisease.get(i).getDiseaseStartDate());
				ps.setDate(4, patientDisease.get(i).getDiseaseEndDate());
				ps.setString(5, patientDisease.get(i).getStatus());
				ps.setInt(6, patientDisease.get(i).getDiseaseId());
			    ps.execute();
				}
				return true;
			}
		});
		}
/*
	private boolean updatePatientDisease(PatientDiseasePojo patientDisease) {
		List<PatientDiseasePojo> l1 =  patientDiseaseSearchByPatientIdAndDiseaseId(patientDisease.getPatientId(), patientDisease.getDiseaseId() );
		//final PatientDiseasePojo pd = new PatientDiseasePojo();
		if(!(l1.isEmpty())) {
			final PatientDiseasePojo pd=l1.get(0);
			String query="UPDATE patientdisease set DISEASEENDDATE = ? STATUS = ? WHERE PATIENTID = ? AND DISEASEID = ? AND DISEASESTARTDATE = ? ";
			
			return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
			   
				ps.setDate(1, patientDisease.getDiseaseEndDate());
			    ps.setString(2, patientDisease.getStatus());
			    ps.setString(3, pd.getPatientId());
			    ps.setInt(4, pd.getDiseaseId());
			    ps.setDate(5, pd.getDiseaseStartDate());
			    return ps.execute();
			}
			});

			}
		return false;
		}
*/
	public List<PatientDiseasePojo> patientDiseaseAll() {
		String sql = "SELECT * FROM patientdisease";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientDiseasePojo>>() {
	        public List<PatientDiseasePojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientDiseasePojo> list = new  ArrayList<PatientDiseasePojo>();
	            while(rs.next()) {
	            	PatientDiseasePojo pd = new PatientDiseasePojo();
	            	pd.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	            	pd.setPatientId(rs.getString("PATIENTID"));
	            	pd.setDiseaseId(rs.getInt("DISEASEID"));
	            	String sql1 ="select * from diseasemaster where DISEASEID = ?";
	            	String disname = jdbcTemplate.query(sql1, new Object[] {rs.getInt("DISEASEID")},new ResultSetExtractor<String>()
	            			{
	            				public String extractData(ResultSet rs) throws SQLException {
	            					return rs.getString("DISEASENAME");
	            				}
	            			});
	            	pd.setDiseaseName(disname);
	            	pd.setDiseaseStartDate(rs.getDate("DISEASESTARTDATE"));
	            	pd.setDiseaseEndDate(rs.getDate("DISEASEENDDATE"));
	            	pd.setStatus(rs.getString("STATUS"));
	            	list.add(pd);
	            }
	            return list;
	            }
	    });
		}

	public List<PatientDiseasePojo> patientDiseaseSearchByPatientId(String pid) {
		String sql = "SELECT * FROM patientdisease WHERE PATIENTID = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<PatientDiseasePojo>>() {
	        public List<PatientDiseasePojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	           	List<PatientDiseasePojo> list = new  ArrayList<PatientDiseasePojo>();
	            while(rs.next()) {
	            	PatientDiseasePojo pd = new PatientDiseasePojo();
	            	pd.setPatientId(rs.getString("PATIENTID"));
	            	pd.setDiseaseId(rs.getInt("DISEASEID"));
	            	
	            	pd.setDiseaseName(rs.getString("DISEASENAME"));
	            	
	            	pd.setDiseaseStartDate(rs.getDate("DISEASESTARTDATE"));
	            	pd.setDiseaseEndDate(rs.getDate("DISEASEENDDATE"));
	            	pd.setStatus(rs.getString("STATUS"));
	            	String sql1= "select DISEASENAME from diseasemaster WHERE DISEASEID = ?";
	            	String disname = (String) jdbcTemplate.query(sql1, new Object[] {pd.getDiseaseId()}, new ResultSetExtractor<String>(){
	            	
	            		public  String extractData(ResultSet rs) throws SQLException
	            		{
	            			DiseasePojo dp =new DiseasePojo();
	            			if(rs.next())
	            			{
	            				
	            				return rs.getString("DISEASENAME");
	            				
	            			}
	            			else
	            			{
	            				System.out.println("disease name not found");
							return null;
	            			}
	            		}
	            	});
	            	if(disname != null)
	            	{
	            		System.out.println("diseasename found" + disname);
	            		pd.setDiseaseName(disname);
	            	}
	            	
	            	list.add(pd);
	            }
	            return list;
	            }
	    });
	}

	public List<PatientDiseasePojo> patientDiseaseSearchByPatientIdAndDiseaseId(String pid,int did) {
		String sql = "SELECT * FROM patientdisease WHERE PATIENTID = ? AND DISEASEID = ? ORDER BY DISEASESTARTDATE DESC";
		return jdbcTemplate.query(sql,  new Object[] { pid , did }, new ResultSetExtractor<List<PatientDiseasePojo>>() {
	        public List<PatientDiseasePojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	           	List<PatientDiseasePojo> list = new  ArrayList<PatientDiseasePojo>();
	            while(rs.next()) {
	            	PatientDiseasePojo pd = new PatientDiseasePojo();
	            	pd.setPatientId(rs.getString("PATIENTID"));
	            	pd.setDiseaseId(rs.getInt("DISEASEID"));
	            	String sql1 ="select * from diseasemaster where DISEASEID = ?";
	            	String disname = jdbcTemplate.query(sql1, new Object[] {rs.getInt("DISEASEID")},new ResultSetExtractor<String>()
	            			{
	            				public String extractData(ResultSet rs) throws SQLException {
	            					if(rs.next())
	            					{
	            						
	            					return rs.getString("DISEASENAME");
	            					}
	            					else
	            					{
	            						System.out.println("disease name not found");
	            						return null;
	            					}
	            				}
	            			});
	            	if(disname != null)
	            	{
	            	System.out.println("diseasename found" + disname);
	            	pd.setDiseaseName(disname);
	            	}
	            	pd.setDiseaseName(disname);
	            	pd.setDiseaseStartDate(rs.getDate("DISEASESTARTDATE"));
	            	pd.setDiseaseEndDate(rs.getDate("DISEASEENDDATE"));
	            	pd.setStatus(rs.getString("STATUS"));
	            	list.add(pd);
	            }
	            return list;
	            }
	    });
	}

	
	public int patientDiseaseDelete(String pid, int did) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientdisease WHERE PATIENTID = ? AND DISEASEID = ?", new Object[] { pid , did });
	}
	public int patientDiseaseDeleteByPrescription(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientdisease WHERE PRESCRIPTIONNO = ?", new Object[] { pid });
	}
	
	public List<PatientDiseasePojo> patientDiseaseSearchByPrescriptionNo1(String pid) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM patientdisease WHERE PRESCRIPTIONNO = ? ";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<PatientDiseasePojo>>() {
	        public List<PatientDiseasePojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	
	        	List<PatientDiseasePojo> list = new  ArrayList<PatientDiseasePojo>();
	            while(rs.next()) {
	            	PatientDiseasePojo pd = new PatientDiseasePojo();
	            	pd.setPatientId(rs.getString("PATIENTID"));
	            	pd.setDiseaseId(rs.getInt("DISEASEID"));
	            	String sql1 ="select * from diseasemaster where DISEASEID = ?";
	            	String disname = jdbcTemplate.query(sql1, new Object[] {rs.getInt("DISEASEID")},new ResultSetExtractor<String>()
	            			{
	            				public String extractData(ResultSet rs) throws SQLException {
	            					if(rs.next())
	            					{
	            						
	            					return rs.getString("DISEASENAME");
	            					}
	            					else
	            					{
	            						System.out.println("disease name not found");
	            						return null;
	            					}
	            				}
	            			});
	            	if(disname != null)
	            	{
	            		System.out.println("diseasename found" + disname);
	            	pd.setDiseaseName(disname);
	            	}
	            	pd.setDiseaseStartDate(rs.getDate("DISEASESTARTDATE"));
	            	pd.setDiseaseEndDate(rs.getDate("DISEASEENDDATE"));
	            	pd.setStatus(rs.getString("STATUS"));
	            	list.add(pd);
	            }
	            return list;
	            }
	    });
	}
	
	public List<PatientPojo> patientDiseaseSearchByDiseaseIdForSMS(int did) {
		String sql = "select distinct on (patientid) PATIENTID,STATUS from patientdisease WHERE DISEASEID = ? order by  patientid, PRESCRIPTIONNO DESC ";
		return jdbcTemplate.query(sql,  new Object[] {did }, new ResultSetExtractor<List<PatientPojo>>() {
	        public List<PatientPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("hello");
	           	List<PatientPojo> list = new  ArrayList<PatientPojo>();
	            while(rs.next()) {
	            	System.out.println("while");
	            	if(rs.getString("STATUS").equals("Active")) {
	            		System.out.println("if");
	            		String sq2 = "SELECT PATIENTID,PATIENTFIRSTNAME,PATIENTMIDDLENAME,PATIENTLASTNAME,PATIENTGENDER,PATIENTDATEOFBIRTH,PATIENTMOBILE1,PATIENTBLOODGROUP FROM patientmaster WHERE PATIENTID=?";
	            		 jdbcTemplate.query(sq2,  new Object[] { rs.getString("PATIENTID") }, new ResultSetExtractor<List<PatientPojo>>() {
	            	        public List<PatientPojo> extractData(ResultSet rs1) throws SQLException,
	            	                DataAccessException {
	            	        	System.out.println("oii");
	            	        	//PatientPojo patient = new PatientPojo();
	                            
	            	        	while(rs1.next()) {
	            	            	System.out.println("oii1");
	            	                PatientPojo patient = new PatientPojo();
	            	                patient.setPatientId(rs1.getString("PATIENTID"));
	            	                patient.setPatientFirstName(rs1.getString("PATIENTFIRSTNAME"));
	            	                patient.setPatientMiddleName(rs1.getString("PATIENTMIDDLENAME"));
	            	                patient.setPatientLastName(rs1.getString("PATIENTLASTNAME"));
	            	                patient.setPatientGender(rs1.getString("PATIENTGENDER"));
	            	                patient.setPatientDOB(rs1.getDate("PATIENTDATEOFBIRTH"));
	            	                patient.setPatientMobile1(rs1.getString("PATIENTMOBILE1"));
	            	                patient.setPatientBloodGroup(rs1.getString("PATIENTBLOODGROUP"));
	            	                list.add(patient);
	            	                
	            	            }
	            	        	System.out.println(list);
	            	            return list;
	            	            
	            	            }
	            	    });

	            	}
	            }
	            System.out.println(list);
	            return list;
	            }
	    });
	}


}
