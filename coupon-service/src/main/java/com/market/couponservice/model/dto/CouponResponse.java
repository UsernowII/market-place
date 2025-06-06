package com.market.couponservice.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class CouponResponse {
    private List<Item> item_ids;
    private BigDecimal total;
}