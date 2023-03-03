package com.example.note.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json 工具類
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 將物件轉換為Json 字串
     *
     * @param obj
     * @return
     */
    public static String convertObjectToJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("將物件轉換為Json 字串時發生錯誤");
        }
    }

}
