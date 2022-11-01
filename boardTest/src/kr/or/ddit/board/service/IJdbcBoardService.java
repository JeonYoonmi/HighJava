package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface IJdbcBoardService {
	/**
	 * JdbcBoardVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param boardVo insert할 자료가 저장된 JdbcBoardVo객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertBoard(JdbcBoardVO boardVo);
	
	/**
	 * 게시글 번호를 인수값으로 받아서 해당 게시글을 삭제하는 메서드
	 * 
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * 수정할 내용이 담긴 JdbcBoardVO객체를 인수값으로 받아서 DB자료를 update하는 메서드
	 * 
	 * @param boardVo 수정할 자료가 저장된 JdbcBoardVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateBoard(JdbcBoardVO boardVo);
	
	/**
	 * 개시글 번호를 인수값으로 받아서 해당 게시글 정보의 내용을 가져와 반환하는 메서드
	 * 
	 * @param boardNo 게시글 번호
	 * @return 게시글 번호에 맞는 자료가 있으면 해당 게시글이 담긴 JdbcBoardVO객체, 자료가 없으면 null을 반환한다.
	 */
	public JdbcBoardVO getBoard(int boardNo);
	
	/**
	 * DB의 JDBC_BOARD테이블의 전체 데이터를 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @return 잔체 데이터가 저장된 List객체
	 */
	public List<JdbcBoardVO> getAllBoardList();
	
	/**
	 * 게시글의 제목을 이용하여 게시글을 검색하는 메서드
	 * 
	 * @param title 검색할 게시글의 제목
	 * @return 검색 결과가 저장된 List객체
	 */
	public List<JdbcBoardVO> getSearchBoardList(String title);
	
	/**
	 * 게시글 번호를 인수값으로 받아서 해당 게시글의 조회수를 증가시키는 메서드
	 * 
	 * @param boardNo 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int setCountIncrement(int boardNo);
}
