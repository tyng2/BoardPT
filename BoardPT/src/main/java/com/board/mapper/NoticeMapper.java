package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.board.dto.NoticeDto;

@Mapper
@Repository
public interface NoticeMapper {
	
	public int maxNumNotice();
	
	public int insertNotice(NoticeDto noticeVO);
	
	public List<NoticeDto> getNotices(@Param("pageSize") int pageSize, @Param("startRow") int startRow);
	
	public int getNoticeCount();
	
	public NoticeDto getNoticeByNum(int num);
	
	public int updateNotice(NoticeDto noticeVO);
	
	public int deleteNotice(int num);
	
	public int countNoticeByNum(int num);

}
