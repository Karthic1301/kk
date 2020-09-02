package com.ast.HealthCare.Dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import com.ast.HealthCare.Pojo.PatientInvestigationPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PatientInvestigationDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;

	JpaConfiguration jpa = new JpaConfiguration();

	PatientInvestigationDao() {
		/*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
		System.out.println("PatientInvestigationDao constructor jdbc " + this.jdbcTemplate);
	}

	public boolean addPatientInvestigation(List<PatientInvestigationPojo> imp,String prescriptionno)
			throws IOException, FileNotFoundException {
		boolean checkpre = false;
		if(prescriptionno != "") {
		patientInvestigationDeleteByPrescription(prescriptionno);
		checkpre = true;
		}
		System.out.println("add patient Investigation ");
		System.out.println("no of records: "+ imp.size());
		// TODO Auto-generated method stub
		for (int i1 = 0; i1 < imp.size(); i1++) {
		if(imp.get(i1).getInvestigationSerialId() > 0)
		{ 
			System.out.println("investigationserialid"+ imp.get(i1).getInvestigationSerialId());
			int i= i1;
			String sql1;
			if(checkpre) {
//			sql1 = "INSERT INTO PATIENTINVESTIGATION ( PATIENTID , RESULTS, INVESTIGATIONIMAGE, INVESTIGATIONDATE, PRESCRIPTIONNO, INVESTIGATIONID,INVESTIGATIONSERIALID)VALUES(?,?,?,?,?,?,?)";
			sql1 = "UPDATE PATIENTINVESTIGATION SET PATIENTID = ?, RESULTS= ?, INVESTIGATIONIMAGE= ?, INVESTIGATIONDATE=?, PRESCRIPTIONNO=?, INVESTIGATIONID =? WHERE INVESTIGATIONSERIALID=?";
			} else {
				sql1 = "UPDATE PATIENTINVESTIGATION SET PATIENTID = ?, RESULTS= ?, INVESTIGATIONIMAGE= ?, INVESTIGATIONDATE=?, PRESCRIPTIONNO=?, INVESTIGATIONID =? WHERE INVESTIGATIONSERIALID=?"; 
			}
				jdbcTemplate.execute(sql1, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				
					
					ps.setString(1, imp.get(i).getPatientId());
					ps.setString(2, imp.get(i).getResults());
					ps.setString(3, imp.get(i).getInvestigationImage());
					ps.setDate(4, imp.get(i).getInvestigationDate());
					ps.setString(5, imp.get(i).getPrescriptionNo());
					ps.setInt(6, imp.get(i).getInvestigationId());
					ps.setInt(7, imp.get(i).getInvestigationSerialId());
					ps.executeUpdate();
				return true;
			}
		});
		}
		else
		{
			System.out.println("no file in investigation so else part");
			int i= i1;
			String sql1 = "INSERT INTO PATIENTINVESTIGATION ( PATIENTID , RESULTS, INVESTIGATIONIMAGE, INVESTIGATIONDATE, PRESCRIPTIONNO, INVESTIGATIONID )VALUES(?,?,?,?,?,?)";
			 jdbcTemplate.execute(sql1, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						ps.setString(1, imp.get(i).getPatientId());
						ps.setString(2, imp.get(i).getResults());
						ps.setString(3, imp.get(i).getInvestigationImage());
						ps.setDate(4, imp.get(i).getInvestigationDate());
						ps.setString(5, imp.get(i).getPrescriptionNo());
						ps.setInt(6, imp.get(i).getInvestigationId());
						
						ps.executeUpdate();
					return true;
				}
			});

		}	}
		return false;
	}
	public List<PatientInvestigationPojo> patientInvestigationAll() {
		// TODO Auto-generated method stub

		System.out.println("patientinvestigationdaoall");

		String sql = "SELECT * FROM patientinvestigation";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PatientInvestigationPojo>>() {
			public List<PatientInvestigationPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<PatientInvestigationPojo> list = new ArrayList<PatientInvestigationPojo>();
				while (rs.next()) {
					PatientInvestigationPojo dt = new PatientInvestigationPojo();

					dt.setPatientId(rs.getString("PATIENTID"));
					dt.setInvestigationId(rs.getInt("INVESTIGATIONID"));
					String sql1 = "select * from INVESTIGATIONMASTER";
					String invname = jdbcTemplate.query(sql1, new ResultSetExtractor<String>() {
						public String extractData(ResultSet rs) throws SQLException {
							return rs.getString("INVESTIGATIONNAME");
						}
					});
					if (invname != null) {
						dt.setInvestigationName(invname);
					}
					dt.setResults(rs.getString("RESULTS"));
					dt.setInvestigationImage(rs.getString("INVESTIGATIONIMAGE"));
					dt.setInvestigationDate(rs.getDate("INVESTIGATIONDATE"));
					dt.setInvestigationFileName(rs.getString("INVESTIGATIONFILENAME"));
					dt.setInvestigationSerialId(rs.getInt("INVESTIGATIONSERIALID"));
					// File file= new File(rs.getString("INVESTIGATIONFILE"));
					// System.out.println(file.getName());
					// System.out.println(file.getAbsoluteFile());
					System.out.println("Working");
					Path path = Paths.get(rs.getString("INVESTIGATIONFILE"));

					try {
						dt.setInvestigationFileByte(Files.readAllBytes(path));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(path);

					list.add(dt);
				}
				return list;
			}
		});
	}

	public int patientInvestigationDelete(int pid2, int inv) throws IOException {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientinvestigation WHERE INVESTIGATIONSERIALID = ? and PATIENTID = ?",
				new Object[] { inv, pid2 });
	}
	
	public int patientInvestigationDeleteByPrescription(String pid2) throws IOException {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM patientinvestigation WHERE  PRESCRIPTIONNO = ? And InvestigationFileName IS NULL",
				new Object[] {  pid2 });
	}

	public List<PatientInvestigationPojo> patientInvestigationSearchByPatientId(String pid2) {
		// TODO Auto-generated method stu//b
		System.out.println("patientinvestigationdaobypatientid");
		String sql = "SELECT * FROM patientinvestigation WHERE PATIENTID = ? ";
		return jdbcTemplate.query(sql, new Object[] { pid2 }, new ResultSetExtractor<List<PatientInvestigationPojo>>() {
			public List<PatientInvestigationPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<PatientInvestigationPojo> list = new ArrayList<PatientInvestigationPojo>();
			
				while (rs.next()) {
					PatientInvestigationPojo dt = new PatientInvestigationPojo();

					dt.setPatientId(rs.getString("PATIENTID"));
					dt.setInvestigationId(rs.getInt("INVESTIGATIONID"));
					dt.setResults(rs.getString("RESULTS"));
					dt.setInvestigationImage(rs.getString("INVESTIGATIONIMAGE"));
					dt.setInvestigationDate(rs.getDate("INVESTIGATIONDATE"));
					dt.setInvestigationFileName(rs.getString("INVESTIGATIONFILENAME"));
					dt.setInvestigationSerialId(rs.getInt("INVESTIGATIONSERIALID"));
					String sql2 = "SELECT * FROM INVESTIGATIONMASTER WHERE INVESTIGATIONID =?";
					String invname = jdbcTemplate.query(sql2, new Object[] { rs.getInt("INVESTIGATIONID") },
							new ResultSetExtractor<String>() {
								public String extractData(ResultSet rs1) throws SQLException {
									if (rs1.next()) {
										System.out.println(
												"investigation name found" + rs1.getString("INVESTIGATIONNAME"));
										return rs1.getString("INVESTIGATIONNAME");
									} else {
										System.out.println("investigation name NOT FOUND");
										return null;
									}
								}
							});
					if (invname != null) {
						dt.setInvestigationName(invname);
					}
					System.out.println("Working");
					list.add(dt);
				}
				return list;

			}
		});
	}

	public List<PatientInvestigationPojo> ReportpatientInvestigationSearchByPatientId(String pid2) {
		// TODO Auto-generated method stu//b
		System.out.println("patientinvestigationdaobypatientid");
		String sql = "SELECT * FROM patientinvestigation WHERE PATIENTID = ? ";
		return jdbcTemplate.query(sql, new Object[] { pid2 }, new ResultSetExtractor<List<PatientInvestigationPojo>>() {
			public List<PatientInvestigationPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<PatientInvestigationPojo> list = new ArrayList<PatientInvestigationPojo>();
				
				while (rs.next()) {
					PatientInvestigationPojo dt = new PatientInvestigationPojo();

					dt.setPatientId(rs.getString("PATIENTID"));
					dt.setInvestigationId(rs.getInt("INVESTIGATIONID"));
					dt.setResults(rs.getString("RESULTS"));
					dt.setInvestigationImage(rs.getString("INVESTIGATIONIMAGE"));
					dt.setInvestigationDate(rs.getDate("INVESTIGATIONDATE"));
					dt.setInvestigationFileName(rs.getString("INVESTIGATIONFILE"));
					dt.setInvestigationSerialId(rs.getInt("INVESTIGATIONSERIALID"));
					String sql2 = "SELECT * FROM INVESTIGATIONMASTER WHERE INVESTIGATIONID =?";
					String invname = jdbcTemplate.query(sql2, new Object[] { rs.getInt("INVESTIGATIONID") },
							new ResultSetExtractor<String>() {
								public String extractData(ResultSet rs1) throws SQLException {
									if (rs1.next()) {
										System.out.println(
												"investigation name found" + rs1.getString("INVESTIGATIONNAME"));
										
										return rs1.getString("INVESTIGATIONNAME");
									} else {
										System.out.println("investigation name NOT FOUND");
										return null;
									}
								}
							});
					if (invname != null) {
						dt.setInvestigationName(invname);
					}
					
					// File file= new File(rs.getString("INVESTIGATIONFILE"));
					// System.out.println(file.getName());
					// System.out.println(file.getAbsoluteFile());
					System.out.println("Working");
					/*
					 * Path path = Paths.get(rs.getString("INVESTIGATIONFILE"));
					 * 
					 * try { dt.setInvestigationFileByte(Files.readAllBytes(path)); } catch
					 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
					 * System.out.println(path);
					 */ list.add(dt);
				}
				return list;

			}
		});
	}

	public List<PatientInvestigationPojo> patientInvestigationSearchByPrescriptionNo1(String pid) {
		// TODO Auto-generated method stub
		System.out.println("patientinvestigationdaosearchbyprescriptionno1");
		String sql = "SELECT * FROM patientinvestigation WHERE PRESCRIPTIONNO = ? ";
		return jdbcTemplate.query(sql, new Object[] { pid }, new ResultSetExtractor<List<PatientInvestigationPojo>>() {
			public List<PatientInvestigationPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<PatientInvestigationPojo> list2 = new ArrayList<PatientInvestigationPojo>();



				while (rs.next()) {
					PatientInvestigationPojo dt = new PatientInvestigationPojo();
					
					dt.setPatientId(rs.getString("PATIENTID"));
					dt.setInvestigationId(rs.getInt("INVESTIGATIONID"));
					dt.setResults(rs.getString("RESULTS"));
					dt.setInvestigationImage(rs.getString("INVESTIGATIONIMAGE"));
					dt.setInvestigationDate(rs.getDate("INVESTIGATIONDATE"));
					
					dt.setInvestigationFileName(rs.getString("INVESTIGATIONFILENAME"));
					dt.setInvestigationSerialId(rs.getInt("INVESTIGATIONSERIALID"));
					String sql2 = "SELECT * FROM INVESTIGATIONMASTER WHERE INVESTIGATIONID =?";
					String invname = jdbcTemplate.query(sql2, new Object[] { rs.getInt("INVESTIGATIONID") },
							new ResultSetExtractor<String>() {
								public String extractData(ResultSet rs1) throws SQLException {
									if (rs1.next()) {
										System.out.println(
												"investigation name found" + rs1.getString("INVESTIGATIONNAME"));
										
										return rs1.getString("INVESTIGATIONNAME");
									} else {
										System.out.println("investigation name NOT FOUND");
										return null;
									}
								}
							});
					if (invname != null) {
						dt.setInvestigationName(invname);
					}
					
					/*
					 * File file= new File(rs.getString("INVESTIGATIONFILE"));
					 * System.out.println("Working"); Path path =
					 * Paths.get(rs.getString("INVESTIGATIONFILE"));
					 * 
					 * try { dt.setInvestigationFileByte(Files.readAllBytes(path)); } catch
					 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
					 * System.out.println(path);
					 */
					list2.add(dt);
				}
				return list2;
			}
		});
	}

	public List<PatientInvestigationPojo> patientInvestigationReportByPatientId(String pid) {
		// TODO Auto-generated method stub

		System.out.println("patientinvestigationdaoall");

		String sql = "SELECT * FROM patientinvestigation WHERE patientid =?";
		return jdbcTemplate.query(sql, new Object[] {pid},new ResultSetExtractor<List<PatientInvestigationPojo>>() {
			public List<PatientInvestigationPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<PatientInvestigationPojo> list = new ArrayList<PatientInvestigationPojo>();
				while (rs.next()) {
					PatientInvestigationPojo dt = new PatientInvestigationPojo();
					if(rs.getString("INVESTIGATIONFILENAME")!= null){
					dt.setPatientId(rs.getString("PATIENTID"));
					dt.setInvestigationId(rs.getInt("INVESTIGATIONID"));
					String sql1 = "select * from INVESTIGATIONMASTER";
					String invname = jdbcTemplate.query(sql1, new ResultSetExtractor<String>() {
						public String extractData(ResultSet rs) throws SQLException {
							if(rs.next()) {
								return rs.getString("INVESTIGATIONNAME");
							}			
							else
								{
								return null;}
								}
					
					});
					if (invname != null) {
						dt.setInvestigationName(invname);
					}
					dt.setResults(rs.getString("RESULTS"));
					dt.setInvestigationImage(rs.getString("INVESTIGATIONIMAGE"));
					dt.setInvestigationDate(rs.getDate("INVESTIGATIONDATE"));
					dt.setInvestigationFileName(rs.getString("INVESTIGATIONFILENAME"));
					dt.setInvestigationSerialId(rs.getInt("INVESTIGATIONSERIALID"));
					// File file= new File(rs.getString("INVESTIGATIONFILE"));
					// System.out.println(file.getName());
					// System.out.println(file.getAbsoluteFile());
					System.out.println("Working");
					Path path = Paths.get(rs.getString("INVESTIGATIONFILE"));

					/*try {
						// dt.setInvestigationFileByte(Files.readAllBytes(path));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					System.out.println(path);

					list.add(dt);}
					
				}
				return list;
			}
		});
	}

}
