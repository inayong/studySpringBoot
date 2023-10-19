package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {
	private List<MemberVO> list;
	
	public MemberDaoListImpl() {
		list = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			list.add(MemberVO.builder().id(i).pass("pass"+i).name("name"+i).regidate(new Date()).build());
		}
	}
	
	@Override
	public List<MemberVO> getMembers() {
		return list;
	}

	@Override
	public MemberVO getMember(int id) {
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
	
	@Override
	public int addMember(MemberVO memberVO) {
		memberVO.setId(getNextId());
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return 0;
	}
	
	public MemberVO findMember(int id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	
	@Override
	public MemberVO updateMember(MemberVO memberVO) {
		MemberVO m = findMember(memberVO.getId());
		if (memberVO != null) m.setName(memberVO.getName());
		if (memberVO != null) m.setPass(memberVO.getPass());
		return m;
	}

	@Override
	public MemberVO removeMember(int id) {
		for (int i = 0; i < list.size(); i++) {
			MemberVO m = list.get(i);
			if (m.getId() == id)
				list.remove(i);
			return m;
		}
		return null;
	}

}
