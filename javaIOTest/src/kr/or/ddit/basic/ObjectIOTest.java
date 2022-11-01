package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 객체를 파일에 입출력하는 예제
public class ObjectIOTest {

	public static void main(String[] args) {
		// Member의 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("홍길서", 20, "서울");
		Member mem3 = new Member("홍길남", 20, "인천");
		Member mem4 = new Member("홍길북", 20, "울산");

		//출력용으로 사용한 try-catch
		try {
			// 객체를 파일에 저장하기
			
			//출력용 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/memObject.bin");
			//보조스트림은 여러개 쓸 수 있음
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			
			//쓰기 작업
			System.out.println("객체 저장하기 시작...");
			
			oout.writeObject(mem1);
			oout.writeObject(mem2);
			oout.writeObject(mem3);
			oout.writeObject(mem4);
			System.out.println("객체 저장하기 끝...");
			System.out.println();
			
			oout.close();	//스트림 닫기
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		////////////////////////////////////////////////////////////////
		
		//저장된 객체를 읽어와 그 내용을 화면에 출력하기
		
		//입력용 스트림 객체 생성
		ObjectInputStream oin = null;
		try {
			oin = new ObjectInputStream(
						new BufferedInputStream(
								new FileInputStream("d:/d_other/memObject.bin")
								)
						);
			
			Object obj;		//읽어온 객체를 저장할 변수
			System.out.println("객체 읽기 작업 시작...");
			
			//readObject()메서드가 데이터를 끝까지 다 읽어오면  EOFException이 발생한다.
			//이 EOFException이 발생하면 끝까지 잘 읽어왔다는 의미가 된다.
			//EOFException은 oin.readObject()에서 발생
			while((obj=oin.readObject()) != null) {
				//읽어온 데이터를 원래의 객체형으로 형변환 후 사용한다.
				Member mem = (Member)obj;
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이  : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("------------------------");
			}
			
		} catch (EOFException e) {
			System.out.println("객체 읽기 작업 끝...");
		} catch (IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(oin != null) {
				try {
					oin.close();	//스트림 닫기
				} catch (IOException e2) {
					// TODO: handle exception
				}
			}
		}
	}

}


// 저장 대상이 되는 객체의 class 작성
class Member implements Serializable{
	//아래를 안쓰면 클래스에 노란줄이 뜨는데 그걸 클릭해서 add generated serial vwersion ID를 선택하면 된다.
	private static final long serialVersionUID = 562285606573703211L;	//내부적으로 버전을 조금 더 빨리 알 수 있다.
	
	private String name;
	private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}