package com.example.student.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Log before batch job starts
    @Before("execution(* com.example.student.batch.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Starting: " + joinPoint.getSignature().toShortString());
    }

    // Log after batch job completes
    @After("execution(* com.example.student.batch.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Completed: " + joinPoint.getSignature().toShortString());
    }

    // Log execution time for batch job methods
    @Around("execution(* com.example.student.batch.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;
        logger.info(joinPoint.getSignature().toShortString() + " executed in " + executionTime + "ms");
        return result;
    }

    // Log exceptions in batch processing
    @AfterThrowing(pointcut = "execution(* com.example.student.batch.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in " + joinPoint.getSignature().toShortString() + " with message: " + exception.getMessage());
    }
}
