package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RelationMappingTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	@Order(1)
	public void testManyToOneInsert() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setPassword("1234");
		member1.setName("둘리");
		member1.setRole("user");
//		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("1234");
		member2.setName("도우너");
		member2.setRole("admin");
//		memberRepo.save(member2);
		
		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle("둘리 게시물" + i);
			board.setContent("둘리 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
//			boardRepo.save(board);
		}
		memberRepo.save(member1);
		
		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle("도우너 게시물" + i);
			board.setContent("도우너 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
//			boardRepo.save(board);
		}
		memberRepo.save(member2);
		
	}
	
	@Test
	@Order(2)
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(5L).get();
		System.out.println("[ " + board.getSeq() + "번 게시글 정보 ]");
		System.out.println("제목 : " + board.getTitle());
		System.out.println("내용 : " + board.getContent());
		System.out.println("작성자 : " + board.getMember().getName());
		System.out.println("작성자 권한 : " + board.getMember().getRole());
	}
	
	@Test
	@Order(3)
	public void testCascadeDelete() {
		memberRepo.deleteById("member2");
	}
}
