package org.kosta.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	
	//Spring3�������� ��û ������ body���� �����ϰ��� �Ѵ�.
	@RequestMapping("/test1")
	@ResponseBody
	public String test1() {
		return "HEllo~~!";
	}
	
	//��ȸ list_get
	@RequestMapping(value="/boardList.do", method=RequestMethod.GET)
	public void boardRetrieve(Model model, HttpServletRequest request) {

		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		if(flashMap !=null ) {
			String message = (String)flashMap.get("resultMessage");
			model.addAttribute("resultMessage", message);
		}		
		List<BoardDTO> blist = bService.selectAll();
		logger.debug("��ȸ : " + blist.size() + "��");
		logger.info("��ȸ : " + blist.size() + "��");
		logger.warn("��ȸ : " + blist.size() + "��");
		logger.error("��ȸ : " + blist.size() + "��");
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
	public String boardInsertDB(BoardDTO b, RedirectAttributes attr, HttpServletRequest request) {
		logger.info("�Է� : " + b.toString());
		
		MultipartFile uploadfile = b.getPhotos(); 
		
		if (uploadfile == null) return "redirect:/board/boardList.do"; 
		
		String path = request.getSession().getServletContext().getRealPath("/resources/uploads"); 
		String fileName = uploadfile.getOriginalFilename(); 
		String fpath = path +"\\" + fileName ; 
		b.setPic(fileName); 
		
		try {
			File file = new File(fpath); 
			uploadfile.transferTo(file); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}		
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
	//title�� ��ȸ
	@RequestMapping("/titleSearch.do")
	public String titleSearch(String title, Model model) {
		List<BoardDTO> blist = bService.selectByTitle(title==null||title==""?"%":"%"+title+"%");
		model.addAttribute("boardDatas", blist);
		return "board/boardListFrame";
	}
	//writer�� ��ȸ
	@RequestMapping("/writerSearch.do")
	public String writerSearch(int writer, Model model) {
		List<BoardDTO> blist = bService.selectByWriter(writer);
		model.addAttribute("boardDatas", blist);
		return "board/boardListFrame";
	}
	//date�� ��ȸ
	@RequestMapping("/dateSearch.do")
	public String dateSearch(Date sdate, Date edate, Model model) {
		List<BoardDTO> blist = bService.selectByRegDate(sdate, edate);
		model.addAttribute("boardDatas", blist);
		return "board/boardListFrame";
	}	
}
