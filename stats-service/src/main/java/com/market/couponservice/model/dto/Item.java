package com.market.couponservice.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Item {

    private String id;
    private String title;
    private BigDecimal price;
    private String siteId;
    private String currencyId;
}
