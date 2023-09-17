package com.example.transfer.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author jack.wen
 * @since 2023/9/16 22:25
 */
@Component
@Aspect
public class LogUtils {

    @Pointcut("execution(public * com.example.transfer.service.impl.TransferServiceImpl.transfer(java.lang.String, java.lang.String, int))")
    public void pointcut1(){}

    @Before("pointcut1()")
    public void beforeMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        System.out.println("业务逻辑开始执行之前执行");
    }

    @AfterReturning(value = "pointcut1()", returning = "returnValue")
    public void afterReturnMethod(Object returnValue) {
        System.out.println("业务逻辑执行完毕后执行");
    }

    @AfterThrowing("pointcut1()")
    public void exceptionMethod() {
        System.out.println("异常时执行");
    }

    @After("pointcut1()")
    public void afterMethod() {
        System.out.println("业务逻辑结束时执行，无论异常与否都执行");
    }

    @Around("pointcut1()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知中的before method");
        Object result = null;
        try{
            // 控制原有业务逻辑是否执行
             result = proceedingJoinPoint.proceed();
        }catch(Exception e) {
            System.out.println("环绕通知中的exception method");
        }finally {
            System.out.println("环绕通知中的after method");
        }
        return result;
    }
}
