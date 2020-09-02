package com.ast.HealthCare.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Pojo.BillDetailHistoryPojo;
import com.ast.HealthCare.Service.BillDetailHistoryService;

@RestController
public class BillDetailHistoryController {
	
	@Autowired
	BillDetailHistoryService bdhs;
	
	@RequestMapping(value = "/AddBillDetailHistory", method = RequestMethod.POST)
	public boolean addBillDetailHistory(HttpServletRequest request, HttpServletResponse response, @RequestBody List<BillDetailHistoryPojo> bdhpojo ) 
	{
		System.out.println("con "+ bdhpojo);
		return bdhs.addBillDetailHistory(bdhpojo);
	}
	
	@RequestMapping(value = "/BillDetailHistoryAll", method = RequestMethod.GET)
	public List<BillDetailHistoryPojo> billDetailHistoryAll(HttpServletRequest request, HttpServletResponse response) {
		
		return bdhs.billDetailHistoryAll();
	}
	
	@RequestMapping(value = "/BillDetailHistoryBySerialId/{sid}", method = RequestMethod.GET)
	public List<BillDetailHistoryPojo> billDetailHistoryBySerialId(HttpServletRequest request, HttpServletResponse response, @PathVariable("sid") int sid) {
		
		return bdhs.billDetailHistoryBySerialId(sid);
	}

}
