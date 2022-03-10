package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.Board;
import com.spring.board.dto.Page;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Board> selectList(Page page) {
		return session.selectList("com.spring.board.BoardMapper.selectList", page);
	}

	@Override
	public Board selectOne(int boardNum) {
		return session.selectOne("com.spring.board.BoardMapper.selectOne", boardNum);
	}

	@Override
	public int insert(Board board) {
		return session.insert("com.spring.board.BoardMapper.insert", board);
	}

	@Override
	public int update(Board board) {
		return session.update("com.spring.board.BoardMapper.update", board);
	}

	@Override
	public int delete(int boardNum) {
		return session.delete("com.spring.board.BoardMapper.update", boardNum);
	}

	@Override
	public int selectTotcnt(Page page) {
		return session.selectOne("com.spring.board.BoardMapper.selectTotcnt", page);
	}

	@Override
	public int updateReadCnt(int boardNum) {
		return session.update("com.spring.board.BoardMapper.updateReadCnt", boardNum);
	}

	@Override
	public int updateRemoveYn(int boardNum) {
		return session.update("com.spring.board.BoardMapper.updateRemoveYn", boardNum);
	}

	@Override
	public int updateLikeCnt(int boardNum) {
		return session.update("com.spring.board.BoardMapper.updateLikeCnt", boardNum);
	}

	@Override
	public int updateDisLikeCnt(int boardNum) {
		return session.update("com.spring.board.BoardMapper.updateDisLikeCnt", boardNum);
	}

}
