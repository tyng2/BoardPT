package com.board.dto;

import lombok.Data;

@Data
public class SiteDto {
	private int siteNum;
	private String siteName, url, id;
	
	@Override
	public String toString() {
		return "Site [siteNum=" + siteNum + ", siteName=" + siteName + ", url=" + url + ", id=" + id + "]";
	}
	
	

}
