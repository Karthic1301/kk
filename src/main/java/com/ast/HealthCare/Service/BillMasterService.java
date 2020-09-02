package com.ast.HealthCare.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.BillDetailDao;
import com.ast.HealthCare.Dao.BillDetailHistoryDao;
import com.ast.HealthCare.Dao.BillMasterDao;
import com.ast.HealthCare.Dao.PatientDao;
import com.ast.HealthCare.Dao.PrescriptionMasterDao;
import com.ast.HealthCare.Pojo.BillMasterPojo;
import com.ast.HealthCare.Pojo.BillMasterReasonPojo;

@Service
public class BillMasterService {
	
	@Autowired
	BillMasterDao bm;
	@Autowired
	BillDetailDao bdh;
	@Autowired
	PrescriptionMasterDao pm;
	@Autowired
	PatientDao pDao;
	
	public BillMasterPojo addbillmaster(BillMasterPojo bmpojo) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println(bmpojo.toString());
		String pid="";
		if(bmpojo.getPatientid()==null)
		{
			pid = pDao.register(bmpojo.getPatientPojo());
			bmpojo.setPatientid(pid);
		}
		
		if(bmpojo.getPrescriptionNo() != null)
		{
			bmpojo.setBillingFlag("PRESCRIPTION");
			
		}
		else
		{
			bmpojo.setBillingFlag("DIRECT");
		}
		BillMasterPojo bpojo = bm.addbillmaster(bmpojo);
		
		for(int i=0; i< bmpojo.getBilldetailpojo().size();i++) {
			bmpojo.getBilldetailpojo().get(i).setBillserailid(bpojo.getSerialid());
			bmpojo.getBilldetailpojo().get(i).setBillno(bpojo.getBillno());
			bmpojo.getBilldetailpojo().get(i).setDoctorid(bpojo.getDoctorId());
			System.out.println(bpojo.getBilldetailpojo().toString());
			
		}
		bdh.addBillDetail(bmpojo.getBilldetailpojo());
		BillMasterPojo res= bm.BillMasterSearchById(bpojo.getSerialid());
		
		if(bmpojo.getPrescriptionNo() != null)
		{
		pm.prescriptionMasterBillIdUpdate(bpojo.getSerialid(), bmpojo.getPrescriptionNo());
		}
		return res;
	}

	public List<BillMasterPojo> billMasterAll() {
		// TODO Auto-generated method stub
		return bm.billMasterAll();
	}
	
	public List<BillMasterPojo> billMasterByWOC() {
		// TODO Auto-generated method stub
		return bm.billMasterByWOC();
	}

	public int billMasterDelete(int bid) {
		// TODO Auto-generated method stub
		return bm.billMasterDelete(bid);
	}

	public BillMasterPojo billMasterUpdate(BillMasterPojo dt) {
		// TODO Auto-generated method stub
		System.out.println(dt.toString());
		boolean res=bm.reasonUpdate(dt);
		boolean updateres = bm.billMasterUpdate(dt);
		bdh.billDetailDeleteByBillId(dt.getSerialid(),dt.getLogusername(),dt.getLogpassword());
		bdh.addBillDetail(dt.getBilldetailpojo());
		BillMasterPojo respojo= bm.BillMasterSearchById(dt.getSerialid());
		System.out.println(respojo.toString());
		return respojo;
	}
	
	public boolean changeBillStatusCancel(BillMasterReasonPojo data) {
		// TODO Auto-generated method stub
		return bm.changeBillStatusCancel(data);
	}
	
	public List<BillMasterPojo> BillMasterSearchByAll(String all) {
		// TODO Auto-generated method stub
		
		return bm.BillMasterSearchByAll(all);
	}
	
	public BillMasterPojo BillMasterSearchByBillNo(String pid) {
		// TODO Auto-generated method stub
		return bm.BillMasterSearchByBillNo(pid);
	}
	
	/*public List<BillMasterPojo> BillMasterSearchByPatientId(String pid) {
		// TODO Auto-generated method stub
		return bm.BillMasterSearchByPatientId(pid);
	}*/
	
	public List<BillMasterPojo> BillMasterSearchByBillStatusCancel() {
		// TODO Auto-generated method stub
		return bm.BillMasterSearchByBillStatusCancel();
	}
	
	public List<BillMasterPojo> BillMasterSearchByBillStatusChanged() {
		// TODO Auto-generated method stub
		return bm.BillMasterSearchByBillStatusChanged();
	}
	
	public List<BillMasterPojo> BillMasterSearchByDate(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return bm.BillMasterSearchByDate(date1,date2);
	}
	
	public boolean addbillmasterhistory(BillMasterPojo bmpojo) {
		// TODO Auto-generated method stub
		return bm.addBillMasterHistory(bmpojo);
	}
	
	public List<BillMasterPojo> billMasterHistoryAll() {
		// TODO Auto-generated method stub
		return bm.billMasterhistoryAll();
	}
	
	public boolean reasonUpdate(BillMasterPojo data) {
		// TODO Auto-generated method stub
		return bm.reasonUpdate(data);
	}
	
	public List<BillMasterPojo> BillMasterHistorySearchByDate(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return bm.BillMasterHistorySearchByDate(date1,date2);
	}
	
	public List<BillMasterPojo> BillMasterHistorySearchByAll(String all) {
		// TODO Auto-generated method stub
		return bm.BillMasterHistorySearchByAll(all);
	}
	
	public List<BillMasterPojo> ModifiedUserid(String bn){
		// TODO Auto-generated method stub
		return bm.ModifiedUserid(bn);
	}

}
