package kr.or.ddit.basic;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jTest {
	// Logger 클래스의 인스턴스를 구한다. (로그기록을 남길 클래스를 지정해서...)
	static Logger logger =  Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		// 로그기록 남기기
		// 형식1) Logger객체변수.로그레벨이름("출력할 메시지");
		//			==> 로그레벨 이름은 소문자로 기술한다.
//		logger.trace("이것을 log4j의 [TRACE]레벨");	//레벨은 소문자로 적어준다
//		logger.debug("이것을 log4j의 [DEBUG]레벨");
//		logger.info("이것을 log4j의 [INFO]레벨");
//		logger.warn("이것을 log4j의 [WARN]레벨");
//		logger.error("이것을 log4j의 [ERROR]레벨");
//		logger.fatal("이것을 log4j의 [FETAL]레벨");

		// 형식2) Logger객체변수.log(Level.레벨이름, "출력할 메세지");
		logger.log(Level.TRACE, "이것을 log4j의 [TRACE]레벨");
		logger.log(Level.ERROR, "이것을 log4j의 [ERROR]레벨");
	}

}
