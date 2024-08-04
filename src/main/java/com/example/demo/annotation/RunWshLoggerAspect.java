package com.example.demo.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class RunWshLoggerAspect {
    @Before("@annotation(runWshLog)")
    public void log(JoinPoint joinPoint, RunWshLog runWshLog) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("请求前的日志信息：方法【 "+className+"."+methodName+"】,请求的参数为：" + Arrays.toString(args));
    }
    @AfterReturning(pointcut = "@annotation(runWshLog)", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, RunWshLog runWshLog, Object result) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("请求返回的日志信息：方法【" + className + "."+methodName+"】,请求的参数为：" + Arrays.toString(args)+"。返回的结果："+result);
    }
}
