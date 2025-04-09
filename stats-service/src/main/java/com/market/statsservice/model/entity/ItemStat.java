package com.market.statsservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class ItemStat {

    @Id
    private String id;

    private String title;

    private BigDecimal price;

    private String siteId;

    private String currencyId;

    @Column(nullable = false)
    private Integer redeemedCount;
}
