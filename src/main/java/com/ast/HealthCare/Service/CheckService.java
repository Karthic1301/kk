package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.CheckDao;
import com.ast.HealthCare.Pojo.CheckPojo;

@Service
public class CheckService {

	@Autowired()
	CheckDao checkObj;
	
	public boolean addCheck(CheckPojo summa) {
		// TODO Auto-generated method stub
		return checkObj.addCheck(summa);
	}

	public List<CheckPojo> viewCheck() {
		// TODO Auto-generated method stub
		return checkObj.viewCheck();
	}

	public int checkDelete(String pid) {
		// TODO Auto-generated method stub
		return checkObj.checkDelete(pid);
	}

	public boolean checkUpdate(CheckPojo dt) {
		// TODO Auto-generated method stub
		return checkObj.checkUpdate(dt);
	}

}
