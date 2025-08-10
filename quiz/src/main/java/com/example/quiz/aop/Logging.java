package com.example.wipro.mouni.quiz.entity;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect 
@Component 
public class Logging {

    private static final Logger log = LoggerFactory.getLogger(Logging.class);

    @Before("execution(* com.example.quiz.controller.QuestionController.addUser(..))")
    public void logbeforeAddUser(JoinPoint joinPoint) {
        log.info("Logging something before addUser endpoint");
        log.info("Which method we are calling: {}", joinPoint.getSignature().getName());
        log.info("Logging something before addUser endpoint");
    }
}

