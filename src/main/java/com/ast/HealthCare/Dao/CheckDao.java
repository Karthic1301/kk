package com.ast.HealthCare.Dao;

import java.sql.Date;
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

import com.ast.HealthCare.Pojo.CheckPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class CheckDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;
	
	JpaConfiguration jpa = new JpaConfiguration();
	CheckDao(){
		 /*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("CheckDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public boolean addCheck(CheckPojo summa) {
		// TODO Auto-generated method stub
		String sql1="INSERT INTO notesmaster (NOTESNAME, NOTESCODE) values(?,?)";
		
		return jdbcTemplate.execute(sql1,
			new PreparedStatementCallback<Boolean>()
		{  
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
			{
				
				ps.setString(1, summa.getName());
				ps.setString(2, summa.getName());
				
			    return ps.execute();
			    
			}
		});
	}

	public List<CheckPojo> viewCheck() {
		// TODO Auto-generated method stub
String sql1="SELECT * FROM notesmaster";
		
	return jdbcTemplate.query(sql1, new ResultSetExtractor<List<CheckPojo>>() {
    public List<CheckPojo> extractData(ResultSet rs) throws SQLException,
            DataAccessException {
    	List<CheckPojo> li = new ArrayList<CheckPojo>();
    	while(rs.next()) {
        	CheckPojo dt = new CheckPojo();
        	Date kk = new Date(2018-01-01);
            dt.setId(5);
            dt.setDate2(kk);
            dt.setName(rs.getString("notesname"));
            li.add(dt);
        }
    	return li;
        }
});
	}

	public int checkDelete(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM notesmaster WHERE NOTESNAME = ?", new Object[] { pid });
	}

	public boolean checkUpdate(CheckPojo dt) {
		// TODO Auto-generated method stub
System.out.println("vic "+dt);
		
		String query="UPDATE notesmaster set NOTESNAME = ?, NOTESCODE = ? where NOTESID = ?";

		//String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID = ?";
		
		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
			ps.setString(1, dt.getName());
			ps.setString(2, dt.getName());
			ps.setInt(3, dt.getId());
		    return ps.execute();
		}
		});

	}

	
}
