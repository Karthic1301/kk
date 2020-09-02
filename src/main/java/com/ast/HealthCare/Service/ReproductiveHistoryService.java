package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.ReproductiveHistoryDao;
import com.ast.HealthCare.Pojo.ReproductiveHistoryPojo;
@Service
public class ReproductiveHistoryService {

	@Autowired
	ReproductiveHistoryDao reproductiveHistoryDao;
	
	public Boolean addReproductiveHistory(ReproductiveHistoryPojo reproductiveHistory) {
		// TODO Auto-generated method stub
		return reproductiveHistoryDao.addReproductiveHistory(reproductiveHistory);
	}

	public List<ReproductiveHistoryPojo> reproductiveHistoryAll() {
		// TODO Auto-generated method stub
		return reproductiveHistoryDao.reproductiveHistoryAll();
	}

	public ReproductiveHistoryPojo reproductiveHistorySearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return reproductiveHistoryDao.reproductiveHistorySearchByPatientId(pid);
	}

	public Boolean updateReproductiveHistory(ReproductiveHistoryPojo reproductiveHistory, String pid) {
		// TODO Auto-generated method stub
		reproductiveHistory.setPatientId(pid);
		return reproductiveHistoryDao.updateReproductiveHistory(reproductiveHistory);
	}

	public int reproductiveHistoryDelete(String pid) {
		// TODO Auto-generated method stub
		return reproductiveHistoryDao.reproductiveHistoryDelete(pid);
	}

}

