package com.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.board.comm.Common;
import com.board.comm.cmmFile;
import com.board.dto.Board;
import com.board.dto.Comment;
import com.board.service.BoardService;

@Controller
public class BoardController {
	private final static int PAGE_SIZE = 8, PAGE_BLOCK_SIZE = 5, HIT_PAGE_SIZE = 5;
	// PAGE_SIZE : 한 페이지의 게시글 개수, PAGE_BLOCK_SIZE : 한 페이지 블록을 구성하는 페이지 개수
	// HIT_PAGE_SIZE : 조회수 순으로 정렬한 게시글이 페이지에 노출되는 개수
	
	private final static int MAX_FILE_SIZE = 1024 * 1024 * 50;	// 최대 파일 크기 : 50MB
	
	@Value("${file.path}")
	private String filePath;	// 파일 저장 경로
	
	
	private Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board.do")
	public String boardList(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("<< Controller / boardList >>");
		
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("pageSize"		, PAGE_SIZE);
		pageMap.put("pageBlockSize"	, PAGE_BLOCK_SIZE);
		pageMap.put("pageSizeHit"	, HIT_PAGE_SIZE);
		
		String bord_catg	= paramMap.get("bord_catg");
		bord_catg 			= Common.null2space(bord_catg);			// 공백이 들어와도 null로 통일
		paramMap.put("bord_catg", bord_catg);
		
		Map<String, Object> result			= boardService.getBoards(pageMap, paramMap);
		List<Board> boardList				= (List<Board>) result.get("boardList");
		Map<String, Integer> pageInfoMap	= (Map<String, Integer>) result.get("pageInfoMap");
		
		log.info("BOARDLIST :: {}", boardList);
		List<Board> hitList = boardService.getBoardsOrderByHit(HIT_PAGE_SIZE);
		
		model.addAttribute("pageInfoMap"	, pageInfoMap);
		model.addAttribute("list"			, boardList);
		model.addAttribute("search"			, paramMap.get("search"));
		model.addAttribute("hitList"		, hitList);
		
		if (bord_catg == null) {
			return "board/board";
		} else {
			return "board/boardCategory";
		}
	}
	
	@GetMapping("/boardWrite.do")
	public String boardWrite(Model model, HttpServletRequest request) {
		log.info("<< GET, boardWrite.do >>");
		
		HttpSession session = request.getSession();
		Object sessionID	= session.getAttribute("sessionID");
		
		log.info("sessionID : " + sessionID);
		request.setAttribute("sessionID", sessionID);
		if (sessionID == null) {
			return "redirect:/board.do";
		}
		
		return "board/write";
	}
	
	
	@PostMapping("/boardWriteProcess.do")
	public ResponseEntity<String> boardWriteProcess(@RequestParam Map<String, String> paramMap, @RequestParam(required = false, name = "bbs_file") MultipartFile[] mFile, HttpServletRequest request, Model model) throws IOException {
		log.info("<< POST, boardWriteProcess.do >> : {}", paramMap);
		
		ResponseEntity<String> res = null;
		
		HttpSession session = request.getSession();
		String sessionID	= (String) session.getAttribute("sessionID");
		
		if (sessionID == null) {
			res = Common.respEnt("로그인 후 작성가능합니다.", "/login.do");
			return res;
		}
		
		System.out.println("file/"+mFile);
		int cnt 				= 0;
		List<String> svnmList 	= new ArrayList<>();  
		for (MultipartFile multipartFile : mFile) {
			
			if (multipartFile.isEmpty()) {
				System.out.println("!@#@!#!@#!@#!@#@!$EDSFDS");
				continue;
				
			} else {
				System.out.println("getName:"				+multipartFile.getName());
				System.out.println("getOriginalFilename:"	+multipartFile.getOriginalFilename());
				System.out.println("getResource:"			+multipartFile.getResource());
				System.out.println("getBytes:"				+multipartFile.getBytes());
				System.out.println("getSize:"				+multipartFile.getSize());
				System.out.println("getContentType:"		+multipartFile.getContentType());
				
				svnmList.add(cmmFile.fileUpload(multipartFile));
				cnt++;
				
			}
		}
		System.out.println("COUNT "+cnt);
		
		
		
		/*
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdir();
			log.info(filePath + " 디렉터리 생성!");
		}
		*/
		
		Board board = new Board();
		
		board.setUser_id(sessionID);
		board.setBord_catg(paramMap.get("categy"));
		board.setBord_titl(paramMap.get("title"));
		board.setBord_cont(paramMap.get("content"));
		board.setBord_wrip(request.getRemoteAddr());
		
		/*
		MultipartRequest mRequest = new MultipartRequest(request, filePath, MAX_FILE_SIZE, "UTF-8", new DefaultFileRenamePolicy());
		
		
		List<BbsFileDto> fileList = new ArrayList<BbsFileDto>();
		
		BbsFileDto bbsFileVO = null;
		String name[] 		= new String[100];
		String changeName[] = new String[100];
		File file[] 		= new File[100];
		
		for (int i = 1; i < 50; i++) {
			name[i] = mRequest.getOriginalFileName("bbs_file" + i);
			if (name[i] == null) {
				break;
			}
			file[i] 		= mRequest.getFile("bbs_file" + i);
			changeName[i] 	= file[i].getName();
			
			log.info("FILE PATH : " + filePath);
			log.info("Name[" + i + "] : " + name[i]);
			log.info("ChangeName[" + i + "] : " + changeName[i]);
			
			bbsFileVO = new BbsFileDto();
			bbsFileVO.setOrgl_file_nm(name[i]);
			bbsFileVO.setSave_file_nm(changeName[i]);
			
			fileList.add(bbsFileVO);
			log.info("BbsFile/" + bbsFileVO);
		}
		 */
		
		
		int count = boardService.insertBoard(board);
		
		System.out.println("=-=-=-=-=-=-=-=-=");
		for (String string : svnmList) {
			System.out.println(string);
			
		}
		
		log.info("INSERT BOARD :: {} // {}", count, board);
		
		res = Common.respEnt(null, "/board.do");
		return res;
	}
	
