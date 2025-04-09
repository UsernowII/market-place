package com.market.statsservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class ItemRequest {

    private String id;
    private String title;
    private BigDecimal price;
    private String siteId;
    private String currencyId;
}
