package com.spring.board.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.board.dao.MemberDAO;
import com.spring.board.dto.Member;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	//암호화
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public Map<String, Object> login(String email, String passwd) {
		
		Map<String, Object> result = new HashMap<>();
		//code : msg
		// 0 : 성공
		// 1 : 이메일이 존재하지 않음
		// 2 : 비밀번호 불일치
		// 3 : 이메일 미인증
		// 4 : 간편 가입 회원(naver)
		
		//회원 조회
		Member member = memberDAO.selectOne(email);
		if (member == null ) { //이메일이 존재하지 않는 경우
			result.put("code", 1);
			result.put("msg", "이메일이 존재하지 않습니다.");
			return result;
		}
		
		//평문과 암호문을 비교해서 일치여부 확인
		boolean match = bCryptPasswordEncoder.matches(passwd, member.getPasswd());
		if (!match) { //비밀번호 불일치의 경우
			result.put("code", 2);
			result.put("msg", "비밀번호가 일치하지 않습니다.");
			return result;
		}
		
		if (!member.getEmailAuth().equals("1")) { //이메일 미인증의 경우
			result.put("code", 3);
			result.put("msg", "이메일인증을 해주세요.");
			return result;
		}
		
		String simpleJoin = member.getSimpleJoin();
		if (!simpleJoin.equals("0")) {
			if (simpleJoin.equals("1")) { //네이버 간편 가입 회원
				result.put("code", 4);
				result.put("msg", "네이버 간편 가입 회원입니다.");
				return result;
			}else {
				return result;
			}
		}
		
		//성공
		result.put("code", 0);
		result.put("msg", "로그인 성공");
		return result;
	}

}
