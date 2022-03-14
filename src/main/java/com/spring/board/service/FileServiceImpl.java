package com.spring.board.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	private String saveDir;

	@Override
	public String fileUpload(MultipartFile file) throws Exception {
		//파일을 폴더에 저장하고 파일명을 리턴
		//업로드 파일 이름 : 시스템 시간 + 실제 파일 이름
		String originFilename = file.getOriginalFilename();
		if (originFilename.equals("")) return "";
		
		String fileName = System.currentTimeMillis() + "_" + originFilename;
		
		//업로드 경로(폴더명), 파일명
		File f = new File(saveDir, fileName);
		file.transferTo(f); //업로드 경로에 저장
		
		return fileName;
	}
	
	
	
}
