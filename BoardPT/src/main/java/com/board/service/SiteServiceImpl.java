package com.board.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dto.Site;
import com.board.mapper.SiteMapper;

@Service
public class SiteServiceImpl implements SiteService {
	
	@Autowired
	private SiteMapper siteMapper;

	@Override
	public JSONArray getSiteList(String id) {
		List<Site> list = siteMapper.getSiteList(id);
		
		JSONArray jArr	= new JSONArray();
		JSONObject jObj	= null;
		
		for(Site site : list) {
			jObj = new JSONObject();
			
			jObj.put("siteNum",		site.getSite_numb());
			jObj.put("siteName",	site.getSite_name());
			jObj.put("url",			site.getSite_url());
			jObj.put("id", 			site.getUser_id());
			
			jArr.add(jObj);
			
		}
		
		return jArr;
	}

	@Override
	public int insertSite(Site site) {
		
		int siteNum = siteMapper.getMaxSite();
		site.setSite_numb(++siteNum);
		
		return siteMapper.insertSite(site);
	}

	@Override
	public int deleteSite(int siteNum) {
		return siteMapper.deleteSite(siteNum);
	}
	
	
	

	
}
