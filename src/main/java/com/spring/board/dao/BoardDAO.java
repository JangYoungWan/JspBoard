package com.spring.board.dao;

import java.util.List;

import com.spring.board.dto.Board;
import com.spring.board.dto.Page;

public interface BoardDAO {
	List<Board> selectList(Page page);
	Board selectOne(int boardNum);
	int insert(Board board);
	int update(Board board);
	int delete(int boardNum);
	
	//전체 게시물 수
	int selectTotCnt(Page page);
	
	//조회수 +1
	int updateReadCnt(int boardNum);
	
	//삭제시 update 
	int updateRemoveYn(int boardNum);
	
	//좋아요시 +1
	int updateLikeCnt(int boardNum);
	
	//싫어요시 +1
	int updateDisLikeCnt(int boardNum);
}
