package com.spring.board.service;

import java.util.List;
import java.util.Map;

import com.spring.board.dto.Board;
import com.spring.board.dto.Page;


public interface BoardService {
	
	List<Board> selectList(Page page); 
	
	Map<String, Object> selectOne(int boardNum);
	
	Map<String, Object> insert(Board board) throws Exception;
	
	Map<String, Object> update(Board board, List<Integer> removeFile) throws Exception; 
	
	int updateReadCnt(int boardNum);
	
	int updateRemoveYn(int boardNum);
	
	void updateLikeCnt(int boardNum);
	
	int selectLikeCnt(int boardNum);
	
	void updateDisLikeCnt(int boardNum);
	
	int selectDisLikeCnt(int boardNum);
}
