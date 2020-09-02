package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.BillHeadMasterDao;
import com.ast.HealthCare.Pojo.BillHeadMasterPojo;

@Service
public class BillHeadMasterService {
	
	@Autowired
	BillHeadMasterDao bhm;
	
	public boolean addbillheadmaster(BillHeadMasterPojo bhmpojo) {
		// TODO Auto-generated method stub
		return bhm.addbillheadmaster(bhmpojo);
	}

	public List<BillHeadMasterPojo> billHeadMasterAll() {
		// TODO Auto-generated method stub
		return bhm.billHeadMasterAll();
	}

	public int billHeadMasterDelete(int bid) {
		// TODO Auto-generated method stub
		return bhm.billHeadMasterDelete(bid);
	}

	public boolean billHeadMasterUpdate(BillHeadMasterPojo dt) {
		// TODO Auto-generated method stub
		return bhm.billHeadMasterUpdate(dt);
	}

}
