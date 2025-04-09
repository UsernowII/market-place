package com.market.couponservice.controller;

import com.market.couponservice.model.dto.CouponRequest;
import com.market.couponservice.model.dto.CouponResponse;
import com.market.couponservice.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
@Log4j2
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public String healthCheck() {
       return "OK";
    }

    @PostMapping("/apply")
    public ResponseEntity<CouponResponse> applyCoupon(@Valid @RequestBody CouponRequest request) {
        log.info("Request from coupon service");
        CouponResponse response = couponService.apply(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
