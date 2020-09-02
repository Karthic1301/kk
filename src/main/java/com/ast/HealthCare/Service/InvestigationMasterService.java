package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.InvestigationMasterDao;
import com.ast.HealthCare.Pojo.InvestigationMasterPojo;

@Service
public class InvestigationMasterService {

	@Autowired
	InvestigationMasterDao imd;

	public boolean addInvestigation(InvestigationMasterPojo imp) {
		// TODO Auto-generated method stub
		return imd.addInvestigation(imp);
	}

	public List<InvestigationMasterPojo> investigationAll() {
		// TODO Auto-generated method stub
		return imd.investigationAll();
	}

	public List<InvestigationMasterPojo> investigationSearchByAll(String all) {
		// TODO Auto-generated method stub
		return imd.investigationSearchByAll(all);
	}

	public boolean investigationUpdate(InvestigationMasterPojo dt) {
		// TODO Auto-generated method stub
		return imd.investigationUpdate(dt);
	}

	public int investigationDelete(int pid) {
		// TODO Auto-generated method stub
		return imd.investigationDelete(pid);
	}
}
