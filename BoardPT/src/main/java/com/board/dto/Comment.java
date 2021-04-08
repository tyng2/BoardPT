package com.board.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Comment {
//	BORD_COMM
	
	// 댓글번호, 게시글번호
	private int comm_numb, bord_numb;
	
	// 회원ID, 댓글 내용
	private String user_id, comm_cont;

	// 댓글 등록일
	private Timestamp comm_date;
	
}
