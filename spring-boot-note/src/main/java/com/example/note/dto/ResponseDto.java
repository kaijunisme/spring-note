package com.example.note.dto;

import com.example.note.enumeration.ReturnCode;
import lombok.Getter;
import lombok.Setter;

/**
 * RESTful API 回應物件公版
 *
 * @param <T>
 */
@Getter
@Setter
public class ResponseDto<T> {

    /**
     * 回傳代碼
     */
    private int returnCode;

    /**
     * 回傳訊息
     */
    private String returnMsg;

    /**
     * 回傳資料
     */
    private T data;

    /**
     * 預設API 成功時的回傳值
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> ok(T data) {
        return of(ReturnCode.SUCCESS, data);
    }

    /**
     * 僅設定回應物件ReturnCode 資料
     *
     * @param returnCode
     * @return
     */
    public static ResponseDto error(ReturnCode returnCode) {
        return of(returnCode, null);
    }

    /**
     * 根據傳入資料設定回應物件內容
     *
     * @param returnCode
     * @return
     */
    public static <T> ResponseDto<T> of(ReturnCode returnCode, T data) {
        ResponseDto<T> dto = new ResponseDto<T>();
        dto.setReturnCode(returnCode.getCode());
        dto.setReturnMsg(returnCode.getMsg());
        dto.setData(data);

        return dto;
    }

    /**
     * 根據傳入資料設定回應物件內容
     *
     * @param returnCode
     * @return
     */
    public static <T> ResponseDto<T> of(ReturnCode returnCode, String returnMsg, T data) {
        ResponseDto<T> dto = new ResponseDto<T>();
        dto.setReturnCode(returnCode.getCode());
        dto.setReturnMsg(returnMsg);
        dto.setData(data);

        return dto;
    }

}
