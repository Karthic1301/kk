package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.DiagnosisDao;
import com.ast.HealthCare.Pojo.DiagnosisPojo;
import com.ast.HealthCare.Pojo.DrugTypePojo;

@Service
public class DiagnosisService {

	@Autowired
	DiagnosisDao diagDao;
	
	public boolean addDiag(DiagnosisPojo diag) {
		// TODO Auto-generated method stub
		return diagDao.addDiag(diag);
	}

	public List<DiagnosisPojo> diagnosisAll() {
		// TODO Auto-generated method stub
		return diagDao.diagnosisAll();
	}

	public List<DiagnosisPojo> diagnosisSearchByAll(String all) {
		// TODO Auto-generated method stub
		return diagDao.diagnosisSearchByAll(all);
	}

	public boolean diagnosisUpdate(DiagnosisPojo dt) {
		// TODO Auto-generated method stub
		return diagDao.diagnosisUpdate(dt);
	}

	public int diagnosisDelete(int pid) {
		// TODO Auto-generated method stub
		return diagDao.diagnosisDelete(pid);
	}

}
