package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.pnu.domain.MemberVO;

public class MemberController {

	List<MemberVO> list;
	
	public MemberController() {
		list = new ArrayList<>();
		
		//데이터 불러오기
		//add
		for (int i = 1; i <= 5; i++) {
			MemberVO m = new MemberVO();
			m.setId(i);
			m.setName("Name" + i);
			m.setPass("Pass" + i);
			m.setRegidate(new Date());
			list.add(m);
		}
	}
	
	//전체리스트 
	//리스트 리턴
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		return list;
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	
	private int getNextId() {
		int mid = -1;
		for (MemberVO m : list) {
			if (mid < m.getId())
				mid = m.getId();
		}
		
		return mid + 1;
	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		
		memberVO.setId(getNextId());
		
		return null;
	}
}
