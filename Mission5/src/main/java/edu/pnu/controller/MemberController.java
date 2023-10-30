package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
//
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member")
	public List<Member> getMembers() {
		return memberService.getMembers();
	}
//	
	@GetMapping("/member/{id}")
	public Member getMember(@PathVariable int id) {
		return memberService.getMember(id);
	}
//	
	@PostMapping("/member")
	public Member addMember(Member member) {
		return memberService.addMember(member);
	}
//	
	@PutMapping("/member")
	public Member updateMember(Member member) {
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public Member removerMember(@PathVariable int id) {
		return memberService.removeMember(id);
	}
}
