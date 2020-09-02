package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.SettingDao;
import com.ast.HealthCare.Pojo.SettingPojo;

@Service
public class SettingService {

	@Autowired
	SettingDao settingDao;

	public boolean addSetting(SettingPojo dis) {
		// TODO Auto-generated method stub
		return settingDao.addSetting(dis);
	}

	public List<SettingPojo> settingAll() {
		// TODO Auto-generated method stub
		return settingDao.settingAll();
	}
	public SettingPojo settingById(int id) {
		// TODO Auto-generated method stub
		return settingDao.settingById(id);
	}

	public int settingDelete(int pid) {
		// TODO Auto-generated method stub
		return settingDao.settingDelete(pid);
	}

	public boolean settingUpdate(SettingPojo dt) {
		// TODO Auto-generated method stub
		return settingDao.settingUpdate(dt);
	}
}
