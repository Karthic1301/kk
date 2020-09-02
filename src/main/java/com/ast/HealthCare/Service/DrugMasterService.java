package com.ast.HealthCare.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.DrugMasterDao;
import com.ast.HealthCare.Pojo.DrugMasterPojo;

@Service
public class DrugMasterService {
	
	@Autowired
	DrugMasterDao DMDao;
	

	public boolean addDrug(DrugMasterPojo drug) {
		// TODO Auto-generated method stub
		return DMDao.addDrug(drug);
	}


	public List<DrugMasterPojo> drugAll() {
		// TODO Auto-generated method stub
		return DMDao.drugAll();
	}


	public boolean drugUpdate(DrugMasterPojo drug) {
		// TODO Auto-generated method stub
		return DMDao.drugUpdate(drug);
	}


	public int drugDelete(int did) {
		// TODO Auto-generated method stub
		return DMDao.drugDelete(did);
	}


	public DrugMasterPojo drugSearchByName(String name) {
		// TODO Auto-generated method stub
		return DMDao.drugSearchByName(name);
	}

}
