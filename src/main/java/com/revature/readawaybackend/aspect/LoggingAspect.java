package com.revature.readawaybackend.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Before(value = "execution(* com.revature.readawaybackend.*.*.*(..))")
  public void beforeAdvice(JoinPoint joinPoint) {
    logger.info("Before method: " + joinPoint.getSignature());
  }

  @After(value = "execution(* com.revature.readawaybackend.*.*.*(..))")
  public void afterAdvice(JoinPoint joinPoint) {
    logger.info("After method: " + joinPoint.getSignature());
  }

  @AfterThrowing(value = "execution(* com.revature.readawaybackend.*.*.*(..))", throwing = "e")
  public void exceptionAdvice(JoinPoint joinPoint, Throwable e) {
    logger.warn("Exception: " + e.getMessage());
  }
}
