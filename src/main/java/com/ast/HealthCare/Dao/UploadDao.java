package com.ast.HealthCare.Dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.ast.main.JpaConfiguration;

@Repository
public class UploadDao {
	@Autowired()
	JdbcTemplate jdbcTemplate;

	@Value("${const.AWSAccessKey}")
	String AWSAccessKey;
	@Value("${const.AWSSecretekey}")
	String AWSSecreteKey;
	@Value("${const.appAssetDirectory}")
	String appAssetDirectory;
	@Value("${const.AWSS3Mode}")
	boolean AWSS3Mode;

	// protected JdbcTemplate jdbcTemplate;
	JpaConfiguration jpa = new JpaConfiguration();

	UploadDao() {
		/* this.jdbcTemplate = new JdbcTemplate(jpa.dd()); */
		System.out.println("UploadDao constructor jdbc " + this.jdbcTemplate);
	}

	// private final String filepath = "E:/uploads/"; // saved uploads

	public Integer uploadDataFile(MultipartFile file) throws IOException, FileNotFoundException {
		System.out.println("upload file controller");
		// String filepath = "E:/uploads/";
		System.out.println("insertpatientinvestigationdao");
		if (file != null) {
			String sql2 = "SELECT INVESTIGATIONSERIALID FROM patientinvestigation  ORDER BY INVESTIGATIONSERIALID DESC LIMIT 1";
			return jdbcTemplate.query(sql2, new ResultSetExtractor<Integer>() {
				@SuppressWarnings("unused")
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					System.out.println("oii");
					// List<Integer> list = new ArrayList<Integer>();
					int invid;
					if (rs.next()) {
						invid = rs.getInt("INVESTIGATIONSERIALID");
					} else {
						invid = 0;

					}
					System.out.println("invid serial id" + invid);

					System.out.println(System.getProperty("java.io.tmpdir"));
					File convFile1 = new File(System.getProperty("java.io.tmpdir") + file.getOriginalFilename());
					String fileName = (invid + 1) + "-" + convFile1.getName();
					System.out.println("filename" + file.getOriginalFilename());
					String sql1 = "INSERT INTO patientinvestigation (INVESTIGATIONFILE,INVESTIGATIONFILENAME) values(?,?) RETURNING INVESTIGATIONSERIALID";
					int id = jdbcTemplate.execute(sql1, new PreparedStatementCallback<Integer>() {
						public Integer doInPreparedStatement(PreparedStatement ps)
								throws SQLException, DataAccessException {

							ps.setString(1, fileName);
							ps.setString(2, convFile1.getName());
							/*
							 * boolean check = ps.execute(); // returns false when correctly executed
							 * System.out.println("check : " + check); return check;
							 */
							ResultSet rs = ps.executeQuery();
							if (rs.next()) {
								System.out.println("Serial id" + rs.getInt("INVESTIGATIONSERIALID"));
								return rs.getInt("INVESTIGATIONSERIALID");
							}
							return 0;
						}

					});
					System.out.println("btry");

					if (AWSS3Mode) {

						AWSCredentials credentials = new BasicAWSCredentials(AWSAccessKey, AWSSecreteKey);
						AmazonS3 s3client = new AmazonS3Client(credentials);
						String bucketName = "astfiles";
						try {
							System.out.println("try");
							/*
							 * convFile.createNewFile(); FileOutputStream fos = new
							 * FileOutputStream(convFile); System.out.println("bwrite");
							 * fos.write(file.getBytes()); System.out.println("awrirte"); fos.close();
							 */
							file.transferTo(convFile1);
							System.out.println("before putobject");
						} catch (Exception e) {
							System.out.println("catch");
						}
						s3client.putObject(new PutObjectRequest(bucketName, fileName, convFile1));
						System.out.println("after putobject");
						return id;
					} else {
						File convFile = new File(appAssetDirectory+"investigationFiles/" + fileName);

						try {
							convFile.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						FileOutputStream fos = null;
						try {
							fos = new FileOutputStream(convFile);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							fos.write(((MultipartFile) file).getBytes());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							fos.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String dbfilepath = convFile.getAbsolutePath();
						System.out.println("orginal filename: " + ((MultipartFile) file).getOriginalFilename());
						System.out.println("convfile: " + convFile);
						System.out.println("Your File Name is : " + convFile.getName());
						System.out.println("dbfilepath: " + dbfilepath);
						return id;
						/*
						 * String sql1 =
						 * "INSERT INTO patientinvestigation (INVESTIGATIONFILE,INVESTIGATIONFILENAME) values(?,?)"
						 * ; boolean check1 = jdbcTemplate.execute(sql1, new
						 * PreparedStatementCallback<Boolean>() { public Boolean
						 * doInPreparedStatement(PreparedStatement ps) throws SQLException,
						 * DataAccessException {
						 * 
						 * ps.setString(1, dbfilepath); ps.setString(2, convFile.getName()); boolean
						 * check = ps.execute(); // returns false when correctly executed
						 * System.out.println("check : " + check); return check; }
						 * 
						 * });
						 */
					}
				}
			});

		}
		return null;
	}

	@SuppressWarnings("unused")
	public byte[] viewfile(Long invid) throws IOException {

		System.out.println("view file dao investigation serial id: " + invid);
		String sql = "select * from PATIENTINVESTIGATION where INVESTIGATIONSERIALID= ?";
		String filename = jdbcTemplate.query(sql, new Object[] { invid }, new ResultSetExtractor<String>() {

			public String extractData(ResultSet rs) throws SQLException {
				List<String> list = new ArrayList<String>();
				String path;
				if (rs.next()) {
					path = rs.getString("INVESTIGATIONFILE");
					return path;
				}
				return null;

			}
		});
		byte[] data;
		if (filename != null) {
			if (AWSS3Mode) {
				AWSCredentials credentials = new BasicAWSCredentials(AWSAccessKey, AWSSecreteKey);
				AmazonS3 s3client = new AmazonS3Client(credentials);
				String bucketName = "astfiles";
				S3Object object = s3client.getObject(new GetObjectRequest(bucketName, filename));
				InputStream objectData = object.getObjectContent();
				data = IOUtils.toByteArray(objectData);
			} else {
				// Process the objectData stream.
				File file = new File(appAssetDirectory+"InvestigationFiles/"+filename);
				System.out.println(file.getName());
				System.out.println(file.getAbsoluteFile());
				System.out.println("Working");
				Path path = Paths.get(appAssetDirectory+"InvestigationFiles/"+filename);
				data = Files.readAllBytes(path);
				System.out.println(path);
				System.out.println(data);
			}
			return data;
		} else {
			return null;
		}
	}

	public List<byte[]> viewFileByPatientId(String pid) throws IOException {

		/*
		 * System.out.println("view file dao"); String sql =
		 * "select * from PATIENTINVESTIGATION where INVESTIGATIONSERIALID= ?"; return
		 * jdbcTemplate.query(sql, new Object[] { pid }, new
		 * ResultSetExtractor<List<byte[]>>() {
		 * 
		 * public List<byte[]> extractData(ResultSet rs) throws SQLException {
		 * List<byte[]> list = new ArrayList<byte[]>(); String path; while (rs.next()) {
		 * byte[] data = null; path = rs.getString("INVESTIGATIONFILE"); if(path !=
		 * null) { File file = new File(filepath); System.out.println(file.getName());
		 * System.out.println(file.getAbsoluteFile()); System.out.println("Working");
		 * Path path1 = Paths.get(filepath);
		 * 
		 * try { data = Files.readAllBytes(path1); System.out.println(path1); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
		 * else { data = null;
		 * 
		 * }
		 * 
		 * System.out.println(data); list.add(data); } return list; } });
		 */
		return null;
	}
}
