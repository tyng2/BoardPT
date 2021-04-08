package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.board.dto.User;

@Mapper
public interface UserMapper {
	
	public int insertUser(User user);
	
	public int countUserById(String user_id);
	
	public String getPasswordById(String user_id);
	
	public List<User> getAllUsers(@Param("search") String search, @Param("startRow") int startRow, @Param("pageSize") int pageSize);

	public User getUserById(String user_id);
	
	public int updateUser(User user);
	
	public int deleteUser(String user_id);
	
	
	
	
	public int insertLoginReg(String logn_id, String logn_ip);
	
}
