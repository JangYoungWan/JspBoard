package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.Member;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSession session;

	@Override
	public int insert(Member member) {
		return session.insert("com.spring.board.MemberMapper.insert", member);
	}

	@Override
	public int update(Member member) {
		return session.update("com.spring.board.MemberMapper.update", member);
	}

	@Override
	public int delete(String email) {
		return session.delete("com.spring.board.MemberMapper.delete", email);
	}

	@Override
	public Member selectOne(String email) {
		return session.selectOne("com.spring.board.MemberMapper.selectOne", email);
	}

	@Override
	public List<Member> selectList(String findValue) {
		return session.selectList("com.spring.board.MemberMapper.selectList", findValue);
	}

	@Override
	public int update_emailAuth(String email) {
		return session.update("com.spring.board.MemberMapper.update_emailAuth", email);
	}
}
