package com.spring.board.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	//파일을 업로드하고 파일명을 리턴한다.
	String fileUpload(MultipartFile file) throws Exception;
}
