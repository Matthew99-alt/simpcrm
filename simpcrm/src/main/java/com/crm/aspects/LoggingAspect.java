package com.crm.aspects;

import com.crm.entity.User;
import com.crm.exception.PermissionDeniedException;
import com.crm.reposotiry.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final UserRepository userRepository;

    @Pointcut("execution(* com.crm.controller.UserController.*(..)) && args(.., @RequestHeader login, @RequestHeader password)")
    public void allMethods(String login, String password) {}

    @Before("allMethods(login, password)")
    public void checkPermissions(JoinPoint joinPoint, String login, String password) {
        logger.debug("Checking permissions for method: {}", joinPoint.getSignature());
        logger.debug("Login: {}, Password: {}", login, password);
        User user = userRepository.findByLoginAndPassword(login, password)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        logger.debug("User found: {}", user);

        if (!"admin".equals(user.getRole())) {
            logger.warn("Permission denied for user: {}", user);
            throw new PermissionDeniedException("Access Denied");
        }
    }

    @Pointcut("execution(* com.crm.controller.OrderController.*(..)) && args(.., @RequestHeader login, @RequestHeader password)")
    public void orderMethods(String login, String password) {}

    @Before("orderMethods(login, password)")
    public void checkOrderPermissions(JoinPoint joinPoint, String login, String password) {
        logger.debug("Checking order permissions for method: {}", joinPoint.getSignature());
        logger.debug("Login: {}, Password: {}", login, password);
        User user = userRepository.findByLoginAndPassword(login, password)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        logger.debug("User found: {}", user);

        if (!"admin".equals(user.getRole()) && !"user".equals(user.getRole())) {
            logger.warn("Permission denied for user: {}", user);
            throw new PermissionDeniedException("Access Denied");
        }
    }
}
