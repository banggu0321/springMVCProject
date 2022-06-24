package com.kosta.myapp.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.BoardEmpVO;
import com.kosta.dto.BoardDTO;

@Repository
public class BoardDAOMybatis {
	
	@Autowired
	SqlSession session;
	
	Logger logger = LoggerFactory.getLogger(BoardDAOMybatis.class);
	
	static final String NAMESPACE = "net.gasan.board.";
	
	//b.bno, b.title, b.content, e.FIRST_NAME ||' '|| e.LAST_NAME fullname
	public List<BoardEmpVO> selectAllJoin() {
		logger.info("Mybatix:selectAllJoin()...");
		return session.selectList(NAMESPACE + "boardEmpInfo");
	}
	//1. 모든 게시판 조회
	public List<BoardDTO> selectAll() {
		logger.info("Mybatix:selectAll()...");
		return session.selectList(NAMESPACE + "selectAll");
	}
	
	//2. 조건조회 (해당 Bno)
	public BoardDTO selectByBoardNo(int post_no) {
		logger.info("Mybatix:selectAll()...");
		return session.selectOne(NAMESPACE + "selectById",post_no);
	}
	//3. 조건조회 (해당 writer)
	public List<BoardDTO> selectByWriter(int writer_id) {
		logger.info("Mybatix:selectByWriter()...");
		return session.selectList(NAMESPACE + "selectWriter",writer_id);
	}
	//4. 조건조회 (해당 title) 
	public List<BoardDTO> selectByTitle(String title) {
		logger.info("Mybatix:selectByTitle()...");
		return session.selectList(NAMESPACE + "selectTitle",title);
	}
	//5. 조건조회 (해당 regdate) 
	public List<BoardDTO> selectByRegDate(Date sdate, Date edate) {
		Map<String, Date> dataMap = new HashMap<>();
		dataMap.put("sdate", sdate);
		dataMap.put("edate", edate);
		logger.info("Mybatix:selectByRegDate()..." + dataMap);
		return session.selectList(NAMESPACE + "selectRegdate", dataMap);
	}
	//INSERT
	public int boardInsert(BoardDTO post) {
		logger.info("Mybatix:boardInsert()..." + post);
		return session.insert(NAMESPACE + "boardInsert", post);
	}
	//UPDATE
	public int boardUpdate(BoardDTO post) {
		logger.info("Mybatix:boardUpdate()..." + post);
		return session.update(NAMESPACE + "boardUpdate", post);
	}
	//DELETE
	public int boardDelete(int bno) {
		logger.info("Mybatix:boardDelete()..." + bno);
		return session.update(NAMESPACE + "boardDelete", bno);
	}
}
