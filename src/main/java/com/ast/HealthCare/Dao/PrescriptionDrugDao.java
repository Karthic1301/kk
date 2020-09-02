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

import com.ast.HealthCare.Pojo.PrescriptionDrugPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PrescriptionDrugDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;

	JpaConfiguration jpa = new JpaConfiguration();

	PrescriptionDrugDao() {
		/*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
		System.out.println("PrescriptionDrugDao constructor jdbc " + this.jdbcTemplate);
	}

	// CREATE TABLE prescriptiondrug(PRESCRIPTIONID INTEGER,DRUGID INTEGER,DOSAGE
	// INTEGER,DRUGTYPE TEXT,
	// DURATION INTEGER,QUANTITY INTEGER,MORNING TEXT,AFTERNOON TEXT,EVENING
	// TEXT,NIGHT TEXT,TT_TYPE TEXT,
	// INTAKE TEXT,NEXTREVIEWDATE DATE);

	public Boolean addPrescriptionDrug(List<PrescriptionDrugPojo> prescriptionDrug,String prescriptionno) {
		prescriptionDrugDelete(prescriptionno);
		// String sql = "INSERT INTO prescriptiondrug (PRESCRIPTIONNO,DRUGID,DOSAGE,DRUGTYPE,DURATION,QUANTITY,MORNING,AFTERNOON,EVENING,NIGHT,TT_TYPE,INTAKE,NEXTREVIEWDATE,PATIENTID,DRUGNAME) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String sql = "INSERT INTO prescriptiondrug (PRESCRIPTIONNO,DRUGID,DOSAGE,DURATION,QUANTITY,MORNING,AFTERNOON,EVENING,NIGHT,TT_TYPE,INTAKE,NEXTREVIEWDATE,PATIENTID,DRUGNAME,GENERICID,GENERICNAME) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				for(int i=0;i<prescriptionDrug.size();i++)
				{
				ps.setString(1, prescriptionDrug.get(i).getPrescriptionNo());
				ps.setInt(2, prescriptionDrug.get(i).getDrugId());
				ps.setDouble(3, prescriptionDrug.get(i).getDosage());
				// ps.setString(4, prescriptionDrug.get(i).getDrugType());
				ps.setInt(4, prescriptionDrug.get(i).getDuration());
				ps.setInt(5, prescriptionDrug.get(i).getQuantity());
				ps.setString(6, prescriptionDrug.get(i).getMorning());
				ps.setString(7, prescriptionDrug.get(i).getAfternoon());
				ps.setString(8, prescriptionDrug.get(i).getEvening());
				ps.setString(9, prescriptionDrug.get(i).getNight());
				ps.setString(10, prescriptionDrug.get(i).getTtType());
				ps.setString(11, prescriptionDrug.get(i).getIntake());
				ps.setDate(12, prescriptionDrug.get(i).getNextReviewDate());
				ps.setString(13, prescriptionDrug.get(i).getPatientId());
				ps.setString(14, prescriptionDrug.get(i).getDrugName());
				
				ps.setInt(15, prescriptionDrug.get(i).getGenericId());
				ps.setString(16,prescriptionDrug.get(i).getGenericName());
				ps.execute();
				}
				return true;
			}
		});
	}

	public List<PrescriptionDrugPojo> prescriptionDrugAll() {
		String sql = "SELECT * FROM prescriptiondrug";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PrescriptionDrugPojo>>() {
			public List<PrescriptionDrugPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<PrescriptionDrugPojo> list = new ArrayList<PrescriptionDrugPojo>();
				while (rs.next()) {

					PrescriptionDrugPojo pd = new PrescriptionDrugPojo();
					pd.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pd.setDrugId(rs.getInt("DRUGID"));
					pd.setPatientId(rs.getString("PATIENTID"));
					pd.setDosage(rs.getDouble("DOSAGE"));
					String sql1 = "SELECT * FROM drugmaster WHERE drugid = " + pd.getDrugId();
			        
					String drugtype = jdbcTemplate.query(sql1, new ResultSetExtractor<String>(){
						public String extractData(ResultSet rs) throws SQLException, DataAccessException {
							if(rs.next())
							{
								String sql1 = "SELECT * FROM drugtypemaster WHERE drugtypeid = " + rs.getInt("drugtypeid");
						        return jdbcTemplate.query(sql1, new ResultSetExtractor<String>(){
									public String extractData(ResultSet rs) throws SQLException, DataAccessException {
										
										if(rs.next())
										{
											return rs.getString("drugtypename");
										}
										return "";
									}
								});
								
							}
							return "";
						}
					});
					pd.setDrugType(drugtype);
					pd.setDuration(rs.getInt("DURATION"));
					pd.setQuantity(rs.getInt("QUANTITY"));
					pd.setDrugName(rs.getString("DRUGNAME"));
					pd.setMorning(rs.getString("MORNING"));
					pd.setAfternoon(rs.getString("AFTERNOON"));
					pd.setEvening(rs.getString("EVENING"));
					pd.setNight(rs.getString("NIGHT"));
					pd.setTtType(rs.getString("TT_TYPE"));
					pd.setIntake(rs.getString("INTAKE"));
					pd.setNextReviewDate(rs.getDate("NEXTREVIEWDATE"));
					pd.setGenericId(rs.getInt("GENERICID"));
					pd.setGenericName(rs.getString("GENERICNAME"));
					list.add(pd);
				}
				return list;
			}
		});
	}

	public PrescriptionDrugPojo prescriptionDrugSearchByPrescriptionId(String pid) {
		String sql = "SELECT * FROM prescriptiondrug WHERE PRESCRIPTIONNO = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<PrescriptionDrugPojo>() {
	        public PrescriptionDrugPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("search by doctor id");
	        	//List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	PrescriptionDrugPojo pd = new PrescriptionDrugPojo();

	        	while(rs.next()) {
		        	
	        		pd.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pd.setDrugId(rs.getInt("DRUGID"));
					pd.setDosage(rs.getDouble("DOSAGE"));
					String sql1 = "SELECT * FROM drugmaster WHERE drugid = " + pd.getDrugId();
					String drugtype = jdbcTemplate.query(sql1, new ResultSetExtractor<String>(){
						public String extractData(ResultSet rs) throws SQLException, DataAccessException {
							if(rs.next())
							{
								String sql1 = "SELECT * FROM drugtypemaster WHERE drugtypeid = " + rs.getInt("drugtypeid");
						        return jdbcTemplate.query(sql1, new ResultSetExtractor<String>(){
									public String extractData(ResultSet rs) throws SQLException, DataAccessException {
										
										if(rs.next())
										{
											return rs.getString("drugtypename");
										}
										return "";
									}
								});
								
							}
							return "";
						}
					});
					
					pd.setDrugType(drugtype);
					pd.setDuration(rs.getInt("DURATION"));
					pd.setQuantity(rs.getInt("QUANTITY"));
					pd.setDrugName(rs.getString("DRUGNAME"));
					pd.setMorning(rs.getString("MORNING"));
					pd.setAfternoon(rs.getString("AFTERNOON"));
					pd.setEvening(rs.getString("EVENING"));
					pd.setNight(rs.getString("NIGHT"));
					pd.setTtType(rs.getString("TT_TYPE"));
					pd.setIntake(rs.getString("INTAKE"));
					pd.setNextReviewDate(rs.getDate("NEXTREVIEWDATE"));
					pd.setGenericId(rs.getInt("GENERICID"));
					pd.setGenericName(rs.getString("GENERICNAME"));
				}
	            return pd;
	            
	            }
	    });
	}

	public int prescriptionDrugDelete(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM prescriptiondrug WHERE PRESCRIPTIONNO = ?", new Object[] { pid });
	}

	public List<PrescriptionDrugPojo> prescriptionDrugSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM prescriptiondrug WHERE PATIENTID = ? ";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<PrescriptionDrugPojo>>() {
	        public List<PrescriptionDrugPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	List<PrescriptionDrugPojo> list = new  ArrayList<PrescriptionDrugPojo>();
	        	
	        	while(rs.next()) {
	        		PrescriptionDrugPojo pd = new PrescriptionDrugPojo();
		  	        
	        		pd.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pd.setDrugId(rs.getInt("DRUGID"));
					pd.setDosage(rs.getDouble("DOSAGE"));
					String sql1 = "SELECT * FROM drugmaster WHERE drugid = " + pd.getDrugId();
					String drugtype = jdbcTemplate.query(sql1, new ResultSetExtractor<String>(){
						public String extractData(ResultSet rs) throws SQLException, DataAccessException {
							if(rs.next())
							{
								String sql1 = "SELECT * FROM drugtypemaster WHERE drugtypeid = " + rs.getInt("drugtypeid");
						        return jdbcTemplate.query(sql1, new ResultSetExtractor<String>(){
									public String extractData(ResultSet rs) throws SQLException, DataAccessException {
										
										if(rs.next())
										{
											return rs.getString("drugtypename");
										}
										return "";
									}
								});
								
							}
							return "";
						}
					});
					
					pd.setDrugType(drugtype);
					pd.setDuration(rs.getInt("DURATION"));
					pd.setQuantity(rs.getInt("QUANTITY"));
					pd.setDrugName(rs.getString("DRUGNAME"));
					pd.setMorning(rs.getString("MORNING"));
					pd.setAfternoon(rs.getString("AFTERNOON"));
					pd.setEvening(rs.getString("EVENING"));
					pd.setNight(rs.getString("NIGHT"));
					pd.setTtType(rs.getString("TT_TYPE"));
					pd.setIntake(rs.getString("INTAKE"));
					pd.setNextReviewDate(rs.getDate("NEXTREVIEWDATE"));
					pd.setGenericId(rs.getInt("GENERICID"));
					pd.setGenericName(rs.getString("GENERICNAME"));
					list.add(pd);
	            }
	            return list;
	            
	            }
	    });
	}
	public List<PrescriptionDrugPojo> prescriptionDrugSearchByPrescriptionNo1(String pid) {
		String sql = "SELECT * FROM prescriptiondrug WHERE PRESCRIPTIONNO = ?";
		return jdbcTemplate.query(sql,  new Object[] { pid }, new ResultSetExtractor<List<PrescriptionDrugPojo>>() {
	        public List<PrescriptionDrugPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("search by doctor id");
	        	//List<PatientPojo> list = new  ArrayList<PatientPojo>();
	        	List<PrescriptionDrugPojo> list = new  ArrayList<PrescriptionDrugPojo>();
	        	
	        	while(rs.next()) {
	        		PrescriptionDrugPojo pd = new PrescriptionDrugPojo();
		  	        
	        		pd.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pd.setDrugId(rs.getInt("DRUGID"));
					pd.setDosage(rs.getDouble("DOSAGE"));String sql1 = "SELECT * FROM drugmaster WHERE drugid = " + pd.getDrugId();
					String drugtype = jdbcTemplate.query(sql1, new ResultSetExtractor<String>(){
						public String extractData(ResultSet rs) throws SQLException, DataAccessException {
							if(rs.next())
							{
								String sql1 = "SELECT * FROM drugtypemaster WHERE drugtypeid = " + rs.getInt("drugtypeid");
						        return jdbcTemplate.query(sql1, new ResultSetExtractor<String>(){
									public String extractData(ResultSet rs) throws SQLException, DataAccessException {
										
										if(rs.next())
										{
											return rs.getString("drugtypename");
										}
										return "";
									}
								});
								
							}
							return "";
						}
					});
					
					pd.setDrugType(drugtype);
					pd.setDuration(rs.getInt("DURATION"));
					pd.setQuantity(rs.getInt("QUANTITY"));
					pd.setDrugName(rs.getString("DRUGNAME"));
					pd.setMorning(rs.getString("MORNING"));
					pd.setAfternoon(rs.getString("AFTERNOON"));
					pd.setEvening(rs.getString("EVENING"));
					pd.setNight(rs.getString("NIGHT"));
					pd.setTtType(rs.getString("TT_TYPE"));
					pd.setIntake(rs.getString("INTAKE"));
					pd.setNextReviewDate(rs.getDate("NEXTREVIEWDATE"));
					pd.setGenericId(rs.getInt("GENERICID"));
					pd.setGenericName(rs.getString("GENERICNAME"));
					list.add(pd);
	            }
	            return list;
	            }
	    });
	}

}
