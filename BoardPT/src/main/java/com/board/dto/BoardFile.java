package com.board.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardFile {
//	BORD_FILE
	
	// 파일번호, 게시글번호
	private int file_numb, bord_numb;
	
	// 원본 파일 이름, 저장 파일 이름
	private String file_olnm, file_svnm;
	
	// 파일 업로드 날짜
	private Timestamp file_date;

}
