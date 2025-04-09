package com.market.couponservice.external.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemWrapper {
    private int code;
    private ItemResponse body;

}
