package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.pnu.domain.MemberVO;

public class MemberService {

	private List<MemberVO> list;
	
	public MemberService() {
		list = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
//			MemberVO m = new MemberVO();
//			m.setId(i);
//			m.setPass("1234");
//			m.setName("name" + 1);
//			m.setRegidate(new Date());
//			list.add(m);
			list.add(MemberVO.builder().id(i).pass("pass"+i).name("name"+i).regidate(new Date()).build());
		}
	}
	
	public List<MemberVO> getMembers() {
		return list;
	}
	
	public MemberVO getMember(Integer id) {
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
	
	public MemberVO addMember(MemberVO memberVO) {
		
		memberVO.setId(getNextId());
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		
		return memberVO;
	}
	
	private MemberVO findMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	
	public MemberVO updateMember(MemberVO memberVO) {
		
		MemberVO fm = findMember(memberVO.getId());
		if (memberVO != null) fm.setName(memberVO.getName());
		if (memberVO != null) fm.setPass(memberVO.getPass());
		
		return fm;
	}
	
	public MemberVO deleteMember(Integer id) {
		for (int i = 0; i < list.size(); i++) {
			MemberVO m = list.get(i);
			if (m.getId() == id) {
				list.remove(i);
				return m;				
			}
		}
		return null;
	}
	
	
}
