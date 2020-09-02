package com.ast.HealthCare.Controller;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Pojo.BillMasterPojo;
import com.ast.HealthCare.Pojo.BillMasterReasonPojo;
import com.ast.HealthCare.Service.BillMasterService;

@RestController
public class BillMasterController {
	
	@Autowired
	BillMasterService bms;
	
	@RequestMapping(value = "/AddBillMaster", method = RequestMethod.POST)
	public BillMasterPojo addbillmaster(HttpServletRequest request, HttpServletResponse response, @RequestBody BillMasterPojo bmpojo ) throws ParseException 
	{
		System.out.println("con "+ bmpojo);
		return bms.addbillmaster(bmpojo);
	}
	
	@RequestMapping(value = "/BillMasterAll", method = RequestMethod.GET)
	public List<BillMasterPojo> billMasterAll(HttpServletRequest request, HttpServletResponse response) {
		
		return bms.billMasterAll();
	}
	
	@RequestMapping(value = "/BillMasterByWOC", method = RequestMethod.GET)
	public List<BillMasterPojo> billMasterByWOC(HttpServletRequest request, HttpServletResponse response) {
		
		return bms.billMasterByWOC();
	}
	
	@RequestMapping(value = "/BillMasterUpdate", method = RequestMethod.PUT)
	public BillMasterPojo billMasterUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody BillMasterPojo dt) {
		return bms.billMasterUpdate(dt);
	}
		
	@RequestMapping(value = "/BillMasterDelete/{bid}", method = RequestMethod.DELETE)
	public int billMasterDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("bid") int bid ) {
		System.out.println("con "+bid);
		return bms.billMasterDelete(bid);
	}
	
	@RequestMapping(value = "/ChangeBillStatusCancel", method = RequestMethod.PUT)
	public boolean changeBillStatusCancel(HttpServletRequest request, HttpServletResponse response, @RequestBody BillMasterReasonPojo data ) {
		System.out.println("con "+data);
		return bms.changeBillStatusCancel(data);
	}
	
	@RequestMapping(value = "/BillMasterSearchByAll/{all}", method = RequestMethod.GET)
	public List<BillMasterPojo> BillMasterSearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
		System.out.println("con "+all);
		return bms.BillMasterSearchByAll(all);
	}
	
	/*@RequestMapping(value = "/BillMasterSearchByPatientId/{pid}", method = RequestMethod.GET)
	public List<BillMasterPojo> BillMasterSearchByPatientId(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		System.out.println("con "+pid);
		return bms.BillMasterSearchByPatientId(pid);
	}
	*/
	@RequestMapping(value = "/BillMasterSearchByBillNo/{bid}", method = RequestMethod.GET)
	public BillMasterPojo BillMasterSearchByBillNo(HttpServletRequest request, HttpServletResponse response, @PathVariable("bid") String bid) {
		System.out.println("con "+bid);
		return bms.BillMasterSearchByBillNo(bid);
	}
	
	@RequestMapping(value = "/BillMasterSearchByBillStatusCancel", method = RequestMethod.GET)
	public List<BillMasterPojo> BillMasterSearchByBillStatusCancel(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("con ");
		return bms.BillMasterSearchByBillStatusCancel();
	}
	
	@RequestMapping(value = "/BillMasterSearchByBillStatusChanged", method = RequestMethod.GET)
	public List<BillMasterPojo> BillMasterSearchByBillStatusChanged(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("con ");
		return bms.BillMasterSearchByBillStatusChanged();
	}
	
	@RequestMapping(value = "/BillMasterSearchByDate/{date1}/{date2}", method = RequestMethod.GET)
	public List<BillMasterPojo> BillMasterSearchByDate(HttpServletRequest request, HttpServletResponse response,@PathVariable("date1") Date date1,@PathVariable("date2") Date date2) {
		System.out.println("con ");
		return bms.BillMasterSearchByDate(date1,date2);
	}
	
	@RequestMapping(value = "/AddBillMasterHistory", method = RequestMethod.POST)
	public boolean addbillmasterhistory(HttpServletRequest request, HttpServletResponse response, @RequestBody BillMasterPojo bmpojo )
	{
		System.out.println("con "+ bmpojo);
		return bms.addbillmasterhistory(bmpojo);
	}
	
	@RequestMapping(value = "/BillMasterHistoryAll", method = RequestMethod.GET)
	public List<BillMasterPojo> billMasterHistoryAll(HttpServletRequest request, HttpServletResponse response) {
		
		return bms.billMasterHistoryAll();
	}
	
	@RequestMapping(value = "/ReasonUpdate", method = RequestMethod.PUT)
	public boolean reasonUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody BillMasterPojo data ) {
		System.out.println("con "+data);
		return bms.reasonUpdate(data);
	}
	
	@RequestMapping(value = "/BillMasterHistorySearchByDate/{date1}/{date2}", method = RequestMethod.GET)
	public List<BillMasterPojo> BillMasterHistorySearchByDate(HttpServletRequest request, HttpServletResponse response,@PathVariable("date1") Date date1,@PathVariable("date2") Date date2) {
		System.out.println("con his");
		return bms.BillMasterHistorySearchByDate(date1,date2);
	}
	
	@RequestMapping(value = "/BillMasterHistorySearchByAll/{all}", method = RequestMethod.GET)
	public List<BillMasterPojo> BillMasterHistorySearchByAll(HttpServletRequest request, HttpServletResponse response, @PathVariable("all") String all) {
		System.out.println("con "+all);
		return bms.BillMasterHistorySearchByAll(all);
	}
	
	@RequestMapping(value = "/modifiedUserid/{bn}", method = RequestMethod.GET)
	public List<BillMasterPojo> ModifiedUserid(HttpServletRequest request, HttpServletResponse response, @PathVariable("bn") String bn) {
		System.out.println("con "+bn);
		return bms.ModifiedUserid(bn);
	}

}
