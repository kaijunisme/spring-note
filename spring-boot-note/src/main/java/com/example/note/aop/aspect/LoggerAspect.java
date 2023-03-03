package com.example.note.aop.aspect;

import com.example.note.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.example.note.constant.AOPConst.POINTCUT_CONTROLLER_LAYER;
import static com.example.note.constant.AOPConst.POINTCUT_SERVICE_LAYER;

/**
 * 紀錄控制器層與業務邏輯層方法執行狀況與內容
 */
@Component
@Aspect
@Slf4j
public class LoggerAspect {

    /**
     * 在控制器切入點及業務邏輯層切入點執行前執行
     *
     * @Before 在pointcut 指定方法執行前執行
     * @param joinPoint
     */
    @Before(value = POINTCUT_CONTROLLER_LAYER + "||" + POINTCUT_SERVICE_LAYER)
    public void logBeforeController(JoinPoint joinPoint) {
        String args = Arrays.stream(joinPoint.getArgs())
                .filter(e -> !(e instanceof byte[]))
                .map(JsonUtil::convertObjectToJsonString)
                .collect(Collectors.joining(", "));

        log.info("{}.{}({}) 開始執行...", getClassName(joinPoint), getMethodName(joinPoint), args);
    }

    /**
     * 在控制器切入點及業務邏輯層切入點執行後執行
     *
     * @After 在pointcut 指定方法執行後執行
     * @param joinPoint
     */
    @After(value = POINTCUT_CONTROLLER_LAYER + "||" + POINTCUT_SERVICE_LAYER)
    public void logAfterController(JoinPoint joinPoint) {
        log.info("{}.{}() 執行結束...", getClassName(joinPoint), getMethodName(joinPoint));
    }

    /**
     * 在控制器切入點及業務邏輯層切入點執行前後執行
     *
     * @Around 在pointcut 指定方法執行前後執行
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_CONTROLLER_LAYER + "||" + POINTCUT_SERVICE_LAYER)
    public Object logAroundController(ProceedingJoinPoint jp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = jp.proceed();

        log.info("{}.{}() 花費時間: {}ms", getClassName(jp), getMethodName(jp), System.currentTimeMillis() - beginTime);

        return result;
    }

    /**
     * 在控制器切入點及業務邏輯層切入點拋出例外時執行
     *
     * @AfterThrowing 在pointcut 指定方法拋出例外時執行
     * @param joinPoint
     * @param throwable
     */
    @AfterThrowing(value = POINTCUT_CONTROLLER_LAYER + "||" + POINTCUT_SERVICE_LAYER, throwing = "throwable")
    public void logAfterThrowException(JoinPoint joinPoint, Throwable throwable) {
        log.info("{}.{} 拋出例外...", getClassName(joinPoint), getMethodName(joinPoint));
        log.info("錯誤訊息: {}", throwable.getMessage());
    }

    /**
     * 取得請求方法類別
     *
     * @param joinPoint
     * @return
     */
    private String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }

    /**
     * 取得請求方法名稱
     *
     * @param joinPoint
     * @return
     */
    private String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

}
