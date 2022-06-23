package com.kosta.myapp.model;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.dto.BoardDTO;
import com.kosta.dto.BoardEmpVO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	//b.bno, b.title, b.content, e.FIRST_NAME ||' '|| e.LAST_NAME fullname
	public List<BoardEmpVO> selectAllJoin() {
		return boardDAO.selectAllJoin();
	}
	//1. 모든 게시판 조회
	public List<BoardDTO> selectAll() {
		return boardDAO.selectAll();
	}
	//2. 조건조회 (해당 Bno)
	public BoardDTO selectByBoardNo(int post_no) {
		return boardDAO.selectByBoardNo(post_no);
	}
	//3. 조건조회 (해당 writer)
	public List<BoardDTO> selectByWriter(int writer_id) {
		return boardDAO.selectByWriter(writer_id);
	}
	//4. 조건조회 (해당 title) SQL_SELECT_TITLE
	public List<BoardDTO> selectByTitle(String title) {
		return boardDAO.selectByTitle(title);
	}
	//5. 조건조회 (해당 regdate) SQL_SELECT_REGDATE between ? and ?
	public List<BoardDTO> selectByRegDate(Date sdate, Date edate) {
		return boardDAO.selectByRegDate(sdate, edate);
	}
	//INSERT
	public int boardInsert(BoardDTO post) {
		return boardDAO.boardInsert(post);
	}
	//UPDATE
	public int boardUpdate(BoardDTO post) {
		return boardDAO.boardUpdate(post);
	}
	//DELETE
	public int boardDelete(int writer_id) {
		return boardDAO.boardDelete(writer_id);
	}
}
