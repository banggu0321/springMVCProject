package com.kosta.myapp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.EmpVO;
import com.kosta.dto.JobVO;

//service가 DAO를 호출
//CRUD작업(insert(C), select(R), update(U), delete(D) ==> DAO(Data Access Object)
@Repository  //+DB연결
public class EmpDAOMybatis {

	@Autowired
	SqlSession session;
	
	static final String NAME_SPACE = "net.gasan.emp.";
	Logger logger = LoggerFactory.getLogger(EmpDAOMybatis.class);
	
	//1. 모든 직원 조회
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectAll");
		logger.info("selectAll 결과 : " + emplist.size());
		return emplist;
	}
	//1-1. 모든 jobs 조회(emplist.jsp select-option을 위한 추가사항)
	public List<JobVO> selectJobAll() {
		List<JobVO> joblist = session.selectList(NAME_SPACE + "selectJobAll");
		logger.info("selectJobAll 결과 : " + joblist.size());
		return joblist;
	}
	//1-2. 모든 manager 조회
	public List<EmpVO> selectManagerAll() {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByManager");
		logger.info("selectManagerAll 결과 : " + emplist.size());
		return emplist;
	}
	//2. 조건조회(특정부서)-department_id
	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByDept");
		logger.info("selectByDept 결과 : " + emplist.size());
		return emplist;
	}
	//3. 조건조회(특정매니저)-manager_id
	public List<EmpVO> selectByManager(int mid) {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByManager2",mid);
		logger.info("selectByManager 결과 : " + emplist.size());
		return emplist;
	}
	//4. 조건조회(특정job)-job_id
	public List<EmpVO> selectByJob(String job_id) {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByJob",job_id);
		logger.info("selectByJob 결과 : " + emplist.size());
		return emplist;
	}
	//5. 조건조회(특정 department_id, job_id, salary>=, hire_date>=?)
	public List<EmpVO> selectByCondition(int deptid, String job_id, double sal, String hire_date) {
		Map<String, Object> map = new HashMap<>();
		map.put("salary", sal);
		map.put("deptid", deptid);
		map.put("jobid", job_id);
		map.put("hiredate", hire_date);		
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByCondition", map);
		logger.info("selectByCondition 결과 : " + emplist.size());
		return emplist;
	}
	//6.특정 직원 1건 조회-employee_id
	public EmpVO selectById(int empid) {
		EmpVO emp = session.selectOne(NAME_SPACE + "selectById",empid);
		logger.info("selectById 결과 : " + emp);
		return emp;
	}
	
	//(추가-이메일중복체크)SQL_SELECT_BYEMAIL
	public int selectByEmail(String email) {
		int result = session.selectOne(NAME_SPACE + "selectByEmailDup", email);
		logger.info("selectByEmail 결과 : " + result + "건 조회");
		return result;
	}
	
	//~DML
	//7. insert
	public int empInsert(EmpVO emp) {
		int result = session.insert(NAME_SPACE + "insert", emp);
		logger.info("empInsert 결과 : " + result + "건 입력");
		return result;
	}
	//8. update(특정 직원 1건 employee_id=?)
	public int empUpdate(EmpVO emp) {
		int result = session.update(NAME_SPACE + "update", emp);
		logger.info("empInsert 결과 : " + result + "건 수정");
		return result;
	}
	//9. update(조건 department_id=?)
	/*
	 * public int empUpdateByDept(EmpVO emp, int deptid) { }
	 */
	//10. delete(특정 직원 1건 employee_id=?)SQL_DELETE
	public int empDelete(int empid) {
		int result = session.delete(NAME_SPACE + "delete", empid);
		logger.info("empDelete 결과 : " + result + "건 삭제");
		return result;
	}
	//11. delete(조건 department_id=?)
	public int empDeleteByDept(int deptid) {
		int result = session.delete(NAME_SPACE + "deleteByDept", deptid);
		logger.info("empDeleteByDept 결과 : " + result + "건 삭제");
		return result;
	}
}
