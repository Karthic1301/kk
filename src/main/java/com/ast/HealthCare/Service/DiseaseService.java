package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.DiseaseDao;
import com.ast.HealthCare.Pojo.DiseasePojo;

@Service
public class DiseaseService {

	@Autowired
	DiseaseDao diseaseDao;

	public boolean addDisease(DiseasePojo dis) {
		// TODO Auto-generated method stub
		return diseaseDao.addDisease(dis);
	}

	public List<DiseasePojo> diseaseAll() {
		// TODO Auto-generated method stub
		return diseaseDao.diseaseAll();
	}

	public int diseaseDelete(int pid) {
		// TODO Auto-generated method stub
		return diseaseDao.diseaseDelete(pid);
	}

	public boolean diseaseUpdate(DiseasePojo dt) {
		// TODO Auto-generated method stub
		return diseaseDao.diseaseUpdate(dt);
	}
	
	
}
