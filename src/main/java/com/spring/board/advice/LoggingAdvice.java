package com.spring.board.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//aop구현
@Component //스프링이 자동 객체 생성
@Aspect 
public class LoggingAdvice {
	//com.spring.myapp.controller 하위의 모든 클래스와 메소드를 실행하기 전에
	//반환형 패키지명.클래스명.메소드명(..)
	@Before("execution(* com.spring.board.*.*.*(..))")
	public void beforeLog(JoinPoint jp) {
		//적용대상(joinpoint)의 메소드명 + 매개변수를 출력
		System.out.println(jp.getSignature().toShortString() + ":" + Arrays.toString(jp.getArgs()));
	}
	
	//리턴값 출력
	//@AfterReturning : 정상수행후 리턴 
	@AfterReturning(pointcut = "execution(* com.spring.board.service.*.*(..))", returning = "rObj")
	private void afterLog(JoinPoint jp, Object rObj) {
		System.out.println(jp.getSignature().toShortString()); //실행된 메소드명 
		if(rObj != null)
			System.out.println("리턴값 : " + rObj.toString());
		else
			System.out.println("리턴값 : 없음");
	}
	
	//@Around : 실행대상의 시작과 끝을 체크
//	@Around("execution(* com.spring.member.service.*.*(..))")
//	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
//		//시작시간
//		long startTime = System.currentTimeMillis();  //시스템의 시간을 1/1000초 단위로 읽기
//		System.out.println(pjp.getSignature().toShortString()); //실행된 메소드명 
//		Object result = pjp.proceed(); //실행되어야할 메소드
//		//끝시간
//		long endTime = System.currentTimeMillis();
//		//소요시간
//		System.out.println("소요시간:" + (endTime-startTime));
//		
//		return result; //실행되어야할 메소드를 호출한 메소드에게 리턴값 전달
//	}
}
