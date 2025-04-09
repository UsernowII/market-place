package com.market.couponservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class CouponRequest {

    @NotNull(message = "The field item_ids cannot be null.")
    private List<@NotBlank(message = "The field name cannot be empty or null.") String> item_ids;
    @NotNull(message = "The field amount cannot be null.")
    private BigDecimal amount;

}
