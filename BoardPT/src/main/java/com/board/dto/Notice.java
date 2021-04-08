package com.board.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Notice {
//	POTL_NOTI
	
	// 공지번호
	private int noti_numb;
	
	// 공지 제목, 공지 내용, 작성자 ID
	private String noti_titl, noti_cont, user_id;
	
	// 공지 등록일
	private Timestamp noti_date;

}
