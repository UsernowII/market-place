package com.market.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/couponServiceFallBack")
    public String couponServiceFallback () {
        return "Coupon Service is down";
    }

    @GetMapping("/statsServiceFallBack")
    public String statsServiceFallback () {
        return "Stats Service is down";
    }

}
