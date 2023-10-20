package edu.pnu.dao;

import java.sql.Connection;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDao {

	Connection con;
	private List<MemberVO> list;
	
	public MemberDao() {
		try {
			Class.forName("org.h2.Driver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
