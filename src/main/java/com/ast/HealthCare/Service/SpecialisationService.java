package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.DoctorDao;
import com.ast.HealthCare.Dao.SpecialisationDao;
import com.ast.HealthCare.Pojo.SpecialisationPojo;

@Service
public class SpecialisationService {
	
	@Autowired
	SpecialisationDao spDao;
	

	public List<SpecialisationPojo> specialisationAll() {
		// TODO Auto-generated method stub
		return spDao.specialisationAll();
	}


	public boolean addSpecialisation(SpecialisationPojo sppojo) {
		// TODO Auto-generated method stub
		return spDao.addSpecialisation(sppojo);
	}


	public List<SpecialisationPojo> specialisationSearchByAll(String all) {
		// TODO Auto-generated method stub
		return spDao.specialisationSearchByAll(all);
	}


	public int specDelete(String pid) {
		// TODO Auto-generated method stub
		return spDao.specDelete(pid);
	}

}
