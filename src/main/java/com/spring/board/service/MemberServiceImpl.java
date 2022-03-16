package com.spring.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.board.dao.MemberDAO;
import com.spring.board.dto.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	//파일 저장
	@Autowired
	private FileService fileService;
	
	//암호화
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//이메일 인증
	@Autowired
	private MailSendService mailSendService;
	
	@Override
	public List<Member> selectList(String findValue) {
		return memberDAO.selectList(findValue);
	}

	@Override
	public Member selectOne(String email) {
		return memberDAO.selectOne(email);
	}

	@Override
	public Map<String, Object> insert(Member member) throws Exception {
		Map<String, Object> result = new HashMap<>();
		//code : msg
		// 0 : 정상
		// 1 : 중복된 아이디
		
		// 중복된 회원인지 체크
		Member dbmember = memberDAO.selectOne(member.getEmail());
		if (dbmember != null) { //만약 중복된 아이디를 가진 회원이 존재할경우
			result.put("code", 1);
			result.put("msg", "중복된 아이디");
			return result;
		}
		
		//파일 업로드 
		String fileName = fileService.fileUpload(member.getPhotoFiles());
		member.setFileName(fileName);
		
		//비밀번호 암호화
		String cryPassword = bCryptPasswordEncoder.encode(member.getPasswd());
		member.setPasswd(cryPassword);
		
		//db에 저장
		int cnt = memberDAO.insert(member); 
		
		//이메일 인증
		String authCode = mailSendService.sendAuthMail(member.getEmail());
		result.put("authCode", authCode);
		
		result.put("code", 0);
		result.put("msg", "정상");
		return result;
	}

	@Override
	public String update_emailAuth(String email) {
		
		int cnt = memberDAO.update_emailAuth(email);
		if (cnt > 0) 
			return "이메일 인증완료";
		else
			return "이메일 미인증";
	}

	@Override
	public Map<String, Object> update(Member member) throws Exception {
		Map<String, Object> result = new HashMap<>();
		//code : msg
		// 0 : 성공
		// 1 : 기존비밀번호 불일치
		
		//회원조회
		Member dbmember = memberDAO.selectOne(member.getEmail());
		
		//기존 비밀번호 일치 여부(일반회원만)
		if (dbmember.getSimpleJoin().equals("0")) { 
			boolean match = bCryptPasswordEncoder.matches(member.getPasswd(), dbmember.getPasswd());
			if (!match) { //비밀번호가 맞지 않은 경우
				result.put("code", 1);
				result.put("msg", "기존 비밀번호 불일치");
				return result;
			}
		}
		
		//변경할 비밀번호 세팅(일반회원만)
		if (dbmember.getSimpleJoin().equals("0")) { 
			String passwd = null;
			if (!member.getChgpasswd().equals("")) { //변경할 비밀번호가 있다면
				passwd = member.getChgpasswd(); //변경할 패스워드
			}else {
				passwd = member.getPasswd(); //기존 패스워드
			}
			member.setPasswd(bCryptPasswordEncoder.encode(passwd));
		}
		
		//파일 처리
		String fileName = fileService.fileUpload(member.getPhotoFiles());
		if (!fileName.equals("")) { //파일을 변경할 경우
			member.setFileName(fileName);
		}
		
		//수정 
		memberDAO.update(member);
		
		result.put("code", 0);
		result.put("msg", "성공");
		return result;
	}

	@Override
	public Map<String, Object> delete(String email, String passwd) {
		Map<String, Object> result = new HashMap<>();
		//code : msg
		// 0 : 성공
		// 1 : 기존비밀번호 불일치
		
		//회원 조회
		Member dbmember = memberDAO.selectOne(email);
		
		//기존 비밀번호 일치 여부(일반회원만)
		if (dbmember.getSimpleJoin().equals("0")) {
			boolean match = bCryptPasswordEncoder.matches(passwd, dbmember.getPasswd());
			if (!match) {
				result.put("code", 1);
				result.put("msg", "기존비밀번호 불일치");
				return result;
			}
		}
		
		//삭제
		memberDAO.delete(email);
		result.put("code", 0);
		result.put("msg", "성공");
		return result;
	}

}
