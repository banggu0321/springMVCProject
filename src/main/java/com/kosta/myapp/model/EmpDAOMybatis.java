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

//service�� DAO�� ȣ��
//CRUD�۾�(insert(C), select(R), update(U), delete(D) ==> DAO(Data Access Object)
@Repository  //+DB����
public class EmpDAOMybatis {

	@Autowired
	SqlSession session;
	
	static final String NAME_SPACE = "net.gasan.emp.";
	Logger logger = LoggerFactory.getLogger(EmpDAOMybatis.class);
	
	//1. ��� ���� ��ȸ
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectAll");
		logger.info("selectAll ��� : " + emplist.size());
		return emplist;
	}
	//1-1. ��� jobs ��ȸ(emplist.jsp select-option�� ���� �߰�����)
	public List<JobVO> selectJobAll() {
		List<JobVO> joblist = session.selectList(NAME_SPACE + "selectJobAll");
		logger.info("selectJobAll ��� : " + joblist.size());
		return joblist;
	}
	//1-2. ��� manager ��ȸ
	public List<EmpVO> selectManagerAll() {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByManager");
		logger.info("selectManagerAll ��� : " + emplist.size());
		return emplist;
	}
	//2. ������ȸ(Ư���μ�)-department_id
	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByDept");
		logger.info("selectByDept ��� : " + emplist.size());
		return emplist;
	}
	//3. ������ȸ(Ư���Ŵ���)-manager_id
	public List<EmpVO> selectByManager(int mid) {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByManager2",mid);
		logger.info("selectByManager ��� : " + emplist.size());
		return emplist;
	}
	//4. ������ȸ(Ư��job)-job_id
	public List<EmpVO> selectByJob(String job_id) {
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByJob",job_id);
		logger.info("selectByJob ��� : " + emplist.size());
		return emplist;
	}
	//5. ������ȸ(Ư�� department_id, job_id, salary>=, hire_date>=?)
	public List<EmpVO> selectByCondition(int deptid, String job_id, double sal, String hire_date) {
		Map<String, Object> map = new HashMap<>();
		map.put("salary", sal);
		map.put("deptid", deptid);
		map.put("jobid", job_id);
		map.put("hiredate", hire_date);		
		List<EmpVO> emplist = session.selectList(NAME_SPACE + "selectByCondition", map);
		logger.info("selectByCondition ��� : " + emplist.size());
		return emplist;
	}
	//6.Ư�� ���� 1�� ��ȸ-employee_id
	public EmpVO selectById(int empid) {
		EmpVO emp = session.selectOne(NAME_SPACE + "selectById",empid);
		logger.info("selectById ��� : " + emp);
		return emp;
	}
	
	//(�߰�-�̸����ߺ�üũ)SQL_SELECT_BYEMAIL
	public int selectByEmail(String email) {
		int result = session.selectOne(NAME_SPACE + "selectByEmailDup", email);
		logger.info("selectByEmail ��� : " + result + "�� ��ȸ");
		return result;
	}
	
	//~DML
	//7. insert
	public int empInsert(EmpVO emp) {
		int result = session.insert(NAME_SPACE + "insert", emp);
		logger.info("empInsert ��� : " + result + "�� �Է�");
		return result;
	}
	//8. update(Ư�� ���� 1�� employee_id=?)
	public int empUpdate(EmpVO emp) {
		int result = session.update(NAME_SPACE + "update", emp);
		logger.info("empInsert ��� : " + result + "�� ����");
		return result;
	}
	//9. update(���� department_id=?)
	/*
	 * public int empUpdateByDept(EmpVO emp, int deptid) { }
	 */
	//10. delete(Ư�� ���� 1�� employee_id=?)SQL_DELETE
	public int empDelete(int empid) {
		int result = session.delete(NAME_SPACE + "delete", empid);
		logger.info("empDelete ��� : " + result + "�� ����");
		return result;
	}
	//11. delete(���� department_id=?)
	public int empDeleteByDept(int deptid) {
		int result = session.delete(NAME_SPACE + "deleteByDept", deptid);
		logger.info("empDeleteByDept ��� : " + result + "�� ����");
		return result;
	}
}
