package com.example.note.enumeration;

import com.example.note.constant.MessageConst;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReturnCode {

    SUCCESS(0, MessageConst.API_SUCCESS),
    PRODUCT_NOT_FOUND(-1, MessageConst.PRODUCT_NOT_FOUND),
    PRODUCT_NAME_DUPLICATE(-2, MessageConst.PRODUCT_NAME_DUPLICATE),
    VALID_ERROR(-9998, MessageConst.VALID_ERROR),
    ERROR(-9999, MessageConst.API_ERROR);

    private int code;

    private String msg;

}
