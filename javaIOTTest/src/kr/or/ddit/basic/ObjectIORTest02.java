package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectIORTest02 {
		public static void main(String[] args) {
			// 이 예제는 객체를 읽어오는 ReadObject()메서드에서 파일의 끝까지 읽었을 떄
			// EOFException이 발생하는데 이 예외처리를 하지 않고 사용하는 방법의 예제이다.
			
			// 자료를 읽어올 떄 EOFException이 발생하지 않게 하려면 모든 객체를 저장한 후
			// 마지막에 null을 저장하고, 이 null값을 읽어올 떄 확인하는 방법을 사용한다.
			
			// Member의 인스턴스 생성
			Member mem1 = new Member("홍길동", 20, "대전");
			Member mem2 = new Member("홍길서", 20, "서울");
			Member mem3 = new Member("홍길남", 20, "인천");
			Member mem4 = new Member("홍길북", 20, "울산");

			//출력용으로 사용한 try-catch
			try {
				// 객체를 파일에 저장하기
				
				//출력용 스트림 객체 생성
				FileOutputStream fout = new FileOutputStream("d:/d_other/memObject2.bin");
				//보조스트림은 여러개 쓸 수 있음
				BufferedOutputStream bout = new BufferedOutputStream(fout);
				ObjectOutputStream oout = new ObjectOutputStream(bout);
				
				//쓰기 작업
				System.out.println("객체 저장하기 시작...");
				
				oout.writeObject(mem1);
				oout.writeObject(mem2);
				oout.writeObject(mem3);
				oout.writeObject(mem4);
				oout.writeObject(null);	//끝을 나타낼 목적으로 마지막에 null을 저장한다.
				System.out.println("객체 저장하기 끝...");
				System.out.println();
				
				oout.close();	//스트림 닫기
				
			} catch (IOException e) {

			}
			
			////////////////////////////////////////////////////////////////
			
			//저장된 객체를 읽어와 그 내용을 화면에 출력하기
			
			//입력용 스트림 객체 생성
			ObjectInputStream oin = null;
			try {
				oin = new ObjectInputStream(
							new BufferedInputStream(
									new FileInputStream("d:/d_other/memObject2.bin")
									)
							);
				
				Object obj;		//읽어온 객체를 저장할 변수
				System.out.println("객체 읽기 작업 시작...");
				
				//readObject()메서드가 데이터를 끝까지 다 읽어오면  EOFException이 발생한다.
				//이 EOFException이 발생하면 끝까지 잘 읽어왔다는 의미가 된다.
				
				
				while((obj=oin.readObject()) != null) {	//읽어온 값이 null인지 확인해서 처리한다.
					//읽어온 데이터를 원래의 객체형으로 형변환 후 사용한다.
					Member mem = (Member)obj;
					System.out.println("이름 : " + mem.getName());
					System.out.println("나이  : " + mem.getAge());
					System.out.println("주소 : " + mem.getAddr());
					System.out.println("------------------------");
				}
				System.out.println("객체 읽기 작업 끝...");
				
//			} catch (EOFException e) {
//				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if(oin != null) {
					try {
						oin.close();	//스트림 닫기
					} catch (IOException e2) {
						
					}
				}
			}
		}
}
