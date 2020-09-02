package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.BirthDetailsDao;
import com.ast.HealthCare.Dao.ContentMasterDao;
import com.ast.HealthCare.Pojo.ContentMasterPojo;

@Service
public class ContentMasterService {

	@Autowired
	ContentMasterDao contentMasterDao;
	@Autowired
	BirthDetailsDao bDao;
	
	

	public boolean addContentMaster(ContentMasterPojo dis) {
		// TODO Auto-generated method stub
		int res= contentMasterDao.addContentMaster(dis);
		return true;
	}

	public List<ContentMasterPojo> contentMasterAll() {
		// TODO Auto-generated method stub
		return contentMasterDao.contentMasterAll();
	}
	
	public ContentMasterPojo contentMasterById(int id) {
		// TODO Auto-generated method stub
		return contentMasterDao.contentMasterById(id);
	}
	public ContentMasterPojo contentMasterByHeadingMasterId(int id) {
		// TODO Auto-generated method stub
		return contentMasterDao.contentMasterByHeadingMasterId(id);
	}

	public int contentMasterDelete(int pid) {
		// TODO Auto-generated method stub
		return contentMasterDao.contentMasterDelete(pid);
	}

	public boolean contentMasterUpdate(ContentMasterPojo dt) {
		// TODO Auto-generated method stub
		return contentMasterDao.contentMasterUpdate(dt);
	}
}
