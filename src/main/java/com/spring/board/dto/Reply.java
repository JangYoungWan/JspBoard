package com.spring.board.dto;

public class Reply {
	private int replyNum; //댓글번호
	private int boardNum; //게시물번호
	private String email; //이메일
	private String content; //내용
	private String ip; //작성자ip
	private int reStep;
	private int reLevel;
	private String regiDate; //등록일자
	private String modiDate; //수정일자
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getReStep() {
		return reStep;
	}
	public void setReStep(int reStep) {
		this.reStep = reStep;
	}
	public int getReLevel() {
		return reLevel;
	}
	public void setReLevel(int reLevel) {
		this.reLevel = reLevel;
	}
	public String getRegiDate() {
		return regiDate;
	}
	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}
	public String getModiDate() {
		return modiDate;
	}
	public void setModiDate(String modiDate) {
		this.modiDate = modiDate;
	}
	@Override
	public String toString() {
		return "Reply [replyNum=" + replyNum + ", boardNum=" + boardNum + ", email=" + email + ", content=" + content
				+ ", ip=" + ip + ", reStep=" + reStep + ", reLevel=" + reLevel + ", regiDate=" + regiDate
				+ ", modiDate=" + modiDate + "]";
	}
	
	
}
