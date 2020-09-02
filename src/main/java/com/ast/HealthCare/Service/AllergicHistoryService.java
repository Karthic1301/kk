package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.AllergicHistoryDao;
import com.ast.HealthCare.Pojo.AllergicHistoryPojo;

@Service
public class AllergicHistoryService {


	@Autowired
	AllergicHistoryDao AHDao;
	
	
	public boolean addAllergicHistory(List<AllergicHistoryPojo> allergic) {
		// TODO Auto-generated method stub
		return AHDao.addAllergicHistory(allergic);
	}


	public List<AllergicHistoryPojo> allergicHistoryAll() {
		// TODO Auto-generated method stub
		return AHDao.allergicHistoryAll();
	}


	public boolean allergicHistoryUpdate(AllergicHistoryPojo allergic) {
		// TODO Auto-generated method stub
		return AHDao.allergicHistoryUpdate(allergic);
	}


	public int allergicHistoryDelete(int pid) {
		// TODO Auto-generated method stub
		return AHDao.allergicHistoryDelete(pid);
	}


	public List<AllergicHistoryPojo> appByDoctor(String pid) {
		// TODO Auto-generated method stub
		return AHDao.allergicHistorySearchByPatientId(pid);
	}


	public int allergicHistoryDelete1(String pid) {
		// TODO Auto-generated method stub
		return AHDao.allergicHistoryDelete1(pid);
	}
}
