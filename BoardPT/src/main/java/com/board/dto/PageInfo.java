package com.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {
	
	private int countAllData, pageBlockSize;
	// 총 데이터 개수,  페이지블록 크기

	private int pageNow;
	// 현재 페이지
	
	private int pageStart, pageEnd;
	// 현재 페이지블록 시작값, 끝값
	
	private int pageMax;
	// 페이지블록 최대값
	
}
