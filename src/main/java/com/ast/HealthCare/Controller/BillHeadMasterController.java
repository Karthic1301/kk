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

import com.ast.HealthCare.Pojo.BillHeadMasterPojo;
import com.ast.HealthCare.Service.BillHeadMasterService;

@RestController
public class BillHeadMasterController {
	
	@Autowired
	BillHeadMasterService bhms;
	
	@RequestMapping(value = "/AddBillHeadMaster", method = RequestMethod.POST)
	public boolean addbillheadmaster(HttpServletRequest request, HttpServletResponse response, @RequestBody BillHeadMasterPojo bhmpojo ) 
	{
		System.out.println("con "+ bhmpojo);
		return bhms.addbillheadmaster(bhmpojo);
	}
	
	@RequestMapping(value = "/BillHeadMasterAll", method = RequestMethod.GET)
	public List<BillHeadMasterPojo> billHeadMasterAll(HttpServletRequest request, HttpServletResponse response) {
		
		return bhms.billHeadMasterAll();
	}
	
	@RequestMapping(value = "/BillHeadMasterUpdate", method = RequestMethod.PUT)
	public boolean billHeadMasterUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody BillHeadMasterPojo dt ) {
		return bhms.billHeadMasterUpdate(dt);
	}
		
	@RequestMapping(value = "/BillHeadMasterDelete/{bid}", method = RequestMethod.DELETE)
	public int billHeadMasterDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("bid") int bid ) {
		System.out.println("con "+bid);
		return bhms.billHeadMasterDelete(bid);
	}


}
