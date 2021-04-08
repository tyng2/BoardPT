package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.board.dto.Site;

@Mapper
@Repository
public interface SiteMapper {

	
	public List<Site> getSiteList(String user_id);
	
	public int getMaxSite();
	
	public int insertSite(Site site);
	
	public int deleteSite(int site_numb);

}
