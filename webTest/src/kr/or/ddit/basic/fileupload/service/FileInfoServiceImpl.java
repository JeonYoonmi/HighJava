package kr.or.ddit.basic.fileupload.service;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.fileupload.dao.FileInfoDaoImpl;
import kr.or.ddit.basic.fileupload.dao.IFileInfoDao;
import kr.or.ddit.basic.fileupload.vo.FileInfoVO;
import kr.or.ddit.ibatis.util.SqlMapClientFactory;

public class FileInfoServiceImpl implements IFileInfoService {
	private SqlMapClient smc;
	private IFileInfoDao dao;
	
	private static FileInfoServiceImpl service;
	
	private FileInfoServiceImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
		dao = FileInfoDaoImpl.getInstance();
	}
	
	public static FileInfoServiceImpl getInstance() {
		if(service==null) service = new FileInfoServiceImpl();
		
		return service;
	}
	
	
	
	@Override
	public int insertFileinfo(FileInfoVO fileVo) {
		int cnt = 0;
		try {
			cnt = dao.insertFileinfo(smc, fileVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<FileInfoVO> getAllFileinfo() {
		List<FileInfoVO> fileList = null;
		try {
			fileList = dao.getAllFileinfo(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileList;
	}

	@Override
	public FileInfoVO getFileinfo(int fileNo) {
		FileInfoVO fileVo = null;
		try {
			fileVo = dao.getFileinfo(smc, fileNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileVo;
	}

}
