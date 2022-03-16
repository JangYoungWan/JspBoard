package com.spring.board.service;

import java.util.Random;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendServiceImpl implements MailSendService{
	
	@Autowired
	private JavaMailSender javaMailSender; 
	
	//인증번호 생성 메소드 
	private String getAuthCode() {
		StringBuffer authCode = new StringBuffer();
		Random random = new Random();
		
		for (int i=0; i<6; i++) {
			authCode.append(random.nextInt(10)); //0~9사이의 정수
		}
		return authCode.toString();
	}
	
	//매개변수: 보낼 이메일, 아이디
	@Override
	public String sendAuthMail(String email) throws Exception {
		
		//6자리 인증번호 생성
		String authCode = getAuthCode();
		System.out.println(authCode);
		
		//보낼 이메일 내용
		StringBuffer content = new StringBuffer();
		//로컬에서 테스트(실서버 구축 후 주석달기)
		content.append(email + "님 반갑습니다. 아래의 링크를 클릭해주세요.<br>");
		content.append("<a href='http://localhost:8082/board/member/joinConfirm?authCode="+ authCode +"'>이메일 인증 확인</a>");
		System.out.println(content.toString());
		
		//실서버 (나중에)
		//content.append(email + "님 반갑습니다. 아래의 링크를 클릭해주세요.<br>");
		//content.append("<a href='http://(실서버주소):8080/my/member/joinConfirm?authCode=" + authCode +"'>이메일인증확인</a>");
		//System.out.println(content.toString());
		
		//보낼 메일 객체 생성
		MimeMessage mimeMsg = javaMailSender.createMimeMessage();
		mimeMsg.setContent("회원가입 이메일 인증", "utf-8");
		mimeMsg.setText(content.toString(), "utf-8", "html");
		mimeMsg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		
		//메일보내기
		javaMailSender.send(mimeMsg);
		
		return authCode;
	}

}
