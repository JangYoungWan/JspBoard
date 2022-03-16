package com.spring.board.service;

import java.util.List;

import com.spring.board.dto.Reply;

public interface ReplyService {
	
	List<Reply> selectList(int boardNum);
	
	int insert(Reply reply);
	
	void delete(int replyNum);
	
	void update(Reply reply);
}
