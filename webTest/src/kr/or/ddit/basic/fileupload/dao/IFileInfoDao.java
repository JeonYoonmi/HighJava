package kr.or.ddit.basic.fileupload.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.fileupload.vo.FileInfoVO;

public interface IFileInfoDao {
	/**
	 * fileVo에 담겨진 자료를 DB에 insert하는 메서드
	 * @param smc
	 * @param fileVo DB에 insert할 자료가 저장된 FileInfoVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 * @throws SQLException
	 */
	public int insertFileinfo(SqlMapClient smc, FileInfoVO fileVo) throws SQLException;
	
	/**
	 * DB에 있는 전체 파일 목록을 가져와 List에 담아서 반환하는 메서드
	 * @param smc
	 * @return 파일 정보가 저장된 List 객체
	 * @throws SQLException
	 */
	public List<FileInfoVO> getAllFileinfo(SqlMapClient smc) throws SQLException;
	
	/**
	 * fileNo를 인수값으로 받아서 해당 파일 정보를 VO에 담아서 반환하는 메서드
	 * @param smc
	 * @param fileNo 검색할 file 번호
	 * @return 검색된 파일 정보가 저장된 FileInfoVO객체
	 * @throws SQLException
	 */
	public FileInfoVO getFileinfo(SqlMapClient smc, int fileNo) throws SQLException; 
}
