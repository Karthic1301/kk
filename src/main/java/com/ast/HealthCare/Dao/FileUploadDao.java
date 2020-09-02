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

import com.ast.HealthCare.Pojo.FileUploadPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class FileUploadDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	FileUploadDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("FileUploadDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addFileUpload(FileUploadPojo upload) {
		// TODO Auto-generated method stub
		String sql1="INSERT INTO fileupload (FILENAME, FILEDATA) values(?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException { 
				
				ps.setString(1, upload.getFileName());
				ps.setString(2, upload.getFileData());
				return ps.execute();
			}
		});
	}

	public List<FileUploadPojo> fileUploadAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM fileupload";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<FileUploadPojo>>() {
	        public List<FileUploadPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<FileUploadPojo> list = new  ArrayList<FileUploadPojo>();
	            while(rs.next()) {
	            	FileUploadPojo dt = new FileUploadPojo();
	          
	                dt.setFileId(rs.getInt("FILEID"));
	                dt.setFileName(rs.getString("FILENAME"));
	                dt.setFileData(rs.getString("FILEDATA"));
	                
	                list.add(dt);
	            }
	            return list;
	            
	            }
	    });
	}

	public FileUploadPojo fileUploadSearchById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM fileupload where FILEID = "+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<FileUploadPojo>() {
	        public FileUploadPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	//List<FileUploadPojo> list = new  ArrayList<FileUploadPojo>();
	        	FileUploadPojo dt = new FileUploadPojo();
		          
	        	if(rs.next()) {
	            
	                dt.setFileId(rs.getInt("FILEID"));
	                dt.setFileName(rs.getString("FILENAME"));
	                dt.setFileData(rs.getString("FILEDATA"));
	                
	            }
	            return dt;
	            
	            }
	    });
	}

	
}
