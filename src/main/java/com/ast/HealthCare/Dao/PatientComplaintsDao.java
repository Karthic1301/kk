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

import com.ast.HealthCare.Pojo.PatientComplaintsPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PatientComplaintsDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;

	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	PatientComplaintsDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("PatientComplaintsDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addPatientComplaints(List<PatientComplaintsPojo> comppojo,String prescriptionno) {
		// TODO Auto-generated method stub
		//patientcomplaints(PRESCRIPTIONID INTEGER,PATIENTID INTEGER,COMPLAINTSID INTEGER,
		//COMPLAINTSDURATION DATE,COMPLAINTSDESCRIPTION TEXT,COMPLAINTSTYPE TEXT);
		patientComplaintsDelete(prescriptionno); // for the reation of update
		String sql1="INSERT INTO patientcomplaints(PRESCRIPTIONNO, PATIENTID, COMPLAINTSDURATION, COMPLAINTSDESCRIPTION, COMPLAINTSTYPE,COMPLAINTSID) values(?,?,?,?,?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {
				for(int i=0;i<comppojo.size();i++)
				{
					
				ps.setString(1, comppojo.get(i).getPrescriptionNo());
				ps.setString(2, comppojo.get(i).getPatientId());
				ps.setString(3, comppojo.get(i).getComplaintsDuration());
				ps.setString(4, comppojo.get(i).getComplaintsDescription());
				ps.setString(5, comppojo.get(i).getComplaintsType());
				ps.setInt(6, comppojo.get(i).getComplaintsId());
				ps.execute();
				
				}
				return true;
			}
		});
	}

	public List<PatientComplaintsPojo> patientComplaintsAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM patientcomplaints";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientComplaintsPojo>>() {
	        public List<PatientComplaintsPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientComplaintsPojo> list = new  ArrayList<PatientComplaintsPojo>();
	            while(rs.next()) {
	            	PatientComplaintsPojo dt = new PatientComplaintsPojo();
	                dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dt.setComplaintsId(rs.getInt("COMPLAINTSID"));
	                String sql1 = "SELECT * FROM COMPLAINTSMASTER WHERE COMPLAINTSID= ?";
					String compname = jdbcTemplate.query(sql1, new Object[] { rs.getInt("COMPLAINTSID") },
							new ResultSetExtractor<String>() {
								public String extractData(ResultSet rs1) throws SQLException {
									if (rs1.next()) {
										return rs1.getString("COMPLAINTSNAME");
									} else {
										System.out.println("complaintsname not obtained");
										return null;
									}

								}
							});
					if(compname != null)
					{
						System.out.println("complaints name obtained: "+ compname);
					dt.setComplaintsName(compname);
					
					}

	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setComplaintsDuration(rs.getString("COMPLAINTSDURATION"));
	                dt.setComplaintsDescription(rs.getString("COMPLAINTSDESCRIPTION"));
	                dt.setComplaintsType(rs.getString("COMPLAINTSTYPE"));
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

	public int patientComplaintsDelete(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientcomplaints WHERE PRESCRIPTIONNO = ?", new Object[] { pid });
	}

	public List<PatientComplaintsPojo> patientComplaintsSearchByPatientId(String pid) {
		String sql = "SELECT * FROM patientcomplaints WHERE PATIENTID = ? ";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<PatientComplaintsPojo>>() {
	        public List<PatientComplaintsPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PatientComplaintsPojo> list = new  ArrayList<PatientComplaintsPojo>();
	        	
	        	while(rs.next()) {
	        		PatientComplaintsPojo dt = new PatientComplaintsPojo();
		  	        
	        	    dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dt.setComplaintsId(rs.getInt("COMPLAINTSID"));
	            	String sql1 = "SELECT * FROM COMPLAINTSMASTER WHERE COMPLAINTSID= ?";
					String compname = jdbcTemplate.query(sql1, new Object[] { rs.getInt("COMPLAINTSID") },
							new ResultSetExtractor<String>() {
								public String extractData(ResultSet rs1) throws SQLException {
									if (rs1.next()) {
										return rs1.getString("COMPLAINTSNAME");
									} else {
										System.out.println("complaintsname not obtained");
										return null;
									}

								}
							});
					if(compname != null)
					{
						System.out.println("complaints name obtained: "+ compname);
					dt.setComplaintsName(compname);
					
					}
	                dt.setComplaintsName(rs.getString("COMPLAINTSNAME"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                dt.setComplaintsDuration(rs.getString("COMPLAINTSDURATION"));
	                dt.setComplaintsDescription(rs.getString("COMPLAINTSDESCRIPTION"));
	                dt.setComplaintsType(rs.getString("COMPLAINTSTYPE"));
	                list.add(dt);
	            }
	            return list;
	            }
	    });
}

	public PatientComplaintsPojo patientComplaintsSearchByPrescriptionNo(String pid) {
		String sql = "SELECT * FROM patientcomplaints WHERE PRESCRIPTIONNO = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<PatientComplaintsPojo>() {
	        public PatientComplaintsPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("search by prescription no in complaints");
	        	PatientComplaintsPojo dt = new PatientComplaintsPojo();
	  	        
	        	while(rs.next()) {
	        		
	        	    dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dt.setComplaintsId(rs.getInt("COMPLAINTSID"));
	                String sql1 = "SELECT * FROM COMPLAINTSMASTER WHERE COMPLAINTSID= ?";
					String compname = jdbcTemplate.query(sql1, new Object[] { rs.getInt("COMPLAINTSID") },
							new ResultSetExtractor<String>() {
								public String extractData(ResultSet rs1) throws SQLException {
									if (rs1.next()) {
										return rs1.getString("COMPLAINTSNAME");
									} else {
										System.out.println("complaintsname not obtained");
										return null;
									}

								}
							});
					if(compname != null)
					{
						System.out.println("complaints name obtained: "+ compname);
					dt.setComplaintsName(compname);
					
					}

	                dt.setPatientId(rs.getString("PATIENTID"));
	             
	                dt.setComplaintsDuration(rs.getString("COMPLAINTSDURATION"));
	                dt.setComplaintsDescription(rs.getString("COMPLAINTSDESCRIPTION"));
	                dt.setComplaintsType(rs.getString("COMPLAINTSTYPE"));
	            }
	            return dt;
	            
	            }
	    });
	}
	public List<PatientComplaintsPojo> patientComplaintsSearchByPrescriptionNo1(String pid) {
		String sql = "SELECT * FROM patientcomplaints WHERE PRESCRIPTIONNO = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<PatientComplaintsPojo>>() {
	        public List<PatientComplaintsPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<PatientComplaintsPojo> lis = new ArrayList<PatientComplaintsPojo>();
	        	System.out.println("search by prescription no in complaints");
	        		  	        
	        	while(rs.next()) {
	        		PatientComplaintsPojo dt = new PatientComplaintsPojo();

	        	    dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
	                dt.setComplaintsId(rs.getInt("COMPLAINTSID"));
	                dt.setPatientId(rs.getString("PATIENTID"));
	                String sql1 = "SELECT * FROM COMPLAINTSMASTER WHERE COMPLAINTSID= ?";
					String compname = jdbcTemplate.query(sql1, new Object[] { rs.getInt("COMPLAINTSID") },
							new ResultSetExtractor<String>() {
								public String extractData(ResultSet rs1) throws SQLException {
									if (rs1.next()) {
										return rs1.getString("COMPLAINTSNAME");
									} else {
										System.out.println("complaintsname not obtained");
										return null;
									}

								}
							});
					if(compname != null)
					{
						System.out.println("complaints name obtained: "+ compname);
					dt.setComplaintsName(compname);
					
					}

	                dt.setComplaintsDuration(rs.getString("COMPLAINTSDURATION"));
	                dt.setComplaintsDescription(rs.getString("COMPLAINTSDESCRIPTION"));
	                dt.setComplaintsType(rs.getString("COMPLAINTSTYPE"));
	                lis.add(dt);
	            }
	            return lis;
	            
	            }
	    });
	}

	public boolean patientComplaintsUpdate(PatientComplaintsPojo comppojo) {
		String query="UPDATE patientCOMPLAINTS set  PATIENTID = ? , COMPLAINTSID = ? , COMPLAINTSDURATION = ? , COMPLAINTSDESCRIPTION = ? , COMPLAINTSTYPE = ?  WHERE PRESCRIPTIONNO = ?";
			    
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
			
			ps.setString(1, comppojo.getPatientId());
			ps.setInt(2, comppojo.getComplaintsId());
			ps.setString(3, comppojo.getComplaintsDuration());
			ps.setString(4, comppojo.getComplaintsDescription());
			ps.setString(5, comppojo.getComplaintsType());		   
			ps.setString(6, comppojo.getPrescriptionNo());
			return ps.execute();
		}
		});
		}
	public boolean patientComplaintsUpdate1(List<PatientComplaintsPojo> comppojo) {
		String query="UPDATE patientCOMPLAINTS set  PATIENTID = ? , COMPLAINTSID = ? , COMPLAINTSDURATION = ? , COMPLAINTSDESCRIPTION = ? , COMPLAINTSTYPE = ?  WHERE PRESCRIPTIONNO = ?";
			    
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
			for(int i=0;i<comppojo.size();i++)
			{
			ps.setString(1, comppojo.get(i).getPatientId());
			ps.setInt(2, comppojo.get(i).getComplaintsId());
			ps.setString(3, comppojo.get(i).getComplaintsDuration());
			ps.setString(4, comppojo.get(i).getComplaintsDescription());
			ps.setString(5, comppojo.get(i).getComplaintsType());		   
			ps.setString(6, comppojo.get(i).getPrescriptionNo());
			}
			return ps.execute();
		}
		});
		}

}
