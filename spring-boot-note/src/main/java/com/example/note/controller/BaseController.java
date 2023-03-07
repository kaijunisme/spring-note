package com.example.note.controller;

import com.example.note.dto.ResponseDto;
import com.example.note.enumeration.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Controller 共用方法
 */
public class BaseController {

    @Autowired
    private MessageSource messageSource;

    /**
     * 統一建立API 執行成功時的回傳物件
     *
     * @param data
     * @param <T>
     * @return
     */
    protected <T> ResponseDto<T> ok(T data) {
        return ResponseDto.of(
                ReturnCode.SUCCESS,
                messageSource.getMessage(ReturnCode.SUCCESS.getMsg(), null, LocaleContextHolder.getLocale()),
                data
        );
    }

}
