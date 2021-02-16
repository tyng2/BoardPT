package com.board.service;

import java.util.List;

import com.board.dto.UserDto;

public interface UserService {

	public int insertUser(UserDto userVO);
	
	public int countUserById(String id);
	
	public int loginCheck(String id, String pw);

	public int insertLoginReg(String id, String ip);
	
	public List<UserDto> getAllUsers(String search, int startRow, int pageSize);
	
	public UserDto getUserById(String id);
	
	public int updateUser(UserDto userVO);

	public int deleteUser(String id);
	
	
}
