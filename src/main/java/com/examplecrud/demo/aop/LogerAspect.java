package com.examplecrud.demo.aop;

import com.examplecrud.demo.aop.annotation.LogExecutionTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogerAspect.class);

    @Before("@annotation(logExecutionTime)")
    public void logExecutionTime(JoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable{
        LOGGER.info("start method: " + joinPoint.getSignature() + " arguments: " + Arrays.asList(joinPoint.getArgs()));
    }



}
