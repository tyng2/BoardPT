package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.board.dto.Notice;

@Mapper
@Repository
public interface NoticeMapper {
	
	public int maxNumNotice();
	
	public int insertNotice(Notice notice);
	
	public List<Notice> getNotices(@Param("pageSize") int pageSize, @Param("startRow") int startRow);
	
	public int getNoticeCount();
	
	public Notice getNoticeByNum(int noti_numb);
	
	public int updateNotice(Notice notice);
	
	public int deleteNotice(int noti_numb);
	
	public int countNoticeByNum(int noti_numb);

}
