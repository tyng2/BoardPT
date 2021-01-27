package com.board.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Common {

	private static Logger log = LoggerFactory.getLogger(Common.class);
	
	public static String null2space(String st) {
		String resultSt = "";
		if (st == null || "".equals(st)) {
			resultSt = null;
		} else {
			resultSt = st;
		}
		
		return resultSt;
	}
	
	
	/* message	== null : url로 리다이렉트 (url == null인 경우 잘못된 호출임)
	 * url		== null : alert 실행 후 이전페이지로 이동  */
	public static ResponseEntity<String> respEnt(String message, String url){
		log.info("<< ResponseEntity respEnt >>");
		
		HttpHeaders headers	= new HttpHeaders();
		
		if (message == null) {
			
			if (url == null) {
				return respEnt("잘못된 호출입니다.", null);
				
			} else {
				headers.add("Location", url);
				return new ResponseEntity<String>(headers, HttpStatus.FOUND);
				
			}
			
		}
		
		StringBuffer sb		= new StringBuffer();
		
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		sb.append("<script>");
		sb.append("alert('" + message + "');");
		
		if (url == null) {
			sb.append("history.back();");
		} else {
			sb.append("location.href='" + url + "';");
		}
		
		sb.append("</script>");
		log.info(sb.toString());
		
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		
	}
	
	
	
}
