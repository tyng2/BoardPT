package com.board.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class User {
//	POTL_USER
	
	// ID, PW, 이름, Email, 주소
	private String user_id, user_pass, user_name, user_mail, user_addr;
	
	// 회원 등록일
	private Timestamp user_date;

}
