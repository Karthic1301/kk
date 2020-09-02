package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.SmsChildDao;
import com.ast.HealthCare.Dao.SmsMasterDao;
import com.ast.HealthCare.Pojo.SmsChildPojo;
import com.ast.HealthCare.Pojo.SmsMasterPojo;

@Service
public class SmsMasterService {
	
	@Autowired
	SmsMasterDao smd;
	@Autowired
	SmsChildDao scdao;
	
	public int addsmsmaster(SmsMasterPojo smp) {
		// TODO Auto-generated method stub
		int smsid = smd.addsmsmaster(smp);
		System.out.println(smsid);
		System.out.println(smp.getSmschildpojo());
		scdao.addSmsChild(smp.getSmschildpojo(),smsid);
		return smsid;
	}

	public List<SmsMasterPojo> smsMasterAll() {
		// TODO Auto-generated method stub
		return smd.SmsMasterAll();
	}

	public int smsMasterDelete(int id) {
		// TODO Auto-generated method stub
		scdao.smsChildDelete(id);
		return smd.smsMasterDelete(id);
	}

	public boolean smsMasterUpdate(SmsMasterPojo dt) {
		// TODO Auto-generated method stub
		scdao.smsChildDelete(dt.getSmsserialid());
		scdao.addSmsChild(dt.getSmschildpojo(),dt.getSmsserialid());
		return smd.smsMasterUpdate(dt);
	}

	public SmsMasterPojo smsMasterById(int id) {
		// TODO Auto-generated method stub
		return smd.smsMasterById(id);
	}


}
