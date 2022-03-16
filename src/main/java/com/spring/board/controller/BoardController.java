package com.spring.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.dto.Board;
import com.spring.board.dto.Page;
import com.spring.board.service.BoardService;


@Controller
@RequestMapping("board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	//게시물 조회로 이동
	@RequestMapping("/")
	public String list() {
		return "board/list";
	}
	
	//게시물 조회
	@RequestMapping("list")
	public String list(@ModelAttribute Page page, Model model) {
		List<Board> blist = boardService.selectList(page);
		model.addAttribute("blist", blist);
		return "board/list";
	}
	
	//상세 조회폼으로 이동
	@RequestMapping("detail")
	public String detail(@RequestParam int boardNum, Model model) {
		boardService.updateReadCnt(boardNum); //조회수 +1
		Map<String, Object> result = boardService.selectOne(boardNum); //게시물 + 게시물의 파일들
		model.addAttribute("result", result);
		return "board/detail";
	}
	
	//게시물 등록폼으로 이동
	@GetMapping("add")
	public void add() {}
	
	//게시물 등록
	@PostMapping("add")
	public String add(@ModelAttribute Board board, HttpServletRequest request, RedirectAttributes rattr) throws Exception {
		board.setIp(request.getRemoteAddr()); //사용자 ip
		Map<String, Object> result = boardService.insert(board);
		String msg = (String)result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		return "redirect:list";
	}
	
	//게시물 삭제
	@RequestMapping("remove")
	public String remove(@RequestParam int boardNum) {
		boardService.updateRemoveYn(boardNum);
		return "redirect:list";
	}
	
	//게시물 수정폼으로 이동
	@GetMapping("modify")
	public String modify(@RequestParam int boardNum, Model model) {
		Map<String, Object> result = boardService.selectOne(boardNum);
		model.addAttribute("result", result);
		return "board/modify";
	}
	
	//게시물 수정
	//required = false : 데이터가 존재하지 않을수도 있음을 의미한다.
	@PostMapping("modify")
	public String modify(@ModelAttribute Board board, @RequestParam(required = false) List<Integer> removefile, HttpServletRequest request, RedirectAttributes rattr) throws Exception {
		board.setIp(request.getRemoteAddr()); //사용자 ip
		Map<String, Object> result = boardService.update(board, removefile);
		String msg = (String)result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		rattr.addAttribute("boardNum", board.getBoardNum());
		return "redirect:detail";
	}
	
	//좋아요 +1
	@ResponseBody //리턴값을 값자체로 보낸다.
	@PutMapping("likecnt/{boardNum}")
	public String likecnt(@PathVariable int boardNum) {
		//db 좋아요 +1 
		boardService.updateLikeCnt(boardNum);
		//db likecnt 조회
		int likecnt = boardService.selectLikeCnt(boardNum);
		return String.valueOf(likecnt);
	}
	
	//싫어요 +1 
	@ResponseBody
	@PutMapping("dislike/{boardNum}")
	public String dislikecnt(@PathVariable int boardNum) {
		//db 싫어요 +1 
		boardService.updateDisLikeCnt(boardNum);
		//db dislikecnt 조회
		int dislikecnt = boardService.selectDisLikeCnt(boardNum);
		return String.valueOf(dislikecnt);
	}
}
