package kr.or.ddit.basic.json;

public class SampleVO {
	private int num;
	private String name;
	private String addr;
	   
	public SampleVO() {
	      
	}

	public SampleVO(int num, String name, String addr) {
	   this.num = num;
	   this.name = name;
	   this.addr = addr;
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

	public String getAddr() {
	   return addr;
	}

	 public void setAddr(String addr) {
	     this.addr = addr;
	 }

}
