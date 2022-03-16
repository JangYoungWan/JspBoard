package com.spring.board.service;

import java.util.Map;

public interface NaverLoginService {

	//네이버 로그인 api
	Map<String, String> getApiUrl() throws Exception;
	
	//토큰을 얻고 개인정보를 얻기
	Map<String, String> getTokenUserInfo(String code, String state) throws Exception;
	
	//db에 네이버 이메일을 저장
	Map<String, Object> insert(String email);
}
