package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
	Vector, hashtable등 예전부터 존재하던 Collection객체들은 내부에 동기화 처리가 되어 있다.
	
	그런데, 세로 구성된 Collection들은 동기화 처리가 되어있지 않다. 그래서, 동기화가 필요한 프로그램에서
	이런 Collection들을 사용하려면 동기화 처리를 한 후에 사용해야 한다.
*/
public class ThreadTest17 {
	private Vector<Integer> vec = new Vector<Integer>();	//Vector와 Thread의 차이 : Vector는 자동으로 동기화 처리가 된다.
	
	//동기화 처리가 안된 List
	private List<Integer> list1 = new ArrayList<Integer>();
	
	//동기화 처리한 List
	private List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());
	
	private void myStart() {
		//익명 구현채 만들기
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<10000; i++) {
//					vec.add(i);
//					list1.add(i);
					list2.add(i);
				}
			}
		};
		
		Thread[] ths = new Thread[] {
			new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r)
		};
		
		for (Thread th : ths) {
			th.start();
		}
		
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
//		System.out.println("vec의 개수 : " + vec.size());
//		System.out.println("list1의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size());
	}
	
	public static void main(String[] args) {
		new ThreadTest17().myStart();
	}




}
