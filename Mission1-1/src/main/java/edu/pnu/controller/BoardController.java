package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BoardVO;
import edu.pnu.service.BoardService;

@RestController
public class BoardController {

	// boardservice 생성
	private BoardService boardService = new BoardService(); 
	
	@GetMapping("/board")
	public List<BoardVO> getBoards() {
		return boardService.getBoards();	
	}
	
	@GetMapping("/board/{seq}")
	public BoardVO getBoard(@PathVariable Integer seq) {
		return boardService.getBoard(seq);
	}
	
	@PostMapping("/board")
	public BoardVO addBoard(BoardVO boardVO) {
		return boardService.addBoard(boardVO);
	}
	
	@PutMapping("/board")
	public BoardVO updateBoard(BoardVO boardVO) {
		return boardService.updateBoard(boardVO);
	}
	
	@DeleteMapping("/board/{seq}")
	public BoardVO deleteBoard(@PathVariable Integer seq) {
		return boardService.deleteBoard(seq);
	}
	
}
