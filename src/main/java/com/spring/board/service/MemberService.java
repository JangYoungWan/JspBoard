package com.spring.board.service;

import java.util.List;
import java.util.Map;

import com.spring.board.dto.Member;

public interface MemberService {
	
	List<Member> selectList(String findValue);
	
	Member selectOne(String email);
	
	Map<String, Object> insert(Member member) throws Exception;
	
	String update_emailAuth(String email);
	
	Map<String, Object> update(Member member) throws Exception;
	
	Map<String, Object> delete(String email, String passwd); 
}
