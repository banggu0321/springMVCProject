package com.kosta.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.dto.DeptDTO;

//Service : Business Logic �ۼ��Ѵ�.
//DB�۾��� DAOȣ���Ѵ�.
@Service
public class DeptService {
	
	@Autowired
	private DeptDAO deptDAO;

	//1. ��� ��ȸ (������ ���� ��ȸ)
	public List<DeptDTO> selectAll() {
		return deptDAO.selectAll();
	}
	//2. Ư���μ� ��ȸ(�μ��ڵ�� ��ȸ) (�μ����̵� �����ϱ� int )
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}
	//3. �����ڵ�� ��ȸ (locid���ͼ� int)
	public List<DeptDTO> selectByLocation(int locid) {
		return deptDAO.selectByLocation(locid);
	}
	//4. �ű� �μ��Է� ( 4���� �ѹ��� �����Ƿ� DeptDTO)
	public int deptInsert(DeptDTO dept) {
		return deptDAO.deptInsert(dept);
	}
	//5. Ư���μ� ����
	public int deptUpdate(DeptDTO dept) {
		return deptDAO.deptUpdate(dept);
	}
	//6. Ư���μ� ����
	public int deptDelete(int deptid) {
		return deptDAO.deptDelete(deptid);
	}
}