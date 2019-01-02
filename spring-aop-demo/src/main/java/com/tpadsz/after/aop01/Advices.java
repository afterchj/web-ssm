package com.tpadsz.after.aop01;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;


/**
 * Created by hongjian.chen on 2019/1/2.
 */
public class Advices {

    public void before(JoinPoint jp){
        System.out.println("----------doBefore----------");
        System.out.println(jp.getSignature().getName());
    }

    //环绕通知
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(pjp.getSignature().getName());
        System.out.println("----------around pre----------");
        Object result = pjp.proceed();
        System.out.println("----------around post----------");
        return result;
    }


    //返回结果通知
    public void afterReturning(JoinPoint jp, Object result) {
        System.out.println(jp.getSignature().getName());
        System.out.println("result=" + result);
        System.out.println("----------return result----------");
    }


    //异常后通知
    public void afterThrowing(JoinPoint jp, Exception exp) {
        System.out.println(jp.getSignature().getName());
        System.out.println("异常消息：" + exp.getMessage());
        System.out.println("----------异常通知----------");
    }

    public void after(JoinPoint jp){
        System.out.println("----------doAfter----------");
    }
}
