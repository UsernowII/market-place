package com.market.couponservice.external.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
public class ItemResponse {
    private String id;
    private String title;
    private BigDecimal price;

    @JsonProperty("site_id")
    private String siteId;

    @JsonProperty("currency_id")
    private String currencyId;

}
