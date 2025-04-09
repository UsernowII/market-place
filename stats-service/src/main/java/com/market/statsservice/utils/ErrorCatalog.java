package com.market.statsservice.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
    INVALID_ITEM("ERR_ITEM_001", "Invalid item parameters."),

    GENERIC_ERROR("ERR_GEN_001", "An unexpected error occurred.");


    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
