package edu.pnu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class DataInitializeTest {

	@Autowired
	private BoardRepository boardRepo;
	
	@DisplayName("테이블(board)에 데이터를 입력")
	@Test
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
}
