package com.board.dto;

import lombok.Data;

@Data
public class BbsFileDto {
	private int fileId, num;
	private String orgl_file_nm, save_file_nm;
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BbsFileDto [fileId=").append(fileId).append(", num=").append(num).append(", orgl_file_nm=")
				.append(orgl_file_nm).append(", save_file_nm=").append(save_file_nm).append("]");
		return builder.toString();
	}
	
	

}
