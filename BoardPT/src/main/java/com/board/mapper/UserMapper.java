package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.board.dto.UserDto;

@Mapper
public interface UserMapper {
	
	public int insertUser(UserDto userVO);
	
	public int countUserById(String id);
	
	public String getPasswordById(String id);
	
	public List<UserDto> getAllUsers(@Param("search") String search, @Param("startRow") int startRow, @Param("pageSize") int pageSize);

	public UserDto getUserById(String id);
	
	public int updateUser(UserDto userVO);
	
	public int deleteUser(String id);
	
	
	
	
	public int insertLoginLog(String id, String ip);
	
}
