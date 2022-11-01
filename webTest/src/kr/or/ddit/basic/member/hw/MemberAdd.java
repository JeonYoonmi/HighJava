package kr.or.ddit.basic.member.hw;

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

@WebServlet("/memberAdd.do")
@MultipartConfig
public class MemberAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uploadFilePath = "D:\\D_Other\\uploadedFiles";
		
		File fileUploadDir = new File(uploadFilePath);
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("chkpass");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);
		System.out.println(memVo.getMem_id());
		System.out.println(memVo.getMem_pass());
		System.out.println(memVo.getMem_name());
		System.out.println(memVo.getMem_tel());
		System.out.println(memVo.getMem_addr());
		
		Part part = request.getPart("photo");
		
		if(part != null) {
			String photo = extractFileName(part);
			if(!"".equals(photo)) {
				try {
					part.write(uploadFilePath + File.separator + id);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		IMemberService service = MemberServiceImpl.getInstance();
		service.insertMember(memVo);
		
		response.sendRedirect(request.getContextPath() + "/memberList.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

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
