package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.SurgicalTypeDao;
import com.ast.HealthCare.Pojo.SurgicalTypePojo;

@Service
public class SurgicalTypeService {

	@Autowired
	SurgicalTypeDao surgicalTypeDao;
	
	public boolean addSurgicalType(SurgicalTypePojo drugtype) {
		// TODO Auto-generated method stub
		return surgicalTypeDao.addSurgicalType(drugtype);
	}

	public List<SurgicalTypePojo> surgicalTypeAll() {
		// TODO Auto-generated method stub
		return surgicalTypeDao.surgicalAll();
	}

	
	public boolean surgicalTypeUpdate(SurgicalTypePojo dt) {
		// TODO Auto-generated method stub
		return surgicalTypeDao.surgicalUpdate(dt);
	}

	public int surgicalTypeDelete(int pid) {
		// TODO Auto-generated method stub
		return surgicalTypeDao.surgicalDelete(pid);
	}

	public SurgicalTypePojo surgicalTypeSearchById(int id) {
		// TODO Auto-generated method stub
		return surgicalTypeDao.surgicalSearchById(id);
	}
	
}
