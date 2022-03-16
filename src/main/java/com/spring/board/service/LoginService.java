package com.spring.board.service;

import java.util.Map;

public interface LoginService {
	
	Map<String, Object> login(String email, String passwd);
}
