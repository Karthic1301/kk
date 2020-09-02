package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.ComplaintsDao;
import com.ast.HealthCare.Pojo.ComplaintsPojo;

@Service
public class ComplaintsService {

	@Autowired
	ComplaintsDao comp;
	
	public boolean addcomplaints(ComplaintsPojo comppojo) {
		// TODO Auto-generated method stub
		return comp.addcomplaints(comppojo);
	}

	public List<ComplaintsPojo> complaintsAll() {
		// TODO Auto-generated method stub
		return comp.complaintsAll();
	}

	public int complaintsDelete(int pid) {
		// TODO Auto-generated method stub
		return comp.complaintsDelete(pid);
	}

	public boolean complaintsUpdate(ComplaintsPojo dt) {
		// TODO Auto-generated method stub
		return comp.complaintsUpdate(dt);
	}

}
