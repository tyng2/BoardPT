package com.board.service;

import java.util.List;
import java.util.Map;

import com.board.dto.Notice;

public interface NoticeService {
	
	
	public int insertNotice(Notice notice);
	
	public List<Notice> getNoticeList(int pageSize, int startRow);
	
	public Map<String, Object> getNotices(Map<String, Integer> pageMap, Map<String, String> paramMap);
	
	public int getNoticeCount();
	
	public Notice getNoticeByNum(int num);
	
	public int updateNotice(Notice notice);
	
	public int deleteNotice(int num);
	
	public int countNoticeByNum(int num);

}
