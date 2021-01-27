package com.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.dto.BoardDto;
import com.board.dto.NoticeDto;
import com.board.service.BoardService;
import com.board.service.NoticeService;

@Controller
public class MainController {

	private Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private NoticeService noticeService;
	
	private final static int BOARD_PAGE_SIZE = 5, BOARD_START_ROW = 0;
	private final static int NOTICE_PAGE_SIZE = 3, NOTICE_START_ROW = 0;
	// PAGE_SIZE : 한 페이지에 보여지는 게시글의 사이즈
	// START_ROW : 몇 번째 항목에서 시작하는지. 첫 항목이 0
	
	@RequestMapping("/")
	public String index() {
		log.info("index");
		return "redirect:/main.do";
	}
	
	@RequestMapping("/main.do")
	public String main(Model model) {
		log.info("main.do");
		List<BoardDto> boardList 	= boardService.getBoardsMain(BOARD_PAGE_SIZE, BOARD_START_ROW);
		List<NoticeDto> noticeList 	= noticeService.getNoticeList(NOTICE_PAGE_SIZE, NOTICE_START_ROW);
		
		model.addAttribute("boardList"	, boardList);
		model.addAttribute("noticeList"	, noticeList);
		log.info("BoardList //{}"	, boardList);
		log.info("NoticeList //{}"	, noticeList);
		
		return "main";
	}
	
	
}
