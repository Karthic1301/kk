package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.LabTestNormalDao;
import com.ast.HealthCare.Dao.LabTestNormalDetailsDao;
import com.ast.HealthCare.Pojo.LabTestNormalDetailsPojo;
import com.ast.HealthCare.Pojo.LabTestNormalPojo;

@Service
public class LabTestNormalService {
	
	@Autowired
	LabTestNormalDao TNDao;
	@Autowired
	LabTestNormalDetailsDao TDDao;
	
	LabTestNormalDetailsPojo detPojo=new LabTestNormalDetailsPojo();
	
	
	

	public LabTestNormalPojo addTestNormal(LabTestNormalPojo drug) {
		// TODO Auto-generated method stub
		LabTestNormalPojo pojo =new LabTestNormalPojo();
		pojo=TNDao.addTestNormal(drug);
		for(int i=0; i< drug.getTestnormaldetail().size();i++)
		{
			
			drug.getTestnormaldetail().get(i).setTestNormalId(pojo.getTestNormalId());
		}
		TDDao.addTestNormalDetails(drug.getTestnormaldetail());
		
		return pojo;
	}


	public List<LabTestNormalPojo> testNormalAll() {
		// TODO Auto-generated method stub
		return TNDao.testNormalAll();
	}


	public boolean testNormalUpdate(LabTestNormalPojo drug) {
		// TODO Auto-generated method stub
		LabTestNormalPojo pojo=new LabTestNormalPojo();
		TDDao.testNormalDetailsDeleteList(drug.getTestNormalId());
		boolean flag=TNDao.testNormalUpdate(drug);
		for(int i=0; i< drug.getTestnormaldetail().size();i++)
		{
			
			drug.getTestnormaldetail().get(i).setTestNormalId(drug.getTestNormalId());
		}
		TDDao.addTestNormalDetails(drug.getTestnormaldetail());
		
		return flag;
	}


	public int testNormalDelete(int did) {
		// TODO Auto-generated method stub
		TDDao.testNormalDetailsDeleteList(did);
		return TNDao.testNormalDelete(did);
	}


	/*public LabTestNormalPojo testNormalSearchByName(String name) {
		// TODO Auto-generated method stub
		return TNDao.testNormalSearchByName(name);
	}*/
	
	public LabTestNormalPojo testNormalSearchById(int id) {
		// TODO Auto-generated method stub
		return TNDao.testNormalSearchById(id);
	}
	
	public LabTestNormalPojo testNormalSearchByTestMasterId(int id) {
		// TODO Auto-generated method stub
		return TNDao.testNormalSearchByTestMasterId(id);
	}

}
