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

import com.ast.HealthCare.Pojo.GenericPojo;
import com.ast.main.JpaConfiguration;

@Repository
public class GenericDao {

	@Autowired()
	 JdbcTemplate jdbcTemplate;
	
	//protected JdbcTemplate jdbcTemplate;

	JpaConfiguration jpa = new JpaConfiguration();

	GenericDao() {
		/*this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
		System.out.println("GenericDao constructor jdbc " + this.jdbcTemplate);
	}

	public boolean addGeneric(final GenericPojo gen) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM genericmaster where GENERICNAME= '" + gen.getGenericName() + "'and GENERICCODE = '"
				+ gen.getGenericCode() + "'";
		Boolean check = jdbcTemplate.query(sql, new ResultSetExtractor<Boolean>() {
			public Boolean extractData(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return true;

				} else {
					return false;
				}
			}
		});
		if (check == false) {
			String sql1 = "INSERT INTO genericmaster (GENERICNAME, GENERICCODE) values(?,?)";

			Boolean check1 = jdbcTemplate.execute(sql1, new PreparedStatementCallback<Boolean>() {
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					ps.setString(1, gen.getGenericName());
					ps.setString(2, gen.getGenericCode());

					return ps.execute();

				}
			});
			if (check1 == false) {
				System.out.println("generic record inserted");
				return true;
			} else {
				System.out.println("generic record not inserted");
				return false;
			}

		} else {
			return false;
		}

	}

	public List<GenericPojo> genericAll() {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM genericmaster";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<GenericPojo>>() {
			public List<GenericPojo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<GenericPojo> list = new ArrayList<GenericPojo>();
				while (rs.next()) {
					GenericPojo dt = new GenericPojo();

					dt.setGenericId(rs.getInt("GENERICID"));
					dt.setGenericCode(rs.getString("GENERICCODE"));
					dt.setGenericName(rs.getString("GENERICNAME"));
					list.add(dt);
				}
				return list;

			}
		});

	}

	public int genericDelete(int pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM genericmaster WHERE GENERICID = ?", new Object[] { pid });
	}

	public boolean genericUpdate(final GenericPojo dt) {
		// TODO Auto-generated method stub
		System.out.println("vic " + dt);

		String query = "UPDATE genericmaster set GENERICNAME = ?,GENERICCODE = ? where GENERICID = ?";

		// String query = "UPDATE patientmaster set PATIENTFIRSTNAME = ? WHERE PATIENTID
		// = ?";

		return jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setString(1, dt.getGenericName());
				ps.setString(2, dt.getGenericCode());
				ps.setInt(3, dt.getGenericId());
				return ps.execute();
			}
		});
	}

}
