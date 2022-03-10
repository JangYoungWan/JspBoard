package com.spring.board.dao;

import java.util.List;

import com.spring.board.dto.Reply;

public interface ReplyDAO {
	
	List<Reply> selectList(int boardNum);
	
	int insert(Reply reply);
	
	int updateRestep(Reply reply);
	
	int update(Reply reply);
	
	int delete(int replyNum);
}
