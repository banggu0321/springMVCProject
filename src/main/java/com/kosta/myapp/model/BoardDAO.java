package com.kosta.myapp.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.BoardEmpVO;
import com.kosta.dto.BoardDTO;
import com.kosta.util.DBUtil;

@Repository
public class BoardDAO {
	
	@Autowired
	DataSource ds;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	static final String SQL_SELECT_ALL = "SELECT * FROM tbl_board ORDER BY 1";
	static final String SQL_SELECT_POST = "SELECT * FROM tbl_board WHERE bno =?";
	static final String SQL_SELECT_WRITER = "SELECT * FROM TBL_BOARD WHERE writer =?";
	static final String SQL_SELECT_TITLE = "SELECT * FROM TBL_BOARD WHERE title=?";
	static final String SQL_SELECT_REGDATE = "SELECT * FROM TBL_BOARD WHERE regdate between ? and ?";
	//static final String SQL_INSERT = "INSERT INTO tbl_board values(seq_bno.nextval,?,?,?,sysdate,sysdate,null)";
	static final String SQL_INSERT = "INSERT INTO tbl_board values(seq_bno.nextval,?,?,?,sysdate,sysdate,?)";
	static final String SQL_UPDATE = "UPDATE tbl_board SET title =?, content=?, updatedate=sysdate WHERE bno=?";
	static final String SQL_DELETE = "DELETE FROM tbl_board WHERE bno=?";
	static final String SQL_BOARDEMP = "SELECT b.bno, b.title, b.content, e.FIRST_NAME ||' '|| e.LAST_NAME fullname"
			+ "FROM TBL_BOARD b JOIN EMPLOYEES e ON (b.writer = e.EMPLOYEE_ID)";
	
	//b.bno, b.title, b.content, e.FIRST_NAME ||' '|| e.LAST_NAME fullname
	public List<BoardEmpVO> selectAllJoin() {
		List<BoardEmpVO> postlist = new ArrayList<>();
		BoardEmpVO board = null;
		try {
			conn = ds.getConnection();//CP이용해서 Connection얻기
			pst = conn.prepareStatement(SQL_BOARDEMP);
			rs = pst.executeQuery();
			while(rs.next()) {
				board = new BoardEmpVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				postlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return postlist;
	}
	//1. 모든 게시판 조회
	public List<BoardDTO> selectAll() {
		List<BoardDTO> postlist = new ArrayList<BoardDTO>();
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_ALL);
			rs = pst.executeQuery();
			while(rs.next()) {
				postlist.add(makepost(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return postlist;
	}
	private BoardDTO makepost(ResultSet rs) throws SQLException {
		BoardDTO post = new BoardDTO();
		post.setBno(rs.getInt("BNO"));
		post.setContent(rs.getString("CONTENT"));
		post.setRegdate(rs.getDate("REGDATE"));
		post.setTitle(rs.getString("TITLE"));
		post.setUpdatedate(rs.getDate("UPDATEDATE"));
		post.setWriter(rs.getInt("WRITER"));
		post.setPic(rs.getString("PIC"));
		return post;
	}
	//2. 조건조회 (해당 Bno)
		public BoardDTO selectByBoardNo(int post_no) {
			BoardDTO post = null;
			try {
				conn = ds.getConnection();
				pst = conn.prepareStatement(SQL_SELECT_POST);
				pst.setInt(1, post_no);
				rs = pst.executeQuery();
				while(rs.next()) {
					post= makepost(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.dbClose(rs, pst, conn);
			}
			return post;
		}
	//3. 조건조회 (해당 writer)
	public List<BoardDTO> selectByWriter(int writer_id) {
		List<BoardDTO> postlist = new ArrayList<BoardDTO>();
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_WRITER);
			pst.setInt(1, writer_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				postlist.add(makepost(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return postlist;
	}
	//4. 조건조회 (해당 title) SQL_SELECT_TITLE
	public List<BoardDTO> selectByTitle(String title) {
		List<BoardDTO> postlist = new ArrayList<BoardDTO>();
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_TITLE);
			pst.setString(1, title);
			rs = pst.executeQuery();
			while(rs.next()) {
				postlist.add(makepost(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return postlist;
	}
	//5. 조건조회 (해당 regdate) SQL_SELECT_REGDATE between ? and ?
	public List<BoardDTO> selectByRegDate(Date sdate, Date edate) {
		List<BoardDTO> postlist = new ArrayList<BoardDTO>();
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_REGDATE);
			pst.setDate(1, sdate);
			pst.setDate(2, edate);
			rs = pst.executeQuery();
			while(rs.next()) {
				postlist.add(makepost(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return postlist;
	}
	//INSERT
	public int boardInsert(BoardDTO post) {
		int result = 0;
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_INSERT);
			pst.setString(1, post.getTitle());
			pst.setString(2, post.getContent());
			pst.setInt(3, post.getWriter());
			pst.setString(4, post.getPic());

			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	//UPDATE
	public int boardUpdate(BoardDTO post) {
		int result = 0;
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_UPDATE);
			pst.setString(1, post.getTitle());
			pst.setString(2, post.getContent());
			pst.setInt(3, post.getBno());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
	//DELETE
	public int boardDelete(int bno) {
		int result = 0;
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_DELETE);
			pst.setInt(1, bno);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		return result;
	}
}
