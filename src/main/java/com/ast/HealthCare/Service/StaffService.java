package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.LoginDao;
import com.ast.HealthCare.Dao.StaffDao;
import com.ast.HealthCare.Pojo.LoginPojo;
import com.ast.HealthCare.Pojo.PatientPojo;
import com.ast.HealthCare.Pojo.StaffPojo;


@Service
public class StaffService {

	@Autowired
	StaffDao staffDao;
	@Autowired
	LoginDao loginDao;
	
	public Boolean addStaff(StaffPojo staff) {
		// TODO Auto-generated method stub
		LoginPojo login = new LoginPojo();
		String sid = staffDao.addStaff(staff);
		if(sid.equals("false"))
			return false;
		else
		{
			login.setUserName(staff.getStaffUserName());
			login.setPassword(staff.getStaffPassword());
			login.setUserId(sid);
			login.setUserCategory("S");
			login.setRoleId(3);
			if(loginDao.addUser(login))
				System.out.println("user not added");
			else 
				System.out.println("user added");
			return true;
		}
	}

	public List<StaffPojo> viewAllStaff() {
		// TODO Auto-generated method stub
		return staffDao.viewAllStaff();
	}

	public StaffPojo staffSearchById(String sid) {
		// TODO Auto-generated method stub
		return staffDao.staffSearchById(sid);
	}

	
	public List<StaffPojo> staffSearchByName(String sname) {
		// TODO Auto-generated method stub
		return staffDao.staffSearchByName(sname);
	}

	public List<StaffPojo> staffSearchByPhoneNo(String sphone) {
		// TODO Auto-generated method stub
		return staffDao.staffSearchByPhoneNo(sphone);
	}

	public int staffDelete(String sid) {
		// TODO Auto-generated method stub
		return staffDao.staffDelete(sid);
	}

	public boolean staffUpdate(StaffPojo staff) {
		// TODO Auto-generated method stub
		return staffDao.staffUpdate(staff);
	}

	public List<StaffPojo> staffSearchByAll(String all) {
		// TODO Auto-generated method stub
		return staffDao.staffSearchByAll(all);
	}
	
	public List<StaffPojo> staffSearchByAllForSMS(String all) {
		// TODO Auto-generated method stub
		return staffDao.staffSearchByAllForSMS(all);
	}

}
