package cn.ylw.microservice.client.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 切面
 * 只有Around方法可以改变返回结果，其他方法只能做处理
 *
 * @author yanluwei
 * @date 2021/4/30
 */
@Aspect
@Component
public class UserControllerAspect {

    @Pointcut("execution(* cn.ylw.microservice.client.controller.UserController.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String name = methodSignature.getMethod().getName();
        System.out.println("执行方法：" + name);
        Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        System.out.println(proceed);
        return "aop执行成功";
    }

    @AfterReturning(value = "pointCut()", returning = "test")
    public void after(String test) {
        System.out.println("after: " + test);
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void exception(Exception e) {
        System.out.println(e.getMessage());
    }
}
