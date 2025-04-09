package com.market.couponservice.util;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    AUTHENTICATION("ERR_AUTH_001", "Error to get token"),

    INVALID_COUPON("ERR_COUPON_001", "Invalid coupon parameters."),

    GENERIC_ERROR("ERR_GEN_001", "An unexpected error occurred.");


    private final String code;
    private final String message;


    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
