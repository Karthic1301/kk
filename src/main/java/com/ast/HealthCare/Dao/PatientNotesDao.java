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

import com.ast.HealthCare.Pojo.PatientNotesPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PatientNotesDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;

	//protected JdbcTemplate jdbcTemplate;

	JpaConfiguration jpa = new JpaConfiguration();

	PatientNotesDao() {
		/*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
		System.out.println("PatientNotesDao constructor jdbc " + this.jdbcTemplate);
	}

	public boolean addPatientNotes(List<PatientNotesPojo> patientNotes, String prescriptionno) {
		// TODO Auto-generated method stub

		System.out.println("dkdkdksdjlkf " + patientNotes);
		notesDeleteByPrescription(prescriptionno);

		String sql1 = "INSERT INTO patientnotes (PATIENTID, PRESCRIPTIONNO, NOTESDESCRIPTION,NOTESID) values(?,?,?,?)";

		return jdbcTemplate.execute(sql1, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				for (int i = 0; i < patientNotes.size(); i++) {
					ps.setString(1, patientNotes.get(i).getPatientId());
					ps.setString(2, patientNotes.get(i).getPrescriptionNo());
					ps.setString(3, patientNotes.get(i).getNotesDescripition());
					ps.setInt(4, patientNotes.get(i).getNotesId());
					ps.execute();
					/*
					 * Integer nid = patientNotes.get(i).getNotesId(); String sql2 =
					 * "SELECT * FROM NOTESMASTER WHERE NOTESID = ?"; String notename =
					 * jdbcTemplate.query(sql2, new Object[] { nid }, new
					 * ResultSetExtractor<String>() { public String extractData(ResultSet rs) throws
					 * SQLException { if (rs.next()) { System.out.println("notesname found" +
					 * rs.getString("NOTESNAME")); return rs.getString("NOTESNAME"); } else { return
					 * null; } } }); if (notename != null) { ps.setString(4, notename);
					 * ps.execute(); }
					 * 
					 * }
					 */ 
				}
				return true;
			}
			
		});

	}
	
	public int notesDeleteByPrescription(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientnotes WHERE PRESCRIPTIONNO = ?", new Object[] { pid });
	}

	public List<PatientNotesPojo> patientNotesAll() {
		String sql = "SELECT * FROM patientnotes";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientNotesPojo>>() {
			public List<PatientNotesPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<PatientNotesPojo> list = new ArrayList<PatientNotesPojo>();
				while (rs.next()) {
					// CREATE TABLE patientnotes(PRESCRIPTIONNO STRING,NOTESID
					// INTEGER,NOTESDESCRIPTION TEXT);

					PatientNotesPojo pn = new PatientNotesPojo();
					pn.setNotesId(rs.getInt("NOTESID"));
					pn.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pn.setNotesDescripition(rs.getString("NOTESDESCRIPTION"));
					// pn.setNotesName(rs.getString("NOTESNAME"));

					String sql1 = "select * from notesmaster where NOTESID= ?";
					String notename = jdbcTemplate.query(sql1, new Object[] { rs.getInt("NOTESID") },
							new ResultSetExtractor<String>() {
								public String extractData(ResultSet rs1) throws SQLException {
									if (rs1.next()) {
										return rs1.getString("NOTESNAME");
									} else {
										System.out.println("notes name not found");
										return null;
									}
								}
							});
					if (notename != null) {
						System.out.println("notes name  found" + notename);
						pn.setNotesName(notename);
					}

					list.add(pn);
				}
				return list;
			}
		});
	}

	public PatientNotesPojo patientNotesSearchByPrescriptionId(String pid) {
		String sql = "SELECT * FROM patientnotes WHERE PRESCRIPTIONNO = ?";
		return jdbcTemplate.query(sql, new Object[] { pid }, new ResultSetExtractor<PatientNotesPojo>() {
			public PatientNotesPojo extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("notes search by prescription id");
				// List<PatientPojo> list = new ArrayList<PatientPojo>();
				PatientNotesPojo pn = new PatientNotesPojo();

				while (rs.next()) {
				
					pn.setNotesId(rs.getInt("NOTESID"));
					pn.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pn.setNotesDescripition(rs.getString("NOTESDESCRIPTION"));

					String sql1 = "select * from notesmaster where NOTESID= ?";
					String notename = jdbcTemplate.query(sql1, new Object[] { rs.getInt("NOTESID") },
							new ResultSetExtractor<String>() {
								public String extractData(ResultSet rs1) throws SQLException {
									if (rs1.next()) {
										return rs1.getString("NOTESNAME");
									} else {
										System.out.println("notes name not found");
										return null;
									}
								}
							});
					if (notename != null) {
						System.out.println("notes name  found" + notename);
						pn.setNotesName(notename);
					}

				}
				return pn;

			}
		});

	}

	public List<PatientNotesPojo> patientNotesSearchByPrescriptionNo1(String presno) {
	String sql = " SELECT * FROM PATIENTNOTES WHERE PRESCRIPTIONNO = ?";
	  return jdbcTemplate.query(sql, new Object[] {presno}, new ResultSetExtractor<List<PatientNotesPojo>>() {
		 public List<PatientNotesPojo> extractData(ResultSet rs) throws DataAccessException, SQLException {
			 List<PatientNotesPojo> list = new ArrayList<PatientNotesPojo>();
			 while(rs.next())
			 {
				PatientNotesPojo pn = new PatientNotesPojo();
				pn.setPatientId(rs.getString("PATIENTID"));
				pn.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
				pn.setNotesDescripition(rs.getString("NOTESDESCRIPTION"));
				pn.setNotesId(rs.getInt("NOTESID"));
				Integer nid = pn.getNotesId();
				System.out.println("notes id =" +nid);
				String sql1 = "SELECT * FROM NOTESMASTER WHERE NOTESID =?";
				String notnam = jdbcTemplate.query(sql1, new Object[] {nid},new ResultSetExtractor<String>() {
					public String extractData(ResultSet rs1) throws SQLException {
						if(rs1.next())
						{
							System.out.println("notes name foound" + rs1.getString("NOTESNAME"));
							return rs1.getString("NOTESNAME");
						}
						else
						{
							System.out.println("notes name found");
							return null;
						}
					}
				});
				if(notnam != null)
				{
					pn.setNotesName(notnam);
				}
				 list.add(pn);	
		 }
			 return list;	
		 }
		
	});
	
	
	
	
	
	
	
						
				/*while (rs.next()) {
					PatientNotesPojo pn = new PatientNotesPojo();

					pn.setNotesId(rs.getInt("NOTESID"));
					pn.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pn.setNotesDescripition(rs.getString("NOTESDESCRIPTION"));

					String sql1 = "select * from notesmaster where NOTESID= ?";
					String notename = jdbcTemplate.query(sql1, new Object[] { rs.getInt("NOTESID") },
							new ResultSetExtractor<String>() {
								public String extractData(ResultSet rs1) throws SQLException {
									if (rs1.next()) {
										System.out.println("notes name  found" +rs1.getString("NOTESNAME"));
										return rs1.getString("NOTESNAME");
									} else {
										System.out.println("notes name not found");
										return null;
									}
								}
							});
					if (notename != null) {
						
						pn.setNotesName(notename);
					}
					list.add(pn);
				}
*/				
			}
	
		
	

	public List<PatientNotesPojo> patientNotesSearchByPatientId(String pid) {
	
		
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM patientnotes WHERE PATIENTID = ? ";
		return jdbcTemplate.query(sql, new Object[] { pid }, new ResultSetExtractor<List<PatientNotesPojo>>() {
			public List<PatientNotesPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<PatientNotesPojo> list = new ArrayList<PatientNotesPojo>();
				
				while(rs.next()) {

	            	PatientNotesPojo pd = new PatientNotesPojo();
	            	pd.setPatientId(rs.getString("PATIENTID"));
	            	pd.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pd.setNotesDescripition(rs.getString("NOTESDESCRIPTION"));
					
	        	    String sql1= "select notesname from notesmaster WHERE NOTESID = ?";
	            	String disname = (String) jdbcTemplate.query(sql1, new Object[] {pd.getNotesId()}, new ResultSetExtractor<String>(){
	            	
	            		public  String extractData(ResultSet rs) throws SQLException
	            		{
	            			System.out.println("inside notesmaster query execution of view by prescriptionnumber");
	            			if(rs.next())
	            			{
	            				
	            				return rs.getString("NOTESNAME");
	            				
	            			}
	            			else
	            			{
	            				System.out.println("notes name not found");
							return null;
	            			}
	            		}
	            	});
	            	if(disname != null)
	            	{
	            		System.out.println("notesname found" + disname);
	            		pd.setNotesName(disname);
	            	}
	            	
	            	list.add(pd);
	            	System.out.println("notes added in list");
	            }
	 
				
				/*while (rs.next()) {
					PatientNotesPojo dt = new PatientNotesPojo();

					dt.setNotesId(rs.getInt("NOTESID"));
					dt.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					dt.setNotesDescripition(rs.getString("NOTESDESCRIPTION"));
					String sql1 = "select * from notesmaster where NOTESID= ?";
					String notename = jdbcTemplate.query(sql1, new Object[] { rs.getInt("NOTESID") },
							new ResultSetExtractor<String>() {
								public String extractData(ResultSet rs1) throws SQLException {
									if (rs1.next()) {
										return rs1.getString("NOTESNAME");
									} else {
										System.out.println("notes name not found");
										return null;
									}
								}
							});
					if (notename != null) {
						System.out.println("notes name  found" + notename);
						dt.setNotesName(notename);
					}

					list.add(dt);
				}
*/				return list;
			}
		});
	}

}
