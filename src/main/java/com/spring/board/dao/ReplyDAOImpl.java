package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.Reply;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	
	@Autowired
	private SqlSession session;

	@Override
	public List<Reply> selectList(int boardNum) {
		return session.selectList("com.spring.board.ReplyMapper.selectList", boardNum);
	}

	@Override
	public int insert(Reply reply) {
		return session.insert("com.spring.board.ReplyMapper.insert", reply);
	}

	@Override
	public int updateRestep(Reply reply) {
		return session.update("com.spring.board.ReplyMapper.updateRestep", reply);
	}

	@Override
	public int update(Reply reply) {
		return session.update("com.spring.board.ReplyMapper.update", reply);
	}

	@Override
	public int delete(int replyNum) {
		return session.delete("com.spring.board.ReplyMapper.delete", replyNum);
	}
}
