package com.spring.board.dao;

import java.util.List;

import com.spring.board.dto.Member;

public interface MemberDAO {
	
	int insert(Member member);
	
	int update(Member member);
	
	int delete(String email);
	
	Member selectOne(String email);
	
	List<Member> selectList(String findValue);
	//이메일 인증
	int update_emailAuth(String email);
}
