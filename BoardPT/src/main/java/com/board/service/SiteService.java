package com.board.service;

import org.json.simple.JSONArray;

import com.board.dto.Site;

public interface SiteService {
	
	public JSONArray getSiteList(String id);
	
	public int insertSite(Site site);
	
	public int deleteSite(int siteNum);

}
