package com.board.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class NoticeDto {
	private int num;
	private String title, content;
	private Timestamp reg_date;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notice [num=").append(num).append(", title=").append(title).append(", content=").append(content)
				.append(", reg_date=").append(reg_date).append("]");
		return builder.toString();
	}
	
	

}