	@GetMapping("/boardView.do")
	public String boardView(@RequestParam Map<String, String> paramMap, HttpServletRequest request, Model model) {
		log.info("<< GET, boardView.do >> :: {}", paramMap);
		
		HttpSession session	= request.getSession();
		String sessionID 	= (String) session.getAttribute("sessionID");
		log.info("sessionID : {}", sessionID);
		String bord_numb 	= paramMap.get("bord_numb");
		int numInt = 0; 
		try {
			numInt = Integer.parseInt(bord_numb);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "redirect:/board.do";
		}
		
		Map<String, Object> resultMap = boardService.getBoardByNum(numInt, sessionID);
		log.info("boardView : {}", resultMap);
		
		model.addAttribute("board"	, resultMap.get("resultBoard"));
		model.addAttribute("comment", resultMap.get("commentList"));
		model.addAttribute("files"	, resultMap.get("fileList"));
		
		return "board/boardView";
	}
	
	@ResponseBody
	@RequestMapping("/boardFileProcess.do")
	public String boardFile(@RequestParam String fileId, HttpServletResponse response) {
		log.info("<< boardFile >> {}", fileId);
		log.info("FilePath {}", filePath);
		
		log.info("아직 파일 다운로드 진행되지 않음!");
		
		/*
		BbsFileDto file = boardService.selectFileByFileId(fileId);
		
		String fileName = file.getOrgl_file_nm();
		
		InputStream is	= null;
		OutputStream os	= null;
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8"); // 파일 한글 깨짐 방지
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attatchment; filename=" + fileName);
			
			byte b[] = new byte[1024];
			is = new FileInputStream(filePath + file.getSave_file_nm());
			os = response.getOutputStream();
			
			int data = 0;
			while ((data = is.read(b, 0, b.length)) != -1) {
				os.write(b, 0, data);
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		*/
		
		
		return "";
	}
	
	
	
	/* Comment */
	
	@ResponseBody
	@GetMapping("/comment.do")
	public JSONArray boardComment(@RequestParam int bord_numb, Model model) {
		log.info("<< GET, comment.do >> {}", bord_numb);
		
		JSONArray jArr = null;
		jArr = commentToJsonArr(boardService.getCommentByBoardNum(bord_numb));
		log.info(jArr.toString());
		return jArr;
	}
	
	public JSONArray commentToJsonArr(List<Comment> list) {
		JSONArray jArr	= new JSONArray();
		JSONObject jObj	= null;
		
		for (Comment comm : list) {
			jObj = new JSONObject();
//			jObj.put("commentId"	, comm.getComm_numb());
//			jObj.put("num"			, comm.getBord_numb());
//			jObj.put("id"			, comm.getUser_id());
//			jObj.put("content"		, comm.getComm_cont());
//			jObj.put("reg_date"		, comm.getComm_date());
			
			jObj.put("comm_numb"	, comm.getComm_numb());
			jObj.put("bord_numb"	, comm.getBord_numb());
			jObj.put("user_id"		, comm.getUser_id());
			jObj.put("comm_cont"	, comm.getComm_cont());
			jObj.put("comm_date"	, comm.getComm_date());
			
			jArr.add(jObj);
		}
		
		return jArr;
	}
	
	@ResponseBody
	@PostMapping("/insertComment.do")
	public String insertComment(@RequestParam Map<String, String> paramMap, HttpServletRequest request) {
		log.info("<< POST, insertComment.do >> :: {}", paramMap);
		
		HttpSession session	= request.getSession();
		String id 			= (String) session.getAttribute("sessionID");
		
		if (id == null) {
			id = "Unknown";
		}
		
		int bord_numb 		= Integer.parseInt(paramMap.get("bord_numb"));
		String comm_cont 	= paramMap.get("comm_cont");
		
		Comment comment = new Comment();
		comment.setBord_numb(bord_numb);
		comment.setUser_id(id);
		comment.setComm_cont(comm_cont);
		
		boardService.insertComment(comment);
		
		return "";
	}
	
	@ResponseBody
	@RequestMapping("/deleteComment.do")
	public String deleteComment(@RequestParam int comm_numb) {
		log.info("<< deleteComment.do {}>>", comm_numb);
		
		boardService.deleteComment(comm_numb);
		
		return "";
	}
	
	
}
