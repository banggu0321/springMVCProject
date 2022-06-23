package com.kosta.myapp.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.DeptDTO;

@Repository		//<bean id=”testDAO” class=”com.kosta.myapp.model.TestDAO”> 객체 만들어라
public class TestDAO {
	
	@Autowired		//만들어진 객체를 읽어라! DataSource ds = new 
	DataSource ds;
	
	// 2. 특정부서 조회(부서코드로 조회)
	public DeptDTO selectById(int deptid) {
		DeptDTO dept = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from departments where department_id = " + deptid;
		try {
			conn = ds.getConnection();
			st = conn.createStatement(); // 통로만들기 try/catch필요
			rs = st.executeQuery(sql); // sql읽기
			if (rs.next()) { // 건당 조회
				dept = new DeptDTO(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}
}
