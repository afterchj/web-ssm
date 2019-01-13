package com.tpadsz.after.aop02;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by hongjian.chen on 2019/1/2.
 */
@Component
@Aspect
public class Advices {

    @Before("execution(* com.tpadsz.after.aop02.MathService.*(..))")
    public void before(JoinPoint jp) {
        System.out.println("----------前置通知----------");
        System.out.println(jp.getSignature().getName());
    }

    //环绕通知
    @Around("execution(* com.tpadsz.after.aop02.MathService.s*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(pjp.getSignature().getName());
        System.out.println("----------环绕前置----------");
        Object result = pjp.proceed();
        System.out.println("----------环绕后置----------");
        return result;
    }


    //返回结果通知
    @AfterReturning(pointcut = "execution(* com.tpadsz.after.aop02.MathService.m*(..))", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        System.out.println(jp.getSignature().getName());
        System.out.println("结果是：" + result);
        System.out.println("----------返回结果----------");
    }

    //异常后通知
    @AfterThrowing(pointcut = "execution(* com.tpadsz.after.aop02.MathService.d*(..))", throwing = "exp")
    public void afterThrowing(JoinPoint jp, Exception exp) {
        System.out.println(jp.getSignature().getName());
        System.out.println("异常消息：" + exp.getMessage());
        System.out.println("----------异常通知----------");
    }

    @After("execution(* com.tpadsz.after.aop02.*.*(..))")
    public void after(JoinPoint jp) {
        System.out.println("----------最终通知----------");
        System.out.println(jp.getSignature().getName());
    }


}
