package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@SpringBootTest
public class MemberDaoTest {
	
	@BeforeAll
	public static void beforeAll() {
		System.out.println("ba--".repeat(20));
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("aa--".repeat(20));
	}
	
	@BeforeEach
	public void beforeEach() {
		System.out.println("be--".repeat(20));
	}
	
	@AfterEach
	public void afterEach() {
		System.out.println("ae--".repeat(20));
	}

	@DisplayName("MemberDao Insert Test")
//	@Test
	public void testInsert() {
		MemberDao dao = new MemberDao();
		int ret = dao.addMember(MemberVO.builder().pass("1234").name("아무개").build());
		
		System.out.println("ret : " + ret);
	}
	
	@DisplayName("MemberDao Select All Test")
//	@Test
	public void testSelectAll() {
		MemberDao dao = new MemberDao();
		
		List<MemberVO> list = dao.getMembers();
		for (MemberVO m : list) {
			System.err.println(m);
		}
	}
	
	@DisplayName("MemberDao Select Test")
//	@Test
	public void testSelect() {
		MemberDao dao = new MemberDao();
		
		MemberVO m = dao.getMember(1);
		System.out.println(m);
	}
	
	@DisplayName("MemberDao Update Test")
//	@Test
	public void testUpdate() {
		MemberDao dao = new MemberDao();
		MemberVO m = dao.updateMember(MemberVO.builder().name("길동홍").id(5).build());
		System.out.println(m);
	}
	
	@DisplayName("MemberDao Remove Test")
	@Test
	public void testRemove() {
		MemberDao dao = new MemberDao();
		MemberVO m = dao.removeMember(4);
		System.out.println(m);
	}
}
