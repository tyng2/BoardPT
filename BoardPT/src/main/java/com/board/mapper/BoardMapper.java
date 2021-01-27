package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.board.dto.BbsFileDto;
import com.board.dto.BoardDto;
import com.board.dto.CommentDto;

@Mapper
public interface BoardMapper {
	
	public int maxNumBoard();
	
	public int insertBoard(BoardDto boardVO);
	
	public List<BoardDto> getBoards(@Param("search") String search, @Param("category") String category, @Param("pageSize") int pageSize, @Param("startRow") int startRow);

	public List<BoardDto> getBoardsMain(@Param("pageSize") int pageSize, @Param("startRow") int startRow);
	
	public List<BoardDto> getBoardsOrderByHit(int pageSizeHit);
	
	public int getBoardCount(@Param("search") String search, @Param("category") String category);
	
	
	/* Board Detail */
	public BoardDto getBoardByNum(int num);
	
	public int updateHit(int num);
	/* Board Detail */
	
	
	public int updateBoard(BoardDto boardVO);
	
	/* DELETE BOARD */
	public int deleteBoard(int num);
	
	public int getCountFileByNum(int num);
	
	public int deleteFileByNum(int num);
	
	public int getCountCommentByNum(int num);
	
	public int deleteCommentByNum(int num);
	/* DELETE BOARD */
	
	
	/* Comment */
	
	public List<CommentDto> getCommentByBoardNum(int num);
	
	public int getNumByCommentId(int commentId);
	
	public int insertComment(CommentDto comment);
	
	public int commentCount(int num);
	
	public int deleteComment(int commentId);
	
	
	/* File */
	public List<BbsFileDto> selectFileByBoardNum(int num);
	
	public BbsFileDto selectFileByFileId(String fileId);
	
	
	/* File */
	
}
