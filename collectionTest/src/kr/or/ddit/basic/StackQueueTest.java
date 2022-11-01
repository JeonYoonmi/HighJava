package kr.or.ddit.basic;

import java.util.LinkedList;
import java.util.Stack;

public class StackQueueTest {
/*
 * Stack => 후입선출(LIFU)의 자료구조(ex. ctrl+z, 웹브라우저에 앞으로가기 뒤로가기, call stack)
 * Queue => 선입선출(FIFU)의 자료구조
 * 가비지 컬랙터 : heap에서 쓰고 있지 않은 것들을 삭제 처리 한다 어느 위치든 순서 상관없이 => stack도 queue도 아니다.
 */
	public static void main(String[] args) {
		//call stack
		System.out.println("메서드 호출 전");
		
		String tem = test();
			
		System.out.println("메서드 호출 후 temp => " + tem);
		
		
		/*
		 * 	Stack의 명령
		 * 1. 자료 입력 : push(입력값)
		 * 2. 지료출력 : pop() => 스택에서 자료를 꺼내온 후 자료를 스텍에서 삭제한다.
		 * 			  peek() => 삭제 없이 자료를 꺼내온다.
		 */
		Stack<String> stack = new Stack<String>();
		
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("변학도");
		stack.push("강감찬");
		
		System.out.println("현재 stack : " + stack);
		System.out.println();
		
		String data = stack.pop();
		System.out.println("꺼내온 값 : " +  data);
		System.out.println("현재 stack : " + stack);
		System.out.println();
		
		System.out.println("꺼내온 값 : " +  stack.pop());
		System.out.println("현재 stack : " + stack);
		System.out.println();

		stack.push("성춘향");
		System.out.println("추가 후 stack : " + stack);
		System.out.println("꺼내온 값 : " +  stack.pop());
		System.out.println("현재 stack : " + stack);
		System.out.println();
		
		System.out.println("삭재없이 꺼내온 값 : " + stack.peek());
		System.out.println("현재 stack : " + stack);
		
		System.out.println("--------------------------------");
		System.out.println();
		
		/*
		 * 	Queue의 명령
		 * 1. 자료 입력 : offer(입력값)
		 * 2. 자료 출력 : poll() => 자료를 Queue에서 꺼내온 후, 그 자료를 Queue에서 삭제한다.
		 * 			  peek() => 삭제없이 자료를 꺼내온다.
		 */
		
		LinkedList<String> queue = new LinkedList<String>();
		
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("변학도");
		queue.offer("강감찬");
		
		System.out.println("현재 queue : " + queue);
		
		String temp = queue.poll();
		System.out.println("꺼내온 값 : " + temp);
		System.out.println("현재 queue : " + queue);
		System.out.println();
		
		System.out.println("꺼내온 값 : " +  queue.poll());
		System.out.println("현재 queue : " + queue);
		System.out.println();
		
		queue.offer("성춘향");
		System.out.println("현재 queue : " + queue);
		System.out.println("꺼내온 값 : " + queue.poll());
		System.out.println("현재 queue : " + queue);
		System.out.println();
		
		System.out.println("삭재없이 꺼내온 값 : " + queue.peek());
		System.out.println("현재 queue : " + queue);
		System.out.println();
	}

	static String test() {
		System.out.println("test");
		return "test return";
	}
	
	
}
