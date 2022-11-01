package kr.or.ddit.basic.fileupload.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.fileupload.vo.FileInfoVO;

public class FileInfoDaoImpl implements IFileInfoDao {
	private static FileInfoDaoImpl dao;
	
	private FileInfoDaoImpl() {}
	
	public static FileInfoDaoImpl getInstance() {
		if(dao==null) dao = new FileInfoDaoImpl();
		
		return dao;
	}

	@Override
	public int insertFileinfo(SqlMapClient smc, FileInfoVO fileVo) throws SQLException {
		int cnt = 0;
		Object obj = smc.insert("fileinfo.insertFileinfo", fileVo);
		if(obj==null) cnt = 1;
		
		return cnt;
	}

	@Override
	public List<FileInfoVO> getAllFileinfo(SqlMapClient smc) throws SQLException {
		return smc.queryForList("fileinfo.getAllFileinfo");
	}

	@Override
	public FileInfoVO getFileinfo(SqlMapClient smc, int fileNo) throws SQLException {
		return (FileInfoVO)smc.queryForObject("fileinfo.getFileinfo", fileNo);
	}
	
	
}
