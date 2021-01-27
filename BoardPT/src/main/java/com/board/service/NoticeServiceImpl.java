package com.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dto.NoticeDto;
import com.board.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	private Logger log = LoggerFactory.getLogger(NoticeServiceImpl.class);
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public int insertNotice(NoticeDto noticeVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<NoticeDto> getNoticeList(int pageSize, int startRow) {
		log.info("<< NoticeServiceImpl // getNoticeList >>");
		
		return noticeMapper.getNotices(pageSize, startRow);
	}

	@Override
	public Map<String, Object> getNotices(Map<String, Integer> pageMap, Map<String, String> paramMap) {
		log.info("<< NoticeServiceImpl // getNotices >>");
		Map<String, Object> result = new HashMap<>();
		
		int pageSize = pageMap.get("pageSize");					// 한 페이지의 게시글 개수
		int pageBlockSize = pageMap.get("pageBlockSize");		// 한 페이지 블록을 구성하는 페이지 개수
		
		String strPageNum = (String) paramMap.get("pageNum");	// 시작 페이지 번호
		
		strPageNum = (strPageNum == null | "".equals(strPageNum)) ? "1" : strPageNum;	// 시작 페이지 기본값 1
		int pageNum = Integer.parseInt(strPageNum); 	// 페이지 번호
		pageNum = (pageNum <= 0) ? 1 : pageNum;
		log.info("페이지 번호//" + strPageNum);
		
		int startRow = (pageNum - 1) * pageSize; 		// 페이지의 시작 행 번호
		
		List<NoticeDto> noticeList = noticeMapper.getNotices(pageSize, startRow);
		int allRowCount = noticeMapper.getNoticeCount();
		
		int maxPage = allRowCount / pageSize + ((allRowCount % pageSize > 0) ? 1 : 0); // 페이지 블록 구하기
		int startPage = (pageNum / pageBlockSize - ((pageNum % pageBlockSize == 0) ? 1 : 0)) * pageBlockSize + 1;
		int endPage = startPage + pageBlockSize - 1;
		endPage = (endPage > maxPage) ? maxPage : endPage;
		
		Map<String, Integer> pageInfoMap = new HashMap<>();	// 페이지 관련 데이터
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		pageInfoMap.put("pageBlockSize", pageBlockSize);
		pageInfoMap.put("maxPage", maxPage);
		pageInfoMap.put("allRowCount", allRowCount);
		pageInfoMap.put("pageNum", pageNum);
		
		result.put("pageInfoMap", pageInfoMap);		// 페이지 관련 데이터
		result.put("noticeList", noticeList);		// 페이지 게시글 리스트
		
		return result;
	}


	@Override
	public int getNoticeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NoticeDto getNoticeByNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateNotice(NoticeDto noticeVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteNotice(int num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countNoticeByNum(int num) {
		// TODO Auto-generated method stub
		return 0;
	}

}
