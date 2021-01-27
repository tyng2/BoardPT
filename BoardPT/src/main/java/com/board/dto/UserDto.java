package com.board.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserDto {
	private String id, password, name, email, address;
	private Timestamp reg_date;

	public UserDto(String id, String password, String name, String email, String address) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserVO [id=").append(id).append(", password=").append(password).append(", name=").append(name)
				.append(", email=").append(email).append(", address=").append(address).append(", reg_date=").append(reg_date).append("]");
		return builder.toString();
	}

	
	
}
