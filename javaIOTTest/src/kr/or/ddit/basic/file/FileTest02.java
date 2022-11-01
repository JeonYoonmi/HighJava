package kr.or.ddit.basic.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest02 {

	public static void main(String[] args) {
		File f1 = new File("d:/d_other/test.txt");
		System.out.println(f1.getName() + "의 크기 : " + f1.length() + "byte(s)");
		System.out.println("path : " + f1.getPath());
		System.out.println("absolutePath : " + f1.getAbsolutePath());
		System.out.println();
		// 현재위치 : 지금 작업이 실행되고 있는 위치 => 자바기준에서는 자바가 실행되는 위치
		// 상대경로 : 현재 위치에서 파일을 찾아가는거
		// 절대경로 : 루트(가장 상단)에서부터 찾아가는 방법
		// . : 현재위치 => 자바에서는 아무것도 안쓰면 현재위차로 인식
		// .. : 상위폴더
		
		File f2 = new File("");	// 또는 File f2 = new File(".");
		System.out.println("path : " + f2.getPath());
		System.out.println("absolutePath : " + f2.getAbsolutePath());	//이클립스에서 자바가 실행되는 위치는 src가 아닌 프로젝트 폴더에서 실행
		
		//현재위치를 절대 경로로 변환하는 다른 방법
		
		// 방법1) System.getProperty("user.dir"); 명령 이용하기
		String path1 = System.getProperty("user.dir");
		System.out.println("현재 디렉토리 : " + path1);
		
		// 방법2) 상대 경로에서 절대 경로로 변환
		Path relativePath = Paths.get("");
		String path2 = relativePath.toAbsolutePath().toString();
		System.out.println("현재 디렉토리 : " + path2);
		
	}

}
