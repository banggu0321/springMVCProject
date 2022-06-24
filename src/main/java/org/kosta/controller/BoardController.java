package org.kosta.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.dto.BoardDTO;
import com.kosta.myapp.model.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService bService;
	
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//��ȸ list_get
	@RequestMapping(value="/boardList.do", method=RequestMethod.GET)
	public void boardRetrieve(Model model, HttpServletRequest request) {

		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		if(flashMap !=null ) {
			String message = (String)flashMap.get("resultMessage");
			model.addAttribute("resultMessage", message);
		}		
		List<BoardDTO> blist = bService.selectAll();
		logger.info("��ȸ : " + blist.size() + "��");
		model.addAttribute("boardDatas", blist);
		//return "board/boardList";
	}
	//�Է� ������ �����ֱ�
	@RequestMapping(value="/boardInsert.do", method=RequestMethod.GET)
	public String boardInsertView() {
		logger.info("�Է� Form���̱�");
		return "board/boardInsert";
	}
	//insert_post
	@RequestMapping(value="/boardInsert.do", method=RequestMethod.POST)
	public String boardInsertDB(BoardDTO b, RedirectAttributes attr) {
		logger.info("�Է� : " + b.toString());
		int result = bService.boardInsert(b);
		attr.addFlashAttribute("resultMessage", result+"�� �Է�");
		return "redirect:/board/boardList.do";
	}
	//�� ������ �����ֱ�
	@RequestMapping(value="/boardDetail.do", method=RequestMethod.GET)
	public String boardUpdateView(int boardid, Model model) {
		logger.info("�󼼺��� : " + boardid);
		model.addAttribute("board", bService.selectByBoardNo(boardid));
		return "board/boardDetail";
	}
	//update_post
	@RequestMapping(value="/boardUpdate.do", method=RequestMethod.POST)
	public String boardUpdateDB(BoardDTO b, RedirectAttributes attr) {
		logger.info("���� : " + b);
		int result = bService.boardUpdate(b);
		attr.addFlashAttribute("resultMessage", b.getBno() + "���� ������");
		return "redirect:/board/boardList.do";
	}
	//����
	@RequestMapping(value="/boardDelete.do", method=RequestMethod.GET)
	public String boardDeleteGet(int bno, RedirectAttributes attr) {
		logger.info("���� : " + bno);
		int result = bService.boardDelete(bno);
		attr.addFlashAttribute("resultMessage", bno + "���� ������");
		return "redirect:/board/boardList.do";
	}
		
	
}
