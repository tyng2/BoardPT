package com.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.comm.Common;
import com.board.dto.Board;
import com.board.dto.Notice;
import com.board.dto.Site;
import com.board.service.BoardService;
import com.board.service.NoticeService;
import com.board.service.SiteService;

@Controller
public class MainController {

	private Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private SiteService siteService;
	
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
		List<Board> boardList 	= boardService.getBoardsMain(BOARD_PAGE_SIZE, BOARD_START_ROW);
		List<Notice> noticeList 	= noticeService.getNoticeList(NOTICE_PAGE_SIZE, NOTICE_START_ROW);
		
		model.addAttribute("boardList"	, boardList);
		model.addAttribute("noticeList"	, noticeList);
		log.info("BoardList //{}"	, boardList);
		log.info("NoticeList //{}"	, noticeList);
		
		return "main";
	}
	
	@ResponseBody
	@RequestMapping("/siteList.do")
	public JSONArray siteList(HttpServletRequest request) {
		log.info("siteList");
		HttpSession session = request.getSession();
		String sessionID	= (String) session.getAttribute("sessionID");
		if (sessionID == null) return null;
		
		JSONArray jArr = siteService.getSiteList(sessionID);
		
		
		return jArr;
	}
	
	
	@RequestMapping("/siteProcess.do")
	public ResponseEntity<String> insertSite(@RequestParam Map<String, String> reqMap, HttpServletRequest request) {
		log.info("siteProcess");
		
		HttpSession session = request.getSession();
		String sessionID	= (String) session.getAttribute("sessionID");
		
		if (sessionID == null) {
			return Common.respEnt("로그인이 필요합니다.", "main.do");
		}
		
		String name	= reqMap.get("name");
		String url	= reqMap.get("url");
		log.info("name / url : {}", name + " / " + url);
		
		if (name.length() == 0 || url.length() == 0) {
			return Common.respEnt("내용을 입력해주세요.", null);
		}
		
		Site site = new Site();
		site.setUser_id(sessionID);
		site.setSite_name(name);
		site.setSite_url(url);
		
		siteService.insertSite(site);
		
		return null;
	}
	
	@RequestMapping("/siteDelete.do")
	public ResponseEntity<String> deleteSite(@RequestParam Map<String, String> reqMap, HttpServletRequest request){
		log.info("siteDelete");
		
		HttpSession session = request.getSession();
		String sessionID	= (String) session.getAttribute("sessionID");
		
		if (sessionID == null) return Common.respEnt("로그인 후 다시 시도해주세요.", null);
		
		int siteNum = 0;
		try {
			siteNum = Integer.parseInt(reqMap.get("siteNum"));
		} catch (NumberFormatException e) {
			
		}
		
		siteService.deleteSite(siteNum);
		
		return null;
	}
	
	
	
}
