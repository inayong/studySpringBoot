package edu.pnu.board.service;

import org.springframework.data.domain.Page;

import edu.pnu.board.domain.Board;
import edu.pnu.board.domain.Search;

public interface BoardService {

//	Page<Board> getBoardList(Board board);
	Page<Board> getBoardList(Search search);
	void insertBoard(Board board);
	void updateBoard(Board board);
	void deleteBoard(Board board);
	Board getBoard(Board board);
}
