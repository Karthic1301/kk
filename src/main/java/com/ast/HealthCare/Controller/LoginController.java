package com.ast.HealthCare.Controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Pojo.AdminPojo;
import com.ast.HealthCare.Pojo.LoginPojo;
import com.ast.HealthCare.Service.LoginService;

@RestController
public class LoginController implements Serializable{


	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginPojo doLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginPojo login) {
    System.out.println("working");
    System.out.println(login);
		return loginService.doLogin(login);
	}
	
	@RequestMapping(value = "/CheckUserName/{userName}", method = RequestMethod.POST)
	public Boolean checkUserName(HttpServletRequest request, HttpServletResponse response, @PathVariable("userName") String userName) {
    System.out.println("working");
    System.out.println(userName);
	return loginService.checkUserName(userName);
	}
	
	@RequestMapping(value = "/AddUserAdmin", method = RequestMethod.POST)
	public Boolean addUser(HttpServletRequest request, HttpServletResponse response, @RequestBody AdminPojo login) {
		 System.out.println(login.getLoginPassword());
		 System.out.println(login.getLoginUserName());
    System.out.println("add user da");
    login.setRoleId(1);
    login.setUserCategory("A");
    String rede = "admin "+login.getUserName();
    login.setUserId(rede);
    System.out.println(login);
		return loginService.addUserAdmin(login);
	}
	@RequestMapping(value = "/UserUpdateAdmin", method = RequestMethod.PUT)
	public boolean userUpdateAdmin(HttpServletRequest request, HttpServletResponse response, @RequestBody AdminPojo login ) {
		return loginService.userUpdateAdmin(login);
	}
	
	@RequestMapping(value = "/UserDeleteAdmin", method = RequestMethod.DELETE)
	public boolean userDeleteAdmin(HttpServletRequest request, HttpServletResponse response, @RequestBody AdminPojo login ) {
		return loginService.userDeleteAdmin(login);
	}

	
	@RequestMapping(value = "/UserAll", method = RequestMethod.GET)
	public List<LoginPojo> userAll(HttpServletRequest request, HttpServletResponse response) {
		
		return loginService.userAll();
	}
	
	@RequestMapping(value = "/UserSearchByAll/{all}", method = RequestMethod.GET)
	public LoginPojo userSearchByAll(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
    System.out.println("working");
    System.out.println(all);
	return loginService.userSearchByAll(all);
	}
    

	@RequestMapping(value = "/UserUpdate", method = RequestMethod.PUT)
	public boolean userUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginPojo login ) {
		return loginService.userUpdate(login);
	}

}