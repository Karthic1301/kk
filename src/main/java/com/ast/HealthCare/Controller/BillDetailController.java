package com.ast.HealthCare.Controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Pojo.BillDetailFeeHeadTypeReportPojo;
import com.ast.HealthCare.Pojo.BillDetailPojo;
import com.ast.HealthCare.Service.BillDetailService;

@RestController
public class BillDetailController {
	
	@Autowired
	BillDetailService bds;
	
	@RequestMapping(value = "/AddBillDetail", method = RequestMethod.POST)
	public boolean addBillDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody List<BillDetailPojo> bdpojo ) 
	{
		System.out.println("con "+ bdpojo);
		return bds.addBillDetail(bdpojo);
	}
	
	@RequestMapping(value = "/BillDetailAll", method = RequestMethod.GET)
	public List<BillDetailPojo> billDetailAll(HttpServletRequest request, HttpServletResponse response) {
		
		return bds.billDetailAll();
	}
	
	@RequestMapping(value = "/BillDetailGetByNo/{no}", method = RequestMethod.GET)
	public List<BillDetailPojo> billDetailGetByNo(HttpServletRequest request, HttpServletResponse response, @PathVariable("no") String no) {
		
		return bds.billDetailGetByNo(no);
	}
	
	@RequestMapping(value = "/BillDetailGetByFeeId/{id}/{date1}/{date2}", method = RequestMethod.GET)
	public List<BillDetailPojo> billDetailGetByFeeId(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, @PathVariable("date1") Date date1, @PathVariable("date2") Date date2) {
		
		return bds.billDetailGetByFeeId(id,date1,date2);
	}
	
	@RequestMapping(value = "/BillDetailGetByFeeIdAndDocId", method = RequestMethod.POST)
	public List<BillDetailPojo> billDetailGetByFeeIdAndDocId(HttpServletRequest request, HttpServletResponse response,@RequestBody BillDetailFeeHeadTypeReportPojo data ) {
		
		return bds.billDetailGetByFeeIdAndDocId(data);
	}
	
	@RequestMapping(value = "/BillDetailUpdate", method = RequestMethod.PUT)
	public boolean billDetailUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody BillDetailPojo dt ) {
		return bds.billDetailUpdate(dt);
	}
		
	@RequestMapping(value = "/BillDetailDelete/{bid}/{user}/{pwd}", method = RequestMethod.DELETE)
	public int billDetailDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("bid") int bid,@PathVariable("user") String user,
			 @PathVariable("pwd") String pwd ) {
		System.out.println("con "+bid);
		return bds.billDetailDelete(bid,user,pwd);
	}


}
