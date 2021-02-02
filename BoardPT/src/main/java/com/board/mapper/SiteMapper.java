package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.board.dto.NoticeDto;
import com.board.dto.SiteDto;

@Mapper
@Repository
public interface SiteMapper {

	
	public List<SiteDto> getSiteList(String id);
	
	public int getMaxSite();
	
	public int insertSite(SiteDto site);
	
	public int deleteSite(int siteNum);

}
