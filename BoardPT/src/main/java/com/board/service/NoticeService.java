package com.board.service;

import java.util.List;
import java.util.Map;

import com.board.dto.NoticeDto;

public interface NoticeService {
	
	
	public int insertNotice(NoticeDto noticeVO);
	
	public List<NoticeDto> getNoticeList(int pageSize, int startRow);
	
	public Map<String, Object> getNotices(Map<String, Integer> pageMap, Map<String, String> paramMap);
	
	public int getNoticeCount();
	
	public NoticeDto getNoticeByNum(int num);
	
	public int updateNotice(NoticeDto noticeVO);
	
	public int deleteNotice(int num);
	
	public int countNoticeByNum(int num);

}
