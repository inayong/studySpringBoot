package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import edu.pnu.domain.MemberVO;

public class MemberDao {

	Connection con;
	private List<MemberVO> list;
	
	public MemberDao() {
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
//			while (rs.next()) {
//				
				mvo.setId(rs.getInt("id"));
				mvo.setPass(rs.getString("pass"));
				mvo.setName(rs.getString("name"));
				mvo.setRegidate(rs.getDate("regidate"));
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return mvo;
	}
	
//	public MemberVO updateMember(MemberVO memberVO) {
//		PreparedStatement psmt;
//		
//		try {
//			String query = "update member set name=? where id=?";
//			psmt = con.prepareStatement(query);
//			psmt.setString(1, memberVO.getName());
//			psmt.setInt(2, memberVO.getId());
//			psmt.executeUpdate();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return memberVO;
//	}

	public MemberVO updateMember(MemberVO memberVO) {
		Statement st;
		
		try {
			//String query = "update member set name=? where id=?";
			String query = "update member ";
			String set = "set";
			
			if (memberVO.getPass() != null) {
				set += (" pass='" + memberVO.getPass() + "' ");
			}
			if (memberVO.getName() != null) {
				if (!set.equals("set")) set += ",";
				set += (" name='" + memberVO.getName() + "' ");
			}
			
			query += (set + " where id=" + memberVO.getId());
			// update member  set pass='1234'  where id=1
			// update member  set name='lee'  where id=1
			// update member  set pass='1234', name='lee'  where id=1 

			st = con.createStatement();
			st.executeUpdate(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO;
	}
	
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
	
//	getMembers();
//	getMember(int id);
//	addMember(MemberVO memberVO);
//	updateMember(MemberVO memberVO);
//	deleteMember(int id);
}
