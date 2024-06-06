package com.crm.aspects;

import com.crm.annotation.LoggingMethod;
import com.crm.exception.PermissionDeniedException;
import com.crm.service.SecurityService;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final SecurityService securityService;

    @Around(value = "args(login, password,..) && @annotation(loggingMethod)", argNames = "joinPoint,login,password,loggingMethod")
    public Object authAdvice(final ProceedingJoinPoint joinPoint, String login, String password, LoggingMethod loggingMethod)
            throws Throwable {
        log.info("start auth with params login: {}, password: {}", login, password);

        List<String> roles = Arrays.asList(loggingMethod.role());
        if (!securityService.checkUser(login, password, roles)) {
            throw new PermissionDeniedException("В доступе отказано");
        }

        return joinPoint.proceed();
    }
}
