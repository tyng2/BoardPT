package com.board.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LoginReg {
//	LOGN_REGI

	// 로그인 로그 번호
	private int logn_numb;
	
	// 로그인 ID, 로그인 IP
	private String logn_id, logn_ip;
	
	// 로그인 일자
	private Timestamp logn_date;
	

}
