package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.GenericDao;
import com.ast.HealthCare.Pojo.GenericPojo;

@Service
public class GenericService {

	@Autowired
	GenericDao generic;
	
	public boolean addGeneric(GenericPojo gen) {
		// TODO Auto-generated method stub
		return generic.addGeneric(gen);
	}

	public List<GenericPojo> genericAll() {
		// TODO Auto-generated method stub
		return generic.genericAll();
	}

	public int genericDelete(int pid) {
		// TODO Auto-generated method stub
		return generic.genericDelete(pid);
	}

	public boolean genericUpdate(GenericPojo dt) {
		// TODO Auto-generated method stub
		return generic.genericUpdate(dt);
	}

}
