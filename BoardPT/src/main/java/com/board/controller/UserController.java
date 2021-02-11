package com.board.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.comm.Common;
import com.board.dto.UserDto;
import com.board.service.UserService;

@Controller
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
//	@Autowired
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/join.do")
	public String joinForm() {
		log.info("join, GET!");
		return "user/join";
	}
	
	@PostMapping("/join.do")
	public ResponseEntity<String> joinProcess(@RequestParam Map<String, String> paramMap) {
		log.info("join, POST!");
		
		ResponseEntity<String> res = null;
		
		String id 		= paramMap.get("id");
//		String pw 		= paramMap.get("pw");
		String pw 		= passwordEncoder.encode(paramMap.get("pw"));
		String name 	= paramMap.get("name");
		String email 	= paramMap.get("email");
		String address 	= paramMap.get("address");
		
		UserDto user = new UserDto(id, pw, name, email, address);
		
		int check = userService.insertUser(user);
		System.out.println("check:"+check);
		
		if (check == 0) {
			res = Common.respEnt("아이디가 중복이거나, 아이디/비밀번호를 제대로 입력하지 않았습니다.", null);
			
		} else {
			res = Common.respEnt("회원가입이 완료되었습니다.", "/login.do");
			
		}
		
		return res;
	}
	
	
	@GetMapping("/login.do")
	public String login() {
		log.info("login, GET");
		return "user/login";
	}
	
//	@PostMapping("/loginProcess.do")
	public ResponseEntity<String> loginProcess(@RequestParam Map<String, String> paramMap, HttpServletRequest request, HttpServletResponse response) {
		log.info("<< LoginProcess, POST >>");
		
		ResponseEntity<String> res = null;
		
		String id 		= paramMap.get("id");
		String pw 		= paramMap.get("pw");
		String loginChk	= paramMap.get("loginChk");
		System.out.println("loginChk: " + loginChk);
		
		int check 		= userService.loginCheck(id, pw);
		System.out.println("Check = " + check);
		
		if (check == 1) {	// 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("sessionID", id);
			
			if (loginChk != null) {
				Cookie c = new Cookie("cookieID", id);
				c.setMaxAge(60*30);
				c.setPath("/");
				response.addCookie(c);
				log.info("cookie ID : " + id);
			}
			
			res = Common.respEnt(null, "/");
			
		} else {			// 로그인 실패
			System.out.println("로그인실패!"+check);
			res = Common.respEnt("아이디나 비밀번호가 맞지 않습니다.", null);
		}
		
		return res;
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		log.info("<< LogoutProcess >>");
		HttpSession session = request.getSession();
		session.invalidate();
		
		Cookie[] c = request.getCookies();
		if (c != null) {
			for (Cookie ck : c) {
				if ("cookieID".equals(ck.getName())) {
					ck.setMaxAge(0);
					ck.setPath("/");
					response.addCookie(ck);
				}
			}
		}
		
		return "redirect:/";
	}
	
	
	
	
}
