package com.market.couponservice.service;

import com.market.couponservice.model.dto.CouponRequest;
import com.market.couponservice.model.dto.CouponResponse;

import java.util.function.Function;

public interface CouponService extends Function<CouponRequest, CouponResponse> {

}
