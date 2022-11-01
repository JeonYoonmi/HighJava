package kr.or.ddit.basic.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.basic.fileupload.service.FileInfoServiceImpl;
import kr.or.ddit.basic.fileupload.service.IFileInfoService;
import kr.or.ddit.basic.fileupload.vo.FileInfoVO;

/*
	- Servlet3.0이상에서 파일 업로드를 하려면 서블릿에 @MultipartConfig 애노테이션을 설정해야한다.
	
	- @multipartConfig에노테이션의 설정 변수를...
	  1) location : 업로드한 파일이 임시적으로 저장될 경로 지정(기본값 : "" --> 시스템의 임시폴더)
	  2) fileSizeThreshold : 이 곳에 지정한 값보다 큰 파일이 전송되면 location에 지정한 폴더에 임시로 저장된다.
	  				(단위 : byte. 기본값 : 0 (무조건 임시 디렉토리 사용))
	  3) maxFileSize : 1개 파일의 최대 크기 (단위 : byte, 기본값 : -1L(무제한))
	  4) maxRequestSize : 서버로 전송되는 request데이터 전체의 최대크기
	  					(모든 파일 크기 + form Data) (단위 : byte, 기본값 : -1L(무제한))
*/
@WebServlet("/fileUpload.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30
)
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET방식 호출할 때는 fileUpload폼을 보여주는 jsp문서로 forwarding한다.
		request.getRequestDispatcher("/basic/fileupload/fileUpload.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST방식으로 호출할 때는 클라이언트가 보낸 파일을 받아서 처리한다.
		request.setCharacterEncoding("utf-8");
		
		//업로드한 파일들이 저장될 폴더 설정
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 만들어 놓는다.
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//--------------------------------------------------
		//파일이 아닌 일반 데이터는 getParameter()메서드나 getParameterValues()메서드를 이용해서 구한다.
		String username = request.getParameter("username");
		System.out.println("일반 파라미터 데이터 : " + username);
		
		//--------------------------------------------------
		
		// 전송되어 온 파일 데이터 처리하기
		String fileName = "";		// 전송되어 온 파일의 이름이 저장될 변수 선언
		
		// 전송된 파일이 2개 이상일 경우에는 파일 정보를 넣어서 처리하기 위한 List객체를 생성한다.
		List<FileInfoVO> fileList = new ArrayList<FileInfoVO>();
		
		/*
			- Servlet 3.0 이상에서 파일 처리를 위해 추가된 메서드들...
			  1) request.getParts() ==> 전체 Part객체는 Collection객체에 담아서 반환한다.
			  2) request.getPart("part이름") ==> 지정한 'part이름'을 가진 개별 Part객체를 반환한다.		-> 보통 name깂이 part이름이 된다
		*/
		
		// 전채 Part객체 개수만큼 반복 처리
		for (Part part : request.getParts()) {
			fileName = extractFileName(part);	// 파일명 구하기
			
			// 찾은 파일명이 공백("")이면 이것은 파일이 아닌 일반 파라미터라는 의미이다.
			if(!"".equals(fileName)) {	// 파일인지 검사...
				// 1개의 Upload파일에 대한 정보를 저장할 VO객체 생성
				FileInfoVO fvo = new FileInfoVO();
				
				fvo.setFile_writer(username);	// 작성자를 VO에 셋팅
				fvo.setOrigin_file_name(fileName);	// 실제 파일명을 VO에 셋팅
				
				// 실제 저장되는 파일 이름이 중복되는 것을 방지하기 위해서 UUID를 이용하여 저장할 파일명을 만든다.
				String saveFileName = UUID.randomUUID().toString();
				fvo.setSave_file_name(saveFileName);	// 저장될 파일명을 VO에 셋팅
				
				// Part객체의 getSize()메서드를 이용해서 업로드 되는 파일의 크기를 알 수 있다. (단위 : byte)
				// byte단위의 파일 크기를 KB단위로 변환해서 VO에 셋팅한다.
				fvo.setFile_size((int)Math.ceil(part.getSize() / 1024.0));	// ceil : 올림
				
				try {
					// Part객체의 write()메서드를 이용하여 업로드된 파일을 지정된 위치에 저장한다.
					// 형식) part.write("저장될 경로명/저장될파일명")
					part.write(uploadPath + File.separator + saveFileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				fileList.add(fvo);
			}	// if문 끝...
			
		}	// for문 끝...
		
		// List에 저장된 파일정보 데이터를 DB에 insert한다.
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		for (FileInfoVO fvo : fileList) {
			service.insertFileinfo(fvo);
		}
		
		// 저장이 완료된 후 파일 목록을 보여준다.
		response.sendRedirect(request.getContextPath() + "/fileList.do");
	}
	
	//--------------------------------------------------
	
	/*
		- Part객체의 구조
		  1) 파일이 아닌 일반 데이터일 경우
		  -------------------------95fasffsdf5-ruuhfe89fu	==>파트를 구분하는 구분선
		  content-disposition: form-data; name="username"	==> 파라미터 명
		  							==> 빈줄
		  hong						==> 파라미터 값
		  
		  2) 파일 일 경우
		  -------------------------95fasffsdf5-ruuhfe89fu	==>파트를 구분하는 구분선
		  //  content-disposition,  content-type : 파일 헤더
		  content-disposition: form-data; name="upFile1"; filename="test1.txt"	==> 파일 정보
		  content-type: taxt/plain	==> 파일의 종류
		  							==> 빈줄
		  abcd1234안녕하세요			==> 파일 내용
	*/
	// Part 구조 안에서 파일명을 찾는 메서드
	private String extractFileName(Part part) {
		String fileName = "";	
		String contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";");
		for (String item : items) {
			if(item.trim().startsWith("filename")) {
				// filename="test1.txt"
				fileName = item.substring(item.indexOf("=")+2, item.length()-1);
			}
		}
		
		return fileName;
	}

}















