package edu.pnu.board.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import edu.pnu.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	
	@Query("select b from Board b") //jpa는 클래스 이름이랑 같아야함(대소문자구분)
	Page<Board> getBoardList(Pageable pageable);
	
}
