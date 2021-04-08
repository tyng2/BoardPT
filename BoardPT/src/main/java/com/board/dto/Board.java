package com.board.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Board {
//	POTL_BORD
	
	// 게시글번호
	private int bord_numb;
	
	// 카테고리, 제목, 내용, 작성자 ID
	private String bord_catg, bord_titl, bord_cont, user_id;
	
	// 게시글 등록일
	private Timestamp bord_date;
	
	// 조회수, 답글참조, 답글레벨, 답글순서
	private int bord_hitc, bord_refr, bord_levl, bord_seqn;
	
	// 게시글 작성 IP
	private String bord_wrip;
	
	// 파일 개수, 댓글 개수
	private int file_cont, comm_cont;

}
