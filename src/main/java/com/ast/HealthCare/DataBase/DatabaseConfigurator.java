/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ast.HealthCare.DataBase;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author User
 */

public final class DatabaseConfigurator {

	/*
	 * @Value("${const.databaseName}") String dbName;
	 */

	protected JdbcTemplate jdbcTemplate;
	protected String dataBaseName;
	private static final Logger LOG = Logger.getLogger(DatabaseConfigurator.class.getName());

	public void setDataSource(DataSource dataSource, String db) {
		System.out.println("set datasource method");
		this.dataBaseName = db;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		System.out.println("getter for jdbc template");
		return jdbcTemplate;
	}

	public void checkDatabase() throws SQLException {
		System.out.println("check database method");
		checkUpdates();
	}

	public boolean doesTableExist(String pTableName) {
		System.out.println("doestable exists method");
		String lValue;
		/*
		 * String sql = "SELECT * " + "FROM information_schema.tables" +
		 * " WHERE table_schema = 'palanimurugandb'" +
		 * "    AND table_name = 'tableMeta'" + ";";
		 */
		/* System.out.println(dbName); */
		String sql = "SELECT * FROM information_schema.tables WHERE table_schema = '" + this.dataBaseName
				+ "' AND table_name = 'TABLEMETA'";
		lValue = this.getJdbcTemplate().query(sql, new ResultSetExtractor<String>() {
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					System.out.println("Table Meta exist");
					return "tablemeta";
				}
				System.out.println("Table Meta doosnot exist");
				return null;

			}
		});
		if (lValue == null) {
			return false;
		}
		boolean lExist = lValue.equalsIgnoreCase(pTableName);
		return lExist;
	}

	public void clearDatabase() throws SQLException {
		System.out.println("clear database method");
		Collection<String> listTables = listTables();
		for (String mListTable : listTables) {
			if (!mListTable.equalsIgnoreCase("TABLEMETA")) {
				try {
					this.getJdbcTemplate().execute("DELETE FROM " + mListTable);
				} catch (Exception e) {
				}
			}
		}
	}

	private List<String> readQueries(String pFileName) {
		System.out.println("read queries method");
		List<String> lQueries = new ArrayList<String>();
		try {
			InputStream stream = this.getClass().getClassLoader().getResourceAsStream(pFileName);
			if (stream != null) {
				lQueries = IOUtils.readLines(stream);
			}
		} catch (IOException ex) {
		}
		return lQueries;
	}

	private Collection<String> listTables() throws SQLException {
		System.out.println("listtables method");
		return this.getJdbcTemplate().queryForList(
				"SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA LIKE 'public' ORDER BY TABLE_NAME",
				String.class);
	}

	private void checkUpdates() throws SQLException {
		if (doesTableExist("TABLEMETA")) {
			Integer lCount = this.getJdbcTemplate().queryForObject("SELECT COUNT(*) as count FROM TABLEMETA",
					Integer.class);
			LOG.info("Table Queries Count : " + lCount);
			List<String> lQueries = readQueries(com.ast.HealthCare.utils.Constants.DB_SCRIPTPATH);
			LOG.info("File Queries Count : " + lQueries.size());
			if (lQueries.size() > lCount) {
				for (int mI = lCount; mI < lQueries.size(); mI++) {
					LOG.info("Executing Query No : " + mI);
					this.getJdbcTemplate().execute(lQueries.get(mI));
					this.getJdbcTemplate().update("INSERT INTO TABLEMETA (QUERY, EXECUTED)VALUES(?,?)",
							lQueries.get(mI), true);
					System.out.println("table created");
				}
			}
		} else {
			System.out.println("creating meta table");
			LOG.info("Creating Meta Table");
			this.getJdbcTemplate()
					.execute("CREATE TABLE TABLEMETA (ID SERIAL PRIMARY KEY, QUERY TEXT, EXECUTED BOOLEAN)");
			checkUpdates();
		}
	}
}
