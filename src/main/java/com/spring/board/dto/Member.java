package com.spring.board.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Member {
	private String email; //이메일
	private String passwd; //비밀번호
	private String chgpasswd; //변경할 비밀번호
	private String zipcode;
	private String addr;
	private String addrDetail;
	private String fileName; //파일이름
	private String emailAuth = "0"; // 0:미인증. 1:인증
	private String simpleJoin = "0"; // 0:일반가입회원, 1:구글, 2:네이버
	private Date regiDate; //등록일자 

	private MultipartFile photoFiles; //파일들

	public Member() {
		super();
	}

	public Member(String email, String passwd, String chgpasswd, String zipcode, String addr, String addrDetail,
			String fileName, String emailAuth, String simpleJoin, Date regiDate, MultipartFile photoFiles) {
		super();
		this.email = email;
		this.passwd = passwd;
		this.chgpasswd = chgpasswd;
		this.zipcode = zipcode;
		this.addr = addr;
		this.addrDetail = addrDetail;
		this.fileName = fileName;
		this.emailAuth = emailAuth;
		this.simpleJoin = simpleJoin;
		this.regiDate = regiDate;
		this.photoFiles = photoFiles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getChgpasswd() {
		return chgpasswd;
	}

	public void setChgpasswd(String chgpasswd) {
		this.chgpasswd = chgpasswd;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEmailAuth() {
		return emailAuth;
	}

	public void setEmailAuth(String emailAuth) {
		this.emailAuth = emailAuth;
	}

	public String getSimpleJoin() {
		return simpleJoin;
	}

	public void setSimpleJoin(String simpleJoin) {
		this.simpleJoin = simpleJoin;
	}

	public Date getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}

	public MultipartFile getPhotoFiles() {
		return photoFiles;
	}

	public void setPhotoFiles(MultipartFile photoFiles) {
		this.photoFiles = photoFiles;
	}

	@Override
	public String toString() {
		return "Member [email=" + email + ", passwd=" + passwd + ", chgpasswd=" + chgpasswd + ", zipcode=" + zipcode
				+ ", addr=" + addr + ", addrDetail=" + addrDetail + ", fileName=" + fileName + ", emailAuth="
				+ emailAuth + ", simpleJoin=" + simpleJoin + ", regiDate=" + regiDate + ", photoFiles=" + photoFiles
				+ "]";
	}
	
	
}
