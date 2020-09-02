package com.ast.HealthCare.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ast.HealthCare.Service.MessageService;

@RestController
public class MessageController {

	@Autowired
	MessageService msgService;
	
	@RequestMapping(value = "/Message/{pid}/{content}", method = RequestMethod.GET)
	public String Message(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") String pid, @PathVariable("content") String content) {
		System.out.println("con "+pid+" "+content);
		return msgService.Message(pid, content);
	}
	
	@RequestMapping(value = "/UpdatePrescriptionNextVisitTime/{time}", method = RequestMethod.PUT)
	public int updatePrescriptionNextVisitTime(HttpServletRequest request, HttpServletResponse response, @PathVariable("time") String time) {
		System.out.println("con "+time);
		return msgService.updatePrescriptionNextVisitTime(time);
	}
}
