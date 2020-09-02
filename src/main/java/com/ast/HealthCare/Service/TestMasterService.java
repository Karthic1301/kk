package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.TestMasterDao;
import com.ast.HealthCare.Pojo.TestMasterPojo;

@Service
public class TestMasterService {

	@Autowired
	TestMasterDao testMasterDao;

	public boolean addTestMaster(TestMasterPojo dis) {
		// TODO Auto-generated method stub
		return testMasterDao.addTestMaster(dis);
	}

	public List<TestMasterPojo> testMasterAll() {
		// TODO Auto-generated method stub
		return testMasterDao.testMasterAll();
	}
	
	public List<TestMasterPojo> testAllByPrescriptionFlag() {
		// TODO Auto-generated method stub
		return testMasterDao.testAllByPrescriptionFlag();
	}
	
	public List<TestMasterPojo> testAllByInOutFlag(String status) {
		// TODO Auto-generated method stub
		return testMasterDao.testAllByInOutFlag(status);
	}
	
	public TestMasterPojo testMasterById(int id) {
		// TODO Auto-generated method stub
		return testMasterDao.testMasterById(id);
	}

	public int testMasterDelete(int pid) {
		// TODO Auto-generated method stub
		return testMasterDao.testMasterDelete(pid);
	}

	public boolean testMasterUpdate(TestMasterPojo dt) {
		// TODO Auto-generated method stub
		return testMasterDao.testMasterUpdate(dt);
	}
	
	
	
}
