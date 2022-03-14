package com.spring.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.board.dao.BoardDAO;
import com.spring.board.dao.BoardFileDAO;
import com.spring.board.dto.Board;
import com.spring.board.dto.BoardFile;
import com.spring.board.dto.Page;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private BoardFileDAO boardFileDAO;
	
	@Autowired
	private FileService fileService;

	@Override
	public List<Board> selectList(Page page) {
		//페이지 처리값
		int curPage = page.getCurPage(); //현재 페이지
		int perPage = page.getPerPage(); //한페이지당 게시물 수
		int perBlock = page.getPerBlock(); //화면 페이지 수
		
		//전체 게시물 수
		int totCnt = boardDAO.selectTotCnt(page);
		
		//전체 페이지 수
		int totPage = totCnt / perPage;
		if (totCnt%perPage != 0) totPage ++; // 나머지가 있으면 +1
		page.setTotPage(totPage);
		
		//시작번호
		int startNum = (curPage-1)*perPage;
		page.setStartNum(startNum);
		
		//끝번호
		int endNum = startNum + perPage - 1;
		page.setEndNum(endNum);
		
		//시작 페이지
		int startPage = curPage - ((curPage-1)%perBlock);
		page.setStartPage(startPage);
		
		//끝 페이지
		int endPage = startPage + perBlock -1;
		if (endPage > totPage) endPage = totPage; //끝페이지는 전체 페이지보다 클수 없기에 지정
		page.setEndPage(endPage);
	
		return boardDAO.selectList(page);
	}

	@Override
	public Map<String, Object> selectOne(int boardNum) {
		//게시물과 게시물 파일들 조회
		//게시물 조회
		Board board = boardDAO.selectOne(boardNum);
		
		//게시물 파일 조회
		List<BoardFile> bflist = boardFileDAO.selectList(boardNum);
		Map<String, Object> result = new HashMap<>();
		result.put("board", board);
		result.put("bflist", bflist);
		return result;
	}

	@Override
	public Map<String, Object> insert(Board board) throws Exception {
		//게시물 저장(mapper에서 bnum 세팅된다)
		boardDAO.insert(board);
		
		//게시물 파일 저장
		List<MultipartFile> files = board.getFiles();
		if (files != null) {
			BoardFile boardFile = new BoardFile();
			boardFile.setBoardNum(board.getBoardNum());
			for (MultipartFile file : files) {
				String fileName = fileService.fileUpload(file);
				//파일이름이 공백이 아닐때 저장
				if (!fileName.equals("")) {
					boardFile.setFileName(fileName);
					boardFileDAO.insert(boardFile);
				}
			}
		}
		//결과 맵
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		result.put("msg", "저장완료");
		return result;
	}

	@Override
	public Map<String, Object> update(Board board, List<Integer> removeFile) throws Exception {
		//게시물 수정
		boardDAO.update(board);
		
		//게시물 파일 삭제
		if(removeFile != null) {
			for(int fileNum: removeFile) {
				boardFileDAO.delete(fileNum);
			}
		}
		
		//게시물 파일 추가
		List<MultipartFile> files = board.getFiles();
		if (files != null) {
			BoardFile boardFile = new BoardFile();
			boardFile.setBoardNum(board.getBoardNum());
			for (MultipartFile file : files) {
				String fileName = fileService.fileUpload(file);
				//파일이름이 공백이 아닐때 저장
				if (!fileName.equals("")) {
					boardFile.setFileName(fileName);
					boardFileDAO.insert(boardFile);
				}
			}
		}
		//결과 맵
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		result.put("msg", "수정완료");
		return result;
	}

	@Override
	public int updateReadCnt(int boardNum) {
		return boardDAO.updateReadCnt(boardNum);
	}

	@Override
	public int updateRemoveYn(int boardNum) {
		return boardDAO.updateRemoveYn(boardNum);
	}

	@Override
	public void updateLikeCnt(int boardNum) {
		boardDAO.updateLikeCnt(boardNum);
	}
	
	@Override
	public int selectLikeCnt(int boardNum) {
		Board board = boardDAO.selectOne(boardNum);
		return board.getLikeCnt();
	}

	@Override
	public void updateDisLikeCnt(int boardNum) {
		boardDAO.updateDisLikeCnt(boardNum);
	}

	@Override
	public int selectDisLikeCnt(int boardNum) {
		Board board = boardDAO.selectOne(boardNum);
		return board.getDislikeCnt();
	}

}
