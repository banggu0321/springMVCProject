package com.kosta.myapp.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.dto.EmpVO;
import com.kosta.dto.JobVO;

@Service
public class EmpService {
	@Autowired
	private EmpDAO empDAO;
	//1. 모든 직원 조회
	public List<EmpVO> selectAll() {
		return empDAO.selectAll();
	}
	//1-1. 모든 jobs 조회(emplist.jsp select-option을 위한 추가사항)
	public List<JobVO> selectJobAll() {
		return empDAO.selectJobAll();
	}
	//1-2. 모든 manager 조회
	public Map<Integer,String> selectManagerAll() {
		return empDAO.selectManagerAll();
	}
	//2. 조건조회(특정부서)-department_id
	public List<EmpVO> selectByDept(int deptid) {
		return empDAO.selectByDept(deptid);
	}
	//3. 조건조회(특정매니저)-manager_id
	public List<EmpVO> selectByManager(int mid) {
		return empDAO.selectByManager(mid);
	}
	//4. 조건조회(특정job)-job_id
	public List<EmpVO> selectByJob(String job_id) { //여긴 달라도 됨
		return empDAO.selectByJob(job_id);
	}
	//5. 조건조회(특정 department_id, job_id, salary>=, hire_date>=?)
	public List<EmpVO> selectByCondition(int deptid, String job_id, double sal, String hire_date) {
		return empDAO.selectByCondition(deptid, job_id, sal, hire_date);
	}
	//6.특정 직원 1건 조회-employee_id
	public EmpVO selectById(int empid) {
		return empDAO.selectById(empid);
	}
	//(추가-이메일중복체크)SQL_SELECT_BYEMAIL
	public int selectByEmail(String email) {
		return empDAO.selectByEmail(email);
	}
	
	//~DML
	//7. insert
	public int empInsert(EmpVO emp) {
		return empDAO.empInsert(emp);
	}
	//8. update(특정 직원 1건 employee_id=?)
	public int empUpdate(EmpVO emp) {
		return empDAO.empUpdate(emp);
	}
	//9. update(조건 department_id=?)
	public int empUpdateByDept(EmpVO emp, int deptid) {
		return empDAO.empUpdateByDept(emp, deptid);
	}
	//10. delete(특정 직원 1건 employee_id=?)
	public int empDelete(int empid) {
		return empDAO.empDelete(empid);
	}
	//11. delete(조건 department_id=?)
	public int empDeleteByDept(int deptid) {
		return empDAO.empDeleteByDept(deptid);
	}
}
