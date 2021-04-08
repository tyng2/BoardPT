package com.board.service;

import java.util.List;

import com.board.dto.User;

public interface UserService {

	public int insertUser(User user);
	
	public int countUserById(String id);
	
	public int loginCheck(String id, String pw);

	public int insertLoginReg(String id, String ip);
	
	public List<User> getAllUsers(String search, int startRow, int pageSize);
	
	public User getUserById(String id);
	
	public int updateUser(User user);

	public int deleteUser(String id);
	
	
}
