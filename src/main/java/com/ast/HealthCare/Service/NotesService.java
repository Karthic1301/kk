package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.NotesDao;
import com.ast.HealthCare.Pojo.NotesPojo;

@Service
public class NotesService {

	@Autowired
	NotesDao notesDao;
	
	public boolean addNotes(NotesPojo notes) {
		// TODO Auto-generated method stub
		return notesDao.addNotes(notes);
	}

	public List<NotesPojo> notesAll() {
		// TODO Auto-generated method stub
		return notesDao.notesAll();
	}

	public int notesDelete(int pid) {
		// TODO Auto-generated method stub
		return notesDao.notesDelete(pid);
	}

	public boolean notesUpdate(NotesPojo dt) {
		// TODO Auto-generated method stub
		return notesDao.notesUpdate(dt);
	}

}
