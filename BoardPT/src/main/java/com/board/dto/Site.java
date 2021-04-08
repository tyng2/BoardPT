package com.board.dto;

import lombok.Data;

@Data
public class Site {
//	POTL_SITE
	
	// 사이트 등록번호
	private int site_numb;
	
	// 사이트 이름, 사이트 URL, 해당 사이트 등록 회원 ID
	private String site_name, site_url, user_id;

}
