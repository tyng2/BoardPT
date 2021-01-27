package com.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.comm.Common;

@Controller
public class TestController {
	
	private Logger log = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/test")
	public ResponseEntity<String> test1() {
		log.info("<< test!! >>");
		System.out.println('d');
		
		ResponseEntity<String> res = null;
		
//		res = Common.respEnt(null, null);
		res = Common.respEnt("skskdkwkek", "/login.do");
		
		return res;
	}
	
	@RequestMapping("/t")
	@ResponseBody
	public String indexTest() {
		log.info("test Index");
		
		return "SDFSFSDdddd";
	}
	
}
