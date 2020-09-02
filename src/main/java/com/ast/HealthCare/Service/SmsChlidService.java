package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.SmsChildDao;
import com.ast.HealthCare.Pojo.SmsChildPojo;

@Service
public class SmsChlidService {
	
	@Autowired
	SmsChildDao scd;

	public boolean addSmsChild(List<SmsChildPojo> smcpojo, int smsid) {
		// TODO Auto-generated method stub
		return scd.addSmsChild(smcpojo, smsid);
	}

	public List<SmsChildPojo> smsChildAll() {
		// TODO Auto-generated method stub
		return scd.smsChildAll();
	}

	public boolean smsChildUpdate(SmsChildPojo dt) {
		// TODO Auto-generated method stub
		return scd.SmsChildUpdate(dt);
	}

	public int smsChildDelete(int pid) {
		// TODO Auto-generated method stub
		return scd.smsChildDelete(pid);
	}
	
	public List<SmsChildPojo> smsChildById(int id) {
		// TODO Auto-generated method stub
		return scd.smsChildById(id);
	}

}
