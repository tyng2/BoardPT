package com.board.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardDto {
	private int num, re_ref, re_lev, re_seq, hit, fileCount, commentCount;
	// 글 번호, 글 그룹 번호, 글 들여쓰기(답글) 레벨, 글 그룹 내에서의 순서, 조회수 
	private String id, category, title, content, ip;
	// 작성자 명, 글 암호, 글 제목, 글 내용, 글에 업로드한 파일명, 작성자 IP 주소
	private Timestamp reg_date;
	// 작성 시간
	
	public BoardDto() {
		super();
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Board [num=").append(num).append(", re_ref=").append(re_ref).append(", re_lev=").append(re_lev)
				.append(", re_seq=").append(re_seq).append(", hit=").append(hit).append(", fileCount=")
				.append(fileCount).append(", commentCount=").append(commentCount).append(", id=").append(id)
				.append(", category=").append(category).append(", title=").append(title).append(", content=")
				.append(content).append(", ip=").append(ip).append(", reg_date=").append(reg_date).append("]");
		return builder.toString();
	}
	
	
	
	
	
	
}
