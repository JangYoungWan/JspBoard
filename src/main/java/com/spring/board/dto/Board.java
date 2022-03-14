package com.spring.board.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Board {
	private int boardNum; //게시물 번호
	private String email; //이메일
	private String subject; //제목
	private String content; //내용
	private int readCnt; //조회수
	private int likeCnt; //좋아요수
	private int dislikeCnt; //싫어요수
	private String ip; //작성자의 ip
	private Date regiDate; //등록일자
	private Date modiDate; //수정일자
	private String removeYn; //삭제여부(y/n)
	
	private List<MultipartFile> files; //파일들

	public Board() {
		super();
	}

	public Board(int boardNum, String email, String subject, String content, int readCnt, int likeCnt, int dislikeCnt,
			String ip, Date regiDate, Date modiDate, String removeYn, List<MultipartFile> files) {
		super();
		this.boardNum = boardNum;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.readCnt = readCnt;
		this.likeCnt = likeCnt;
		this.dislikeCnt = dislikeCnt;
		this.ip = ip;
		this.regiDate = regiDate;
		this.modiDate = modiDate;
		this.removeYn = removeYn;
		this.files = files;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int lickeCnt) {
		this.likeCnt = lickeCnt;
	}

	public int getDislikeCnt() {
		return dislikeCnt;
	}

	public void setDislikeCnt(int dislikeCnt) {
		this.dislikeCnt = dislikeCnt;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}

	public Date getModiDate() {
		return modiDate;
	}

	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}

	public String getRemoveYn() {
		return removeYn;
	}

	public void setRemoveYn(String removeYn) {
		this.removeYn = removeYn;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Board [boardNum=" + boardNum + ", email=" + email + ", subject=" + subject + ", content=" + content
				+ ", readCnt=" + readCnt + ", likeCnt=" + likeCnt + ", dislikeCnt=" + dislikeCnt + ", ip=" + ip
				+ ", regiDate=" + regiDate + ", modiDate=" + modiDate + ", removeYn=" + removeYn + ", files=" + files
				+ "]";
	}

	
}
