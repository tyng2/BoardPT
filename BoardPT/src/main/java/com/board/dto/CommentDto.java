package com.board.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CommentDto {
	private int commentId, num;
	private String id, content;
	private Timestamp reg_date;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("comment [commentId=").append(commentId).append(", num=").append(num).append(", id=").append(id)
				.append(", content=").append(content).append(", reg_date=").append(reg_date).append("]");
		return builder.toString();
	}
	
	
	

}
