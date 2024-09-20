package com.musinsa.category.search.common;

public class ApiException extends RuntimeException {

    private final ApiExceptionCode code;

    public ApiException(ApiExceptionCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public ApiException(ApiExceptionCode code, String message) {
        super(message);
        this.code = code;
    }

    public ApiExceptionCode getExceptionCode() {
        return code;
    }
}
