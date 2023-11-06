package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepo;
//
//	public MemberService() {
//		
//	}
//	
	public List<Member> getMembers() {
//		List<Member> list = memberRepo.findAll();
//		return list;
		return memberRepo.findAll();
		
	}
//
	public Member getMember(int id) {
		return memberRepo.findById(id).get();
	}

//
	public Member addMember(Member member) {
		return memberRepo.save(member);
	}
//
	public Member updateMember(Member member) {
		return memberRepo.save(member);
	}
//
	public Member removeMember(int id) {
		return null;
//		return memberRepo.deleteById();
		
	}


}
