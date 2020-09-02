package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.BillDetailHistoryDao;
import com.ast.HealthCare.Pojo.BillDetailHistoryPojo;

@Service
public class BillDetailHistoryService {
	
	@Autowired
	BillDetailHistoryDao bhd;

	public boolean addBillDetailHistory(List<BillDetailHistoryPojo> bdhpojo) {
		// TODO Auto-generated method stub
		return bhd.addBillDetailHistory(bdhpojo);
	}

	public List<BillDetailHistoryPojo> billDetailHistoryAll() {
		// TODO Auto-generated method stub
		return bhd.billDetailHistoryAll();
	}
	
	public List<BillDetailHistoryPojo> billDetailHistoryBySerialId(int sid) {
		// TODO Auto-generated method stub
		return bhd.billDetailHistoryBySerialId(sid);
	}

}
