package com.musinsa.category.search.common;


import lombok.Getter;

@Getter
public enum ApiExceptionCode {

    CATEGORY_NOT_FOUND(404, "C001", "Category not found"),
    BRAND_NOT_FOUND(404, "B001", "Brand not found"),
    BRAND_NAME_DUPLICATE(409, "B001", "Brand name duplicate"),
    PRODUCT_NOT_FOUND(404, "P001", "Product not found"),


    DATA_INTEGRITY_VIOLATION(400, "E401", "Data integrity violation has occurred"),
    INVALID_DATA(400, "E402", "Invalid data provided"),
    INTERNAL_SERVER_ERROR(500, "E500", "Internal server error occurred"),
    ACCESS_DENIED(403, "E403", "Access is denied");

    private final int status;
    private final String code;
    private final String message;

    ApiExceptionCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
