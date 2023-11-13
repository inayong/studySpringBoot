package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.board.domain.Board;
import edu.pnu.board.domain.Member;
import edu.pnu.board.domain.Role;
import edu.pnu.board.persistence.BoardRepository;
import edu.pnu.board.persistence.MemberRepository;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testInsert() {
		Member mem1 = new Member();
		mem1.setId("member");
		mem1.setPassword(encoder.encode("member123"));
		mem1.setName("둘리");
		mem1.setRole(Role.ROLE_MEMBER);
		mem1.setEnabled(true);
		memberRepo.save(mem1);
		
		Member mem2 = new Member();
		mem2.setId("admin");
		mem2.setPassword(encoder.encode("admin123"));
		mem2.setName("도우너");
		mem2.setRole(Role.ROLE_ADMIN);
		mem2.setEnabled(true);
		memberRepo.save(mem2);
		
		for (int i = 1; i <= 13; i++) {
			Board board = new Board();
			board.setMember(mem1);
			board.setTitle(mem1.getName() + "가 등록한 게시글" + i);
			board.setContent(mem1.getName() + "가 등록한 내용" + i);
			boardRepo.save(board);
		}
		
		for (int i = 1; i <= 13; i++) {
			Board board = new Board();
			board.setMember(mem2);
			board.setTitle(mem2.getName() + "가 등록한 게시글" + i);
			board.setContent(mem2.getName() + "가 등록한 내용" + i);
			boardRepo.save(board);
		}
	}
	
//	@Test
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		
		System.out.println("[" + board.getSeq() + "번 게시글 상세 정보 ]");
		System.out.println("제목 \t : " + board.getTitle());
		System.out.println("작성자\t : " + board.getMember().getName());
		System.out.println("내용 \t : " + board.getContent());
		System.out.println("등록일\t : " + board.getCreateDate());
		System.out.println("조회수\t : " + board.getCnt());
	}
	
//	@Test
	public void testGetBoardList() {
		Member member = memberRepo.findById("member").get();
		
		System.out.println("[" + member.getName() + "가 등록한 게시글 ]");
		for (Board board : member.getBoardList()) {
			System.out.println("---> " + board.toString());
		}
	}
}
