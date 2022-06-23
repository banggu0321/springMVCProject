package org.kosta.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.dto.BoardDTO;
import com.kosta.myapp.model.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService bService;
	
	//조회 list_get
	@RequestMapping(value="/boardList.do", method=RequestMethod.GET)
	public void boardRetrieve(Model model, HttpServletRequest request) {
		model.addAttribute("boardDatas", bService.selectAll());
		//return "board/boardList";
	}
	//입력 페이지 보여주기
	@RequestMapping(value="/boardInsert.do", method=RequestMethod.GET)
	public String boardInsertView() {
		return "board/boardInsert";
	}
	//insert_post
	@RequestMapping(value="/boardInsert.do", method=RequestMethod.POST)
	public String boardInsertDB(BoardDTO b) {
		bService.boardInsert(b);
		return "redirect:/board/boardList.do";
	}
	//상세 페이지 보여주기
	@RequestMapping(value="/boardDetail.do", method=RequestMethod.GET)
	public String boardUpdateView(int boardid, Model model) {
		model.addAttribute("board", bService.selectByBoardNo(boardid));
		return "board/boardDetail";
	}
	//update_post
	@RequestMapping(value="/boardUpdate.do", method=RequestMethod.POST)
	public String boardUpdateDB(BoardDTO b) {
		bService.boardUpdate(b);
		return "redirect:/board/boardList.do";
	}
	//삭제
	@RequestMapping(value="/boardDelete.do", method=RequestMethod.GET)
	public String boardDeleteGet(int bno) {
		bService.boardDelete(bno);
		return "redirect:/board/boardList.do";
	}
		
	
}
