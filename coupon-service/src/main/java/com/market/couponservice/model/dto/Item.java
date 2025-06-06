package com.market.couponservice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
public class Item {

    private String id;
    private String title;
    private BigDecimal price;
    private String siteId;
    private String currencyId;
}
