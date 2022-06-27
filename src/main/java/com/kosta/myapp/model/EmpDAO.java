package com.kosta.myapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.EmpVO;
import com.kosta.dto.JobVO;
import com.kosta.util.DBUtil;

//service가 DAO를 호출
//CRUD작업(insert(C), select(R), update(U), delete(D) ==> DAO(Data Access Object)
@Repository
public class EmpDAO {
	@Autowired
	DataSource ds;
	
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	int result;
	
	static final String SQL_SELECT_ALL = "select * from employees order by 1";
	static final String SQL_SELECT_BYDEPT = "select * from employees where department_id = ? order by 1";
	static final String SQL_SELECT_BYMANAGER = "select * from employees where manager_id = ? order by 1";
	static final String SQL_SELECT_BYJOB = "select * from employees where job_id = ? order by 1";
	static final String SQL_SELECT_CONDITION = 
			  "select * from employees "
			+ " where department_id = ? "
			+ " and job_id = ?"
			+ " and salary >= ?"
			+ " and hire_date >= ?"
			+ " order by 1";
	static final String SQL_SELECT_BYID = "select * from employees where employee_id = ?";
	static final String SQL_INSERT = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
	static final String SQL_UPDATE = "UPDATE EMPLOYEES SET "
			+ " FIRST_NAME=?, "
			+ " LAST_NAME=?, "
			+ " EMAIL=?, "
			+ " PHONE_NUMBER=?, "
			+ " HIRE_DATE=?, "
			+ " JOB_ID=?, "
			+ " SALARY=?, "
			+ " COMMISSION_PCT=?, "
			+ " MANAGER_ID=decode(?,0,null,?), "
			+ " DEPARTMENT_ID=? "
			+ "WHERE EMPLOYEE_ID=?";
	static final String SQL_UPDATE_BYDEPT = "UPDATE EMPLOYEES SET "
			+ " SALARY=?, "
			+ " COMMISSION_PCT=? "
			+ "WHERE DEPARTMENT_ID =? ";
	static final String SQL_DELETE = "DELETE FROM EMPLOYEES e "
			+ "WHERE EMPLOYEE_ID =?";
	static final String SQL_DELETE_BYDEPT = "DELETE FROM EMPLOYEES e "
			+ "WHERE DEPARTMENT_ID =?";
	
	//추가
	static final String SQL_JOB_ALL = "select * from jobs order by 1";
	static final String SQL_MANAGER_ALL="select employee_id, first_name "
			+ " from employees "
			+ " where employee_id in (select distinct manager_id from employees)";
	//static final String SQL_SELECT_BYEMAIL = "select * from employees where email = ?";
	static final String SQL_SELECT_BYEMAIL = "select count(*) from employees where email = ?";
	
