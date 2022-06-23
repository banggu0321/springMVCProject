package com.kosta.myapp.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.DeptDTO;

@Repository		//<bean id=��testDAO�� class=��com.kosta.myapp.model.TestDAO��> ��ü ������
public class TestDAO {
	
	@Autowired		//������� ��ü�� �о��! DataSource ds = new 
	DataSource ds;
	
	// 2. Ư���μ� ��ȸ(�μ��ڵ�� ��ȸ)
	public DeptDTO selectById(int deptid) {
		DeptDTO dept = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from departments where department_id = " + deptid;
		try {
			conn = ds.getConnection();
			st = conn.createStatement(); // ��θ���� try/catch�ʿ�
			rs = st.executeQuery(sql); // sql�б�
			if (rs.next()) { // �Ǵ� ��ȸ
				dept = new DeptDTO(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}
}
