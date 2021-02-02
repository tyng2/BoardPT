package com.board.service;

import org.json.simple.JSONArray;

import com.board.dto.SiteDto;

public interface SiteService {
	
	public JSONArray getSiteList(String id);
	
	public int insertSite(SiteDto site);
	
	public int deleteSite(int siteNum);

}
