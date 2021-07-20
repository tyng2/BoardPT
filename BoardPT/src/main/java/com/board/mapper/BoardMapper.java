package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.board.dto.Board;
import com.board.dto.BoardFile;
import com.board.dto.Comment;

@Mapper
public interface BoardMapper {
	
	public int maxNumBoard();
	
	public int insertBoard(Board board);
	
	public List<Board> getBoards(@Param("search") String search, @Param("category") String category, @Param("pageSize") int pageSize, @Param("startRow") int startRow);

	public List<Board> getBoardsMain(@Param("pageSize") int pageSize, @Param("startRow") int startRow);
	
	public List<Board> getBoardsOrderByHit(int pageSizeHit);
	
	public int getBoardCount(@Param("search") String search, @Param("category") String category);
	
	
	/* Board Detail */
	public Board getBoardByNum(int bord_numb);
	
	public int updateHit(int bord_numb);
	/* Board Detail */
	
	
	public int updateBoard(Board board);
	
	/* DELETE BOARD */
	public int deleteBoard(int bord_numb);
	
	public int getCountFileByNum(int bord_numb);
	
	public int deleteFileByNum(int bord_numb);
	
	public int getCountCommentByNum(int bord_numb);
	
	public int deleteCommentByNum(int bord_numb);
	/* DELETE BOARD */
	
	
	/* Comment */
	
	public List<Comment> getCommentByBoardNum(int bord_numb);
	
	public int getNumByCommentId(int comm_numb);
	
	public int insertComment(Comment comment);
	
	public int commentCount(int bord_numb);
	
	public int deleteComment(int comm_numb);
	
	
	/* File */
	public List<BoardFile> selectFileByBoardNum(int bord_numb);
	
	public BoardFile selectFileByFileId(String file_numb);
	
	public int insertFile(List<BoardFile> fileList);
	
	/* File */
	
}
