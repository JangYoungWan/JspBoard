package com.spring.board.dto;

public class BoardFile {
	private int fileNum; //파일번호
	private int boardNum; //게시물번호
	private String fileName; //파일이름
	
	public BoardFile() {
		super();
	}
	public BoardFile(int fileNum, int boardNum, String fileName) {
		super();
		this.fileNum = fileNum;
		this.boardNum = boardNum;
		this.fileName = fileName;
	}
	
	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String toString() {
		return "BoardFile [fileNum=" + fileNum + ", boardNum=" + boardNum + ", fileName=" + fileName + "]";
	}
}
