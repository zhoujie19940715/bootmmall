package com.zhoujie.mall.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

//@Aspect
@Component
@Slf4j
public class RequestLogAspect {
    @Pointcut("execution (public * com.zhoujie.mall.controller..*.*(..))")
    public void webLog(){

    }

    /*@Before("webLog()")
    public void doBefore(Joinpoint joinpoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("URL："+request.getRequestURL().toString());

        log.info("IP："+request.getRemoteAddr());

    }*/

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret){
        log.info("RESPONSE:"+ret);
    }
}
