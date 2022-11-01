package kr.or.ddit.basic.file;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		//File객체 만들기 연습
		
		//1. new File(String 파일 또는 경로)
		//		=> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분 문자는 '\'를 사용하거나 '/'를 사용할 수 있다.
		File file1 = new File("d:/d_other/test.txt");	//구분 문자를 '/'로 했을 떄
		File file2 = new File("d:\\d_other\\test.txt");	//구분 문자를 '\'로 했을 떄
		
		System.out.println("파일명1 : " + file1.getName());
		System.out.println("파일명2 : " + file2.getName());
		System.out.println("디렉토리인가??? => " + file2.isDirectory());
		System.out.println("파일인가??? => " + file2.isFile());
		System.out.println();
		
		File file3 = new File("d:/d_other");	//폴더정보만 나옴
		System.out.println("파일명3 : " + file3.getName());
		System.out.println("디렉토리인가??? => " + file3.isDirectory());
		System.out.println("파일인가??? => " + file3.isFile());
		System.out.println();
		
		//2. new File(File parent, String child) => 파일 1, 2와 동일
		//		=> 'parent'디렉토리(폴더) 안에 있는 'child'파일에 대한 정보를 갖는다.
		File file4 = new File(file3, "test.txt");
		System.out.println("파일명4 : " + file4.getName());
		System.out.println("디렉토리인가??? => " + file4.isDirectory());
		System.out.println("파일인가??? => " + file4.isFile());
		System.out.println();
		
		//3. new File(String parent, String child)
		File file5 = new File("d:/d_other", "test.txt");
		System.out.println("파일명5 : " + file5.getName());
		System.out.println("디렉토리인가??? => " + file5.isDirectory());
		System.out.println("파일인가??? => " + file5.isFile());
		System.out.println();
		
		//디렉토리(폴더)만들기
		/*
		 - mkdir() => File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
					=> 반환값 : 만들기 성공(true), 만들기 실패(false)
					=> 중간부분에 경로가 모두 만들어져 있어야 마지막 위치의 경로를 만들 수 있다.

		 - mkdirs() => 중간부분의 경로가 없으면 중간부분의 경로도 같이 만들어 준다.
		*/
		File file6 = new File("d:/d_other/연습용");
		System.out.println("파일명6 : " + file6.getName());
		System.out.println("디렉토리인가??? => " + file6.isDirectory());
		System.out.println("파일인가??? => " + file6.isFile());
		System.out.println(file6.getName() + "의 존재여부 : " + file6.exists());
		
		if(!file6.exists()) {
			if(file6.mkdir()) {
				System.out.println(file6.getName() + "만들기 성공");
			}else {
				System.out.println(file6.getName() + "만들기 실패");
			}
		}
		
		File file7 = new File("d:/d_other/test/java/src");
		if(!file7.exists()) {
			if(file7.mkdirs()) {
				System.out.println(file7.getName() + "만들기 성공");
			}else {
				System.out.println(file7.getName() + "만들기 실패");
			}
		}
		
	}

}
