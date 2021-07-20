package com.board.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.board.dto.PageInfo;

@Component
public class PageSet {
	
	private static int LIMIT_SIZE, PAGE_BLOCK_SIZE;
	// 한 페이지당 로그 개수, 페이지 블록 개수
	
	@Value("${page.limit}")
	private void setLimit(int page_limit) {
		LIMIT_SIZE = page_limit;
	}
	
	@Value("${page.block}")
	private void setBlockSize(int page_block) {
		PAGE_BLOCK_SIZE = page_block;
	}
	
	public static PageInfo getPageData(int page, int countAllData) {
		
		int pageMax		= countAllData / LIMIT_SIZE + ((countAllData % LIMIT_SIZE != 0) ? 1 : 0);
		int pageStart	= (page / PAGE_BLOCK_SIZE - ((page % PAGE_BLOCK_SIZE == 0) ? 1 : 0)) * PAGE_BLOCK_SIZE + 1;;
		int pageEnd		= pageStart + PAGE_BLOCK_SIZE - 1;
		pageEnd			= (pageEnd > pageMax) ? pageMax : pageEnd;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCountAllData(countAllData);
		pageInfo.setPageBlockSize(PAGE_BLOCK_SIZE);
		pageInfo.setPageNow(page);
		pageInfo.setPageStart(pageStart);
		pageInfo.setPageEnd(pageEnd);
		pageInfo.setPageMax(pageMax);
		
		return pageInfo;
	}

}
