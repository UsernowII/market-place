package com.market.statsservice.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String id;
    private String title;
    private BigDecimal price;
    private String siteId;
    private String currencyId;
}
