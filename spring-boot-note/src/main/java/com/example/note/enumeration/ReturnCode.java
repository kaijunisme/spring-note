package com.example.note.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReturnCode {

    SUCCESS(0, "API SUCCESS"),
    PRODUCT_NOT_FOUND(-1, "查無相關Product 資料"),
    PRODUCT_NAME_DUPLICATE(-2, "已有相同名稱之Product 資料"),
    ERROR(-9999, "未知的錯誤");

    private int code;

    private String msg;

}
