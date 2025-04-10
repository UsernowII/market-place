package com.market.couponservice.external.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ItemWrapper {
    private int code;
    private ItemResponse body;

}
