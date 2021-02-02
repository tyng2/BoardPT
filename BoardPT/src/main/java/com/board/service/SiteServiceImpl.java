package com.board.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dto.SiteDto;
import com.board.mapper.SiteMapper;

@Service
public class SiteServiceImpl implements SiteService {
	
	@Autowired
	private SiteMapper siteMapper;

	@Override
	public JSONArray getSiteList(String id) {
		List<SiteDto> list = siteMapper.getSiteList(id);
		
		JSONArray jArr	= new JSONArray();
		JSONObject jObj	= null;
		
		for(SiteDto site : list) {
			jObj = new JSONObject();
			
			jObj.put("siteNum",		site.getSiteNum());
			jObj.put("siteName",	site.getSiteName());
			jObj.put("url",			site.getUrl());
			jObj.put("id", 			site.getId());
			
			jArr.add(jObj);
			
		}
		
		return jArr;
	}

	@Override
	public int insertSite(SiteDto site) {
		
		int siteNum = siteMapper.getMaxSite();
		site.setSiteNum(++siteNum);
		
		return siteMapper.insertSite(site);
	}

	@Override
	public int deleteSite(int siteNum) {
		return siteMapper.deleteSite(siteNum);
	}
	
	
	

	
}
