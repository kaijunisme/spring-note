package com.example.note.constant;

/**
 * i18n 訊息設定常數類
 */
public class MessageConst {

    public static final String API_SUCCESS = "return.message.api.success";

    public static final String PRODUCT_NOT_FOUND = "return.message.business.exception.ProductNotFound";
    public static final String PRODUCT_NAME_DUPLICATE = "return.message.business.exception.ProductNameDuplicate";

    public static final String VALID_ERROR = "return.message.validation.constraints.default";
    public static final String PRODUCT_NAME_NOT_BLANK = "return.message.validation.constraints.ProductDto.name.NotBlank";
    public static final String PRODUCT_PRICE_NOT_NULL = "return.message.validation.constraints.ProductDto.price.NotNull";
    public static final String PRODUCT_PRICE_MIN = "return.message.validation.constraints.ProductDto.price.Min";
    public static final String PRODUCT_STOCK_NOT_NULL = "return.message.validation.constraints.ProductDto.stock.NotNull";
    public static final String PRODUCT_STOCK_MIN = "return.message.validation.constraints.ProductDto.stock.Min";
    public static final String PRODUCT_STATUS_NOT_BLANK = "return.message.validation.constraints.ProductDto.status.NotBlank";
    public static final String PRODUCT_STATUS_PATTERN = "return.message.validation.constraints.ProductDto.status.Pattern";

    public static final String API_ERROR = "return.message.api.error";

}
