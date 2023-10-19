package edu.pnu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {

//	private MemberService memberService; 
//	
//	//생성자
//	public MemberController() {
//		memberService = new MemberService();
//	}
	
//	MemberService memberService = new MemberService();
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable int id) {
		return memberService.getMember(id);
	}
	
	@PostMapping("/member")
	public int addMember(MemberVO mvo) {
		return memberService.addMember(mvo);
	}
	
	@PutMapping("/member")
	public MemberVO updateMember(MemberVO mvo) {
		return memberService.updateMember(mvo);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO removeMember(@PathVariable int id) {
		return memberService.removeMember(id);
	}
}
