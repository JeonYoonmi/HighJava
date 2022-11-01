package kr.or.ddit.basic.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.basic.fileupload.service.FileInfoServiceImpl;
import kr.or.ddit.basic.fileupload.service.IFileInfoService;
import kr.or.ddit.basic.fileupload.vo.FileInfoVO;

@WebServlet("/fileDownload.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어온 fileno값을 구한다.
		String strFileno = request.getParameter("fileno");
		int fileNo = Integer.parseInt(strFileno);
		
		// fileno값을 이용하여 DB에서 해당 파일 정보를 가져온다.
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		FileInfoVO fvo = service.getFileinfo(fileNo);
		
		// 업로드한 파일들이 저장될 폴더 설정
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 만들어 놓는다.
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		response.setCharacterEncoding("utf-8");
		
		// 다운 받을 파일의 File객체 생성 ==> 실제 저장된 파일명을 지정하여 생성한다.
		File downFile = new File(file, fvo.getSave_file_name());
		
		if(downFile.exists()) {	// 파일이 있을 때...
			response.setContentType("application/octet-stream; charset=utf-8");
			
			String headerKey = "content-disposition";
			
			// 이곳에는 다운로드 할 때 클라이언트에 저장될 파일 이름을 지정하는 곳으로 원래의 파일명으로 지정한다.
//			String headerValue = "attachment; filename=\"" + fvo.getOrigin_file_name() + "\";"; 
			String headerValue = "attachment; " + getEncodingFileName(request, fvo.getOrigin_file_name());
			
			response.setHeader(headerKey, headerValue);
			
			BufferedOutputStream out = null;
			BufferedInputStream in = null;
			try {
				// 출력용 스트림 객체 생성 ==> response객체 이용
				out = new BufferedOutputStream(response.getOutputStream());
				
				// 파일 입력용 스트림 객체 생성
				in = new BufferedInputStream(new FileInputStream(downFile));
				
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
			
			
		}else {	//파일이 없을 때...
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<h3>" + fvo.getOrigin_file_name() + "파일이 존재하지 않습니다.</h3>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	// 다운로드 파일명에 한글이 있을 떄 한글이 깨지는 것을 방지하는 메서드
	private String getEncodingFileName(HttpServletRequest request, String filename) {
		String encodedFilename = "";
		String userAgent = request.getHeader("USer-Agent");
		
		try {
			// MSIE 10버전 이하의 웹브라우저
			if(userAgent.contains("MSIE") || userAgent.contains("Trident")) {
				encodedFilename = "filename\"" + URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "\\ ") + "\"";
			}else {	// 기타 웹 브라우저
				encodedFilename = "filename*=UTF-8''" + URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "%20");
				
			}
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("지원하지 않는 인코딩 방식입니다...");
		}
		
		return encodedFilename;
	}

}
