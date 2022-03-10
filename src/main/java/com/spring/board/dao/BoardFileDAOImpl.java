package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.BoardFile;

@Repository
public class BoardFileDAOImpl implements BoardFileDAO{
	
	@Autowired
	private SqlSession session;

	@Override
	public List<BoardFile> selectList(int boardNum) {
		return session.selectList("com.spring.board.BoardFileMapper.selectList", boardNum);
	}

	@Override
	public int insert(BoardFile boardFile) {
		return session.insert("com.spring.board.BoardFileMapper.insert", boardFile);
	}

	@Override
	public int delete(int boardNum) {
		return session.delete("com.spring.board.BoardFileMapper.delete", boardNum);
	}
	
}
