package com.ast.HealthCare.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ast.HealthCare.Dao.PrescriptionChildDao;
import com.ast.HealthCare.Pojo.PrescriptionChildPojo;
import com.ast.HealthCare.Pojo.PrescriptionChildPojo1;

@Service
public class PrescriptionChildService {

	@Autowired
	PrescriptionChildDao pcd;

	public PrescriptionChildPojo childAll(String pid) {
		// TODO Auto-generated method stub
		return pcd.childAll(pid);
	}

	public Boolean insertChild(PrescriptionChildPojo preschild, String prerscriptionno) {
		System.out.println("insertchild service");
		// TODO Auto-generated method stub
		return pcd.insertChild(preschild,prerscriptionno);
	}

	public PrescriptionChildPojo prescriptionChildSearchByPrescriptionNo(String pid) {
		// TODO Auto-generated method stub
		return pcd.prescriptionChildSearchByPrescriptionNo(pid);
	}
}
