package com.ast.HealthCare.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Pojo.PrescriptionChildPojo;
import com.ast.HealthCare.Service.PrescriptionChildService;

@RestController
public class PrescriptionChildController {

	@Autowired
	PrescriptionChildService pcs;
	
	@RequestMapping(value = "/PrescriptionChildAll/{pid}", method = RequestMethod.GET)
	public PrescriptionChildPojo childAll(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {	
		return pcs.childAll(pid);
	}
	
	@RequestMapping(value = "/AddPrescriptionChild/{pid}", method = RequestMethod.POST)
	public Boolean insertChild(HttpServletRequest request, HttpServletResponse response, @RequestBody PrescriptionChildPojo child, @PathVariable("pid") String pid) throws FileNotFoundException, IOException {	
		System.out.println("addprescriptionchild controller" +child.getInves());
		return pcs.insertChild(child,pid);

	}	
	
	@RequestMapping(value="/InsertPrescriptionChild/{pid}", method=RequestMethod.POST)
	public Boolean insertchild(HttpServletRequest request, HttpServletResponse response, @RequestBody PrescriptionChildPojo preschild, @PathVariable("pid") String pid)
	{
		System.out.println("insert precsriptionchildPojo");
		// return true;
		return pcs.insertChild(preschild,pid);
	}
	
	@RequestMapping(value = "/PrescriptionChildSearchByPrescriptionNo/{pid}", method = RequestMethod.GET)
	public PrescriptionChildPojo prescriptionChildSearchByPrescriptionNo(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid) {
		System.out.println("con "+pid);
		return pcs.prescriptionChildSearchByPrescriptionNo(pid);
	}

}
