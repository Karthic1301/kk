package com.ast.HealthCare.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ast.HealthCare.Dao.UploadDao;

@Service
public class UploadService {

	@Autowired
	UploadDao uploaddao;

	public Integer uploadDataFile(MultipartFile file) throws IOException {
		return uploaddao.uploadDataFile(file);

	}

	public byte[] viewfile(Long invid) throws IOException {
		return uploaddao.viewfile(invid);
	}

	public List<byte[]> viewFileByPatientId(String pid) throws IOException {
		// TODO Auto-generated method stub
		return uploaddao.viewFileByPatientId(pid);
	}

}
