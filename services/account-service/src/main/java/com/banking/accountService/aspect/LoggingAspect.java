package com.banking.accountService.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Around("execution (* com.banking.accountService.services.*.*(..)")
	public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable{
		
		String method = joinPoint.getSignature().toShortString();
        long start = System.currentTimeMillis();
        log.info("➡ Entering {}", method);
        Object result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - start;
        log.info("⬅ Exiting {} | ExecutionTime={}ms", method, timeTaken);
        
        return result;
	}
	
	@AfterThrowing(
		    pointcut = "execution(* com.banking.accountService.services.*.*(..))",
		    throwing = "ex"
		)
		public void logExceptions(JoinPoint jp, Exception ex) {
		    log.error("Exception in {} → {}",
		            jp.getSignature(),
		            ex.getMessage());
		}
	
	
	@Around("execution(* com.banking.accountService.controller.*.*(..))")
	public Object logController(ProceedingJoinPoint jp) throws Throwable {

	    log.info("API Called → {}", jp.getSignature());

	    return jp.proceed();
	}
	
}
