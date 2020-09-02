package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.FindingsDao;
import com.ast.HealthCare.Pojo.FindingsPojo;

@Service
public class FindingsService {

	@Autowired
	FindingsDao findingsDao;
	
	public boolean addFindings(FindingsPojo findings) {
		// TODO Auto-generated method stub
		return findingsDao.addFindings(findings);
	}

	public List<FindingsPojo> findingsAll() {
		// TODO Auto-generated method stub
		return findingsDao.findingsAll();
	}

	public int findingsDelete(int pid) {
		// TODO Auto-generated method stub
		return findingsDao.findingsDelete(pid);
	}

	public boolean findingsUpdate(FindingsPojo dt) {
		// TODO Auto-generated method stub
		return findingsDao.findingsUpdate(dt);
	}

}
