package com.example.note.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 定義Spring AOP 切入點
 */
public class Pointcuts {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerLayer() {
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceLayer() {
    }

}
