package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface IJdbcBoardDao {
	
	/**
	 * JdbcBoardVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param conn Connection객체
	 * @param boardVo insert할 자료가 저장된 JdbcBoardVo객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 * @throws SQLException
	 */
	public int insertBoard(Connection conn, JdbcBoardVO boardVo) throws SQLException;
	
	/**
	 * 게시글 번호를 인수값으로 받아서 해당 게시글을 삭제하는 메서드
	 * 
	 * @param conn Connection객체
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 * @throws SQLException
	 */
	public int deleteBoard(Connection conn, int boardNo) throws SQLException;
	
	/**
	 * 수정할 내용이 담긴 JdbcBoardVO객체를 인수값으로 받아서 DB자료를 update하는 메서드
	 * 
	 * @param conn Connection객체
	 * @param boardVo 수정할 자료가 저장된 JdbcBoardVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 * @throws SQLException
	 */
	public int updateBoard(Connection conn, JdbcBoardVO boardVo) throws SQLException;
	
	/**
	 * 개시글 번호를 인수값으로 받아서 해당 게시글 정보의 내용을 가져와 반환하는 메서드
	 * 
	 * @param conn Connection객체
	 * @param boardNo 게시글 번호
	 * @return 게시글 번호에 맞는 자료가 있으면 해당 게시글이 담긴 JdbcBoardVO객체, 자료가 없으면 null을 반환한다.
	 * @throws SQLException
	 */
	public JdbcBoardVO getBoard(Connection conn, int boardNo) throws SQLException;
	
	/**
	 * DB의 JDBC_BOARD테이블의 전체 데이터를 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @param conn Connection객체
	 * @return 잔체 데이터가 저장된 List객체
	 * @throws SQLException
	 */
	public List<JdbcBoardVO> getAllBoardList(Connection conn) throws SQLException;
	
	/**
	 * 게시글의 제목을 이용하여 게시글을 검색하는 메서드
	 * 
	 * @param conn Connection객체
	 * @param title 검색할 게시글의 제목
	 * @return 검색 결과가 저장된 List객체
	 * @throws SQLException
	 */
	public List<JdbcBoardVO> getSearchBoardList(Connection conn, String title) throws SQLException;
	
	/**
	 * 게시글 번호를 인수값으로 받아서 해당 게시글의 조회수를 증가시키는 메서드
	 * 
	 * @param conn Connection객체
	 * @param boardNo 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 * @throws SQLException
	 */
	public int setCountIncrement(Connection conn, int boardNo) throws SQLException;
}