	//1-1. 모든 jobs 조회(emplist.jsp select-option을 위한 추가사항)
	public List<JobVO> selectJobAll() {
		List<JobVO> joblist = new ArrayList<JobVO>();
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(SQL_JOB_ALL);
			while(rs.next()) {
				JobVO job = new JobVO(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
				joblist.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return joblist;
	}
	//1-2. 모든 manager 조회
	public Map<Integer,String> selectManagerAll() {
		Map<Integer,String> managerMap = new HashMap<Integer, String>();
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(SQL_MANAGER_ALL);
			while(rs.next()) {
				managerMap.put(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return managerMap;
	}
	//1. 모든 직원 조회
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(SQL_SELECT_ALL);
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}

	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO();
		emp.setCommission_pct(rs.getDouble("COMMISSION_PCT"));
		emp.setDepartment_id(rs.getInt("department_id"));
		emp.setEmail(rs.getString("EMAIL"));
		emp.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
		emp.setFirst_name(rs.getString("FIRST_NAME"));
		emp.setHire_date(rs.getDate("HIRE_DATE"));
		emp.setJob_id(rs.getString("JOB_ID"));
		emp.setLast_name(rs.getString("LAST_NAME"));
		emp.setManager_id(rs.getInt("MANAGER_ID"));
		emp.setPhone_number(rs.getString("PHONE_NUMBER"));
		emp.setSalary(rs.getDouble("SALARY"));
		return emp;
	}
	//2. 조건조회(특정부서)-department_id
	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_BYDEPT);
			pst.setInt(1, deptid); //첫번째 ?에 부서번호를 넣는다.
			rs = pst.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return emplist;
	}
	//3. 조건조회(특정매니저)-manager_id
	public List<EmpVO> selectByManager(int mid) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_BYMANAGER);
			pst.setInt(1, mid); //첫번째 ?에 부서번호를 넣는다.
			rs = pst.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return emplist;
	}
	//4. 조건조회(특정job)-job_id
	public List<EmpVO> selectByJob(String job_id) { //여긴 달라도 됨
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_BYJOB);
			pst.setString(1, job_id); //첫번째 ?에 부서번호를 넣는다.
			rs = pst.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return emplist;
	}
	//5. 조건조회(특정 department_id, job_id, salary>=, hire_date>=?)
	public List<EmpVO> selectByCondition(int deptid, String job_id, double sal, String hire_date) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_CONDITION);
			pst.setInt(1, deptid); 		 //첫번째 ?에 deptid를 넣는다.
			pst.setString(2, job_id); 	 //두번째 ?에 job_id를 넣는다.
			pst.setDouble(3, sal); 		 //세번째 ?에 sal를 넣는다.
			pst.setString(4, hire_date); //네번째 ?에 hire_date를 넣는다.
			rs = pst.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return emplist;
	}
	//6.특정 직원 1건 조회-employee_id
	public EmpVO selectById(int empid) {
		EmpVO emp = null;
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_BYID);
			pst.setInt(1, empid); 		 //첫번째 ?에 empid를 넣는다.
			rs = pst.executeQuery();
			while(rs.next()) {
				emp =  makeEmp(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return emp;
	}
	
	//(추가-이메일중복체크)SQL_SELECT_BYEMAIL
	public int selectByEmail(String email) {
		int result = 0;
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_BYEMAIL);
			pst.setString(1, email); 		 //첫번째 ?에 email를 넣는다.
			rs = pst.executeQuery();
			while(rs.next()) {
				result =  rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	/*public EmpVO selectByEmail(String email) {
		EmpVO emp = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BYEMAIL);
			pst.setString(1, email); 		 //첫번째 ?에 email를 넣는다.
			rs = pst.executeQuery();
			while(rs.next()) {
				emp =  makeEmp(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return emp;
	}*/
	
	//~DML
	//7. insert
	public int empInsert(EmpVO emp) {
		int result = 0;
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_INSERT);
			pst.setInt(1, emp.getEmployee_id()); 
			pst.setString(2, emp.getFirst_name()); 
			pst.setString(3, emp.getLast_name());
			pst.setString(4, emp.getEmail());
			pst.setString(5, emp.getPhone_number());
			pst.setDate(6, emp.getHire_date());
			pst.setString(7, emp.getJob_id());
			pst.setDouble(8, emp.getSalary());
			pst.setDouble(9, emp.getCommission_pct());
			pst.setInt(10, emp.getManager_id());
			pst.setInt(11, emp.getDepartment_id());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	//8. update(특정 직원 1건 employee_id=?)
	public int empUpdate(EmpVO emp) {
		int result = 0;
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_UPDATE);
			pst.setInt(12, emp.getEmployee_id()); 
			pst.setString(1, emp.getFirst_name()); 
			pst.setString(2, emp.getLast_name());
			pst.setString(3, emp.getEmail());
			pst.setString(4, emp.getPhone_number());
			pst.setDate(5, emp.getHire_date());
			pst.setString(6, emp.getJob_id());
			pst.setDouble(7, emp.getSalary());
			pst.setDouble(8, emp.getCommission_pct());
			pst.setInt(9, emp.getManager_id());
			pst.setInt(10, emp.getManager_id());
			pst.setInt(11, emp.getDepartment_id());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	//9. update(조건 department_id=?)
	public int empUpdateByDept(EmpVO emp, int deptid) {
		int result = 0;
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_UPDATE_BYDEPT);
			pst.setDouble(1, emp.getSalary());
			pst.setDouble(2, emp.getCommission_pct());
			pst.setInt(3, deptid);
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	//10. delete(특정 직원 1건 employee_id=?)SQL_DELETE
	public int empDelete(int empid) {
		int result = 0;
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_DELETE);
			pst.setInt(1, empid);
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	//11. delete(조건 department_id=?)
	public int empDeleteByDept(int deptid) {
		int result = 0;
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_DELETE_BYDEPT);
			pst.setInt(1, deptid);
			
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
}
