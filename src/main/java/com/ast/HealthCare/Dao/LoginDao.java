package com.ast.HealthCare.Dao;

import java.io.Serializable;
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

import com.ast.HealthCare.Pojo.AdminPojo;
import com.ast.HealthCare.Pojo.LoginPojo;


@Repository
public class LoginDao implements Serializable{
	
	@Autowired()
	JdbcTemplate jdbcTemplate;
	
	private static final long serialVersionUID = 1L;

    
    @Value("${const.passwordEncryptKey}")
	String encryptionkey;

	// CREATE TABLE  usermaster(USERID TEXT PRIMARY KEY,USERNAME TEXT,ROLEID INT,USERPASSWORD TEXT,USERSERIALIID SERIAL,USERCATEGORY TEXT);

	//protected static JdbcTemplate jdbcTemplate;
	LoginDao(){
		/* this.jdbcTemplate = new JdbcTemplate(jpa.dd());*/
	 System.out.println("LoginDao constructor jdbc "+this.jdbcTemplate);
	}
	
	public LoginPojo loginDao(String pUsername, String pPassword) {
		System.out.println(pUsername+pPassword+encryptionkey);
		String sql = "SELECT USERID,USERNAME,ROLEID,AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "'),USERCATEGORY FROM usermaster WHERE USERNAME= '"+pUsername+"'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<com.ast.HealthCare.Pojo.LoginPojo>() {
	        public LoginPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	// List<LoginPojo> list = new  ArrayList<LoginPojo>();
	        	LoginPojo login = new LoginPojo();
                
	            while(rs.next()) {
	            	
	            	System.out.println("oii1");
	            	System.out.println(rs.getString("AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "')"));
	              //  LoginPojo login = new LoginPojo();
	                login.setUserName(rs.getString("USERNAME"));
	                if(pPassword.equals(rs.getString("AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "')"))) {
	                login.setPassword(rs.getString("AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "')"));
	                login.setUserId(rs.getString("USERID"));     
	                login.setRoleId(rs.getInt("ROLEID"));      
	                login.setUserCategory(rs.getString("USERCATEGORY")); 
	                }
	                return login; 
	                //list.add(login);
	               
	            }
	            return login;
	        }
	    });
	}
	
	public Boolean checkUserName(String userName) {
		// TODO Auto-generated method stub
		// String sql = "SELECT * FROM usermaster WHERE USERNAME= '"+userName+"'";
		String sql = "SELECT USERID,USERNAME,ROLEID,AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "'),USERCATEGORY FROM usermaster WHERE USERNAME= '"+userName+"'";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Boolean>() {
		public Boolean extractData(ResultSet rs) throws SQLException,
        DataAccessException {
			
			if (rs.next()) {
				return false;
			}else {
				return true;
			}
		}
	    });
	}

	public Boolean addUserAdmin(AdminPojo login) {
		// TODO Auto-generated method stub
String sql1 = "SELECT USERID,USERNAME,ROLEID,AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "'),USERCATEGORY FROM usermaster WHERE USERNAME= '"+login.getLoginUserName()+"'";
		
		 return jdbcTemplate.query(sql1, new ResultSetExtractor<Boolean>() {
	        public Boolean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	            while(rs.next()) {
	            	System.out.println("oii"+login.getLoginPassword()+" kk "+rs.getString("AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "')"));
	                if(login.getLoginPassword().equals(rs.getString("AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "')"))) {
		String sql = "insert into usermaster (USERNAME,USERPASSWORD,USERID,ROLEID,USERCATEGORY) values (?,AES_ENCRYPT( ?, '" + encryptionkey +"'),?,?,?)";

		return jdbcTemplate.execute(sql,new PreparedStatementCallback <Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
			          
				ps.setString(1, login.getUserName());
			    ps.setString(2, login.getPassword());
			    ps.setString(3, login.getUserId());
			    ps.setInt(4, 1);
			    ps.setString(5, login.getUserCategory());
			    
			    return ps.execute();
			}
			});
	                }
	            }
	            return null;
	        }
	    });
	}
 




	public boolean addUser(final LoginPojo login) {
		// TODO Auto-generated method stub
		
		String sql = "insert into usermaster (USERNAME,USERPASSWORD,USERID,ROLEID,USERCATEGORY) values (?,AES_ENCRYPT( ?, '" + encryptionkey +"'),?,?,?)";

		return jdbcTemplate.execute(sql,new PreparedStatementCallback <Boolean>(){  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
			        throws SQLException, DataAccessException {  
			          
				ps.setString(1, login.getUserName());
			    ps.setString(2, login.getPassword());
			    ps.setString(3, login.getUserId());
			    ps.setInt(4, 1);
			    ps.setString(5, login.getUserCategory());
			    
			    return ps.execute();
			}
			});
		
	}


	public int deleteUser(String pid) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM usermaster WHERE USERID = ?", new Object[] { pid });
		
	}


	public List<LoginPojo> userAll() {
		// TODO Auto-generated method stub
		
		// String sql = "SELECT * FROM usermaster";
		String sql = "SELECT USERID,USERNAME,ROLEID,AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "'),USERCATEGORY FROM usermaster";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<LoginPojo>>() {
	        public List<LoginPojo> extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	List<LoginPojo> list = new  ArrayList<LoginPojo>();
	            while(rs.next()) {
	                LoginPojo login = new LoginPojo();
	                login.setUserId(rs.getString("USERID"));
	                login.setPassword(rs.getString("AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "')"));
	                login.setUserCategory(rs.getString("USERCATEGORY"));
	                login.setRoleId(rs.getInt("ROLEID"));
	                login.setUserName(rs.getString("USERNAME"));
	                
	                list.add(login);
	                
	            }
	            return list;
	            
	            }
	    });
		
	}

