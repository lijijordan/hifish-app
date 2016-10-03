package com.hifish.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * The type Rest controller aspect.
 */
@Aspect
@Component(value = "restControllerAspect")
public class RestControllerAspect {

    /**
     * Log before rest call.
     *
     * @param pjp the pjp
     * @throws Throwable the throwable
     */
    @Before("execution(public * com.hifish..app.api.rest.*Controller.*(..))")
    public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
        System.out.println(":::::AOP Before REST call:::::" + pjp);
    }
}
