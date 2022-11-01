package kr.or.ddit.basic;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Browser b = new Browser();
		
		b.goURL("1.네이버");
		b.history();
		
		b.goURL("2. 야후");
		b.history();
		
		b.goURL("3.구글");
		b.goURL("4.다음");
		b.history();
		
		System.out.println("뒤로가기 후...");
		b.goBack();
		b.history();
		
		System.out.println("뒤로가기 후...");
		b.goBack();
		b.history();
		
		System.out.println("앞으로가기 후...");
		b.goFoward();
		b.history();
		
		System.out.println("새로운 사이트 접속 후...");
		b.goURL("5.네이트");
		b.history();
		
		
		
	}

}

//웹 브라우저의 앞으로 가기 뒤로가기 기능 구현하기(Stack이용)
class Browser{
	private Stack<String> back; //이전 방문 내용이 저장될 스텍
	private Stack<String> forward; //다음 방문 내용이 저장될 스텍
	private String currentURL; //현재 페이지
	
	//생성자 
	public Browser() {
		back = new Stack<String>();
		forward = new Stack<String>();
		currentURL = "";
	}
	
	//사이트를 방문하는 메서드 => 인수값으로 방문할 url을 지정한다.
	public void goURL(String url){
		System.out.println(url + "사이트에 접속합니다...");
		
		if(currentURL != null && !"".equals(currentURL)) { //현재 보고있는 페이지가 있으면...
			back.push(currentURL);	//현재 페이지를 back스택에 추가한다.
			forward.clear();		//forward 스텍 데이터 전체 삭제
		}
		
		currentURL = url; //현재 페이지 변경
	}
	
	// 뒤로가기
	public void goBack() {
		//back스택이 비어있는지 검사 => isEmpty()
		if(!back.isEmpty()) {
			forward.push(currentURL);	//현재 페이지를forward스텍에 추가
			currentURL = back.pop();	//back스텍에서 1개의 요소를 꺼내와 현재 페이지로 한다
		}
	}
	
	// 앞으로 가기
	public void goFoward() {
		if(!forward.isEmpty()) {
			back.push(currentURL);		//현재 페이지를 back스텍에 추가
			currentURL = forward.pop();	//forward스텍에서 1개의 요소를 꺼내와 현재 페이지로 한다
		}
	}
	
	//방문기록 확인하기
	public void history() {
		System.out.println();
		System.out.println("--------------------------");
		System.out.println("                 방   문   기   록");
		System.out.println("--------------------------");
		System.out.println("back => " + back);
		System.out.println("현재 => " + currentURL);
		System.out.println("foward => " + forward);
		System.out.println("--------------------------");
		System.out.println();
	}
}