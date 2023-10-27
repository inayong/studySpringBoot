package edu.pnu;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DynamicQueryTest2 {

	@Autowired
	private DynamicBoardRepository boardRepo;
	
	@Test
	@Order(1)
	public void test1() {
		//데이터 100개 입력
		Random rd = new Random();
		for (int i = 0; i < 100; i++) {
			boardRepo.save(Board.builder()
					.title("title" + i)
					.writer("writer" + i)
					.content("content" + i)
					.cnt(rd.nextLong(100))
					.build()
					);
		}
		
		String searchCondition = "title";
		String searchKeyword = "title1";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("title")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		}
		
		Pageable paging = PageRequest.of(0, 10);
		Page<Board> boardList = boardRepo.findAll(builder, paging);
		
//		List<Board> boardList = boardRepo.findByTitleContaining("%" + searchKeyword + "%");
		
		System.out.println("검색결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
}
