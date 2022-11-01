package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class StudentTest {
/*
		 문제) 학번(int), 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수(모두 int)를 멤버로 갖는
		Student클래스를 만든다.
		이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 한다.
		(이 때 총점은 세 과목의 점수를 이용해서 초기화 한다.)
			
		이 Student객체는 List에 저장하여 관리한다.
			
		List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬기준을 구현하고,
		총점의 역순으로 정렬하는데 총점이 같으면 이름의 내림차순으로 정렬되는 외부 정렬 기준 클래스를 작성하여
		정렬된 결과를 출력하시오.
			
		(단, 등수는 List에 전체 데이터가 추가 된 후에 저장되도록 한다.)
 */
	public static void main(String[] args) {
		List<Student> student = new ArrayList<Student>();
		Random r = new Random();
		student.add(new Student(1, "홍길동", r.nextInt(101), r.nextInt(101), r.nextInt(101)));
		student.add(new Student(5, "이순신", r.nextInt(101), r.nextInt(101), r.nextInt(101)));
		student.add(new Student(9, "성춘향", r.nextInt(101), r.nextInt(101), r.nextInt(101)));
		student.add(new Student(3, "강감찬", r.nextInt(101), r.nextInt(101), r.nextInt(101)));
		student.add(new Student(6, "일지매", r.nextInt(101), r.nextInt(101), r.nextInt(101)));
		student.add(new Student(2, "변학도", r.nextInt(101), r.nextInt(101), r.nextInt(101)));
		
		for (Student std1 : student) {
			for (Student std2 : student) {
				if (std1.getSum() < std2.getSum()) {
					int rank = std1.getRank() + 1;
					std1.setRank(rank);
				}
			}
		}
		
		System.out.println();
		
		System.out.println("정렬전...");
		for(Student std : student) {
			System.out.println(std);
		}
		System.out.println("--------------------------------------");
		
		Collections.sort(student);		
		
		System.out.println("학번의 오름차순 정렬후...");
		for(Student std : student) {
			System.out.println(std);
		}
		System.out.println("--------------------------------------");
		
		Collections.sort(student, new sumDesc()); 		
		
		System.out.println("총점의 내림차순 정렬후...");
		for(Student std : student) {
			System.out.println(std);
		}
		System.out.println("--------------------------------------");
	}

}


class Student implements Comparable<Student>{
	private int num; //학번
	private String name; //이름
	private int kor; //국어점수
	private int eng; //영어점수
	private int math; //수학점수
	private int sum; //총점
	private int rank = 1; //석차
	
	public Student(int num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor + math + eng;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum="
				+ sum + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student std) {
		return Integer.compare(this.getNum(), std.getNum());
//		if(this.getNum() > stu.num) {
//			return 1;
//		}else if(this.getNum() < stu.num) {
//			return -1;	
//		}
//		return 0;
	}
	
}

class sumDesc implements Comparator<Student>{

	public int compare(Student std1, Student std2) {
		if(std1.getSum() == std2.getSum()) {
			return std1.getName().compareTo(std2.getName()) * -1;
		}else {
			return Integer.compare(std1.getSum(), std2.getSum()) * -1;
			
		}
		
//		if(std1.getSum() < std2.getSum()) {
//			return 1;
//		}else if(std1.getSum() > std2.getSum()) {
//			return -1;
//		}else if(std1.getSum() == std2.getSum()) {
//			if(std1.getName().compareTo(std2.getName()) > 0) {
//				return -1;
//			}else if(std1.getName().compareTo(std2.getName()) < 0) {
//				return 1;
//			}
//		}
//		return 0;
	}

}