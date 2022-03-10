package com.spring.board.dao;

import java.util.List;

import com.spring.board.dto.BoardFile;

public interface BoardFileDAO {
	
	List<BoardFile> selectList(int boardNum);
	
	int insert(BoardFile boardFile);
	
	int delete(int boardNum);
	
}
