package com.board.service;

import java.util.List;
import java.util.Map;

import com.board.dto.Board;
import com.board.dto.Comment;
import com.board.dto.BoardFile;

public interface BoardService {
	
	
	public int maxNumBoard();
	
	public int insertBoard(Board board);
	
//	public List<BoardVO> getBoards(String search, String category, int pageSize, int startRow);
	public Map<String, Object> getBoards(Map<String, Integer> pageMap, Map<String, String> paramMap);
	
	public List<Board> getBoardsMain(int pageSize, int startRow);
	
	public List<Board> getBoardsOrderByHit(int pageSizeHit);
	
	public int getBoardCount(String search, String category);
	
	public Map<String, Object> getBoardByNum(int num, String sessionID);	// Board Detail
	
	public List<Comment> getCommentByBoardNum(int num);					// Comment AJAX
	
	public int updateBoard(Board board);
	
	public int deleteBoard(int num);										// Delete Board
	
	
	
	/* Comment */
	
	public void insertComment(Comment comment);
	
	public void deleteComment(int commentId);
	
	
	/* Comment */
	
	
	
	/* File */
	
	public BoardFile selectFileByFileId(String fileId);
	
	
	
	
}
