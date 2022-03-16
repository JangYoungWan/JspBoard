package com.spring.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.board.dto.Reply;
import com.spring.board.service.ReplyService;

@RestController // @Controller + @ResponseBody : 리졸버 작동안됨
@RequestMapping("reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	//댓글의 리스트 
	@GetMapping("list/{boardNum}")
	public List<Reply> list(@PathVariable int boardNum){
		List<Reply> rlist = replyService.selectList(boardNum);
		return rlist;
	}
	
	//댓글추가
	@PostMapping("/")
	public String add(@RequestBody Reply reply, HttpServletRequest request) {
		reply.setIp(request.getRemoteAddr());
		replyService.insert(reply);
		return "ok";
	}
	
	//댓글삭제
	@DeleteMapping("{replyNum}")
	public String delete(@PathVariable int replyNum) {
		replyService.delete(replyNum);
		return "ok";
	}
	
	//댓글수정
	@PutMapping("/")
	public String modify(@RequestBody Reply reply) {
		replyService.update(reply);
		return "ok";
	}
}

