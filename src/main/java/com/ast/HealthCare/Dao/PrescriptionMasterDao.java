package com.ast.HealthCare.Dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.PrescriptionMasterPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class PrescriptionMasterDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	@Autowired
	VitalInfoDao vitalDao;
	
	@Autowired
	AppointmentDao appDao;
	
	@Autowired
	PatientDao pdao;
	
	@Autowired
	MessageDao msg;
	

	// CREATE TABLE prescriptionmaster(PRESCRIPTIONID SERIAL,PRESCRIPTIONNO
	// TEXT,PATIENTID TEXT,DOCTORID TEXT,
	// CONSULTINGDATE DATE,AGE INTEGER,PRIMARY KEY(PRESCRIPTIONID));

	
	//protected JdbcTemplate jdbcTemplate;

	String hoscode = "PRE";

	JpaConfiguration jpa = new JpaConfiguration();

	PrescriptionMasterDao() {
		/*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
		System.out.println("PrescriptionMasterDao constructor jdbc " + this.jdbcTemplate);
	}

	public PrescriptionMasterPojo addPrescriptionMaster(final PrescriptionMasterPojo prescription) {
		System.out.println("dao " + prescription);
		String pre;
		long num1;
		String sql = "SELECT PRESCRIPTIONID FROM prescriptionmaster order by PRESCRIPTIONID DESC";
		int check1 = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {
					public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
						if (rs.next()) {
							return rs.getInt("PRESCRIPTIONID");
						} else {
							return 0;
						}
					}
				});
		if (check1 != 0) {
			// token = ;
			num1 = check1 + 1;
		} else {
			num1 = 1;
		}
		Calendar now = Calendar.getInstance(); // Gets the current date and time
		final int year = now.get(Calendar.YEAR);

		String num2;
		if (num1 < 10) {
			num2 = "0000000" + num1;
		} else if (num1 < 100) {
			num2 = "000000" + num1;
		} else if (num1 < 1000) {
			num2 = "00000" + num1;
		} else if (num1 < 10000) {
			num2 = "00000" + num1;
		} else if (num1 < 100000) {
			num2 = "0000" + num1;
		} else if (num1 < 1000000) {
			num2 = "000" + num1;
		} else if (num1 < 10000000) {
			num2 = "00" + num1;
		} else if (num1 < 100000000) {
			num2 = "0" + num1;
		} else
			num2 = "" + num1;

		System.out.println(num2);
		final String pid = hoscode + year + num2;

		System.out.println("before da "+pid);
		String sql1 = "INSERT INTO prescriptionmaster(PRESCRIPTIONNO,PATIENTID,DOCTORID,CONSULTINGDATE,AGE,NEXTREVIEWDATE,BILLID)values(?,?,?,?,?,?,?)";
		Boolean abc =  jdbcTemplate.execute(sql1, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, pid);
				ps.setString(2, prescription.getPatientId());
				ps.setString(3, prescription.getDoctorId());
				ps.setDate(4, prescription.getConsultingDate());
				ps.setInt(5, prescription.getPatientAge());
				ps.setDate(6, prescription.getNextReviewDate());
				ps.setInt(7, 0);
				return ps.execute();
			}
		});
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		    LocalDateTime now1 = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
		    java.sql.Date todayDate = java.sql.Date.valueOf(now1.toLocalDate());
		  //  System.out.println("massage date date "+(todayDate.getDate() + 1)+""+prescription.getNextReviewDate().getDate());
		    if((prescription.getNextReviewDate()!=null)&&((todayDate.getDate() + 1)==prescription.getNextReviewDate().getDate())&&
		    		((todayDate.getMonth())==prescription.getNextReviewDate().getMonth())&&
		    		((todayDate.getYear())==prescription.getNextReviewDate().getYear())) {
		    	/*&&
	    		((todayDate.getHours())>8)*/
		    	System.out.println("massage sent "+prescription.getPatientId());
		    	nextVisitAlert(prescription.getPatientId(),prescription.getNextReviewDate() );
		    		} else {
		    			System.out.println("massage not sent "+prescription.getPatientId());
		    		}
	System.out.println("after da "+pid);
	PrescriptionMasterPojo ppojo = prescriptionMasterSearchByPrescriptionNo(pid);
		if(abc)
			return ppojo;
		else
		{
			//int ekk = vitalDao.vitalInfoDelete1(prescription.getPatientId());
			System.out.println("check");
			//System.out.println(vitalDao.vitalInfoDelete1(prescription.getPatientId()));
			appDao.clearAppointmentByDoctorPatientAndDate(prescription.getDoctorId(),prescription.getPatientId(),prescription.getConsultingDate());
			return ppojo;
		}
	}
	
	
	public Boolean prescriptionMasterBillIdUpdate(int billid,String no) {
		String query="UPDATE prescriptionmaster set BILLID=?  where PRESCRIPTIONNO = ?";
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		@SuppressWarnings("deprecation")
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setInt(1,billid);
			ps.setString(2, no);
			return ps.execute();
		}
		});
	}

	public List<PrescriptionMasterPojo> prescriptionMasterAll() {
		String sql = "SELECT * FROM prescriptionmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PrescriptionMasterPojo>>() {
			public List<PrescriptionMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<PrescriptionMasterPojo> list = new ArrayList<PrescriptionMasterPojo>();
				while (rs.next()) {
					//hello
					PrescriptionMasterPojo pm = new PrescriptionMasterPojo();
					pm.setPrescriptionId(rs.getInt("PRESCRIPTIONID"));
					pm.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pm.setPatientId(rs.getString("PATIENTID"));
					pm.setDoctorId(rs.getString("DOCTORID"));
					pm.setNextReviewDate(rs.getDate("NEXTREVIEWDATE"));
					pm.setConsultingDate(rs.getDate("CONSULTINGDATE"));
					pm.setPatientAge(rs.getInt("AGE"));
					pm.setBillId(rs.getInt("BILLID"));
					list.add(pm);
				}
				return list;
			}
		});
	}

	
	public List<PrescriptionMasterPojo> prescriptionMasterByBillId(int billid) {
		String sql = "SELECT * FROM prescriptionmaster WHERE BILLID ="+billid;
		System.out.println(sql);
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<PrescriptionMasterPojo>>() {
			public List<PrescriptionMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<PrescriptionMasterPojo> list = new ArrayList<PrescriptionMasterPojo>();
				while (rs.next()) {
					//hello
					PrescriptionMasterPojo pm = new PrescriptionMasterPojo();
					pm.setPrescriptionId(rs.getInt("PRESCRIPTIONID"));
					pm.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pm.setPatientId(rs.getString("PATIENTID"));
					pm.setDoctorId(rs.getString("DOCTORID"));
					pm.setNextReviewDate(rs.getDate("NEXTREVIEWDATE"));
					pm.setConsultingDate(rs.getDate("CONSULTINGDATE"));
					pm.setPatientAge(rs.getInt("AGE"));
					pm.setBillId(rs.getInt("BILLID"));
					pm.setPatientPojo(pdao.patientSearchById(rs.getString("PATIENTID")));
					list.add(pm);
				}
				return list;
			}
		});
	}


	public List<PrescriptionMasterPojo> prescriptionMasterSearchByPatientId(String pid) {
		
		//GET BY PID IN PRESCRIPTION ORDER BY DATE = PRESCRIPTIONID AND PDATE AS OUTPUT
		
		String sql = "SELECT * FROM prescriptionmaster WHERE PATIENTID = ? ORDER BY CONSULTINGDATE";
		System.out.println(sql);
		return jdbcTemplate.query(sql, new Object[] { pid }, new ResultSetExtractor<List<PrescriptionMasterPojo>>() {
			public List<PrescriptionMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<PrescriptionMasterPojo> list = new ArrayList<PrescriptionMasterPojo>();

				while (rs.next()) {
					System.out.println("oii1");
					PrescriptionMasterPojo pm = new PrescriptionMasterPojo();
					pm.setPrescriptionId(rs.getInt("PRESCRIPTIONID"));
					pm.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pm.setPatientId(rs.getString("PATIENTID"));
					pm.setDoctorId(rs.getString("DOCTORID"));
					pm.setConsultingDate(rs.getDate("CONSULTINGDATE"));
					pm.setNextReviewDate(rs.getDate("NEXTREVIEWDATE"));
					pm.setPatientAge(rs.getInt("AGE"));
					pm.setBillId(rs.getInt("BILLID"));
					list.add(pm);

				}
				return list;

			}
		});
	}

	public List<PrescriptionMasterPojo> prescriptionMasterSearchByDoctorId(String did) {
		String sql = "SELECT * FROM prescriptionmaster WHERE DOCTORID LIKE ?";
		return jdbcTemplate.query(sql, new Object[] { did }, new ResultSetExtractor<List<PrescriptionMasterPojo>>() {
			public List<PrescriptionMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				List<PrescriptionMasterPojo> list = new ArrayList<PrescriptionMasterPojo>();

				while (rs.next()) {
					System.out.println("oii1");
					PrescriptionMasterPojo pm = new PrescriptionMasterPojo();
					pm.setPrescriptionId(rs.getInt("PRESCRIPTIONID"));
					pm.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pm.setPatientId(rs.getString("PATIENTID"));
					pm.setDoctorId(rs.getString("DOCTORID"));
					pm.setConsultingDate(rs.getDate("CONSULTINGDATE"));
					pm.setPatientAge(rs.getInt("AGE"));
					pm.setNextReviewDate(rs.getDate("NEXTREVIEWDATE"));
					pm.setBillId(rs.getInt("BILLID"));
					list.add(pm);
				}
				return list;
			}
		});
	}

	public PrescriptionMasterPojo prescriptionMasterSearchByPrescriptionNo(String pid) {
		String sql = "SELECT * FROM prescriptionmaster WHERE PRESCRIPTIONNO LIKE ?";
		return jdbcTemplate.query(sql, new Object[] { pid }, new ResultSetExtractor<PrescriptionMasterPojo>() {
			public PrescriptionMasterPojo extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				PrescriptionMasterPojo pm = new PrescriptionMasterPojo();
				while (rs.next()) {
					System.out.println("oii1");
					pm.setPrescriptionId(rs.getInt("PRESCRIPTIONID"));
					pm.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pm.setPatientId(rs.getString("PATIENTID"));
					pm.setDoctorId(rs.getString("DOCTORID"));
					pm.setConsultingDate(rs.getDate("CONSULTINGDATE"));
					pm.setPatientAge(rs.getInt("AGE"));
					pm.setNextReviewDate(rs.getDate("NEXTREVIEWDATE"));
					pm.setBillId(rs.getInt("BILLID"));
				}
				return pm;

			}
		});
	}
	
	public PrescriptionMasterPojo prescriptionMasterCheckPrescriptionByBillId(String pid) {
		String sql = "SELECT * FROM prescriptionmaster WHERE PRESCRIPTIONNO = ? AND BILLID=0";
		System.out.println(sql);
		return jdbcTemplate.query(sql, new Object[] { pid }, new ResultSetExtractor<PrescriptionMasterPojo>() {
			public PrescriptionMasterPojo extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				PrescriptionMasterPojo pm = new PrescriptionMasterPojo();
				while (rs.next()) {
					System.out.println("oii1");
					pm.setPrescriptionId(rs.getInt("PRESCRIPTIONID"));
					pm.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pm.setPatientId(rs.getString("PATIENTID"));
					pm.setDoctorId(rs.getString("DOCTORID"));
					pm.setConsultingDate(rs.getDate("CONSULTINGDATE"));
					pm.setPatientAge(rs.getInt("AGE"));
					pm.setNextReviewDate(rs.getDate("NEXTREVIEWDATE"));
					pm.setBillId(rs.getInt("BILLID"));
				}
				return pm;

			}
		});
	}

	public Boolean prescriptionMasterUpdate(final PrescriptionMasterPojo prescription) {
		String query="UPDATE prescriptionmaster set PATIENTID = ?, DOCTORID = ?, CONSULTINGDATE = ?, AGE = ?, NEXTREVIEWDATE = ?  where PRESCRIPTIONNO = ?";
		appDao.clearAppointmentByDoctorPatientAndDate(prescription.getDoctorId(),prescription.getPatientId(),prescription.getConsultingDate());
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		@SuppressWarnings("deprecation")
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, prescription.getPatientId());
			ps.setString(2, prescription.getDoctorId());
			ps.setDate(3, prescription.getConsultingDate());
			ps.setInt(4, prescription.getPatientAge());
			ps.setDate(5, prescription.getNextReviewDate());
			ps.setString(6, prescription.getPrescriptionNo());
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			    LocalDateTime now1 = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
			    java.sql.Date todayDate = java.sql.Date.valueOf(now1.toLocalDate());
			  //  System.out.println("massage date date "+(todayDate.getDate() + 1)+""+prescription.getNextReviewDate());
			    if(((prescription.getNextReviewDate()!=null)&&(todayDate.getDate() + 1)==prescription.getNextReviewDate().getDate())&&
			    		((todayDate.getMonth())==prescription.getNextReviewDate().getMonth())&&
			    		((todayDate.getYear())==prescription.getNextReviewDate().getYear())) {
			    	/*&&
		    		((todayDate.getHours())>8)*/
			    	System.out.println("massage sent "+prescription.getPatientId());
			    	nextVisitAlert(prescription.getPatientId(),prescription.getNextReviewDate() );
			    		} else {
			    			System.out.println("massage not sent "+prescription.getPatientId());
			    		}
			return ps.execute();
		}
		});
	}

	public int prescriptionMasterDelete(String pno) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM prescriptionmaster WHERE PRESCRIPTIONNO = ?", new Object[] { pno });
	}

	public PrescriptionMasterPojo prescriptionMasterSearchByDoctorPatientAndDate(String did, String pid, Date date) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM prescriptionmaster WHERE DOCTORID = ? AND PATIENTID = ? AND CONSULTINGDATE = ?";
		return jdbcTemplate.query(sql, new Object[] { did,pid,date }, new ResultSetExtractor<PrescriptionMasterPojo>() {
			public PrescriptionMasterPojo extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				PrescriptionMasterPojo pm = new PrescriptionMasterPojo();

				if (rs.next()) {
					System.out.println("oii1");
					pm.setPrescriptionId(rs.getInt("PRESCRIPTIONID"));
					pm.setPrescriptionNo(rs.getString("PRESCRIPTIONNO"));
					pm.setPatientId(rs.getString("PATIENTID"));
					pm.setDoctorId(rs.getString("DOCTORID"));
					pm.setConsultingDate(rs.getDate("CONSULTINGDATE"));
					pm.setPatientAge(rs.getInt("AGE"));
					pm.setNextReviewDate(rs.getDate("NEXTREVIEWDATE"));
					pm.setBillId(rs.getInt("BILLID"));
					return pm;

				} else {
					System.out.println("oiiielse");
					return pm;
				}
				

			}
		});
	}
	public void nextVisitAlert(String pid,Date nextreviewdate) {
	     /*  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		    LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
		    java.sql.Date todayDate = java.sql.Date.valueOf(now.toLocalDate());
		    todayDate.setDate(todayDate.getDate() + 1);
		    String currentTime = dtf.format(now);
		    System.out.println("Now SystemTime is: "+ todayDate);
	       String sqq = "SELECT * FROM prescriptionmaster WHERE NEXTREVIEWDATE = '"+todayDate+"'";
	    	
	    	Boolean dkd = jdbcTemplate.query(sqq, new ResultSetExtractor<Boolean>() {
		        public Boolean extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		        	
		        	while(rs.next())
		        	{*/
		        		System.out.println(pid);
		        		PatientPojo ppojo = new PatientPojo();
		        		ppojo = pdao.patientSearchById(pid);
		        		String mob1 = ppojo.getPatientMobile1();
		        		if(mob1.length() == 10)
		        		{
		        			String content = "Dear%20"+ppojo.getPatientFirstName()+",%20Your%20next%20Consulting%20date%20is%20tommorow("+nextreviewdate+")" ;
		        			/*nextreviewdate.getDate()+"-"+nextreviewdate.getMonth()+"-"+nextreviewdate.getYear()*/
		        			msg.MessageNextvisitAlert(mob1,content);
		        			System.out.println("message sent to mobile 1");
		        		}
		        	/*}
		        	return true;
		        }
		       });*/
	}
	
}


