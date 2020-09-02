package com.ast.HealthCare.Dao;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.SmsChildPojo;
import com.ast.HealthCare.Pojo.SmsMasterPojo;
import com.ast.HealthCare.Pojo.SmsSettingPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class SMSSettingDao {
	
	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;

	JpaConfiguration jpa = new JpaConfiguration();

	SMSSettingDao() {
		/*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
		System.out.println("SMSSettingDao constructor jdbc " + this.jdbcTemplate);
	}

	public List<SmsMasterPojo> Smssetting() {
		// TODO Auto-generated method stub
		System.out.println("smssetting hi");
		String sql = "SELECT * FROM smsmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<SmsMasterPojo>>() {
			public List<SmsMasterPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<SmsMasterPojo> list = new ArrayList<SmsMasterPojo>();
				while (rs.next()) {
					rs.getDate("CREATEDATE");
					rs.getString("SMSCONTENT");
					rs.getString("REPEATMODE");
					rs.getDate("SENDDATE");
					rs.getDate("NEXTSMSDATE");
					rs.getString("SENDTIME");
					rs.getString("REPEATCATEGORY");
					rs.getInt("SMSSTATUS");
					rs.getInt("PATIENTSELECTIONSTATUS");
					rs.getInt("DOCTORSELECTIONSTATUS");
					rs.getInt("STAFFSELECTIONSTATUS");
					Date nextsmsdate = rs.getDate("NEXTSMSDATE");
					// Message content
					String content = null;
					try {
						content = java.net.URLEncoder.encode(rs.getString("SMSCONTENT"), "UTF-8").replace("+", "%20");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(content);
					// To get current date and time
					Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
					cal.get(Calendar.DATE);
					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
					String formatted = dateformat.format(cal.getTime());
					java.util.Date cd = null;
					try {
						cd = dateformat.parse(formatted);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					java.sql.Date currtentDate = new java.sql.Date(cd.getTime());
					SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
					String formatted1 = timeformat.format(cal.getTime());
					java.util.Date ct = null;
					try {
						ct = timeformat.parse(formatted1);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					java.sql.Time currtentTime = new java.sql.Time(ct.getTime());
					System.out.println(currtentDate);
					System.out.println(currtentTime);
					// adding 30 minutes to current time
					Calendar now = Calendar.getInstance(); // Gets the current date and time
					now.add(Calendar.MINUTE, 30);
					String formatted2 = timeformat.format(now.getTime());
					java.util.Date ctp30 = null; // current time + 30 minutes
					try {
						ctp30 = timeformat.parse(formatted2);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					java.sql.Time currtentTimePlusMin = new java.sql.Time(ctp30.getTime());
					System.out.println(currtentTimePlusMin);
					// convert sendtime string object to time object
					java.sql.Time timeValue = null;
					try {
						timeValue = new java.sql.Time(timeformat.parse(rs.getString("SENDTIME")).getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(timeValue);
					String repeatmode = rs.getString("REPEATMODE");
					System.out.println(repeatmode);
					if (repeatmode.equals("Repeat")) {
						System.out.println("Repeat mode kullla");
						System.out.println(nextsmsdate);
						System.out.println(currtentDate.after(nextsmsdate) || currtentDate.equals(nextsmsdate));
						if (currtentDate.after(nextsmsdate) || currtentDate.equals(nextsmsdate)) {
							System.out.println("date if kulla ");
							if ((timeValue.after(currtentTime) && timeValue.before(currtentTimePlusMin))
									|| timeValue.equals(currtentTime)) {
								System.out.println("time if kulla ");
								// 1st checking the next sms sendate
								if (currtentDate.after(nextsmsdate)) {
									System.out.println("// 1st checking the next sms sendate if kulla ");
									for (int i = 0; currtentDate.after(nextsmsdate); i++) {
										System.out.println("for kulla ");
										String rm = rs.getString("REPEATCATEGORY");
										System.out.println(rm);
										if (rm.equals("DAILY")) {
											Calendar calendar = Calendar.getInstance();
											calendar.setTime(nextsmsdate);
											calendar.add(Calendar.DATE, 1);// i.e increment by day
											nextsmsdate = calendar.getTime();
											System.out.println("changed daily date is " + nextsmsdate);
										} else if (rm.equals("WEEKLY")) {
											Calendar calendar = Calendar.getInstance();
											calendar.setTime(nextsmsdate);
											calendar.add(Calendar.WEEK_OF_YEAR, 1);// i.e increment by week
											nextsmsdate = calendar.getTime();
										} else if (rm.equals("MONTHLY")) {
											Calendar calendar = Calendar.getInstance();
											calendar.setTime(nextsmsdate);
											calendar.add(Calendar.MONTH, 1); // i.e increment by month
											nextsmsdate = calendar.getTime();
										} else if (rm.equals("YEARLY")) {
											Calendar calendar = Calendar.getInstance();
											calendar.setTime(nextsmsdate);
											calendar.add(Calendar.YEAR, 1);// i.e increment by year
											nextsmsdate = calendar.getTime();
										}
									}
								}
								// 2nd sending sms
								if (rs.getInt("SMSSTATUS") == 0) {
									if (currtentDate.equals(nextsmsdate)) {
										System.out.println("date if() checked");
										// System.out.println(rs.getString("SENDTIME"));
										// System.out.println(fd2);
										if ((timeValue.after(currtentTime) && timeValue.before(currtentTimePlusMin))
												|| timeValue.equals(currtentTime)) {
											System.out.println("time if() checked");
											if (rs.getInt("PATIENTSELECTIONSTATUS") == 1) {
												System.out.println("if patient all");
												SmsToPatientAll(content);
											}
											if (rs.getInt("STAFFSELECTIONSTATUS") == 1) {
												System.out.println("if staff all");
												SmsToStaffAll(content);
											}
											if (rs.getInt("DOCTORSELECTIONSTATUS") == 1) {
												System.out.println("if doctor all");
												SmsToDoctorAll(content);
											}
											String sql2 = "SELECT * FROM smschild";
											jdbcTemplate.query(sql2, new ResultSetExtractor<List<SmsChildPojo>>() {
												public List<SmsChildPojo> extractData(ResultSet rs2)
														throws SQLException, DataAccessException {
													List<SmsChildPojo> list = new ArrayList<SmsChildPojo>();
													while (rs2.next()) {
														rs2.getString("MOBILENO");
														rs2.getString("USERID");
														rs2.getInt("SMSMASTERID");
														System.out.println(rs2.getInt("SMSMASTERID"));
														System.out.println(rs.getInt("SMSID"));
														if (rs2.getInt("SMSMASTERID") == rs.getInt("SMSID")) {
															System.out.println("inside if");
															String pid2 = rs2.getString("MOBILENO");
															String sql3 = "SELECT * FROM smssetting";
															jdbcTemplate.query(sql3,
																	new ResultSetExtractor<SmsSettingPojo>() {
																		public SmsSettingPojo extractData(ResultSet rs3)
																				throws SQLException,
																				DataAccessException {
																			while (rs3.next()) {
																				String content = null;
																				try {
																					content = java.net.URLEncoder
																							.encode(rs.getString(
																									"SMSCONTENT"),
																									"UTF-8")
																							.replace("+", "%20");
																				} catch (UnsupportedEncodingException e) {
																					// TODO Auto-generated catch block
																					e.printStackTrace();
																				}
																				String smsUrl = rs3.getString("SMSURL");
																				String smsApiKey = rs3
																						.getString("APIKEY");
																				String smsSender = rs3
																						.getString("SMSSENDER");
																				try {
																					System.out.println(
																							rs.getString("SMSCONTENT"));
																					String requestUrl = "" + smsUrl
																							+ "?workingkey=" + smsApiKey
																							+ "&to=" + pid2 + "&sender="
																							+ smsSender + "&message="
																							+ content;
																					URL url = null;
																					url = new URL(requestUrl);
																					HttpURLConnection uc = (HttpURLConnection) url
																							.openConnection();
																					System.out.println(
																							uc.getResponseMessage());
																					uc.disconnect();
																				} catch (Exception ex) {
																					System.out.println(ex.getMessage());
																				}
																			}
																			return null;
																		}
																	});
														}
													}
													return list;
												}
											});
										}
									}
								}
								// 3rd setting next sms sendate
								if (currtentDate.equals(nextsmsdate)) {
									String rm = rs.getString("REPEATCATEGORY");
									System.out.println(rm);
									if (rm.equals("DAILY")) {
										Calendar calendar = Calendar.getInstance();
										calendar.setTime(nextsmsdate);
										calendar.add(Calendar.DATE, 1);// i.e increment by day
										nextsmsdate = calendar.getTime();
										jdbcTemplate.update("UPDATE smsmaster set NEXTSMSDATE='" + nextsmsdate
												+ "' WHERE SMSID = '" + rs.getInt("SMSID") + "'");
									} else if (rm.equals("WEEKLY")) {
										Calendar calendar = Calendar.getInstance();
										calendar.setTime(nextsmsdate);
										calendar.add(Calendar.WEEK_OF_YEAR, 1);// i.e increment by week
										nextsmsdate = calendar.getTime();
										jdbcTemplate.update("UPDATE smsmaster set NEXTSMSDATE='" + nextsmsdate
												+ "' WHERE SMSID = '" + rs.getInt("SMSID") + "'");
									} else if (rm.equals("MONTHLY")) {
										Calendar calendar = Calendar.getInstance();
										calendar.setTime(nextsmsdate);
										calendar.add(Calendar.MONTH, 1); // i.e increment by month
										nextsmsdate = calendar.getTime();
										jdbcTemplate.update("UPDATE smsmaster set NEXTSMSDATE='" + nextsmsdate
												+ "' WHERE SMSID = '" + rs.getInt("SMSID") + "'");
									} else if (rm.equals("YEARLY")) {
										Calendar calendar = Calendar.getInstance();
										calendar.setTime(nextsmsdate);
										calendar.add(Calendar.YEAR, 1);// i.e increment by year
										nextsmsdate = calendar.getTime();
										jdbcTemplate.update("UPDATE smsmaster set NEXTSMSDATE='" + nextsmsdate
												+ "' WHERE SMSID = '" + rs.getInt("SMSID") + "'");
									}
								}
							}
						}
					} else if (repeatmode.equals("Single")) {
						System.out.println("single mode is checked");
						if (rs.getInt("SMSSTATUS") == 0) {
							System.out.println("single status is checked");
							if (currtentDate.equals(nextsmsdate)) {
								System.out.println("date if() checked");
								// System.out.println(rs.getString("SENDTIME"));
								// System.out.println(fd2);
								if ((timeValue.after(currtentTime) && timeValue.before(currtentTimePlusMin))
										|| timeValue.equals(currtentTime)) {
									System.out.println("time if() checked");
									if (rs.getInt("PATIENTSELECTIONSTATUS") == 1) {
										System.out.println("if patient all");
										SmsToPatientAll(content);
									}
									if (rs.getInt("STAFFSELECTIONSTATUS") == 1) {
										System.out.println("if staff all");
										SmsToStaffAll(content);
									}
									if (rs.getInt("DOCTORSELECTIONSTATUS") == 1) {
										System.out.println("if doctor all");
										SmsToDoctorAll(content);
									}
									String sql2 = "SELECT * FROM smschild";
									jdbcTemplate.query(sql2, new ResultSetExtractor<List<SmsChildPojo>>() {
										public List<SmsChildPojo> extractData(ResultSet rs2)
												throws SQLException, DataAccessException {
											List<SmsChildPojo> list = new ArrayList<SmsChildPojo>();
											while (rs2.next()) {
												rs2.getString("MOBILENO");
												rs2.getString("USERID");
												rs2.getInt("SMSMASTERID");
												System.out.println(rs2.getInt("SMSMASTERID"));
												System.out.println(rs.getInt("SMSID"));
												if (rs2.getInt("SMSMASTERID") == rs.getInt("SMSID")) {
													System.out.println("inside if");
													String pid2 = rs2.getString("MOBILENO");
													String sql3 = "SELECT * FROM smssetting";
													jdbcTemplate.query(sql3, new ResultSetExtractor<SmsSettingPojo>() {
														public SmsSettingPojo extractData(ResultSet rs3)
																throws SQLException, DataAccessException {
															while (rs3.next()) {
																String content = null;
																try {
																	content = java.net.URLEncoder
																			.encode(rs.getString("SMSCONTENT"), "UTF-8")
																			.replace("+", "%20");
																} catch (UnsupportedEncodingException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
																String smsUrl = rs3.getString("SMSURL");
																String smsApiKey = rs3.getString("APIKEY");
																String smsSender = rs3.getString("SMSSENDER");
																try {
																	System.out.println(rs.getString("SMSCONTENT"));
																	String requestUrl = "" + smsUrl + "?workingkey="
																			+ smsApiKey + "&to=" + pid2 + "&sender="
																			+ smsSender + "&message=" + content;
																	URL url = null;
																	url = new URL(requestUrl);
																	HttpURLConnection uc = (HttpURLConnection) url
																			.openConnection();
																	System.out.println(uc.getResponseMessage());
																	uc.disconnect();
																} catch (Exception ex) {
																	System.out.println(ex.getMessage());
																}
															}
															return null;
														}
													});
												}
											}
											return list;
										}
									});
								}
							}
						}
					}
				}
				return list;
			}
		});
	}

	public void SmsToPatientAll(String content) {
		System.out.println("SmsToPatientAll");
		String sql = "SELECT  PATIENTMOBILE1  from patientmaster";
		jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					System.out.println("inside while");
					String pid = rs.getString("PATIENTMOBILE1");
					String sql3 = "SELECT * FROM smssetting";
					jdbcTemplate.query(sql3, new ResultSetExtractor<SmsSettingPojo>() {
						public SmsSettingPojo extractData(ResultSet rs3) throws SQLException, DataAccessException {
							while (rs3.next()) {
								String smsUrl = rs3.getString("SMSURL");
								String smsApiKey = rs3.getString("APIKEY");
								String smsSender = rs3.getString("SMSSENDER");
								try {
									// System.out.println(rs.getString("SMSCONTENT"));
									String requestUrl = "" + smsUrl + "?workingkey=" + smsApiKey + "&to=" + pid
											+ "&sender=" + smsSender + "&message=" + content;
									URL url = null;
									url = new URL(requestUrl);
									HttpURLConnection uc = (HttpURLConnection) url.openConnection();
									System.out.println(uc.getResponseMessage());
									uc.disconnect();
								} catch (Exception ex) {
									System.out.println(ex.getMessage());
								}
							}
							return null;
						}
					});
				}
				return null;
			}
		});
	}

	public void SmsToStaffAll(String content) {
		System.out.println("SmsToStaffAll");
		String sql = "SELECT  STAFFMOBILE1  from staffmaster";
		jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					System.out.println("inside while");
					String pid = rs.getString("STAFFMOBILE1");
					String sql3 = "SELECT * FROM smssetting";
					jdbcTemplate.query(sql3, new ResultSetExtractor<SmsSettingPojo>() {
						public SmsSettingPojo extractData(ResultSet rs3) throws SQLException, DataAccessException {
							while (rs3.next()) {
								String smsUrl = rs3.getString("SMSURL");
								String smsApiKey = rs3.getString("APIKEY");
								String smsSender = rs3.getString("SMSSENDER");
								try {
									// System.out.println(rs.getString("SMSCONTENT"));
									String requestUrl = "" + smsUrl + "?workingkey=" + smsApiKey + "&to=" + pid
											+ "&sender=" + smsSender + "&message=" + content;
									URL url = null;
									url = new URL(requestUrl);
									HttpURLConnection uc = (HttpURLConnection) url.openConnection();
									System.out.println(uc.getResponseMessage());
									uc.disconnect();
								} catch (Exception ex) {
									System.out.println(ex.getMessage());
								}
							}
							return null;
						}
					});
				}
				return null;
			}
		});
	}

	public void SmsToDoctorAll(String content) {
		System.out.println("SmsToDoctorAll");
		String sql = "SELECT  DOCTORMOBILE1  from doctormaster";
		jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					System.out.println("inside while");
					String pid = rs.getString("DOCTORMOBILE1");
					String sql3 = "SELECT * FROM smssetting";
					jdbcTemplate.query(sql3, new ResultSetExtractor<SmsSettingPojo>() {
						public SmsSettingPojo extractData(ResultSet rs3) throws SQLException, DataAccessException {
							while (rs3.next()) {
								String smsUrl = rs3.getString("SMSURL");
								String smsApiKey = rs3.getString("APIKEY");
								String smsSender = rs3.getString("SMSSENDER");
								try {
									// System.out.println(rs.getString("SMSCONTENT"));
									String requestUrl = "" + smsUrl + "?workingkey=" + smsApiKey + "&to=" + pid
											+ "&sender=" + smsSender + "&message=" + content;
									URL url = null;
									url = new URL(requestUrl);
									HttpURLConnection uc = (HttpURLConnection) url.openConnection();
									System.out.println(uc.getResponseMessage());
									uc.disconnect();
								} catch (Exception ex) {
									System.out.println(ex.getMessage());
								}
							}
							return null;
						}
					});
				}
				return null;
			}
		});
	}

	PatientPojo getPatient(String pid) {
		String sql = "SELECT PATIENTID,PATIENTFIRSTNAME,PATIENTDATEOFBIRTH,PATIENTMOBILE1 FROM patientmaster WHERE PATIENTID = ?";
		return jdbcTemplate.query(sql, new Object[] { pid }, new ResultSetExtractor<PatientPojo>() {
			public PatientPojo extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				// List<PatientPojo> list = new ArrayList<PatientPojo>();
				PatientPojo patient = new PatientPojo();
				while (rs.next()) {
					System.out.println("oii1");
					patient.setPatientId(rs.getString("PATIENTID"));
					patient.setPatientFirstName(rs.getString("PATIENTFIRSTNAME"));
					patient.setPatientDOB(rs.getDate("PATIENTDATEOFBIRTH"));
					patient.setPatientMobile1(rs.getString("PATIENTMOBILE1"));
				}
				return patient;
			}
		});
	}

	public void dailysms() {
		// To get current date and time
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
		cal.get(Calendar.DATE);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = dateformat.format(cal.getTime());
		java.util.Date cd = null;
		try {
			cd = dateformat.parse(formatted);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date currtentDate = new java.sql.Date(cd.getTime());
		SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");
		String formatted1 = timeformat.format(cal.getTime());
		java.util.Date ct = null;
		try {
			ct = timeformat.parse(formatted1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Time currtentTime = new java.sql.Time(ct.getTime());
		System.out.println(currtentDate);
		System.out.println(currtentTime);
		String sqq = "SELECT * FROM dailysmssetting where status=0";
		jdbcTemplate.query(sqq, new ResultSetExtractor<Boolean>() {
			public Boolean extractData(ResultSet rs1) throws SQLException, DataAccessException {
				while (rs1.next()) {
					int id = rs1.getInt("DAILYSMSID");
					rs1.getString("SMSTIME");
					rs1.getString("DAILYSMSNAME");
					// convert SMStime string object to time object
					java.sql.Time timeValue = null;
					try {
						timeValue = new java.sql.Time(timeformat.parse(rs1.getString("SMSTIME")).getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (currtentTime.equals(timeValue)) {
						System.out.println("time equal if kulla");
						if (id == 1) {
							nextvisitdate();
						}
						if (id == 2) {
							birthdaysms();
						}
					}
				}
				return true;
			}
		});
	}

	void sendSms(String mno, String content) {
		String sql3 = "SELECT * FROM smssetting";
		jdbcTemplate.query(sql3, new ResultSetExtractor<SmsSettingPojo>() {
			public SmsSettingPojo extractData(ResultSet rs3) throws SQLException, DataAccessException {
				while (rs3.next()) {
					String smsUrl = rs3.getString("SMSURL");
					String smsApiKey = rs3.getString("APIKEY");
					String smsSender = rs3.getString("SMSSENDER");
					try {
						// System.out.println(rs.getString("SMSCONTENT"));
						String requestUrl = "" + smsUrl + "?workingkey=" + smsApiKey + "&to=" + mno + "&sender="
								+ smsSender + "&message=" + content;
						URL url = null;
						url = new URL(requestUrl);
						HttpURLConnection uc = (HttpURLConnection) url.openConnection();
						System.out.println(uc.getResponseMessage());
						uc.disconnect();
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
				return null;
			}
		});
	}

	public void nextvisitdate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");
		Date now1 = new Date();
		String nowString = df.format(now1);
		System.out.println("Now SystemTime is: " + nowString);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
		java.sql.Date todayDate = java.sql.Date.valueOf(now.toLocalDate());
		todayDate.setDate(todayDate.getDate() + 1);
		String currentTime = dtf.format(now);
		System.out.println("Now SystemTime is: " + todayDate);
		String sqq = "SELECT * FROM prescriptionmaster WHERE NEXTREVIEWDATE = '" + todayDate + "'";
		Boolean dkd = jdbcTemplate.query(sqq, new ResultSetExtractor<Boolean>() {
			public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					String pid = rs.getString("PATIENTID");
					Date nextreviewdate = rs.getDate("NEXTREVIEWDATE");
					System.out.println("id = "+pid);
					PatientPojo ppojo = new PatientPojo();
					ppojo = getPatient(pid);
					// ppojo = pdao.patientSearchById(pid);
					String mob1 = ppojo.getPatientMobile1();
					System.out.println("mobile = "+mob1);
					if (mob1.length() == 10) {
						System.out.println("mobile number checking if kulla ");
						// msg.MessageNextvisitAlert(mob1,nextreviewdate );
						String content = "Dear%20" + ppojo.getPatientFirstName()
								+ ",%20Your%20next%20Consulting%20date%20is%20tommorow(" + nextreviewdate + ")";
						/*
						 * nextreviewdate.getDate()+"-"+nextreviewdate.getMonth()+"-"+nextreviewdate.
						 * getYear()
						 */
						sendSms(mob1, content);
						System.out.println("message sent to mobile 1");
					} else {
						System.out.println("no mobile 1");
					}
				}
				return true;
			}
		});
		// sendSms("9944228028","happy_birthday");
		System.out.println("after dao " + dkd);
		// premasdao.nextVisitAlert();
	}

	public void birthdaysms() {
		// To get current date and time
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
		cal.get(Calendar.DATE);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = dateformat.format(cal.getTime());
		java.util.Date cd = null;
		try {
			cd = dateformat.parse(formatted);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date currtentDate = new java.sql.Date(cd.getTime());
		System.out.println(currtentDate);
		String sql = "SELECT PATIENTID,PATIENTFIRSTNAME,PATIENTDATEOFBIRTH,PATIENTMOBILE1 FROM patientmaster where  DATEPART('month', PATIENTDATEOFBIRTH) = DATEPART('month', CURRENT_DATE) AND DATEPART('day', PATIENTDATEOFBIRTH) = DATEPART('day', CURRENT_DATE)";
		jdbcTemplate.query(sql, new ResultSetExtractor<PatientPojo>() {
			public PatientPojo extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("oii");
				while (rs.next()) {
					System.out.println("oii1");
					String pname = rs.getString("PATIENTFIRSTNAME");
					System.out.println(pname);
					rs.getDate("PATIENTDATEOFBIRTH");
					String mob = rs.getString("PATIENTMOBILE1");
					String content = null;
					try {
						content = java.net.URLEncoder.encode(
								"Dear " + pname
										+ ", Happy birthday! I hope all your birthday wishes and dreams come true.",
								"UTF-8").replace("+", "%20");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(mob + content);
					sendSms(mob, content);
				}
				return null;

			}
		});
	}

	// service was written in messageService and controller was written in
	// messageController
	public int updatePrescriptionNextVisitTime(String time) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("UPDATE dailysmssetting set SMSTIME='" + time + "' WHERE DAILYSMSID = 1");
	}
}
