package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.SurgicalHistoryDao;
import com.ast.HealthCare.Pojo.SurgicalHistoryPojo;

@Service
public class SurgicalHistoryService {

	@Autowired
	SurgicalHistoryDao shd;

	public boolean addSurgicalHistory(List<SurgicalHistoryPojo> imp) {
		// TODO Auto-generated method stub
		return shd.addSurgicalHistory(imp);
	}

	public List<SurgicalHistoryPojo> surgicalHistoryAll() {
		// TODO Auto-generated method stub
		return shd.surgicalHistoryAll();
	}

	public boolean surgicalHistoryUpdate(SurgicalHistoryPojo dt) {
		// TODO Auto-generated method stub
		return shd.surgicalHistoryUpdate(dt);
	}

	public int surgicalHistoryDelete(int pid) {
		// TODO Auto-generated method stub
		return shd.surgicalHistoryDelete(pid);
	}

	public List<SurgicalHistoryPojo> SurgicalHistorySearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return shd.SurgicalHistorySearchByPatientId(pid);
	}
}
