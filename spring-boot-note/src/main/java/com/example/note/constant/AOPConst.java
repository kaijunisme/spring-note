package com.example.note.constant;

/**
 * Spring AOP 相關常數類
 */
public class AOPConst {

    private static final String POINTCUT_DEFINITION_PATH = "com.example.note.aop.pointcut.Pointcuts.";
    public static final String POINTCUT_CONTROLLER_LAYER = POINTCUT_DEFINITION_PATH + "controllerLayer()";
    public static final String POINTCUT_SERVICE_LAYER = POINTCUT_DEFINITION_PATH + "serviceLayer()";

}
