package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoH2Impl implements MemberInterface {

	Connection con;
	private List<MemberVO> list;
	
	public MemberDaoH2Impl() {
		try {
			// JDBC 드라이버 로드
			Class.forName("org.h2.Driver");
			
			// 데이터베이스 연결
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission2", "sa", "abcd");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public List<MemberVO> getMembers() {
		list = new ArrayList<>();
		Statement st;
		ResultSet rs;
		try {
			String query = "select * from member";
			
			st = con.createStatement();
			rs = st.executeQuery(query);	
			
			while(rs.next()) {
				MemberVO mvo = new MemberVO();
				
				mvo.setId(rs.getInt("id"));
				mvo.setPass(rs.getString("pass"));
				mvo.setName(rs.getString("name"));
				mvo.setRegidate(rs.getDate("regidate"));
				
				list.add(mvo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public MemberVO getMember(int id) {
		PreparedStatement psmt;
		ResultSet rs;
		MemberVO mvo = new MemberVO();
		try {
			String query = "select * from member where id=?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			rs.next();		
				mvo.setId(rs.getInt("id"));
				mvo.setPass(rs.getString("pass"));
				mvo.setName(rs.getString("name"));
				mvo.setRegidate(rs.getDate("regidate"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mvo;
	}
	
	@Override
	public int addMember(MemberVO memberVO) {
		PreparedStatement psmt = null;
		int ret = 0;
		
		try {
			String query = "insert into member (pass, name) values (?, ?)";
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			ret = psmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	@Override
	public MemberVO updateMember(MemberVO memberVO) {
		PreparedStatement psmt;
		
		try {
			String query = "update member set name=? where id=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getName());
			psmt.setInt(2, memberVO.getId());
			psmt.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO;
	}
	
	@Override
	public MemberVO removeMember(int id) {
		PreparedStatement psmt;
		
		try {
			String query = "DELETE FROM member WHERE ID=?";
			
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
