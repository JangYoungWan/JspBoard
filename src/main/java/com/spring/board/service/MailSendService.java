package com.spring.board.service;

public interface MailSendService {
	
	//이메일을 전송하고 인증번호를 반환
	String sendAuthMail(String email) throws Exception;
}
