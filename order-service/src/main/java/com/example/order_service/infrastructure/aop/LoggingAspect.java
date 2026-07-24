package com.example.order_service.infrastructure.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // Intercepta cualquier metodo dentro del paquete application.usecase
    @Around("execution(* com.example.order_service.application.usecase..*.execute(..))")
    public Object logUseCaseExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info(">> Ejecutando {}.{}() con argumentos: {}",
                className, methodName, Arrays.toString(args));

        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;

            log.info("<< {}.{}() finalizó en {} ms", className, methodName, duration);
            return result;

        } catch (Exception ex) {
            long duration = System.currentTimeMillis() - startTime;
            log.error("xx {}.{}() falló en {} ms - Excepción: {}",
                    className, methodName, duration, ex.getMessage());
            throw ex;
        }
    }

}