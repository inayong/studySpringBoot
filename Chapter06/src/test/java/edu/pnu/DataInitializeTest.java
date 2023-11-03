package edu.pnu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class DataInitializeTest {

	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@DisplayName("테이블(board)에 데이터를 입력")
//	@Test
	public void testDataInsert() {
		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setWriter("둘리");
			board.setTitle("둘리가 등록한 게시물" + i);
			board.setContent("둘리가 등록한 내용" + i);
			boardRepo.save(board);
		}
		
		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setWriter("도우너");
			board.setTitle("도우너가 등록한 게시글" + i);
			board.setContent("도우너가 등록한 내용" + i);
			boardRepo.save(board);
		}
	}
	
	@DisplayName("데이터초기화")
	@Test
	public void testDataInsert2() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setName("둘리");
		member1.setPassword("member111");
		member1.setRole("ROLE_USER");
		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setName("도우너");
		member2.setPassword("member222");
		member2.setRole("ROLE_ADMIN");
		memberRepo.save(member2);
		
		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setWriter("둘리");
			board.setTitle("둘리가 등록한 게시글 " + i);
			board.setContent("둘리가 등록한 내용 " + i);
			boardRepo.save(board);
		}
		
		for(int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setWriter("도우너");
			board.setTitle("도우너가 등록한 게시글 " + i);
			board.setContent("도우너가 등록한 내용 " + i);
			boardRepo.save(board);
		}
	}
}
