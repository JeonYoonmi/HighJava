package kr.or.ddit.basic.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.basic.fileupload.service.FileInfoServiceImpl;
import kr.or.ddit.basic.fileupload.service.IFileInfoService;
import kr.or.ddit.basic.fileupload.vo.FileInfoVO;

@WebServlet(asyncSupported = true, urlPatterns = { "/images/imageSrcView.do" })
public class ImageSrcView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어온 fileno값을 구한다.
		String strFileno = request.getParameter("fileno");
		int fileno = Integer.parseInt(strFileno);
		
		// fileno값을 이용하여 DB에서 해당 파일 정보를 가져온다.
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		FileInfoVO fvo = service.getFileinfo(fileno);
		
		// 업로드한 파일들이 저장될 폴더 설정
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 만들어 놓는다.
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		response.setCharacterEncoding("utf-8");
		
		// 다운 받을 파일의 File객체 생성 ==> 실제 저장된 파일명을 지정하여 생성한다.
		File imageFile = new File(file, fvo.getSave_file_name());
		
		if(imageFile.exists()) {	// 이미지 파일이 있을 때...
			
			BufferedOutputStream out = null;
			BufferedInputStream in = null;
			try {
				// 출력용 스트림 객체 생성 ==> response객체 이용
				out = new BufferedOutputStream(response.getOutputStream());
				
				// 파일 입력용 스트림 객체 생성
				in = new BufferedInputStream(new FileInputStream(imageFile));
				
				byte[] temp = new byte[1024];
				int len = 0;
				
				while((len = in.read(temp))>0) {
					out.write(temp, 0, len);
				}
				out.flush();
				
			} catch (IOException e) {
				System.out.println("입출력 오류 : " + e.getMessage());
			}finally {
				if(in!=null) try { in.close(); } catch(IOException e) {}
				if(out!=null) try { out.close(); } catch(IOException e) {}
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
