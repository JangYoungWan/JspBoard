package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.dao.ReplyDAO;
import com.spring.board.dto.Reply;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public List<Reply> selectList(int boardNum) {
		return replyDAO.selectList(boardNum);
	}

	@Transactional
	@Override
	public int insert(Reply reply) {
		
		//현재 데이터의 글순서와 글 레벨+1
		reply.setReStep(reply.getReStep() + 1);
		reply.setReLevel(reply.getReLevel() + 1);
		
		//다른 자료의 글순서(ReStep+1) db수정
		replyDAO.updateRestep(reply);
		
		//저장
		return replyDAO.insert(reply);
	}

	@Override
	public void delete(int replyNum) {
		replyDAO.delete(replyNum);
	}

	@Override
	public void update(Reply reply) {
		replyDAO.update(reply);
	}

}
