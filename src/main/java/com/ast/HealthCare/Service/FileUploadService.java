package com.ast.HealthCare.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ast.HealthCare.Dao.FileUploadDao;
import com.ast.HealthCare.Pojo.DrugTypePojo;
import com.ast.HealthCare.Pojo.FileUploadPojo;

@Service
public class FileUploadService {

	@Autowired
	FileUploadDao fileDao;

	public boolean addFileUpload(FileUploadPojo upload) {
		// TODO Auto-generated method stub
		return fileDao.addFileUpload(upload);
	}

	public List<FileUploadPojo> fileUploadAll() {
		// TODO Auto-generated method stub
		return fileDao.fileUploadAll();
	}

	public FileUploadPojo fileUploadSearchById(int id) {
		// TODO Auto-generated method stub
		return fileDao.fileUploadSearchById(id);
	}
	
}
