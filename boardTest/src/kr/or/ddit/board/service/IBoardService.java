package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	/**
	 * 게시물을 추가하는 메서드
	 * @param boardVo
	 * @return 작업성공 : 1, 작업실패: 0
	 */
	public int insertBoard(BoardVO boardVo);
	
	/**
	 * 수정하는 메서드
	 * 
	 * @param boardNo
	 * @return 작업성공 : 1, 작업실패: 0
	 */
	public int updateBoard(BoardVO boardVo);
	
	/**
	 * 게시물을 삭제하는 메서드
	 * @param boardNo
	 * @return 작업성공 : 1, 작업실패: 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * 모든 게시물을 반환하는 메서드
	 * @return BoardVo객체가 저장된 List
	 */
	public List<BoardVO> getAllBoard();
	
	/**
	 * 원하는 게시물만 반환하는 메서드
	 * @param boardTitle
	 * @return 원하는 BoardVo객체가 저장된 List
	 */
	public List<BoardVO> searchBoard(String boardTitle);
	
	/**
	 * 조회수를 증가시키는 메서드
	 * @param boardCnt
	 * @return
	 */
	public int updateBoardCnt(int boardCnt);
	
	/**
	 * 게시물 상세보기 메서드
	 * @param boardNo
	 * @return
	 */
	public Map<Integer, BoardVO> selectBoard(int boardNo);
}
