package com.board.service;

import java.util.List;
import java.util.Map;

import com.board.dto.BbsFileDto;
import com.board.dto.BoardDto;
import com.board.dto.CommentDto;

public interface BoardService {
	
	
	public int maxNumBoard();
	
	public int insertBoard(BoardDto boardVO);
	
//	public List<BoardVO> getBoards(String search, String category, int pageSize, int startRow);
	public Map<String, Object> getBoards(Map<String, Integer> pageMap, Map<String, String> paramMap);
	
	public List<BoardDto> getBoardsMain(int pageSize, int startRow);
	
	public List<BoardDto> getBoardsOrderByHit(int pageSizeHit);
	
	public int getBoardCount(String search, String category);
	
	public Map<String, Object> getBoardByNum(int num, String sessionID);	// Board Detail
	
	public List<CommentDto> getCommentByBoardNum(int num);					// Comment AJAX
	
	public int updateBoard(BoardDto boardVO);
	
	public int deleteBoard(int num);										// Delete Board
	
	
	
	/* Comment */
	
	public void insertComment(CommentDto comment);
	
	public void deleteComment(int commentId);
	
	
	/* Comment */
	
	
	
	/* File */
	
	public BbsFileDto selectFileByFileId(String fileId);
	
	
	
	
}
