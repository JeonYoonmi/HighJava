package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	/**
	 * 게시물을 추가하는 메서드
	 * @param conn
	 * @param boardVo
	 * @return 작업성공 : 1, 작업실패: 0
	 * @throws SQLException
	 */
	public int insertBoard(Connection conn, BoardVO boardVo) throws SQLException;
	
	/**
	 * 수정하는 메서드
	 * 
	 * @param conn
	 * @param boardNo
	 * @return 작업성공 : 1, 작업실패: 0
	 * @throws SQLException
	 */
	public int updateBoard(Connection conn, BoardVO boardVo) throws SQLException;
	
	/**
	 * 게시물을 삭제하는 메서드
	 * @param conn
	 * @param boardNo
	 * @return 작업성공 : 1, 작업실패: 0
	 * @throws SQLException
	 */
	public int deleteBoard(Connection conn, int boardNo) throws SQLException;
	
	/**
	 * 모든 게시물을 반환하는 메서드
	 * @param conn
	 * @return BoardVo객체가 저장된 List
	 * @throws SQLException
	 */
	public List<BoardVO> getAllBoard(Connection conn) throws SQLException;
	
	/**
	 * 원하는 게시물만 반환하는 메서드
	 * @param conn
	 * @param boardTitle
	 * @return 원하는 BoardVo객체가 저장된 List
	 * @throws SQLException
	 */
	public List<BoardVO> searchBoard(Connection conn, String boardTitle) throws SQLException;
	
	/**
	 * 조회수를 증가시키는 메서드
	 * @param conn
	 * @param boardCnt
	 * @return
	 * @throws SQLException
	 */
	public int updateBoardCnt(Connection conn, int boardCnt) throws SQLException;
	
	/**
	 * 게시물 상세보기 메서드
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws SQLException
	 */
	public Map<Integer, BoardVO> selectBoard(Connection conn, int boardNo) throws SQLException;
}
