package io.github.hizhangbo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Bob
 * @date 2020-02-24 11:51
 */
@Aspect
@Component
public class LogAspect {

    private long mills;
    private String methodName;

    @Pointcut("execution(* io.github.hizhangbo.service..*.*(..))")
    private void pointcut() {
    }

//    @Pointcut("within(io.github.hizhangbo.service..*)")
//    private void pointcut() {
//    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        methodName = signature.getName();


        mills = System.currentTimeMillis();
        System.out.println(methodName + "@Before...");

    }

    @After("pointcut()")
    public void after() {

        mills = System.currentTimeMillis() - mills;
        System.out.println(methodName + "@After...");
        System.out.println("execute time:" + mills + "ms");
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println(methodName + "@AfterReturning...");
    }

//    @Around("pointcut()")
//    public void around() {
//        System.out.println("@Around...");
//    }

    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        System.out.println(methodName + "@AfterThrowing...");
        System.out.println(ex.toString());
    }
}