	public LoginPojo userSearchByAll(String all) {
		// TODO Auto-generated method stub
		// String sql = "SELECT * FROM usermaster WHERE USERID = ? or USERNAME = ?";
		String sql = "SELECT USERID,USERNAME,ROLEID,AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "'),USERCATEGORY FROM usermaster WHERE USERID = ? or USERNAME = ?";
		
		return jdbcTemplate.query(sql,  new Object[] { all, all }, new ResultSetExtractor<LoginPojo>() {
	        public LoginPojo extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	        	LoginPojo login = new LoginPojo();
	        	//PatientPojo patient = new PatientPojo();
                
	        	while(rs.next()) {
	            	System.out.println("oii1");
	                login.setUserId(rs.getString("USERID"));   
	                login.setUserName(rs.getString("USERNAME"));   
	                login.setRoleId(rs.getInt("ROLEID"));   
	                login.setPassword(rs.getString("AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "')"));   
	               // login.setUserSerialId(rs.getString("USERSERIALIID"));   
	                login.setUserCategory(rs.getString("USERCATEGORY"));   
		            }
	            return login;
	            
	            }
	    });
	}

	public boolean userUpdate(final LoginPojo login) {
		// TODO Auto-generated method stub
//// CREATE TABLE  usermaster(USERID TEXT PRIMARY KEY,USERNAME TEXT,ROLEID INT,USERPASSWORD TEXT,USERSERIALIID SERIAL,USERCATEGORY TEXT);
		
		String query="UPDATE usermaster set USERPASSWORD = AES_ENCRYPT( ?, '" + encryptionkey +"') WHERE USERID = ?";

		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
		public Boolean doInPreparedStatement(PreparedStatement ps)  
		        throws SQLException, DataAccessException {  
		   
		    ps.setString(1, login.getPassword());
		    ps.setString(2, login.getUserId());
		    return ps.execute();
		}
		});
	
	}

	public boolean userUpdateAdmin(AdminPojo login) {
		// TODO Auto-generated method stub
		String sql1 = "SELECT USERID,USERNAME,ROLEID,AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "'),USERCATEGORY FROM usermaster WHERE USERNAME= '"+login.getUserName()+"'";
		
		 return jdbcTemplate.query(sql1, new ResultSetExtractor<Boolean>() {
	        public Boolean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("oii");
	            while(rs.next()) {
	                if(login.getPassword().equals(rs.getString("AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "')"))) {
	                	System.out.println("oii if");
	                	String query="UPDATE usermaster set USERPASSWORD = AES_ENCRYPT( ?, '" + encryptionkey +"') WHERE USERID = ?";

	            		return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
	            		public Boolean doInPreparedStatement(PreparedStatement ps)  
	            		        throws SQLException, DataAccessException {  
	            		   
	            		    ps.setString(1, login.getUpdatePassword());
	            		    ps.setString(2, login.getUserId());
	            		    return ps.execute();
	            		}
	            		});
	                }
	            }
	            return true;
	        }
	    });
	}

	public boolean userDeleteAdmin(AdminPojo login) {
		// TODO Auto-generated method s
		String sql = "SELECT COUNT(*) FROM usermaster WHERE USERCATEGORY = 'A'";
		return jdbcTemplate.query(sql,  new Object[] { }, new ResultSetExtractor<Boolean>() {
	        public Boolean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	System.out.println("allergic history search by patient id");
	        	rs.next();
	        	if(rs.getInt("COUNT")>1) {
	        		String sql1 = "SELECT USERID,USERNAME,ROLEID,AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "'),USERCATEGORY FROM usermaster WHERE USERNAME= '"+login.getUserName()+"'";
	        		
	       		 return jdbcTemplate.query(sql1, new ResultSetExtractor<Boolean>() {
	       	        public Boolean extractData(ResultSet rs) throws SQLException,
	       	                DataAccessException {
	       	        	System.out.println("oii");
	       	            while(rs.next()) {
	       	                if(login.getPassword().equals(rs.getString("AES_DECRYPT(USERPASSWORD, '" + encryptionkey+ "')"))) {
	       	                	 jdbcTemplate.update("DELETE FROM usermaster WHERE USERNAME = ?", new Object[] { login.getUserName() });
	       	                	return false;
	       	                }
	       	            }
	       	            return true;
	       	        }
	       	    });
	        			        	
	            }
	        	return true;
	        }
	    });
		
		}

	
/*
	public LoginPojo loginDao(String pUsername, String pPassword) {
		LoginPojo users = (LoginPojo) getEntityManager()
	                .createQuery("Select u from LoginPojo u where USERNAME LIKE ? AND USERPASSWORD LIKE ?")
	                .setParameter(1, pUsername)
	                .setParameter(2, pPassword)
	                .getSingleResult();
		System.out.println(users.getUserName());
		System.out.println(users.getPassword());
	        return users;
	}
*/
/*extends BaseDAO<Integer, LoginPojo> 
 * 
 * 	public LoginPojo loginDao(String userName, String password) {
		LoginPojo users = (LoginPojo) getEntityManager()
                .createQuery("Select u from LoginPojo u where USERNAME LIKE ? AND USERPASSWORD LIKE ?")
                .setParameter(1, userName)
                .setParameter(2, password)
                .getSingleResult();
	System.out.println(users.getUserName());
	System.out.println(users.getPassword());
        return users;
        }
*/






	
	

}