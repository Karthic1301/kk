package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.HeadingMasterDao;
import com.ast.HealthCare.Pojo.HeadingMasterPojo;

@Service
public class HeadingMasterService {

	@Autowired
	HeadingMasterDao headingMasterDao;

	public boolean addHeadingMaster(HeadingMasterPojo dis) {
		// TODO Auto-generated method stub
		return headingMasterDao.addHeadingMaster(dis);
	}

	public List<HeadingMasterPojo> headingMasterAll() {
		// TODO Auto-generated method stub
		return headingMasterDao.headingMasterAll();
	}

	public int headingMasterDelete(int pid) {
		// TODO Auto-generated method stub
		return headingMasterDao.headingMasterDelete(pid);
	}

	public boolean headingMasterUpdate(HeadingMasterPojo dt) {
		// TODO Auto-generated method stub
		return headingMasterDao.headingMasterUpdate(dt);
	}
}
