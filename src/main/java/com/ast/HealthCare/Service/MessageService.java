package com.ast.HealthCare.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.MessageDao;
import com.ast.HealthCare.Dao.SMSSettingDao;

@Service
public class MessageService {
	
	@Autowired
	MessageDao msgDao;
	@Autowired
	SMSSettingDao ssd;
	
	public String Message(String pid, String content) {
		// TODO Auto-generated method stub
		return msgDao.Message(pid, content);
	}
	
	public int updatePrescriptionNextVisitTime(String time) {
		// TODO Auto-generated method stub
				return ssd.updatePrescriptionNextVisitTime(time);
	}

}
