package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.pnu.domain.BoardVO;

public class BoardService {
	// boardVO를 담을 리스트 생성
	List<BoardVO> list;
	
	// BoardService 생성자
	public BoardService() {
		list = new ArrayList<>();
		
		for (int i = 1; i <= 5; i++) {
			//Builder 패턴을 이용한 객체 생성
			list.add(BoardVO.builder().seq(i).title("title" + i).writer("writer" + i).content("content" + i).createDate(new Date()).cnt(i-1).build());
		}
	}
	//전체 데이터 반환
	public List<BoardVO> getBoards() {
		return list;
	}
	
	//데이터 찾기
	private BoardVO findBoard(Integer seq) {
		for (BoardVO b : list) {
			if (b.getSeq() == seq)
				return b;
		}
		return null;
	}
	
	//BoardVO에서 seq가 seq인 데이터 찾아서 리턴
	//변수 쓰기
	public BoardVO getBoard(Integer seq) {
		return findBoard(seq);
	}
	
	// 입력되어 있는 seq중 최대값에 +1 
	private int getNextSeq() {
		int mid = -1;
		for (BoardVO b : list) {
			if (mid < b.getSeq())
				mid = b.getSeq();
		}
		return mid + 1;
	}
	
	//add
	public BoardVO addBoard(BoardVO boardVO) {
		boardVO.setSeq(getNextSeq());
		boardVO.setCreateDate(new Date());
		
		list.add(boardVO);
		return boardVO;
	}
	
	//update
	public BoardVO updateBoard(BoardVO boardVO) {
		BoardVO ub = findBoard(boardVO.getSeq());
		if (boardVO.getTitle() != null) ub.setTitle(boardVO.getTitle());
		if (boardVO.getContent() != null) ub.setContent(boardVO.getContent());
		if (boardVO.getWriter() != null) ub.setWriter(boardVO.getWriter());
		return ub;
	}
	
	//delete
	public BoardVO deleteBoard(Integer seq) {
		for (int i = 0; i < list.size(); i++) {
			BoardVO b = list.get(i);
			if (b.getSeq() == seq) {
				list.remove(i);
				return b;
			}
		}
		return null;
	}
	
}
