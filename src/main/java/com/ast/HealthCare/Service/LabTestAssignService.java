package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.LabTestAssignDao;
import com.ast.HealthCare.Dao.LabTestAssignDetailsDao;
import com.ast.HealthCare.Pojo.LabTestAssignPojo;
import com.ast.HealthCare.Pojo.LabTestNormalPojo;

@Service
public class LabTestAssignService {

	@Autowired
	LabTestAssignDao TMDao;
	@Autowired
	LabTestAssignDetailsDao LTAD;
	

	public LabTestAssignPojo addTestAssign(LabTestAssignPojo drug) {
		// TODO Auto-generated method stub
		System.out.println(drug.toString());
		LabTestAssignPojo pojo =new LabTestAssignPojo();
		pojo=TMDao.addTestAssign(drug);
		for(int i=0; i< drug.getLabTestAssignDet().size();i++)
		{
			
			drug.getLabTestAssignDet().get(i).setLabTestId(pojo.getLabTestId());
		}
		LTAD.addTestAssignDetails(drug.getLabTestAssignDet());
		
		return pojo;
	
	}


	public List<LabTestAssignPojo> testAssignAll() {
		// TODO Auto-generated method stub
		return TMDao.testAssignAll();
	}


	public boolean testAssignUpdate(LabTestAssignPojo drug) {
		// TODO Auto-generated method stub
		LabTestAssignPojo pojo=new LabTestAssignPojo();
		LTAD.testAssignDetailsDeleteList(drug.getLabTestId());
		boolean flag=TMDao.testAssignUpdate(drug);
		for(int i=0; i< drug.getLabTestAssignDet().size();i++)
		{
			
			drug.getLabTestAssignDet().get(i).setLabTestId(drug.getLabTestId());
		}
		LTAD.addTestAssignDetails(drug.getLabTestAssignDet());
		
		return flag;
	}


	public int testAssignDelete(int did) {
		// TODO Auto-generated method stub
		LTAD.testAssignDetailsDeleteList(did);
		return TMDao.testAssignDelete(did);
	}


	public LabTestAssignPojo testAssignSearchByName(String name) {
		// TODO Auto-generated method stub
		return TMDao.testAssignSearchByName(name);
	}
	
	public LabTestAssignPojo testAssignSearchById(int id) {
		// TODO Auto-generated method stub
		return TMDao.testAssignSearchById(id);
	}
	
	public LabTestAssignPojo testAssignSearchByTestMasterId(int id) {
		// TODO Auto-generated method stub
		return TMDao.testAssignSearchByTestMasterId(id);
	}

	
}
