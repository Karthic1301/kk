package com.ast.HealthCare.Service;

import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.BillDetailDao;
import com.ast.HealthCare.Pojo.BillDetailFeeHeadTypeReportPojo;
import com.ast.HealthCare.Pojo.BillDetailPojo;

@Service


public class BillDetailService {
	@Autowired
	BillDetailDao bd;

	public boolean addBillDetail(List<BillDetailPojo> bdpojo) {
		// TODO Auto-generated method stub
		return bd.addBillDetail(bdpojo);
	}

	public List<BillDetailPojo> billDetailAll() {
		// TODO Auto-generated method stub
		return bd.billDetailAll();
	}

	public boolean billDetailUpdate(BillDetailPojo dt) {
		// TODO Auto-generated method stub
		return bd.billDetailUpdate(dt);
	}

	public int billDetailDelete(int bid, String user, String pwd) {
		// TODO Auto-generated method stub
		return bd.billDetailDelete(bid,user,pwd);
	}

	public List<BillDetailPojo> billDetailGetByNo(String no) {
		// TODO Auto-generated method stub
		return bd.billDetailGetByNo(no);
	}
	
	public List<BillDetailPojo> billDetailGetByFeeId(int id, Date date1, Date date2) {
		// TODO Auto-generated method stub
		return bd.billDetailGetByFeeId(id, date1, date2);
	}

	public List<BillDetailPojo> billDetailGetByFeeIdAndDocId(BillDetailFeeHeadTypeReportPojo data) {
		// TODO Auto-generated method stub
		return bd.billDetailGetByFeeIdAndDocId(data);
	}

	

}
