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

import com.ast.HealthCare.Pojo.NotesPojo;
import com.ast.HealthCare.Service.NotesService;

@RestController
public class NotesController {
	
	@Autowired
	NotesService notesService;
	
	@RequestMapping(value = "/AddNotes", method = RequestMethod.POST)
	public boolean addNotes(HttpServletRequest request, HttpServletResponse response, @RequestBody NotesPojo notes ) 
	{
		System.out.println("con "+ notes);
		return notesService.addNotes(notes);
	}
	
	@RequestMapping(value = "/NotesAll", method = RequestMethod.GET)
	public List<NotesPojo> notesAll(HttpServletRequest request, HttpServletResponse response) {
		
		return notesService.notesAll();
	}
	
	@RequestMapping(value = "/NotesUpdate", method = RequestMethod.PUT)
	public boolean notesUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody NotesPojo dt ) {
		return notesService.notesUpdate(dt);
	}
		
	@RequestMapping(value = "/NotesDelete/{pid}", method = RequestMethod.DELETE)
	public int notesDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable("pid") int pid ) {
		System.out.println("con "+pid);
		return notesService.notesDelete(pid);
	}


}
