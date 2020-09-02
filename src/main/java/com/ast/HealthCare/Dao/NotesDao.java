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

import com.ast.HealthCare.Pojo.NotesPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class NotesDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;

	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	NotesDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("NotesDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addNotes(final NotesPojo notes) {
		// TODO Auto-generated method stub
		String sql1="INSERT INTO notesmaster (NOTESNAME, NOTESCODE) values(?,?)";
		
		return jdbcTemplate.execute(sql1,new PreparedStatementCallback<Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
				ps.setString(1, notes.getNotesName());
				ps.setString(2, notes.getNotesCode());
				
			    return ps.execute();
			    
			}
		});

	}

	public List<NotesPojo> notesAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM notesmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<NotesPojo>>() {
	        public List<NotesPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<NotesPojo> list = new  ArrayList<NotesPojo>();
	            while(rs.next()) {
	            	NotesPojo dt = new NotesPojo();
	          
	                dt.setNotesId(rs.getInt("NOTESID"));
	                dt.setNotesCode(rs.getString("NOTESCODE"));
	                dt.setNotesName(rs.getString("NOTESNAME"));
	                list.add(dt);
	            }
	            return list;
	            }
	    });
	}

	public int notesDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM notesmaster WHERE NOTESID = ?", new Object[] { pid });
	}

	public boolean notesUpdate(final NotesPojo dt) {
		// TODO Auto-generated method stub
System.out.println("vic "+dt);
		
		String query="UPDATE notesmaster set NOTESNAME = ?, NOTESCODE = ? where NOTESID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getNotesName());
			ps.setString(2, dt.getNotesCode());
			ps.setInt(3, dt.getNotesId());
		    return ps.execute();
		}
		});
	}

}
