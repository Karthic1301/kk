package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.SocialAndPreventiveHistoryDao;
import com.ast.HealthCare.Pojo.SocialAndPreventiveHistoryPojo;

@Service
public class SocialAndPreventiveHistoryService {

	@Autowired
	SocialAndPreventiveHistoryDao sphd;

	public boolean addsph(SocialAndPreventiveHistoryPojo imp) {
		// TODO Auto-generated method stub
		return sphd.addsph(imp);
	}

	public List<SocialAndPreventiveHistoryPojo> sphAll() {
		// TODO Auto-generated method stub
		return sphd.sphAll();
	}

	public boolean sphUpdate(SocialAndPreventiveHistoryPojo dt) {
		// TODO Auto-generated method stub
		return sphd.sphUpdate(dt);
	}

	public int sphDelete(int pid) {
		// TODO Auto-generated method stub
		return sphd.sphDelete(pid);
	}

	public SocialAndPreventiveHistoryPojo sphSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return sphd.sphSearchByPatientId(pid);
	}
}
