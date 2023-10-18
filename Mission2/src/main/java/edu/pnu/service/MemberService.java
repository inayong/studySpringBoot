package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = new MemberDao(); 	
	}
	
	public List<MemberVO> getMembers() {
		return memberDao.getMembers();
	}
	
	public MemberVO getMember(int id) {
		return memberDao.getMember(id);
	}
	
	public int addMember(MemberVO mvo) {
		return memberDao.addMember(mvo);
	}
	
	public MemberVO updateMember(MemberVO mvo) {
		return memberDao.updateMember(mvo);
	}
	
	public MemberVO removeMember(int id) {
		return memberDao.removeMember(id);
	}

}
