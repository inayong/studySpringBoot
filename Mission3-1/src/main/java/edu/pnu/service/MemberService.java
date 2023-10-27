package edu.pnu.service;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberDaoListImpl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	private MemberInterface memberDao;
	

	public MemberService(Environment env) {
//	public MemberService() {
		
		String type = env.getProperty("service.type");
		if (type != null && type.equals("h2")) {
			System.out.println("h2service");
			memberDao = new MemberDaoH2Impl();
		}
//		else if (type.equals("mysql"))  memberDao = new MemberDaoMySQLImpl();
		else {
			System.out.println("listservice");
			memberDao = new MemberDaoListImpl();
		}
		
		memberDao = new MemberDaoH2Impl(); 	
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
