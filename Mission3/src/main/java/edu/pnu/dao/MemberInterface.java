package edu.pnu.dao;

import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {

	int addMember(MemberVO memberVO);

	List<MemberVO> getMembers();

	MemberVO getMember(int id);

	MemberVO updateMember(MemberVO memberVO);

	MemberVO removeMember(int id);

}