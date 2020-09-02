package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.DoctorDao;
import com.ast.HealthCare.Dao.DrugTypeDao;
import com.ast.HealthCare.Pojo.DrugTypePojo;

@Service
public class DrugTypeService {


	@Autowired
	DrugTypeDao drugTypeDao;
	
	public boolean addDrugType(DrugTypePojo drugtype) {
		// TODO Auto-generated method stub
		return drugTypeDao.addDrugType(drugtype);
	}

	public List<DrugTypePojo> drugTypeAll() {
		// TODO Auto-generated method stub
		return drugTypeDao.drugTypeAll();
	}

	public List<DrugTypePojo> drugTypeSearchByAll(String all) {
		// TODO Auto-generated method stub
		return drugTypeDao.drugTypeSearchByAll(all);
	}

	public boolean drugTypeUpdate(DrugTypePojo dt) {
		// TODO Auto-generated method stub
		return drugTypeDao.drugTypeUpdate(dt);
	}

	public int drugTypeDelete(int pid) {
		// TODO Auto-generated method stub
		return drugTypeDao.drugTypeDelete(pid);
	}

	public DrugTypePojo drugTypeSearchById(int id) {
		// TODO Auto-generated method stub
		return drugTypeDao.drugTypeSearchById(id);
	}

}
