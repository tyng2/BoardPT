package com.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.dto.Notice;
import com.board.service.NoticeService;

@Controller
public class NoticeController {
	private final static int PAGE_SIZE = 4, PAGE_BLOCK_SIZE = 7, HIT_PAGE_SIZE = 5;
	// PAGE_SIZE : 한 페이지의 게시글 개수, PAGE_BLOCK_SIZE : 한 페이지 블록을 구성하는 페이지 개수
	// HIT_PAGE_SIZE : 조회수 순으로 정렬한 게시글이 페이지에 노출되는 개수
	
	private Logger log = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/notice.do")
	public String notice(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("<< Controller / noticeList >>");
		
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("pageSize", PAGE_SIZE);
		pageMap.put("pageBlockSize", PAGE_BLOCK_SIZE);
		pageMap.put("pageSizeHit", HIT_PAGE_SIZE);
		
		Map<String, Object> result = noticeService.getNotices(pageMap, paramMap);
		List<Notice> noticeList = (List<Notice>) result.get("noticeList");
		Map<String, Integer> pageInfoMap = (Map<String, Integer>) result.get("pageInfoMap");
		
		model.addAttribute("pageInfoMap",pageInfoMap);
		model.addAttribute("noticeList", noticeList);
		
		
		return "notice/notice";
	}
	
	
	
}
